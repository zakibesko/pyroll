import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PayrollManager manager =
                new PayrollManager();

        // Start Auditor Thread
        AuditorThread auditor =
                new AuditorThread("payroll_data");

        auditor.setDaemon(true);

        auditor.start();

        while (true) {

            System.out.println("\n===== PAYROLL VAULT =====");

            System.out.println("1. Enroll Employee");
            System.out.println("2. Generate Paystub");
            System.out.println("3. Exit");

            System.out.print("Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Employee ID: ");
                    int id = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Salary: ");
                    double salary = sc.nextDouble();

                    sc.nextLine();

                    System.out.print("Department: ");
                    String dept = sc.nextLine();

                    Employee emp =
                            new Employee(
                                    id,
                                    name,
                                    salary,
                                    dept
                            );

                    manager.saveEmployee(emp);

                    break;

                case 2:

                    System.out.print(
                            "Enter Employee ID: "
                    );

                    int searchID = sc.nextInt();

                    Employee loaded =
                            manager.loadEmployee(searchID);

                    if (loaded != null) {

                        System.out.println(
                                "\n=== PAYSTUB ==="
                        );

                        System.out.println(loaded);
                    }

                    break;

                case 3:

                    System.out.println("Exiting...");
                    System.exit(0);

                default:

                    System.out.println("Invalid choice.");
            }
        }
    }
}