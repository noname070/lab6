package ru.noname07.lab6.collection.data;

import javax.xml.bind.annotation.XmlRootElement;

import ru.noname07.lab6.collection.Valid;

/**
 * element`s address object
 * @see Organization
 */
@XmlRootElement
public class Address implements Valid {
    private String street; // Поле может быть null

    /**
     * default constructor
     * @param street
     */
    public Address(String street) {
        this.street = street;
    }

    /**
     * get object`s street
     * @return street
     */
    public String getStreet() {
        return this.street;
    }

    /**
     * set object`s address
     * @param street
     */
    public void setAddress(String street) {
        this.street = street;
    }

    /**
     * validation for object`s data
     * 
     * @return element valid? true : false
     */
    @Override
    public boolean isValid() {
        if (this.street.isEmpty())
            return false;

        return true;
    }

}
