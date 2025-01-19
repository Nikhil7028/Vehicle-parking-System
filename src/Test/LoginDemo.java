package Test;

//public class LoginDemo {
//}


import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;
        import java.sql.*;

public class LoginDemo implements ActionListener {
    static final String JDBC_URL = "jdbc:mysql://localhost:3308/vps";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "dbms";

    JFrame jf;
    JLabel label1, label2, lmes, background;
    JButton login;
    JTextField textfield1;
    JPasswordField passwordfield;
    String userValue = null;
    String passValue = null;

    public LoginDemo() {
        initComponents();
        imagebkg();
    }

    public void initComponents() {
        jf = new JFrame("Login");
        jf.setSize(800, 500);
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10)); // 4 rows, 1 column

        label1 = new JLabel("User Login");
        label1.setFont(new Font("Times New Roman", Font.BOLD, 24));
        panel.add(label1);

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        label2 = new JLabel("Username:");
        label2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        userPanel.add(label2);
        textfield1 = new JTextField(15);
        textfield1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        userPanel.add(textfield1);
        panel.add(userPanel);

        JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        label2 = new JLabel("Password:");
        label2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        passPanel.add(label2);
        passwordfield = new JPasswordField(15);
        passwordfield.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        passPanel.add(passwordfield);
        panel.add(passPanel);

        login = new JButton("Login");
        login.setFont(new Font("Times New Roman", Font.BOLD, 20));
        login.addActionListener(this);
        panel.add(login);

        lmes = new JLabel(" ");
        lmes.setFont(new Font("Times New Roman", Font.BOLD, 18));
        panel.add(lmes);

        jf.add(panel);
        jf.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == login) {
            userValue = textfield1.getText();
            passValue = new String(passwordfield.getPassword());

            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
                String query = "SELECT * FROM Admin WHERE NameAdmin = ? AND PassAdmin = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, userValue);
                preparedStatement.setString(2, passValue);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    System.out.println("Authentication successful! User found in the database.");
                    lmes.setText("Login successful. Welcome " + userValue);
                    lmes.setForeground(Color.GREEN);
                    jf.dispose(); // Close login window after successful login
                    //new Page(); // Open Home window or perform required *******************    delete page
                } else {
                    System.out.println("Invalid username or password.");
                    lmes.setText("Invalid username or password.");
                    lmes.setForeground(Color.RED);
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e);
            }
        }
    }
    public void imagebkg()
    {
        background = new JLabel(new ImageIcon("D:\\MY_DATA\\PROJECT_files\\V_P_S_16_Nov\\loginBkg.jpg")); // "D:\MY_DATA\PROJECT_files\V_P_S_16_Nov\loginBkg.jpg"
        jf.add(background);
        background.setLayout(new FlowLayout()); // If you want to add other components

        background.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginDemo();
    }
}
