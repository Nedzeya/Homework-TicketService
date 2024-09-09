package main.interfaces;

import java.lang.reflect.Field;

public interface Printable {
    default void print() {
        Class<?> oClass = this.getClass();
        Field[] oFields = oClass.getDeclaredFields();

        System.out.println("Content of " + oClass.getSimpleName() + " is ");
        for (Field f : oFields) {
            f.setAccessible(true);
            try {
                Object fValue = f.get(this);
                System.out.println(f.getName() + " - " + fValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
