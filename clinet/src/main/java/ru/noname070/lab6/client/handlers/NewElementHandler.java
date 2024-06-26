package ru.noname070.lab6.client.handlers;

import java.util.Scanner;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

import ru.noname070.lab6.client.utils.L18n;
import ru.noname070.lab6.client.data.*;

/**
 * static for new element creating
 */
public class NewElementHandler {
    private static InputStream in;
    private static PrintStream out;
    /**
     * core method. receives user input (n times) and fills in a new element.
     * uses {@link #checker}
     */
    public static Organization newElement(InputStream currin, PrintStream currout) {

        in = currin;
        out = currout;

        Organization org = new Organization();
        Coordinates cords = new Coordinates();

        Scanner localScanner = new Scanner(in);
        
        out.println(L18n.getGeneralBundle().getString("create.organization.new_element"));
        while (!checker("create.organization.name",
                        localScanner,
                        org::setName,
                        name -> name != null && StringUtils.isAlphaSpace(name),
                        String::valueOf,
                        null)) {
        }

        out.println(L18n.getGeneralBundle().getString("create.coordinates.new_element"));
        while (!checker("create.coordinates.x",
                        localScanner,
                        cords::setX,
                        x -> !x.isEmpty() && StringUtils.isNumeric(x.strip()),
                        Double::valueOf,
                        "create.err.nums_only")) {
        }
        while (!checker("create.coordinates.y",
                        localScanner,
                        cords::setY,
                        y -> !y.isEmpty() && StringUtils.isNumeric(y.strip()),
                        Float::valueOf,
                        "create.err.nums_only")) {
        }
        org.setCoordinates(cords);
        out.println(L18n.getGeneralBundle().getString("create.coordinates.complete"));

        while (!checker("create.organization.annualTurnover",
                        localScanner,
                        org::setAnnualTurnover,
                        annualTurnover -> StringUtils.isNumeric(annualTurnover.strip()),
                        Float::valueOf, 
                        "create.err.incorrect_value")) {
        }
        while (!checker("create.organization.employeesCount", 
                        localScanner,
                        org::setEmployeesCount,
                        employeesCount -> StringUtils.isNumeric(employeesCount.strip()),
                        Integer::valueOf,
                        "create.err.incorrect_value")) {
        }

        do {
            out.printf(
                L18n.getGeneralBundle().getString("create.organization.orgType") + " >",
                        Arrays.stream(OrganizationType.values())
                                .map(Object::toString)
                                .collect(Collectors.joining(" ")));

        } while (!checker(null,
                        localScanner,
                        org::setType,
                        type -> true, //ну да костыль, зато код работает и главное к р а с и в о
                        type -> OrganizationType.valueOf(type.toUpperCase()),
                        "create.err.incorrect_value"));

        out.println(L18n.getGeneralBundle().getString("create.address.new_element"));
        while (!checker("create.address.street",
                        localScanner,
                        org::setOfficialAddress, 
                        street -> !street.isEmpty(),
                        Address::new,
                        "create.err.incorrect_value")) {
        }
        out.println(L18n.getGeneralBundle().getString("create.address.complete"));

        return org;
    }

    /**
     * A basic pipline for entering, checking and adding parts of a new element
     * 
     * invented for FLEX ONLY. but the code is shorter.
     * 
     * @param <R> parameterized type of part for a new element 
     * @param bundleContext what the console asks the user. for the {@link L18n}.
     * @param scannerInput {@link Scanner} obj
     * @param setter sets the new parameterized object to the correct place
     * @param condition input check
     * @param preparing make a string into a parameterized object
     * @param errBundle what the console asks the user in error. for the {@link L18n}.у
     * @return condition met ? true : false 
     * 
     * @see Organization
     * @see L18n
     * 
     */
    private static <R> boolean checker( String bundleContext,
                                        Scanner scannerInput,
                                        Consumer<R> setter,
                                        Predicate<String> condition,
                                        Function<String, R> preparing,
                                        String errBundle) {
        if (bundleContext != null) out.print(L18n.getGeneralBundle().getString(bundleContext) + " >");
        String input = scannerInput.nextLine();
        String err = errBundle != null ? L18n.getGeneralBundle().getString(errBundle) + "\n" : "";
        try {
            if (condition.test(input)) {
                R out = preparing.apply(input);
                setter.accept(out);
                return true;
            } else
                out.print(err);
            return false;
        } catch (Exception e) {
            out.print(err);
            return false;
        }    
    }
}
