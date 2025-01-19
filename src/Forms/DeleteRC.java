package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;

public class DeleteRC extends JFrame implements KeyListener, ActionListener {
    private JTextField txtVno;
    private JLabel lblmess;
    private JTable tb;
    private JButton btndelete, btnHome;

    public DeleteRC() {
        setTitle("Delete Records");
        setSize(1250, 800);
        setLayout(null);

        JLabel lblVno = new JLabel("Enter Vehicle no: ");
        lblVno.setBounds(40, 50, 200, 30);
        lblVno.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(lblVno);

        txtVno = new JTextField();
        txtVno.setBounds(250, 50, 130, 25);
        txtVno.setFont(new Font("Times New Roman", Font.BOLD, 20));
        txtVno.addKeyListener(this); // Add KeyListener to listen for text changes
        add(txtVno);

        lblmess = new JLabel("");
        lblmess.setBounds(390, 50, 190, 30);
        lblmess.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblmess.setForeground(Color.green);
        lblmess.setVisible(true);
        add(lblmess);

        btndelete=new JButton("Delete");
        btndelete.setBounds(540, 50, 140, 30);
        btndelete.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btndelete.addActionListener(this);
        add(btndelete);

        btnHome=new JButton("Home");
        btnHome.setBounds(730, 50, 140, 30);
        btnHome.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnHome.addActionListener(this);
        add(btnHome);



        setVisible(true);
    }

    private void callData(String vn) {
        Object[][] data = fetchDataFromDatabase(vn);
        if (data != null) {
            String[] columnNames = {"Vehicle no", "Owner first name", "Owner last name", "Vehicle type", "Mobile No", "Duration", "Amount", "Entry time", "Exit time"};
            tb = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(tb);
            scrollPane.setBounds(40, 100, 1100, 600);
            add(scrollPane);
            revalidate(); // Refresh the frame to display the new table
        } else {
            // Handle no data found
        }
    }

//    private Object[][] fetchDataFromDatabase(String vn) {
//        // Database connectivity variables
//        String url = "jdbc:mysql://localhost:3308/vps";
//        String username = "root";
//        String password = "dbms";
//
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = DriverManager.getConnection(url, username, password);
//            statement = connection.createStatement();
//
//            // Use PreparedStatement to prevent SQL Injection
//            String query = "SELECT * FROM vehicle WHERE Vehicleno LIKE  '?%'";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, vn);
//
//            resultSet = preparedStatement.executeQuery();
//
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            int columnCount = metaData.getColumnCount();
//
//            java.util.List<Object[]> rows = new java.util.ArrayList<>();
//
//            while (resultSet.next()) {
//                Object[] rowData = new Object[columnCount];
//                for (int i = 1; i <= columnCount; i++) {
//                    rowData[i - 1] = resultSet.getObject(i);
//                }
//                rows.add(rowData);
//            }
//
//            Object[][] data = new Object[rows.size()][];
//            for (int i = 0; i < rows.size(); i++) {
//                data[i] = rows.get(i);
//            }
//
//            return data;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (resultSet != null) resultSet.close();
//                if (statement != null) statement.close();
//                if (connection != null) connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }


    private Object[][] fetchDataFromDatabase(String vn) {
        // Database connectivity variables
        String url = "jdbc:mysql://localhost:3308/vps";
        String username = "root";
        String password = "dbms";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(url, username, password);

            // Use PreparedStatement to prevent SQL Injection
            String query = "SELECT * FROM vehicle WHERE Vehicleno LIKE ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, vn + "%"); // Append wildcard to the parameter value

            resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            java.util.List<Object[]> rows = new java.util.ArrayList<>();

            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = resultSet.getObject(i);
                }
                rows.add(rowData);
            }

            Object[][] data = new Object[rows.size()][];
            for (int i = 0; i < rows.size(); i++) {
                data[i] = rows.get(i);
            }

            return data;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (txtVno.getText().equals("")) {
            lblmess.setText("Enter Vehicle number");
        } else {
            lblmess.setText("");
            String vn = txtVno.getText().trim();
            callData(vn);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ev)
    {
        if (ev.getSource()==btndelete)
        {
            String strvn=txtVno.getText();
            if(strvn.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Enter Vehicle number");
            }
            else
            {
                deleteFun(strvn);
            }
        }
        if(ev.getSource()==btnHome)
        {
//            setDefaultCloseOperation();
            dispose();
            new Home();
        }

    }
    void deleteFun(String strvn)
    {
        String url = "jdbc:mysql://localhost:3308/vps";
        String username = "root";
        String password = "dbms";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            try {
                connection = DriverManager.getConnection(url, username, password);

                // Use PreparedStatement to prevent SQL Injection
                String query = "DELETE FROM vehicle WHERE Vehicleno = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, strvn);

                int rowsDeleted = preparedStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Record with Vehicle Number " + strvn + " deleted successfully.");
//                String strmsg=""
                    JOptionPane.showMessageDialog(null, "Record deleted successfully with " + strvn, "Information", JOptionPane.INFORMATION_MESSAGE);
                    txtVno.setText("");
                    callData("");
                } else {
//                JOptionPane.showMessageDialog(null, "No record found with Vehicle Number"+strvn);
                    JOptionPane.showMessageDialog(this, "No record found with Vehicle Number " + strvn, "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (preparedStatement != null) preparedStatement.close();
                    if (connection != null) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            System.out.println("Deletion canceled by user.");
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DeleteRC());
    }


}
