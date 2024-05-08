package view;

import jdbc.controller.BookingController;
import jdbc.controller.GuestsController;
import jdbc.model.Guests;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

public class DisplayBookingsFrame extends JFrame {

    private JTable tableBookings, tableGuests;
    private DefaultTableModel tableModelBooking, tableModelGuests;
    private JButton btnEdit, btnDelete, btnReturn;
    private JTabbedPane panelSelection;
    private BookingController bookingController;
    private GuestsController guestsController;
    private JLabel labelTitle;
    private JTextField textSearch;
    private JButton btnSearch;


    public static void main(String[] args) {
        new DisplayBookingsFrame();
    }


    public DisplayBookingsFrame(){
        //Instantiating BookingController class. This way you will be able to call BookingDAO methods.
        this.bookingController = new BookingController();
        //Instantiating GuestsController class. This way you will be able to call GuestDAO methods.
        this.guestsController = new GuestsController();

        initComponents();
        btnActions();
        initFrame();

    }

    private void initFrame() {
        setTitle("Search Engine");
        setLayout(null);
        setSize(900, 570);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setSize(900,570);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);
        add(mainPanel);

        labelTitle = new JLabel("SEARCH ENGINE");
        labelTitle.setBounds(340, 40, 190, 31);
        labelTitle.setFont(new Font("Roboto", Font.BOLD, 19));
        mainPanel.add(labelTitle);

        textSearch = new JTextField();
        textSearch.setBounds(600, 100, 120, 31);
        textSearch.setFont(new Font("Roboto", Font.PLAIN, 16));
        mainPanel.add(textSearch);

        btnSearch = new JButton("SEARCH");
        btnSearch.setBounds(750,100,120,30);
        btnSearch.setFont(new Font("Roboto", Font.PLAIN, 18));
        btnSearch.setForeground(Color.BLACK);
        btnSearch.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(btnSearch);



        // Creates a tabbed pane (panel con pestañas). Bookings & Guests tables will be added to it.
        panelSelection = new JTabbedPane();
        panelSelection.setBounds(20, 130, 865, 328);
        panelSelection.setFont(new Font("Roboto", Font.BOLD, 16));
        mainPanel.add(panelSelection);

        //Creating a new table to list the info of Bookings within MySQL DB
        tableBookings = new JTable();
        tableBookings.setFont(new Font("Roboto", Font.PLAIN, 16));
        panelSelection.addTab("Bookings", tableBookings);
        tableModelBooking = (DefaultTableModel) tableBookings.getModel();

        //No matter if addColumn() is null, empty String or any String, as long as it has one of these, it will add a column to the model.
        tableModelBooking.addColumn("id");
        tableModelBooking.addColumn("check_in");
        tableModelBooking.addColumn("check_out");
        tableModelBooking.addColumn("price");
        tableModelBooking.addColumn("payment_method");

        tableBookings.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        fillBookingsTable();


        tableGuests = new JTable();
        tableGuests.setFont(new Font("Roboto", Font.PLAIN, 16));
        panelSelection.addTab("Guests", tableGuests);

        tableModelGuests = (DefaultTableModel) tableGuests.getModel();

        tableModelGuests.addColumn("id");
        tableModelGuests.addColumn("first_name");
        tableModelGuests.addColumn("last_name");
        tableModelGuests.addColumn("birth_date");
        tableModelGuests.addColumn("phone_number");
        tableModelGuests.addColumn("booking_id");

        //tableGuests.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        fillGuestsTable();


