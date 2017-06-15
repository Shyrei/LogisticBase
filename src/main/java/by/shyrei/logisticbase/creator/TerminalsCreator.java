package by.shyrei.logisticbase.creator;

import by.shyrei.logisticbase.entity.Terminal;
import by.shyrei.logisticbase.service.ConfigurationManager;
import by.shyrei.logisticbase.service.IdTerminalGenerator;

import java.util.ArrayDeque;

/**
 * Project LogisticBase
 * Created on 15.06.2017.
 * author Shyrei Uladzimir
 */
public class TerminalsCreator {

    private static final int NUMBER_OF_TERMINALS = ConfigurationManager.getProperty("number.of.terminals");

    public ArrayDeque<Terminal> terminalsCreator(){
        ArrayDeque<Terminal> terminals = new ArrayDeque<>();
        for (int i = 0 ; i < NUMBER_OF_TERMINALS; i ++) {
            terminals.add(new Terminal(IdTerminalGenerator.generateId()));
        }
        return terminals;
    }
}


