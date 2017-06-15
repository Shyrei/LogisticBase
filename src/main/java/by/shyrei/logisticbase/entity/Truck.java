package by.shyrei.logisticbase.entity;

import by.shyrei.logisticbase.exception.LogisticBaseResourсeException;
import by.shyrei.logisticbase.truckstate.TrackState;

/**
 * Project LogisticBase
 * Created on 14.06.2017.
 * author Shyrei Uladzimir
 */
public class Truck extends Thread {
    private long trackId;
    private TrackState trackState;
    private int goods;
    private Base base = Base.getInstance();

    public Truck(long trackId, TrackState trackState, int goods) {
        this.trackId = trackId;
        this.trackState = trackState;
        this.goods = goods;
    }

    public int getGoods() {
        return goods;
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
        changeState();
        try {
            terminal = base.useTerminal();
            if (terminal != null) {
                terminal.terminalWork(this);
                changeState();
            }
        } catch (LogisticBaseResourсeException e) {
            System.err.println(e.getMessage());
            // use log
        } finally {
            if (terminal != null) {
                base.leaveTerminal(terminal);
                changeState();
            }
        }
    }

    @Override
    public String toString() {
        return "Грузовик № " + trackId;
    }
}





