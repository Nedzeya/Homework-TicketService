package main;

import main.enums.TicketClass;
import main.enums.TicketType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BusTicketReader {
    public static List<BusTicket> readTicketsFromFile(String filePath) throws IOException {
        List<BusTicket> busTickets = new ArrayList<>();
        BusTicketsValidator busTicketsValidator = new BusTicketsValidator();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String ticketClassStr = extractValue(line, "ticketClass");
                String ticketTypeStr = extractValue(line, "ticketType");
                String startDateStr = extractValue(line, "startDate");
                BigDecimal price = new BigDecimal(extractValue(line, "price"));

                TicketClass ticketClass = TicketClass.valueOf(ticketClassStr);
                TicketType ticketType = TicketType.valueOf(ticketTypeStr);
                long startDate = LocalDate.parse(startDateStr).toEpochDay();

                BusTicket busTicket = new BusTicket(ticketClass, ticketType, startDate, price);
                if (busTicketsValidator.validateBusTicket(busTicket)) busTickets.add(busTicket);
                else System.out.println("Ticket failed validation: " + busTicket);
            }
        }
        return busTickets;
    }

    private static String extractValue(String line, String key) {
        String prefix = "\"" + key + "\":\"";
        int startIndex = line.indexOf(prefix) + prefix.length();
        int endIndex = line.indexOf("\"", startIndex);
        return line.substring(startIndex, endIndex);
    }
}

