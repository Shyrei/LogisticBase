package by.shyrei.logisticbase.runner;

import by.shyrei.logisticbase.creator.TruckQueueCreator;
import by.shyrei.logisticbase.entity.Truck;
import java.util.PriorityQueue;


/**
 * Project LogisticBase
 * Created on 14.06.2017.
 * author Shyrei Uladzimir
 */
public class Runner {

    public static void main(String[] args) {

        TruckQueueCreator creator = new TruckQueueCreator();
        PriorityQueue<Truck> trucks = creator.truckQueueCreator();
        while (trucks.peek() != null) {
            trucks.poll().start();
        }
    }
}
