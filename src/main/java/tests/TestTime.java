package tests;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Instant;

public class TestTime extends JFrame {

    private JDateChooser dateTest;
    private JButton btnOK;

    public TestTime(){
        setLayout(null);
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        dateTest = new JDateChooser();
        dateTest.setBounds(10, 25, 265, 20);
        dateTest.setDateFormatString("yyyy-MM-dd");

        btnOK = new JButton("OK");
        btnOK.setBounds(10, 225, 100, 20);

        add(dateTest);
        add(btnOK);

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = ((JTextField)dateTest.getDateEditor().getUiComponent()).getText();
                //Date date2 = Date.valueOf(String.valueOf(dateTest.getDate()));   me genera expection


                System.out.println("getDate: " + dateTest.getDate());
                System.out.println("getDateFormatString: " + dateTest.getDateFormatString());
                System.out.println(date);
               // System.out.println(date2);

            }
        });
    }

    public static void main(String[] args) {
        TestTime testTime = new TestTime();

//        testTime.setLayout(null);
//        testTime.setSize(400, 400);
        testTime.setVisible(true);

    }

}


