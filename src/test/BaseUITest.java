package bruh.calculator.ui;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import bruh.Calculator;

public abstract class BaseUITest 
{
    protected Robot robot;
    protected FrameFixture window;
    protected Calculator calculator;

    @BeforeEach
    public void setUp() 
    {
        robot = BasicRobot.robotWithNewAwtHierarchy();

        calculator = GuiActionRunner.execute(() -> new Calculator());

        window = new FrameFixture(robot, calculator);
        window.show();
    }

    @AfterEach
    public void tearDown()
    {
        if (window != null)
            window.cleanUp();
    }
}