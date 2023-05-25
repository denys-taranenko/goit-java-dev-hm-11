package org.goit.spacetravel.ticket;

import org.goit.spacetravel.client.Client;
import org.goit.spacetravel.exception.NullOutputException;
import org.goit.spacetravel.hibernate_util.HibernateUtil;
import org.goit.spacetravel.planet.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TicketCrudService implements TicketService{

    HibernateUtil util = HibernateUtil.getInstance();

    @Override
    public void getTicketById(int ticketId) throws NullOutputException {
        try (Session session = util.getSessionFactory().openSession()){
            Ticket existingTicket = session.get(Ticket.class, ticketId);
            if (existingTicket == null) {
                throw new NullOutputException("The ticket with id " + ticketId + " does not exist.");
            } else {
                System.out.println(existingTicket);
            }
        }
    }

    @Override
    public void updateTicket(int ticketId, String planetFrom, String planetTo) throws NullOutputException {
        if (!planetFrom.matches("[A-Z0-9]*$") || !planetTo.matches("[A-Z0-9]*$")) {
            System.out.println("The planet ID should contain capital letters without special characters");
        }else {
            try(Session session = util.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                Ticket existingTicket = session.get(Ticket.class, ticketId);
                Planet planetFromID = session.get(Planet.class, planetFrom);
                Planet planetToID = session.get(Planet.class, planetTo);
                if (existingTicket == null) {
                    throw new NullOutputException("The client with id \" + ticketId + \" does not exists.");
                }else if (planetFromID == null) {
                    throw new NullOutputException("The planet of origin with id " + planetFromID + " does not exists.");
                } else if (planetToID == null) {
                    throw new NullOutputException("The destination planet with id " + planetToID + " does not exist.");
                } else {
                    existingTicket.setPlanetFrom(planetFromID);
                    existingTicket.setPlanetTo(planetToID);
                    session.persist(existingTicket);
                    System.out.println("The ticket with id " + ticketId + " was updated.");
                    transaction.commit();
                }
            }
        }
    }

    @Override
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets;
        try (Session session = util.getSessionFactory().openSession()){
            Query<Ticket> ticketQuery = session.createQuery(
                    "from Ticket",
                    Ticket.class
            );
            tickets = ticketQuery.list();
            return tickets;
        }
    }

    @Override
    public void createTicket(String createdAt, int clientId, String planetFrom, String planetTo) {
        if (!planetFrom.matches("[A-Z0-9]*$") || !planetTo.matches("[A-Z0-9]*$")) {
            System.out.println("The planet ID must contain capital letters without special characters.");
        } else {
            try (Session session = util.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                Client existingClient = session.get(Client.class, clientId);
                Planet planetFromID = session.get(Planet.class, planetFrom);
                Planet planetToID = session.get(Planet.class, planetTo);
                if (existingClient == null) {
                    System.out.println("The client with id " + clientId + " does not exists.");
                } else if (planetFromID == null) {
                    System.out.println("The planet of origin with id " + planetFromID + " does not exists.");
                } else if (planetToID == null) {
                    System.out.println("The destination planet with id " + planetToID + " does not exists.");
                } else {
                    Ticket newTicket = new Ticket();
                    newTicket.setCreatedAt(createdAt);
                    newTicket.setClient(existingClient);
                    newTicket.setPlanetFrom(planetFromID);
                    newTicket.setPlanetTo(planetToID);
                    session.persist(newTicket);
                    System.out.println("The new ticket has been created.\n" + newTicket);
                    transaction.commit();
                }
            }
        }
    }

    @Override
    public void deleteTicketById(int ticketId) throws NullOutputException {
        try (Session session = util.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, ticketId);
            if (ticket == null) {
                throw new NullOutputException("The ticket with id " + ticketId + " does not exist.");
            } else {
                session.remove(ticket);
                transaction.commit();
                System.out.println("The ticket with id " + ticketId + " was deleted.");
            }
        }
    }
}
