
package ru.noname070.lab6.server.console.commands;
import ru.noname070.lab6.server.utils.L18n;
import ru.noname070.lab6.server.console.Console;

/**
 * Realisation for "exit" command. Useless in lab6 on server side
 * 
 * @see AbstractCommand
 */
public class Exit extends AbstractCommand {

    public Exit() {
        super("exit", L18n.getGeneralBundle().getString("command.exit.description"), false);
    }

    @Override
    public void execute() {
        Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.exit.message"));
        System.exit(0);
    }

}
