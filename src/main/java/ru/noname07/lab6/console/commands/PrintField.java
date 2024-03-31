package ru.noname07.lab6.console.commands;

import java.util.ArrayList;
import java.util.Collections;

import ru.noname07.lab6.App;
import ru.noname07.lab6.collection.data.Organization;

/**
 * Realisation for "print_field_ascending_official_address" command
 * @see Command
 */
public class PrintField extends Command {

    public PrintField() {
        super("print_field_ascending_official_address", App.generalBundle.getString("command.print_field_ascending_official_address.description"),
                false);
    }

    @Override
    public void execute() {
        if (!App.collection.getData().isEmpty()) {
            System.out.println( App.generalBundle.getString("command.print_field_ascending_official_address.execute"));
            ArrayList<String> addresses = new ArrayList<>();
            for (Organization org : App.collection.getData()) {
                addresses.add(org.getAddress().getStreet());
            }

            Collections.sort(addresses);
            addresses.forEach(System.out::println);

        } else {
            System.err.println( App.generalBundle.getString("command.err.empty"));
        }
    }

}
