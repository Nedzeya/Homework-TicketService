import java.util.*;

public class TicketService {
    // Store Tickets, MAX 10 Tickets in void addTickets
    private Map<String, Ticket> tickets = new HashMap<>();

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

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a ticket to the collection.
     * If the collection already contains 10 tickets, an  IllegalArgumentException is thrown.
     */
    private void addTicket(Ticket ticket) {
        if (tickets.size() >= 10) {
            throw new IllegalArgumentException("The Map can store only 10 tickets.");
        }
        tickets.put(ticket.getId(), ticket);
    }
    /**
     * Return a ticket by ID
     */
    private Ticket getTicketById(String id) {
        return tickets.get(id);
    }

	// generate tickets
	private static Ticket[] getTickets() {
		long eventTime;
		Random random = new Random();
		boolean isPromo;
		Ticket[] tickets = new Ticket[9];

		for (int i = 0; i < tickets.length; i++) {

			isPromo = random.nextBoolean();
			eventTime = System.currentTimeMillis() / 1000;
			Sector sector = Sector.values()[i % Sector.values().length];
			tickets[i] = new Ticket("ID" + (i + 1),
					"Hall-" + (i + 1),
					"00" + (i + 1), eventTime, isPromo, sector, 5.5);
		}
		return tickets;
	}

	// returns tickets according to their stadiumSector
	private static List<Ticket> getTicketsByStadiumSector(char stadiumSector, Ticket[] initial) {
		List<Ticket> tickets = new ArrayList<>();
		char sectorToMatch;
		for (var element : initial) {
			sectorToMatch = element.getSector().name().charAt(0);
			if (sectorToMatch == stadiumSector) {
				tickets.add(element);
			}
		}
		return tickets;
	}


}