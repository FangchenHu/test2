import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {
    private JPanel panel;
    private JTextField textField;
    private JButton[] buttons;
    private String[] labels = { "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+" };
    private double result = 0;
    private String operator = "=";
    private boolean startOfNumber = true;

    public Calculator() {
        textField = new JTextField(12);
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        buttons = new JButton[16];
        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton(labels[i]);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }
        add(textField, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        String command = evt.getActionCommand();
        if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
            if (startOfNumber == true)
                textField.setText(command);
            else
                textField.setText(textField.getText() + command);
            startOfNumber = false;
        } else {
            if (startOfNumber) {
                if (command.equals("-")) {
                    textField.setText(command);
                    startOfNumber = false;
                } else
                    operator = command;
            } else {
                double x = Double.parseDouble(textField.getText());
                calculate(x);
                operator = command;
                startOfNumber = true;
            }
        }
    }

    public void calculate(double n) {
        if (operator.equals("+"))
            result += n;
        else if (operator.equals("-"))
            result -= n;
        else if (operator.equals("*"))
            result *= n;
        else if (operator.equals("/"))
            result /= n;
        else if (operator.equals("="))
            result = n;
        textField.setText("" + result);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
    }
}
