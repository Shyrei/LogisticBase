package by.shyrei.logisticbase.creator;

import by.shyrei.logisticbase.entity.Truck;
import by.shyrei.logisticbase.service.ConfigurationManager;
import by.shyrei.logisticbase.service.IdGenerator;
import by.shyrei.logisticbase.truckstate.GoToBase;

import java.util.ArrayDeque;
import java.util.Random;

/**
 * Project LogisticBase
 * Created on 15.06.2017.
 * author Shyrei Uladzimir
 */
public class TruckQueueCreator {

    private static final int NUMBER_OF_TRUCKS = ConfigurationManager.getProperty("number.of.truck");
    private static final int TRUCK_MAX_CAPACITY = ConfigurationManager.getProperty("truck.max.capacity");

    public ArrayDeque<Truck> truckQueueCreator() {
        ArrayDeque<Truck> truckQueue = new ArrayDeque<>();
        for (int i = 0; i < NUMBER_OF_TRUCKS; i++) {
            truckQueue.add(new Truck(IdGenerator.generateIdTruck(), new GoToBase(), new Random().nextInt(4), TRUCK_MAX_CAPACITY));
        }
        return truckQueue;
    }
}
