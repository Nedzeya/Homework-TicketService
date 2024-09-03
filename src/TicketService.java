import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TicketService {
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
					'A',
					5.5);
			System.out.println(fullTicket);

			calendar.set(2024, Calendar.AUGUST, 30, 16, 2, 0);
			long eventTime1 = calendar.getTimeInMillis();

			Ticket limitedTicket = new Ticket("Hall",
					"002",
					eventTime1);
			System.out.println(limitedTicket);

			//saving ticket price
			emptyTicket.toSaveTicketPrice(49.99);

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	// generate tickets
	private static Ticket[] getTickets() {
		var stadiumSector = "ABC".toCharArray();
		long eventTime;
		Random random = new Random();
		boolean isPromo;
		Ticket[] tickets = new Ticket[9];

		for (int i = 0; i < tickets.length; i++) {

			isPromo = random.nextBoolean();
			eventTime = System.currentTimeMillis() / 1000;
			char sector = stadiumSector[i % stadiumSector.length];
			tickets[i] = new Ticket("ID" + (i + 1),
					"Hall-" + (i + 1),
					"00" + (i + 1), eventTime, isPromo, sector, 5.5);
		}
		return tickets;
	}

	// returns tickets according to their stadiumSector
	private static List<Ticket> getTicketByStadiumSector(char stadiumSector, Ticket[] initial) {
		List<Ticket> tickets = new ArrayList<>();

		for (var element : initial) {
			if (element.getStadiumSector() == stadiumSector) {
				tickets.add(element);
			}
		}
		return tickets;
	}


}