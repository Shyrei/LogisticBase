package by.shyrei.logisticbase.service;

/**
 * Project LogisticBase
 * Created on 14.06.2017.
 * author Shyrei Uladzimir
 */
public class IdGenerator {

    private static long truckCounter = 100;
    private static long terminalCounter = 1;

    public static long generateIdTruck() {
        return truckCounter++;
    }

    public static long generateIdTerminal() {
        return terminalCounter++;
    }
}
