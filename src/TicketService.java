import java.util.Calendar;

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
                    Sector.A,
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
            System.out.println(emptyTicket.toStringTicketPrice());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}