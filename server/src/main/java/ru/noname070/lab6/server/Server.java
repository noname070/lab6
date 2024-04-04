package ru.noname070.lab6.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBException;

import ru.noname070.lab6.server.collection.CollectionManager;
import ru.noname070.lab6.server.console.Console;
import ru.noname070.lab6.server.utils.L18n;

public class Server {

    private static String host;
    private static int port;
    private static Locale currentLocale;

    public static void main(String[] args) throws UnknownHostException, IOException {
        // helios adaptation
        try {
            switch (args.length) {
                case 2:
                    host = String.valueOf(args[0]);
                    port = Integer.valueOf(args[1]);
                    break;

                case 3:
                    host = String.valueOf(args[0]);
                    port = Integer.valueOf(args[1]);
                    currentLocale = new Locale(args[2].equals("ru") ? "ru_RU" : "en_EN");

                    // default:
                    // throw new IllegalArgumentException("Wrong args. Use `java -jar server.jar
                    // HOST PORT [LOCALE[ru|en]]`");
                default:
                    host = "localhost";
                    port = 42061; // mac os limits src:https://support.apple.com/en-us/103229
                    currentLocale = new Locale("en_EN");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        L18n.setGeneralBundle(ResourceBundle.getBundle("l18n/GeneralBundle", currentLocale));
        CollectionManager.loadData();

        System.out.println(L18n.getGeneralBundle().getString("serv.log.start"));

        try (ServerSocket serverSocket = new ServerSocket(port, 100, InetAddress.getByName(host))) {
            while (!serverSocket.isClosed()) {
                try (Socket client = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {

                    // Deadmou5
                    // System.setOut( new PrintStream(client.getOutputStream()) );

                    Console.setConsolePrintStream(new PrintStream(client.getOutputStream()));
                    System.out.println(
                            L18n.getGeneralBundle().getString("serv.log.new_client") + " " + client.toString());

                    String text = "";
                    do {
                        text = in.readLine();
                        System.out.println(L18n.getGeneralBundle().getString("serv.log.msg_from_client") + text);

                        if (text == null) {
                            System.out.println(L18n.getGeneralBundle().getString("serv.err.client_disconnected"));
                            // System.exit(-1);
                            break;
                        }

                        if (!text.isEmpty()) {
                            Console.addCommand(text);
                            try {
                                Console.processStack();
                            } catch (JAXBException e) {
                                e.printStackTrace();
                            }

                            // DEBUG ONLY
                            // out.write(text + "\n");
                            // out.flush();
                        }

                    } while (!text.equals("exit"));
                    System.out.println(L18n.getGeneralBundle().getString("serv.err.closed"));
                    CollectionManager.saveData();
                }
            }
        }

    }
}
