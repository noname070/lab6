package ru.noname070.lab6.server.collection.data;

import javax.xml.bind.annotation.XmlEnum;

/**
 * element`s orgType object
 * 
 * @see Organization
 */
@XmlEnum
public enum OrganizationType {
    COMMERCIAL,
    PUBLIC,
    GOVERNMENT,
    PRIVATE_LIMITED_COMPANY,
    OPEN_JOINT_STOCK_COMPANY;
}
