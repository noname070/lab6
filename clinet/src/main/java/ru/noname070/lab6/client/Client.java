package ru.noname070.lab6.client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import ru.noname070.lab6.client.io.Authform;
import ru.noname070.lab6.client.io.Connection;
import ru.noname070.lab6.client.utils.L18n;

/* 
  java -jar cleint.jar HOST PORT [LOCALE]
*/

public class Client {
    public static Connection connection;

    private static Locale currentLocale;
    private static String host;
    private static int port;

    public static void main(String[] args) throws UnknownHostException, IOException {
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
                    // throw new IllegalArgumentException("Wrong args. Use `java -jar client.jar
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

        L18n.setGeneralBundle(ResourceBundle.getBundle("l18n/GeneralBundle", Client.currentLocale));

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        while (connection.isWorking()) {
            try {
                System.out.print("|<lab6>| >");
                String input = scanner.nextLine();
                if (!input.isEmpty()) {
                    connection.processCommand(StringUtils.split(input.strip()));
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

    }
}
