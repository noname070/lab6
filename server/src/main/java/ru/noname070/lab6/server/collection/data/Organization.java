package ru.noname070.lab6.server.collection.data;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

/**
 * Main element class
 * 
 * @see Coordinates
 * @see OrganizationType
 * @see Address
 */
@XmlRootElement
public class Organization implements Comparable<Organization> {

    @Setter private static int nextId = 1;

    @Getter @Setter private int id; // Значение поля должно быть больше 0, Значение этого поля должно быть
                    // уникальным, Значение этого поля должно генерироваться автоматически
    @Getter @Setter private String name; // Поле не может быть null, Строка не может быть пустой
    @Getter @Setter private Coordinates coordinates; // Поле не может быть null
    @Getter @Setter private java.time.LocalDate creationDate; // Поле не может быть null, Значение этого поля должно генерироваться
                                              // автоматически
    @Getter @Setter private Float annualTurnover; // Поле может быть null, Значение поля должно быть больше 0
    @Getter @Setter private Integer employeesCount; // Значение поля должно быть больше 0
    @Getter @Setter private OrganizationType type; // Поле не может быть null
    @Getter @Setter private Address officialAddress; // Поле не может быть null

    /**
     * Defautl constructor. 
     * data fills gradually
     */
    public Organization() {
        this.id = nextId;
        nextId++;

        this.creationDate = java.time.LocalDate.now();
    }

    public static int nextId() {
        return nextId++;
    }

    /**
     * validation for element`s data
     * 
     * @return element valid? true : false
     */
    public boolean isValid() {
        if (this.id < 0)
            return false;
        if (this.name == null || this.name.isEmpty())
            return false;
        if (this.coordinates == null || !this.coordinates.isValid())
            return false;
        if (this.creationDate == null)
            return false;
        if (this.employeesCount == null || this.employeesCount <= 0)
            return false;
        if (this.annualTurnover != null) {
            if (this.annualTurnover < 0)
                return false;
        }
        if (this.type == null || !Arrays.asList(OrganizationType.values()).contains(this.type))
            return false;
        if (this.officialAddress == null || !this.officialAddress.isValid())
            return false;

        return true;
    }

    @Override
    public String toString() {
        String output = String.format(
                "`Organization` ID: %s; Name: %s; Coordinates: (%s, %s); CreationDate: %s, EmployeesCount: %s, AnnualTurnover: %s, OrganizationType: %s, Address: %s",
                this.getId(),
                this.getName(),
                this.getCoordinates().getX(), this.getCoordinates().getY(),
                this.getCreationDate().toString(),
                this.getEmployeesCount(),
                this.getAnnualTurnover(),
                this.getType(),
                this.getOfficialAddress().getStreet());

        return output;
    }

    public int compareTo(Organization org) {
        return org.getEmployeesCount();
    }

}
