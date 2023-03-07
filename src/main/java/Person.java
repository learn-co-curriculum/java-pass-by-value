public class Person {

    private String name;
    private int age;

    public static void testPerson(Person p) {
        p.age = 30;
        p = new Person();
        p.name = "Jie";
        p.age = 50;
    }

    public static void main(String[] args) {
        Person employee = new Person();
        employee.name = "Amir";
        employee.age = 25;

        System.out.printf("before testPerson: %s %d %n", employee.name, employee.age );

        testPerson(employee);

        System.out.printf("before testPerson: %s %d %n", employee.name, employee.age );
    }

}
