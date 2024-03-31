package ru.noname070.lab6.client.data;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * element`s address object
 * @see Organization
 */

@XmlRootElement
@AllArgsConstructor
public class Address {
    @Getter @Setter private String street; // Поле может быть null
    
    /**
     * validation for object`s data
     * 
     * @return element valid? true : false
     */
    public boolean isValid() {
        if (this.street.isEmpty())
            return false;

        return true;
    }

}
