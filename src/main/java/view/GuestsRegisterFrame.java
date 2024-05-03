package view;

import com.toedter.calendar.JDateChooser;
import jdbc.controller.GuestsController;
import jdbc.model.Booking;
import jdbc.model.Guests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

public class GuestsRegisterFrame extends JFrame {

    private JLabel labelFirstName, labelLastName, labelBirthDate, labelPhone, labelBookingId;
    private JTextField textFirstName, textLastName, textPhone, textBookingId;
    private JDateChooser dateBirth;
    private JButton btnOk;
    private  JButton btnReturn;
    private JButton btnExit;

    private GuestsController guestsController;


    public static void main(String[] args) {
        //bookindId = 0 is the default value
        GuestsRegisterFrame frame = new GuestsRegisterFrame(0);
        frame.setVisible(true);
    }


    public GuestsRegisterFrame(int bookingId){
        //Instantiating GuestController class. This way you will be able to call GuestsDAO (CRUD) methods.
        this.guestsController = new GuestsController();
        //this.id = bookingId;

        System.out.println("bookingId que viene desde BookingFrame: " + bookingId);
        initComponents(bookingId);
        btnActions();
        initFrame();

    }

     private void initFrame() {
        setTitle("Guests Registration");
        setLayout(null);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initComponents(int bookingId) {
        JPanel mainPanel = new JPanel();
        mainPanel.setSize(600,500);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);
        add(mainPanel);

        int posX = 60;

        labelFirstName = new JLabel("First Name");
        labelFirstName.setBounds(posX, 80, 240, 15);
        labelFirstName.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelFirstName.setForeground(Color.BLACK);

        labelLastName = new JLabel("Last Name");
        labelLastName.setBounds(posX, 140, 240, 15);
        labelLastName.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelLastName.setForeground(Color.BLACK);

        labelBirthDate = new JLabel("Birth Date");
        labelBirthDate.setBounds(posX, 200, 240, 15);
        labelBirthDate.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelBirthDate.setForeground(Color.BLACK);

        labelPhone = new JLabel("Phone Number");
        labelPhone.setBounds(posX, 260, 240, 15);
        labelPhone.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelPhone.setForeground(Color.BLACK);

        labelBookingId = new JLabel("Booking ID");
        labelBookingId.setBounds(posX,320, 240, 15);
        labelBookingId.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelBookingId.setForeground(Color.BLACK);

        textFirstName = new JTextField();
        textFirstName.setBounds(posX, 100, 270, 30);
        textFirstName.setFont(new Font("Roboto", Font.PLAIN, 16));
        textFirstName.setForeground(Color.BLACK);

        textLastName = new JTextField();
        textLastName.setBounds(posX, 160, 270, 30);
        textLastName.setFont(new Font("Roboto", Font.PLAIN, 16));
        textLastName.setForeground(Color.BLACK);

        dateBirth = new JDateChooser();
        dateBirth.setBounds(posX, 220, 270, 30);
        dateBirth.setDateFormatString("yyyy-MM-dd");
        dateBirth.setFont(new Font("Roboto", Font.PLAIN, 16));
        dateBirth.setForeground(Color.BLACK);
        dateBirth.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));


        textPhone = new JTextField();
        textPhone.setBounds(posX, 280, 270, 30);
        textPhone.setFont(new Font("Roboto", Font.PLAIN, 16));
        textPhone.setForeground(Color.BLACK);


        textBookingId = new JTextField();
        textBookingId.setBounds(posX,340,270,30);
        textBookingId.setFont(new Font("Roboto", Font.PLAIN, 16));
        textBookingId.setForeground(Color.BLACK);
        textBookingId.setEditable(false);
        String id = String.valueOf(bookingId);
        textBookingId.setText(id);

        btnOk = new JButton("SAVE");
        btnOk.setBounds(posX, 400, 120, 30);
        btnOk.setFont(new Font("Roboto", Font.BOLD, 18));
        btnOk.setForeground(Color.BLACK);
        btnOk.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));

        btnReturn = new JButton("<");
        btnReturn.setBounds(0,0,50,40);
        btnReturn.setFont(new Font("Roboto", Font.BOLD, 15));
        btnReturn.setForeground(Color.BLACK);
        btnReturn.setBackground(Color.WHITE);
        btnReturn.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        btnReturn.setFocusPainted(false);
        btnReturn.setBorderPainted(false);

        btnExit = new JButton("X");
        btnExit.setBounds(550,0,50,40);
        btnExit.setFont(new Font(null, Font.BOLD, 15));
        btnExit.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        btnExit.setBackground(Color.WHITE);
//        btnExit.setFocusPainted(false);
        btnExit.setBorderPainted(false);
        //btnExit.setForeground();
        mainPanel.add(btnExit);

        mainPanel.add(labelFirstName);
        mainPanel.add(labelLastName);
        mainPanel.add(labelBirthDate);
        mainPanel.add(labelPhone);
        mainPanel.add(labelBookingId);

        mainPanel.add(textFirstName);
        mainPanel.add(textLastName);
        mainPanel.add(dateBirth);
        mainPanel.add(textPhone);
        mainPanel.add(textBookingId);

        mainPanel.add(btnOk);
        mainPanel.add(btnReturn);

    }

    private void btnActions() {
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textFirstName.getText().isBlank() && !textLastName.getText().isBlank() &&
                        !textPhone.getText().isBlank() && dateBirth.getDate() != null){
                    save();
                    new MainFrame().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(
                            null, "No empty fields allowed!", "", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookingFrame().setVisible(true);
                dispose();
            }
        });

        btnReturn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnReturn.setBackground(Color.BLACK);
                btnReturn.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnReturn.setBackground(Color.WHITE);
                btnReturn.setForeground(Color.BLACK);
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

    private void save(){
        String dateBirthString = ((JTextField)dateBirth.getDateEditor().getUiComponent()).getText();

        Guests guests = new Guests(
                textFirstName.getText(),
                textLastName.getText(),
                Date.valueOf(dateBirthString),
                textPhone.getText(),
                Integer.parseInt(textBookingId.getText())
        );

        guestsController.create(guests);
        System.out.println("Guest #: " + guests.getId() + " successfully created");

    }

}
