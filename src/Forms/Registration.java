package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class Registration extends JFrame implements ActionListener {
    static final String JDBC_URL = "jdbc:mysql://localhost:3308/vps";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "dbms";

    private final String SECURTY_CODE="MC232512";    //Security code *********************
    private JTextField usernameField, mobileNumberField, ansField;
    private JPasswordField passwordField, reEnterPasswordField, passSecurityCode;
//    private String l;
    private JComboBox<String> securityQuestionComboBox;
    private JLabel lmess;
    private JTextArea answerTextArea;
    private JButton registerButton, clearButton, loginButton;
    private String uname,password,repassword,securityQ,securityAns,Mobile, securityCode;

    public Registration() {
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(null);
        getContentPane().setBackground(Color.PINK);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 30, 100, 30);
        usernameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(160, 30, 150, 30);
        usernameField.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 70, 100, 30);

        passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 70, 150, 30);

        passwordField.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(passwordField);

        JLabel reEnterPasswordLabel = new JLabel("Re-enter Password:");
        reEnterPasswordLabel.setBounds(50, 110, 190, 30);

        reEnterPasswordLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        add(reEnterPasswordLabel);

        reEnterPasswordField=new JPasswordField();
        reEnterPasswordField = new JPasswordField();
        reEnterPasswordField.setBounds(250, 110, 150, 30);

        reEnterPasswordField.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(reEnterPasswordField);

        lmess = new JLabel(" ");
        lmess.setBounds(400,110, 400, 30);
        add(lmess);




        JLabel securityQuestionLabel = new JLabel("Security Question:");
        securityQuestionLabel.setBounds(50, 150, 170, 30);

        securityQuestionLabel.setFont(new Font("Times New Roman", Font.BOLD, 19));
        add(securityQuestionLabel);

        String[] securityQuestions = {"---Select Question---","What is your date of birth?","What is your favorite pet?",
                "What’s your favorite movie?", "What was your first car?"};
        securityQuestionComboBox = new JComboBox<>(securityQuestions);
        securityQuestionComboBox.setBounds(230, 150, 230, 30);

        securityQuestionComboBox.setFont(new Font("Times New Roman", Font.BOLD, 17));
        add(securityQuestionComboBox);

        JLabel answerLabel = new JLabel("Answer:");
        answerLabel.setBounds(50, 190, 80, 30);

        answerLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(answerLabel);

        ansField=new JTextField();

        ansField.setBounds(140, 190, 240, 60);

//        answerTextArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
//        add(answerTextArea);

        ansField.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(ansField);

        JLabel mobileNumberLabel = new JLabel("Mobile Number:");
        mobileNumberLabel.setBounds(50, 260, 150, 30);

        mobileNumberLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(mobileNumberLabel);

        mobileNumberField = new JTextField();
        mobileNumberField.setBounds(200, 260, 150, 30);
        mobileNumberField.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(mobileNumberField);

        JLabel lblSecurityCode=new JLabel("Security Code");
        lblSecurityCode.setBounds(370, 260, 150, 30);
        lblSecurityCode.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(lblSecurityCode);

        passSecurityCode = new JPasswordField();
        passSecurityCode.setBounds(530, 260, 150, 30);
        passSecurityCode.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(passSecurityCode);







        registerButton = new JButton("Register");
        registerButton.setBounds(78, 310, 140, 30);
        registerButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        registerButton.setBackground(Color.GREEN);
        registerButton.addActionListener(this);
        add(registerButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(230, 310, 100, 30);
        clearButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        clearButton.addActionListener(this);
        clearButton.setBackground(Color.BLUE);
        add(clearButton);

        loginButton = new JButton("Login");
        loginButton.setBounds(154, 350, 100, 30);
        loginButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        loginButton.addActionListener(this);
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.CYAN);

        add(loginButton);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton)
        {
            String reString= checkEmpty();
            if (reString.equals("empty"))
            {
                JOptionPane.showMessageDialog(this, "Fill all the information");
            }
            else
            {

                if (Mobile.length() ==10)
                {
//                    try
//                    {
//                        int mob= Integer.parseInt(Mobile);
//                    }
//                    catch (Exception ex)
//                    {
//                        JOptionPane.showMessageDialog(this, "Character is not allow in mobile number");
//                    }


                     if (password.equals(repassword))
                     {
                        lmess.setText("");
                        if (securityCode.equals(SECURTY_CODE))
                        {
                            inDetail();           //insert detail
//                            JOptionPane.showMessageDialog(this, "ok");

                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this, "Invalid security code \n Please enter correct security code \n OR Ask to the Owner");
                        }

                     }
                     else
                     {
//                        JOptionPane.showMessageDialog(this, "");
                        lmess.setText("");
                        lmess.setText(" \uD83D\uDE21 Password Not match");
                        lmess.setForeground(Color.RED);

                     }


                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Invalid Mobile Number");

                }


            }
