package org.pahappa.systems.ticketing.views;

// import org.pahappa.systems.ticketing.services.TicketService;
import org.pahappa.systems.ticketing.services.impl.TicketServiceImpl;
import org.pahappa.systems.ticketing.constants.TicketStatus;
import org.pahappa.systems.ticketing.models.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketView implements BaseTicketView {

    // private final TicketService ticketService;
    private final TicketServiceImpl ticketServiceImpl;
    // private final Ticket ticket;
    private final Scanner scanner;
    private List<Ticket> ticketList = new ArrayList<>();

    public TicketView() {
        //this.ticket = new Ticket();
        this.scanner = new Scanner(System.in);
        ticketServiceImpl = new TicketServiceImpl();
    }

    @Override
    public void displayMenu() {
        System.out.println("********* Call Center Ticket System *********\n\n");
        boolean running = true;
        while (running) {
            System.out.println("Choose an operation:");
            System.out.println("1. Create Ticket");
            System.out.println("2. Get All Tickets");
            System.out.println("3. Get Tickets of Status");
            System.out.println("4. Update Ticket");
            System.out.println("5. Delete Ticket");
            System.out.println("6. Exit");
            System.out.println();

            int choice = getValidChoice();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    createTicket();
                    break;
                case 2:
                    getAllTickets();
                    break;
                case 3:
                    getTicketsOfStatus();
                    break;
                case 4:
                    updateTicket();
                    break;
                case 5:
                    deleteTicket();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");

            }
        }
    }

    @Override
    public void createTicket() {
        // Prompt the agent to enter ticket details
        String ticketId;
    
        System.out.println("Enter the ticket ID: ");
        ticketId = scanner.nextLine();
    
        // Check if the ticket ID already exists in the ticketList
        if (isTicketIdExists(ticketId)) {
            System.out.println("Ticket ID already exists. Please enter a unique ticket ID.");
            return;
        }
    
        System.out.println("Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.println("Enter contact information: ");
        String contactInfo = scanner.nextLine();
        System.out.println("Enter ticket category: ");
        String ticketCategory = scanner.nextLine();
        System.out.println("Enter brief description: ");
        String description = scanner.nextLine();
    
        String selectedStatus = displayMenuAndGetStatus();
        System.out.println("Enter ticket priority level: ");
        String priority = scanner.nextLine();
    
        // Create a new ticket object
        Ticket newTicket = new Ticket(ticketId, customerName, contactInfo, ticketCategory, description, selectedStatus, priority);
    
        // Display success message
        System.out.println("Ticket created successfully!");
        System.out.println("\n");
        ticketServiceImpl.createTicket(newTicket);
    }
    
    private boolean isTicketIdExists(String ticketId) {
        ticketList = ticketServiceImpl.getAllTickets();
        for (Ticket ticket : ticketList) {
            if (ticket.getTicketId().equals(ticketId)) {
                return true;
            }
        }
        return false;
    }

    private int getValidChoice() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                System.out.println();
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    private String displayMenuAndGetStatus(){
        // Display ticket status menu
        System.out.println("Select ticket status: ");
        for (TicketStatus status : TicketStatus.values()) {
            System.out.println(status.ordinal() + 1 + ". " + status);
        }
        int statusChoice = getValidChoice();
        
        // Get the selected ticket status
        TicketStatus[] statusValues = TicketStatus.values();
        TicketStatus selectedStatus = statusValues[statusChoice - 1];
        return selectedStatus.toString();
    }

    @Override
    public void getAllTickets() {
        ticketList = ticketServiceImpl.getAllTickets();
        for (Ticket ticket : ticketList) {
        System.out.println("Ticket ID: " + ticket.getTicketId());
        System.out.println("Customer Name: " + ticket.getCustomerName());
        System.out.println("Contact Information: " + ticket.getContactInfo());
        System.out.println("Ticket Category: " + ticket.getTicketCategory());
        System.out.println("Description: " + ticket.getDescription());
        System.out.println("Ticket Status: " + ticket.getStatus());
        System.out.println("Priority: " + ticket.getPriority());
        System.out.println();
       }
    }

    @Override
    public void getTicketsOfStatus() {
List<Ticket> filteredTickets;
Scanner scanner = new Scanner(System.in);
// Prompt the user to choose a status from the menu
System.out.println("Choose a status to filter tickets:");
TicketStatus[] statusValues = TicketStatus.values();
for (int i = 0; i < statusValues.length; i++) {
System.out.println((i + 1) + ". " + statusValues[i]);
}
int statusChoice = scanner.nextInt();
// Check if the status choice is within the valid range
if (statusChoice >= 1 && statusChoice <= statusValues.length) {
TicketStatus chosenStatus = statusValues[statusChoice - 1];
// Filter tickets by the chosen status using TicketServiceImpl
// TicketServiceImpl ticketService = new TicketServiceImpl();
filteredTickets = ticketServiceImpl.getTicketsOfStatus(chosenStatus);
// Print the filtered tickets
for (Ticket ticket : filteredTickets) {
System.out.println("Customer Name: " + ticket.getCustomerName());
System.out.println("Contact Information: " + ticket.getContactInfo());
System.out.println("Ticket Category: " + ticket.getTicketCategory());
System.out.println("Description: " + ticket.getDescription());
System.out.println("Status: " + ticket.getStatus());
System.out.println();
}
} else {
System.out.println("Invalid status choice.");
}
}

    @Override
    public void updateTicket() {
        // Display the available ticket IDs for selection
      System.out.println("Available ticket IDs:");
      List<Ticket> ticketList = ticketServiceImpl.getAllTickets();
      for (Ticket ticket : ticketList) {
           System.out.println(ticket.getTicketId());
    }

    // Prompt the user to enter the ticket ID to be updated
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the ticket ID to update: ");
    String ticketId = scanner.nextLine();

    // Find the ticket in the ticketList based on the provided ticket ID
    Ticket selectedTicket = null;
    for (Ticket ticket : ticketList) {
        if (ticket.getTicketId().equals(ticketId)) {
            selectedTicket = ticket;
            break;
       }
    }

    // Check if the ticket is found
    if (selectedTicket != null) {
    // Prompt the user to enter updated ticket details
         System.out.println("Enter updated customer name: ");
         String customerName = scanner.nextLine();
         System.out.println("Enter updated contact information: ");
         String contactInfo = scanner.nextLine();
         System.out.println("Enter updated ticket category: ");
         String ticketCategory = scanner.nextLine();
         System.out.println("Enter updated description: ");
         String description = scanner.nextLine();
         System.out.println("Enter updated status: ");
         String status = scanner.nextLine();
         System.out.println("Enter updated priority: ");
         String priority = scanner.nextLine();

         // Create an updated ticket object
        Ticket updatedTicket = new Ticket(ticketId,customerName, contactInfo, ticketCategory, description, status, priority);

         // Call the updateTicket method in the service layer to update the ticket
         ticketServiceImpl.updateTicket(updatedTicket);
         } else {
              System.out.println("Ticket not found.");
          }

    }
    
    @Override
    public void deleteTicket() {

    }
}
