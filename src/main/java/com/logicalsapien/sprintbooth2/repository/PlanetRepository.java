package com.logicalsapien.sprintbooth2.repository;

import com.logicalsapien.sprintbooth2.entity.Planet;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PlanetRepository extends CrudRepository<Planet, Long> {

  /**
   * Method to return List of Planets with same substring in name.
   * Does the same job as below method, but different way of doing in Spring
   * @param value Value to check
   * @return List of Planel
   */
  List<Planet> findByNameContaining(String value);

  /**
   * Method to return List of Planets with same substring in name.
   * Does the same job as above method, but different way of doing in Spring
   * @param value Value to check
   * @return List of Planel
   */
  @Query("SELECT p FROM Planet p WHERE p.name LIKE %:value%")
  List<Planet> findByNameLike(@Param("value") String value);
}
