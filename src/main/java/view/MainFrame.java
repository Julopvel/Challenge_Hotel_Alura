package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {

    private JButton btnNewBooking, btnDisplayBookings;
    private JLabel labelTitle;
    private JButton btnExit;


    public static void main(String[] args) {
        new MainFrame();
    }


    public MainFrame(){

        initComponents();
        btnActions();
        initFrame();
    }


    private void initFrame() {
        setTitle("Main");
        setLayout(null);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);   //Removes the upper bar of the window.
        setVisible(true);

    }

    private void initComponents() {
        //This mainPanel (JPanel) includes within all the other components (btns, labels,..)
        JPanel mainPanel = new JPanel();
        mainPanel.setSize(600,400);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);
        add(mainPanel);


        String description = "<html><body> Welcome to the reservation system of Hotel Alura!  </body></html>";
        labelTitle = new JLabel(description);
        labelTitle.setBounds(60, 50, 270, 40);
        labelTitle.setFont(new Font(null, Font.BOLD, 17));
        //labelTitle.setLayout(null);
        mainPanel.add(labelTitle);

        btnNewBooking = new JButton("New Reservation");
        btnNewBooking.setBounds(80, 200, 200, 50);
        btnNewBooking.setFont(new Font(null, Font.BOLD, 15));
        btnNewBooking.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));       //Changes the image of the cursor.
//        btnNewBooking.setBackground(Color.WHITE);
        btnNewBooking.setBackground(new Color(12, 138, 199));
        btnNewBooking.setForeground(Color.BLACK);
        btnNewBooking.setFocusPainted(false);
        btnNewBooking.setBorderPainted(false);
        mainPanel.add(btnNewBooking);

        btnDisplayBookings = new JButton("Show Reservations");
        btnDisplayBookings.setBounds(80, 250, 200, 50);
        btnDisplayBookings.setFont(new Font(null, Font.BOLD, 15));
        btnDisplayBookings.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
//        btnDisplayBookings.setBackground(Color.WHITE);
        btnDisplayBookings.setBackground(new Color(12, 138, 199));
        btnDisplayBookings.setForeground(Color.BLACK);
        btnDisplayBookings.setBorderPainted(false);
        mainPanel.add(btnDisplayBookings);

        btnExit = new JButton("X");
        btnExit.setBounds(550,0,50,40);
        btnExit.setFont(new Font(null, Font.BOLD, 15));
        btnExit.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        btnExit.setBackground(Color.WHITE);
        btnExit.setFocusPainted(false);
        btnExit.setBorderPainted(false);
        //btnExit.setForeground();
        mainPanel.add(btnExit);

        /**
         * This panel contains the JLabel below (lblCopyR)
         */
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 335, 600, 30);
        panel_1.setBackground(new Color(12, 138, 199));
        panel_1.setLayout(null);
        mainPanel.add(panel_1);

        JLabel lblCopyR = new JLabel("Developed by: Julopvel © 2024");
        lblCopyR.setBounds(180, 5, 284, 19);
        lblCopyR.setForeground(new Color(240, 248, 255));
        lblCopyR.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel_1.add(lblCopyR);

    }

    private void btnActions() {
        btnNewBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Booking();
                System.out.println("Creando nueva reserva");
            }
        });

        btnNewBooking.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnNewBooking.setBackground(new Color(118, 187, 223));
                btnNewBooking.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnNewBooking.setBackground(new Color(12, 138, 199));
//                btnNewBooking.setBackground(Color.WHITE);
                btnNewBooking.setForeground(Color.BLACK);
            }
        });

        btnDisplayBookings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayBookings();
                System.out.println("Mostrando las reservas");
            }
        });

        btnDisplayBookings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
//                btnDisplayBookings.setBackground(new Color(118, 187, 223));
                btnDisplayBookings.setForeground(Color.WHITE);
                btnDisplayBookings.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnDisplayBookings.setBackground(new Color(12, 138, 199));
//                btnDisplayBookings.setBackground(Color.WHITE);
                btnDisplayBookings.setForeground(Color.BLACK);
            }
        });


        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnExit.setBackground(Color.RED);
                btnExit.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnExit.setBackground(Color.WHITE);
                btnExit.setForeground(Color.BLACK);
            }
        });
    }

    private void DisplayBookings() {
        DisplayBookingsFrame displayBookingsFrame = new DisplayBookingsFrame();
        displayBookingsFrame.setVisible(true);
        dispose();
    }

    private void Booking(){
        BookingFrame bookingFrame = new BookingFrame();
        bookingFrame.setVisible(true);
        dispose();
    }



}
