//1.  0-100 units=2 per unit			50 unit =100
//        101-150=3 per unit	110 unit = 200 + (10*3)30 =230
//        151-200=4 per unit
//        201-250=5 per unit
//        251-300=6 per unit
//        301-500 = 7 per unit
//        501 and above=8 per unit
//        According to the rate list above generate electricity bill for custumer


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElectricityBillCalculatorGUI {

    public static double calculateElectricityBill(int units) {
        if (units < 0) {
            throw new IllegalArgumentException("Invalid unit count");
        }

        double totalCost = 0;

        if (units <= 100) {
            totalCost = units * 2;
        } else if (units <= 150) {
            totalCost = 100 * 2 + (units - 100) * 3;
        } else if (units <= 200) {
            totalCost = 100 * 2 + 50 * 3 + (units - 150) * 4;
        } else if (units <= 250) {
            totalCost = 100 * 2 + 50 * 3 + 50 * 4 + (units - 200) * 5;
        } else if (units <= 300) {
            totalCost = 100 * 2 + 50 * 3 + 50 * 4 + 50 * 5 + (units - 250) * 6;
        } else if (units <= 500) {
            totalCost = 100 * 2 + 50 * 3 + 50 * 4 + 50 * 5 + 50 * 6 + (units - 300) * 7;
        } else {
            totalCost = 100 * 2 + 50 * 3 + 50 * 4 + 50 * 5 + 50 * 6 + 200 * 7 + (units - 500) * 8;
        }

        return totalCost;
    }

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Electricity Bill Calculator");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Create the label for units input
        JLabel label = new JLabel("Enter units consumed:");
        label.setBounds(50, 30, 150, 20);
        frame.add(label);

        // Create the text field for units input
        JTextField textField = new JTextField();
        textField.setBounds(200, 30, 100, 20);
        frame.add(textField);

        // Create the button to calculate the bill
        JButton button = new JButton("Calculate Bill");
        button.setBounds(50, 70, 150, 30);
        frame.add(button);

        // Create the label to display the result
        JLabel resultLabel = new JLabel();
        resultLabel.setBounds(50, 110, 300, 20);
        frame.add(resultLabel);

        // Add action listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int units = Integer.parseInt(textField.getText());
                    double bill = calculateElectricityBill(units);
                    resultLabel.setText("The electricity bill is: " + bill);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter a valid number.");
                } catch (IllegalArgumentException ex) {
                    resultLabel.setText(ex.getMessage());
                }
            }
        });

        // Set the frame visibility
        frame.setVisible(true);
    }
}
