package ru.noname07.lab6.utils;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.LinkedList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ru.noname07.lab6.collection.data.*;

/**
 * Old serializer based on {@link javax.xml.bind.JAXB}
 * actually not used
 */
public class Serializer {
    private JAXBContext jaxbContext;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public Serializer() throws JAXBException {
        this.jaxbContext = JAXBContext.newInstance(Organization.class, Address.class, Coordinates.class,
                OrganizationType.class);
        this.marshaller = jaxbContext.createMarshaller();
        this.unmarshaller = jaxbContext.createUnmarshaller();
    }

    public String serialize(LinkedList<Organization> data) throws JAXBException {
        this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        this.marshaller.marshal(data, stringWriter);
        return stringWriter.toString();
    }

    @SuppressWarnings("unchecked")
    public LinkedList<Organization> deserialize(String xml) throws JAXBException {
        StringReader stringReader = new StringReader(xml);
        return (LinkedList<Organization>) this.unmarshaller.unmarshal(stringReader);

    }

}
