package ru.noname070.lab6.client.io.seriallizer;

import ru.noname070.lab6.client.data.*;

import java.util.LinkedList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

/**
 * serialization of a collection to xml and back
 */
public class Serializer {
    private final XStream xstream;

    /**
     * Default construct
     */
    public Serializer() {
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
     * @param organization current collection
     * @return rawData serialized string
     */
    public String serialize(Organization organization) {
        String rawData = xstream.toXML(organization);
        return rawData;
    }
    
    /**
     * deserialize xml to LinkedList {@link Organization} collection
     * 
     * @param rawData raw string from xml
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
