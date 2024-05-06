package ru.noname070.lab6.server.utils;

import java.util.LinkedList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import lombok.extern.slf4j.Slf4j;
import ru.noname070.lab6.server.collection.data.*;

/**
 * ExperementalSerializer for LinkedList to/from xml.
 * @hidden why experemental? idk, more correctly "v2"
 * @see Organization
 */
@Slf4j
public class ExperementalSerializer {
    private final XStream xstream;

    /**
     * Common constructor to {@link Organization} serialization
     */
    public ExperementalSerializer() {
        xstream = new XStream();

        xstream.alias("address", Address.class);
        xstream.alias("coordinates", Coordinates.class);
        xstream.alias("organization", Organization.class);
        xstream.alias("organizationType", OrganizationType.class);

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
     * @param data current collection
     * @return rawData record-ready string
     */
    public String serialize(LinkedList<Organization> data) {
        log.info("serialize data to file");
        String rawData = xstream.toXML(data);
        return rawData;
    }

    /**
     * deserialize xml to LinkedList {@link Organization} collection
     * 
     * @param rawData       raw string in xml
     * @param FLAG_toLinked flag, if needs to process LinkedList
     */
    @SuppressWarnings("unchecked")
    public LinkedList<Organization> deserialize(String rawData, boolean FLAG_toLinked) {
        log.info("deserialize data to linkedList of elements");
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

    /**
     * deserialize xml to {@link Organization} element
     * 
     * @param rawData raw string in xml
     */
    public Organization deserialize(String rawData) {
        log.info("deserialize data to element");
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
