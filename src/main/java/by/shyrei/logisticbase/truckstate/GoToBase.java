package by.shyrei.logisticbase.truckstate;

import by.shyrei.logisticbase.entity.Truck;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Project LogisticBase
 * Created on 14.06.2017.
 * author Shyrei Uladzimir
 */
public class GoToBase implements TrackState {

    private final static Logger logger = LogManager.getLogger(GoToBase.class);
    private static ReentrantLock lock = new ReentrantLock();

    @Override
    public void work(Truck truck) {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            System.out.println(truck + " выехал на базу.");
            if (truck.getGoods() == 0) {
                truck.setTrackState(new LoadGoods());
            } else {
                truck.setTrackState(new UnloadGoods());
            }
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
