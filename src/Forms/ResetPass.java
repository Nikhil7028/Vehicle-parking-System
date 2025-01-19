package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ResetPass extends JFrame implements ActionListener
{
    static final String JDBC_URL = "jdbc:mysql://localhost:3308/vps";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "dbms";
    private JTextField txtUsername, ansField;
    private String OPTION=null;
    private JRadioButton radiobtnPass, radiobtnQues;
    private ButtonGroup group;
    private JLabel lblPrevPass,securityQuestionLabel,answerLabel, lblnewpass, lblrenewpass, lblError;
    private JComboBox<String> securityQuestionComboBox;
    private JButton btnreset,btnclear,btnlogin;
    private JPasswordField passPriv, txtnewpass, txtrenewpass;
    private String strUname,strPrePass, strNewPass, strNewRePass, strSecuritQ, strAns;
    ResetPass() {

        setTitle("Reset Password");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(null);
        getContentPane().setBackground(Color.PINK);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 60, 100, 30);
        usernameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(usernameLabel);

        txtUsername = new JTextField();
        txtUsername.setBounds(160, 60, 150, 30);
        txtUsername.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(txtUsername);



        JLabel QestioPass = new JLabel("How you want to reset your password?");
        QestioPass.setBounds(52, 120, 320, 30);

        QestioPass.setFont(new Font("Times New Roman", Font.BOLD, 19));
        add(QestioPass);
//        /******************/
//
        radiobtnPass=new JRadioButton("Using previous password");
        radiobtnPass.setBounds(160, 160, 250, 30);
        radiobtnPass.setFont(new Font("Times New Roman", Font.BOLD, 17));
        radiobtnPass.addActionListener(this);
        radiobtnPass.setBackground(Color.pink);
        add(radiobtnPass);
//
        radiobtnQues=new JRadioButton("Using Using security Question");
        radiobtnQues.setBounds(160, 190, 250, 30);
        radiobtnQues.setFont(new Font("Times New Roman", Font.BOLD, 17));
        radiobtnQues.addActionListener(this);
        radiobtnQues.setBackground(Color.pink);
        add(radiobtnQues);

        group = new ButtonGroup();
        group.add(radiobtnPass);
        group.add(radiobtnQues);

        lblPrevPass = new JLabel("Enter previous Password:");
        lblPrevPass.setBounds(52, 225, 200, 30);
        lblPrevPass.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblPrevPass.setVisible(false);
        add(lblPrevPass);

        passPriv=new JPasswordField();
        passPriv = new JPasswordField();
        passPriv.setBounds(255, 225, 150, 30);
        passPriv.setFont(new Font("Times New Roman", Font.BOLD, 20));
        passPriv.setVisible(false);
        add(passPriv);
//        lblPrevPass.setVisible(false);
//        passPriv.setVisible(false);
//****************************************SECURITY question**********************************************
//        lmess = new JLabel(" ");
//        lmess.setBounds(400,110, 400, 30);
//        add(lmess);







        securityQuestionLabel = new JLabel("Security Question:");
        securityQuestionLabel.setBounds(52, 225, 170, 30);
        securityQuestionLabel.setFont(new Font("Times New Roman", Font.BOLD, 19));
        securityQuestionLabel.setVisible(false);
        add(securityQuestionLabel);

        String[] securityQuestions = {"---Select Question---","What is your date of birth?","What is your favorite pet?",
                "What’s your favorite movie?", "What was your first car?"};
        securityQuestionComboBox = new JComboBox<>(securityQuestions);
        securityQuestionComboBox.setBounds(225, 225, 230, 30);
        securityQuestionComboBox.setFont(new Font("Times New Roman", Font.BOLD, 17));
        securityQuestionComboBox.setVisible(false);
        add(securityQuestionComboBox);

        answerLabel = new JLabel("Answer:");
        answerLabel.setBounds(53, 260, 80, 30);
        answerLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        answerLabel.setVisible(false);
        add(answerLabel);

        ansField=new JTextField();
        ansField.setBounds(135, 260, 240, 40);
        ansField.setFont(new Font("Times New Roman", Font.BOLD, 20));
        ansField.setVisible(false);
        add(ansField);


/* ---------------------------     Reset password using previous    ---------------------------------------------*/
        lblnewpass = new JLabel("Enter new Password:");
        lblnewpass.setBounds(52, 305, 190, 30);
        lblnewpass.setFont(new Font("Times New Roman", Font.BOLD, 20));
//        lblnewpass.setVisible(false);
        add(lblnewpass);

        txtnewpass = new JPasswordField();
        txtnewpass.setBounds(245, 305, 150, 30);
        txtnewpass.setFont(new Font("Times New Roman", Font.BOLD, 20));
//        txtnewpass.setVisible(false);
        add(txtnewpass);

        lblrenewpass = new JLabel("Re-enter new Password:");
        lblrenewpass.setBounds(52, 335, 190, 30);
        lblrenewpass.setFont(new Font("Times New Roman", Font.BOLD, 18));
        add(lblrenewpass);

        txtrenewpass=new JPasswordField();
        txtrenewpass.setBounds(250, 338, 150, 30);
        txtrenewpass.setFont(new Font("Times New Roman", Font.BOLD, 20));
//        txtrenewpass.setVisible(false);
        add(txtrenewpass);

        lblError=new JLabel("");
        lblError.setBounds(410,338,200,30);
        lblError.setFont(new Font("Times New Roman", Font.ITALIC, 19));
        lblError.setForeground(Color.RED);
        add(lblError);




        btnclear =new JButton("Clear");
        btnclear.setBounds(74,410,100,30);
        btnclear.setFont(new Font("Times New Roman",Font.BOLD,20));
        btnclear.addActionListener(this);
        btnclear.setBackground(Color.blue);
        btnclear.setForeground(Color.WHITE);

        add(btnclear);

        btnreset=new JButton("Reset");
        btnreset.setBounds(182,410,100,30);
        btnreset.setFont(new Font("Times New Roman",Font.BOLD,20));
        btnreset.addActionListener(this);
        btnreset.setBackground(Color.WHITE);
        btnreset.setForeground(Color.BLUE);
        add(btnreset);


        btnlogin=new JButton("Login");
        btnlogin.setBounds(128,480,100,30);
        btnlogin.setFont(new Font("Times New Roman",Font.BOLD,20));
        btnlogin.setBackground(Color.green);
        btnlogin.setForeground(Color.WHITE);
        btnlogin.addActionListener(this);
        add(btnlogin);




        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        lblError.setText("");
//        passPriv.setVisible(false);
//        lblPrevPass.setVisible(false);
//        securityQuestionLabel.setVisible(false);
//        securityQuestionComboBox.setVisible(false);
//        ansField.setVisible(false);
//        answerLabel.setVisible(false);
//        txtnewpass.setVisible(false);
//        txtrenewpass.setVisible(false);
        //First make all invisible
        if(event.getSource() ==radiobtnPass)
        { OPTION="P";
            securityQuestionLabel.setVisible(false);
            securityQuestionComboBox.setVisible(false);
            ansField.setVisible(false);
            answerLabel.setVisible(false);

            lblPrevPass.setVisible(true);
            passPriv.setVisible(true);
            lblnewpass.setVisible(true);
            txtnewpass.setVisible(true);
            lblrenewpass.setVisible(true);
            txtrenewpass.setVisible(true);
            lblError.setVisible(true);
        }
        else if (event.getSource()==radiobtnQues)
        { OPTION="Q";
            lblPrevPass.setVisible(false);
            passPriv.setVisible(false);
            securityQuestionLabel.setVisible(true);
            securityQuestionComboBox.setVisible(true);
            ansField.setVisible(true);
            answerLabel.setVisible(true);
        }
        else if (event.getSource()==btnclear)
        {
            clear();
        }
        else if (event.getSource()==btnlogin)
        {
            dispose();
            new LoginForm();
        }
        else if(event.getSource()==btnreset)
        {
            passUpdate();

        }
    }

