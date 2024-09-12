package main.products.fine;

import java.util.HashSet;
import java.util.Set;

public class FineHashSet {
    private Set<Fine> fines;
    private int capacity;
    private int size;

    public FineHashSet() {
        capacity = 3;
        fines = new HashSet<>(capacity);
        size = 0;
    }

   
}
