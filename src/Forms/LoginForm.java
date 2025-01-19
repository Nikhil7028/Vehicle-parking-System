package Forms;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Arrays;

class LoginForm implements ActionListener {
    static final String JDBC_URL = "jdbc:mysql://localhost:3308/vps";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "dbms";

    JPanel panel;
//    Home H = null;   //Created home object for home page
    JFrame jf;
    JLabel label1, label2, lmes, background;
    JButton login, register, btnreset;
    JTextField textfield1, textfield2, textfield3;
    JPasswordField passwordfield;
    String userValue = null;       //get user entered username from the textField1
    String passValue = null;

    public LoginForm() {
        initComponents();
        imagebkg();

    }

    public void initComponents() {
        jf = new JFrame("Login");
        jf.setTitle("Login");
        jf.setLayout(null);
        jf.setSize(800, 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        jf.setVisible(true);

        JScrollPane scrollBar = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jf.add(scrollBar);

        label1 = new JLabel("User Login");
        label1.setFont(new Font("Times New Roman", Font.BOLD, 24));
        label1.setBounds(400, 20, 500, 40);
        jf.add(label1);

        label1 = new JLabel("Username");
        label1.setFont(new Font("Times New Roman", Font.BOLD, 24));
        label1.setBounds(200, 80, 150, 40);
        jf.add(label1);

        textfield1 = new JTextField();
        textfield1.setFont(new Font("Times New Roman", Font.BOLD, 24));
        textfield1.setBounds(400, 80, 150, 30);
        jf.add(textfield1);

        label1 = new JLabel("Password");
        label1.setFont(new Font("Times New Roman", Font.BOLD, 24));
        label1.setBounds(200, 200, 100, 40);
        jf.add(label1);

        passwordfield = new JPasswordField();
        passwordfield.setFont(new Font("Times New Roman", Font.BOLD, 24));
        passwordfield.setBounds(400, 200, 150, 30);
        jf.add(passwordfield);

        login = new JButton("Login");
        login.setFont(new Font("Times New Roman", Font.BOLD, 24));
        login.setBounds(400, 280, 100, 30);
        login.addActionListener(this);
        jf.add(login);

        register =new JButton("Registration");
        register.setFont(new Font("Times New Roman", Font.BOLD, 20));
        register.setBounds(200, 280, 150, 30);
        register.addActionListener(this);
        jf.add(register);

        btnreset=new JButton("Reset password");
        btnreset.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnreset.setBounds(300, 340, 140, 30);
        btnreset.addActionListener(this);
        btnreset.setBackground(Color.WHITE);
        btnreset.setForeground(Color.BLUE);

        jf.add(btnreset);


        lmes = new JLabel(" ");
        lmes.setBounds(500,240, 400, 30);
        jf.add(lmes);


        Container contentPane = jf.getContentPane();
        contentPane.setBackground(Color.GREEN);
        jf.setVisible(true);

    }

    public void actionPerformed(ActionEvent event) {
//            if(e.equals("Login"))
        if (event.getSource() == login)
        {
            userValue = textfield1.getText();        //get user entered username from the textField1
//            passValue = Arrays.toString(passwordfield.getPassword());
            char[] pass=passwordfield.getPassword();
            passValue= new String (pass);


//                boolean flag = validation(userValue, passValue);

            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                String query = "SELECT * FROM admin WHERE NameAdmin = ? AND PassAdmin = ?";
//            NameAdmin
//                    PassAdmin
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, userValue);
                preparedStatement.setString(2, passValue);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    System.out.println("Authentication successful! User found in the database.");
                    lmes.setText(" ");
                    JOptionPane.showMessageDialog(null, "Login sucessfully welcome "+userValue);
                    jf.setVisible(false);
                    new Home();


                } else {
                    System.out.println("Invalid username or password.");
                    lmes.setText("Invalid username or password.");
                    lmes.setForeground(Color.RED);
                }
            } catch (SQLException e) {
                System.out.println("Error " + e);

            }
        }
        else if(event.getSource()==register)
        {
            jf.setVisible(false);
            new Registration();
        }
        else if (event.getSource()==btnreset)
        {
            jf.setVisible(false);
            new ResetPass();

        }

//                if (userValue.equals("Nikhil") && passValue.equals("1234"))
//                {  //if authentic, navigate user to a new page
//
//                    //create instance of the Home
//                    Home home = new Home();
//                    jf.setVisible(false);
//
//                    //make page visible to the user
//                    JOptionPane.showMessageDialog(null,"Login sucessfully");
//
//                    //create a welcome label and set it to the new page
//                    JLabel wel_label = new JLabel("Welcome: "+userValue);
//
//                }
//                else{
//                    //show error message
//                    JOptionPane.showMessageDialog(null,"Invalid user name and password :: please Provide valid user name and password");
//                }

    }
    public void imagebkg()
    {
        background = new JLabel(new ImageIcon("D:\\MY_DATA\\PROJECT_files\\V_P_S_16_Nov\\loginBkg.jpg")); // "D:\MY_DATA\PROJECT_files\V_P_S_16_Nov\loginBkg.jpg"
        jf.add(background);
        background.setLayout(new FlowLayout()); // If you want to add other components

        background.setVisible(true);
    }






//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String s=e.getActionCommand();
//        if(s.equals("Login"))
//        {
//            jf.setVisible(false);
//            H=new Home();
//            H.show();
//
//}
//        }







    //Main method
    public static void main(String[] args)
    {
        new LoginForm();
    }



}
