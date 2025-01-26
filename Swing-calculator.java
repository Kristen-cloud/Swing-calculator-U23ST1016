import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingCalculator extends JFrame implements ActionListener {
    private JTextField inputField;
    private double firstNumber = 0;
    private String operator = "";
    private boolean isOperatorClicked = false;

    public SwingCalculator() {
        // Set up the JFrame
        setTitle("Swing Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the input field
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.BOLD, 24));
        inputField.setHorizontalAlignment(JTextField.RIGHT);
        inputField.setEditable(false);
        add(inputField, BorderLayout.NORTH);

        // Create the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        // Button labels
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        // Add buttons to the panel
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            if (isOperatorClicked) {
                inputField.setText("");
                isOperatorClicked = false;
            }
            inputField.setText(inputField.getText() + command);
        } else if (command.equals("C")) {
            inputField.setText("");
            firstNumber = 0;
            operator = "";
        } else if (command.equals("=")) {
            double secondNumber = Double.parseDouble(inputField.getText());
            switch (operator) {
                case "+" -> firstNumber += secondNumber;
                case "-" -> firstNumber -= secondNumber;
                case "*" -> firstNumber *= secondNumber;
                case "/" -> firstNumber /= secondNumber;
            }
            inputField.setText(String.valueOf(firstNumber));
            operator = "";
        } else {
            firstNumber = Double.parseDouble(inputField.getText());
            operator = command;
            isOperatorClicked = true;
        }
    }

    public static void main(String[] args) {
        new SwingCalculator();
    }
}
