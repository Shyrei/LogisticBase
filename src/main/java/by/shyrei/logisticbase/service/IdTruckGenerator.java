package by.shyrei.logisticbase.service;

/**
 * Project LogisticBase
 * Created on 14.06.2017.
 * author Shyrei Uladzimir
 */
public class IdTruckGenerator {

    private static long counter = 100;

    public static long generateId() {
        return counter++;
    }
}

