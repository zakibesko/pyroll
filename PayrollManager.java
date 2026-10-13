import java.io.*;
import java.time.LocalDateTime;

public class PayrollManager {

    private final String folder = "payroll_data/";

    public PayrollManager() {

        File dir = new File(folder);

        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    // SAVE EMPLOYEE
    public void saveEmployee(Employee emp) {

        String filename =
                folder + emp.getEmployeeID() + ".bin";

        try (

                FileOutputStream fos =
                        new FileOutputStream(filename);

                BufferedOutputStream bos =
                        new BufferedOutputStream(fos);

                ObfuscatedOutputStream obs =
                        new ObfuscatedOutputStream(bos);

                ObjectOutputStream oos =
                        new ObjectOutputStream(obs)

        ) {

            oos.writeObject(emp);

            System.out.println(
                    "Employee saved securely."
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // LOAD EMPLOYEE
    public Employee loadEmployee(int id) {

        String filename = folder + id + ".bin";

        try (

                FileInputStream fis =
                        new FileInputStream(filename);

                BufferedInputStream bis =
                        new BufferedInputStream(fis);

                ObfuscatedInputStream oisFilter =
                        new ObfuscatedInputStream(bis);

                ObjectInputStream ois =
                        new ObjectInputStream(oisFilter)

        ) {

            Employee emp =
                    (Employee) ois.readObject();

            logSalaryAccess(id);

            return emp;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    // AUDIT LOGGING
    private void logSalaryAccess(int id) {

        try (

                PrintWriter writer =
                        new PrintWriter(
                                new BufferedWriter(
                                        new FileWriter(
                                                "audit.log",
                                                true
                                        )
                                )
                        )

        ) {

            writer.println(
                    "Access Grant: Salary for EmpID "
                            + id
                            + " viewed at "
                            + LocalDateTime.now()
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}