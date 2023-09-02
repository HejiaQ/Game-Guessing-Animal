import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Math.floor;
import static java.lang.Math.random;


public class GUI extends JFrame {
    private JTextField textField;
    private JLabel resultLabel;
    private JLabel chanceCount;
    private JLabel scoreCount;
    private JLabel pastScore;
    String[][] animals ={
            {"dog", "it wags tail", "it is a common pet", "it barks"},
            {"cat", "It is related to tiger", "it walks quietly", "it goes Meow"},
            {"whale", "it lives in water", "it has fins", "it is a mammal"},
            {"shark", "it has sharp teeth", "it is scary", "it is huge"},
            {"racoon", "it steals food from garbage cans", "it has black and white tail", "it carries many virus"},
            {"rabbit", "it has long ears", "it has large front tooth", "it eats carrots"}
    };
    int random_num = (int) floor(random() * (animals.length));
    String predefinedText = animals[random_num][0];
    int i = 1;
    public int score = 0;




    public GUI() {
        textField = new JTextField(20);
        resultLabel = new JLabel("Guess what animal I'm thinking about?");
        chanceCount = new JLabel("Chance left: 4");
        scoreCount = new JLabel("Score: 0");
        String readScore;
        {
            try {
                BufferedReader fileReader = new BufferedReader(new FileReader("score.txt"));
                readScore = fileReader.readLine();
                fileReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        pastScore = new JLabel("Past Score: " + readScore);

        textField.setFont(new Font("DialogInput", Font.PLAIN, 20));
        chanceCount.setFont(new Font("Dialog", Font.ITALIC, 18));
        scoreCount.setFont(new Font("Dialog", Font.ITALIC, 18));
        pastScore.setFont(new Font("Dialog", Font.ITALIC, 18));
        resultLabel.setFont(new Font("Dialog", Font.PLAIN, 20));


        JButton submitButton = new JButton("submit");
        JButton replayButton = new JButton("restart");
        replayButton.setEnabled(false);
        replayButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                random_num = (int) floor(random() * (animals.length));
                predefinedText = animals[random_num][0];
                chanceCount.setText("Chance left: 4");
                resultLabel.setText("Guess what animal I'm thinking about?");
                i = 1;
                submitButton.setEnabled(true);
                replayButton.setEnabled(false);

            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userAnswer = textField.getText();
                if ((userAnswer.equals(predefinedText))) {
                    resultLabel.setText("Correct!");
                    score++;
                    scoreCount.setText("Score: " + score);
                    submitButton.setEnabled(false);
                    replayButton.setEnabled(true);
                    chanceCount.setText("Chance left: " + (4 - i));

                } else if (!userAnswer.equals(predefinedText) && (i <= 3)) {
                    resultLabel.setText("No, I will give a clue:" + animals[random_num][i]);
                    chanceCount.setText("Chance left: " + (4 - i));
                    i++;
                } else {
                    chanceCount.setText("Chance left: 0");
                    resultLabel.setText("No. Sorry, game over. The right answer is " + predefinedText);
                    submitButton.setEnabled(false);
                    replayButton.setEnabled(true);

                }

            }
        });


        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(textField);
        panel.add(submitButton);
        panel.add(resultLabel);
        panel.add(chanceCount);
        panel.add(scoreCount);
        panel.add(replayButton);
        panel.add(pastScore);

        panel.setBackground(new Color(214, 190, 250));
        Dimension size = chanceCount.getPreferredSize();
        chanceCount.setBounds(350, 10, size.width, size.height);
        Dimension size_2 = resultLabel.getPreferredSize();
        resultLabel.setBounds(50, 100, 500, size_2.height);
        Dimension size_3 = textField.getPreferredSize();
        textField.setBounds(10, 150, size_3.width, size_3.height);
        scoreCount.setBounds(200, 10, 100, 20);
        submitButton.setBounds(275, 160, 100, 20);
        replayButton.setBounds(380, 160, 100, 20);
        pastScore.setBounds(10, 10, 150, 20);


        JFrame frame = new JFrame("Game of guessing animals");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        frame.setVisible(true);

    }
}
