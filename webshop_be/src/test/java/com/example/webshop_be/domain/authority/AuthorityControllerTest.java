package com.example.webshop_be.domain.authority;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Transactional
    @DisplayName("Get one authority by id, expected 200 OK and the authority")
    @Sql("/authorityTestData.sql")
    @Test
    void getAuthorityByTheCorrectId_expectHTTPStatus200() throws Exception {

        //WHEN
        MvcResult res = mockMvc.perform(get("/authorities/4").accept(MediaType.APPLICATION_JSON))
                .andReturn();

        //THEN
        JSONAssert.assertEquals("{\"id\":\"4\",\"name\":\"USER_SEE_GLOBAL\"}",
                res.getResponse().getContentAsString(),
                JSONCompareMode.LENIENT);

        Assertions.assertEquals(200, res.getResponse().getStatus());
    }


    @Transactional
    @Sql("/authorityTestData.sql")
    @DisplayName("Get all authorities which exists, expected 200 OK and a list of authorities")
    @Test
    void getAllAuthoritiesThatAreAvailable_expectHTTPStatus200() throws Exception {

        //WHEN
        MvcResult res =
                mockMvc.perform(get("/authorities").accept(MediaType.APPLICATION_JSON)).andReturn();

        //THEN
        JSONArray jsonArray = new JSONArray(res.getResponse().getContentAsString());
        JSONObject jsonObject = jsonArray.getJSONObject(2);
        Assertions.assertEquals("3", jsonObject.get("id"));
        Assertions.assertEquals("USER_DEACTIVATE", jsonObject.get("name"));

        Assertions.assertEquals(200, res.getResponse().getStatus());

    }

    @Test
    @DisplayName("Update one Authority by ID which exists, expected 200 OK and the correct Authority")
    @Transactional
    @Sql("/authorityTestData.sql")
    void updateAuthorityByAuthorityId_expectHTTPStatus200() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        //GIVEN
        AuthorityDTO authority = new AuthorityDTO();
        authority.setName("Asics");

        String brandContent = objectMapper.writeValueAsString(authority);

        //WHEN
        MvcResult res = mockMvc.perform(
                        put("/authorities/3").contentType(MediaType.APPLICATION_JSON).content(brandContent)
                                .characterEncoding("utf-8"))
                .andReturn();

        //THEN
        Assertions.assertEquals(200, res.getResponse().getStatus());

    }

    @Test
    @DisplayName("Create one Authority, expected 200 OK and the correct Authority")
    @Transactional
    @Sql("/authorityTestData.sql")
    void createAuthority_expectHTTPStatus200() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        //GIVEN
        AuthorityDTO authority = new AuthorityDTO();
        authority.setName("Kappa");
        authority.setId("7");

        String authorityContent = objectMapper.writeValueAsString(authority);

        //WHEN
        MvcResult res = mockMvc.perform(
                        post("/authorities").contentType(MediaType.APPLICATION_JSON)
                                .content(authorityContent)
                                .characterEncoding("utf-8"))
                .andReturn();

        //THEN
        Assertions.assertEquals(201, res.getResponse().getStatus());

    }

    @Test
    @DisplayName("Delete one Authority by ID which exists, expected 200 OK and the correct Authority")
    @Transactional
    @Sql("/authorityTestData.sql")
    void deleteAuthorityByAuthorityId_expectHTTPStatus200() throws Exception {

        //WHEN
        MvcResult mockData =
                mockMvc.perform(delete("/authorities/2").accept(MediaType.APPLICATION_JSON))
                        .andReturn();

        //THEN
        Assertions.assertEquals(204, mockData.getResponse().getStatus());

    }
}