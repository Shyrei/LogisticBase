package by.shyrei.logisticbase.creator;

import by.shyrei.logisticbase.entity.Truck;
import by.shyrei.logisticbase.service.ConfigurationManager;
import by.shyrei.logisticbase.service.IdGenerator;
import by.shyrei.logisticbase.truckstate.GoToBase;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Project LogisticBase
 * Created on 15.06.2017.
 * author Shyrei Uladzimir
 */
public class TruckQueueCreator {

    private static final int NUMBER_OF_TRUCKS = ConfigurationManager.getProperty("number.of.truck");
    private static final int TRUCK_MAX_CAPACITY = ConfigurationManager.getProperty("truck.max.capacity");

    public PriorityQueue<Truck> truckQueueCreator() {
        PriorityQueue<Truck> truckQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.getPriorityGoods()));
        for (int i = 0; i < NUMBER_OF_TRUCKS; i++) {
            truckQueue.add(new Truck(IdGenerator.generateIdTruck(), new GoToBase(), new Random().nextInt(4), TRUCK_MAX_CAPACITY, new Random().nextInt(2)));
        }
        return truckQueue;
    }
}
