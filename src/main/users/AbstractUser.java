package main.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class AbstractUser {

    private String name;

    public abstract void printRole();
    public abstract void checkTicket();
}
