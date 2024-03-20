import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Tasks {

    /*

        public Tasks(String name) {
            super(name);
            setSize(500, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(3, 2));

            init();
            JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            loginPanel.add(doTasks);
            add(loginPanel);
        }

        public void init() {
            doTasks = new JLabel("Do the tasks");

        }
    */
    public static void generateTask(int maxNum, int numTasks) {
        Random rNum = new Random();
        for (int i = 0; i < numTasks; i++) {
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
                    System.out.println("sum of three " + newSum);
                }
            } else {
                if (sum < 0 || sum > maxNum) {
                    numTasks++;
                } else {
                    System.out.println("sum " + sum);
                }
            }
        }
    }

    private static boolean checkNum(int number, int maxNumber) {
        if (number <= maxNumber && number >= 0) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {

        generateTask(5, 3);

    }
}
