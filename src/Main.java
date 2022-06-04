import java.util.Random;

public class Main {
    private static final String[] setOfNames = new String[]{"Semyon Semyonovich Gorbunkov", "Fyodor Mikhailovich Sukhov",
            "Pavel Artemyevich Vereshchagin", "Max Otto von Schtirlitz", "Afanasiy Nikolaevich Borshchyov",
            "Yevgeniy Ivanovich Troshkin", "Anton Semyonovich Shpak", "Ivan Vasilyevich Bunsha",
            "Gleb Yegorovich Zheglov", "Pyotr Ruchnikov", "Yevgeniy Petrovich Foks"};
    private static final Random salary = new Random();
    private static final Double[] salaries = new Double[setOfNames.length];
    private static String processedName;
    private static String processedMiddlename = null;
    private static String processedSurname = null;
    private static final int[] division = new int[]{1, 2, 3, 4, 5};
    public static Employee[] staff = new Employee[setOfNames.length];

    private static void nameProcessing(String nameToProcess) {
        processedName = nameToProcess.split(" ")[0];
        switch (nameToProcess.split(" ").length) {
            case 2 -> {
                processedMiddlename = "";
                processedSurname = nameToProcess.split(" ")[1];
            }
            case 3 -> {
                processedMiddlename = nameToProcess.split(" ")[1];
                processedSurname = nameToProcess.split(" ")[2];
            }
            case 4 -> {
                processedMiddlename = nameToProcess.split(" ")[1];
                processedSurname = nameToProcess.split(" ")[2] + " " + nameToProcess.split(" ")[3];
            }
        }
    }

    private static void staffListInitializing() {
        int divisionName = 0;
        for (int i = 0; i < setOfNames.length; i++) {
            nameProcessing(setOfNames[i]);
            if (divisionName == division.length) {
                divisionName = 0;
            }
            staff[i] = new Employee(processedName, processedMiddlename, processedSurname,
                    division[divisionName], salaries[i]);
            divisionName++;
        }
    }

    private static void salariesInitializing() {
        for (int i = 0; i < salaries.length; i++) {
            salaries[i] = salary.nextDouble(200_000D, 300_000D);
        }
    }

    private static Employee maxSalary(int division) {
        Double max = 0D;
        Employee person = null;
        for (Employee employee : staff) {
            if (division == 0) {
                if (employee.getSalary() > max) {
                    max = employee.getSalary();
                    person = employee;
                }
            } else if ((division == employee.getDivision()) && (employee.getSalary() > max)) {
                max = employee.getSalary();
                person = employee;
            }
        }
        return person;
    }

    private static Employee minSalary(int division) {
        Double min = staff[0].getSalary();
        Employee person = staff[0];
        for (Employee employee : staff) {
            if (division == 0) {
                if (employee.getSalary() < min) {
                    min = employee.getSalary();
                    person = employee;
                }
            } else if ((division == employee.getDivision()) && (employee.getSalary() < min)) {
                min = employee.getSalary();
                person = employee;
            }
        }
        return person;
    }

    private static Double averageSalary(int division) {
        Double average = 0D;
        int staffNo = 0;
        for (Employee employee : staff) {
            if (division == 0) {
                average += employee.getSalary();
                staffNo++;
            } else if (employee.getDivision() == division) {
                average += employee.getSalary();
                staffNo++;
            }
        }
        return average / staffNo;
    }

    private static Double totalSalary(int division) {
        Double sum = 0D;
        for (Employee employee : staff) {
            if (division == 0) {
                sum += employee.getSalary();
            } else if (division == employee.getDivision()) {
                sum += employee.getSalary();
            }
        }
        return sum;
    }

    private static void salaryChange(int division, Double percentage) {
        System.out.print("Salary of " + division + " staff ");
        if (percentage > 0) {
            System.out.print("was increased ");
        } else {
            System.out.print("was decreased ");
        }
        System.out.println("on " + percentage + "%.");
        for (Employee employee : staff) {
            if (division == 0) {
                employee.setSalary(employee.getSalary() * (1 + percentage / 100));
            } else if (division == employee.getDivision()) {
                employee.setSalary(employee.getSalary() * (1 + percentage / 100));
            }
        }
    }

