package ru.noname070.lab6.server.console;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.NoArgsConstructor;
import javax.xml.bind.JAXBException;
import ru.noname070.lab6.server.collection.data.Organization;
import ru.noname070.lab6.server.console.commands.*;
import ru.noname070.lab6.server.utils.ExperementalSerializer;

/**
 * Console
 * processes a sequence of commands
 */
@NoArgsConstructor
@Slf4j
public class Console {
    @Getter private static final Map<String, AbstractCommand> commandList = new HashMap<String, AbstractCommand>();
    private static final Gson gson = new GsonBuilder().create();

    @Getter @Setter private static ArrayDeque<String> commandLines = new ArrayDeque<String>();
    @Getter @Setter private static InputStream consoleInputStream = System.in;
    @Getter @Setter private static PrintStream consolePrintStream = System.out;

    static {
        commandList.put("help", new Help());
        commandList.put("info", new Info());
        commandList.put("show", new Show());
        commandList.put("add", new Add());
        commandList.put("update", new Update());
        commandList.put("remove_by_id", new Remove.RemoveById());
        commandList.put("clear", new Clear());
        // commandList.put("save", new Save());
        // commandList.put("execute_script", new ExecureScript());
        // commandList.put("exit", new Exit());
        commandList.put("remove_last", new Remove.RemoveLast());
        commandList.put("remove_greater", new Remove.RemoveGreater());
        commandList.put("sort", new Sort());
        commandList.put("filter_by_annual_turnover", new Filter.FilterByAnnualTurnover());
        commandList.put("filter_greater_than_annual_turnover", new Filter.FilterByGreaterThanAnnualTurnover());
        commandList.put("print_field_ascending_official_address", new PrintField());
        // commandList.put("start_cache", new Cache.StartCache());
        // commandList.put("save_cache", new Cache.SaveCache());
    }

    /**
     * get and remove last command line from the stack
     * 
     * @return command line
     */
    public static String getLastCommandLine() {
        log.info("removed last inputLine");
        return Console.commandLines.removeLast();
    }

    /**
     * add command line to stack as First-Input
     * 
     * @param inputLine : input line
     */
    public static void addCommand(String inputLine) {
        log.info("add new inputLine :" + inputLine);
        Console.commandLines.addFirst(inputLine);
    }

    /**
     * add command line to stack as Last-Input
     * 
     * @param lastLine : input line
     */
    public static void addLast(String lastLine) {
        Console.commandLines.addLast(lastLine);
    }

    /**
     * @return stack size
     */
    public static int getStackSize() {
        log.info("current stack size :" + Console.commandLines.size());
        return Console.commandLines.size();
    }

    /**
     * processing command lines stack, while not empty
     * 
     * @throws JAXBException
     */
    public static void processStack() throws JAXBException {
        log.info("start to processing stack of inputline");
        while (!Console.commandLines.isEmpty()) {
            String inputLine = Console.commandLines.removeLast();
            log.info("current inputLine :" + inputLine);

            Command inputCommand = gson.fromJson(inputLine, Command.class);

            String commandName = inputCommand.getName();
            String commandArgs = inputCommand.getArgs();
            Organization org = null;
            String commandOrg = inputCommand.getOrg();

            log.info("deserialized command : %s[name=%s;args=%s;org=%s]", inputCommand.toString(), commandName, commandArgs, commandOrg);

            if (commandOrg != null) {
                org = new ExperementalSerializer().deserialize(commandOrg);
            }

            AbstractCommand command = Console.commandList.get(commandName);
            if (command == null) {
                Console.getConsolePrintStream().println("");
                return;
            }

            if (command.isNeedOrg() && org==null) {
                Console.getConsolePrintStream().println("dd_need_collection");
                Console.commandLines.addLast(inputLine);
                continue;
            }

            if (commandArgs != null) {
                command.execute(commandArgs);
            } else if (org != null) {
                command.execute(org);
            } else {
                command.execute();
            }

        }
    }

}
