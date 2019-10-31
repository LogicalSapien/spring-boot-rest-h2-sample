package com.logicalsapien.sprintbooth2.service;

import com.logicalsapien.sprintbooth2.entity.Planet;
import com.logicalsapien.sprintbooth2.repository.PlanetRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Plane Service Implementation.
 */
@Service
public class PlanetServiceImpl implements PlanetService {

  /**
   * Autowiring the Planet Repository.
   */
  @Autowired
  private PlanetRepository planetRepository;

  /**
   * Get All Planets.
   * @return List of all planets.
   */
  @Override
  public List<Planet> getAllPlanets() {
    return (List<Planet>) planetRepository.findAll();
  }

  /**
   * Get Planet By Id.
   * @param id Id
   * @return Planet
   */
  @Override
  public Planet getPlanetById(final Long id) {
    return planetRepository.findById(id).get();
  }

  /**
   * Save Planet.
   * @param planet Planet to save
   * @return Saved Planet
   */
  @Override
  public Planet savePlanet(final Planet planet) {
    return planetRepository.save(planet);
  }

  /**
   * Update Planet.
   * @param id Id
   * @param planetToUpdate Planet to Update
   * @return Updated Planet
   */
  @Override
  public Planet updatePlanetById(
          final Long id, final Planet planetToUpdate) {
    // Fetch the Planet from db
    Planet planetFromDb = planetRepository.findById(id).get();
    planetFromDb.setName(planetToUpdate.getName());
    planetFromDb.setNumberOfMoons(planetToUpdate.getNumberOfMoons());
    return planetRepository.save(planetFromDb);
  }

  /**
   * Delete Planet by Id.
   * @param id Id
   */
  @Override
  public void deletePlanetById(final Long id) {
    planetRepository.deleteById(id);
  }

  /**
   * Search Planet by name containing.
   * @param searchString SearchString
   * @return Search result
   */
  @Override
  public List<Planet> getPlanetByNameContaining(final String searchString) {
    return planetRepository.findByNameContaining(searchString);
  }

  /**
   * Search Planet by name like.
   * @param searchString SearchString
   * @return Search result
   */
  @Override
  public List<Planet> getPlanetByNameLike(final String searchString) {
    return planetRepository.findByNameLike(searchString);
  }
}
