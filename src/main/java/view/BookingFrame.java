package view;

import com.toedter.calendar.JDateChooser;
import jdbc.controller.BookingController;
import jdbc.model.Booking;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

public class BookingFrame extends JFrame {
    private JLabel labelCheckIn, labelCheckOut, labelBookingPrice, labelPaymentMethod;
    private JDateChooser dateCheckIn, dateCheckOut;
    private JFormattedTextField textBookingPrice;
    private JComboBox<String> comboPaymentMethod;
    private JButton btnOK;
    private JButton btnReturn;

    private BookingController bookingController;


    public static void main(String[] args) {
        new BookingFrame();
    }


    public BookingFrame(){
        //Instantiating BookingController class. This way you will be able to call BookingDAO (CRUD) methods.
        this.bookingController = new BookingController();

        Container container = getContentPane();

        initComponents(container);
        btnActions();
        initFrame();
    }

    private void initFrame(){
        setTitle("Booking");
        setLayout(null);
        setSize(600, 450);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents(Container container){
        //container that includes within the rest of components (labels, buttons, comboBox, ...)
        container.setBackground(Color.WHITE);


        int posX = 270;

        labelCheckIn = new JLabel("Check in");
        labelCheckIn.setBounds(posX, 80, 240, 15);
        labelCheckIn.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelCheckIn.setForeground(Color.BLACK);

        labelCheckOut = new JLabel("Check out");
        labelCheckOut.setBounds(posX, 140, 240, 15);
        labelCheckOut.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelCheckOut.setForeground(Color.BLACK);

        labelBookingPrice = new JLabel("Price");
        labelBookingPrice.setBounds(posX, 200, 240, 15);
        labelBookingPrice.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelBookingPrice.setForeground(Color.BLACK);

        labelPaymentMethod = new JLabel("Payment Method");
        labelPaymentMethod.setBounds(posX, 260, 240, 15);
        labelPaymentMethod.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelPaymentMethod.setForeground(Color.BLACK);


        dateCheckIn = new JDateChooser();
        dateCheckIn.setBounds(posX, 100, 270, 30);
        dateCheckIn.setFont(new Font("Roboto", Font.PLAIN, 16));
        dateCheckIn.setDateFormatString("yyyy-MM-dd");
        dateCheckIn.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));

        dateCheckOut = new JDateChooser();
        dateCheckOut.setBounds(posX, 160, 270, 30);
        dateCheckOut.setFont(new Font("Roboto", Font.PLAIN, 16));
        dateCheckOut.setDateFormatString("yyyy-MM-dd");
        dateCheckOut.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));

        dateCheckOut.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                bookingPrice(dateCheckIn, dateCheckOut);
            }
        });


        textBookingPrice = new JFormattedTextField();
        textBookingPrice.setBounds(posX, 220, 270, 30);
        textBookingPrice.setFont(new Font("Roboto", Font.PLAIN, 16));
        textBookingPrice.setEditable(false);
        //textBookingPrice.setBorder(javax.swing.BorderFactory.createEmptyBorder());


        String[] options = new String[]{"Choose an option", "Credit card", "Debit card", "Cash"};
        comboPaymentMethod = new JComboBox(options);
        comboPaymentMethod.setBounds(posX, 280, 270, 30);
        comboPaymentMethod.setFont(new Font("Roboto", Font.PLAIN, 16));

        btnOK = new JButton("NEXT");
        btnOK.setBounds(posX + 150, 340, 120, 30);
        btnOK.setFont(new Font("Roboto", Font.PLAIN, 18));
        btnOK.setForeground(Color.BLACK);
        btnOK.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));

        btnReturn = new JButton("<");
        btnReturn.setBounds(0,0,50,40);
        btnReturn.setFont(new Font("Roboto", Font.BOLD, 15));
        btnReturn.setForeground(Color.BLACK);
        btnReturn.setBackground(Color.WHITE);
        btnReturn.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        btnReturn.setFocusPainted(false);
        btnReturn.setBorderPainted(false);



        container.add(labelCheckIn);
        container.add(labelCheckOut);
        container.add(labelBookingPrice);
        container.add(labelPaymentMethod);
        container.add(dateCheckIn);
        container.add(dateCheckOut);
        container.add(textBookingPrice);
        container.add(comboPaymentMethod);

        container.add(btnOK);
        container.add(btnReturn);
//        container.add(btnCancel);
//        container.add(btnClear);

    }

    private void btnActions(){
        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainFrame().setVisible(true);
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

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dateCheckIn.getDate() != null && dateCheckOut.getDate() != null) {
                    //Dentro del método save() se crea la ventana GuestsRegisterFrame y se elimina esta actual, i.e BookingFrame
                    save();

                }
                else {
                    JOptionPane.showMessageDialog(
                            null, "No empty fields allowed!", "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void save() {

        String dateCheckInString = ((JTextField) dateCheckIn.getDateEditor().getUiComponent()).getText();
        String dateCheckOutString = ((JTextField) dateCheckOut.getDateEditor().getUiComponent()).getText();

//        System.out.println("Class checkIn: " + Date.valueOf(dateCheckInString).getClass());
//        System.out.println("Value checkIn: " + Date.valueOf(dateCheckInString));
//        System.out.println("Class checkOut: " + Date.valueOf(dateCheckOutString).getClass());
//        System.out.println("Value checkIn: " + Date.valueOf(dateCheckOutString));
//        System.out.println("Class price: " + textBookingPrice.getValue().getClass());
//        System.out.println("Value price: " + textBookingPrice.getValue());
//        System.out.println("Class payMethod: " + comboPaymentMethod.getSelectedItem().toString().getClass());
//        System.out.println("Value payMethod: " + comboPaymentMethod.getSelectedItem().toString());


        Booking booking = new Booking(
                java.sql.Date.valueOf(dateCheckInString),
                Date.valueOf(dateCheckOutString),
                (Integer) textBookingPrice.getValue(),
                comboPaymentMethod.getSelectedItem().toString()
        );

        bookingController.create(booking);

        System.out.println("Booking #: " + booking.getId() + " successfully created");

        GuestsRegisterFrame guestsRegisterFrame = new GuestsRegisterFrame(booking.getId());
        guestsRegisterFrame.setVisible(true);
        dispose();

    }

    private void bookingPrice(JDateChooser dateCheckIn, JDateChooser dateCheckOut) {
        if (dateCheckIn.getDate() != null && dateCheckOut.getDate() != null) {
            Calendar checkIn = dateCheckIn.getCalendar();
            Calendar checkOut = dateCheckOut.getCalendar();
            int days = -1; // Usamos -1 para contar a partir del dia siguiente
            int valuePerDay = 510;
            int totalAmount;

            while (checkIn.before(checkOut) || checkIn.equals(checkOut)) {
                days++;
                checkIn.add(Calendar.DATE, 1); //dias a ser aumentados
            }
            totalAmount = days * valuePerDay;

            textBookingPrice.setValue(totalAmount);
        }
    }


    private void clearAll(){
        this.dateCheckIn.setDate(null);
        this.dateCheckOut.setDate(null);
        this.textBookingPrice.setText("");
        this.comboPaymentMethod.setSelectedIndex(0);
    }

}
