package Test;

//public class MobileNumberFieldExample {
//}


import javax.swing.*;
import java.awt.event.*;

public class MobileNumberFieldExample extends JFrame {
    private JTextField mobileNumberField;

    public MobileNumberFieldExample() {
        super("Mobile Number Field");
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Mobile Number:");
        mobileNumberField = new JTextField(10); // Adjust the column size as needed

        // Allow only numeric input
        mobileNumberField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });

        panel.add(label);
        panel.add(mobileNumberField);
        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MobileNumberFieldExample());
    }
}
