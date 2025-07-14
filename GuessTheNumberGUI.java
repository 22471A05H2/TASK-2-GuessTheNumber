import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GuessTheNumberGUI {

    private int secretNumber;
    private int attempts;
    private JFrame frame;
    private JTextField guessField;
    private JLabel messageLabel;
    private JLabel attemptsLabel;
    private JButton guessButton;

    public GuessTheNumberGUI() {
        // Initialize game
        secretNumber = new Random().nextInt(100) + 1;
        attempts = 0;

        // Create frame
        frame = new JFrame("🎯 Guess the Number Game - Task");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(30, 30, 60));  // Dark background

        // Header
        JLabel titleLabel = new JLabel("🎯 TASK: Number Guessing Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0x00E5FF));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(40, 40, 80));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        JLabel instructionLabel = new JLabel("I'm thinking of a number between 1 and 100.");
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        instructionLabel.setForeground(Color.WHITE);
        centerPanel.add(instructionLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        guessField = new JTextField();
        guessField.setMaximumSize(new Dimension(200, 30));
        guessField.setHorizontalAlignment(JTextField.CENTER);
        guessField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        centerPanel.add(guessField);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        guessButton = new JButton("Submit Guess");
        guessButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        guessButton.setBackground(new Color(0x00E5FF));
        guessButton.setForeground(Color.BLACK);
        guessButton.setFocusPainted(false);
        guessButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(guessButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        messageLabel = new JLabel("Enter your guess and click Submit.", SwingConstants.CENTER);
        messageLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(messageLabel);

        attemptsLabel = new JLabel("Attempts: 0", SwingConstants.CENTER);
        attemptsLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        attemptsLabel.setForeground(Color.LIGHT_GRAY);
        attemptsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(attemptsLabel);

        frame.add(centerPanel, BorderLayout.CENTER);

        // Button action
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processGuess();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void processGuess() {
        String input = guessField.getText().trim();
        try {
            int guess = Integer.parseInt(input);
            attempts++;
            attemptsLabel.setText("Attempts: " + attempts);

            if (guess < secretNumber) {
                messageLabel.setText("📉 Too low! Try again.");
                messageLabel.setForeground(new Color(255, 150, 0));
            } else if (guess > secretNumber) {
                messageLabel.setText("📈 Too high! Try again.");
                messageLabel.setForeground(new Color(255, 150, 0));
            } else {
                messageLabel.setText("🎉 Correct! You guessed it in " + attempts + " attempts!");
                messageLabel.setForeground(new Color(0, 255, 127));
                guessButton.setEnabled(false);
            }

            guessField.setText("");
            guessField.requestFocus();

        } catch (NumberFormatException ex) {
            messageLabel.setText("❌ Please enter a valid number!");
            messageLabel.setForeground(Color.RED);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuessTheNumberGUI());
    }
}
