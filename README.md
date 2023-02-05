# Pass By Value

## Learning Goals

- Demonstrate Parameter Pass By Value

## Code Along

Fork and clone this lesson to run the code.

## Introduction

Programming languages implement different techniques for passing an argument to a parameter
when a method is called:

- Pass by value: The actual argument value is copied to the parameter variable. The method uses the copy.
- Pass by reference: An alias or reference to the actual argument is passed to the method. The method accesses the actual argument, not a copy.

Java uses **pass by value** for both primitive types and reference types.
Even though a variable might hold an object reference (i.e. an address),
the address is **copied** into the parameter variable and thus the method
uses a copy of the value.

NOTE: It is considered poor style to reassign a parameter variable.
The method examples in this lesson reassign the parameter variables
to demonstrate the concept of pass by value.

## Passing a primitive type into a method

We'll start with an example that demonstrates pass by value for primitive data types:

```java
public class Primitives {

   public static void testPrimitives(double a, boolean b, int c) {
      a = 0.45;
      b = true;
      c = 100;
   }

   public static void main(String[] args) {
      double humidity = 0.9;
      boolean isRaining = false;
      int temperature = 58;

      System.out.printf("before testPrimitives: %.2f %b %d %n", humidity, isRaining, temperature);

      testPrimitives(humidity, isRaining, temperature);

      System.out.printf("after testPrimitives: %.2f %b %d %n", humidity, isRaining, temperature);

   }
}
```

The output demonstrates the values of the `main` method variables are not modified by the call to `testPrimitives`:

```text
before testPrimitives: 0.90 false 58 
after testPrimitives: 0.90 false 58 
```

1. Set a breakpoint in the `main` method on the line that calls `testPrimitives`, then launch the debugger.  
   `testPrimitives(humidity, isRaining, temperature);`
2. Press step-into to call the `testPrimitives` method, passing the argument values.
   Notice the call stack adds a frame for the `testPrimitives` method, with the values from the
   arguments (humidity, isRaining, temperature)  copied into the parameters (a, b, c).       
   ![primitives step into](https://curriculum-content.s3.amazonaws.com/6676/java-mod2-strings/primitives_step0.png)
3. Normally we would not reassign values to the parameter, but the method does so to demonstrate that changing
   the parameter value will not affect the variables used as arguments.     
   
   ```java
   public static void testPrimitives(double a, boolean b, int c) {
       a = 0.45;
       b = true;
       c = 100;
   }
   ```  

   Press step-over to execute the three assignment statements in the method.
   The `testPrimitives` parameter variables change value, but not the variables in the `main` method:        
   ![primitives step over](https://curriculum-content.s3.amazonaws.com/6676/java-mod2-strings/primitives_step1.png)
4. Step again to complete the `testPrimitives` method and return to the `main` method.
   We can confirm that pass by value means the values stored in the `main` method variables
   remain the same.    
   ![primitives step out](https://curriculum-content.s3.amazonaws.com/6676/java-mod2-strings/primitives_step2.png)


## Passing a `Person` into a method


Let's see how pass by value works with a reference type such as `Person`.
The static method `testPerson` declares one parameter `Person p`.  We'll
use the debugger to see what happens when `p` is used to access instance variables,
as well as when `p` is assigned to a new object.

```java
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
```

The program prints:

```text
before testPerson: Amir 25 
before testPerson: Amir 30 
```


1. Set a breakpoint in the `main` method on the line that calls `testPerson`, then launch the debugger.  
   `testPerson(employee);`
2. Press step-into to call the `testPerson` method, passing the `employee` variable as the argument.
   Pass by value means the value stored in the argument `employee` (a memory address) will be copied
   into the parameter `p`. As a result, both `employee` and `p` point at the same object.    
   ![person step into](https://curriculum-content.s3.amazonaws.com/6676/java-mod2-strings/person_step0.png)
3. Press step-over to execute each statement in the `testPerson` method:

| Code                | Visualizer                                                                                                                                                                                                                                                                                                                                         |
|---------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `p.age = 30;`       | ![person step1](https://curriculum-content.s3.amazonaws.com/6676/java-mod2-strings/person_step1.png) <br>The age is changed.                                                                                                                                                                                                                       |
| `p = new Person();` | ![person step2](https://curriculum-content.s3.amazonaws.com/6676/java-mod2-strings/person_step2.png) <br>Parameter <code>p</code> points to the new object.<br>Variable <code>employee</code>still points to the original object due to pass by value.<br>If Java used pass by reference, <code>employee</code>would also point to the new object. |
| `p.name = "Jie";`   | ![person step3](https://curriculum-content.s3.amazonaws.com/6676/java-mod2-strings/person_step3.png) <br>The name of the new object is changed.                                                                                                                                                                                                    |
| `p.age = 50;`       | ![person step4](https://curriculum-content.s3.amazonaws.com/6676/java-mod2-strings/person_step4.png) <br>The age of the new object is changed.                                                                                                                                                                                                     |


After returning to the `main` method, we see the `age`  has been updated,
but `employee` still points to the same `Person` object in memory.  If Java
implemented pass by reference, the `employee` variable would point to the
50-year-old person named "Jie".

![person step5](https://curriculum-content.s3.amazonaws.com/6676/java-mod2-strings/person_step5.png) 

## Passing an array into a method



<details>
    <summary>Can you predict what is printed by the <code>main</code> method for the class below?
    Step through with the debugger to see how pass by value works with arrays.</summary>

  <p><br>before testArray:[88, 99, 77, 100]<br>after testArray:[10, 99, 77, 100]</p>

</details>

<br>

```java
import java.util.Arrays;

public class IntArray {

    public static void testArray(int[] nums) {
        nums[0] = 10;
        nums = new int[3];
        nums[0] = 20;
    }

    public static void main(String[] args) {
        int[] grades = {88,99,77,100};

        System.out.println("before testArray:" + Arrays.toString(grades) );

        testArray(grades);

        System.out.println("after testArray:" + Arrays.toString(grades) );
    }
}

```

## Conclusion

Pass by value means the argument value is copied into the parameter variable.
If the parameter is a reference type, the parameter points at the same object as the argument
and thus is able to change the object's state.  Parameter reassignment is discouraged and usually not useful
since it won't affect the value of the argument.

## Resources

- [Java Pass By Value](https://www.baeldung.com/java-pass-by-value-or-pass-by-reference)
