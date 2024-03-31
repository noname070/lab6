package ru.noname070.lab6.client.data;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
 * element`s coordinates object
 * 
 * @see Organization
 */
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