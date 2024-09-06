package users;

public class Client extends User {

    public Client(String name) {
        super(name);
    }
    @Override
    public void printRole() {
        System.out.println("My role is CLIENT");
    }
    public void getTicket() {
        System.out.println("Client " + getName() + " is getting a ticket.");
    }
}
