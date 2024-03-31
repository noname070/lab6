package ru.noname07.lab6.console.commands;

import org.apache.commons.lang3.StringUtils;

import ru.noname07.lab6.App;
import ru.noname07.lab6.collection.data.Organization;
import ru.noname07.lab6.console.CreateNewElement;

/**
 * Realisation for "update" command
 * @see Command
 */
public class Update extends Command {

    public Update() {
        super("update", App.generalBundle.getString("command.update.description"), true);
    }

    @Override
    public void execute(String[] args) {
        if (!App.collection.getData().isEmpty()) {

            if (StringUtils.isNumeric(args[1])) {
                int id = Integer.parseInt(args[1]);
                if (!(id < Organization.getStartId())) {
                    System.err.println(App.generalBundle.getString("command.err.non_existing_element"));
                    return;
                }

                Organization org = CreateNewElement.newElement();
                App.collection.getData().set(id, org);
                System.out.printf(App.generalBundle.getString("command.update.execute"), id);
            }

        } else {System.err.println(App.generalBundle.getString("command.err.empty"));}
    }

    
}
