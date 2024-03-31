package ru.noname070.lab6.server.console.commands;

import ru.noname070.lab6.server.utils.L18n;
import ru.noname070.lab6.server.collection.CollectionManager;
import ru.noname070.lab6.server.collection.data.Organization;
import ru.noname070.lab6.server.console.Console;

/**
 * Realisation for "update" command
 * 
 * @see AbstractCommand
 */
public class Update extends AbstractCommand {

    public Update() {
        super("update", L18n.getGeneralBundle().getString("command.update.description"), true);
    }

    @Override
    public void execute(Organization org) {
        if (!CollectionManager.getData().isEmpty()) {
            CollectionManager.getData().set(org.getId(), org);
            Console.getConsolePrintStream().printf(L18n.getGeneralBundle().getString("command.update.execute"),
                    org.getId());
        } else {
            Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.err.empty"));
        }
    }

}
