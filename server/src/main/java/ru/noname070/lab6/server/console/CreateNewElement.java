package ru.noname070.lab6.server.console;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.function.*;

import org.apache.commons.lang3.StringUtils;

import ru.noname070.lab6.server.utils.L18n;
import ru.noname070.lab6.server.collection.data.*;

/**
 * Manager for new elements
 */
public class CreateNewElement {

    /**
     * run new element builder
     * if {@link Console#getStackSize} empty (means that commands are processed from
     * the incoming stream) : build step-by-step from incomming stream
     * else : process stack to load data
     */
    public static Organization newElement() {
        if (Console.getStackSize() == 0) {
            // return CreateNewElement.fromInputEx();
            return CreateNewElement.fromInput();
        } else
            return CreateNewElement.fromStack();
    }

    /**
     * bully loads data from stack
     * 
     * @see {@link CreateNewElement#newElement}
     */
    public static Organization fromStack() { // warning : if stack is not full - runtime error; recomend : redirect the
                                             // stream to .fromInput(), thereby saving checks
        Organization org = new Organization();
        org.setName(Console.getLastCommandLine());
        org.setCoordinates(new Coordinates(
                Double.parseDouble(Console.getLastCommandLine()),
                Float.parseFloat(Console.getLastCommandLine())));
        org.setAnnualTurnover(Float.parseFloat(Console.getLastCommandLine()));
        org.setEmployeesCount(Integer.parseInt(Console.getLastCommandLine()));
        org.setType(OrganizationType.valueOf(Console.getLastCommandLine()));
        org.setOfficialAddress(new Address(Console.getLastCommandLine()));
        if (org.isValid()) {
            return org;
        } else {
            Console.getConsolePrintStream().println(L18n.getGeneralBundle().getString("create.err.incorrect_value"));
            System.exit(-1); // TODO какая то шляпа получается (
            return null; // for vscode
        }
    }

    /**
     * loads data from console input stream, with all checkings
     * 
     * @see {@link CreateNewElement#newElement}
     */

    public static Organization fromInputEx() {
        Organization org = new Organization();
        Coordinates cords = new Coordinates();

        Scanner localScanner = new Scanner(System.in);

        System.out.println(L18n.getGeneralBundle().getString("create.organization.new_element"));
        while (!checker("create.organization.name",
                localScanner,
                org::setName,
                name -> !name.equals(null) && StringUtils.isAlpha(name),
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
                type -> true, // TODO lmao
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

    private static <R> boolean checker(String bundleContext,
            Scanner scannerInput,
            Consumer<R> setter,
            Predicate<String> condition,
            Function<String, R> preparing,
            String errBundle) {

        if (bundleContext != null) {
            System.out.print(L18n.getGeneralBundle().getString(bundleContext) + " >");
        }

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

    public static Organization fromInput() {

        Organization org = new Organization();

        String input = "";

        System.out.println(L18n.getGeneralBundle().getString("create.organization.new_element"));
        @SuppressWarnings("resource")
        Scanner localScanner = new Scanner(System.in);

        // Name
        String name = "";
        while (true) {
            System.out.print(L18n.getGeneralBundle().getString("create.organization.name") + " >");
            name = localScanner.nextLine();
            if (!name.isEmpty() && StringUtils.isAlpha(name)) {
                org.setName(name);
                break;
            }
        }

        // Coordinates
        Coordinates coordinates = new Coordinates();
        System.out.println(L18n.getGeneralBundle().getString("create.coordinates.new_element"));

        while (true) {
            System.out.print(L18n.getGeneralBundle().getString("create.coordinates.x") + " >");
            String x = localScanner.nextLine();

            System.out.print(L18n.getGeneralBundle().getString("create.coordinates.y") + " >");
            String y = localScanner.nextLine();

            if (!x.isEmpty() && !y.isEmpty() && StringUtils.isNumeric(x.strip()) && StringUtils.isNumeric(y.strip())) {
                coordinates.setX(Double.parseDouble(x.strip()));
                coordinates.setY(Float.parseFloat(y.strip()));

                break;
            } else {
                System.err.println(L18n.getGeneralBundle().getString("create.err.nums_only"));
            }
        }
        System.err.println(L18n.getGeneralBundle().getString("create.coordinates.complete"));
        org.setCoordinates(coordinates);

        // annualTurnover
        while (true) {
            System.out.print(" annualTurnover>");
            input = localScanner.nextLine();
            if (input.equals("")) {
                // org.setAnnualTurnover(null);
                break;
            } else if (StringUtils.isNumeric(input.strip())) {
                org.setAnnualTurnover(Float.parseFloat(input));
                break;
            } else {
                System.err.println(L18n.getGeneralBundle().getString("create.err.incorrect_value"));
            }
        }
        input = "";

        // employeesCount
        while (true) {
            System.out.print(" employeesCount>");
            input = localScanner.nextLine();
            if (!input.equals("") & StringUtils.isNumeric(input)) {
                if (Integer.parseInt(input) <= 0) {
                    System.err.println(L18n.getGeneralBundle().getString("create.err.value_greater_than_zero"));
                    continue;
                } else {
                    org.setEmployeesCount(Integer.parseInt(input));
                    break;
                }
            }
        }
        input = "";

        // type
        while (true) {
            System.out.printf(L18n.getGeneralBundle().getString("create.organization.orgType") + " >",
                    Arrays.stream(OrganizationType.values())
                            .map(v -> v.toString())
                            .collect(Collectors.joining(" ")));

            input = localScanner.nextLine();
            if (Arrays.stream(OrganizationType.values())
                    .map(v -> v.toString())
                    .toList()
                    .contains(input)) {

                org.setType(OrganizationType.valueOf(input));
                break;

            } else {
                System.err.println(L18n.getGeneralBundle().getString("create.err.incorrect_type"));
            }
        }
        input = "";

        // address
        System.out.println(L18n.getGeneralBundle().getString("create.address.new_element"));
        while (true) {
            System.out.print(L18n.getGeneralBundle().getString("create.address.street") + " >");
            input = localScanner.nextLine();
            if (!input.isEmpty()) {
                org.setOfficialAddress(new Address(input));
                break;
            }
        }
        System.out.println(L18n.getGeneralBundle().getString("create.address.complete"));

        return org;
    }

    // DEV ONLY
    public static Organization genRandom() {
        Organization org = new Organization();
        Random rnd = new Random();

        org.setName("genOrg" + rnd.nextInt(9999));
        org.setCoordinates(new Coordinates(rnd.nextDouble(), rnd.nextFloat()));
        org.setAnnualTurnover(rnd.nextFloat());
        org.setEmployeesCount(rnd.nextInt(999));
        org.setType(OrganizationType.GOVERNMENT);
        org.setOfficialAddress(new Address("far far away"));
        return org;
    }
}
