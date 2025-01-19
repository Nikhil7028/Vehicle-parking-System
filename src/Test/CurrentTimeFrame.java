package Test;

//public class CurrentTimeFrame {
//}


import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTimeFrame extends JFrame {

    private JLabel timeLabel;

    public CurrentTimeFrame() {
        super("Display Current Time");
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        updateTime();
        add(timeLabel);

        // Refresh time every second (1000 milliseconds)
        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateTime() {
        Date now = new Date(); // Get current time
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); // Format time as HH:mm:ss
        String currentTime = sdf.format(now); // Format date to string
        timeLabel.setText("Current Time: " + currentTime);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrentTimeFrame());
    }
}
