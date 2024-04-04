package ru.noname070.lab6.server.console.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Wrapper for serializable command
 */
@NoArgsConstructor
public class Command {
    @Getter private String name;
    @Getter private String args;
    @Getter private String org;

}