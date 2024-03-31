package ru.noname070.lab6.server.console.commands;

import ru.noname070.lab6.server.collection.data.Organization;

/**
 * common command iface
 */
public interface ICommand {
    void execute();

    void execute(String arg);

    void execute(Organization org);

    String getName();

    String getDescription();
}
