package ru.noname07.lab6.console.commands;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.IntStream;

import ru.noname07.lab6.App;
import ru.noname07.lab6.collection.data.Organization;

/**
 * Wrapper class for remove-like commands
 * @see Command
 */
public class Remove {

    /**
     * Realisation for "remove_last" command
     * @see Command
     * @see Remove
     */
    public static class RemoveLast extends Command {

        public RemoveLast() {
            super("remove_last", App.generalBundle.getString("command.remove_last.description"), false);
        }

        @Override
        public void execute() {
            if (!App.collection.getData().isEmpty()) {
                Organization org = App.collection.getData().removeLast();
                System.out.printf(App.generalBundle.getString("command.remove_last.execute"),
                                    org.getName(), org.getId(), org.hashCode());
            } else {
                System.err.println(App.generalBundle.getString("command.err.empty"));
            }
        }

    }

    /**
     * Realisation for "remove_greater" command
     * @see Command
     * @see Remove
     */
    public static class RemoveGreater extends Command {

        public RemoveGreater() {
            super("remove_greater", App.generalBundle.getString("command.remove_greater.description"), true);
        }

        @Override
        public void execute() {
            if (!App.collection.getData().isEmpty()) {
                Organization inputElement = null;
                // new inputElement
                LinkedList<Organization> data = App.collection.getData();

                Iterator<Organization> iter = data.iterator();

                IntStream idxs = data
                        .stream()
                        .mapToInt(org -> org.compareTo(inputElement))
                        .filter(i -> i >= 0);

                Set<Integer> idxsToRemove = new HashSet<Integer>(idxs.boxed().toList());
                while (iter.hasNext()) {
                    Organization org = iter.next();
                    if (idxsToRemove.contains(org.getId())) {
                        iter.remove();

                        System.out.printf(App.generalBundle.getString("command.remove_greater.execute"),
                                org.getName(), org.getId(), org.hashCode());
                    }
                }
            } else {
                System.err.println(App.generalBundle.getString("command.err.empty"));
            }
        }

    }

    /**
     * Realisation for "remove_by_id" command
     * @see Command
     * @see Remove
     */
    public static class RemoveById extends Command {

        public RemoveById() {
            super("remove_by_id", App.generalBundle.getString("command.remove_by_id.description"), true);
        }

        @Override
        public void execute(String[] args) {
            if (!App.collection.getData().isEmpty()) {
                LinkedList<Organization> data = App.collection.getData();

                int id = Integer.parseInt(args[1]);
                Iterator<Organization> iter = data.iterator();
                while (iter.hasNext()) {
                    Organization org = iter.next();
                    if (org.getId().equals(id)) {
                        iter.remove();

                        System.out.printf(App.generalBundle.getString("command.remove_by_id.execute")+"\n",
                                org.getName(), org.getId(), org.hashCode());
                        break;
                    }
                }
            }
        }

    }

}
