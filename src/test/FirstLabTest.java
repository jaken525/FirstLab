import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

public class FirstLab {

    private static final String[] GREETINGS = {
        "Поважный Виталий Евгеньевич РИС-22-1б",
        "Зырянов Ростислав Александрович РИС-22-1б",
		"Вышенская Екатерина Игоревна РИС-22-1б"
    };

    @Test
    public void TestPrint() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        App.printGreetings(printStream);

        System.setOut(originalOut);

        String expected = String.join("\n", GREETINGS);
        assertEquals(expected, outputStream.toString());
    }
}