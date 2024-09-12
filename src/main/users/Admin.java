package main.users;

import main.products.ticket.Ticket;
import main.interfaces.TicketChecker;

public class Admin extends AbstractUser implements TicketChecker {
    public Admin(String name) {
        super(name);
    }

    @Override
    public void printRole() {
        System.out.println("My role is ADMIN");
    }

    @Override
    public boolean checkTicket(Ticket ticket) {
        System.out.println("Admin " + getName() + " is checking a ticket " + ticket);
        return ticket != null;
    }
}