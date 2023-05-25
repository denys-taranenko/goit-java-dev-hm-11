package org.goit.spacetravel.client;

import org.goit.spacetravel.exception.NullOutputException;
import org.goit.spacetravel.hibernate_util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ClientCrudService implements ClientService {

    HibernateUtil util = HibernateUtil.getInstance();

    @Override
    public void createNewClient(Client client) {
        if (client.getName().length() <= 3) {
            System.out.println("A new client cannot be created due to the restrictions," +
                    " the name must have at least 2 characters.");
        } else {
            try (Session session = util.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                Client newClient = new Client();
                newClient.setName(client.getName());
                session.persist(newClient);
                transaction.commit();
                System.out.println("The client with name " + client.getName() + " has been created.\n" + newClient);
            }
        }
    }

    @Override
    public void deleteClient(Client client) throws NullOutputException {
        try (Session session = util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Client existingClient = session.get(Client.class, client.getId());
            if (client == null) {
                throw new NullOutputException("The client with name " + client.getName() + " does not exists.");
            } else {
                session.remove(existingClient);
                transaction.commit();
                System.out.println("The client with name " + client.getName() + " was deleted.");
            }
        }
    }

    @Override
    public void getClientById(int id) throws NullOutputException {
        try (Session session = util.getSessionFactory().openSession()) {
            Client clientFromDB = session.get(Client.class, id);
            if (clientFromDB == null) {
                throw new NullOutputException("The client with id " + id + " does not exists.");
            } else {
                System.out.println(clientFromDB);
            }
        }
    }

    @Override
    public void updateClient(Client client) throws NullOutputException {
        if (client.getName().length() <= 3) {
            System.out.println("A client's name can't be updated due to the restrictions," +
                    " the name must consist of at least 3 characters.");
        } else {
            try (Session session = util.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                client = session.get(Client.class, client.getId());
                if (client == null) {
                    throw new NullOutputException("The client " + client + " does not exist.");
                } else {
                    client.setName(client.getName());
                    session.persist(client);
                    transaction.commit();
                    System.out.println("The client's name with id " + client.getId() + " was updated to " +
                            client.getName() + ".\n" + client);
                }
            }
        }
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients;
        try  (Session session = util.getSessionFactory().openSession()){
            Query<Client> clientQuery = session.createQuery(
                    "from Client",
                    Client.class
            );
            clients = clientQuery.list();
        }
        return clients;
    }
}
