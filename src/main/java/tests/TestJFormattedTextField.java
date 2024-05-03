package tests;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

public class TestJFormattedTextField {

    public static void main(String[] args) {

        /**
         * With the code below we ensure the system only accepts numbers (int)
         */

        NumberFormat format = NumberFormat.getInstance();

        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatter.setCommitsOnValidEdit(true);
        JFormattedTextField field = new JFormattedTextField(formatter);

        JOptionPane.showMessageDialog(null, field);
        // getValue() always returns something valid
        System.out.println(field.getValue());
        System.out.println(field.getClass());
    }
}
