package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main_class extends JFrame {

    boolean nightModeOn = false;
    JToggleButton nightModeToggle;


    Color lightBG = null;
    Color lightFont = Color.BLACK;
    Color lightButtonBG = Color.BLACK;
    Color lightButtonFG = Color.WHITE;

    Color darkBG = new Color(40, 40, 40);
    Color darkFont = Color.WHITE;
    Color darkButtonBG = Color.LIGHT_GRAY;
    Color darkButtonFG = Color.BLACK;

    Main_class() {

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1120, 630, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 1120, 630);
        background.setLayout(null);

        JLabel heading = new JLabel("Employee Management System");
        heading.setBounds(340, 155, 500, 40);
        heading.setFont(new Font("Raleway", Font.BOLD, 25));
        heading.setForeground(Color.BLACK);
        background.add(heading);


        JButton exit = new JButton("Exit");
        exit.setBounds(1027, 3, 75, 27);
        exit.setForeground(lightButtonFG);
        exit.setBackground(lightButtonBG);
        background.add(exit);
        exit.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are You Sure You Want To Exit The 'Employee Management System' Program...??",
                    "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        JButton add = new JButton("Add Employee");
        add.setBounds(335, 270, 150, 40);
        add.setForeground(lightButtonFG);
        add.setBackground(lightButtonBG);
        background.add(add);
        add.addActionListener(e -> {
            new AddEmployee();
            setVisible(false);
        });

        JButton view = new JButton("View Employee");
        view.setBounds(565, 270, 150, 40);
        view.setForeground(lightButtonFG);
        view.setBackground(lightButtonBG);
        background.add(view);
        view.addActionListener(e -> {
            new View_Employee();
            setVisible(false);
        });

        JButton rem = new JButton("Remove Employee");
        rem.setBounds(440, 370, 150, 40);
        rem.setForeground(lightButtonFG);
        rem.setBackground(lightButtonBG);
        background.add(rem);
        rem.addActionListener(e -> new RemoveEmployee());

        nightModeToggle = new JToggleButton("Night Mode");
        nightModeToggle.setBounds(13, 550, 100, 27);
        nightModeToggle.setBackground(lightButtonBG);
        nightModeToggle.setForeground(lightButtonFG);
        background.add(nightModeToggle);
        nightModeToggle.addItemListener(e -> toggleNightMode(
                background, heading, add, view, rem, nightModeToggle, exit, nightModeToggle.isSelected()
        ));

        add(background);

        setSize(1120, 630);
        setLocation(250, 100);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void toggleNightMode(JLabel background, JLabel heading,
                                 JButton add, JButton view, JButton rem,
                                 JToggleButton toggleButton, JButton exitButton, boolean enabled) {

        nightModeOn = enabled;

        Color bg = enabled ? darkBG : lightBG;
        Color fg = enabled ? darkFont : lightFont;
        Color buttonBG = enabled ? darkButtonBG : lightButtonBG;
        Color buttonFG = enabled ? darkButtonFG : lightButtonFG;

        getContentPane().setBackground(bg);
        background.setBackground(bg);

        add.setBackground(buttonBG);
        add.setForeground(buttonFG);
        view.setBackground(buttonBG);
        view.setForeground(buttonFG);
        rem.setBackground(buttonBG);
        rem.setForeground(buttonFG);
        toggleButton.setBackground(buttonBG);
        toggleButton.setForeground(buttonFG);
        exitButton.setBackground(buttonBG);
        exitButton.setForeground(buttonFG);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new Main_class();
    }
}
