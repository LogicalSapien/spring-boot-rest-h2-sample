package com.logicalsapien.sprintbooth2.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

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
    assertThat(response.getContentAsString(),
        equalTo(getJsonFromObject(List.of(planet1))));
    verify(planetService, times(1)).getAllPlanets();
  }

  /**
   * Get planet by id test.
   * @throws Exception exception
   */
  @Test
  @DisplayName("Get planet by id test")
  public void getPlanetByIdTest() throws Exception {
    Planet planet1 = new Planet();
    planet1.setName("Mercury");
    when(planetService.getPlanetById(1L)).thenReturn(planet1);
    MockHttpServletResponse response = mockMvc.perform(get("/planet/1")
        .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    /* Assert */
    assertThat(response.getStatus(), equalTo(HttpStatus.OK.value()));
    assertThat(response.getContentAsString(),
        equalTo(getJsonFromObject(planet1)));
    verify(planetService, times(1)).getPlanetById(1L);
  }

  /**
   * Save planet test.
   * @throws Exception exception
   */
  @Test
  @DisplayName("Save planet test")
  public void savePlanetTest() throws Exception {
    Planet planet1 = new Planet();
    planet1.setName("Mercury");
    when(planetService.savePlanet(any())).thenAnswer(
        invocation -> {
          Object[] args = invocation.getArguments();
          Planet planetToSave = (Planet) args[0];
          planetToSave.setId(1L);
          return planetToSave;
        });
    MockHttpServletResponse response = mockMvc.perform(post("/planet")
        .contentType(MediaType.APPLICATION_JSON)
        .content(getJsonFromObject(planet1))
        .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    /* Assert */
    assertThat(response.getStatus(), equalTo(HttpStatus.CREATED.value()));
    // update expected
    planet1.setId(1L);
    assertThat(response.getContentAsString(),
        equalTo(getJsonFromObject(planet1)));
    verify(planetService, times(1)).savePlanet(any());
  }

  /**
   * Update planet test.
   * @throws Exception exception
   */
  @Test
  @DisplayName("Update planet test")
  public void updatePlanetByIdTest() throws Exception {
    Planet planet1 = new Planet();
    planet1.setName("Mercury");
    planet1.setId(1L);
    when(planetService.updatePlanetById(any(), any())).thenAnswer(
        invocation -> {
          Object[] args = invocation.getArguments();
          Planet planetToSave = (Planet) args[1];
          return planetToSave;
        });
    MockHttpServletResponse response = mockMvc.perform(put("/planet/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(getJsonFromObject(planet1))
        .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    /* Assert */
    assertThat(response.getStatus(), equalTo(HttpStatus.OK.value()));
    assertThat(response.getContentAsString(),
        equalTo(getJsonFromObject(planet1)));
    verify(planetService, times(1)).updatePlanetById(any(), any());
  }

  /**
   * Delete planet by id test.
   * @throws Exception exception
   */
  @Test
  @DisplayName("Delete planet by id test")
  public void deletePlanetByIdTest() throws Exception {
    MockHttpServletResponse response = mockMvc.perform(delete("/planet/1")
        .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    /* Assert */
    assertThat(response.getStatus(), equalTo(HttpStatus.OK.value()));
    assertThat(response.getContentAsString(), equalTo("Success"));
    verify(planetService, times(1)).deletePlanetById(1L);
  }

  /**
   * Get Planet by name containing test.
   * @throws Exception exception
   */
  @Test
  @DisplayName("Get Planet by name containing test")
  public void getPlanetByNameContainingTest() throws Exception {
    Planet planet1 = new Planet();
    planet1.setName("Mercury");
    when(planetService.getPlanetByNameContaining("Mer"))
        .thenReturn(List.of(planet1));
    MockHttpServletResponse response
        = mockMvc.perform(get("/planet/search1/Mer")
        .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    /* Assert */
    assertThat(response.getStatus(), equalTo(HttpStatus.OK.value()));
    assertThat(response.getContentAsString(),
        equalTo(getJsonFromObject(List.of(planet1))));
    verify(planetService, times(1)).getPlanetByNameContaining("Mer");
  }

  /**
   * Get Planet by name like test.
   * @throws Exception exception
   */
  @Test
  @DisplayName("Get Planet by name like test")
  public void getPlanetByNameLikeTest() throws Exception {
    Planet planet1 = new Planet();
    planet1.setName("Mercury");
    when(planetService.getPlanetByNameLike("Mer"))
        .thenReturn(List.of(planet1));
    MockHttpServletResponse response
        = mockMvc.perform(get("/planet/search2/Mer")
        .accept(MediaType.APPLICATION_JSON))
        .andReturn().getResponse();

    /* Assert */
    assertThat(response.getStatus(), equalTo(HttpStatus.OK.value()));
    assertThat(response.getContentAsString(),
        equalTo(getJsonFromObject(List.of(planet1))));
    verify(planetService, times(1)).getPlanetByNameLike("Mer");
  }

  /**
   * To Convert an Object to JSON String.
   * @param o Object
   * @return Object as String
   * @throws JsonProcessingException throws JsonProcessingException
   */
  private static String getJsonFromObject(
      final Object o) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    return mapper.writeValueAsString(o);
  }
}
