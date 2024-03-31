package ru.noname07.lab6.console.commands;

import java.util.LinkedList;

import ru.noname07.lab6.App;
import ru.noname07.lab6.collection.data.Organization;

/**
 * Realisation for "sort" command
 * @see Command
 */
public class Sort extends Command {

    public Sort() {
        super("sort", App.generalBundle.getString("command.sort.description"), false);
    }

    @Override
    public void execute() {
        if (!App.collection.getData().isEmpty()) {
            LinkedList<Organization> data = App.collection.getData();
            
            for (int i = 1; i < data.size(); i++) {
                Organization key = data.get(i);
                int j = i - 1;

                while (j >= 0 && ((Comparable<Organization>) data.get(j)).compareTo(key) > 0) {
                    data.set(j + 1, data.get(j));
                    j--;
                }

                data.set(j + 1, key);
            }

            System.out.println(App.generalBundle.getString("command.sort.execute"));
        } else {
            System.err.println(App.generalBundle.getString("command.err.empty"));
        }
    }

}
