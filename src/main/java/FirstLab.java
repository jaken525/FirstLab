package bruh;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FirstLab extends Application {

    private TextField input1, input2, result;
    private Calculator calc = new Calculator();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Калькулятор");

        input1 = new TextField();
        input1.setPromptText("Первое число");
        input2 = new TextField();
        input2.setPromptText("Второе число");
        result = new TextField();
        result.setEditable(false);
        result.setStyle("-fx-background-color: #f0f0f0; -fx-font-weight: bold;");

        Button addBtn = new Button("+");
        Button subBtn = new Button("−");
        Button mulBtn = new Button("×");
        Button divBtn = new Button("÷");

        String btnStyle = "-fx-font-size: 16; -fx-pref-width: 60; -fx-pref-height: 40;";
        addBtn.setStyle(btnStyle + "-fx-background-color: #4CAF50; -fx-text-fill: white;");
        subBtn.setStyle(btnStyle + "-fx-background-color: #f44336; -fx-text-fill: white;");
        mulBtn.setStyle(btnStyle + "-fx-background-color: #2196F3; -fx-text-fill: white;");
        divBtn.setStyle(btnStyle + "-fx-background-color: #FF9800; -fx-text-fill: white;");

        addBtn.setOnAction(e -> calculate('+'));
        subBtn.setOnAction(e -> calculate('-'));
        mulBtn.setOnAction(e -> calculate('*'));
        divBtn.setOnAction(e -> calculate('/'));

        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.add(addBtn, 0, 0);
        buttonGrid.add(subBtn, 1, 0);
        buttonGrid.add(mulBtn, 0, 1);
        buttonGrid.add(divBtn, 1, 1);

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #f9f9f9;");

        Label title = new Label("Калькулятор");
        title.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");

        root.getChildren().addAll(
                title,
                new Label("Число 1:"), input1,
                new Label("Число 2:"), input2,
                buttonGrid,
                new Label("Результат:"), result
        );

        Scene scene = new Scene(root, 360, 480);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void calculate(char operation) {
        try {
            double a = Double.parseDouble(input1.getText());
            double b = Double.parseDouble(input2.getText());
            double res = 0;

            switch (operation) {
                case '+' -> res = calc.add(a, b);
                case '-' -> res = calc.subtract(a, b);
                case '*' -> res = calc.multiply(a, b);
                case '/' -> res = calc.divide(a, b);
            }
            result.setText(String.valueOf(res));
        } catch (NumberFormatException e) {
            result.setText("Ошибка: введите числа");
        } catch (ArithmeticException e) {
            result.setText("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            result.setText("Неизвестная ошибка");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}