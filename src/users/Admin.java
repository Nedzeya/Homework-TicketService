package users;

public class Admin extends User {
    public Admin(String name) {
        super(name);
    }

    @Override
    public void printRole() {
        System.out.println("My role is ADMIN");
    }
    public void checkTicket() {
        System.out.println("Admin " + getName() + " is checking a ticket.");
    }
}
