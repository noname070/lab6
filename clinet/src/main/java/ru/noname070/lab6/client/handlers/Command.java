package ru.noname070.lab6.client.handlers;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

/**
 * Wrapper for communication between client and server
 */
@NoArgsConstructor
public class Command {

    @Getter @Setter private String name;
    @Setter @Getter private String args;
    @Setter private String org; // serialized Organization

}