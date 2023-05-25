package org.goit.spacetravel.client;

import org.goit.spacetravel.exception.NullOutputException;

import java.util.List;

public interface ClientService {

    void createNewClient(Client client);
    void deleteClient(Client client) throws NullOutputException;

    void getClientById (int id) throws NullOutputException;

    void updateClient (Client client) throws NullOutputException;

    List<Client> getAllClients();
}
