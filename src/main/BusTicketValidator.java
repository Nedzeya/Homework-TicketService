package main;

import main.enums.TicketType;

import java.math.BigDecimal;

public class BusTicketValidator {

    public boolean validateBusTicket(BusTicket busTicket) {
        TicketType ticketType = busTicket.getTicketType();
        if ((ticketType == TicketType.DAY ||
                ticketType == TicketType.WEEK ||
                ticketType == TicketType.YEAR) &&
                busTicket.getStartDate() <= 0) {
            System.out.println("Error: Start date is required for ticket type" + ticketType);
            return false;
        }
        if (busTicket.getPrice().compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("Error: Price can't be zero.");
            return false;
        }
        return true;
    }
}
