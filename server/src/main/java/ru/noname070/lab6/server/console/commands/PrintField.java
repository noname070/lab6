package ru.noname070.lab6.server.console.commands;

import ru.noname070.lab6.server.utils.L18n;
import ru.noname070.lab6.server.collection.CollectionManager;
import ru.noname070.lab6.server.console.Console;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * Realisation for "print_field_ascending_official_address" command
 * 
 * @see AbstractCommand
 */
@Slf4j
public class PrintField extends AbstractCommand {

    public PrintField() {
        super("print_field_ascending_official_address",
                L18n.getGeneralBundle().getString("command.print_field_ascending_official_address.description"),
                false, false);
    }

    @Override
    public void execute() {
        log.info("executed command '" + getName() + "'");
        if (!CollectionManager.getData().isEmpty()) {

            String output = CollectionManager.getData().stream()
                    .map(org -> org.getOfficialAddress().getStreet())
                    .sorted(String::compareTo)
                    .collect(Collectors.joining("|||"));

            Console.getConsolePrintStream()
                    .println(L18n.getGeneralBundle().getString("command.print_field_ascending_official_address.execute")
                            + output);

        } else {
            log.error("Empty data");
            Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.err.empty"));
        }
    }

}
