package ru.noname070.lab6.server.console.commands;

import ru.noname070.lab6.server.utils.L18n;
import ru.noname070.lab6.server.collection.CollectionManager;
import ru.noname070.lab6.server.console.Console;

/**
 * Realisation for "save" command
 * @see AbstractCommand
 */
public class Save extends AbstractCommand {

    public Save() {
        super("save", L18n.getGeneralBundle().getString("command.save.description"), false);
    }

    @Override
    public void execute() {
        if (!CollectionManager.getData().isEmpty()) {
            CollectionManager.saveData();
            Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.save.execute"));
        } else {Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.err.empty"));}
    }

}
