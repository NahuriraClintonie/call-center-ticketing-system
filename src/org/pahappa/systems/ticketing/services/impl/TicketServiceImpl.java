package org.pahappa.systems.ticketing.services.impl;

import org.pahappa.systems.ticketing.constants.TicketStatus;
import org.pahappa.systems.ticketing.models.Ticket;
import org.pahappa.systems.ticketing.services.TicketService;

import java.util.ArrayList;
import java.util.List;

public class TicketServiceImpl implements TicketService {
    private List<Ticket> ticketList = new ArrayList<>();
    public TicketServiceImpl(){

    }

    public TicketServiceImpl(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @Override
    public void createTicket(Ticket ticket) {
        
        ticketList.add(ticket);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketList;
    }

    @Override
    public List<Ticket> getTicketsOfStatus(TicketStatus ticketStatus) {
    List<Ticket> filteredTickets = new ArrayList<>();
    for (Ticket ticket : ticketList) {
          if (ticket.getStatus() == ticketStatus.toString()) {
             filteredTickets.add(ticket);
      }
    }
       return filteredTickets;
    }

    @Override
    public void updateTicket(Ticket updatedTicket) {
        // Find the ticket in the ticketList based on a unique identifier (e.g., ticket ID)
    for (int i = 0; i < ticketList.size(); i++) {
    Ticket ticket = ticketList.get(i);
    if (ticket.getTicketId().equals(updatedTicket.getTicketId())) {
    // Update the ticket attributes with the values from updatedTicket
    ticket.setCustomerName(updatedTicket.getCustomerName());
    ticket.setContactInfo(updatedTicket.getContactInfo());
    ticket.setTicketCategory(updatedTicket.getTicketCategory());
    ticket.setDescription(updatedTicket.getDescription());
    ticket.setStatus(updatedTicket.getStatus());
    ticket.setPriority(updatedTicket.getPriority());
    // You can update other attributes as per your Ticket class definition
    // Display success message
    System.out.println("Ticket updated successfully!");
    return;
    }
    }
    // If the ticket is not found, display an error message
    System.out.println("Ticket not found. Update failed.");
    
    }

    @Override
    public void deleteTicket(int index) {
        if (index >= 0 && index < ticketList.size()) {
            ticketList.remove(index);
            System.out.println("Ticket deleted successfully!");
            } else {
            System.out.println("Invalid index. Deletion failed.");
            }
    }
    }
