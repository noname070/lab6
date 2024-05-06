package ru.noname070.lab6.server.console.commands;

import ru.noname070.lab6.server.utils.L18n;
import lombok.extern.slf4j.Slf4j;
import ru.noname070.lab6.server.collection.CollectionManager;
import ru.noname070.lab6.server.collection.data.Organization;
import ru.noname070.lab6.server.console.Console;

/**
 * Realisation for "clear" command
 * 
 * @see AbstractCommand
 */
@Slf4j
public class Clear extends AbstractCommand {

    public Clear() {
        super("clear", L18n.getGeneralBundle().getString("command.clear.description"), false, false);
    }

    @Override
    public void execute() {
        log.info("executed command '" + getName() + "'");
        CollectionManager.getData().clear();
        Organization.setNextId(0);
        Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.clear.execute"));
    }

}