        btnEdit = new JButton("EDIT");
        btnEdit.setBounds(600, 480, 120, 30);
        btnEdit.setFont(new Font("Roboto", Font.PLAIN, 18));
        btnEdit.setForeground(Color.BLACK);
        btnEdit.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));

        btnDelete = new JButton("DELETE");
        btnDelete.setBounds(750,480,120,30);
        btnDelete.setFont(new Font("Roboto", Font.PLAIN, 18));
        btnDelete.setForeground(Color.BLACK);
        btnDelete.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));

        btnReturn = new JButton("<");
        btnReturn.setBounds(0,0,50,40);
        btnReturn.setFont(new Font("Roboto", Font.BOLD, 15));
        btnReturn.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        btnReturn.setForeground(Color.BLACK);
        btnReturn.setBackground(Color.WHITE);
        btnReturn.setFocusPainted(false);
        btnReturn.setBorderPainted(false);


        mainPanel.add(btnEdit);
        mainPanel.add(btnDelete);
        mainPanel.add(btnReturn);
    }


    private void btnActions() {
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTables();
                if (textSearch.getText().isBlank()){
                    fillBookingsTable();
                    fillGuestsTable();
                } else {
                    fillBookingsTableId();
                    fillGuestsTableId();
                }
            }
        });



        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRow();
                clearTables();
                fillBookingsTable();
                fillGuestsTable();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit();
                clearTables();
                fillBookingsTable();
                fillGuestsTable();
            }
        });


        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainFrame().setVisible(true);
                dispose();
            }
        });

        btnReturn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

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
    }


    private void clearTables(){
        //TODO probar tableModelGuests.setRowCount(0) en vez de: tableModelGuests.getDataVector().clear();
        //Para etse caso funcionó mejor tableModelGuests.setRowCount(0);

        //tableModelGuests.getDataVector().clear();
        tableModelGuests.setRowCount(0);
        tableModelBooking.setRowCount(0);
    }

    private void fillGuestsTable() {
        var guestsList = this.guestsController.list();
        for (Guests guests : guestsList) {
            tableModelGuests.addRow(new Object[]{
                    guests.getId(),
                    guests.getFirstName(),
                    guests.getLastName(),
                    guests.getBirthDate(),
                    guests.getPhoneNumber(),
                    guests.getBookingId()
            });
        }
    }

    private void fillGuestsTableId(){
        var guestsListId = this.guestsController.listId(Integer.parseInt(textSearch.getText()));
        for (Guests guests : guestsListId) {
            tableModelGuests.addRow(new Object[]{
                    guests.getId(),
                    guests.getFirstName(),
                    guests.getLastName(),
                    guests.getBirthDate(),
                    guests.getPhoneNumber(),
                    guests.getBookingId()
            });
        }
    }

    private void fillBookingsTable() {
        var bookingList = this.bookingController.list();
        bookingList.forEach(booking -> tableModelBooking.addRow(new Object[]{
                booking.getId(),
                booking.getCheckIn(),
                booking.getCheckOut(),
                booking.getPrice(),
                booking.getPaymentMethod()
        }));
    }

    private void fillBookingsTableId(){
        var bookingListId = this.bookingController.listId(Integer.parseInt(textSearch.getText()));
        bookingListId.forEach(booking -> tableModelBooking.addRow(new Object[]{
                booking.getId(),
                booking.getCheckIn(),
                booking.getCheckOut(),
                booking.getPrice(),
                booking.getPaymentMethod()
        }));
    }

    private void deleteRow() {
        int rowBookings = tableBookings.getSelectedRow();
        int rowGuests = tableGuests.getSelectedRow();

        if (rowBookings >= 0) {
            int deleteBooking = JOptionPane.showConfirmDialog(null, "Do you want to delete the row?");

            if (deleteBooking == JOptionPane.YES_OPTION) {
                int deleteBookingId = (Integer) tableBookings.getValueAt(rowBookings, 0);
                bookingController.delete(deleteBookingId);

                JOptionPane.showMessageDialog(null, "Successfully deleted");

            }

        } else if (rowGuests >= 0) {

            int deleteGuest = JOptionPane.showConfirmDialog(null, "Do you want to delete the row?");

            if (deleteGuest == JOptionPane.YES_OPTION) {
                int deleteGuestId = (Integer) tableGuests.getValueAt(rowGuests, 0);
                guestsController.delete(deleteGuestId);

                JOptionPane.showMessageDialog(null, "Successfully deleted");
            }

        } else {
            JOptionPane.showMessageDialog(
                    null, "Select the row you want to delete!", null, JOptionPane.ERROR_MESSAGE);
        }

    }

    private void edit() {
        int rowBookings = tableBookings.getSelectedRow();
        int rowGuests = tableGuests.getSelectedRow();

        if (rowBookings >= 0) {
            //Logica editar reservas

            int id = Integer.parseInt(tableModelBooking.getValueAt(rowBookings, 0).toString());
            Date checkIn = Date.valueOf(tableModelBooking.getValueAt(rowBookings, 1).toString());
            Date checkOut = Date.valueOf(tableModelBooking.getValueAt(rowBookings, 2).toString());
            int price = Integer.parseInt(tableModelBooking.getValueAt(rowBookings, 3).toString());
            String paymentMethod = String.valueOf(tableBookings.getValueAt(rowBookings, 4));

            System.out.printf("{id: %s, checkIn: %s, checkOut: %s, price: %s, paymentMethod: %s}",
                    id, checkIn, checkOut, price, paymentMethod);
            System.out.println();

            this.bookingController.update(checkIn, checkOut, price, paymentMethod, id);

        } else if (rowGuests >= 0) {
            //Logica editar datos huespedes

            int id = Integer.parseInt(tableModelGuests.getValueAt(rowGuests, 0).toString());
            String firstName = String.valueOf(tableGuests.getValueAt(rowGuests, 1));
            String lastName = String.valueOf(tableGuests.getValueAt(rowGuests, 2));
            Date birthDate = Date.valueOf(String.valueOf(tableGuests.getValueAt(rowGuests, 3)));
            String phoneNumber = String.valueOf(tableGuests.getValueAt(rowGuests, 4));
            int bookingId = Integer.parseInt(tableModelGuests.getValueAt(rowGuests, 5).toString());

            System.out.printf("{id: %s, firstName: %s, lastName: %s, birthDate: %s, phoneNumber: %s, bookingId: %s}",
                    id, firstName, lastName, birthDate, phoneNumber, bookingId);
            System.out.println();

            this.guestsController.update(firstName, lastName, birthDate, phoneNumber, bookingId, id);

        } else {
            JOptionPane.showMessageDialog(
                    null, "A row must be selected!", null, JOptionPane.ERROR_MESSAGE);
        }

    }


}
