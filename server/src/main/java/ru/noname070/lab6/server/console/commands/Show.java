package ru.noname070.lab6.server.console.commands;

import ru.noname070.lab6.server.utils.L18n;
import ru.noname070.lab6.server.collection.CollectionManager;
import ru.noname070.lab6.server.collection.data.Organization;
import ru.noname070.lab6.server.console.Console;

import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * Realisation for "show" command
 * 
 * @see AbstractCommand
 */
@Slf4j
public class Show extends AbstractCommand {

    public Show() {
        super("show", L18n.getGeneralBundle().getString("command.show.description"), false, false);
    }

    @Override
    public void execute() {
        log.info("executed command '" + getName() + "'");
        if (!CollectionManager.getData().isEmpty()) {
            String output = CollectionManager.getData().stream()
                    .map(Organization::toString)
                    .collect(Collectors.joining("|||"));
            log.info("elements count: " + output.split("|||").length);
            Console.getConsolePrintStream().println(output);
        } else {
            log.error("Empty data");
            Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.err.empty"));
        }
    }

}
