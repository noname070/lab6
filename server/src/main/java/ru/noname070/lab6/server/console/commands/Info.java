package ru.noname070.lab6.server.console.commands;

import ru.noname070.lab6.server.utils.L18n;
import ru.noname070.lab6.server.collection.CollectionManager;
import ru.noname070.lab6.server.console.Console;

/**
 * Realisation for "info" command
 * 
 * @see AbstractCommand
 */
public class Info extends AbstractCommand {

    public Info() {
        super("info", L18n.getGeneralBundle().getString("command.info.description"), false);
    }

    @Override
    public void execute() {
        if (!CollectionManager.getData().isEmpty()) {
            Console.getConsolePrintStream().printf(L18n.getGeneralBundle().getString("command.info.execute"),
                    CollectionManager.getData().getClass().getName(),
                    CollectionManager.getData().size());
        } else {
            Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.err.empty"));
        }
    }

}
