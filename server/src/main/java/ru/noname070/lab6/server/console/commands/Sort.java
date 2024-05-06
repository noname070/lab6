package ru.noname070.lab6.server.console.commands;

import ru.noname070.lab6.server.utils.L18n;
import lombok.extern.slf4j.Slf4j;
import ru.noname070.lab6.server.collection.CollectionManager;
import ru.noname070.lab6.server.collection.data.Organization;
import ru.noname070.lab6.server.console.Console;

/**
 * Realisation for "sort" command
 * @see AbstractCommand
 */
@Slf4j
public class Sort extends AbstractCommand {

    public Sort() {
        super("sort", L18n.getGeneralBundle().getString("command.sort.description"), false, false);
    }

    @Override
    public void execute() {
        log.info("executed command '" + getName() + "'");
        if (!CollectionManager.getData().isEmpty()) {
            CollectionManager.getData().sort(Organization::compareTo);
            log.info("Collection was sorted");
            Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.sort.execute"));
        } else {
            log.error("Empty data");
            Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.err.empty"));
        }
    }

}
