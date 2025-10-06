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
    }

    public static void printGreetings(PrintStream out) {
        for (String greeting : GREETINGS) {
            out.println(greeting);
        }
    }
}