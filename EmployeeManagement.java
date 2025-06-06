package capproj1;

import java.sql.*;
import java.util.Scanner;

public class EmployeeManager {

    static final String Url = "jdbc:mysql://localhost:3306/employee_db";
    static final String User = "root";
    static final String Password= "Meghaak"; 

    public static void main(String[] args) {
        try //try block start
        (Connection conn = DriverManager.getConnection(Url, User, Password);
             Scanner sc = new Scanner(System.in)) {

            while (true) {
                System.out.println("\n--- Employee Management System ---");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter the choice: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        addEmployee(conn, sc);
                        break;
                    case 2:
                        viewEmployees(conn);
                        break;
                    case 3:
                        updateEmployee(conn, sc);
                        break;
                    case 4:
                        deleteEmployee(conn, sc);
                        break;
                    case 5:
                        System.out.println("Exiting, please wait...");
                        return;
                    default:
                        System.out.println("Invalid choice. kindly Try again.");
                }
            }

        } catch (SQLException e) { //catch block
            e.printStackTrace();
        }
    }

    static void addEmployee(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter ID: "); //enter id int
            int id = sc.nextInt();
            sc.nextLine(); 
            System.out.print("Enter Name: "); //takes name as input
            String name = sc.nextLine();
            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            String query = "INSERT INTO employee VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setDouble(3, salary);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " employee(s) added.");
        } catch (SQLException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }

    static void viewEmployees(Connection conn) {
        try {
            String query = "SELECT * FROM employee";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("\nID\tName\tSalary");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                                   rs.getString("name") + "\t" +
                                   rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            System.out.println("Error viewing employees: " + e.getMessage());
        }
    }

    static void updateEmployee(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter ID to update: ");
            int id = sc.nextInt();
            sc.nextLine(); // consume newline
            System.out.print("Enter New Name: ");
            String name = sc.nextLine();
            System.out.print("Enter New Salary: ");
            double salary = sc.nextDouble();

            String query = "UPDATE employee SET name=?, salary=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setDouble(2, salary);
            stmt.setInt(3, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee updated.");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating employee: " + e.getMessage());
        }
    }

    static void deleteEmployee(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter ID to delete: ");
            int id = sc.nextInt();

            String query = "DELETE FROM employee WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee deleted.");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }
    }
              }
