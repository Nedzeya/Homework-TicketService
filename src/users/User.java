package users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class User {

    private String name;

    public abstract void printRole();
    public abstract void getTicket();
    public abstract void checkTicket();
}
