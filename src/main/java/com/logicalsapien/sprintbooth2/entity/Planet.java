package com.logicalsapien.sprintbooth2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Planet Entity Class.
 */
@Entity
public class Planet {

  /**
   * Id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Planet Name.
   */
  private String name;

  /**
   * No Of Moons.
   */
  private Integer numberOfMoons;

  /**
   * Get id.
   * @return Get Id.
   */
  public Long getId() {
    return id;
  }

  /**
   * Set id.
   * @param idA Id to Set
   */
  public void setId(final Long idA) {
    this.id = idA;
  }

  /**
   * Get name.
   * @return Name
   */
  public String getName() {
    return name;
  }

  /**
   * Set name.
   * @param nameA Name to Set
   */
  public void setName(final String nameA) {
    this.name = nameA;
  }

  /**
   * Get Number of moons.
   * @return Number of Moons.
   */
  public Integer getNumberOfMoons() {
    return numberOfMoons;
  }

  /**
   * Set number  of moons.
   * @param numberOfMoonsA Number of moons to Set
   */
  public void setNumberOfMoons(final Integer numberOfMoonsA) {
    this.numberOfMoons = numberOfMoonsA;
  }
}
