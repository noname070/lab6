package ru.noname07.lab6.collection;

import java.io.IOException;
import java.util.LinkedList;

import ru.noname07.lab6.App;
import ru.noname07.lab6.collection.data.Organization;
import ru.noname07.lab6.utils.ExperementalSerializer;
import ru.noname07.lab6.utils.IOManager;

/**
 * Class-container to manage collection
 * 
 * @see Organization
 */
public class CollectionManager {
    private LinkedList<Organization> data = new LinkedList<Organization>();

    /**
     * @return collection data
     * @see Organization
     */
    public LinkedList<Organization> getData() {
        return data;
    }

    /**
     * loads data from file
     * 
     * @see IOManager
     * @see ExperementalSerializer
     */
    public void loadData() {
        String rawData;
        int maxId = 0;
        try {
            if (!IOManager.checkFile(App.FILE_PATH)) {
                System.err.println(App.generalBundle.getString("cm.err.cant_io") + "\"" + App.FILE_PATH + "\" ");
                System.exit(-1);
            }
            rawData = IOManager.readFromFile(App.FILE_PATH);
            if (rawData.isEmpty()) {
                System.err.println(String.format(App.generalBundle.getString("cm.err.empty"), App.FILE_PATH));
            } else {
                try {
                    data = new ExperementalSerializer().deserialize(rawData);
                } catch (com.thoughtworks.xstream.converters.ConversionException e) {
                    System.err.println("Oops i got errors by xml convertor");
                }
                // id numeration fix

                for (Organization org : data) {
                    if (!org.isValid()) {
                        System.out.println(App.generalBundle.getString("cm.err.load_invalid"));
                        data.remove(org);
                    }
                }

                for (Organization org : data) {
                    maxId = Math.max(org.getId(), maxId);
                }
                Organization.setStartId(maxId);

                System.out.println(String.format(App.generalBundle.getString("cm.data.from_loaded"), App.FILE_PATH));
            }

        } catch (IOException e) {
            System.err.println(App.generalBundle.getString("cm.err.cant_io") + " \"" + App.FILE_PATH + "\"");
            System.exit(-1);
        }

    }

    /**
     * save data to file
     * 
     * @see IOManager
     * @see ExperementalSerializer
     */
    public void saveData() {
        try {
            String rawData = new ExperementalSerializer().serialize(data);
            IOManager.writeToFile(App.FILE_PATH, rawData);
        } catch (IOException e) {
            System.err.println(App.generalBundle.getString("cm.err.cant_io") + App.FILE_PATH);
            e.printStackTrace();
        }

    }
}
