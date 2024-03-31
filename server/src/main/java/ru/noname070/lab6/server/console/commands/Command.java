package ru.noname070.lab6.server.console.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 */
@NoArgsConstructor
public class Command implements Serializable {
    @Getter
    private String name;
    @Getter
    private String args;
    @Getter
    private String org;

}