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

                BusTicket busTicket = parseTicket(line);

                if (busTicketsValidator.validateBusTicket(busTicket)) {
                    busTickets.add(busTicket);
                } else {
                    System.out.println("Ticket failed validation: " + busTicket);
                }
            }
        }
        return busTickets;
    }

    private static BusTicket parseTicket(String line) {
        JSONObject jsonObject = new JSONObject(new JSONTokener(line));

        String ticketClassStr = jsonObject.optString("ticketClass", null);
        String ticketTypeStr = jsonObject.optString("ticketType", null);
        String startDateStr = jsonObject.optString("startDate", null);
        String priceStr = jsonObject.optString("price", null);

        TicketClass ticketClass = parseTicketClass(ticketClassStr);
        TicketType ticketType = parseTicketType(ticketTypeStr);
        long startDate = parseStartDate(startDateStr);
        BigDecimal price = parsePrice(priceStr);

        return new BusTicket(ticketClass, ticketType, startDate, price);
    }

    private static BigDecimal parsePrice(String priceStr) {
        try {
            return priceStr != null ? new BigDecimal(priceStr) : null;
        } catch (NumberFormatException e) {
            System.out.println("Invalid price format: " + e.getMessage());
            return null;
        }
    }

    private static long parseStartDate(String startDateStr) {
        try {
            return startDateStr != null ? LocalDate.parse(startDateStr).toEpochDay() : -1;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + e.getMessage());
            return -1;
        }
    }

    private static TicketType parseTicketType(String ticketTypeStr) {
        try {
            return ticketTypeStr != null ? TicketType.valueOf(ticketTypeStr) : null;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid TicketType format: " + e.getMessage());
            return null;
        }
    }

    private static TicketClass parseTicketClass(String ticketClassStr) {
        try {
            return ticketClassStr != null ? TicketClass.valueOf(ticketClassStr) : null;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid TicketClass format: " + e.getMessage());
            return null;
        }
    }
}

