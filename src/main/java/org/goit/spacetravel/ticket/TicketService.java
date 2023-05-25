package org.goit.spacetravel.ticket;

import org.goit.spacetravel.exception.NullOutputException;

import java.util.List;

public interface TicketService {
    void getTicketById(int ticketId) throws NullOutputException;
    void updateTicket(int ticketId, String planetFrom, String planetTo) throws NullOutputException;
    List<Ticket> getAllTickets();
    void createTicket(String createdAt, int clientId, String planetFrom, String planetTo);
    void deleteTicketById(int ticketId) throws NullOutputException;
}
