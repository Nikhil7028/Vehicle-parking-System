//package Forms;

//public class UpdateCapacity {
//}
package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;

public class UpdateCapacity extends JFrame implements ItemListener, ActionListener
{
    static final String JDBC_URL = "jdbc:mysql://localhost:3308/vps";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "dbms";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    private JFrame jfP=null;
    private JButton btnUpdate, btnbk, btnInsert;
    private JLabel lmess2,lmess4, lmessH;
    private JComboBox v_type;
    private JTextField txtcapacity, txtW4, txtWH;
    private String vehiclety;  //for update
    private int capacity;      //for update
    public UpdateCapacity()
    {
        jfP=new JFrame("Manage capacity of vehicle");
        jfP.setSize(510,500);
        jfP.setLayout(null);

        JLabel lbltitle=new JLabel("Manage Total capacity of vehicle \uD83C\uDFCD\uFE0F \uD83D\uDE98 \uD83D\uDE9A");
        lbltitle.setFont(new Font("Time New Roman",Font.BOLD,20));
        lbltitle.setBounds(95,15,400,40);
        lbltitle.setForeground(Color.red);
        jfP.add(lbltitle);


        JLabel lblW2=new JLabel("Vehicle type: ");
        lblW2.setFont(new Font("Time New Roman",Font.BOLD,20));
        lblW2.setBounds(50,60,150,30);
        jfP.add(lblW2);

        String[] s1={"---Select---","Two wheeler","Four wheeler","Heavy Vehicle"};
        v_type=new JComboBox<>(s1);
        v_type.setBounds(215,60,160,30);
        v_type.addItemListener(this);
        jfP.add(v_type);

        JLabel lblW4=new JLabel("New Capacity for vehicle :");
        lblW4.setFont(new Font("Time New Roman",Font.BOLD,20));
        lblW4.setBounds(50,105,250,30);
        jfP.add(lblW4);

        txtcapacity=new JTextField();
        txtcapacity.setFont(new Font("Time New Roman",Font.BOLD,20));
        txtcapacity.setBounds(299,105,70,30);
        jfP.add(txtcapacity);



        btnUpdate=new JButton("Update");
        btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnUpdate.setBounds(200, 180, 150, 30);
        btnUpdate.setBackground(Color.GREEN);
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.addActionListener(this);
        jfP.add(btnUpdate);

        btnbk=new JButton("Home");
        btnbk.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnbk.setBounds(210, 240, 140, 30);
        btnbk.setBackground(Color.BLACK);
        btnbk.setForeground(Color.WHITE);
        btnbk.addActionListener(this);
        jfP.add(btnbk);

        btnInsert=new JButton("Insert record");
        btnInsert.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnInsert.setBounds(50, 240, 140, 30);
        btnInsert.addActionListener(this);
        jfP.add(btnInsert);

        lmess2=new JLabel("");          //Two vehicle
        lmess2.setFont(new Font("Time New Roman",Font.ITALIC,15));
        lmess2.setBounds(50,285,320,20);
        lmess2.setForeground(Color.blue);
        jfP.add(lmess2);

        lmess4=new JLabel("");          //Four vehicle
        lmess4.setFont(new Font("Time New Roman",Font.ITALIC,15));
        lmess4.setForeground(Color.BLUE);
        lmess4.setBounds(50,315,350,20);
        jfP.add(lmess4);

        lmessH=new JLabel("");         //Heavy vehicle
        lmessH.setFont(new Font("Time New Roman",Font.ITALIC,15));
        lmessH.setForeground(Color.blue);
        lmessH.setBounds(50,345,320,20);
        jfP.add(lmessH);





        currentP();   /* to print current price   ==========>>>>>>>>>>*/
        jfP.setBackground(Color.GRAY);
        jfP.setVisible(true);

    }
    void managePrice()
    {



    }
    void currentP()
    {
        // to show current capacity
        try {

            // Establish the connection to the database
            connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // Create a statement
            statement = connection.createStatement();

            // Execute the query to retrieve data
            String query = "SELECT Vehicle_type, Capacity FROM availability";
            resultSet = statement.executeQuery(query);

            // Variables to store retrieved data
            String[] vt={"","",""};
            String[] price={"","",""};

            // Retrieve data and store it in variables
            int i=0; //iterator
            while (resultSet.next())
            {
                vt[i] = resultSet.getString("Vehicle_type");
                price[i] = resultSet.getString("Capacity");


                if( vt[i].equals("Two wheeler"))//"Two wheeler" )
                {
                    // 🏍️
                    lmess2.setText("* \uD83C\uDFCD\uFE0F Vehicle type: "+vt[i]+"    Total capacity: "+price[i]);
                }
                else if (vt[i].equals("Heavy Vehicle"))
                {
                    lmess4.setText("* \uD83D\uDE9A Vehicle type: "+vt[i]+"   Total capacity: "+price[i]);
                }
                else if (vt[i].equals("Four wheeler"))
                {  //i==2
                    lmessH.setText("* \uD83D\uDE98 Vehicle type: "+vt[i]+"  Total capacity: "+price[i]);
                }

                i+=1;

            }


            // Do something with the retrieved data stored in variables
            // For example, use the data in further processing or calculations

        }
        catch (SQLException e)
        {
            e.getMessage();
        }
        finally
        {
            // Close the resources in a finally block to ensure they're closed even if an exception occurs
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override   // For combo box
    public void itemStateChanged(ItemEvent e)
    {
    }

    @Override // for button
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource()==btnbk)
        {
            jfP.setVisible(false);
            new Home();
        }
        else if (event.getSource()==btnUpdate)
        {


            vehiclety = (String) v_type.getSelectedItem();
            String strCap= txtcapacity.getText();

            if (vehiclety.equals("---Select---") || strCap.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Empty field  fill all field");

            }
            else
            {
                try {
                    capacity = Integer.parseInt(strCap);   //convert string to integer
                    updateP();    //update price function
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Only number is allow \n" + ex);
                }
            }
        }
        else if (event.getSource()==btnInsert)
        {
            jfP.setVisible(false);
            new InsertRc();

        }

    }
    void updateP()
    {
        Connection connection=null;
        try
        {
            connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            String updateQuery = "UPDATE availability SET Capacity = ? WHERE Vehicle_type = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1,capacity); // strNewPass, strUname, strPrePass need to be defined somewhere
            preparedStatement.setString(2, vehiclety);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0)
            {
//                System.out.println("Password updated successfully!");
                JOptionPane.showMessageDialog(null, "Capacity of vehicle "+vehiclety+" \n updated !");
                currentP();
            }
            else
            {
//                System.out.println("Invalid username or password.");

                JOptionPane.showMessageDialog(null, "Capacity is not updated \n it might be some issue");
            }
        } catch (SQLException e) {
//            System.out.println("Error: " + e);
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        finally
        {
            try {
                if (connection != null) {
                    connection.close(); // Close the connection in the finally block
                }
            } catch (SQLException e) {
                System.out.println("Error while closing connection: " + e);
            }
        }
    }




    public static void main(String[] args) {
        new UpdateCapacity();
    }


}

