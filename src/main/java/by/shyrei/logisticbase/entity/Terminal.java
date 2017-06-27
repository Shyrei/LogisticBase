package by.shyrei.logisticbase.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;


/**
 * Project LogisticBase
 * Created on 14.06.2017.
 * author Shyrei Uladzimir
 */
public class Terminal {

    private final static Logger logger = LogManager.getLogger(Terminal.class);
    private long terminalId;


    public Terminal(long terminalId) {
        this.terminalId = terminalId;
    }

    public long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(long terminalId) {
        this.terminalId = terminalId;
    }

    public void terminalWork(Truck truck) {
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(this + " используется " + truck + ", кол-во товара на базе :" + Base.getInstance().getBaseGoods());
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Терминал № " + terminalId;
    }
}
