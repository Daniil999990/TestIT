package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Controller {

    @FXML
    private ToggleGroup answers;

    @FXML
    private Text question_text;

    @FXML
    private RadioButton radio_btn_1;

    @FXML
    private RadioButton radio_btn_2;

    @FXML
    private RadioButton radio_btn_3;

    @FXML
    private RadioButton radio_btn_4;

    @FXML
    private Button answerBtn;
    @FXML
    private Button closeProgramBtn;
    private int nowQuestion = 0, correctAnswers;
    private String nowCorrectAnswer;


    private final Questions[] questions = new Questions[]{
            new Questions("Як здійснюється вивід тексту в консоль в мові програмування Java?",
                    new String[]{"Console.Write()", "console.log()", "System.printfn()", "System.out.println()"}),
            new Questions("Який тип даних відповідає за цілі числа?",
                    new String[]{"String", "Float", "Boolean", "Integer"}),
            new Questions("Де правильно присвоєно нове значення багатовимірному масиву?",
                    new String[]{"a(0)(0) = 1;", "a[0 0] = 1;", "a{0}{0} = 1;", "a[0][0] = 1;"}),
            new Questions("Який метод дозволяє запустити програму на Java?",
                    new String[]{"Основного метода немає", "З класу, який був написаний першим і з методів, що є всередині нього", "Будь-який, його можна встановити в налаштуваннях проекту", "З методу main в будь-якому з класів"}),
            new Questions("Кожен файл повинен називатися...",
                    new String[]{"за ім'ям першої бібліотеки в ньому", "за ім'ям назви пакета", "як вам захочеться", "за ім'ям класу в ньому"}),
            new Questions("Скільки параметрів може приймати функція?",
                    new String[]{"5", "10", "20", "необмежена кількість"}),
            new Questions("Що таке інтерфейс в Java",
                    new String[]{"Інтерфейс - це клас", "Інтерфейс не може мати жодного методу", "Інтерфейс може містити реалізацію методів", "Інтерфейс оголошує набір методів, але не реалізує їх."}),
            new Questions("Як визначити константу в Java?",
                    new String[]{"const CONSTANT_NAME = value", "constant String CONSTANT_NAME = value", "static CONSTANT_NAME = value", "final String CONSTANT_NAME = value"}),
            new Questions("Яка ключова різниця між ArrayList та LinkedList в Java?",
                    new String[]{"ArrayList є більш ефективним для вставки та вилучення, ніж LinkedList", "LinkedList є більш ефективним для операцій пошуку, ніж ArrayList", "Не один", "ArrayList базується на масиві, а LinkedList - на зв'язаних списках"}),
            new Questions("Яка різниця між HashSet та TreeSet в Java?",
                    new String[]{"HashSet гарантує порядок додавання елементів", "HashSet не дозволяє дублікати елементів", "TreeSet гарантує сортування елементів за замовчуванням", "TreeSet використовує червоно-чорне дерево для зберігання елементів"}),
    };

    @FXML
    public void initialize() {
        nowCorrectAnswer = questions[nowQuestion].correctAnswer();

        answerBtn.setOnAction(e -> {
            RadioButton selectedRadioButton = (RadioButton) answers.getSelectedToggle();
            if (selectedRadioButton != null) {
                String toogleGroupValue = selectedRadioButton.getText();

                if (toogleGroupValue.equals(nowCorrectAnswer)) {
                    System.out.println("Відповідь вірна");
                    correctAnswers++;
                } else {
                    System.out.println("Не вірна відповідь");
                }

                // Это был последний вопрос
                if (nowQuestion + 1 == questions.length) {
                    radio_btn_1.setVisible(false);
                    radio_btn_2.setVisible(false);
                    radio_btn_3.setVisible(false);
                    radio_btn_4.setVisible(false);
                    answerBtn.setVisible(false);

                    question_text.setText("Ви відповили " + correctAnswers + " з " + questions.length + " питань!");
                } else {
                    nowQuestion++;
                    nowCorrectAnswer = questions[nowQuestion].correctAnswer();

                    question_text.setText(questions[nowQuestion].getQuestion());
                    String[] answers = questions[nowQuestion].getAnswers();


                    List<String> intList = Arrays.asList(answers);

                    Collections.shuffle(intList);

                    radio_btn_1.setText(intList.get(0));
                    radio_btn_2.setText(intList.get(1));
                    radio_btn_3.setText(intList.get(2));
                    radio_btn_4.setText(intList.get(3));

                    selectedRadioButton.setSelected(false);
                    closeProgramBtn.setOnAction(this::handleCloseProgram);
                }

            }
        });
    }

    private void handleCloseProgram(javafx.event.ActionEvent actionEvent){
        Platform.exit();
    }
}