package ru.noname07.lab6.console;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.HashMap;

import ru.noname07.lab6.App;
import ru.noname07.lab6.console.commands.*;
import ru.noname07.lab6.utils.IOManager;

/**
 * Console
 * processes a sequence of commands
 */
public class Console {
    private static final HashMap<String, Command> commandList = new HashMap<String, Command>();
    // private boolean isWorking = true;
    private InputStream inputStream;
    private PrintStream printStream;

    private static ArrayDeque<String> commandLines = new ArrayDeque<String>();

    static {
        commandList.put("help", new Help());
        commandList.put("info", new Info());
        commandList.put("show", new Show());
        commandList.put("add", new Add());
        commandList.put("update", new Update());
        commandList.put("remove_by_id", new Remove.RemoveById());
        commandList.put("clear", new Clear());
        commandList.put("save", new Save());
        commandList.put("execute_script", new ExecureScript());
        commandList.put("exit", new Exit());
        commandList.put("remove_last", new Remove.RemoveLast());
        commandList.put("remove_greater", new Remove.RemoveGreater());
        commandList.put("sort", new Sort());
        commandList.put("filter_by_annual_turnover", new Filter.FilterByAnnualTurnover());
        commandList.put("filter_greater_than_annual_turnover", new Filter.FilterByGreaterThanAnnualTurnover());
        commandList.put("print_field_ascending_official_address", new PrintField());
        // addition task 3
        commandList.put("start_cache", new Cache.StartCache());
        commandList.put("save_cache", new Cache.SaveCache());
    }

    /**
     * Constructor if needs use not stn in/out
     */
    public Console(InputStream inputStream, PrintStream printStream) {
        this.inputStream = inputStream;
        this.printStream = printStream;
    }

    /**
     * Common constructor with std in&out
     */
    public Console() {
        this.inputStream = System.in;
        this.printStream = System.out;
    }

    /**
     * get command list
     * 
     * @return command list
     */
    public HashMap<String, Command> getCommandList() {
        return Console.commandList;
    }

    /**
     * get console installed input stream
     * 
     * @return console input stream
     */
    public InputStream getInputStream() {
        return this.inputStream;
    }

    /**
     * get console installed pring stream
     * 
     * @return console print stream
     */
    public PrintStream getPrintStream() {
        return this.printStream;
    }

    /**
     * there should be logic for managing
     * the console status, but it is not used anywhere (
     */

    /**
     * get&remove last command line from the stack
     * 
     * @return command line
     */
    public static String getLastCommandLine() {
        return Console.commandLines.removeLast();
    }

    /**
     * add command line to stack as First-Input
     * @param inputLine : input line
     */
    public static void addCommand(String inputLine) {
        Console.commandLines.addFirst(inputLine);
    }

    /**
     * add command line to stack as Last-Input
     * @param lastLine : input line
     */
    public static void addLast(String lastLine) {
        Console.commandLines.addLast(lastLine);
    }

    /**
     * @return stack size
     */
    public static int getStackSize() {
        return Console.commandLines.size();
    }

    /**
     * processing command lines stack, while not empty
     */
    public void processStack() {
        while (!Console.commandLines.isEmpty()) {
            String inputLine = Console.commandLines.removeLast();
            String input[] = inputLine.split(" ");

            // dev only
            if (input[0].equals("clFileHeap")) {
                ExecureScript.removeFileFromSet(input[1]);
                return;
            }

            if (!(input.length == 0)) {
                if (commandList.containsKey(input[0])) {
                    if (input.length == 1) {
                        commandList.get(input[0]).execute();
                    } else if (input.length == 2) {
                        commandList.get(input[0]).execute(input);
                    }
                }
            }

            if (!App.toScriptSave.equals("")) {
                try {
                    IOManager.addToFile(App.toScriptSave, inputLine);
                } catch (IOException e) {
                    System.err.println("Can`t i/o with file " + App.toScriptSave);
                    e.printStackTrace();
                }
            }
        }
    }

}
