package main.users;

public class Admin extends AbstractUser {
    public Admin(String name) {
        super(name);
    }

    @Override
    public void printRole() {
        System.out.println("My role is ADMIN");
    }

    @Override
    public void getTicket() {

    }

    @Override
    public void checkTicket() {
        System.out.println("Admin " + getName() + " is checking a ticket.");
    }
}