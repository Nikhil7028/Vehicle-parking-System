//package Forms;
//
////
//
//
//
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.*;
//
//class Display extends JFrame implements ActionListener {
//    private JTable table;
//    JButton backButton;
//    private JScrollPane scrollPane;
//
//    public Display() {
//        setTitle("Database Records");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(800, 800);
//        setLocationRelativeTo(null);
//
//        // Fetch data from the database
//        Object[][] data = fetchDataFromDatabase();
//
//        // Column names
//        String[] columnNames = {"Vehicle no", "Owner first name", "Owner last name","Vehicle type","Mobile No" /* Add more columns as needed */};
//
//        table = new JTable(data, columnNames);
//        scrollPane = new JScrollPane(table);
//        getContentPane().add(scrollPane, BorderLayout.CENTER);
//
//        backButton = new JButton("Back");
//
//    }
////    public void
//
//    private Object[][] fetchDataFromDatabase() {
//        // Database connectivity variables
//        String url = "jdbc:mysql://localhost:3308/vps";
//        String username = "root";
//        String password = "dbms";
//        String tableName = "vehicle";
//
//        // Variables for database connection and result set
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            // Establish the database connection
//            connection = DriverManager.getConnection(url, username, password);
//
//            // Create a SQL statement
//            statement = connection.createStatement();
//
//            // Query to select all records from the table
//            String query = "SELECT * FROM " + tableName;
//
//            // Execute the query and get the result set
//            resultSet = statement.executeQuery(query);
//
//            // Get the ResultSetMetaData to know the number of columns
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            int columnCount = metaData.getColumnCount();
//
//            // List to store the fetched data
//            java.util.List<Object[]> rows = new java.util.ArrayList<>();
//
//            // Iterate through the result set
//            while (resultSet.next()) {
//                // Array to store data for each row
//                Object[] rowData = new Object[columnCount];
//                for (int i = 1; i <= columnCount; i++) {
//                    // Fetch data by column index
//                    rowData[i - 1] = resultSet.getObject(i);
//                }
//                // Add the row data to the list
//                rows.add(rowData);
//            }
//
//            // Convert the list to a 2D array
//            Object[][] data = new Object[rows.size()][];
//            for (int i = 0; i < rows.size(); i++) {
//                data[i] = rows.get(i);
//            }
//
//            return data;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Handle exceptions properly in your application
//        } finally {
//            // Close resources in a finally block to ensure they are closed even if an exception occurs
//            try {
//                if (resultSet != null) resultSet.close();
//                if (statement != null) statement.close();
//                if (connection != null) connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        // Return null if an error occurred
//        return null;
//    }
//
//    public static void main(String[] args) {
//
//        SwingUtilities.invokeLater(() -> {
//            Display recordsDisplay = new Display();
//            recordsDisplay.setVisible(true);
//        });
//
//    }
//    void call()   //this method use to call it invoke from Home page
//    {
//        SwingUtilities.invokeLater(() -> {
//            Display recordsDisplay = new Display();
//            recordsDisplay.setVisible(true);
//        });
//
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent event) {
//        if(event.getSource()== backButton )
//        {
//            dispose();
//            new Home();
//        }
//
//    }
//}

package Forms;

//import Test.HomeDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class Display extends JFrame
{
    private JTable table;
    private JScrollPane scrollPane;

    public Display() {
        setTitle("Database Records");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 800);
        setLocationRelativeTo(null);

        // Fetch data from the database
        Object[][] data = fetchDataFromDatabase();

        // Column names
        String[] columnNames = {"Vehicle no", "Owner first name", "Owner last name","Vehicle type","Mobile No","Duration","Amount","Entry time","Exit time" /* Add more columns as needed */};

        table = new JTable(data, columnNames);
        scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current window and go back
                dispose(); // Close the current window
                new Home();
                // For example, opening another window: new PreviousWindow().setVisible(true);
            }
        });
        getContentPane().add(backButton, BorderLayout.SOUTH);
    }

    private Object[][] fetchDataFromDatabase()
    {
        // Database connectivity variables
        String url = "jdbc:mysql://localhost:3308/vps";
        String username = "root";
        String password = "dbms";
        String tableName = "vehicle";

        // Variables for database connection and result set
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish the database connection
            connection = DriverManager.getConnection(url, username, password);

            // Create a SQL statement
            statement = connection.createStatement();

            // Query to select all records from the table
            String query = "SELECT * FROM " + tableName;

            // Execute the query and get the result set
            resultSet = statement.executeQuery(query);

            // Get the ResultSetMetaData to know the number of columns
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // List to store the fetched data
            java.util.List<Object[]> rows = new java.util.ArrayList<>();

            // Iterate through the result set
            while (resultSet.next()) {
                // Array to store data for each row
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    // Fetch data by column index
                    rowData[i - 1] = resultSet.getObject(i);
                }
                // Add the row data to the list
                rows.add(rowData);
            }

            // Convert the list to a 2D array
            Object[][] data = new Object[rows.size()][];
            for (int i = 0; i < rows.size(); i++) {
                data[i] = rows.get(i);
            }

            return data;

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions properly in your application
        } finally {
            // Close resources in a finally block to ensure they are closed even if an exception occurs
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            Test.RecordsDisplay recordsDisplay = new Test.RecordsDisplay();
//            recordsDisplay.setVisible(true);
//        });
        public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Display recordsDisplay = new Display();
            recordsDisplay.setVisible(true);
        });

    }
        void call()   //this method use to call it invoke from Home page
    {
        SwingUtilities.invokeLater(() -> {
            Display recordsDisplay = new Display();
            recordsDisplay.setVisible(true);
        });

    }

}
