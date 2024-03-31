package ru.noname07.lab6.utils;

import java.util.LinkedList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import ru.noname07.lab6.collection.CollectionManager;
import ru.noname07.lab6.collection.data.*;

/**
 * ExperementalSerializer for LinkedList to/from xml  
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

        xstream.addImplicitCollection(CollectionManager.class, "data");

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
    public LinkedList<Organization> deserialize(String rawData) {
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES); 
        xstream.allowTypeHierarchy(List.class);
        xstream.allowTypeHierarchy(String.class);
        xstream.ignoreUnknownElements();
        xstream.allowTypes(new Class[] {LinkedList.class, Organization.class});

        @SuppressWarnings("unchecked")
        LinkedList<Organization> data = (LinkedList<Organization>) xstream.fromXML(rawData);
        return data;
    }

    
}
