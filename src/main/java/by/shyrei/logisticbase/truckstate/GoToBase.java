package by.shyrei.logisticbase.truckstate;

import by.shyrei.logisticbase.entity.Truck;
import java.util.concurrent.TimeUnit;


/**
 * Project LogisticBase
 * Created on 14.06.2017.
 * author Shyrei Uladzimir
 */
public class GoToBase implements TrackState {
    @Override
    public void work(Truck truck) {
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(truck + " выехал на базу.");
            if (truck.getGoods() == 0) {
                truck.setTrackState(new LoadGoods());
            } else {
                truck.setTrackState(new UnloadGoods());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
