package org.goit.spacetravel;

import org.goit.spacetravel.client.Client;
import org.goit.spacetravel.client.ClientCrudService;
import org.goit.spacetravel.database_flyway_service.DatabaseFlyWayService;
import org.goit.spacetravel.exception.NullOutputException;
import org.goit.spacetravel.planet.Planet;
import org.goit.spacetravel.planet.PlanetCrudService;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException, NullOutputException {

        new DatabaseFlyWayService().initFlyWayDB();

        ClientCrudService clientCrudService = new ClientCrudService();

        Client client = new Client();
        client.setName("Tonks");
        clientCrudService.createNewClient(client);
        clientCrudService.deleteClient(client);

        PlanetCrudService planetCrudService = new PlanetCrudService();
        Planet planet = new Planet();
        planet.setId("SAT");
        planet.setName("Saturn");
        planetCrudService.createNewPlanet(planet);
        planetCrudService.deletePlanet(planet);


    }
}
