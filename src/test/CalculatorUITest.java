package bruh.calculator.ui;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorUITest extends BaseUITest 
{
    @Test
    @DisplayName("Должен отображать все основные кнопки калькулятора")
    void shouldDisplayAllBasicCalculatorButtons() 
    {
        for (int i = 0; i <= 9; i++)
            window.button(String.valueOf(i)).requireVisible();

        window.button("+").requireVisible();
        window.button("-").requireVisible();
        window.button("*").requireVisible();
        window.button("/").requireVisible();
        window.button("=").requireVisible();
        window.button("C").requireVisible();

        window.textBox().requireVisible();
    }

    @Test
    @DisplayName("Должен выполнять простые арифметические операции")
    void shouldPerformBasicArithmeticOperations()
     {
        window.button("2").click();
        window.button("+").click();
        window.button("3").click();
        window.button("=").click();
        
        window.textBox().requireText("5.0");

        window.button("C").click();
        window.button("9").click();
        window.button("-").click();
        window.button("4").click();
        window.button("=").click();
        
        window.textBox().requireText("5.0");

        window.button("C").click();
        window.button("3").click();
        window.button("*").click();
        window.button("4").click();
        window.button("=").click();
        
        window.textBox().requireText("12.0");

        window.button("C").click();
        window.button("8").click();
        window.button("/").click();
        window.button("2").click();
        window.button("=").click();
        
        window.textBox().requireText("4.0");
    }

    @Test
    @DisplayName("Должен очищать поле ввода при нажатии кнопки C")
    void shouldClearInputFieldWhenClearButtonClicked()
    {
        window.button("1").click();
        window.button("2").click();
        window.button("3").click();
        
        window.textBox().requireText("123");
        
        window.button("C").click();
        window.textBox().requireText("");
    }

    @Test
    @DisplayName("Должен обрабатывать последовательные операции")
    void shouldHandleSequentialOperations() 
    {
        window.button("1").click();
        window.button("+").click();
        window.button("2").click();
        window.button("+").click();
        window.button("3").click();
        window.button("=").click();
        
        window.textBox().requireText("6.0");
    }

    @Test
    @DisplayName("Должен обрабатывать операции с плавающей точкой")
    void shouldHandleDecimalOperations()
    {
        window.button("5").click();
        window.button(".").click();
        window.button("5").click();
        window.button("+").click();
        window.button("2").click();
        window.button(".").click();
        window.button("5").click();
        window.button("=").click();
        
        window.textBox().requireText("8.0");
    }

    @Test
    @DisplayName("Должен отображать правильный заголовок окна")
    void shouldDisplayCorrectWindowTitle()
    {
        window.requireTitle("Calculator");
    }

    @Test
    @DisplayName("Должен обрабатывать деление на ноль")
    void shouldHandleDivisionByZero()
    {
        window.button("5").click();
        window.button("/").click();
        window.button("0").click();
        window.button("=").click();
        
        String result = window.textBox().text();
        assertThat(result).isIn("Infinity", "Ошибка", "Cannot divide by zero");
    }
}