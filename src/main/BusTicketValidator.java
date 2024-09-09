package main;

import main.enums.TicketType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusTicketValidator {

    public void validateTickets(List<BusTicket> busTickets) {
        int validTickets = 0;

        for (BusTicket busTicket : busTickets) {
            if (validateBusTicket(busTicket)) {
                validTickets++;
            }
        }
        System.out.println("Total = " + busTickets.size());
        System.out.println("Valid = " + validTickets);
        System.out.println("Most popular violation = " + getMostPopularViolation());
    }

    private boolean validateBusTicket(BusTicket busTicket) {
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

    private final Map<String, Integer> violationsCounter = new HashMap<>();

    private void addViolationToCounter(String violation) {
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