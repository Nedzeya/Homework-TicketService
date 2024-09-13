package main.users;

import lombok.Setter;
import main.interfaces.TicketHolder;
import main.products.ticket.Ticket;

@Setter
public class Client extends AbstractUser implements TicketHolder {
    private Ticket ticket;

    public Client(String name) {
        super(name);
    }
    @Override
    public void printRole() {
        System.out.println("My role is CLIENT");
    }
    @Override
    public Ticket getTicket() {
        System.out.println("Client " + getName() + " is getting a ticket "+ this.ticket);
        return ticket;
    }
}
