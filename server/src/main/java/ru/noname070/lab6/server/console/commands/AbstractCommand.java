package ru.noname070.lab6.server.console.commands;

import java.util.ArrayList;
import java.util.Arrays;

import lombok.Getter;
import ru.noname070.lab6.server.utils.L18n;
import ru.noname070.lab6.server.collection.data.Organization;
import ru.noname070.lab6.server.console.Console;

/**
 * command implementation super-class
 * one of the execute methods must be overridden
 * 
 * @see ICommand
 */
public abstract class AbstractCommand implements ICommand {
    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private boolean needArgs = false;

    /**
     * Common constructor
     * 
     * @param name        : command name
     * @param description : command description
     * @param needArgs    : flag, command need args? true : fale
     */
    public AbstractCommand(String name, String description, boolean needArgs) {
        this.name = name;
        this.description = description;
        this.needArgs = needArgs;
    }

    /**
     * common execute without args
     */
    public void execute() {
        if (!this.needArgs) {
            Console.getConsolePrintStream()
                    .println(L18n.getGeneralBundle().getString("command.command.execute") + this.getClass().getName());
        } else {
            Console.getConsolePrintStream().printf(L18n.getGeneralBundle().getString("command.command.execute"),
                    this.name);
        }
    }

    /**
     * common execute with args
     */
    public void execute(String arg) {
        if (this.needArgs) {
            ArrayList<String> localArgs = new ArrayList<String>();
            localArgs.addAll(Arrays.asList(arg.split(" ")));
            localArgs.remove(0);

            if (localArgs.size() != 1) {
                Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.err.no_param"));
            } else
                Console.getConsolePrintStream().printf(
                        L18n.getGeneralBundle().getString("command.command.executed_with_args"),
                        this.getClass().getName(), arg);
        } else {
            Console.getConsolePrintStream().printf(L18n.getGeneralBundle().getString("command.err.use_without_args"),
                    this.name);
        }
    }

    /*
     * common execute with org
     */
    public void execute(Organization org) {
        if (this.needArgs) {
            Console.getConsolePrintStream().printf(
                    L18n.getGeneralBundle().getString("command.command.executed_with_args"), this.getClass().getName(),
                    org);
        } else {
            Console.getConsolePrintStream().printf(L18n.getGeneralBundle().getString("command.err.use_without_args"),
                    this.name);
        }
    }

}