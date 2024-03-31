package ru.noname070.lab6.client.handlers;

import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

import ru.noname070.lab6.client.utils.L18n;
import ru.noname070.lab6.client.data.Address;
import ru.noname070.lab6.client.data.Coordinates;
import ru.noname070.lab6.client.data.Organization;
import ru.noname070.lab6.client.data.OrganizationType;

public class NewElementHandler {
    public static Organization newElement() {

        Organization org = new Organization();
        Coordinates cords = new Coordinates();

        Scanner localScanner = new Scanner(System.in);

        System.out.println(L18n.getGeneralBundle().getString("create.organization.new_element"));
        while (!checker("create.organization.name",
                        localScanner,
                        org::setName,
                        name -> name != null && StringUtils.isAlphaSpace(name),
                        String::valueOf,
                        null)) {
        }

        System.out.println(L18n.getGeneralBundle().getString("create.coordinates.new_element"));
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
        System.err.println(L18n.getGeneralBundle().getString("create.coordinates.complete"));

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
            System.out.printf(
                L18n.getGeneralBundle().getString("create.organization.orgType") + " >",
                        Arrays.stream(OrganizationType.values())
                                .map(Object::toString)
                                .collect(Collectors.joining(" ")));

        } while (!checker(null,
                        localScanner,
                        org::setType,
                        type -> true,
                        type -> OrganizationType.valueOf(type.toUpperCase()),
                        "create.err.incorrect_value"));

        System.out.println(L18n.getGeneralBundle().getString("create.address.new_element"));
        while (!checker("create.address.street",
                        localScanner,
                        org::setOfficialAddress, 
                        street -> !street.isEmpty(),
                        Address::new,
                        "create.err.incorrect_value")) {
        }
        System.out.println(L18n.getGeneralBundle().getString("create.address.complete"));

        return org;
    }

    private static <R> boolean checker( String bundleContext,
                                        Scanner scannerInput,
                                        Consumer<R> setter,
                                        Predicate<String> condition,
                                        Function<String, R> preparing,
                                        String errBundle) {
        if (bundleContext != null) System.out.print(L18n.getGeneralBundle().getString(bundleContext) + " >");
        String input = scannerInput.nextLine();
        String err = errBundle != null ? L18n.getGeneralBundle().getString(errBundle) + "\n" : "";
        try {
            if (condition.test(input)) {
                R out = preparing.apply(input);
                setter.accept(out);
                return true;
            } else
                System.err.print(err);
            return false;
        } catch (Exception e) {
            System.err.print(err);
            return false;
        }    
    }
}
