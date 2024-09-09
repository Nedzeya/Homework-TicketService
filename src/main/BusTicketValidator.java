package main;

import main.enums.TicketType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BusTicketValidator {

    public boolean validateBusTicket(BusTicket busTicket) {
        TicketType ticketType = busTicket.getTicketType();
        if ((ticketType == TicketType.DAY ||
                ticketType == TicketType.WEEK ||
                ticketType == TicketType.YEAR) &&
                busTicket.getStartDate() <= 0) {
            System.out.println("Error: Start date is required for ticket type" + ticketType);
            addViolationToCounter("start date");
            return false;
        }
        if (busTicket.getPrice().compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("Error: Price can't be zero.");
            addViolationToCounter("price");
            return false;
        }
        return true;
    }

    private Map<String, Integer> violationsCounter = new HashMap<>();

    private void addViolationToCounter (String violation) {
        violationsCounter.put(violation, violationsCounter.getOrDefault(violation, 0) + 1);
    }

    private String getMostPopularViolation() {
        return violationsCounter.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No violations");
    }

}
