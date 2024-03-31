package ru.noname070.lab6.server.console.commands;

import ru.noname070.lab6.server.utils.L18n;
import ru.noname070.lab6.server.collection.CollectionManager;
import ru.noname070.lab6.server.collection.data.Organization;
import ru.noname070.lab6.server.console.Console;

import java.util.stream.Collectors;

/**
 * Realisation for "show" command
 * 
 * @see AbstractCommand
 */
public class Show extends AbstractCommand {

    public Show() {
        super("show", L18n.getGeneralBundle().getString("command.show.description"), false);
    }

    @Override
    public void execute() {
        if (!CollectionManager.getData().isEmpty()) {
            String output = CollectionManager.getData().stream()
                    .map(Organization::toString)
                    .collect(Collectors.joining("|||"));
            Console.getConsolePrintStream().println(output);
        } else {
            Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.err.empty"));
        }
    }

}
