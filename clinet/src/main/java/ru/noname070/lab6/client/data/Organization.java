package ru.noname070.lab6.client.data;

import java.util.Arrays;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Main element class
 * 
 * @see Coordinates
 * @see OrganizationType
 * @see Address
 */
@XmlRootElement
public class Organization implements Comparable<Organization>, Serializable {

    @SuppressWarnings("unused") // NOTE : need for good serialization
    private static int nextId;

    @Setter @Getter private int id; // Значение поля должно быть больше 0, Значение этого поля должно быть
                    // уникальным, Значение этого поля должно генерироваться автоматически
    @Setter @Getter private String name; // Поле не может быть null, Строка не может быть пустой
    @Setter @Getter private Coordinates coordinates; // Поле не может быть null
    @Setter @Getter private java.time.LocalDate creationDate; // Поле не может быть null, Значение этого поля должно генерироваться
                                              // автоматически
    @Setter @Getter private Float annualTurnover; // Поле может быть null, Значение поля должно быть больше 0
    @Setter @Getter private Integer employeesCount; // Значение поля должно быть больше 0
    @Setter @Getter private OrganizationType type; // Поле не может быть null
    @Setter @Getter private Address officialAddress; // Поле не может быть null

    /**
     * Defautl constructor
     * data fills gradually
     */
    public Organization() {
        this.creationDate = java.time.LocalDate.now();
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
        if (this.annualTurnover != null || this.annualTurnover < 0) {
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
        return String.format(
                "`Organization` ID: %s; Name: %s; Coordinates: (%s, %s); CreationDate: %s, EmployeesCount: %s, AnnualTurnover: %s, OrganizationType: %s, Address: %s",
                this.getId(),
                this.getName(),
                this.getCoordinates().getX(), this.getCoordinates().getY(),
                this.getCreationDate().toString(),
                this.getEmployeesCount(),
                this.getAnnualTurnover(),
                this.getType(),
                this.getOfficialAddress().getStreet());
    }

    public int compareTo(Organization org) {
        return org.getEmployeesCount();
    }

}
