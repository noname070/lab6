package ru.noname07.lab6.console.commands;

import ru.noname07.lab6.App;

/**
 * Realisation for "exit" command
 * @see Command
 */
public class Exit extends Command {

    public Exit() {
        super("exit", App.generalBundle.getString("command.exit.description"), false);
    }

    @Override
    public void execute() {
        System.out.println(App.generalBundle.getString("command.exit.message"));
        System.exit(0);
    }

}
