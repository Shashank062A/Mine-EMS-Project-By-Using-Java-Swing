package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class UpdateEmployee extends JFrame implements ActionListener {
    JTextField tname, tfname, taddress, tphone, temail, tsalary, tdesignation;
    JLabel tempid, taadhar, tdob;
    JButton add, back;
    JToggleButton nightModeToggle;
    String number;
    JComboBox<String> Boxeducation;

    boolean isNightMode = false;


    Color lightBG = new Color(128, 218, 235);
    Color lightFieldBG = new Color(255, 250, 250);
    Color lightFont = Color.BLACK;
    Color lightButtonBG = Color.BLACK;
    Color lightButtonFG = Color.WHITE;

    Color darkBG = new Color(40, 40, 40);
    Color darkFieldBG = Color.BLACK;  //
    Color darkFont = Color.WHITE;
    Color darkButtonBG = Color.LIGHT_GRAY;
    Color darkButtonFG = Color.BLACK;

    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            if (!isNightMode) {
                Color color1 = new Color(144, 238, 144);
                Color color2 = new Color(135, 206, 235);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
            } else {
                Color color1 = new Color(0, 15, 63);
                Color color2 = new Color(61, 7, 72);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
            }
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    UpdateEmployee(String number) {
        this.number = number;

        GradientPanel contentPane = new GradientPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        nightModeToggle = new JToggleButton("Night Mode");
        nightModeToggle.setBounds(10, 627, 100, 27);
        nightModeToggle.setBackground(lightButtonBG);
        nightModeToggle.setForeground(lightButtonFG);
        nightModeToggle.addItemListener(e -> toggleNightMode(nightModeToggle.isSelected()));
        add(nightModeToggle);

        JLabel heading = new JLabel("Update Employee Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        add(heading);

        JLabel lname = new JLabel("Name");
        lname.setBounds(50, 150, 150, 30);
        lname.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(lname);
        tname = new JTextField();
        tname.setBounds(200, 150, 150, 30);
        add(tname);

        JLabel lfname = new JLabel("Father's Name");
        lfname.setBounds(400, 150, 150, 30);
        lfname.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(lfname);
        tfname = new JTextField();
        tfname.setBounds(600, 150, 150, 30);
        add(tfname);

        JLabel ldob = new JLabel("Date Of Birth");
        ldob.setBounds(50, 200, 150, 30);
        ldob.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(ldob);
        tdob = new JLabel();
        tdob.setBounds(200, 200, 150, 30);
        tdob.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(tdob);

        JLabel lsalary = new JLabel("Salary");
        lsalary.setBounds(400, 200, 150, 30);
        lsalary.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(lsalary);
        tsalary = new JTextField();
        tsalary.setBounds(600, 200, 150, 30);
        add(tsalary);

        JLabel laddress = new JLabel("Address");
        laddress.setBounds(50, 250, 150, 30);
        laddress.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(laddress);
        taddress = new JTextField();
        taddress.setBounds(200, 250, 150, 30);
        add(taddress);

        JLabel lphone = new JLabel("Phone");
        lphone.setBounds(400, 250, 150, 30);
        lphone.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(lphone);
        tphone = new JTextField();
        tphone.setBounds(600, 250, 150, 30);
        add(tphone);

        JLabel lemail = new JLabel("Email");
        lemail.setBounds(50, 300, 150, 30);
        lemail.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(lemail);
        temail = new JTextField();
        temail.setBounds(200, 300, 150, 30);
        add(temail);

        JLabel leducation = new JLabel("Highest Education");
        leducation.setBounds(400, 300, 180, 30);
        leducation.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(leducation);
        String[] items = {"10th", "12th", "Diploma", "BA", "BBA", "BE", "B.Tech", "BCA", "BSC", "B.Com", "MA", "MBA", "ME", "M.Tech", "MCA", "MSC", "M.Com", "PHD"};
        Boxeducation = new JComboBox<>(items);
        Boxeducation.setBounds(600, 300, 150, 30);
        add(Boxeducation);

        JLabel ldesignation = new JLabel("Designation");
        ldesignation.setBounds(50, 350, 150, 30);
        ldesignation.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(ldesignation);
        tdesignation = new JTextField();
        tdesignation.setBounds(200, 350, 150, 30);
        add(tdesignation);

        JLabel laadhar = new JLabel("Aadhar Number");
        laadhar.setBounds(400, 350, 150, 30);
        laadhar.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(laadhar);
        taadhar = new JLabel();
        taadhar.setBounds(600, 350, 150, 30);
        taadhar.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(taadhar);

        JLabel lid = new JLabel("Employee ID");
        lid.setBounds(50, 400, 150, 30);
        lid.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(lid);
        tempid = new JLabel();
        tempid.setBounds(200, 400, 150, 30);
        tempid.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(tempid);

        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("select * from employee where empId = '" + number + "'");
            while (rs.next()) {
                tname.setText(rs.getString("name"));
                tfname.setText(rs.getString("fname"));
                tdob.setText(rs.getString("dob"));
                taddress.setText(rs.getString("address"));
                tsalary.setText(rs.getString("salary"));
                tphone.setText(rs.getString("phone"));
                temail.setText(rs.getString("email"));
                Boxeducation.setSelectedItem(rs.getString("education"));
                taadhar.setText(rs.getString("addhar"));
                tempid.setText(rs.getString("empId"));
                tdesignation.setText(rs.getString("designation"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        add = new JButton("UPDATE");
        add.setBounds(250, 550, 150, 40);
        add.addActionListener(this);
        add(add);

        back = new JButton("BACK");
        back.setBounds(450, 550, 150, 40);
        back.addActionListener(this);
        add(back);

        toggleNightMode(false);

        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
    }

    private void toggleNightMode(boolean enabled) {
        isNightMode = enabled;

        Color fg = enabled ? darkFont : lightFont;
        Color fieldBG = enabled ? darkFieldBG : lightFieldBG;
        Color buttonBG = enabled ? darkButtonBG : lightButtonBG;
        Color buttonFG = enabled ? darkButtonFG : lightButtonFG;

        for (Component comp : getContentPane().getComponents()) {
            if (comp instanceof JLabel) {
                comp.setForeground(fg);
            }
            if (comp instanceof JTextField) {
                comp.setBackground(fieldBG);
                comp.setForeground(fg);
                ((JTextField) comp).setCaretColor(fg);
            }
            if (comp instanceof JComboBox) {
                comp.setBackground(fieldBG);
                comp.setForeground(fg);
            }
            if (comp instanceof JButton || comp instanceof JToggleButton) {
                comp.setBackground(buttonBG);
                comp.setForeground(buttonFG);
            }
        }

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            try {
                conn c = new conn();
                String query = "update employee set name = '" + tname.getText() + "', fname = '" + tfname.getText() + "', salary = '" + tsalary.getText() + "', address = '" + taddress.getText() + "', phone = '" + tphone.getText() + "', email = '" + temail.getText() + "', education = '" + Boxeducation.getSelectedItem() + "', designation = '" + tdesignation.getText() + "' where empId = '" + number + "'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details Updated Successfully");
                setVisible(false);
                new Main_class();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
            new View_Employee();
        }
    }

    public static void main(String[] args) {
        new UpdateEmployee("");
    }
}
