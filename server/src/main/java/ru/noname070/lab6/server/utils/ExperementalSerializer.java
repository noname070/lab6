package ru.noname070.lab6.server.utils;

import java.util.LinkedList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import ru.noname070.lab6.server.collection.data.*;

/**
 * ExperementalSerializer for LinkedList to/from xml
 * 
 * @see Organization
 */
public class ExperementalSerializer {
    private final XStream xstream;

    /**
     * Common constructor to i/o with {@link Organization}
     */
    public ExperementalSerializer() {
        xstream = new XStream();

        xstream.alias("address", Address.class);
        xstream.alias("coordinates", Coordinates.class);
        xstream.alias("organization", Organization.class);
        xstream.alias("organizationType", OrganizationType.class);

        // xstream.addImplicitCollection(CollectionManager.class, "data"); // ЕБАНЫЕ
        // РАЗРАБОТЧИКИ КУСКА ГОВНА КОТОРОЕ В ЦИКЛЕ WHILE СИДИТ ПЕРЕБИРАЕТ НЕ-СТАТИК
        // АТРИБУТЫ У КОНТЕЙНЕРА ПОШЛИ НАХУЙ

        xstream.setMode(XStream.NO_REFERENCES);
        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(List.class);
        xstream.allowTypeHierarchy(String.class);
        xstream.ignoreUnknownElements();

    }

    /**
     * serialize LinkedList {@link Organization} collection to xml
     * 
     * @param data : current collection
     * @return rawData : record-ready string
     */
    public String serialize(LinkedList<Organization> data) {
        String rawData = xstream.toXML(data);
        return rawData;
    }

    /**
     * deserialize xml to LinkedList {@link Organization} collection
     * 
     * @param rawData : raw string from xml file
     */
    @SuppressWarnings("unchecked")
    public LinkedList<Organization> deserialize(String rawData, boolean FLAG_toLinked) {
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(List.class);
        xstream.allowTypeHierarchy(String.class);
        xstream.ignoreUnknownElements();
        xstream.allowTypes(new Class[] { LinkedList.class, Organization.class });

        return (LinkedList<Organization>) xstream.fromXML(rawData);
    }

    public Organization deserialize(String rawData) {
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(List.class);
        xstream.allowTypeHierarchy(String.class);
        xstream.ignoreUnknownElements();
        xstream.allowTypes(new Class[] { Organization.class });

        return (Organization) xstream.fromXML(rawData);

    }

}
