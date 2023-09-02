// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


import java.io.*;

public class Main {


    public static void main(String[] args) {
        // Load the score from the file when the program starts




        GUI mygui = new GUI();



        // Register a shutdown hook to save the score before the program exits
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                BufferedWriter fileWriter = new BufferedWriter(new FileWriter("score.txt"));
                fileWriter.write(String.valueOf(mygui.score));
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Score saved before exiting.");
        }));

        // Print the current score

    }
}
