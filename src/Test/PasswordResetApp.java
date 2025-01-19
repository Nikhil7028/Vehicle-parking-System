package Test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PasswordResetApp extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField newPasswordField;
    private JButton resetButton;
    private JRadioButton optionARadioButton;
    private JRadioButton optionBRadioButton;

    public PasswordResetApp() {
        setTitle("Password Reset");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        panel.add(usernameLabel);

        usernameField = new JTextField(15);
        panel.add(usernameField);

        JLabel newPasswordLabel = new JLabel("New Password:");
        panel.add(newPasswordLabel);

        newPasswordField = new JPasswordField(15);
        panel.add(newPasswordField);

        optionARadioButton = new JRadioButton("Option A");
        optionBRadioButton = new JRadioButton("Option B");

        ButtonGroup group = new ButtonGroup();
        group.add(optionARadioButton);
        group.add(optionBRadioButton);

        panel.add(optionARadioButton);
        panel.add(optionBRadioButton);

        resetButton = new JButton("Reset Password");
        resetButton.addActionListener(this);
        panel.add(resetButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            String username = usernameField.getText();
            char[] newPasswordChars = newPasswordField.getPassword();
            String newPassword = new String(newPasswordChars);

            String selectedOption = "";
            if (optionARadioButton.isSelected()) {
                selectedOption = "Option A";
            }
            else if (optionBRadioButton.isSelected()) {
                selectedOption = "Option B";
            }

            // Perform password reset logic here (this is just a placeholder)
            // Replace this with your actual password reset functionality
            System.out.println("Username: " + username);
            System.out.println("New Password: " + newPassword);
            System.out.println("Selected Option: " + selectedOption);

            // Display a message indicating successful password reset
            JOptionPane.showMessageDialog(this, "Password reset successful for user: " + username +
                    "\nSelected Option: " + selectedOption);

            // Clear fields after reset
            usernameField.setText("");
            newPasswordField.setText("");
            optionARadioButton.setSelected(false);
            optionBRadioButton.setSelected(false);
        }
    }

    public static void main(String[] args) {
        // Run the application
        SwingUtilities.invokeLater(() -> new PasswordResetApp());
    }
}
