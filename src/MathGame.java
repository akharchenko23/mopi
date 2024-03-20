import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class MathGame extends JFrame {

    /**
     * скільки правильно в кінці
     * грати ще
     * в мінусові не можна
     * у нових вікнах
     * <p>
     * <p>
     * Написати програму "Математична гра", що виводить приклади
     * на додавання і віднімання з 2, 3-х дій і просить у користувача відповідь.
     * <p>
     * В якості обмеження користувач вказує максимальне число
     * до якого він вже знає числа.
     * І вказує кількість прикладів, які мають бути надруковані для підрахунків.
     * <p>
     * Наприклад, користувач ввів максимальне число 6 і кількість прикладів - 6,
     * йому виводяться приклади: 2+2-1=, 4+2+0=, 5+1-2-2=, 3+3-5+4=, 2-2+1+3=, 6-2-2=.
     * <p>
     * Це гра з графічним інтерфейсом. Знаки і числа генеруються.
     */


    private JLabel greetingMessage;

    private JButton okButton;
    private JLabel maxNum;
    private JLabel numTasks;
    private JTextField maxNumber;
    private JTextField numOfTasks;
    public JLabel doTasks;
    private JLabel taskOne, taskTwo, taskThree, signOne, signTwo, equalSign;
    private JTextField answer;
    private JButton check;
    private int corrrectAnswers;

    private ArrayList<JLabel> taskLabels = new ArrayList<>(); // Список для зберігання компонентів кожного прикладу
    private ArrayList<JTextField> answerFields = new ArrayList<>();


    public int maxN;
    public int tN;
    public JFrame tasksFrame = new JFrame("do the tasks");


    public MathGame(String name) {
        super(name);
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, 2));

        init();
        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginPanel.add(greetingMessage);
        add(loginPanel);
        //loginPanel.add(greetingMessage);

        JPanel askingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        askingPanel.add(numTasks);
        askingPanel.add(numOfTasks);
        askingPanel.add(maxNum);
        askingPanel.add(maxNumber);
        add(askingPanel);

        okButton = new JButton("OK");
        okButton.setBackground(Color.GREEN);
        okButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String maximumN = maxNumber.getText();
                maxN = Integer.valueOf(maximumN);
                String tasksN = numOfTasks.getText();
                tN = Integer.valueOf(tasksN);

                if (checkPos(maxN, tN)) {
                    //ідемо в наступне вікно

                    tasksFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    setLocationRelativeTo(null);
                    tasksFrame.setSize(500, 400);

                    initTwo();
                    generateTask(maxN, tN);
                    JPanel taskPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

                    taskPanel.add(doTasks);
                    tasksFrame.add(taskPanel);

                    JPanel taskAndAnswer = new JPanel();
                    for (int i = 0; i < taskLabels.size(); i++) { // Додаємо всі компоненти прикладів до контейнера
                        taskAndAnswer.add(taskLabels.get(i));
                        taskAndAnswer.add(answerFields.get(i));
                    }
                    taskAndAnswer.add(check); // Додаємо кнопку перевірки
                    tasksFrame.add(taskAndAnswer);

                    tasksFrame.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(MathGame.this, "ERROR");
                }
            }
        });
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.add(okButton);
        add(buttonsPanel);
        // setVisible(true);
        // Ініціалізуємо кнопку перевірки
        check = new JButton("Check Answers");
        check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(tasksFrame, "correct answers: " + corrrectAnswers);
