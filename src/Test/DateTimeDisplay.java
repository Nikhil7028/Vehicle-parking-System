package Test;

//public class DateTimeDisplay {
//}


import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeDisplay extends JFrame {

    private JLabel dateTimeLabel;

    public DateTimeDisplay() {
        setTitle("Current Date and Time");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setLocationRelativeTo(null);

        dateTimeLabel = new JLabel("", SwingConstants.CENTER);
        dateTimeLabel.setFont(new Font("Arial", Font.BOLD, 20));

        updateDateTime(); // Initially display current date and time

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(dateTimeLabel, BorderLayout.CENTER);

        add(panel);
        setVisible(true);

        // Update date and time every second
        Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();
    }

    private void updateDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(new Date());
        dateTimeLabel.setText("Current Date and Time: " + formattedDate);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DateTimeDisplay());
    }
}
