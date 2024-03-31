package ru.noname070.lab6.server.collection.data;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * element`s coordinates object
 * 
 * @see Organization
 */
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates {
    @Getter @Setter private Double x;
    @Getter @Setter private Float y;

    /**
     * validation for object`s data
     * 
     * @return element valid? true : false
     */
    public boolean isValid() {
        if (this.x == null || this.x == 0)
            return false;
        if (this.y == 0 || this.y > 715 || this.y == null)
            return false;

        return true;
    }

}