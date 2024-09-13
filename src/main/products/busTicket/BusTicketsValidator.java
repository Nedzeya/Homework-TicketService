package main.products.busTicket;

import main.enums.TicketType;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    public boolean validateBusTicket(BusTicket busTicket) {
        return validateHavingStartDate(busTicket)
                && validatePrice(busTicket)
                && validateStartDate(busTicket)
                && validateEvenPrice(busTicket)
                && validateTicketType(busTicket);
    }

    /**
     * only DAY,WEEK,YEAR types must have a startDate
     */
    private boolean validateHavingStartDate(BusTicket busTicket) {
        TicketType ticketType = busTicket.getTicketType();
        LocalDate startDate = busTicket.getStartDate();
        if ((TicketType.DAY.equals(ticketType) ||
                TicketType.WEEK.equals(ticketType) ||
                TicketType.YEAR.equals(ticketType)) &&
                startDate == null) {
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
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = busTicket.getStartDate();
        if (startDate != null && startDate.isAfter(currentDate)) {
            System.out.println("Error: Start date cannot be in the future.");
            addViolationToCounter("start date");
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
            addViolationToCounter("price");
            return false;
        }

        if (busTicketPrice.remainder(BigDecimal.valueOf(2)).compareTo(BigDecimal.ZERO) != 0) {
            System.out.println("Error: The price must be an even number.");
            addViolationToCounter("price");
            return false;
        }
        return true;
    }

    /**
     * valid values are DAY,WEEK,MONTH,YEAR
     */
    private boolean validateTicketType(BusTicket busTicket) {
        TicketType ticketType = busTicket.getTicketType();
        boolean isValid = false;

        for (TicketType type : TicketType.values()) {
            if (type == ticketType) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            System.out.println("Error: Invalid ticket type.");
            addViolationToCounter("type");
        }
        return isValid;
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