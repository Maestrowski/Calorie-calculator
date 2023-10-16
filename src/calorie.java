import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calorie extends JFrame{
    private JPanel mPanel;
    private JTextField jWeight;
    private JTextField jHeight;
    private JTextField jAge;
    private JTextField jGender;
    private JButton bClose;
    private JButton bSubmit;

    private JLabel jResults;

    public int choice(String d,String[] a) {
        Object[] options = new Object[a.length];
        for (int i = 0; i < a.length; i++) {
            options[i] = i + 1;
        }
        int result = JOptionPane.showOptionDialog(null, d, "Exercise Rate",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (result >= 0 && result < options.length) {
            return result + 1;
        }
        return -1; // indicate an error or no selection
    }

    public calorie() {
        mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.Y_AXIS));

        Color myColor = new Color(0,255,222);
        JLabel jHeightLabel = new JLabel("Height:");
        jHeightLabel.setForeground(myColor);
        JLabel jWeightLabel = new JLabel("Weight:");
        jWeightLabel.setForeground(myColor);
        JLabel jAgeLabel = new JLabel("Age:");
        jAgeLabel.setForeground(myColor);
        JLabel jGenderLabel = new JLabel("Gender (M/F):");
        jGenderLabel.setForeground(myColor);

        JPanel heightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        heightPanel.setBackground(new Color(0,128,128));
        heightPanel.add(jHeightLabel);
        heightPanel.add(jHeight);

        JPanel weightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        weightPanel.setBackground(new Color(0,128,128));
        weightPanel.add(jWeightLabel);
        weightPanel.add(jWeight);

        JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        agePanel.setBackground(new Color(0,128,128));
        agePanel.add(jAgeLabel);
        agePanel.add(jAge);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(new Color(0,128,128));
        genderPanel.add(jGenderLabel);
        genderPanel.add(jGender);

        jHeight.setPreferredSize(new Dimension(150,25));
        jWeight.setPreferredSize(new Dimension(150,25));
        jAge.setPreferredSize(new Dimension(150,25));
        jGender.setPreferredSize(new Dimension(150,25));
        


        mPanel.add(heightPanel);
        mPanel.add(weightPanel);
        mPanel.add(agePanel);
        mPanel.add(genderPanel);

        mPanel.add(bClose);
        mPanel.add(bSubmit);
        mPanel.add(jResults);


        bClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        bSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double weight = Double.parseDouble(jWeight.getText());
                    double height = Double.parseDouble(jHeight.getText());
                    int age = Integer.parseInt(jAge.getText());
                    String gender = jGender.getText();

                    String[] choices = {"0 days","1-3 days per week", "3-5 days per week", "6-7 days per week","Intense exercise"};
                    int exercise = choice("How much do you exercise",choices);

                    String[] gainOrLose = {"Gain","Maintain", "Lose"};
                    int weightGoal = choice("Do you want to gain, maintain or lose weight",gainOrLose);

                    double bmr;
                    if (gender.equalsIgnoreCase("M")) {
                        bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
                    } else {
                        bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
                    }

                    double dailyCalories = 0;
                    if (exercise == 1) {
                        dailyCalories = bmr * 1.2;
                    }
                    else if (exercise == 2) {
                        dailyCalories = bmr * 1.375;
                    }
                    else if (exercise == 3) {
                        dailyCalories = bmr * 1.55;
                    }
                    else if (exercise == 4) {
                        dailyCalories = bmr * 1.9;
                    }
                    else if (exercise == 5) {
                        dailyCalories = bmr * 1.9;
                    }
                    else if (exercise == -1) {
                        jResults.setText("Your daily calorie needs are unknown - You didn't highlight all the details.");
                    }
                    else {
                        jResults.setText("Your daily calorie needs are unknown - You didn't highlight all the details.");
                    }

                    double calorieGoal;
                    if (weightGoal == 1) {
                        calorieGoal = dailyCalories + 500;
                        jResults.setText("To gain weight you should eat minimum: " + calorieGoal + " kcal.");
                    }
                    else if (weightGoal == 2) {
                        jResults.setText("To maintain your weight you should eat approximately: " + dailyCalories + " kcal.");
                    }
                    else if (weightGoal == 3) {
                        calorieGoal = dailyCalories - 500;
                        jResults.setText("To lose weight you should eat: " + calorieGoal + " kcal.");
                    }
                    else {
                        jResults.setText("Your daily calorie needs are unknown - You didn't highlight all the details");
                    }
                } catch (NumberFormatException ex) {
                    jResults.setText("Please enter a valid number for weight, height, and age");
                }
            }
        });

    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle(f.getClass().getSimpleName());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600, 400);
        f.setLocationRelativeTo(null);

        calorie c = new calorie();
        f.add(c.mPanel);

        f.setVisible(true);
    }



}
