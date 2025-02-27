package GamUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import GameLogic.WordGameLogic;

public class WordGameFrontend extends JFrame {
    private WordGameLogic gameLogic;
    private JLabel lettersLabel;
    private JTextField wordInput;
    private JLabel scoreLabel;
    private JLabel messageLabel; // Label for displaying feedback
    JPanel lettersPanel;
    private int score = 0;

    public WordGameFrontend() {
        // Initialize game logic with a number of words
        gameLogic = new WordGameLogic(5);

        // Set up the JFrame
        setTitle("Word Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(970, 530);
        setLayout(new BorderLayout());

        // Panel to display letters
        lettersPanel = new JPanel(new GridLayout(3,3));
        lettersLabel = new JLabel("Letters will appear here", SwingConstants.CENTER);
        lettersLabel.setFont(new Font("Arial", Font.BOLD, 18));
        lettersPanel.add(lettersLabel);
        add(lettersPanel, BorderLayout.NORTH);

        // Panel for input and buttons
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        wordInput = new JTextField(20);
        wordInput.setFont(new Font("Arial",Font.PLAIN,18));
        topPanel.add(wordInput);

        JButton submitButton = new JButton("Submit");
        topPanel.add(submitButton);

        JButton newGameButton = new JButton("New Game");
        topPanel.add(newGameButton);

        inputPanel.add(topPanel, BorderLayout.NORTH); // Buttons and text field at the top

        // Label for displaying feedback messages
        messageLabel = new JLabel(" ", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(messageLabel, BorderLayout.CENTER); // Message label below the buttons

        add(inputPanel, BorderLayout.CENTER);
        // Panel to display score
        JPanel scorePanel = new JPanel();
        scoreLabel = new JLabel("Score: 0");
        scorePanel.add(scoreLabel);
        add(scorePanel, BorderLayout.SOUTH);

        // Add action listeners
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = wordInput.getText().trim();
                if (gameLogic.ValidateWord(word)) {
                	 LinkedList<Character> letters = gameLogic.GenereteLetters();
                     
                     lettersPanel.removeAll(); 
                    
                     for (Character c : letters) {
                     	JButton letterButton=new JButton(c.toString()); 
                     	 letterButton.setFont(new Font("Arial", Font.BOLD, 20));
                     	 lettersPanel.add(letterButton);
                     }
                     
                     lettersPanel.revalidate();
                     lettersPanel.repaint();
                    score += word.length();
                    scoreLabel.setText("Score: " + score);
                    wordInput.setText("");
                    // Update the message label with green text
                    messageLabel.setText("Correct word!");
                    messageLabel.setForeground(Color.green);
                } else {
                    // Update the message label with red text
                    messageLabel.setText("Invalid word!");
                    messageLabel.setForeground(Color.RED);
                }
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        // Start the first game
        startNewGame();
    }

    private void startNewGame() {
        gameLogic.SetRandomWords();
        System.out.println(gameLogic.getWords().toString());
        LinkedList<Character> letters = gameLogic.GenereteLetters();
        
        lettersPanel.removeAll(); 
       
        for (Character c : letters) {
        	JButton letterButton=new JButton(c.toString()); 
        	 letterButton.setFont(new Font("Arial", Font.BOLD, 20));
        	 lettersPanel.add(letterButton);
        }
        
        lettersPanel.revalidate();
        lettersPanel.repaint();
        score = 0;
        scoreLabel.setText("Score: 0");
        wordInput.setText("");
        messageLabel.setText(" "); // Clear the message label
    }


}
