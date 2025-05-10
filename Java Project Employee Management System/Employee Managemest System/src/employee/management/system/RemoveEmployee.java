package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RemoveEmployee extends JFrame implements ActionListener {
    Choice choiceEMPID;
    JButton delete, back;
    JLabel textName, textPhone, textEmail;
    JToggleButton nightModeToggle;
    boolean isNightMode = false;


    Color lightBG = new Color(255, 255, 255);
    Color lightFont = Color.BLACK;
    Color lightButtonBG = Color.BLACK;
    Color lightButtonFG = Color.WHITE;

    Color darkBG = new Color(40, 40, 40);
    Color darkFont = Color.GRAY;
    Color darkButtonBG = Color.LIGHT_GRAY;
    Color darkButtonFG = Color.BLACK;

    RemoveEmployee() {
        setLayout(null);
        getContentPane().setBackground(lightBG);


        nightModeToggle = new JToggleButton("Night Mode");
        nightModeToggle.setBounds(880, 330, 100, 27);
        nightModeToggle.setBackground(lightButtonBG);
        nightModeToggle.setForeground(lightButtonFG);
        nightModeToggle.addItemListener(e -> toggleNightMode(nightModeToggle.isSelected()));
        add(nightModeToggle);

        JLabel label = new JLabel("Employee ID");
        label.setBounds(50, 50, 100, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(label);

        choiceEMPID = new Choice();
        choiceEMPID.setBounds(200, 50, 150, 30);
        add(choiceEMPID);

        textName = new JLabel();
        textName.setBounds(200, 100, 100, 30);
        add(textName);

        textPhone = new JLabel();
        textPhone.setBounds(200, 150, 100, 30);
        add(textPhone);

        textEmail = new JLabel();
        textEmail.setBounds(200, 200, 100, 30);
        add(textEmail);

        JLabel labelName = new JLabel("Name");
        labelName.setBounds(50, 100, 100, 30);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelName);

        JLabel labelPhone = new JLabel("Phone");
        labelPhone.setBounds(50, 150, 100, 30);
        labelPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelPhone);

        JLabel labelEmail = new JLabel("Email");
        labelEmail.setBounds(50, 200, 100, 30);
        labelEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelEmail);

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM employee");
            while (resultSet.next()) {
                choiceEMPID.add(resultSet.getString("empId"));
            }

            ResultSet rs = c.statement.executeQuery("SELECT * FROM employee WHERE empId = '" + choiceEMPID.getSelectedItem() + "'");
            while (rs.next()) {
                textName.setText(rs.getString("name"));
                textPhone.setText(rs.getString("phone"));
                textEmail.setText(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        choiceEMPID.addItemListener(e -> {
            try {
                conn c = new conn();
                ResultSet resultSet = c.statement.executeQuery("SELECT * FROM employee WHERE empId = '" + choiceEMPID.getSelectedItem() + "'");
                while (resultSet.next()) {
                    textName.setText(resultSet.getString("name"));
                    textPhone.setText(resultSet.getString("phone"));
                    textEmail.setText(resultSet.getString("email"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        delete = new JButton("Delete");
        delete.setBounds(80, 300, 100, 30);
        delete.setBackground(lightButtonBG);
        delete.setForeground(lightButtonFG);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("Back");
        back.setBounds(220, 300, 100, 30);
        back.setBackground(lightButtonBG);
        back.setForeground(lightButtonFG);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        JLabel img = new JLabel(new ImageIcon(i2));
        img.setBounds(700, 80, 200, 200);
        add(img);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icons/rback.png"));
        Image i12 = i11.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i12));
        image.setBounds(0, 0, 1120, 630);
        add(image);

        setSize(1000, 400);
        setLocation(300, 150);
        setVisible(true);
    }

    private void toggleNightMode(boolean enabled) {
        isNightMode = enabled;

        Color bg = enabled ? darkBG : lightBG;
        Color fg = enabled ? darkFont : lightFont;
        Color buttonBG = enabled ? darkButtonBG : lightButtonBG;
        Color buttonFG = enabled ? darkButtonFG : lightButtonFG;


        getContentPane().setBackground(bg);


        for (Component comp : getContentPane().getComponents()) {
            if (comp instanceof JLabel && !(comp instanceof Choice)) {
                comp.setForeground(fg);
            }
            if (comp instanceof JButton || comp instanceof JToggleButton) {
                comp.setBackground(buttonBG);
                comp.setForeground(buttonFG);
            }


            if (comp instanceof Choice) {
                if (enabled) {
                    comp.setBackground(Color.BLACK);
                    comp.setForeground(Color.WHITE);
                } else {
                    comp.setBackground(lightBG);
                    comp.setForeground(lightFont);
                }
            }
        }

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            try {
                conn c = new conn();
                String query = "DELETE FROM employee WHERE empId='" + choiceEMPID.getSelectedItem() + "'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Employee Deleted Successfully");
                setVisible(false);
                new Main_class();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
            new Main_class();
        }
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}
