package ru.noname07.lab6.console.commands;

import java.util.HashSet;
import java.util.Scanner;

import ru.noname07.lab6.App;
import ru.noname07.lab6.console.Console;

import java.io.File;
import java.io.FileInputStream;

/**
 * Realisation for "execute_script" command
 * @see Command
 */
public class ExecureScript extends Command {

    /**
     * stores executable scripts. needed to prevent recursion
     */
    private static HashSet<String> filesToExecute = new HashSet<String>();

    public ExecureScript() {
        super("excute script", App.generalBundle.getString("command.execute_script.description"), true);

    }

    /**
     * part of the recursion prevention mechanism.
     * @param filePath : absolute path to the executable file
     * @apiNote use only after executed sxript ends
     */
    public static void removeFileFromSet(String filePath) {
        filesToExecute.remove(filePath);
    }

    @Override
    public void execute(String[] args) { // 10 iq realisaiton
        File file = new File(args[1]);
        if (!file.exists()) {
            System.err.println(App.generalBundle.getString("command.err.file_not_exist"));
            return;
        }
        if (filesToExecute.contains(file.getAbsolutePath())) {
            System.err.printf(App.generalBundle.getString("command.execute_script.recursion"), file.getAbsolutePath());
            System.exit(-1);
            // return;
        } else {
            filesToExecute.add(file.getAbsolutePath());
        }

        try (FileInputStream commandsStream = new FileInputStream(file)) {
            @SuppressWarnings("resource")
            Scanner localScanner = new Scanner(commandsStream);
            while (localScanner.hasNext()) {
                Console.addCommand(localScanner.nextLine());
            }
            Console.addCommand("clFileHeap " + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
