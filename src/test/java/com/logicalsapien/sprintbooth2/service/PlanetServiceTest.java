package com.logicalsapien.sprintbooth2.service;

import com.logicalsapien.sprintbooth2.entity.Planet;
import com.logicalsapien.sprintbooth2.repository.PlanetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Planet Service Test.
 */
@SpringBootTest
class PlanetServiceTest {

  /**
   * Autowire PlanetService.
   */
  @Autowired
  private PlanetService planetService;

  /**
   * Create a mock implementation of the PlanetRepository.
   */
  @MockBean
  private PlanetRepository planetRepository;

  /**
   * Get all Planets test.
   */
  @Test
  @DisplayName("Get all Planets test")
  void getAllPlanetsTest() {
    Planet planet1 = new Planet();
    planet1.setName("Mercury");
    doReturn(List.of(planet1)).when(planetRepository).findAll();

    // Execute the service call
    List<Planet> returnedPlanets = planetService.getAllPlanets();

    // Assert the response
    Assertions.assertNotNull(returnedPlanets);
    Assertions.assertEquals(List.of(planet1), returnedPlanets);
  }

  /**
   * Get Planet by id test.
   */
  @Test
  @DisplayName("Get Planet by id test")
  void getPlanetByIdTest() {
    Planet planet1 = new Planet();
    planet1.setName("Mercury");
    doReturn(Optional.of(planet1)).when(planetRepository).findById(1L);

    // Execute the service call
    Planet returnedPlanet = planetService.getPlanetById(1L);

    // Assert the response
    Assertions.assertNotNull(returnedPlanet);
    Assertions.assertSame(planet1, returnedPlanet,
        "The planet returned was not the same as the mock");
  }

  /**
   * Save Planet test.
   */
  @Test
  @DisplayName("Save Planet test")
  void savePlanetTest() {
    Planet planet1 = new Planet();
    planet1.setName("Mercury");

    when(planetRepository.save(any())).thenAnswer(
        invocation -> {
          Object[] args = invocation.getArguments();
          Planet planetToSave = (Planet) args[0];
          planetToSave.setId(1L);
          return planetToSave;
        });

    // Execute the service call
    Planet returnedPlanet = planetService.savePlanet(planet1);

    // Assert the response
    Assertions.assertNotNull(returnedPlanet);
    Assertions.assertSame(planet1, returnedPlanet);
  }

  /**
   * Update Planet test.
   */
  @Test
  @DisplayName("Update Planet test")
  void updatePlanetByIdTest() {
    Planet planet1 = new Planet();
    planet1.setName("Mercury");

    doReturn(Optional.of(planet1)).when(planetRepository).findById(1L);

    when(planetRepository.save(any())).thenAnswer(
        invocation -> {
          Object[] args = invocation.getArguments();
          Planet planetToSave = (Planet) args[0];
          return planetToSave;
        });

    // Execute the service call
    Planet returnedPlanet = planetService.updatePlanetById(1L, planet1);

    // Assert the response
    Assertions.assertNotNull(returnedPlanet);
    Assertions.assertSame(planet1, returnedPlanet);
  }

  /**
   * Delete Planet by id test.
   */
  @Test
  @DisplayName("Delete Planet by id test")
  void deletePlanetByIdTest() {

    // Execute the service call
     planetService.deletePlanetById(1L);

    // Assert the response
    verify(planetRepository, times(1)).deleteById(1L);
  }

  /**
   * Get Planets by name containing test.
   */
  @Test
  @DisplayName("Get Planets by name containing test")
  void getPlanetByNameContainingTest() {
    Planet planet1 = new Planet();
    planet1.setName("Mercury");
    doReturn(List.of(planet1)).when(planetRepository)
        .findByNameContaining("Mer");

    // Execute the service call
    List<Planet> returnedPlanets
        = planetService.getPlanetByNameContaining("Mer");

    // Assert the response
    Assertions.assertNotNull(returnedPlanets);
    Assertions.assertEquals(List.of(planet1), returnedPlanets);
  }

  /**
   * Get Planets by name like test.
   */
  @Test
  @DisplayName("Get Planets by name like test")
  void getPlanetByNameLikeTest() {
    Planet planet1 = new Planet();
    planet1.setName("Mercury");
    doReturn(List.of(planet1)).when(planetRepository).findByNameLike("Mer");

    // Execute the service call
    List<Planet> returnedPlanets = planetService.getPlanetByNameLike("Mer");

    // Assert the response
    Assertions.assertNotNull(returnedPlanets);
    Assertions.assertEquals(List.of(planet1), returnedPlanets);
  }

}
