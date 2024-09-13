package main.products.ticket;

import lombok.Getter;
import main.enums.Sector;
import java.util.*;

@Getter
public class TicketManager {

    // Store Tickets, MAX 10 Tickets in void addTickets
    private Map<String, Ticket> tickets = new HashMap<>();

    /**
     * Adds a ticket to the collection.
     * If the collection already contains 10 tickets, an IllegalArgumentException is thrown.
     */
    public void addTicket(Ticket ticket) {
        if (tickets.size() >= 10) {
            throw new IllegalArgumentException("The Map can store only 10 tickets.");
        }
        tickets.put(ticket.getId(), ticket);
    }

    /**
     * Return a ticket by ID
     */
    public Ticket getTicketById(String id) {
        return tickets.get(id);
    }

    /**
     * Generate and add ten tickets to the map tickets.
     */
    public void generateTickets() {
        long eventTime;
        Random random = new Random();
        boolean isPromo;
        for (int i = 0; i < 10; i++) {
            isPromo = random.nextBoolean();
            eventTime = System.currentTimeMillis();
            Sector sector = Sector.values()[i % Sector.values().length];
            String eventCode = String.format("%03d", i + 1);
            Ticket ticket = new Ticket("ID" + (i + 1),
                    "Hall-" + (i + 1),
                    eventCode,
                    eventTime,
                    isPromo,
                    sector,
                    5.5);
            addTicket(ticket);
        }
    }

    /**
     * This method iterates through all tickets in the provided map
     * and returns tickets according to their sector.
     */
    public List<Ticket> getTicketsByStadiumSector(Sector sector) {
        List<Ticket> resultTickets = new ArrayList<>();
        for (Ticket ticket : tickets.values()) {
            if (ticket.getSector() == sector) {
                resultTickets.add(ticket);
            }
        }
        return resultTickets;
    }
}