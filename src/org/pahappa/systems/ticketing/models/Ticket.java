package org.pahappa.systems.ticketing.models;


/**
 * A ticket refers to a unit of work or a request that is submitted
 * by a user or customer to seek assistance, report an issue, or request a service.
 * It serves as a record or a container that captures all the relevant information
 * related to the user's request or issue.
 */
public class Ticket {
    private String ticketId;
    private String customerName;
    private String contactInfo;
    private String ticketCategory;
    private String description;
    private String status;
    private String priority;

    

    public Ticket(String ticketId, String customerName, String contactInfo, String ticketCategory, String description, String status, String priority) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.contactInfo = contactInfo;
        this.ticketCategory = ticketCategory;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getTicketCategory() {
        return ticketCategory;
    }

    public void setTicketCategory(String ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

        public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}