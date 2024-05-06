package ru.noname070.lab6.server.console.commands;

import ru.noname070.lab6.server.utils.L18n;
import ru.noname070.lab6.server.console.Console;

import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * Realisation for "help" command
 * 
 * @see AbstractCommand
 */
@Slf4j
public class Help extends AbstractCommand {

    public Help() {
        super("help", L18n.getGeneralBundle().getString("command.help.description"), false, false);
    }

    @Override
    public void execute() {
        log.info("executed command '" + getName() + "'");
        String out = Console.getCommandList().values().stream()
                        .map(command -> String.format("%-40s\t:\t%s", command.getName(), command.getDescription()))
                        .collect(Collectors.joining("|||"));

        Console.getConsolePrintStream().println(out);
    }

}
