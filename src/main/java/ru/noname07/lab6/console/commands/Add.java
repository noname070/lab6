package ru.noname07.lab6.console.commands;

import ru.noname07.lab6.App;
import ru.noname07.lab6.collection.data.Organization;
import ru.noname07.lab6.console.CreateNewElement;

/**
 * Realisation for "add" command
 * @see Command
 */ 
public class Add extends Command {

    public Add() {
        super("add", App.generalBundle.getString("command.add.description"), false);
    }

    @Override
    public void execute() {
        Organization element = CreateNewElement.newElement();
        if (element.isValid()) {
            // App.collection.getData().add(element);
            // App.collection.getData().add(element.getId(), element);
            App.collection.getData().add(element);
        } else {
            System.err.println(App.generalBundle.getString("command.err.invalid_element"));
        }
    }

    // DEV ONLY
    @Override
    public void execute(String[] args) {
        if (args[1].equals("devrnd")) {
        Organization element = CreateNewElement.genRandom();
        App.collection.getData().add(element);
        System.out.println("[DEV] gen random element to collection.");
        } else {
            System.err.printf("You can`t use add with args\n");
        }
    }

}
