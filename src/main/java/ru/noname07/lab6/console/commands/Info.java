package ru.noname07.lab6.console.commands;

import ru.noname07.lab6.App;

/**
 * Realisation for "info" command
 * @see Command
 */
public class Info extends Command {

    public Info() {
        super("info", App.generalBundle.getString("command.info.description"), false);
    }

    @Override
    public void execute() { // TODO ?
        if (!App.collection.getData().isEmpty()) {
            System.out.printf(App.generalBundle.getString("command.info.execute"),
                    App.collection.getData().getClass().getName(),
                    App.collection.getData().size());
        } else {
            System.err.println(App.generalBundle.getString("command.err.empty"));
        }
    }

}
