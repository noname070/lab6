package ru.noname070.lab6.server.console.commands;

import ru.noname070.lab6.server.utils.L18n;
import ru.noname070.lab6.server.collection.CollectionManager;
import ru.noname070.lab6.server.console.Console;
import java.util.stream.Collectors;

/**
 * Realisation for "print_field_ascending_official_address" command
 * 
 * @see AbstractCommand
 */
public class PrintField extends AbstractCommand {

    public PrintField() {
        super("print_field_ascending_official_address",
                L18n.getGeneralBundle().getString("command.print_field_ascending_official_address.description"),
                false);
    }

    @Override
    public void execute() {
        if (!CollectionManager.getData().isEmpty()) {

            String output = CollectionManager.getData().stream()
                    .map(org -> org.getOfficialAddress().getStreet())
                    .sorted(String::compareTo)
                    .collect(Collectors.joining("|||"));

            Console.getConsolePrintStream()
                    .println(L18n.getGeneralBundle().getString("command.print_field_ascending_official_address.execute")
                            + output);

        } else {
            Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.err.empty"));
        }
    }

}
