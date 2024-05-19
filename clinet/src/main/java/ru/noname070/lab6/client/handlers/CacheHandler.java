package ru.noname070.lab6.client.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.google.gson.JsonIOException;

import ru.noname070.lab6.client.Client;
import ru.noname070.lab6.client.io.Connection;
import ru.noname070.lab6.client.io.seriallizer.Serializer;
import ru.noname070.lab6.client.utils.L18n;

/**
 * caching`nd`execution user scripts
 */
public class CacheHandler {
    private static Set<String> filesToExecute = new HashSet<String>();

    public static void executeScript(String fpath) {
        File file = new File(fpath);
        if (!file.exists()) {
            System.err.println(L18n.getGeneralBundle().getString("command.err.file_not_exist"));
            return;
        }
        if (filesToExecute.contains(file.getAbsolutePath())) {
            System.err.printf(L18n.getGeneralBundle().getString("command.execute_script.recursion"),
                    file.getAbsolutePath());
            return;
        } else {
            filesToExecute.add(file.getAbsolutePath());
        }

        try (FileInputStream commandsStream = new FileInputStream(file)) {
            @SuppressWarnings("resource")
            Scanner localScanner = new Scanner(commandsStream);
            // Socket currentConnection = Client.connection.getConnection();
            BufferedWriter localOut = Client.connection.getOut();
            BufferedReader localIn = Client.connection.getIn();
            while (localScanner.hasNext()) {
                String[] input = localScanner.nextLine().strip().split(" ");
                Command command = new Command();
                switch (input.length) {
                    case 1:
                        command.setName(input[0]);
                        break;

                    case 2:
                        command.setName(input[0]);
                        command.setArgs(input[1]);
                        break;

                    default:
                        return;
                }
                if (command.getName().equals("exit")) {
                    Client.connection.getConnection().close();
                    System.out.println(L18n.getGeneralBundle().getString("command.exit.message"));
                    System.exit(0);
                }
                try {
                    localOut.write(Connection.getGson().toJson(command) + "\n");
                    localOut.flush();

                    String response = localIn.readLine();
                    // untested
                    if (response.equals("dd_need_collection")) {
                        // TODO зачем тут каждый раз создавать объект Serializer. Подумайте как создать его один раз
                        // TODO и про PrintStream, зачем вообще тут это нужно? Можно вынести это в приватный метод сюда же и не делать какую то дичь
                        command.setOrg(new Serializer().serialize(NewElementHandler.newElement(commandsStream,
                                new PrintStream(PrintStream.nullOutputStream())))); // wtf
                        /* часть кода на 100 строчке
                        Вот так код будет просто читабельнее
                        command.setOrg(getOrg(commandsStream));
                         */

                        localOut.write(Connection.getGson().toJson(command) + "\n");
                        localOut.flush();
                    } else
                        // костылек, но иначе надо запариваться с буффером
                        System.out.println(response.replace("|||", "\n"));
                } catch (JsonIOException e) {
                    System.err.println(L18n.getGeneralBundle().getString("json.err.cant_ser"));
                } catch (Exception e) {
                    System.err.println(L18n.getGeneralBundle().getString("net.err.cant_send"));
                    Client.connection.getConnection().close();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    private static final PrintStream ps =  new PrintStream(PrintStream.nullOutputStream());
    private static final Serializer serializer =  new Serializer();
    private static String getOrg(FileInputStream commandStream){
        return serializer.serialize(NewElementHandler.newElement(commandStream, ps));
    }
     */

}
