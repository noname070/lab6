package ru.noname070.lab6.server.console.commands;

import ru.noname070.lab6.server.utils.L18n;
import ru.noname070.lab6.server.collection.CollectionManager;
import ru.noname070.lab6.server.collection.data.Organization;
import ru.noname070.lab6.server.console.Console;

/**
 * Realisation for "clear" command
 * 
 * @see AbstractCommand
 */
public class Clear extends AbstractCommand {

    public Clear() {
        super("clear", L18n.getGeneralBundle().getString("command.clear.description"), false);
    }

    @Override
    public void execute() {
        CollectionManager.getData().clear();
        Organization.setNextId(0);
        Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.clear.execute"));
    }

}
