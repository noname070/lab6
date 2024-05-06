package ru.noname070.lab6.server.console.commands;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;
import ru.noname070.lab6.server.utils.L18n;
import ru.noname070.lab6.server.collection.CollectionManager;
import ru.noname070.lab6.server.collection.data.Organization;
import ru.noname070.lab6.server.console.Console;

import java.util.stream.Collectors;

/**
 * Wrapper class for filer-like commands
 * 
 * @see AbstractCommand
 */
public class Filter {

    /**
     * Realisation for "filter_by_annual_turnover" command
     * 
     * @see AbstractCommand
     * @see Filter
     */
    @Slf4j
    static public class FilterByAnnualTurnover extends AbstractCommand {

        public FilterByAnnualTurnover() {
            super("filter_by_annual_turnover",
                    L18n.getGeneralBundle().getString("command.filter_by_annual_turnover.description"), true, false);
        }

        @Override
        public void execute(String arg) {
            log.info("executed command '" + getName() + "', args:" + arg);
            if (!CollectionManager.getData().isEmpty()) {
                if (!StringUtils.isNumeric(arg.replace(".", ""))) {
                    log.error("Incorrect input");
                    Console.getConsolePrintStream()
                            .println(L18n.getGeneralBundle().getString("command.err.incorrect_value"));
                    return;
                }

                float annualTurnover = Float.parseFloat(arg);

                String output = CollectionManager.getData()
                        .stream()
                        .filter(org -> org.getAnnualTurnover().equals(annualTurnover))
                        .map(Organization::toString)
                        .collect(Collectors.joining("\n")).toString();
                log.info("filtered count: " + output.length());

                Console.getConsolePrintStream().println(
                        String.format(L18n.getGeneralBundle()
                                .getString("command.filter_greater_than_annual_turnover.execute"), arg)
                                + output);
            } else {
                log.error("Empty data");
                Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.err.empty"));
            }
        }
    }

    /**
     * Realisation for "filter_greater_than_annual_turnover" command
     * 
     * @see AbstractCommand
     * @see Filter
     */
    @Slf4j
    static public class FilterByGreaterThanAnnualTurnover extends AbstractCommand {

        public FilterByGreaterThanAnnualTurnover() {
            super("filter_greater_than_annual_turnover",
                    L18n.getGeneralBundle().getString("command.filter_greater_than_annual_turnover.description"), true, false);
        }

        @Override
        public void execute(String arg) {
            log.info("executed command '" + getName() + "', args:" + arg);
            if (!CollectionManager.getData().isEmpty()) {
                if (!StringUtils.isNumeric(arg)) {
                    log.error("Incorrect input");
                    Console.getConsolePrintStream()
                            .println(L18n.getGeneralBundle().getString("command.err.incorrect_value"));
                    return;
                }
                float annualTurnover = Float.parseFloat(arg);

                String output = CollectionManager.getData().stream()
                        .filter(org -> org != null && org.getAnnualTurnover() > annualTurnover)
                        .map(Organization::toString)
                        .collect(Collectors.joining("|||"));

                log.info("filtered count: " + output.length());

                Console.getConsolePrintStream().println(
                        String.format(L18n.getGeneralBundle()
                                .getString("command.filter_greater_than_annual_turnover.execute"), annualTurnover)
                                + output);
            } else {
                log.error("Empty data");
                Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.err.empty"));
            }

        }

    }

}
