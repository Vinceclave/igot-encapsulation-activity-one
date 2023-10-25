package Encapsulation;
import java.util.Scanner;
import java.time.LocalDate;
import java.lang.NullPointerException;
import java.util.InputMismatchException;

public class Person {
    public static Scanner sc = new Scanner(System.in);
    static LocalDate date = LocalDate.now();
    private static String firstName, middleName, lastName;
    private static  int age;
    private static String month; // this serves as the attributes for Birth Date
    private  int  day, year; // this serves as the attributes for Birth Date
    private String address;

    public Person() {};
    public Person(String firstName, String middleName, String lastName, int age, String address) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
    }

    public  boolean isLeapYear() {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidMonth(String month) {
        String[] validMonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for (String validMonth : validMonths) {
            if (validMonth.equals(month)) {
                return true;
            }
        }
        return false;
    }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setLastName() { this.lastName = lastName; }

    public void setDay(int day) {
        if (isValidMonth(month)) {
            int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

            if (isLeapYear() && month.equalsIgnoreCase("Feb")) {
                if (day >= 1 && day <= 29) {
                    this.day = day;
                } else {
                    this.day = -1; // Invalid day for February during a leap year.
                }
            } else if (day >= 1 && day <= 30) {
                this.day = day;
            }else if (day >= 1 && day <= 31) {
                this.day = day;
            } else {
                this.day = -1; // Invalid day for other months.
            }
        } else {
            this.day = -1; // Invalid month.
        }
    }


    public void setAge(int age) { this.age = age; }

    public void setMonth(int month) {
        String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        if (month >= 1 && month <= 12)
            this.month = months[month - 1]; // Set the 'month' field based on the input 'month'
        else
            System.out.println("Invalid month"); // Provide feedback for an invalid month value
    }

    public void setYear(int year) {
        if (year > 0 && year <= date.getYear())
            this.year = year;
    }

    public  void setBirthDate(int month, int day, int year) {
        setMonth(month);
        setDay(day);
        setYear(year);
    }

    public  String getBirthDate() {
        String month = getMonth();
        int day = getDay();
        int year = getYear();

        if (day != -1 && !month.isEmpty() && year != 0) {
            return month + " " + day + ", " + year;
        } else {
            return "Invalid birthdate";
        }
    }

    public static int getAge() { return age; }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getAddress() {
        return address;
    }

    public static String getMonth() { return month; }
    public  int getYear() { return year; }
    public  int getDay() { return day; }

    public static String getFirstName() { return firstName; }
    public static String getMiddleName() { return middleName; }
    public static  String getLastName() { return lastName; }


    public  void viewDetails() throws InterruptedException {
        System.out.println("Personal Information");
        System.out.println("First Name: " + getFirstName());
        Thread.sleep(1000);
        System.out.println("Middle Name: " + getMiddleName());
        Thread.sleep(1000);
        System.out.println("Last Name: " + getLastName());
        Thread.sleep(1000);
        Thread.sleep(1000);
        System.out.println("Age: " + getAge());
        System.out.println("Address: " + getAddress());
        Thread.sleep(1000);
        
        try {
            System.out.println("Birth Date: " + getBirthDate());
        } catch (NullPointerException e) {
            System.out.println("Birth Date: No info.");
        }
    }

    public static void main(String[] args)  throws InterruptedException{
        String first, middle, last, address;
        int age;
        int choice;
        Person person = null;
        boolean isValid = false;

        do {
            while (person == null) {
                System.out.print("Enter First Name: ");
                first = sc.nextLine();
                System.out.print("Enter Middle Name: ");
                middle = sc.nextLine();
                System.out.print("Enter Last Name: ");
                last = sc.nextLine();
                System.out.print("Enter Age: ");
                age = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Address: ");
                address = sc.nextLine();

                if (first.isEmpty() || middle.isEmpty() || last.isEmpty() || address.isEmpty()) {
                    System.out.println("All fields must be filled. Please try again.");
                } else {
                    person = new Person(first, middle, last, age, address);
                }

                while (true) {
                    try {
                        System.out.println("1. Edit Name");
                        System.out.println("2. Birth Date");
                        System.out.println("3. Edit Address");
                        System.out.println("4. Display");
                        System.out.println("0. Exit");

                        System.out.println("Enter your choice");
                        choice = sc.nextInt();
                        sc.nextLine();

                        if (choice == 0) {
                            isValid = true;
                            break;  // Exit the loop when the user selects 0.
                        } else if (choice >= 1 && choice <= 4) {
                            switch (choice) {
                                case 1:
                                    System.out.print("Enter New First Name: ");
                                    String newFirstName = sc.nextLine();
                                    System.out.print("Enter New Middle Name: ");
                                    String newMiddleName = sc.nextLine();
                                    System.out.print("Enter New Last Name: ");
                                    String newLastName = sc.nextLine();

                                    // Update the person's name if the new values are not empty
                                    if (!newFirstName.isEmpty() || !newMiddleName.isEmpty() || !newLastName.isEmpty()) {
                                        person.setFirstName(newFirstName);
                                        person.setMiddleName(newMiddleName);
                                        person.setLastName(newLastName);
                                        System.out.println("Name updated successfully.");
                                    } else {
                                        System.out.println("Name not updated. All fields must be filled.");
                                    }
                                    break;
                                case 2:
                                    int day, month, year;
                                    boolean isSomeUpload;
                                    while (true) {
                                        try {
                                            System.out.print("Enter month: ");
                                            month = sc.nextInt();
                                            
                                            System.out.print("Enter day: ");
                                            day = sc.nextInt();
                                            
                                            System.out.print("Enter year: ");
                                            year = sc.nextInt();

                                            if (month != -1 && day != -1 && year != -1) { // Check if all values are valid.
                                                person.setBirthDate(month, day, year);
                                                System.out.println(person.getBirthDate());
                                                break; // Exit the loop when a valid date is provided.
                                            } else {
                                                System.out.println("Invalid date. Please enter a valid date.");
                                            }
                                        } catch (InputMismatchException e) {
                                            System.out.println("Error: Enter a numeric value");
                                            sc.next(); // Consume the invalid input to prevent an infinite loop.
                                        }
                                    }
                                    break;
                                case 3:
                                    System.out.print("Enter New Address: ");
                                    String newAddress = sc.nextLine();
                                    person.setAddress(newAddress);

                                    break;
                                case 4:
                                    person.viewDetails();
                                    break;
                            }
                        } else {
                            System.out.println("Invalid choice: Pick from (0 - 3)");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Enter a numeric value");
                        sc.next(); // Consume the invalid input to prevent an infinite loop.
                    }
                }

            }
        } while (!isValid);
    }
}
