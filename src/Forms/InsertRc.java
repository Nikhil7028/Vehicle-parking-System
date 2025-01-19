package Forms;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertRc implements ItemListener, ActionListener
{
    private double price2, price4, PriceH;
    static final String JDBC_URL = "jdbc:mysql://localhost:3308/vps";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "dbms";
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    Connection connection = null;
    Statement statement = null;

//    preparedStatement pstmt=null;
    ResultSet resultSet = null;
    JFrame jfI=null;
    String Vehicleno,fname,lname, vehiclety,mobile,formattedDate, Entime, EXtime=null;
    JPanel panel;

    JLabel vehicleNo, f_name, l_name,lblMn,pay,time,date,type,lbl1,lmess;
    private JLabel lbltime,lDu;
    private JTextField txtTime;
    JComboBox v_type;

    JButton login, register,btnsubmit, btnPrice,btnUpdate, btnAmount;
    private JButton btnback;
    JTextField txtvno, txtfn, txtln, txtMn, txtDuration, txtAmount;
    JPasswordField passwordfield;
    private int PRICE=1, AMOUNT, Duration;
    private int currentVehicle=0,availableCapacity=0;
    public InsertRc()
    {
        System.out.println("insert call");
        components();
    }
    void components()
    {
        jfI=new JFrame("Entry record");
        jfI.setSize(800,800);
        jfI.setTitle("Entry records");
        jfI.setLayout(null);



        btnPrice=new JButton("Manage Rates");
        btnPrice.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnPrice.setBounds(610, 20, 159, 25);
        btnPrice.addActionListener(this);
        btnPrice.setBackground(Color.BLACK);
        btnPrice.setForeground(Color.WHITE);
        jfI.add(btnPrice);



        vehicleNo = new JLabel("Vehicle Number: ");
        vehicleNo.setFont(new Font("Times New Roman", Font.BOLD, 20));
        vehicleNo.setBounds(100, 85, 150, 40);
        jfI.add(vehicleNo);

        txtvno = new JTextField();
        txtvno.setFont(new Font("Times New Roman", Font.BOLD, 20));
        txtvno.setBounds(260, 85, 150, 30);
        jfI.add(txtvno);

        f_name=new JLabel("Owner first Name:");
        f_name.setFont(new Font("Times New Roman",Font.BOLD,20));
        f_name.setBounds(95,125,170,30);
        jfI.add(f_name);

        txtfn = new JTextField();
        txtfn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        txtfn.setBounds(265, 125, 150, 30);
        jfI.add(txtfn);

        l_name=new JLabel("Owner last Name:");
        l_name.setFont(new Font("Times New Roman",Font.BOLD,20));
        l_name.setBounds(95,155,170,30);
        jfI.add(l_name);

        txtln = new JTextField();
        txtln.setFont(new Font("Times New Roman", Font.BOLD, 20));
        txtln.setBounds(265, 157, 150, 30);
        jfI.add(txtln);

        type=new JLabel("Vehicle type:");
        type.setFont(new Font("Times New Roman",Font.BOLD,20));
        type.setBounds(95,195,170,30);
        jfI.add(type);


        String[] s1={"---Select---","Two wheeler","Four wheeler","Heavy Vehicle"};
        v_type=new JComboBox<>(s1);
        v_type.setBounds(265,195,160,30);
        v_type.addItemListener(this);
        jfI.add(v_type);


        lblMn=new JLabel("Owner Mobile No.:");
        lblMn.setFont(new Font("Times New Roman",Font.BOLD,20));
        lblMn.setBounds(95,230,180,30);
        jfI.add(lblMn);

        txtMn = new JTextField();
        txtMn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        txtMn.setBounds(270, 230, 120, 30);
        jfI.add(txtMn);
//        txtMn.addKeyListener(this);

        JLabel lblDuration =new JLabel("Duration of parking(in Hour) \uD83D\uDD52 : ");
        lblDuration.setFont(new Font("Times New Roman",Font.BOLD,20));
        lblDuration.setBounds(90,270,270,30);
        jfI.add(lblDuration);

        txtDuration=new JTextField();
        txtDuration.setFont(new Font("Times New Roman", Font.BOLD, 20));
        txtDuration.setBounds(360, 270, 120, 30);
        txtDuration.addActionListener(this);
        jfI.add(txtDuration);

        lDu=new JLabel("");    /******************************************/ //showing error invaild duration
        lDu.setFont(new Font("Times New Roman",Font.ITALIC,17));
        lDu.setBounds(485,270,230,25);
        lDu.setForeground(Color.RED);
        jfI.add(lDu);


//        JLabel lblAmount=new JLabel("Total Amount(₹): ");
//        lblAmount.setFont(new Font("Time New Roman",Font.BOLD,20));
//        lblAmount.setBounds(90,310,140,30);
//        jfI.add(lblAmount);

        btnAmount=new JButton("Total amount");
        btnAmount.setFont(new Font("Time New Roman",Font.BOLD,20));
        btnAmount.setBounds(90,310,160,30);
        btnAmount.addActionListener(this);
        jfI.add(btnAmount);


        txtAmount=new JTextField();
        txtAmount.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtAmount.setBounds(255, 310, 120, 30);
        txtAmount.setEditable(false);
        jfI.add(txtAmount);
//        txtAmount.setText("hi");
/* ******************************************************/ //call quick calculation
//        txtDuration.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                calculateAmount();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                calculateAmount();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                // Not needed for plain text fields
//            }
//        });

        /* ******************************************************/




        btnsubmit=new JButton("Submit");
        btnsubmit.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnsubmit.setBounds(190, 400, 150, 30);
        btnsubmit.addActionListener(this);
        jfI.add(btnsubmit);
        btnsubmit.setVisible(false);

        lmess = new JLabel(" ");
        lmess.setBounds(210,450, 400, 30);
        lmess.setFont(new Font("Time New Roman",Font.ITALIC,17));
        jfI.add(lmess);

        btnback=new JButton("Back");
        btnback.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnback.setBounds(360, 400, 150, 30);
        btnback.addActionListener(this);
        jfI.add(btnback);

//        initialize();                   /*  call current time*/
        DateTimeDisplay();



        jfI.setVisible(true);
        jfI.getContentPane().setBackground(Color.ORANGE);


    }
    //function for update time
private void initialize()
{
    lbltime = new JLabel("Current Time: ");
    lbltime.setFont(new Font("Arial", Font.PLAIN, 24));
    lbltime.setBounds(450, 70, 300, 30); // Adjust these values based on your layout
    jfI.add(lbltime);



/*************************************************************Update time and date***********/
    updateTime(); // Initialize with the current time

    Timer timer = new Timer(1000, e -> updateTime());
    timer.start();

    lbltime.setVisible(true);
    jfI.setVisible(true); // Ensure the frame visibility is set to true
}

    private void updateTime() {
        java.util.Date now = new Date(); // Get current time
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); // Format time as HH:mm:ss
        String currentTime = sdf.format(now); // Format date to string
        lbltime.setText("Current Time: " + currentTime);
        jfI.add(lbltime);
    }

    @Override    //Perform action on clicking
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource()==btnsubmit)
        {
            vehiclety = (String) v_type.getSelectedItem();
            getValue();
            calculateAmount();


            Vehicleno = txtvno.getText();
            fname = txtfn.getText();
            lname = txtln.getText();
            vehiclety = (String) v_type.getSelectedItem();
            mobile = txtMn.getText();
//            Duration  alreay assing in CalculateAmount function
//            AMOUNT alreay assing in CalculateAmount function



            if (Vehicleno.isEmpty() || fname.isEmpty() || lname.isEmpty() || vehiclety.equals("---Select---") || mobile.length() != 10) {
                lmess.setText("You \uD83D\uDE21 entered Invalid data please enter valid data");
                lmess.setForeground(Color.RED);

            } else {
                vehiclety = (String) v_type.getSelectedItem();
                currentVehicleNo(vehiclety);
                available(vehiclety);
                if (currentVehicle<availableCapacity)
                {
                    inData();
                }
                else
                {
                    String errorMessage="Space is not available for "+vehiclety+" in parking PARKING IS FULL";
                    JOptionPane.showMessageDialog(jfI, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                }

//                                               /* insert record */

            }
        }
        else if (event.getSource()==btnback)
        {
            jfI.setVisible(false);
            new Home();

        }
        else if (event.getSource()==btnPrice)
        {
            jfI.setVisible(false);
            new PriceUpdate();
        }
        //Calculate amount
        else if (event.getSource()==btnAmount)
        {
            vehiclety = (String) v_type.getSelectedItem();
            getValue();
            calculateAmount();
            btnsubmit.setVisible(true);


        }


    }


    void inData()
    {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
//                String query = "INSERT INTO vehicle (name, email) VALUES (?, ?)";
            Vehicleno = txtvno.getText();
            fname = txtfn.getText();
            lname = txtln.getText();
            vehiclety = (String) v_type.getSelectedItem();
            mobile = txtMn.getText();
//            Duration  alreay assing in CalculateAmount function
//            AMOUNT alreay assing in CalculateAmount function
            Entime=formattedDate;

//`DurationInHour`,
//`Amount`,
//`EntryTime`,
//`ExitTime`);
            String query = "INSERT INTO vehicle (Vehicleno,OwnFname,OwnerLname,v_type,mobile,DurationInHour,Amount,EntryTime,ExitTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Vehicleno);
            preparedStatement.setString(2, fname);
            preparedStatement.setString(3, lname);
            preparedStatement.setString(4, vehiclety);
            preparedStatement.setString(5, mobile);
            preparedStatement.setInt(6, Duration);
            preparedStatement.setInt(7, AMOUNT);
            preparedStatement.setString(8, Entime);
            preparedStatement.setString(9, EXtime);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Data inserted successfully!");
                lmess.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to insert data.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }





    @Override
    public void itemStateChanged(ItemEvent e) { // for combo box
//        lbl1 = new JLabel(" ");
//        lbl1.setBounds(500, 220, 190, 40);
//        if (e.getStateChange() == ItemEvent.SELECTED) {
//            if (e.getSource() == v_type) {
////                lbl1=new JLabel(" ");
//                String selectedType = (String) v_type.getSelectedItem();
//                System.out.println("Selected Vehicle Type: " + selectedType);
//                lbl1.setText(v_type.getSelectedItem() + " selected");
//                jfI.add(lbl1);
//
//            }
//        }
    }


    private void calculateAmount() {
        try {
            String vty=(String) v_type.getSelectedItem();
            if (vty.equals("---Select---"))
            {
                JOptionPane.showMessageDialog(null, "Please select vehicle type");

            }
            else
            { //************************************************************************************
                Duration = Integer.parseInt(txtDuration.getText());
                AMOUNT = Duration * PRICE;
                txtAmount.setText(String.valueOf(AMOUNT));
                lDu.setText("");
            }
        }
        catch (NumberFormatException ex)
        {
//            JOptionPane.showMessageDialog(null, "Please enter a valid number for duration.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            lDu.setText("Invalid number for duration");
            txtDuration.setText(""); // Clear the field upon invalid input
            txtAmount.setText(""); // Clear the amount field as well
        }
    }

    void getValue()
    {


        try
        {
            // Establish the connection to the database
            connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // Create a statement
            statement = connection.createStatement();

            // Execute the query to retrieve data
            String query = "SELECT Vehicle_type, PriceForHr FROM price";
            resultSet = statement.executeQuery(query);

            // Variables to store retrieved data
            String[] vt={"","",""};
            int[] prices={1,1,1};

            // Retrieve data and store it in variables
            int i=0; //iterator
            while (resultSet.next())
            {
                vt[i] = resultSet.getString("Vehicle_type");
                prices[i] = resultSet.getInt("PriceForHr");

                // You can process the retrieved data here
                // For example, print it to the console
//                System.out.println("Vehicle type: " + vt[i]);
//                System.out.println("Price: " + price[i]);

                if( vt[0].equals(vehiclety) )  //Check user vehicle type and table vehicle type
                {
                    PRICE=prices[0];
                }
                if (vt[1].equals(vehiclety))
                {
                    PRICE=prices[1];
                }
                if (vt[2].equals(vehiclety))
                {
                    PRICE=prices[2];

                }

                i+=1;

            }
//            return PRICE;



            // Do something with the retrieved data stored in variables
            // For example, use the data in further processing or calculations

        }
        catch (SQLException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            // Close the resources in a finally block to ensure they're closed even if an exception occurs
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

    }




    void DateTimeDisplay() {


//        dateTimeLabel = new JLabel("", SwingConstants.CENTER);
        lbltime=new JLabel();
        lbltime.setFont(new Font("Arial", Font.BOLD, 15));
//        lbltime.setBounds();
        lbltime.setBounds(450, 70, 360, 25); // Adjust these values based on your layout

        updateDateTime(); // Initially display current date and time

//        JPanel panel = new JPanel(new BorderLayout());
        jfI.add(lbltime);

//        add(panel);
//        setVisible(true);

        // Update date and time every second
        Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();
    }

    private void updateDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formattedDate = dateFormat.format(new Date());
        lbltime.setText("Current Date and Time: " + formattedDate);
    }

    void currentVehicleNo(String vt)
    {
        PreparedStatement pstmt = null;

        try {
//            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            String sql = "SELECT COUNT(*) AS count FROM vehicle WHERE v_type=? and ExitTime is null";
             pstmt= connection.prepareStatement(sql);

            pstmt.setString(1, vt);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                currentVehicle = rs.getInt("count");
                System.out.println("Count of " + vt + ": " + currentVehicle);
            }
            else
            {

                System.out.println("No data found for " + vt);
            }

            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException se2) {
                // nothing we can do
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    void available(String vt)
    {

        PreparedStatement pstmt = null;

        try {
//            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            String sql = "SELECT Capacity FROM availability WHERE Vehicle_type=?";
            pstmt = connection.prepareStatement(sql);

            // Set the vehicle type parameter dynamically

            pstmt.setString(1, vt);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                availableCapacity = rs.getInt("Capacity");
                System.out.println("available Capacity for vehicle type " + vt + ": " + availableCapacity);
            } else {
                System.out.println("No available Capacity found for vehicle type " + availableCapacity);
            }

            rs.close();
            pstmt.close();
            connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException se2) {
                // nothing we can do
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }


    }



    public static void main(String[] args) {
        new InsertRc();
    }
}




