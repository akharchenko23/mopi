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
    //public JButton playAgainButton;
    private JLabel taskOne, taskTwo, taskThree, signOne, signTwo, equalSign;
    private JTextField answer;
    private JButton check;
    private int corrrectAnswers;
    public JPanel taskPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    public JPanel taskAndAnswer = new JPanel();


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
                    //MathGame.this.getContentPane().removeAll();
                    tasksFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    setLocationRelativeTo(null);
                    tasksFrame.setSize(500, 400);

                    initTwo();
                    generateTask(maxN, tN);
                    //JPanel taskPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

                    taskPanel.add(doTasks);
                    tasksFrame.add(taskPanel);

                    //    JPanel taskAndAnswer = new JPanel();
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
        // buttonsPanel.add(playAgainButton);
        add(buttonsPanel);
        // setVisible(true);
        // Ініціалізуємо кнопку перевірки
        check = new JButton("Check Answers");
        check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //checkAnswers();
                //System.out.println("tasks Label " + taskLabels);
                for (int i = 0; i < taskLabels.size(); i++) {

                    JLabel taskLabel = taskLabels.get(i);
                    JTextField answerField = answerFields.get(i);
                    String taskText = taskLabel.getText().trim();
                    System.out.println("taskTest getText " + taskText);
                    String answer = answerField.getText().trim();
                    System.out.println("answerField answer " + answer);

                    String[] parts = taskText.split("[+\\-=]");


                    int firstDodanok = Integer.parseInt(parts[0].trim());//////////////////////////////////
                    System.out.println("перший доданок " + firstDodanok);
                    if (parts.length == 2) {
                        for (int j = 1; j < parts.length; j++) {
                            String part = parts[j].trim();
                            //System.out.println("part " + part);
                            // char[] omg = taskText.toCharArray();
//                            for (char ch : omg) {
//                                System.out.println("chars " + ch);
//                            }
                            char sign = taskText.charAt(2);
                            //System.out.println("sign " + sign);
                            int num = Integer.parseInt(part);
                            if (sign == '+') {
                                firstDodanok += num;
                            } else {
                                firstDodanok -= num;
                            }
                        }
                    } else if (parts.length == 3) {
                        System.out.println("3 dodanky");

                        for (String str : parts) {
                            System.out.println("parts " + str);
                        }

                        for (int j = 1; j < parts.length; j++) {

                            String part = parts[j].trim();

                            System.out.println("part " + part);

                            char[] omg = taskText.toCharArray();

                            for (char ch : omg) {
                                System.out.println("chars " + ch);
                            }
                            if (j == 1) {
                                char sign = taskText.charAt(2);
                                System.out.println("sign1 " + sign);
                                int num = Integer.parseInt(part);
                                if (sign == '+') {
                                    firstDodanok += num;
                                } else {
                                    firstDodanok -= num;
                                }
                            } else if (j==2) {
                                char sign = taskText.charAt(6);
                                System.out.println("sign2 " + sign);
                                String part3 = parts[j].trim();
                                System.out.println("part3 " + part3);
                                int num = Integer.parseInt(part3);
                                if (sign == '+') {
                                    firstDodanok += num;
                                } else {
                                    firstDodanok -= num;
                                }
                            }

                        }
                    }else{
                        System.out.println("4 dodanky");

                        for (String str : parts) {
                            System.out.println("parts " + str);
                        }

                        for (int j = 1; j < parts.length; j++) {

                            String part = parts[j].trim();

                            System.out.println("part " + part);

                            char[] omg = taskText.toCharArray();

                            for (char ch : omg) {
                                System.out.println("chars " + ch);
                            }
                            if (j == 1) {
                                char sign = taskText.charAt(2);
                                System.out.println("sign1 " + sign);
                                int num = Integer.parseInt(part);
                                if (sign == '+') {
                                    firstDodanok += num;
                                } else {
                                    firstDodanok -= num;
                                }
                            } else if (j==2){
                                char sign = taskText.charAt(6);
                                System.out.println("sign2 " + sign);
                                String part3 = parts[j].trim();
                                System.out.println("part3 " + part3);
                                int num = Integer.parseInt(part3);
                                if (sign == '+') {
                                    firstDodanok += num;
                                } else {
                                    firstDodanok -= num;
                                }
                            }else{
                                char sign = taskText.charAt(10);
                                System.out.println("ЗНАКККККККК "+ sign);
                                String part4 = parts[j].trim();
                                int num = Integer.parseInt(part4);
                                if (sign == '+') {
                                    firstDodanok += num;
                                } else {
                                    firstDodanok -= num;
                                }
                            }

                        }
                    }

                    if (answer.equals(Integer.toString(firstDodanok))) {
                        JOptionPane.showMessageDialog(tasksFrame, "task " + (i + 1) + " correct");
                    } else {
                        JOptionPane.showMessageDialog(tasksFrame, "task " + (i + 1) + " ERROR");
                    }
                }

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

//        playAgainButton = new JButton("Грати знову");
//        playAgainButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Викликайте метод для початку нової гри
//                stop();
//            }
//        });

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
            int numberTwo = rNum.nextInt(0, maxNum + 1);
            int numberThree = rNum.nextInt(0, maxNum + 1);
            int numberFour = rNum.nextInt(0, maxNum + 1);

            int signFirst = rNum.nextInt(0, 2);
            int signSecond = rNum.nextInt(0, 2);
            int signThird = rNum.nextInt(0, 2);

            int numOfNumbers = rNum.nextInt(2, 5);

            int sum = calculateSum(numberOne, numberTwo, numberThree, numberFour, signFirst, signSecond, signThird, numOfNumbers);

            if (sum >= 0 && sum <= maxNum) {
                taskBuilder.append(numberOne);
                if (signFirst == 0) {
                    taskBuilder.append(" - ");
                } else {
                    taskBuilder.append(" + ");
                }
                taskBuilder.append(numberTwo);

                if (numOfNumbers >= 3) {
                    if (signSecond == 0) {
                        taskBuilder.append(" - ").append(numberThree);
                    } else {
                        taskBuilder.append(" + ").append(numberThree);
                    }
                }
                if (numOfNumbers == 4) {
                    if (signThird == 0) {
                        taskBuilder.append(" - ").append(numberFour);
                    } else {
                        taskBuilder.append(" + ").append(numberFour);
                    }
                }
                taskBuilder.append(" = ");

                JTextField answerField = new JTextField(5);
                answerFields.add(answerField);
                JLabel taskLabel = new JLabel(taskBuilder.toString());
                taskLabels.add(taskLabel);
            }else{
                numTasks++;
            }
        }
    }

    private int calculateSum(int num1, int num2, int num3, int num4, int sign1, int sign2, int sign3, int numOfNumbers) {
        int sum = (sign1 == 0) ? num1 - num2 : num1 + num2;

        if (numOfNumbers >= 3) {
            sum = (sign2 == 0) ? sum - num3 : sum + num3;
        }
        if (numOfNumbers == 4) {
            sum = (sign3 == 0) ? sum - num4 : sum + num4;
        }
        return sum;
    }


    private void checkAnswers() {

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

    private void stop() {
        this.getContentPane().removeAll();
//        taskPanel.removeAll();
//        taskAndAnswer.removeAll();
//        tasksFrame.removeAll();
//        //tasksFrame.dispose();
//
//        maxNumber.setText("");
//        numOfTasks.setText("");
//        answer.setText("");
//        taskLabels.clear();
//        answerFields.clear();
//        corrrectAnswers = 0;
//
//
//        generateTask(maxN, tN);
//
//        validate();
//        repaint();
    }

}
