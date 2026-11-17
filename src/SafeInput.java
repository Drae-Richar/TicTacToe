import java.util.Scanner;

public class SafeInput {

    // get a text that is not empty
    public static String getNonZeroLenString(Scanner in, String prompt) {
        String retString = "";
        boolean done = false;
        do {
            System.out.println(prompt);
            retString = in.nextLine();
            if (retString.length() > 0) {
                done = true;
            }
        } while (!done);
        return retString;
    }

    // validate an integer
    public static int getInt(Scanner in, String prompt) {
        int retVal = 0;
        String trash = "";
        boolean done = false;
        do {
            System.out.println(prompt);
            if (in.hasNextInt()) {
                retVal = in.nextInt();
                in.nextLine(); //clear the buffer
                done = true;
            } else {
                trash = in.nextLine(); //clear the buffer
                System.out.println("Sorry, you must enter an integer");
            }
        } while (!done);
        return retVal;
    }

    public static double getDouble(Scanner pipe, String prompt) {

        double retValue = 0.0;
        boolean valid = false;
        String trash = "";

        do {
            IO.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retValue = pipe.nextDouble();
                pipe.nextLine();
                valid = true;
            } else {
                trash = pipe.nextLine();
                pipe.nextLine();
                IO.print("Enter a valid number not " + trash + ". ");
            }

        } while (!valid);
        return retValue;

    }
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {

        int retValue;
        boolean valid;


        do {
            retValue = getInt(pipe, prompt + "[" + low + "-" + high + "]");
            valid = retValue >= low && retValue <= high;

            if (!valid) {
                IO.print("Please enter a value from " + low + " to " + high + ".");
            }
        } while (!valid);
        return retValue;
    }
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double retValue;
        boolean valid;

        do {
            retValue = getDouble(pipe, prompt + "[" + low + "-" + high + "]");

            valid = retValue >= low && retValue <= high;
            if (!valid) {
                IO.print("Please enter a value from " + low + " to " + high + ".");
            }
        } while (!valid);
        return retValue;

    }
    public static boolean getYNconfirm (Scanner pipe, String prompt) {
        String response;

        do{
            IO.print ("\n" + prompt + "n [Y/N]: ");
            response = pipe.nextLine().trim();

            if (response.equals("Y")) {
                return true;
            }
            else if(response.equals("N")) {
                return false;
            }
            else {
                IO.print("Please enter Y or N not");
            }
        } while ( true);
    }
    public static String getRegExString (Scanner pipe, String prompt, String regEx) {
        String response;
        boolean valid;
        do {
            IO.print("\n" + prompt + ": ");
            response = pipe.nextLine();

            valid = response.matches(regEx);

            if (!valid) {
                IO.println("Invalid input. Please try again.");

            }
        } while (!valid);
        return response;
    }
    public static void prettyHeader(String msg)
    {
        int width = 60;
        int innerWidth = width - 6;

        int leftSpaces = (innerWidth - msg.length()) / 2;
        int rightSpaces = innerWidth - msg.length() - leftSpaces;

        for (int i = 0; i < width; i++)
        {
            IO.print("*");
        }
        IO.println();

        IO.print("***");

        for (int i = 0; i < leftSpaces; i++)
        {
            IO.print(" ");
        }

        IO.print(msg);

        for (int i = 0; i < rightSpaces; i++)
        {
            IO.print(" ");
        }

        IO.println("***");

        for (int i = 0; i < width; i++)
        {
            IO.print("*");
        }
        IO.println();
    }
}