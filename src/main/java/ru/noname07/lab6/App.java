package ru.noname07.lab6;

import java.util.Locale;
import java.util.Scanner;

import ru.noname07.lab6.collection.CollectionManager;
import ru.noname07.lab6.console.Console;

import java.util.ResourceBundle;

/*
 * 0 args   :   common start
 * 1 arg    :   arg = path2data
 * 2 arg    :   1arg = path2data 2arg=lang
 */


public class App {

    public static Locale currentLocale;
    public static String FILE_PATH;
    public static CollectionManager collection;
    public static String toScriptSave = "";
    public static ResourceBundle generalBundle;
    public static Console console;

    public static void main(String[] args) throws Exception {
        if (args.length == 1) {
            FILE_PATH = args[0];
        } else if (args.length == 2) {
            FILE_PATH = args[0];
            currentLocale = new Locale(args[1].equals("ru") ? "ru_RU" : "en_EN");
        } else {
            FILE_PATH = "src/main/resources/AppData/data.xml";
            currentLocale = new Locale("en_EN");
        }

        generalBundle = ResourceBundle.getBundle("l18n/GeneralBundle", App.currentLocale);
        console = new Console();

        collection = new CollectionManager();
        collection.loadData();

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(console.getInputStream());

        while (true) {
            try {
                System.out.print("|<lab5>| >");
                Console.addCommand(scanner.nextLine());
                console.processStack();
            } catch (java.util.NoSuchElementException e) {
                System.out.println(generalBundle.getString("command.exit.message"));
            }
        }
    }

}