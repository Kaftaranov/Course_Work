public class Employee {
    private final String name;
    private final String middlename;
    private final String surname;
    private int division;
    private  Double salary;
    private final int id;
    private static int idNo = 1;

    public Employee(String name, String middlename, String surname, int division, Double salary)
    {this.name = name;
        this.middlename = middlename;
        this.surname = surname;
        this.division = division;
        this.salary = salary;
        id = idNo++;
    }
    public  String getName() {return name;}
    public  String getMiddlename() {return middlename;}
    public  String getSurname() {return surname;}
    public  int getDivision() {return division;}
    public  Double getSalary() {return salary;}
    public int getId(){return id;}
    public void setDivision (int division) {this.division = division;}
    public void setSalary (Double salary) {this.salary = salary;}
}