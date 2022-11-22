package com.example.demofx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GuessNumberController implements Initializable {
    GuessNumber guessNumber = new GuessNumber();;
    int randomNumber;
    int attemptsLeft;
    int minValue;
    int maxValue;
    boolean isWin;

    @FXML
    private Label welcomeText;
    @FXML
    private Label enterYourNumberText;
    @FXML
    private Label attemptsAmountText;
    @FXML
    private Label numberRangeText;
    @FXML
    private Label attemptsAmountValue;
    @FXML
    private Label numberRangeValue;
    @FXML
    private TextField numberTextField;

    @FXML
    private Button startButton;
    @FXML
    private Button exitButton;

    @FXML
    private RadioButton radioButtonEasy;

    @FXML
    private RadioButton radioButtonMedium;

    @FXML
    private RadioButton radioButtonHard;

    private void init() {
        enterYourNumberText.setText("Уровень сложности");
        attemptsAmountText.setText("Попыток");
        numberRangeText.setText("Уровень сложности");
        isWin = false;
        numberTextField.setVisible(false);
        startButton.setText("Играть");
        numberTextField.clear();
        attemptsAmountValue.setVisible(false);
        minValue = 0;
        maxValue = 0;
        ToggleGroup tg = new ToggleGroup();
        radioButtonEasy.setToggleGroup(tg);
        radioButtonMedium.setToggleGroup(tg);
        radioButtonHard.setToggleGroup(tg);
        radioButtonEasy.setVisible(true);
        radioButtonMedium.setVisible(true);
        radioButtonHard.setVisible(true);
        radioButtonEasy.setSelected(true);
        setEasyComplexity();


        radioButtonEasy.setOnAction(actionEvent -> {
            setEasyComplexity();
        });

        radioButtonMedium.setOnAction(actionEvent -> {
            setMediumComplexity();
        });

        radioButtonHard.setOnAction(actionEvent -> {
            setHardComplexity();
        });

    }



    @FXML
    protected void onStartButtonClick() {
        if (startButton.getText().equals("Играть")) {
            enterYourNumberText.setText("Введите целое число");
            startButton.setText("Проверить");
            radioButtonEasy.setVisible(false);
            radioButtonMedium.setVisible(false);
            radioButtonHard.setVisible(false);
            numberTextField.setVisible(true);
        } else if (startButton.getText().equals("Начать")) {
            init();
        } else if (startButton.getText().equals("Проверить")) {
            guess();
        }
    }

    @FXML
    protected void onExitButtonClick() {
        close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    private void close() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    private void setMinValue(int userNumber) {
        if ((this.randomNumber > userNumber) && (userNumber > minValue)) {
            this.minValue = userNumber;
        }
    }

    private void setMaxValue(int userNumber) {
        if ((this.randomNumber < userNumber) && (userNumber < maxValue)) {
            this.maxValue = userNumber;
        }
    }

    private void setEasyComplexity(){
        numberRangeValue.setText("   0 < " + guessNumber.complexity.get("EASY").numberRange + "");
        attemptsAmountValue.setText("   " + guessNumber.complexity.get("EASY").attemptsAmount + "");
        randomNumber = randNumber(guessNumber.complexity.get("EASY").numberRange);
        attemptsLeft = guessNumber.complexity.get("EASY").attemptsAmount;
        maxValue = guessNumber.complexity.get("EASY").numberRange;
        attemptsAmountValue.setVisible(true);
    }

    private void setMediumComplexity(){
        numberRangeValue.setText("   0 < " + guessNumber.complexity.get("MEDIUM").numberRange + "");
        attemptsAmountValue.setText("   " + guessNumber.complexity.get("MEDIUM").attemptsAmount + "");
        randomNumber = randNumber(guessNumber.complexity.get("MEDIUM").numberRange);
        attemptsLeft = guessNumber.complexity.get("MEDIUM").attemptsAmount;
        maxValue = guessNumber.complexity.get("MEDIUM").numberRange;
        attemptsAmountValue.setVisible(true);
    }

    private void setHardComplexity(){
        numberRangeValue.setText("   0 < " + guessNumber.complexity.get("HARD").numberRange + "");
        attemptsAmountValue.setText("   " + guessNumber.complexity.get("HARD").attemptsAmount + "");
        randomNumber = randNumber(guessNumber.complexity.get("HARD").numberRange);
        attemptsLeft = guessNumber.complexity.get("HARD").attemptsAmount;
        maxValue = guessNumber.complexity.get("HARD").numberRange;
        attemptsAmountValue.setVisible(true);
    }

    public int randNumber(int complexityLimit){
        return (int)(Math.random() * (complexityLimit - 1) + 1);
    }

    public boolean isNumbersEqual(int guessNumber, int userNumber) {
        return guessNumber == userNumber;
    }
    private void guess() {
        attemptsLeft--;
        attemptsAmountValue.setText("" + attemptsLeft);
        try {
            int userNumber = Integer.parseInt(numberTextField.getText());
            numberTextField.clear();
            isWin = isNumbersEqual(userNumber, randomNumber);
            if (isWin) {
                enterYourNumberText.setText("Вы выиграли! Число " + randomNumber);
                numberTextField.setVisible(false);
                startButton.setText("Играть");

            } else if (attemptsLeft == 0) {
                enterYourNumberText.setText("Вы проиграли.. Число было " + randomNumber);
                startButton.setText("Играть");
                numberTextField.setVisible(false);
            } else {
                if (userNumber > randomNumber) {
                    enterYourNumberText.setText("Число " + userNumber + " > Загаданного. Пробуйте еще.");
                } else {
                    enterYourNumberText.setText("Число " + userNumber + " < Загаданного. Пробуйте еще.");
                }
                setMaxValue(userNumber);
                setMinValue(userNumber);
                enterYourNumberText.setText(minValue + " < Загаданное число < " + maxValue);
            }

        } catch (Exception e) {
            enterYourNumberText.setText("Неверный формат");
        }
    }
}
