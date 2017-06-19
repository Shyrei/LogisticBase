package by.shyrei.logisticbase.truckstate;

import by.shyrei.logisticbase.entity.Base;
import by.shyrei.logisticbase.entity.Terminal;
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
public class LoadGoods implements TrackState {

    private final static Logger logger = LogManager.getLogger(LoadGoods.class);
    private Base base = Base.getInstance();
    private static ReentrantLock lock = new ReentrantLock();

    @Override
    public void work(Truck truck) {

        try {
            lock.lock();
            if (base.getBaseGoods().get() > truck.getMaxCapacity()) {
                base.getBaseGoods().set(base.getBaseGoods().intValue() - truck.getMaxCapacity());
                truck.setGoods(truck.getMaxCapacity());
                TimeUnit.SECONDS.sleep(1);
                System.out.println(truck + " загрузился" + ", кол-во товара на базе :" + Base.getInstance().getBaseGoods());
                truck.setTrackState(new LeaveBase());
            }
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
