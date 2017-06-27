package by.shyrei.logisticbase.entity;

import by.shyrei.logisticbase.truckstate.TrackState;


/**
 * Project LogisticBase
 * Created on 14.06.2017.
 * author Shyrei Uladzimir
 */
public class Truck extends Thread {

    private int goods;
    private int priorityGoods;
    private int maxCapacity;
    private long trackId;
    private TrackState trackState;
    private static Base base = Base.getInstance();

    public Truck(long trackId, TrackState trackState, int goods, int maxCapacity, int priorityGoods) {
        this.goods = goods;
        this.maxCapacity = maxCapacity;
        this.trackId = trackId;
        this.trackState = trackState;
        this.priorityGoods = priorityGoods;
    }

    public int getGoods() {
        return goods;
    }

    public void setGoods(int goods) {
        this.goods = goods;
    }

    public int getPriorityGoods() {
        return priorityGoods;
    }

    public void setPriorityGoods(int priorityGoods) {
        this.priorityGoods = priorityGoods;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setTrackState(TrackState trackState) {
        this.trackState = trackState;
    }

    private void changeState() {
        trackState.work(this);
    }

    @Override
    public void run() {
        Terminal terminal = null;
        try {
            terminal = base.useTerminal();
            changeState();
            if (terminal != null) {
                terminal.terminalWork(this);
                changeState();
            }
        } finally {
            if (terminal != null) {
                base.leaveTerminal(terminal);
                changeState();
            }
        }
    }

    @Override
    public String toString() {
        return "Грузовик № " + trackId + ", кол-во товара: " + goods + ", вместимость: " + maxCapacity + ", срочность: " + priorityGoods;


    }
}





