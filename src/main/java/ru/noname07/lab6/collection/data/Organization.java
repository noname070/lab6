package ru.noname07.lab6.collection.data;

import java.time.LocalDate;

import java.util.Arrays;
import javax.xml.bind.annotation.XmlRootElement;

import ru.noname07.lab6.collection.Valid;

/**
 * Main element class
 * 
 * @see Coordinates
 * @see OrganizationType
 * @see Address
 */
@XmlRootElement
public class Organization implements Valid, Comparable<Organization> {

    private static int nextId = 1;

    private int id; // Значение поля должно быть больше 0, Значение этого поля должно быть
                    // уникальным, Значение этого поля должно генерироваться автоматически
    private String name; // Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; // Поле не может быть null
    private java.time.LocalDate creationDate; // Поле не может быть null, Значение этого поля должно генерироваться
                                              // автоматически
    private Float annualTurnover; // Поле может быть null, Значение поля должно быть больше 0
    private Integer employeesCount; // Значение поля должно быть больше 0
    private OrganizationType type; // Поле не может быть null
    private Address officialAddress; // Поле не может быть null

    /*
     * Constructor from ready-made data
     * actually, not used
     */
    public Organization(String name, Coordinates coordinates, float annualTurnover, int employeesCount,
            OrganizationType type, Address officialAddress) {
        this.id = nextId;
        nextId++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = java.time.LocalDate.now();
        this.annualTurnover = annualTurnover;
        this.employeesCount = employeesCount;
        this.type = type;
        this.officialAddress = officialAddress;

    }

    /**
     * Defautl constructor
     * data fills gradually
     */
    public Organization() {
        this.id = nextId;
        nextId++;

        this.creationDate = java.time.LocalDate.now();
    }

    /**
     * @return elemnt`s id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * @return element`s name
     */
    public String getName() {
        return name;
    }

    /**
     * @return element`s coordinates object
     * @see Coordinates
     */
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    /**
     * @return element`s creation date
     */
    public LocalDate getCreationDate() {
        return this.creationDate;
    }

    /**
     * @return element`s annalTurnover
     */
    public Float getAnnualTurnover() {
        return this.annualTurnover;
    }

    /**
     * @return element`s employeesCount
     */
    public int getEmployeesCount() {
        return this.employeesCount;
    }

    /**
     * @return element`s organization enum object
     * @see OrganizationType
     */
    public OrganizationType getOrganizationType() {
        return this.type;
    }

    /**
     * @return element`s address object
     * @see Address
     */
    public Address getAddress() {
        return this.officialAddress;
    }

    /**
     * set static start id couter
     * 
     * @param id : startId counter
     */
    public static void setStartId(Integer id) {
        nextId = id + 1;
    }

    /**
     * get static start id counter
     * 
     * @return startId counter
     */
    public static int getStartId() {
        return nextId;
    }

    /**
     * set name for elemtn
     * 
     * @param newName : new name for element
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * set coordinates for element
     * 
     * @param newCoordinates : Coordinates object for element
     * @see Coordinates
     */
    public void setCoordinates(Coordinates newCoordinates) {
        this.coordinates = newCoordinates;
    }

    /**
     * set annualTurnover for element
     * 
     * @param newAnnualTurnover : newAnnualTurnover for element
     */
    public void setAnnualTurnover(float newAnnualTurnover) {
        this.annualTurnover = newAnnualTurnover;
    }

    /**
     * set employeesCount for element
     * 
     * @param newEmployeesCount : newEmployeesCount for element
     */
    public void setEmployeesCount(int newEmployeesCount) {
        this.employeesCount = newEmployeesCount;
    }

    /**
     * set organization type for element
     * 
     * @param newType : OrganizationType object for element
     * @see OrganizationType
     */
    public void setOrganizationType(OrganizationType newType) {
        this.type = newType;
    }

    /**
     * set address object for element
     * 
     * @param newAddress : Address object for element
     * @see Address
     */
    public void setAddress(Address newAddress) {
        this.officialAddress = newAddress;
    }

    /**
     * set orgnization type object for element
     * 
     * @param type : OrganizationType object for element
     * @see OrganizationType
     */
    public void setType(OrganizationType type) {
        this.type = type;
    }

    /**
     * validation for element`s data
     * 
     * @return element valid? true : false
     */
    @Override
    public boolean isValid() {
        if (this.id <= 0)
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
                this.getOrganizationType(),
                this.getAddress().getStreet());

        return output;
    }

    public int compareTo(Organization org) {
        return org.getEmployeesCount();
    }

}
