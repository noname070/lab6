package ru.noname070.lab6.server.console.commands;

import ru.noname070.lab6.server.utils.L18n;
import lombok.extern.slf4j.Slf4j;
import ru.noname070.lab6.server.collection.CollectionManager;
import ru.noname070.lab6.server.console.Console;

/**
 * Realisation for "save" command
 * 
 * @see AbstractCommand
 */
@Slf4j
public class Save extends AbstractCommand {

    public Save() {
        super("save", L18n.getGeneralBundle().getString("command.save.description"), false, false);
    }

    @Override
    public void execute() {
        log.info("executed command '" + getName() + "'");
        if (!CollectionManager.getData().isEmpty()) {
            CollectionManager.saveData();
            log.info("data saved in file");
            Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.save.execute"));
        } else {
            log.error("Empty data");
            Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.err.empty"));
        }
    }

}
