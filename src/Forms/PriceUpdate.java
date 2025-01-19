package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;

public class PriceUpdate extends JFrame implements ItemListener, ActionListener
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
    private JTextField txtPrice, txtW4, txtWH;
    private String vehiclety;  //for update
    private int price;      //for update
    public PriceUpdate()
    {
        jfP=new JFrame("Mange price");
        jfP.setSize(480,480);
        jfP.setLayout(null);

        JLabel lbltitle=new JLabel("Manage Vehicle rate \uD83D\uDCB8 \uD83D\uDCB6 \uD83D\uDCB7");
        lbltitle.setFont(new Font("Time New Roman",Font.BOLD,20));
        lbltitle.setBounds(95,15,400,40);
        lbltitle.setForeground(Color.GREEN);
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

        JLabel lblW4=new JLabel("New price :");
        lblW4.setFont(new Font("Time New Roman",Font.BOLD,20));
        lblW4.setBounds(50,105,150,30);
        jfP.add(lblW4);

        txtPrice=new JTextField();
        txtPrice.setFont(new Font("Time New Roman",Font.BOLD,20));
        txtPrice.setBounds(210,105,150,30);
        jfP.add(txtPrice);



        btnUpdate=new JButton("Update");
        btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnUpdate.setBounds(200, 180, 150, 30);
        btnUpdate.setBackground(Color.GREEN);
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.addActionListener(this);
        jfP.add(btnUpdate);

        btnbk=new JButton("Back");
        btnbk.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnbk.setBounds(210, 240, 100, 30);
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
        lmess4.setBounds(50,315,320,20);
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
        try {
            // Establish the connection to the database
            connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // Create a statement
            statement = connection.createStatement();

            // Execute the query to retrieve data
            String query = "SELECT Vehicle_type, PriceForHr FROM price";
            resultSet = statement.executeQuery(query);

            // Variables to store retrieved data
            String[] vt={"","",""};
            String[] price={"","",""};

            // Retrieve data and store it in variables
            int i=0; //iterator
            while (resultSet.next())
            {
                vt[i] = resultSet.getString("Vehicle_type");
                price[i] = resultSet.getString("PriceForHr");

                // You can process the retrieved data here
                // For example, print it to the console
//                System.out.println("Vehicle type: " + vt[i]);
//                System.out.println("Price: " + price[i]);

                if( i==0 )
                {
                    lmess2.setText("* Vehicle type: "+vt[0]+"    price per hr: "+price[0]);
                }
                else if (i==1)
                {
                    lmess4.setText("* Vehicle type: "+vt[1]+"   price per hr: "+price[1]);
                }
                else
                {  //i==2
                    lmessH.setText("* Vehicle type: "+vt[2]+"  Price per hr: "+price[2]);
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
            new InsertRc();
        }
        else if (event.getSource()==btnUpdate)
        {


            vehiclety = (String) v_type.getSelectedItem();
            String strPrice= txtPrice.getText();

            if (vehiclety.equals("---Select---") || strPrice.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Empty field  fill all field");

            }
            else
            {
                try {
                    price = Integer.parseInt(strPrice);   //convert string to integer
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

            String updateQuery = "UPDATE price SET PriceForHr = ? WHERE Vehicle_type = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, price); // strNewPass, strUname, strPrePass need to be defined somewhere
            preparedStatement.setString(2, vehiclety);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0)
            {
//                System.out.println("Password updated successfully!");
                JOptionPane.showMessageDialog(null, "Price of vehicle "+vehiclety+" \n updated !");
                currentP();
            }
            else
            {
//                System.out.println("Invalid username or password.");
                JOptionPane.showMessageDialog(null, "Price is not updated \n it might be some issue");
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
        new PriceUpdate();
    }


}
