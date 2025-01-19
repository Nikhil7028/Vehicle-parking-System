package Test;
//

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;

public class DurationAmountCalculator extends JFrame
{
    private JTextField durationField;
    private JTextField amountField;

    public DurationAmountCalculator()
    {
        setTitle("Duration and Amount Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        durationField = new JTextField(10);
        amountField = new JTextField(10);
        amountField.setEditable(false); // Making the amount field uneditable

        durationField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateAmount();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateAmount();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not needed for plain text fields
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Duration Time: "));
        panel.add(durationField);
        panel.add(new JLabel("Amount: "));
        panel.add(amountField);

        add(panel);
        setVisible(true);
    }

    private void calculateAmount() {
        try {
            int duration = Integer.parseInt(durationField.getText());
            int amount = duration * 10;
            amountField.setText(Integer.toString(amount));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for duration.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            amountField.setText(""); // Clear the amount field upon invalid input
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DurationAmountCalculator();
            }
        });
    }
}
