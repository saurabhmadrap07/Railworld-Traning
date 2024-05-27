import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    // Create a frame
    private JFrame frame;

    // Create a textfield
    private JTextField textField;

    // Store operator and operands
    private String operator;
    private double num1, num2, result;

    // Constructor
    public Calculator() {
        frame = new JFrame("Calculator");
        textField = new JTextField();
        operator = "";

        // Set the layout of the frame
        frame.setLayout(new BorderLayout());

        // Create a panel for buttons and set GridLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));

        // Array of button labels
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        // Create buttons and add them to the panel
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        // Add textfield and button panel to the frame
        frame.add(textField, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Set frame properties
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Action listener method
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            // If the button is a number, append it to the text field
            textField.setText(textField.getText() + command);
        } else if (command.charAt(0) == 'C') {
            // Clear the text field
            textField.setText("");
            operator = "";
            num1 = num2 = result = 0;
        } else if (command.charAt(0) == '=') {
            // Perform calculation
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": result = num1 / num2; break;
            }
            textField.setText(String.valueOf(result));
            operator = "";
        } else {
            // Store the first number and the operator
            num1 = Double.parseDouble(textField.getText());
            operator = command;
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