//            if(passwordField.equals(reEnterPasswordField) )// checkEmpty())
//            {
//                JOptionPane.showMessageDialog(this, "Registered successfully!");
//
//            }


//            JOptionPane.showMessageDialog(this, "Registered successfully!");
        }
        else if (e.getSource() == clearButton)
        {
            // Clear all fields
            usernameField.setText("");
            passwordField.setText("");
//            reEnterPasswordField.
            securityQuestionComboBox.setSelectedIndex(0);
//            answerTextArea.setText("");
            ansField.setText("");
            passSecurityCode.setText("");
            mobileNumberField.setText("");
            lmess.setText("");
            reEnterPasswordField.setText("");
        }
        else if (e.getSource() == loginButton)
        {
            dispose();
            new LoginForm();

        }
    }
    //Original function
    String checkEmpty()
    {
//        usernameField, mobileNumberField, ansField
//        passwordField, reEnterPasswordField
        uname = usernameField.getText();
//        password= Arrays.toString(passwordField.getPassword());
        char[] passwordchar = passwordField.getPassword();
        password = new String(passwordchar);
        char[] passwordrechar = reEnterPasswordField.getPassword();
        repassword = new String(passwordrechar);
        char[] code= passSecurityCode.getPassword();
        securityCode=new String(code);

        securityQ = (String) securityQuestionComboBox.getSelectedItem();
        securityAns = ansField.getText();
        Mobile = mobileNumberField.getText();
        if (uname.isEmpty() || password.isEmpty() || repassword.isEmpty() || securityQ.equals("---Select Question---") || securityAns.isEmpty() || Mobile.isEmpty() || securityCode.isEmpty())  //if empty return true
        {
            return "empty";
        }
        return "non";
    }
//boolean checkEmpty() {
//    // Check for empty fields
//    uname = usernameField.getText();
//    char[] passwordChar = passwordField.getPassword();
//    password = new String(passwordChar);
//    char[] repasswordChar = reEnterPasswordField.getPassword();
//    repassword = new String(repasswordChar);
//    securityQ = (String) securityQuestionComboBox.getSelectedItem();
//    securityAns = ansField.getText();
//    Mobile = mobileNumberField.getText();
//
//    return !uname.isEmpty() && password.length() > 0 && repassword.length() > 0 && !securityQ.equals("---Select Question---") && !securityAns.isEmpty() && !Mobile.isEmpty();
//}
/*           function 03 */
//    boolean checkEmpty() {
//        uname = usernameField.getText();
//        char[] passwordChars = passwordField.getPassword();
//        password = new String(passwordChars);
//        char[] reEnterPasswordChars = reEnterPasswordField.getPassword();
//        reEnterPasswordField = new String(reEnterPasswordChars);
//        securityQ = (String) securityQuestionComboBox.getSelectedItem();
//        securityAns = ansField.getText();
//        Mobile = mobileNumberField.getText();
//
//        // Rest of your code and validations
//        return true;
//    }

 void inDetail()
 {
     try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD))
     {
//                String query = "INSERT INTO vehicle (name, email) VALUES (?, ?)";

         String query = "INSERT INTO admin (NameAdmin,PassAdmin,Securityques,Ans,Mobileno) VALUES (?, ?, ?, ?, ?)";
         PreparedStatement preparedStatement = connection.prepareStatement(query);
//         uname.isEmpty() || password.isEmpty() || repassword.isEmpty() || securityQ.equals("---Select Question---") || securityAns.isEmpty() || Mobile.isEmpty() || securityCode.isEmpty())  //if empty return true
         preparedStatement.setString(1, uname);
         preparedStatement.setString(2, password);
         preparedStatement.setString(3, securityQ);
         preparedStatement.setString(4, securityAns);
         preparedStatement.setString(5, Mobile);

         int rowsAffected = preparedStatement.executeUpdate();
         if (rowsAffected > 0) {
             JOptionPane.showMessageDialog(null, "Data inserted successfully!");
//             lmess.setText("");
         } else {
             JOptionPane.showMessageDialog(null, "Failed to insert data.");
         }
         connection.close();
     } catch (SQLException ex)
     {
         ex.printStackTrace();
         JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
     }


 }
    public static void main(String[] args) {
        new Registration();
    }
}
