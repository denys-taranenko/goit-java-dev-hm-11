package org.goit.spacetravel.planet;

import org.goit.spacetravel.exception.NullOutputException;

import java.util.List;

public interface PlanetService {

    void createNewPlanet(Planet planet);
    void deletePlanet(Planet planet) throws NullOutputException;
    void getPlanetById(String id) throws NullOutputException;
    void updatePlanet(String id, Planet planet) throws NullOutputException;
    List<Planet> getAllPlanets();
}
