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

    public void putFine(Fine fine) {
        if (fines.contains(fine)) {
            System.out.println("A fine with that number already exists.");
            return;
        }

        if (size >= capacity) {
            resize();
        }

        fines.add(fine);
        size++;
        System.out.println("Fine added: " + fine);
    }

    public boolean containsFine(int id) {
        for (Fine fine : fines) {
            if (fine.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void deleteFine(int id) {
        fines.removeIf(fine -> fine.getId() == id);
        System.out.println("Fine with id:  " + id + " deleted.");
    }

    private void resize() {
        capacity += 3;
        Set<Fine> newSetOfFines = new HashSet<>(capacity);
        newSetOfFines.addAll(fines);
        fines = newSetOfFines;
        System.out.println("Capacity increased to " + capacity);
    }
}
