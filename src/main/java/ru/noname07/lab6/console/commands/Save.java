package ru.noname07.lab6.console.commands;

import ru.noname07.lab6.App;

/**
 * Realisation for "save" command
 * @see Command
 */
public class Save extends Command {

    public Save() {
        super("save", App.generalBundle.getString("command.save.description"), false);
    }

    @Override
    public void execute() {
        if (!App.collection.getData().isEmpty()) {
            App.collection.saveData();
            System.out.println(App.generalBundle.getString("command.save.execute"));
        } else {System.err.println(App.generalBundle.getString("command.err.empty"));}
    }

}
