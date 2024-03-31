package ru.noname07.lab6.console.commands;

import java.util.ArrayList;
import java.util.Arrays;

import ru.noname07.lab6.App;

/**
 * command implementation super-class
 * one of the execute methods must be overridden
 * @see ICommand
 */
public abstract class Command implements ICommand {
    private String name;
    private String description;
    private boolean needArgs = false;

    /**
     * Common constructor
     * @param name : command name
     * @param description : command description
     * @param needArgs : flag, command need args? true : fale
     */
    public Command(String name, String description, boolean needArgs) {
        this.name = name;
        this.description = description;
        this.needArgs = needArgs;
    }

    /**
     * common execute without args
     */
    public void execute() {
        if (!this.needArgs) {
            System.out.println(App.generalBundle.getString("command.command.execute") + this.getClass().getName());
        } else {
            System.err.printf(App.generalBundle.getString("command.command.execute"), this.name);
        }
    }

    /**
     * common execute with args
     */
    public void execute(String[] args) {
        if (this.needArgs) {
            ArrayList<String> localArgs = new ArrayList<String>();
            localArgs.addAll(Arrays.asList(args));
            localArgs.remove(0);

            if (localArgs.size() != 1) {
                System.err.println(App.generalBundle.getString("command.err.no_param"));
            } else
                System.out.printf(App.generalBundle.getString("command.command.executed_with_args"), this.getClass().getName(), args[0]);
        } else {
            System.err.printf(App.generalBundle.getString("command.err.use_without_args"), this.name);
        }
    }

    /**
     * get command name
     * @return command name
     */
    public String getName() {
        return this.name;
    }

    /**
     * get command description
     * @return command description
     * @see Help
     */
    public String getDescription() {
        return this.description;
    }

}