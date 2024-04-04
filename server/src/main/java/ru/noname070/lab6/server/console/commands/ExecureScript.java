package ru.noname070.lab6.server.console.commands;

import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;

import ru.noname070.lab6.server.utils.L18n;
import ru.noname070.lab6.server.console.Console;

/**
 * Realisation for "execute_script" command. Useless in lab6 on server side
 * @deprecated client-side realisation
 * @see AbstractCommand
 */

// TODO НА СТОРОНЕ КЛИЕНТА
public class ExecureScript extends AbstractCommand {

    /**
     * stores executable scripts. needed to prevent recursion
     */
    private static HashSet<String> filesToExecute = new HashSet<String>();

    public ExecureScript() {
        super("excute script", L18n.getGeneralBundle().getString("command.execute_script.description"), true);

    }

    /**
     * part of the recursion prevention mechanism.
     * 
     * @param filePath : absolute path to the executable file
     * @apiNote use only after executed sxript ends
     */
    public static void removeFileFromSet(String filePath) {
        filesToExecute.remove(filePath);
    }

    @Override
    public void execute(String arg) { // 10 iq realisaiton
        File file = new File(arg);
        if (!file.exists()) {
            Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.err.file_not_exist"));
            return;
        }
        if (filesToExecute.contains(file.getAbsolutePath())) {
            Console.getConsolePrintStream().printf(
                    L18n.getGeneralBundle().getString("command.execute_script.recursion") + '\n', file.getAbsolutePath());
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
