package users;

public class Client extends AbstractUser {

    public Client(String name) {
        super(name);
    }
    @Override
    public void printRole() {
        System.out.println("My role is CLIENT");
    }
    @Override
    public void getTicket() {
        System.out.println("Client " + getName() + " is getting a ticket.");
    }

    @Override
    public void checkTicket() {

    }
}
