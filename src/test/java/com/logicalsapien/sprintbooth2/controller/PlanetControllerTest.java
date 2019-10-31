package com.logicalsapien.sprintbooth2.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicalsapien.sprintbooth2.entity.Planet;
import com.logicalsapien.sprintbooth2.service.PlanetService;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Planet Controller Tests.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = PlanetController.class)
public class PlanetControllerTest {

  /**
   * Mock Mvc.
   */
  @Autowired
  private MockMvc mockMvc;

  /**
   * Rest Template for testing the api.
   */
  @MockBean
  private PlanetService planetService;

  /**
   * List all Planets test.
   * @throws Exception exception
   */
  @Test
  @DisplayName("List all Planets test")
  public void listAllPlanetsTest() throws Exception {
    Planet planet1 = new Planet();
    planet1.setName("Mercury");
    when(planetService.getAllPlanets()).thenReturn(List.of(planet1));
    MockHttpServletResponse response = mockMvc.perform(get("/planet")
        .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    /* Assert */
    assertThat(response.getStatus(), equalTo(HttpStatus.OK.value()));
    assertThat(response.getContentAsString(), equalTo(getJsonFromObject(List.of(planet1))));
    verify(planetService,times(1)).getAllPlanets();
  }

  /**
   * To Convert an Object to JSON String.
   * @param o Object
   * @return Object as String
   * @throws JsonProcessingException throws JsonProcessingException
   */
  private static  String getJsonFromObject(Object o) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    return mapper.writeValueAsString(o);
  }
}
