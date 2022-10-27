package com.example.webshop_be.domain.brand;

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

@AutoConfigureMockMvc
@SpringBootTest
class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get all brands, expected 200 OK and an array of brands")
    @Transactional
    @Sql("/brandTestData.sql")
    void getAllBrands_expectHTTPStatus200() throws Exception {

        //WHEN
        MvcResult mockData =
                mockMvc.perform(get("/brands").accept(MediaType.APPLICATION_JSON)).andReturn();

        //THEN
        JSONArray jsonArray = new JSONArray(mockData.getResponse().getContentAsString());
        JSONObject jsonObject = jsonArray.getJSONObject(2);
        Assertions.assertEquals("3", jsonObject.get("id"));
        Assertions.assertEquals("Jordan", jsonObject.get("name"));

        Assertions.assertEquals(200, mockData.getResponse().getStatus());

    }

    @Test
    @DisplayName("Get one Brand by ID which exists, expected 200 OK and the correct Brand")
    @Transactional
    @Sql("/brandTestData.sql")
    void getBrandByBrandId_expectHTTPStatus200() throws Exception {

        //WHEN
        MvcResult mockData =
                mockMvc.perform(get("/brands/1").accept(MediaType.APPLICATION_JSON)).andReturn();

        //THEN
        JSONAssert.assertEquals("{\"id\":\"1\",\"name\":\"Nike\"}",
                mockData.getResponse().getContentAsString(), JSONCompareMode.LENIENT);

        Assertions.assertEquals(302, mockData.getResponse().getStatus());

    }

    @Test
    @DisplayName("Update one Brand by ID which exists, expected 200 OK and the correct Brand")
    @Transactional
    @Sql("/brandTestData.sql")
    void updateBrandByBrandId_expectHTTPStatus200() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        //GIVEN
        BrandDTO brand = new BrandDTO();
        brand.setName("Asics");

        String brandContent = objectMapper.writeValueAsString(brand);

        //WHEN
        MvcResult res = mockMvc.perform(
                        put("/brands/3").contentType(MediaType.APPLICATION_JSON).content(brandContent)
                                .characterEncoding("utf-8"))
                .andReturn();

        //THEN
        Assertions.assertEquals(200, res.getResponse().getStatus());

    }

    @Test
    @DisplayName("Create one Brand, expected 200 OK and the correct Brand")
    @Transactional
    @Sql("/brandTestData.sql")
    void createBrand_expectHTTPStatus200() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        //GIVEN
        BrandDTO brand = new BrandDTO();
        brand.setName("Kappa");
        brand.setId("7");

        String brandContent = objectMapper.writeValueAsString(brand);

        //WHEN
        MvcResult res = mockMvc.perform(
                        post("/brands").contentType(MediaType.APPLICATION_JSON).content(brandContent)
                                .characterEncoding("utf-8"))
                .andReturn();

        //THEN
        Assertions.assertEquals(201, res.getResponse().getStatus());

    }

    @Test
    @DisplayName("Delete one Brand by ID which exists, expected 200 OK and the correct Brand")
    @Transactional
    @Sql("/brandTestData.sql")
    void deleteBrandByBrandId_expectHTTPStatus200() throws Exception {

        //WHEN
        MvcResult mockData =
                mockMvc.perform(delete("/brands/2").accept(MediaType.APPLICATION_JSON)).andReturn();

        //THEN
        Assertions.assertEquals(204, mockData.getResponse().getStatus());

    }
}