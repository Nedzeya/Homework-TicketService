import java.util.*;

public class TicketService {
    // Store Tickets, MAX 10 Tickets in void addTickets
    private static Map<String, Ticket> tickets = new HashMap<>();

    public static void main(String[] args) {
        try {
            Ticket emptyTicket = new Ticket();
            System.out.println(emptyTicket);

            Calendar calendar = Calendar.getInstance();
            calendar.set(2024, Calendar.AUGUST, 15, 12, 0, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            long eventTime = calendar.getTimeInMillis();

            Ticket fullTicket = new Ticket("ID01",
                    "Concert",
                    "001", eventTime,
                    true,
                    Sector.A,
                    5.5);
            System.out.println(fullTicket);

            calendar.set(2024, Calendar.AUGUST, 30, 16, 2, 0);
            long eventTime1 = calendar.getTimeInMillis();

            Ticket limitedTicket = new Ticket("Hall",
                    "002",
                    eventTime1);
            System.out.println(limitedTicket);
// Saving ticket price
            emptyTicket.toSaveTicketPrice(49.99);
            System.out.println(emptyTicket.toStringTicketPrice());
//Generating tickets
            generateTenTickets();
            //testing getTicketById
            System.out.println(getTicketById("ID1"));
            //testing getTicketsByStadiumSector
            List<Ticket> ticketsByStadiumSector = getTicketsByStadiumSector(Sector.A, tickets);
            System.out.println(ticketsByStadiumSector.size());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a ticket to the collection.
     * If the collection already contains 10 tickets, an  IllegalArgumentException is thrown.
     */
    private static void addTicket(Ticket ticket) {
        if (tickets.size() >= 10) {
            throw new IllegalArgumentException("The Map can store only 10 tickets.");
        }
        tickets.put(ticket.getId(), ticket);
    }

    /**
     * Return a ticket by ID
     */
    private static Ticket getTicketById(String id) {
        return tickets.get(id);
    }

    /**
     * Generate and add five tickets to the map tickets.
     */
    private static void generateTenTickets() {
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
    private static List<Ticket> getTicketsByStadiumSector(Sector sector, Map<String, Ticket> tickets) {
        List<Ticket> resultTickets = new ArrayList<>();
        for (Ticket ticket : tickets.values()) {
            if (ticket.getSector() == sector) {
                resultTickets.add(ticket);
            }
        }
        return resultTickets;
    }
}