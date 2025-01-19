package Test;

//public class RegistrationForm {
//}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegistrationForm extends JFrame {

    private JTextField usernameField, emailField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegistrationForm() {
        super("Registration Form");
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        panel.add(usernameLabel);
        usernameField = new JTextField(20);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);
        passwordField = new JPasswordField(20);
        panel.add(passwordField);

        JLabel emailLabel = new JLabel("Email:");
        panel.add(emailLabel);
        emailField = new JTextField(20);
        panel.add(emailField);

        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerButtonClicked();
            }
        });
        panel.add(registerButton);

        add(panel);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void registerButtonClicked() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String email = emailField.getText();

        // Perform registration logic here
        // For example: store the registration details in a database

        JOptionPane.showMessageDialog(this, "Registration Successful!\nUsername: " + username + "\nEmail: " + email);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm());
    }
}


