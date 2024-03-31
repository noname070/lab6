package ru.noname070.lab6.server.console.commands;

import ru.noname070.lab6.server.utils.L18n;
import ru.noname070.lab6.server.collection.CollectionManager;
import ru.noname070.lab6.server.collection.data.Organization;
import ru.noname070.lab6.server.console.Console;

/**
 * Realisation for "sort" command
 * @see AbstractCommand
 */
public class Sort extends AbstractCommand {

    public Sort() {
        super("sort", L18n.getGeneralBundle().getString("command.sort.description"), false);
    }

    @Override
    public void execute() {
        if (!CollectionManager.getData().isEmpty()) {
            CollectionManager.getData().sort(Organization::compareTo);
            Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.sort.execute"));
        } else {
            Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.err.empty"));
        }
    }

}
