package by.shyrei.logisticbase.entity;

import by.shyrei.logisticbase.service.ConfigurationManager;
import by.shyrei.logisticbase.service.IdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Project LogisticBase
 * Created on 14.06.2017.
 * author Shyrei Uladzimir
 */
public class Base {

    private final static Logger logger = LogManager.getLogger(Base.class);

    private static Base instance;
    private static AtomicBoolean createInstance = new AtomicBoolean(false);

    private static ReentrantLock lock = new ReentrantLock();

    private final ArrayDeque<Terminal> terminals = new ArrayDeque<>();
    private final Semaphore semaphore = new Semaphore(NUMBER_OF_TERMINALS, true);

    private static final int NUMBER_OF_TERMINALS = ConfigurationManager.getProperty("number.of.terminals");
    private AtomicInteger baseGoods = new AtomicInteger(ConfigurationManager.getProperty("base.goods"));
    public static final int MAX_CAPACITY = ConfigurationManager.getProperty("base.max.capacity");

    private Base() {
        do {
            terminals.add(new Terminal(IdGenerator.generateIdTerminal()));
        } while (terminals.size() != NUMBER_OF_TERMINALS);
    }

    public AtomicInteger getBaseGoods() {
        return baseGoods;
    }

    public static Base getInstance() {
        if (!createInstance.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new Base();
                    createInstance.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Terminal useTerminal() {
        Terminal terminal = null;
        try {
            lock.lock();
            semaphore.acquire();
            terminal = terminals.poll();
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            lock.unlock();
        }
        return terminal;
    }

    public void leaveTerminal(Terminal terminal) {
        terminals.push(terminal);
        semaphore.release();
    }

    public void clearBase() {
        getBaseGoods().set(new Random().nextInt(20) + 20);
    }

    @Override
    public String toString() {
        return "Base{" +
                "baseGoods=" + baseGoods +
                '}';
    }
}
