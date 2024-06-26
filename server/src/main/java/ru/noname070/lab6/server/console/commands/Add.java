package ru.noname070.lab6.server.console.commands;

import ru.noname070.lab6.server.utils.L18n;
import lombok.extern.slf4j.Slf4j;
import ru.noname070.lab6.server.collection.CollectionManager;
import ru.noname070.lab6.server.collection.data.Organization;
import ru.noname070.lab6.server.console.Console;
import ru.noname070.lab6.server.console.CreateNewElement;

/**
 * Realisation for "add" command
 * 
 * @see AbstractCommand
 */
@SuppressWarnings("deprecation")
@Slf4j
public class Add extends AbstractCommand {

    public Add() {
        super("add", L18n.getGeneralBundle().getString("command.add.description"), false, true);
    }

    // DEV ONLY
    @Override
    public void execute(String arg) {
        log.info("executed command '" + getName() + "', args:" + arg);
        if (arg.equals("devrnd")) {
            CollectionManager.getData().add(CreateNewElement.genRandom());
            Console.getConsolePrintStream().println("[DEV] gen random element to collection.");
        } else {
            Console.getConsolePrintStream().printf("You can`t use add with args\n");
        }
    }

    @Override
    public void execute(Organization org) {
        log.info("executed command '" + getName() + "', org:" + org.toString());
        int nextId = Organization.nextId();
        org.setId(nextId+1);
        CollectionManager.getData().add(org);
        Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.add.description"));

    }

}