void passUpdate()
{
    String check = isEmpty();  // Check if the fields are empty or not, assuming this method exists and returns a string
    if (check.equals("okp"))
    {
        Connection connection=null;
        try
        {
            connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            String updateQuery = "UPDATE admin SET PassAdmin = ? WHERE NameAdmin = ? AND PassAdmin = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, strNewPass); // strNewPass, strUname, strPrePass need to be defined somewhere
            preparedStatement.setString(2, strUname);
            preparedStatement.setString(3, strPrePass);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Password updated successfully!");
                JOptionPane.showMessageDialog(null, "Password reset successfully");
            } else {
                System.out.println("Invalid username or password.");
                JOptionPane.showMessageDialog(null, "Invalid username or password\nPlease enter correct or use another option.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
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
    /***************reset password for security question*/
    else if (check.equals("okq"))
    {

        Connection connection=null;
        try
        {
            connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

//            String updateQuery = "UPDATE admin SET PassAdmin = ? WHERE NameAdmin = ? AND PassAdmin = ?";
            String updateQuery="update vps.admin set PassAdmin = ? where NameAdmin = ? AND Securityques = ? AND ans = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, strNewPass); // strNewPass, strUname, strPrePass need to be defined somewhere
            preparedStatement.setString(2, strUname);
            preparedStatement.setString(3, strSecuritQ);
            preparedStatement.setString(4, strAns);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Password updated successfully!");
                JOptionPane.showMessageDialog(null, "Password reset successfully");
            } else {
                System.out.println("Invalid username or password.");
                JOptionPane.showMessageDialog(null, "Invalid username or password\nPlease enter correct or use another option.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
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


}


    String isEmpty()
    { //return false if empty
        if(OPTION.equals("P"))
        {
            strUname=txtUsername.getText();
                char[] pre=passPriv.getPassword();
            strPrePass=new String(pre);
                char[] n=txtnewpass.getPassword();
            strNewPass=new String(n);
                char[] rn=txtrenewpass.getPassword();
            strNewRePass= new String(rn);

            if (strUname.isEmpty() || strPrePass.isEmpty() || strNewPass.isEmpty() || strNewRePass.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Fill all the information");
                return "Empty";
            }
            else if(!(strNewPass.equals(strNewRePass)))
            {
                lblError.setText("Password is not match");
                return "nomatch";
            }
            else
            {
                return "okp";
            }

        }

        /* ***********Security question reset password ********************/
        else if (OPTION.equals("Q"))
        {  //write for Question
            System.out.println("Question");
            strUname=txtUsername.getText();
                char[] n=txtnewpass.getPassword();
            strNewPass=new String(n);                   //New password
                char[] rn=txtrenewpass.getPassword();
            strNewRePass= new String(rn);               //reenter new password

            strSecuritQ = (String) securityQuestionComboBox.getSelectedItem();
            strAns = ansField.getText();
            if (strUname.isEmpty() || strSecuritQ.equals("---Select Question---") || strNewPass.isEmpty() || strNewRePass.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Fill all the information");
                return "Empty";
            }
            else if(!(strNewPass.equals(strNewRePass)))
            {
                lblError.setText("Password is not match");
                return "nomatch";
            }
            else
            {
                return "okq";
            }


        }
        return "";

    }


    void clear()
    {
        txtUsername.setText("");
        radiobtnPass.setSelected(false);
        radiobtnQues.setSelected(false);
        passPriv.setText("");
        txtnewpass.setText("");
        txtrenewpass.setText("");
        ansField.setText("");
        securityQuestionComboBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new ResetPass();
    }
}
