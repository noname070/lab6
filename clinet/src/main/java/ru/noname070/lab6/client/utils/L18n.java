package ru.noname070.lab6.client.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.ResourceBundle;

/**
 * L18n wrapper
 */
public class L18n {
    // TODO Вот у Вас каждый раз вызывается L18n.getGeneralBundle.getString()
    @Getter @Setter static public ResourceBundle generalBundle;

    // TODO Почему бы просто не сделать метод который сразу делает getString()?
    /*
    public static String getString(String key){
        return generalBundle.getString(key);
    }
     */
}

