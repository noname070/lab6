package ru.noname07.lab6.collection.data;

import javax.xml.bind.annotation.XmlRootElement;

import ru.noname07.lab6.collection.Valid;

/**
 * element`s coordinates object
 * 
 * @see Organization
 */
@XmlRootElement
public class Coordinates implements Valid {
    private Double x;
    private Float y;

    /**
     * default constructor
     * 
     * @param x : coordinate
     * @param y : coordinate
     */
    public Coordinates(double x, Float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * empty constructor
     * data fills gradually
     */
    public Coordinates() {

    }

    /**
     * set x coordinate
     * 
     * @param x : x coordinate
     */
    public void setX(Double x) {
        this.x = x;
    }

    /**
     * set y coordinate
     * 
     * @param y : y coordinate
     */
    public void setY(Float y) {
        this.y = y;
    }

    /**
     * get x coordinate
     * 
     * @return x coordinate
     */
    public Double getX() {
        return this.x;
    }

    /**
     * get y coordinate
     * 
     * @return y coordinate
     */
    public Float getY() {
        return this.y;
    }

    /**
     * validation for object`s data
     * 
     * @return element valid? true : false
     */
    @Override
    public boolean isValid() {
        if (this.x == null || this.x == 0)
            return false;
        if (this.y == 0 || this.y > 715 || this.y == null)
            return false;

        return true;
    }

}