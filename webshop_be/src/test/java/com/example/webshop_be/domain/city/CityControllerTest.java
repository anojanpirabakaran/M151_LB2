package com.example.webshop_be.domain.city;

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
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get all cities, expected 200 OK and an array of cities")
    @Transactional
    @Sql("/cityTestData.sql")
    void getAllCities_expectHTTPStatus200() throws Exception {

        //WHEN
        MvcResult mockData =
                mockMvc.perform(get("/cities").accept(MediaType.APPLICATION_JSON)).andReturn();

        //THEN
        JSONArray jsonArray = new JSONArray(mockData.getResponse().getContentAsString());
        JSONObject jsonObject = jsonArray.getJSONObject(4);
        Assertions.assertEquals("5", jsonObject.get("id"));
        Assertions.assertEquals("Maennedorf", jsonObject.get("name"));
        Assertions.assertEquals(8708, jsonObject.get("postalCode"));

        Assertions.assertEquals(200, mockData.getResponse().getStatus());

    }

    @Test
    @DisplayName("Get one City by ID which exists, expected 200 OK and the correct City")
    @Transactional
    @Sql("/cityTestData.sql")
    void getCityByCityId_expectHTTPStatus200() throws Exception {

        //WHEN
        MvcResult mockData =
                mockMvc.perform(get("/cities/1").accept(MediaType.APPLICATION_JSON)).andReturn();

        //THEN
        JSONAssert.assertEquals("{\"id\":\"1\",\"name\":\"Niederlenz\",\"postalCode\":5702}",
                mockData.getResponse().getContentAsString(), JSONCompareMode.LENIENT);

        Assertions.assertEquals(200, mockData.getResponse().getStatus());

    }

    @Test
    @DisplayName("Update one City by ID which exists, expected 200 OK and the correct City")
    @Transactional
    @Sql("/cityTestData.sql")
    void updateCityByCityId_expectHTTPStatus200() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        //GIVEN
        CityDTO city = new CityDTO();
        city.setName("Zurich");
        city.setPostalCode(8004);

        String cityContent = objectMapper.writeValueAsString(city);

        //WHEN
        MvcResult res = mockMvc.perform(
                        put("/cities/3").contentType(MediaType.APPLICATION_JSON).content(cityContent)
                                .characterEncoding("utf-8"))
                .andReturn();

        //THEN
        Assertions.assertEquals(200, res.getResponse().getStatus());

    }

    @Test
    @DisplayName("Create one City, expected 201 OK and the correct City")
    @Transactional
    @Sql("/cityTestData.sql")
    void createCity_expectHTTPStatus200() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        //GIVEN
        CityDTO city = new CityDTO();
        city.setName("Zurich");
        city.setPostalCode(8004);
        city.setId("8");

        String cityContent = objectMapper.writeValueAsString(city);

        //WHEN
        MvcResult res = mockMvc.perform(
                        post("/cities/").contentType(MediaType.APPLICATION_JSON).content(cityContent)
                                .characterEncoding("utf-8"))
                .andReturn();

        //THEN
        Assertions.assertEquals(201, res.getResponse().getStatus());

    }

    @Test
    @DisplayName("Delete one City by ID which exists, expected 200 OK and the correct City")
    @Transactional
    @Sql("/cityTestData.sql")
    void deleteCityByCityId_expectHTTPStatus200() throws Exception {

        //WHEN
        MvcResult mockData =
                mockMvc.perform(delete("/cities/2").accept(MediaType.APPLICATION_JSON)).andReturn();

        //THEN
        Assertions.assertEquals(204, mockData.getResponse().getStatus());

    }
}