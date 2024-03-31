package ru.noname07.lab6.console.commands;

import ru.noname07.lab6.App;
import ru.noname07.lab6.collection.data.Organization;

/**
 * Realisation for "show" command
 * @see Command
 */
public class Show extends Command {

    public Show() {
        super("show", App.generalBundle.getString("command.show.description"), false);
    }

    @Override
    public void execute() {
        if (!App.collection.getData().isEmpty()) {
            for (Organization org : App.collection.getData()) {
                System.out.println(org);
            }
        } else {System.err.println(App.generalBundle.getString("command.err.empty"));}
    }

    
}
