import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class DataInput {

    private static void writeText(String wr){
        if (wr == null)
            System.out.print("Введіть дані: ");
        else
            System.out.print(wr);
    }

    public static Long getLong() throws IOException{
        String s = getString();
        Long value = Long.valueOf(s);
        return value;
    }

    public static char getChar() throws IOException{
        String s = getString();
        return s.charAt(0);
    }

    public static Integer getInt() {
        while (true) {
           // writeText("integer:");
            String s = getString();

            try {
                Integer value = Integer.valueOf(s);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Це не integer. Спробуйте ще раз.");
            }
        }
    }

    public static String getString() {
        String s = "";
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            s = br.readLine();
        }catch (IOException ex){
            System.out.println("Can't read the string");
            System.exit(0);
        }
        return s;
    }



/*
    public static int decide(int numOfChoice) throws IOException {
        int fate = getInt();
        while (fate < 1 || fate > numOfChoice) {
            System.out.print("Невірне значення! Будь ласка, введіть число від 1 до " + numOfChoice + ": ");
            fate = getInt();
        }
        return fate;
    }
*/

    /*
public static String getString() {
    try {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    } catch (IOException e) {
        e.printStackTrace(); // You might want to log the exception
        return ""; // Return a default value (empty string) in case of an exception
    }
}

    public static Double getDouble() {
        writeText("Enter a double:");
        String s = "";
        try {
            s = getString();
            return Double.valueOf(s);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return null; // Or throw an exception, return a default value, etc.
        }
    }
*/
}