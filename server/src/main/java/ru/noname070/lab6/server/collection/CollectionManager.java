package ru.noname070.lab6.server.collection;

import java.io.IOException;
import java.util.LinkedList;

import lombok.Getter;

import ru.noname070.lab6.server.utils.L18n;
import ru.noname070.lab6.server.collection.data.Organization;
import ru.noname070.lab6.server.console.Console;
import ru.noname070.lab6.server.utils.ExperementalSerializer;
import ru.noname070.lab6.server.utils.IOManager;

/**
 * Static class-container to manage collection
 * 
 * @see Organization
 */
public class CollectionManager {
    @Getter public static LinkedList<Organization> data = new LinkedList<Organization>();
    private static String FILE_PATH = "src/main/resources/AppData/data.xml"; 

    /**
     * loads data from file
     * 
     * @see IOManager
     * @see ExperementalSerializer
     */
    public static void loadData() {
        String rawData;
        int maxId = 1;
        try {
            if (!IOManager.checkFile(FILE_PATH)) {
                Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("cm.err.cant_io") + "\"" + FILE_PATH + "\" ");
                System.exit(-1);
            }
            rawData = IOManager.readFromFile(FILE_PATH);
            if (rawData.isEmpty()) {
                Console.getConsolePrintStream().println(String.format(L18n.getGeneralBundle().getString("cm.err.empty"), FILE_PATH));
            } else {
                try {
                    data = new ExperementalSerializer().deserialize(rawData, true);
                } catch (com.thoughtworks.xstream.converters.ConversionException e) {
                    Console.getConsolePrintStream().println("Oops i got errors by xml convertor");
                }
                // id numeration fix

                for (Organization org : data) {
                    if (!org.isValid()) {
                        System.out.println(L18n.getGeneralBundle().getString("cm.err.load_invalid"));
                        data.remove(org);
                    }
                }

                for (Organization org : data) {
                    maxId = Math.max(org.getId(), maxId);
                }
                Organization.setNextId(maxId+1);

                System.out.println(String.format(L18n.getGeneralBundle().getString("cm.data.from_loaded"), FILE_PATH));
            }

        } catch (IOException e) {
            System.err.println(L18n.getGeneralBundle().getString("cm.err.cant_io") + " \"" + FILE_PATH + "\"");
            System.exit(-1);
        }

    }

    /**
     * save data to file
     * 
     * @see IOManager
     * @see ExperementalSerializer
     */
    public static void saveData() {
        try {
            String rawData = new ExperementalSerializer().serialize(getData());
            IOManager.writeToFile(FILE_PATH, rawData);
        } catch (IOException e) {
            System.err.println(L18n.getGeneralBundle().getString("cm.err.cant_io") + FILE_PATH);
            e.printStackTrace();
        }

    }
}
