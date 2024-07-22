package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.Year;

public class MainFrame extends JFrame {

    private int xMouse, yMouse;
    private JPanel header;
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
//        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBackground(new Color(238,238,238));
        mainPanel.setLayout(null);
        add(mainPanel);

        header = new JPanel();
        header.setBounds(0,0,600,40);
        header.setLayout(null);
        header.setOpaque(false);                //false so it does not paint the header by default
        mainPanel.add(header);

        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - xMouse, y - yMouse);
            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });

        btnExit = new JButton("X");
        btnExit.setBounds(550,0,50,40);
        btnExit.setFont(new Font(null, Font.PLAIN, 18));
        btnExit.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        btnExit.setBackground(new Color(238,238,238));
        btnExit.setHorizontalAlignment(SwingConstants.CENTER);
        btnExit.setFocusPainted(false);
        btnExit.setBorderPainted(false);
        header.add(btnExit);


        String description = "<html><body> Welcome to the reservation system of Hotel Alura!  </body></html>";
        labelTitle = new JLabel(description);
        labelTitle.setBounds(60, 50, 270, 40);
        labelTitle.setFont(new Font(null, Font.BOLD, 17));
        //labelTitle.setLayout(null);
        mainPanel.add(labelTitle);

        btnNewBooking = new JButton("New Reservation");
        btnNewBooking.setBounds(60, 150, 250, 50);
        btnNewBooking.setFont(new Font(null, Font.BOLD, 15));
        btnNewBooking.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));       //Changes the image of the cursor.
        btnNewBooking.setBackground(new Color(0, 141, 202));
        btnNewBooking.setForeground(new Color(255, 255, 255));
        btnNewBooking.setFocusPainted(false);
        btnNewBooking.setBorderPainted(false);
        btnNewBooking.setIcon(new ImageIcon(MainFrame.class.getResource("/images/icon-reservas.png")));
        btnNewBooking.setIconTextGap(20);
        mainPanel.add(btnNewBooking);

        btnDisplayBookings = new JButton("Show Reservations");
        btnDisplayBookings.setBounds(60, 200, 250, 50);
        btnDisplayBookings.setFont(new Font(null, Font.BOLD, 15));
        btnDisplayBookings.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        btnDisplayBookings.setBackground(new Color(0, 141, 202));
        btnDisplayBookings.setForeground(new Color(255, 255, 255));
        btnDisplayBookings.setBorderPainted(false);
        btnDisplayBookings.setIcon(new ImageIcon(MainFrame.class.getResource("/images/icon_search.png")));
        btnDisplayBookings.setIconTextGap(20);
        mainPanel.add(btnDisplayBookings);


        /**
         * This panel contains the JLabel below (lblCopyR)
         */
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 335, 600, 60);
        panel_1.setBackground(new Color(0, 141, 202));
        panel_1.setLayout(null);
        mainPanel.add(panel_1);

        //To get the current year and print the whole message in the About! panel
        int currentYear = Year.now().getValue();
        String devBy = "<html><body>Developed by Julian Lopez © " + currentYear +
                "<p>https://github.com/Julopvel</p></body></html>";
        JLabel lblCopyR = new JLabel(devBy);
        lblCopyR.setBounds(180, 5, 284, 40);
        lblCopyR.setForeground(new Color(238, 238,238));
        lblCopyR.setFont(new Font("Roboto", Font.BOLD, 15));
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
                btnNewBooking.setBackground(new Color(0, 141, 202));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnNewBooking.setBackground(new Color(0, 134, 190));
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
                btnDisplayBookings.setBackground(new Color(0, 141, 202));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnDisplayBookings.setBackground(new Color(0, 134, 190));
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
                btnExit.setBackground(new Color(238,238,238));
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
