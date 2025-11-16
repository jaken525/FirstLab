import bruh.Calculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FirstLab {
    private static final Calculator calc = new Calculator();

    public static void main(String[] args) {
        // Запуск в EDT
        SwingUtilities.invokeLater(FirstLab::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // Окно
        JFrame frame = new JFrame("Калькулятор");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(380, 500);
        frame.setResizable(false);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Шрифты
        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Font fieldFont = new Font("Consolas", Font.PLAIN, 18);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 20);

        // Заголовок
        JLabel title = new JLabel("Калькулятор", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(46, 125, 50));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        frame.add(title, gbc);

        // Поле 1
        gbc.gridwidth = 1; gbc.gridy++;
        JLabel label1 = new JLabel("Число 1:");
        label1.setFont(labelFont);
        frame.add(label1, gbc);

        gbc.gridy++;
        JTextField input1 = new JTextField();
        input1.setFont(fieldFont);
        input1.setHorizontalAlignment(JTextField.RIGHT);
        input1.setPreferredSize(new Dimension(340, 45));
        frame.add(input1, gbc);

        // П��оле 2
        gbc.gridy++;
        JLabel label2 = new JLabel("Число 2:");
        label2.setFont(labelFont);
        frame.add(label2, gbc);

        gbc.gridy++;
        JTextField input2 = new JTextField();
        input2.setFont(fieldFont);
        input2.setHorizontalAlignment(JTextField.RIGHT);
        input2.setPreferredSize(new Dimension(340, 45));
        frame.add(input2, gbc);

        // Кнопки
        gbc.gridy++;
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        buttonPanel.setBackground(new Color(245, 245, 245));

        JButton addBtn = createButton("+", new Color(76, 175, 80), buttonFont);   // зелёный
        JButton subBtn = createButton("−", new Color(244, 67, 54), buttonFont);   // красный
        JButton mulBtn = createButton("×", new Color(33, 150, 243), buttonFont);  // синий
        JButton divBtn = createButton("÷", new Color(255, 152, 0), buttonFont);   // оранжевый

        buttonPanel.add(addBtn);
        buttonPanel.add(subBtn);
        buttonPanel.add(mulBtn);
        buttonPanel.add(divBtn);

        frame.add(buttonPanel, gbc);

        // Результат
        gbc.gridy++;
        JLabel resultLabel = new JLabel("Результат:");
        resultLabel.setFont(labelFont);
        frame.add(resultLabel, gbc);

        gbc.gridy++;
        JTextField resultField = new JTextField();
        resultField.setFont(new Font("Consolas", Font.BOLD, 20));
        resultField.setHorizontalAlignment(JTextField.RIGHT);
        resultField.setEditable(false);
        resultField.setBackground(new Color(240, 248, 255));
        resultField.setForeground(new Color(46, 125, 50));
        resultField.setPreferredSize(new Dimension(340, 50));
        frame.add(resultField, gbc);

        // Обработчики
        addBtn.addActionListener(e -> calculate(input1, input2, resultField, '+'));
        subBtn.addActionListener(e -> calculate(input1, input2, resultField, '-'));
        mulBtn.addActionListener(e -> calculate(input1, input2, resultField, '*'));
        divBtn.addActionListener(e -> calculate(input1, input2, resultField, '/'));

        // Клавиша Enter
        input2.addActionListener(e -> calculate(input1, input2, resultField, '+')); // по умолчанию сложение

        frame.getContentPane().setBackground(new Color(250, 250, 250));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JButton createButton(String text, Color bg, Font font) {
        JButton btn = new JButton(text);
        btn.setFont(font);
        btn.setForeground(Color.WHITE);
        btn.setBackground(bg);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Эффект наведения
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(bg.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(bg);
            }
        });

        return btn;
    }

    private static void calculate(JTextField in1, JTextField in2, JTextField result, char op) {
        try {
            double a = Double.parseDouble(in1.getText());
            double b = Double.parseDouble(in2.getText());
            double res = switch (op) {
                case '+' -> calc.add(a, b);
                case '-' -> calc.subtract(a, b);
                case '*' -> calc.multiply(a, b);
                case '/' -> calc.divide(a, b);
                default -> 0;
            };
            result.setText(String.format("%.6g", res).replace(',', '.'));
        } catch (NumberFormatException e) {
            result.setText("Ошибка: введите числа");
            result.setForeground(Color.RED);
        } catch (ArithmeticException e) {
            result.setText("Ошибка: " + e.getMessage());
            result.setForeground(Color.RED);
        } catch (Exception e) {
            result.setText("Неизвестная ошибка");
            result.setForeground(Color.RED);
        }
    }
}