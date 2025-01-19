package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Home implements ActionListener {
    public JFrame jfH;
    private JButton btninsert, btnupdate, btndelete, btndisplay, btnout, btnManagePrice, btnCap;

    public Home() {
        jfH = new JFrame("Home page");
        jfH.setLayout(null); // Set layout manager
        jfH.setSize(800, 800); // Set size instead of using bounds
        jfH.getContentPane().setBackground(Color.ORANGE);
        btnoption();
        jfH.setVisible(true);
    }

    void btnoption() {
        btninsert = new JButton("Insert record");
        btninsert.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btninsert.setBounds(90,100,150,30);
        btninsert.addActionListener(this);
        jfH.add(btninsert);

        btnManagePrice = new JButton("Manage price");
        btnManagePrice.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnManagePrice.setBounds(600,100,150,30);
        btnManagePrice.setForeground(Color.WHITE);
        btnManagePrice.setBackground(Color.blue);
        btnManagePrice.addActionListener(this);
        jfH.add(btnManagePrice);

        btnupdate = new JButton("Update");
        btnupdate.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnupdate.setBounds(345,260,150,30);
        btnupdate.addActionListener(this);
        jfH.add(btnupdate);

        btndelete = new JButton("Delete");
        btndelete.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btndelete.setBounds(345,380,150,30);
        btndelete.addActionListener(this);
        jfH.add(btndelete);


        btnCap = new JButton ("Manage capacity");
        btnCap.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnCap.addActionListener(this);
        btnCap.setBounds(90,600,190,30);
        btnCap.setBackground(Color.red);
        btnCap.setForeground(Color.WHITE);
        jfH.add(btnCap);

        btndisplay = new JButton("Display");
        btndisplay.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btndisplay.setBounds(600,600,150,30);
        btndisplay.addActionListener(this);
        jfH.add(btndisplay);



        btnout = new JButton ("Logout");
        btnout.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnout.addActionListener(this);
        btnout.setBounds(680,20,100,30);
        btnout.setBackground(Color.red);
        btnout.setForeground(Color.WHITE);
        jfH.add(btnout);




//        jfH.setBackground(Color.ORANGE);
        jfH.setBackground(Color.GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btninsert) {
            jfH.setVisible(false);
             new InsertRc(); // Handle InsertRc instantiation
        }
        else if(event.getSource()==btndisplay)
        {
            jfH.setVisible(false);

            Display d=new Display();
            d.call();

        }
        else if (event.getSource()==btnout)
        {
            jfH.setVisible(false);
            new LoginForm();

        }
        else if (event.getSource() ==btndelete)
        {
            jfH.setVisible(false);
            new DeleteRC();
        }
        else if (event.getSource()==btnManagePrice)
        {
            jfH.setVisible(false);
            new PriceUpdate();

        } else if (event.getSource()==btnCap)
        {
            jfH.setVisible(false);
            new UpdateCapacity();

        }
        else if (event.getSource()==btnupdate)
        {
            jfH.setVisible(false);
            new UpdateRc();

        }
    }

    public static void main(String[] args) {
        new Home();
    }
}
