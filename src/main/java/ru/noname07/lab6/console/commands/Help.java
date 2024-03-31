package ru.noname07.lab6.console.commands;

import ru.noname07.lab6.App;

/**
 * Realisation for "help" command
 * @see Command
 */
public class Help extends Command {

    public Help() {
        super("help", App.generalBundle.getString("command.help.description"), false);
    }

    @Override
    public void execute() {
        for (Command command : App.console.getCommandList().values()) {
            System.out.printf("%-40s\t:\t%s %n", command.getName(), command.getDescription());
        }
    }

}
