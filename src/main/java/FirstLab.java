import java.io.PrintStream;

public class FirstLab {
    private static final String[] GREETINGS = {
        "Поважный Виталий Евгеньевич РИС-22-1б",
        "Зырянов Ростислав Александрович РИС-22-1б",
        "Вышенская Екатерина Игоревна РИС-22-1б",
        "Карнаухов Михаил Евгеньевич РИС-22-1б"
    };

    public static void main(String[] args) {
        printGreetings(System.out);
		
        Calculator calc = new Calculator();
        System.out.println("Addition: " + calc.add(5.0, 3.0));
        System.out.println("Subtraction: " + calc.subtract(5.0, 3.0));
        System.out.println("Multiplication: " + calc.multiply(5.0, 3.0));
        System.out.println("Division: " + calc.divide(6.0, 2.0));
    }

    public static void printGreetings(PrintStream out) {
        for (String greeting : GREETINGS) {
            out.println(greeting);
        }
    }
}