    private static void printFooter(int size) {
        for (int i = 0; i < size; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    private static void printFullDataOfStaff() {
        String headingForName = "Surname, Name, Middle name        |";
        String headingForSalary = "        Salary       |";
        String headingForDivision = "   Division    |";
        String headingForId = " ID number |";

        printFooter(headingForName.length() + headingForSalary.length() + headingForDivision.length() + headingForId.length());
        System.out.println(headingForName + headingForSalary + headingForDivision + headingForId);
        printFooter(headingForName.length() + headingForSalary.length() + headingForDivision.length() + headingForId.length());
        for (Employee employee : staff) {
            System.out.print(employee.getSurname() + " " + employee.getName() + " " + employee.getMiddlename());

            for (int i = 0; i <= (headingForName.length() - 1 - employee.getName().length() - 1 - employee.getMiddlename().length() - 1 -
                    employee.getSurname().length() - 1); i++) {
                System.out.print(" ");
            }
            System.out.println("|" + "\t" + employee.getSalary() + "\t" + "|" + "\t\t" + employee.getDivision() + "\t\t"
                    + "|" + "\t" + employee.getId() + "\t\t" + "|");
        }
        printFooter(headingForName.length() + headingForSalary.length() + headingForDivision.length() + headingForId.length());
    }

    private static void printNamesOfStaff(int division) {
        String headingForName = "Surname, Name, Middle name        |";
        String headingForSalary = "        Salary       |";
        String headingForId = " ID number |";
        int headingLength = headingForName.length() + headingForSalary.length() + headingForId.length();

        printFooter(headingForName.length());
        if (division == 0) {
            System.out.println("List of full names of whole staff");
            printFooter(headingForName.length());
            for (Employee employee : staff) {
                System.out.println(employee.getSurname() + " " + employee.getName() + " " +
                        employee.getMiddlename());
            }
        } else {
            System.out.println("List of division " + division + " staff");
            printFooter(headingLength);
            System.out.print(headingForName);
            System.out.print(headingForSalary);
            System.out.println(headingForId);
            printFooter(headingLength);
            for (Employee employee : staff) {
                if (employee.getDivision() == division) {
                    System.out.print(employee.getSurname() + " " + employee.getName() + " " + employee.getMiddlename());
                    for (int i = 0; i <= (headingForName.length() - 1 - employee.getName().length() - 1 -
                            employee.getMiddlename().length() - 1 - employee.getSurname().length() - 1); i++) {
                        System.out.print(" ");
                    }
                    System.out.println("|" + "\t" + employee.getSalary() + "\t" + "|"
                            + "\t" + employee.getId() + "\t\t" + "|");
                }
            }
            printFooter(headingLength);
        }
    }

    private static void printMaxSalary(int division) {
        if (division == 0) {
            System.out.println("Maximum salary of all " + maxSalary(0).getSalary() + " has " +
                    maxSalary(0).getSurname() + " " + maxSalary(0).getName() + " " +
                    maxSalary(0).getMiddlename() + " from division " + maxSalary(0).getDivision());
        } else {
            System.out.println("Maximum salary " + maxSalary(division).getSalary() +
                    " in division " + maxSalary(division).getDivision() + " has " +
                    maxSalary(division).getSurname() + " " + maxSalary(division).getName() + " " +
                    maxSalary(division).getMiddlename() + ".");
        }
    }

    private static void printMinSalary(int division) {
        if (division == 0) {
            System.out.println("Minimum salary of all " + minSalary(0).getSalary() + " has " +
                    minSalary(0).getSurname() + " " + minSalary(0).getName() + " " +
                    minSalary(0).getMiddlename() + " from division " + minSalary(0).getDivision() + ".");
        } else {
            System.out.println("Minimum salary " + minSalary(division).getSalary() +
                    " in division " + minSalary(division).getDivision() + " has " +
                    minSalary(division).getSurname() + " " + minSalary(division).getName() + " " +
                    minSalary(division).getMiddlename() + ".");
        }
    }

    private static void printAverageSalary(int division) {
        if (division == 0) {
            System.out.println("Average salary of staff is " + averageSalary(0) + ".");
        } else {
            System.out.println("Average salary in division " + division + " is " +
                    averageSalary(division));
        }
    }

    private static void printTotalSalary(int division) {
        if (division == 0) {
            System.out.println("Total expenses for staff salary is " + totalSalary(0).toString() + ".");
        } else {
            System.out.println("Total expenses for salary of " + division + " division are " +
                    totalSalary(division).toString() + ".");
        }
    }

    private static void printSalariesFor(int division) {
        printTotalSalary(division);
        printMinSalary(division);
        printMaxSalary(division);
        printAverageSalary(division);
    }

    private static void arrangeSalary(Double referenceSalary) {
        String headingForName = "Surname, Name, Middle name        |";
        String headingForSalary = "        Salary       |";
        String headingForId = " ID number |";
        int headingLength = headingForName.length() + headingForSalary.length() + headingForId.length();

        System.out.println("List of staff with salaries segregated by " + referenceSalary);
        printFooter(headingLength);
        System.out.print(headingForName);
        System.out.print(headingForSalary);
        System.out.println(headingForId);
        printFooter(headingLength);
        for (Employee employee : staff) {
            if (employee.getSalary() < referenceSalary) {
                formatEmployeeName(headingForName, employee);
            }
        }
        System.out.println("\t\t\t" + "Reference salary " + referenceSalary);
        for (Employee employee : staff) {
            if (employee.getSalary() >= referenceSalary) {
                formatEmployeeName(headingForName, employee);
            }
        }
        printFooter(headingLength);
    }

    private static void formatEmployeeName(String headingForName, Employee employee) {
        System.out.print(employee.getSurname() + " " + employee.getName() + " " + employee.getMiddlename());
        for (int i = 0; i <= (headingForName.length() - 1 - employee.getName().length() - 1 - employee.getMiddlename().length() - 1 -
                employee.getSurname().length() - 1); i++) {
            System.out.print(" ");
        }
        System.out.println("|" + "\t" + employee.getSalary() + "\t" + "|"
                + "\t" + employee.getId() + "\t\t" + "|");
    }

    public static void main(String[] args) {
        salariesInitializing();
        staffListInitializing();
        printFullDataOfStaff();
        printSalariesFor(0);
        System.out.println("=====================================================================================");
        printNamesOfStaff(0);

        printNamesOfStaff(1);

        printSalariesFor(1);
        System.out.println("=====================================================================================");
        salaryChange(0, 10D);
        printFullDataOfStaff();
        printSalariesFor(0);
        System.out.println("=====================================================================================");
        salaryChange(2, -25D);
        printNamesOfStaff(2);

        printSalariesFor(2);
        System.out.println("=====================================================================================");
        arrangeSalary(250000D);
    }
}