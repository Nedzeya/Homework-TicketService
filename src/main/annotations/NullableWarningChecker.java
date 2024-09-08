package main.annotations;

import java.lang.reflect.Field;

public class NullableWarningChecker {
    public static void check(Object o) {
        Class<?> oClass = o.getClass();
        Field[] oFields = oClass.getDeclaredFields();

        for (Field f : oFields) {
            if (f.isAnnotationPresent(NullableWarning.class)){
                f.setAccessible(true);
                try {
                    Object fValue = f.get(o);
                    if(fValue == null){
                        System.out.println("Variable " + f.getName()
                                + " is null in " + oClass.getSimpleName() + "!");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
