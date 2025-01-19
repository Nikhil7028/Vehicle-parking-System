package Forms;




    import java.sql.*;

public class TestDb {

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Nikhil", "root", "dbms");//Establishing connection
            System.out.println("Connected With the database successfully");
        } catch (SQLException e) {
            System.out.println("Error while connecting to the database"+e);
        }
    }

}
