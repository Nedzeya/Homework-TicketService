package main;

import main.enums.TicketClass;
import main.enums.TicketType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONTokener;

public class BusTicketReader {
    public static List<BusTicket> readTicketsFromFile(String filePath) throws IOException {
        List<BusTicket> busTickets = new ArrayList<>();
        BusTicketsValidator busTicketsValidator = new BusTicketsValidator();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                JSONObject jsonObject = new JSONObject(new JSONTokener(line));

                String ticketClassStr = jsonObject.optString("ticketClass", null);
                String ticketTypeStr = jsonObject.optString("ticketType", null);
                String startDateStr = jsonObject.optString("startDate", null);
                BigDecimal price = null;

                try {
                    price = jsonObject.isNull("price") ? null : new BigDecimal(jsonObject.getString("price"));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price format: " + e.getMessage());
                }

                TicketClass ticketClass = null;
                TicketType ticketType = null;
                long startDate = -1;

                try {
                    if (ticketClassStr != null) {
                        ticketClass = TicketClass.valueOf(ticketClassStr);
                    }
                    if (ticketTypeStr != null) {
                        ticketType = TicketType.valueOf(ticketTypeStr);
                    }
                    if (startDateStr != null) {
                        startDate = LocalDate.parse(startDateStr).toEpochDay();
                    }
                } catch (IllegalArgumentException | DateTimeParseException e) {
                    System.out.println("Invalid format for ticket data: " + e.getMessage());
                }

                BusTicket busTicket = new BusTicket(ticketClass, ticketType, startDate, price);

                if (busTicketsValidator.validateBusTicket(busTicket)) {
                    busTickets.add(busTicket);
                } else {
                    System.out.println("Ticket failed validation: " + busTicket);
                }
            }
        }
        return busTickets;
    }
}

