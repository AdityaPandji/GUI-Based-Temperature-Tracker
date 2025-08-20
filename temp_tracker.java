import javax.swing.; 
import java.awt.; 
import java.awt.event.*;
public class TempTrackerGUI extends JFrame implements ActionListener { JTextField tempFields[] = new JTextField[7]; JButton calculateButton; JLabel resultLabel;
TempTrackerGUI() {
    setTitle("Temperature Tracker");
    setSize(450, 450);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout(10, 10));

    JLabel instruction = new JLabel("Enter temperatures for 7 days:", SwingConstants.CENTER);
    add(instruction, BorderLayout.NORTH);

    JPanel inputPanel = new JPanel(new GridLayout(7, 2, 5, 5));
    for (int i = 0; i < 7; i++) {
        inputPanel.add(new JLabel("Day " + (i + 1) + ":"));
        tempFields[i] = new JTextField();
        inputPanel.add(tempFields[i]);
    }
    add(inputPanel, BorderLayout.CENTER);

    calculateButton = new JButton("Calculate");
    resultLabel = new JLabel("", SwingConstants.CENTER);
    calculateButton.addActionListener(this);

    JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 5, 5));
    bottomPanel.add(calculateButton);
    bottomPanel.add(resultLabel);
    add(bottomPanel, BorderLayout.SOUTH);

    setVisible(true);
}

public void actionPerformed(ActionEvent e) {
    float sum = 0, max, min;
    int maxDay = 0, minDay = 0;
    float[] temperatures = new float[7];

    for (int i = 0; i < 7; i++) {
        try {
            temperatures[i] = Float.parseFloat(tempFields[i].getText());
            sum += temperatures[i];
        } catch (NumberFormatException ex) {
            resultLabel.setText("Enter valid numbers!");
            return;
        }
    }

    float avg = sum / 7;
    max = min = temperatures[0];

    for (int i = 1; i < 7; i++) {
        if (temperatures[i] > max) {
            max = temperatures[i];
            maxDay = i;
        }
        if (temperatures[i] < min) {
            min = temperatures[i];
            minDay = i;
        }
    }

    resultLabel.setText("Average: " + avg + " | Maximum: " + max + " (Day " + (maxDay + 1) + ")" +
                        " | Minimum: " + min + " (Day " + (minDay + 1) + ")");
}

public static void main(String args[]) {
    new TempTrackerGUI();
}
}