//                for (int i = 0; i < taskLabels.size(); i++) {
//
//                    JLabel taskLabel = taskLabels.get(i);
//                    JTextField answerField = answerFields.get(i);
//                    String taskText = taskLabel.getText().trim();
//                    String answer = answerField.getText().trim();
//
//                    String[] parts = taskText.split("[+\\-=]");
//
//                    int result = Integer.parseInt(parts[0].trim());//////////////////////////////////
//
//                    for (int j = 1; j < parts.length; j++) {
//                        String part = parts[j].trim();
//                        char sign = taskText.charAt(part.length());
//                        int num = Integer.parseInt(part);
//                        if (sign == '+') {
//                            result += num;
//                        } else {
//                            result -= num;
//                        }
//                    }
//
//                    if (answer.equals(Integer.toString(result))) {
//                        JOptionPane.showMessageDialog(tasksFrame, "correct");
//                    } else {
//                        JOptionPane.showMessageDialog(tasksFrame, "ERROR");
//                    }
//                }
            }
        });

        buttonsPanel.add(check);

        setVisible(true);

    }

    private void init() {
        greetingMessage = new JLabel("Welcome");
        numTasks = new JLabel("How many tasks do you want to perform? ");
        maxNum = new JLabel("What is the greatest number you know? ");
        maxNumber = new JTextField(20);
        numOfTasks = new JTextField(20);

    }

    private void initTwo() {
        doTasks = new JLabel("do the tasks correctly");
        //taskOne = new JLabel(" 1 ");
        //signOne = new JLabel(" + ");
        //taskTwo = new JLabel(" 2 ");
        //signTwo = new JLabel(" - ");
        // taskThree = new JLabel(" 3 ");
        equalSign = new JLabel(" = ");

        answer = new JTextField(10);
    }

    public void generateTask(int maxNum, int numTasks) {
        Random rNum = new Random();


        for (int i = 0; i < numTasks; i++) {
            StringBuilder taskBuilder = new StringBuilder();
            int numberOne = rNum.nextInt(0, maxNum + 1);

            // System.out.println("1 num " + numberOne);
            int numberTwo = rNum.nextInt(0, maxNum + 1);

            //System.out.println("2 num " + numberTwo);
            int numberThree = rNum.nextInt(0, maxNum + 1);

            //System.out.println("3 num " + numberThree);
            int signFirst = rNum.nextInt(0, 2);

            // System.out.println("0 for - " + signFirst);
            int signSecond = rNum.nextInt(0, 2);

            //System.out.println("0 for - " + signSecond);
            int numOfNumbers = rNum.nextInt(2, 4);

            System.out.println("доданки " + numOfNumbers);

            int sum = 0;

            if (signFirst == 0) {
                sum = numberOne - numberTwo;
                System.out.println(numberOne + " - " + numberTwo);
            } else {
                sum = numberOne + numberTwo;
                System.out.println(numberOne + " + " + numberTwo);
            }
            int newSum = 0;
            if (numOfNumbers == 3) {
                if (signSecond == 0) {
                    newSum = sum - numberThree;
                    System.out.println(" - " + numberThree);
                } else {
                    newSum = sum + numberThree;
                    System.out.println(" + " + numberThree);
                }
                if (newSum < 0 || newSum > maxNum) {
                    numTasks++;
                } else {
                    System.out.println("sum of three: " + newSum);

                    //String strOne = Integer.toString(numberOne);
                    //taskOne = new JLabel(strOne);

                    taskBuilder.append(numberOne);

                    if (signFirst == 0) {
                        //signOne = new JLabel(" - ");
                        taskBuilder.append(" - ");
                    } else {
                        // signOne = new JLabel(" + ");
                        taskBuilder.append(" + ");
                    }
                    //String strTwo = Integer.toString(numberTwo);
                    //taskTwo = new JLabel(strTwo);
                    taskBuilder.append(numberTwo);
                    if (signSecond == 0) {
                        //signTwo = new JLabel(" - ");
                        taskBuilder.append(" - ");

                    } else {
                        //signTwo = new JLabel(" + ");
                        taskBuilder.append(" + ");

                    }
                    //String strThree = Integer.toString(numberThree);
                    // taskThree = new JLabel(strThree);
                    taskBuilder.append(numberThree);
                    taskBuilder.append(" = ");
                    JTextField answerField = new JTextField(5);
                    answerFields.add(answerField);
                    JLabel taskLabel = new JLabel(taskBuilder.toString());
                    taskLabels.add(taskLabel);
                    String str = answerField.getText();
                    if(Integer.valueOf(str) == newSum){
                        corrrectAnswers++;
                    }
//                    JTextField answerField = new JTextField(5);
//                    answerFields.add(answerField);
                    //mouse listeners todo
                    ////
                    /*
                    ///////////////////////////////////////////////////////
                    check = new JButton("OK");
                    check.setBackground(Color.GREEN);
                    check.setLocation(100, 200);
                    tasksFrame.add(check);
                    check.setVisible(true);
                    /////////////////////////////////////////////////
                    int finalNewSum = newSum;
                    check.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            String ans = answer.getText();


                            if (ans.equals(Integer.toString(finalNewSum))) {
                                JOptionPane.showMessageDialog(tasksFrame, "правильно");
                            } else {
                                JOptionPane.showMessageDialog(tasksFrame, "ERROR");
                            }
                        }
                    });
                    //JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                   // buttonPanel.add(check);

                    ////

                     */

                }
            } else {
                if (sum < 0 || sum > maxNum) {
                    numTasks++;
                } else {
                    System.out.println("sum: " + sum);
                    //String strOne = Integer.toString(numberOne);
                    // taskOne = new JLabel(strOne);
                    taskBuilder.append(numberOne);

                    if (signFirst == 0) {
                        //signOne = new JLabel(" - ");
                        taskBuilder.append(" - ");

                    } else {
                        //signOne = new JLabel(" + ");
                        taskBuilder.append(" + ");

                    }
                    //String strTwo = Integer.toString(numberTwo);
                    //taskTwo = new JLabel(strTwo);
                    taskBuilder.append(numberTwo);
                    taskBuilder.append(" = ");

                    JTextField answerField = new JTextField(5);
                    answerFields.add(answerField);
                    JLabel taskLabel = new JLabel(taskBuilder.toString());
                    taskLabels.add(taskLabel);
                    String str = answerField.getText();
                    if(Integer.valueOf(str) == sum){
                        corrrectAnswers++;
                    }
                    //signTwo = new JLabel(" ");
                    //taskThree = new JLabel(" ");
                    //mouse listeners todo
//                    if (answer.getText().equals(Integer.valueOf(sum))) {
//                        equalSign.setBackground(Color.YELLOW);
//                    }
                }
            }
            //taskBuilder.append(" = ");

        }
    }

    /*
        public static void randomTask(int maxNum, int numTasks) {

            for (int i = 0; i < numTasks; i++) {
                int sum = 0;

                Random dodankyR = new Random();
                int dodanky = dodankyR.nextInt(2, 4);
                // System.out.println("доданків " + dodanky);

                Random numRone = new Random();
                int numOne = numRone.nextInt(0, maxNum + 1);
                //System.out.println("число " + numOne);

                Random signR = new Random();
                int sign = signR.nextInt(0, 2);

                Random numR = new Random();
                int num = numR.nextInt(0, maxNum + 1);
                // System.out.println("число " + num);

                if (sign == 0) {
                    //System.out.println("+");
                    numOne += num;
                } else {
                    //System.out.println("-");
                    numOne -= num;
                }
                int mySign = signR.nextInt(0, 2);
                int number = numR.nextInt(0, maxNum + 1);
                if (dodanky == 3) {


                    //System.out.println("число " + num);
                    if (mySign == 0) {
                        // System.out.println("+");
                        numOne += number;
                    } else {
                        //System.out.println("-");
                        numOne -= number;
                    }


                }
                //це результат
                if (!checkNum(numOne, maxNum)) {
                    numTasks++;
                } else {

                    if (dodanky == 3) {
                        System.out.println(numOne +" "+ sign +" "+ num +" "+ mySign +" "+ number + "сума =" + numOne);
                    }else{
                        System.out.println(numOne +" "+ sign +" "+ num +" "+ "сума =" + numOne);

                    }
                }

            }
        }
    */

    private boolean checkNum(int number, int maxNumber) {
        if (number <= maxNumber && number >= 0) {
            return true;
        }
        return false;
    }

    private boolean checkPos(int i, int j) {
        if (i >= 0 && j >= 0) {
            return true;
        }
        return false;
    }


}
