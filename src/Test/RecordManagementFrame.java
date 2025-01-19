package Test;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class RecordManagementFrame extends JFrame implements ActionListener {
//
//    public RecordManagementFrame() {
//        super("Record Management");
//        initialize();
//    }
//
//    private void initialize() {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(400, 150); // Set frame size
//
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
//        buttonPanel.setBackground(Color.WHITE); // Set background color if needed
//
//        // Create buttons
//        JButton insertButton = createButton("Insert record");
//        JButton updateButton = createButton("Update record");
//        JButton deleteButton = createButton("Delete record");
//        JButton allRecordsButton = createButton("All records");
//
//        // Add buttons to the panel
//        buttonPanel.add(insertButton);
//        buttonPanel.add(updateButton);
//        buttonPanel.add(deleteButton);
//        buttonPanel.add(allRecordsButton);
//
//        add(buttonPanel);
//        setVisible(true);
//    }
//
//    // Method to create styled buttons
//    private JButton createButton(String label) {
//        JButton button = new JButton(label);
//        button.setFont(new Font("Arial", Font.BOLD, 14));
//        button.setForeground(Color.WHITE);
//        button.setBackground(Color.BLUE);
//        button.setFocusPainted(false);
//        button.addActionListener(this); // Add action listener for button clicks
//        return button;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        // Action handling for button clicks goes here
//        if (e.getActionCommand().equals("Insert record")) {
//            // Perform action for Insert record button
//            System.out.println("Insert record button clicked");
//        } else if (e.getActionCommand().equals("Update record")) {
//            // Perform action for Update record button
//            System.out.println("Update record button clicked");
//        } else if (e.getActionCommand().equals("Delete record")) {
//            // Perform action for Delete record button
//            System.out.println("Delete record button clicked");
//        } else if (e.getActionCommand().equals("All records")) {
//            // Perform action for All records button
//            System.out.println("All records button clicked");
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new RecordManagementFrame());
//    }
//}



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RecordManagementFrame extends JFrame {

    public RecordManagementFrame() {
        super("Record Operations");
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300); // Set frame size

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;

        JButton insertButton = new JButton("Insert Record");
        insertButton.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(insertButton, gbc);

        JButton updateButton = new JButton("Update Record");
        updateButton.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(updateButton, gbc);

        JButton deleteButton = new JButton("Delete Record");
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(deleteButton, gbc);

        JButton allRecordsButton = new JButton("All Records");
        allRecordsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(allRecordsButton, gbc);

        add(panel);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RecordManagementFrame());
    }
}
