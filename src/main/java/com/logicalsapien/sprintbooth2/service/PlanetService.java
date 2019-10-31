package com.logicalsapien.sprintbooth2.service;

import com.logicalsapien.sprintbooth2.entity.Planet;

import java.util.List;

/**
 * Planet Service.
 */
public interface PlanetService {

  /**
   * Get All Planets.
   * @return List of all planets.
   */
  List<Planet> getAllPlanets();

  /**
   * Get Planet By Id.
   * @param id Id
   * @return Planet
   */
  Planet getPlanetById(Long id);

  /**
   * Save Planet.
   * @param planet Planet to save
   * @return Saved Planet
   */
  Planet savePlanet(Planet planet);

  /**
   * Update Planet.
   * @param id Id
   * @param planetToUpdate Planet to Update
   * @return Updated Planet
   */
  Planet updatePlanetById(Long id, Planet planetToUpdate);

  /**
   * Delete Planet by Id.
   * @param id Id
   */
  void deletePlanetById(Long id);

  /**
   * Search Planet by name containing.
   * @param searchString SearchString
   * @return Search result
   */
  List<Planet> getPlanetByNameContaining(String searchString);

  /**
   * Search Planet by name like.
   * @param searchString SearchString
   * @return Search result
   */
  List<Planet> getPlanetByNameLike(String searchString);
}
