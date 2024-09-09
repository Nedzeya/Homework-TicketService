package main;

import main.enums.TicketType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusTicketsValidator {

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
        return validateHavingStartDate(busTicket)
                && validatePrice(busTicket)
                && validateStartDate(busTicket)
                && validateEvenPrice(busTicket);
    }

    /**
     * only DAY,WEEK,YEAR types must have a startDate
     */
    private boolean validateHavingStartDate(BusTicket busTicket) {
        TicketType ticketType = busTicket.getTicketType();
        if ((ticketType == TicketType.DAY ||
                ticketType == TicketType.WEEK ||
                ticketType == TicketType.YEAR) &&
                busTicket.getStartDate() <= 0) {
            System.out.println("Error: Start date is required for ticket type " + ticketType);
            addViolationToCounter("start date");
            return false;
        }
        return true;
    }

    /**
     * price can't be zero
     */
    private boolean validatePrice(BusTicket busTicket) {
        BigDecimal busTicketPrice = busTicket.getPrice();
        if (busTicketPrice == null || busTicketPrice.compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("Error: Price can't be null or zero.");
            addViolationToCounter("price");
            return false;
        }
        return true;
    }

    /**
     * start date cannot be in the future
     */
    private boolean validateStartDate(BusTicket busTicket) {
        long currentTimestamp = System.currentTimeMillis() / 1000;
        if (busTicket.getStartDate() > currentTimestamp) {
            System.out.println("Error: Start date cannot be in the future.");
            return false;
        }
        return true;
    }

    /**
     * price should always be even
     */
    private boolean validateEvenPrice(BusTicket busTicket) {
        BigDecimal busTicketPrice = busTicket.getPrice();

        if (busTicketPrice.stripTrailingZeros().scale() > 0) {
            System.out.println("Error: The price must be a whole number.");
            return false;
        }

        if (busTicketPrice.remainder(BigDecimal.valueOf(2)).compareTo(BigDecimal.ZERO) != 0) {
            System.out.println("Error: The price must be an even number.");
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