package cc.atm.bankinformationsystem.database;

import cc.atm.CustomerAccount;
import cc.atm.Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgreSQLDriver implements IDatabaseRepository {

    private Connection connect() {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/atm",
                    "postgres", "LecturePassword");

            System.out.println("Connected to the database!");

        } catch (Exception e) {
            System.out.println("Connection attempt failed!");
            e.printStackTrace();
        }
        return conn;
    }

    public CustomerAccount verifyUser(int accountNumber, int password) {
        CustomerAccount customerAccount = null;

        System.out.println("Bank information system is verifying the user...");
        Utilities.waiting(2000);
        System.out.println("Connected to the database (PostgreSQL database management system) and querying the user...");
        Utilities.waiting(2000);

        Connection conn = this.connect();

        try {
            String sql = "SELECT * FROM customeraccount WHERE accountnumber=" + accountNumber + " AND password=" + password;

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            //***** Close the connection *****
            conn.close();

            int accNo;
            double balance;
            String firstName;
            String lastName;

            while (rs.next()) {
                accNo = rs.getInt("accountnumber");
                balance = rs.getDouble("balance");
                firstName = rs.getString("name");
                lastName = rs.getString("surname");

                customerAccount = new CustomerAccount(accNo, balance, firstName, lastName);
                System.out.println(customerAccount);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customerAccount;
    }

    public void updateAccount(CustomerAccount customerAccount) {

        Connection conn = this.connect();

        try {
            String sql = "UPDATE \"KullaniciHesabi\" SET bakiye=" + customerAccount.getBalance() + " WHERE \"hesapNumarasi\"=" + customerAccount.getAccountNumber();

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            conn.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Bank information system updated the account...");
    }
}
