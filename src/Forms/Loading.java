package Forms;//this is loading window for project

import java.awt.Color;

import javax.swing.*;

class Loading
{
    static JProgressBar b;
    public Loading()
    {
//        System.out.println("i am in loading");
        JFrame frame1=new JFrame("Loading...");
        frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // creating a panel using the JPanel class. It provides
        // space in which an application can attach any other component.
        JPanel p = new JPanel( ) ;
        // creating a progress bar using JProgressBar constructor

        b = new JProgressBar();


        /* setting the initial value to 0 */
        b.setValue(0);

        // setting StringPainted to true to make the progress bar
        // render a string
        b.setStringPainted(true);


        // add a progressbar using add( ) function
        p.add(b);

        // add a panel using add( ) function
        frame1.add( p ) ;

        //set frame size and visibility
        frame1.setSize(500,500);
        frame1.setVisible(true);
        p.setBackground(Color.LIGHT_GRAY);

        //call fill() method
        fill();


//        frame1.setVisible(false);  // set form invisible
//        ********************************************************Call login form ----->
        frame1.setVisible(false);
        new LoginForm();

    }

    // function to dynamically increase progress
    static public void fill()
    {
        int i=0;
        try
        {
            while (i<=100)
            {
//                fill the menu bar to define the value
                b.setValue(i+1);
                Thread.sleep(400);
//                i+=1;
                i+=9;

            }

//            new LoginForm();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }



    }

    public static void main(String[] args) {
        new Loading();
    }


}

