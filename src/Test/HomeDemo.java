package Test;

//public class HomeDemo {
//}


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class HomeDemo implements ActionListener {
    public JFrame jfH;
    private JButton btninsert, btnupdate, btndelete, btndisplay;

    public HomeDemo() {
        jfH = new JFrame("Home page");
        jfH.setLayout(new FlowLayout()); // Set layout manager
        jfH.setSize(500, 500); // Set size instead of using bounds
        btnoption();
        jfH.setVisible(true);
    }

    void btnoption() {
        btninsert = new JButton("Insert record");
        btninsert.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btninsert.addActionListener(this);
        jfH.add(btninsert);

        btnupdate = new JButton("Update");
        btnupdate.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnupdate.addActionListener(this);
        jfH.add(btnupdate);

        btndelete = new JButton("Delete");
        btndelete.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btndelete.addActionListener(this);
        jfH.add(btndelete);

        btndisplay = new JButton("Display");
        btndisplay.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btndisplay.addActionListener(this);
        jfH.add(btndisplay);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btninsert) {
            jfH.setVisible(false);
            // new InsertRc(); // Handle InsertRc instantiation
        }
    }

    public static void main(String[] args) {
        new HomeDemo();
    }
}
