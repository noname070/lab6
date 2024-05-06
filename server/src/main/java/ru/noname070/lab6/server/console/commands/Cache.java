package ru.noname070.lab6.server.console.commands;

// NOTE : КЕШИРУЕМСЯ НА СТОРОНЕ КЛИЕНТА, ДЛЯ СЕРВЕРА ЭТО ЮЗЛЕСС КОД

// import java.io.BufferedInputStream;
// import java.io.BufferedOutputStream;
// import java.io.IOException;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;

import org.apache.commons.lang3.StringUtils;

import ru.noname070.lab6.server.utils.L18n;
// import ru.noname070.lab6.server.Server;
import ru.noname070.lab6.server.console.Console;

/**
 * Wrapper class for cache-like commands. Useless in lab6 on server side
 * @deprecated client-side realisation
 * @see AbstractCommand
 */
public class Cache {

    /**
     * Realisation for "start_cache" command
     * @deprecated client-side realisation
     * @see AbstractCommand
     * @see Cache
     */
    public static class StartCache extends AbstractCommand {

        public StartCache() {
            super("start_cache", L18n.getGeneralBundle().getString("command.start_cache.description"), false, false);
        }

        @Override
        public void execute() {
            // Server.toScriptSave = "src/main/resources/AppData/SAVED_SCRIPT.txt";
            Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("command.start_cache.execute"));
        }

    }

    /**
     * Realisation for "save_cache" command
     * @deprecated client-side realisation
     * @see AbstractCommand
     * @see Cache
     */
    public static class SaveCache extends AbstractCommand {

        public SaveCache() {
            super("save_cache", L18n.getGeneralBundle().getString("command.save_cache.description"), false, false);
        }

        @Override
        public void execute() {
            // if (!Server.toScriptSave.equals("")) {
                // Server.toScriptSave = "";

                Console.getConsolePrintStream()
                        .println(L18n.getGeneralBundle().getString("command.save_cache.execute") ); //+ Server.toScriptSave);
            // }
        }

        @Override
        public void execute(String arg) {
            // if (!Server.toScriptSave.equals("")) {
                if (StringUtils.isAlphanumeric(arg)) {
                    // try (BufferedInputStream bInputStream = new BufferedInputStream(
                    //         new FileInputStream(Server.toScriptSave));
                    //         BufferedOutputStream bOutputStream = new BufferedOutputStream(
                    //                 new FileOutputStream("resources/AppData/" + arg + ".txt"))) {
                    //     byte[] bytes = bInputStream.readAllBytes();
                    //     bInputStream.close();

                    //     bOutputStream.write(bytes);
                    //     bOutputStream.close();

                    // } catch (IOException e) {
                    //     // System.err.println(L18n.getGeneralBundle().getString("command.err.incorrect_value"));
                    //     e.printStackTrace();
                    // }

                    Console.getConsolePrintStream()
                            .println(L18n.getGeneralBundle().getString("command.save_cache.execute")
                                    + "resources/AppData/" + arg + ".txt");
                } else
                    Console.getConsolePrintStream()
                            .println(L18n.getGeneralBundle().getString("command.err.incorrect_value"));
            // }
        }

    }

}
