package main;

import main.enums.Sector;
import main.products.ticket.Ticket;
import main.products.busTicket.BusTicket;
import main.products.busTicket.BusTicketReader;
import main.products.busTicket.BusTicketsValidator;
import main.products.ticket.TicketManager;
import main.users.Admin;
import main.users.Client;
import main.users.AbstractUser;

import java.util.*;

public class Main {

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
            emptyTicket.printTicketPrice();

            TicketManager ticketManager = new TicketManager();
//Generating tickets
            ticketManager.generateTenTickets();
            //testing getTicketById
            System.out.println(ticketManager.getTicketById("ID1"));
            //testing getTicketsByStadiumSector
            List<Ticket> ticketsByStadiumSector = ticketManager.getTicketsByStadiumSector(Sector.A);
            System.out.println(ticketsByStadiumSector.size());
            //testing shared() by phone and by phone and email
            emptyTicket.shared("123-456-789");
            fullTicket.shared("123-456-789", "ticket@email.com");
            //testing users polymorphism
            AbstractUser client = new Client("Nice Client");
            AbstractUser admin = new Admin("Good Admin");
            client.printRole();
            Ticket clientTicket = ((Client) client).getTicket();
            admin.printRole();
            boolean checkingResult = ((Admin) admin).checkTicket(clientTicket);
            System.out.println("Checking result is: " + checkingResult);

            //testing @NullableWarning
            Ticket nullSectorFullTicket = new Ticket("ID02",
                    "Concert",
                    "001", eventTime,
                    true,
                    null,
                    5.5);

            //BusTicketValidatorTesting and reading from file
            System.out.println("Read and validate busTickets: ");
            BusTicketsValidator busTicketsValidator = new BusTicketsValidator();
            String filePath = "src/main/files/busTickets.json";
            List<BusTicket> busTickets = BusTicketReader.readTicketsFromFile(filePath);
            busTicketsValidator.validateTickets(busTickets);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}