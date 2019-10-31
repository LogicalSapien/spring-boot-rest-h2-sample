package com.logicalsapien.sprintbooth2.controller;

import com.logicalsapien.sprintbooth2.entity.Planet;
import com.logicalsapien.sprintbooth2.service.PlanetService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Planet Rest Controller.
 */
@RestController
@RequestMapping("/planet")
public class PlanetController {

  /**
   * Autowiring the Planet Service.
   */
  @Autowired
  private PlanetService planetService;

  /**
   * get Api to return list of all planets.
   * @return List of Planets
   */
  @GetMapping()
  public ResponseEntity<List<Planet>> getAllPlanets() {
    List<Planet> planetList = planetService.getAllPlanets();
    return new ResponseEntity<>(planetList, HttpStatus.OK);
  }

  /**
   * get Api to return the Planet by Id.
   * @param id Id
   * @return Planets
   */
  @GetMapping("/{id}")
  public ResponseEntity<Planet> getPlanetById(
          @PathVariable("id") final Long id) {
    Planet planet = planetService.getPlanetById(id);
    return new ResponseEntity<>(planet, HttpStatus.OK);
  }

  /**
   * Api to save new Planet.
   * @param planet Planet to add
   * @return Saved Planet
   */
  @PostMapping()
  public ResponseEntity<Planet> savePlanet(
          @RequestBody final  Planet planet) {
    Planet savedPlanet = planetService.savePlanet(planet);
    return new ResponseEntity<>(savedPlanet, HttpStatus.CREATED);
  }

  /**
   * Api to update an existing Planet.
   * @param id Id to update
   * @param planetToUpdate Planet to update
   * @return Updated Planet
   */
  @PutMapping("/{id}")
  public ResponseEntity<Planet> updatePlanetById(
          @PathVariable("id") final Long id,
          @RequestBody final Planet planetToUpdate) {
    Planet updatedPlanet
            = planetService.updatePlanetById(id, planetToUpdate);
    return new ResponseEntity<>(updatedPlanet, HttpStatus.OK);
  }

  /**
   * Api to delete a planet.
   * @param id Id to delete
   * @return Success Message
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePlanetById(
          @PathVariable("id") final Long id) {
    planetService.deletePlanetById(id);
    return new ResponseEntity<>("Success", HttpStatus.OK);
  }

  /**
   * get Api to return search planet by name containing.
   * @param searchString Search String
   * @return Search Result
   */
  @GetMapping("/search1/{searchString}")
  public ResponseEntity<List<Planet>> getPlanetByNameContaining(
          @PathVariable("searchString") final String searchString) {
    List<Planet> planetList
            = planetService.getPlanetByNameContaining(searchString);
    return new ResponseEntity<>(planetList, HttpStatus.OK);
  }

  /**
   * get Api to return search planet by name like.
   * @param searchString Search String
   * @return Search Result
   */
  @GetMapping("/search2/{searchString}")
  public ResponseEntity<List<Planet>> getPlanetByNameLike(
          @PathVariable("searchString") final String searchString) {
    List<Planet> planetList
            = planetService.getPlanetByNameLike(searchString);
    return new ResponseEntity<>(planetList, HttpStatus.OK);
  }
}
