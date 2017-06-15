package by.shyrei.logisticbase.entity;

import by.shyrei.logisticbase.exception.LogisticBaseResourсeException;
import by.shyrei.logisticbase.service.ConfigurationManager;
import by.shyrei.logisticbase.service.IdTerminalGenerator;
import java.util.ArrayDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Project LogisticBase
 * Created on 14.06.2017.
 * author Shyrei Uladzimir
 */
public class Base {
    private static Base instance;
    private static ReentrantLock lock = new ReentrantLock();
    private final ArrayDeque<Terminal> terminals = new ArrayDeque<>();
    private final Semaphore semaphore = new Semaphore(NUMBER_OF_TERMINALS, true);
    private static final int NUMBER_OF_TERMINALS = ConfigurationManager.getProperty("number.of.terminals");

    private Base() {
     /*   do {

        } while (terminals.size() == NUMBER_OF_TERMINALS);*/

        for (int i = 0; i < NUMBER_OF_TERMINALS; i++) {
                try {
                terminals.add(new Terminal(IdTerminalGenerator.generateId()));
            } finally {
                if (terminals.size() == 10) {

                } else {
                    // добавить необходимое количество terminals.add()
                }
            }
        }
    }

    public static Base getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new Base();
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }

    public Terminal useTerminal() throws LogisticBaseResourсeException {
        Terminal terminal = null;
        try {
            lock.lock();
            semaphore.acquire();
            terminal = terminals.poll();
        } catch (InterruptedException e) {
            // запись в лог + e.getMessage();
            e.getMessage();
        } finally {
            lock.unlock();
        }
        return terminal;
    }

    public void leaveTerminal(Terminal terminal) {
        terminals.push(terminal);
        System.out.println("Терминал № " + terminal.getTerminalId() + " освободился."); // спросить надо ли менять на Logger
        semaphore.release();
    }
}
