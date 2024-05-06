package ru.noname070.lab6.server.console.commands;

import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import ru.noname070.lab6.server.utils.L18n;
import ru.noname070.lab6.server.collection.CollectionManager;
import ru.noname070.lab6.server.collection.data.Organization;
import ru.noname070.lab6.server.console.Console;

/**
 * Wrapper class for remove-like commands
 * 
 * @see AbstractCommand
 */
public class Remove {

    /**
     * Realisation for "remove_last" command
     * 
     * @see AbstractCommand
     * @see Remove
     */
    @Slf4j
    public static class RemoveLast extends AbstractCommand {

        public RemoveLast() {
            super("remove_last", L18n.getGeneralBundle().getString("command.remove_last.description"), false, false);
        }

        @Override
        public void execute() {
            log.info("executed command '" + getName() + "'");
            if (!CollectionManager.getData().isEmpty()) {
                Organization org = CollectionManager.getData().removeLast();
                log.info("removed last element");
                Console.getConsolePrintStream().printf(L18n.getGeneralBundle().getString("command.remove_last.execute"),
                        org.getName(), org.getId(), org.hashCode());
            } else {
                log.error("Empty data");
                Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.err.empty"));
            }
        }

    }

    /**
     * Realisation for "remove_greater" command
     * 
     * @see AbstractCommand
     * @see Remove
     */
    @Slf4j
    public static class RemoveGreater extends AbstractCommand {

        public RemoveGreater() {
            super("remove_greater", L18n.getGeneralBundle().getString("command.remove_greater.description"), false, true);
        }

        @Override
        public void execute(Organization inputOrg) {
            log.info("executed command '" + getName() + "', org:" + inputOrg.toString());
            if (!CollectionManager.getData().isEmpty()) {
                String output = CollectionManager.getData().stream()
                        .filter(org -> inputOrg.compareTo(inputOrg) > 0 ? true : false)
                        .map(orgToRemove -> {
                            CollectionManager.getData().remove(orgToRemove);
                            return String.format(L18n.getGeneralBundle().getString("command.remove_greater.execute"),
                                    orgToRemove.getName(), orgToRemove.getId(), orgToRemove.hashCode());
                        }).collect(Collectors.joining("|||"));
                log.info("removed items :" + output.split("|||").length );
                Console.getConsolePrintStream().println(output);
            } else {
                log.error("Empty data");
                Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.err.empty"));
            }
        }

    }

    /**
     * Realisation for "remove_by_id" command
     * 
     * @see AbstractCommand
     * @see Remove
     */
    @Slf4j
    public static class RemoveById extends AbstractCommand {

        public RemoveById() {
            super("remove_by_id", L18n.getGeneralBundle().getString("command.remove_by_id.description"), true, false);
        }

        @Override
        public void execute(String arg) {
            log.info("executed command '" + getName() + "', args:" + arg);
            if (!CollectionManager.getData().isEmpty()) {

                // тз есть тз :lois
                int idToRemove = Integer.parseInt(arg);
                String output = CollectionManager.getData().stream()
                        .filter(org -> org.getId() == idToRemove)
                        .map(orgToRemove -> {
                            CollectionManager.getData().remove(orgToRemove);
                            log.info("removed org :" + orgToRemove.toString());
                            return String.format(L18n.getGeneralBundle().getString("command.remove_by_id.execute"),
                                    orgToRemove.getName(), orgToRemove.getId(), orgToRemove.hashCode());
                        }).collect(Collectors.joining("|||"));
                Console.getConsolePrintStream().println(output);
            }
        }

    }

}
