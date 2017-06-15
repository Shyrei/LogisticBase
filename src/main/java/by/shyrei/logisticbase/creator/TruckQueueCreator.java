package by.shyrei.logisticbase.creator;

import by.shyrei.logisticbase.entity.Truck;
import by.shyrei.logisticbase.service.ConfigurationManager;
import by.shyrei.logisticbase.service.IdTruckGenerator;
import by.shyrei.logisticbase.truckstate.GoToBase;
import java.util.ArrayDeque;
import java.util.Random;

/**
 * Project LogisticBase
 * Created on 15.06.2017.
 * author Shyrei Uladzimir
 */
public class TruckQueueCreator {

    /* .... переделать на PrioretyQueue с компаратором... реализовать компаратор так чтобы он добавлял в начало очереди машины с срочным грузом...
       .... в класс Грузовик добавить поле "срочный груз" ....
       ....!!!!!!!!!!  уточнить будет ли семафор работать с такой очередью, инфу в нете не нашел...
     */

    private static final int NUMBER_OF_TRUCKS = ConfigurationManager.getProperty("number.of.truck");

    public ArrayDeque<Truck> truckQueueCreator() {
        ArrayDeque<Truck> truckQueue = new ArrayDeque<>();
        for (int i = 0; i < NUMBER_OF_TRUCKS; i++) {
            truckQueue.add(new Truck(IdTruckGenerator.generateId(), new GoToBase(), new Random().nextInt(2)));
        }
        return truckQueue;
    }
}
