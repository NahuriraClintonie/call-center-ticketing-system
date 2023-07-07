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
        
    }

    @Override
    public void getTicketsOfStatus() {

    }

    @Override
    public void updateTicket() {

    }
    
    @Override
    public void deleteTicket() {

    }
}
