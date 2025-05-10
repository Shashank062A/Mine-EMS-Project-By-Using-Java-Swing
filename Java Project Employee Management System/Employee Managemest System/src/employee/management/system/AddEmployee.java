package employee.management.system;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class AddEmployee extends JFrame implements ActionListener {

    Random ran = new Random();
    int number = ran.nextInt(999999);

    JTextField tname, tfname, taddress, tphone, taadhar, temail, tsalary, tdesignation;
    JLabel tempid, heading;
    JDateChooser tdob;
    JButton add, back, nightModeButton;
    JComboBox<String> Boxeducation;

    boolean nightModeOn = false;


    Color lightBG = new Color(128, 218, 235);
    Color lightFieldBG = new Color(255, 250, 250);
    Color lightFont = Color.BLACK;
    Color lightButtonBG = Color.BLACK;
    Color lightButtonFG = Color.WHITE;

    Color darkBG = new Color(40, 40, 40);
    Color darkFieldBG = new Color(0, 0, 0);
    Color darkFont = Color.WHITE;
    Color darkButtonBG = Color.LIGHT_GRAY;
    Color darkButtonFG = Color.BLACK;


    JLabel[] labels;


    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            if (!nightModeOn) {
                Color color1 = new Color(128, 218, 235); // Top color
                Color color2 = new Color(255, 255, 255); // Bottom color
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
            } else {
                Color color1 = new Color(13, 18, 77, 255);  // Darker top
                Color color2 = new Color(0, 0, 00);  // Darker bottom
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
            }
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    AddEmployee() {
        GradientPanel contentPane = new GradientPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        heading = new JLabel("Add Employee Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        heading.setForeground(lightFont);
        add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(50, 150, 150, 30);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(name);

        tname = new JTextField();
        tname.setBounds(200, 150, 150, 30);
        tname.setBackground(lightFieldBG);
        add(tname);

        JLabel fname = new JLabel("Father's Name");
        fname.setBounds(400, 150, 150, 30);
        fname.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(fname);

        tfname = new JTextField();
        tfname.setBounds(600, 150, 150, 30);
        tfname.setBackground(lightFieldBG);
        add(tfname);

        JLabel dob = new JLabel("Date Of Birth");
        dob.setBounds(50, 200, 150, 30);
        dob.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(dob);

        tdob = new JDateChooser();
        tdob.setBounds(200, 200, 150, 30);
        tdob.setBackground(lightFieldBG);
        tdob.getCalendarButton().setBackground(lightFieldBG);
        add(tdob);

        JLabel salary = new JLabel("Salary");
        salary.setBounds(400, 200, 150, 30);
        salary.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(salary);

        tsalary = new JTextField();
        tsalary.setBounds(600, 200, 150, 30);
        tsalary.setBackground(lightFieldBG);
        add(tsalary);

        JLabel address = new JLabel("Address");
        address.setBounds(50, 250, 150, 30);
        address.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(address);

        taddress = new JTextField();
        taddress.setBounds(200, 250, 150, 30);
        taddress.setBackground(lightFieldBG);
        add(taddress);

        JLabel phone = new JLabel("Phone No.");
        phone.setBounds(400, 250, 150, 30);
        phone.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(phone);

        tphone = new JTextField();
        tphone.setBounds(600, 250, 150, 30);
        tphone.setBackground(lightFieldBG);
        add(tphone);

        JLabel email = new JLabel("Email");
        email.setBounds(50, 300, 150, 30);
        email.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(email);

        temail = new JTextField();
        temail.setBounds(200, 300, 150, 30);
        temail.setBackground(lightFieldBG);
        add(temail);

        JLabel education = new JLabel("Highest Education");
        education.setBounds(400, 300, 180, 30);
        education.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(education);

        String[] items = {"10th", "12th", "Diploma", "BA", "BBA", "BE", "B.Tech", "BCA", "BSC", "B.Com", "MA", "MBA", "ME", "M.Tech", "MCA", "MSC", "M.Com", "PHD"};
        Boxeducation = new JComboBox<>(items);
        Boxeducation.setBackground(lightFieldBG);
        Boxeducation.setBounds(600, 300, 150, 30);
        add(Boxeducation);

        JLabel designation = new JLabel("Designation");
        designation.setBounds(50, 350, 150, 30);
        designation.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(designation);

        tdesignation = new JTextField();
        tdesignation.setBounds(200, 350, 150, 30);
        tdesignation.setBackground(lightFieldBG);
        add(tdesignation);

        JLabel aadhar = new JLabel("Aadhar No.");
        aadhar.setBounds(400, 350, 150, 30);
        aadhar.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(aadhar);

        taadhar = new JTextField();
        taadhar.setBounds(600, 350, 150, 30);
        taadhar.setBackground(lightFieldBG);
        add(taadhar);

        JLabel empid = new JLabel("Employee ID.");
        empid.setBounds(50, 400, 150, 30);
        empid.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(empid);

        tempid = new JLabel("" + number);
        tempid.setBounds(200, 400, 150, 30);
        tempid.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        tempid.setForeground(lightFont);
        add(tempid);

        add = new JButton("ADD");
        add.setBounds(250, 550, 150, 40);
        add.setBackground(lightButtonBG);
        add.setForeground(lightButtonFG);
        add.addActionListener(this);
        add(add);

        back = new JButton("BACK");
        back.setBounds(450, 550, 150, 40);
        back.setBackground(lightButtonBG);
        back.setForeground(lightButtonFG);
        back.addActionListener(this);
        add(back);

        nightModeButton = new JButton("Night Mode");
        nightModeButton.setBounds(13, 620, 100, 27);
        nightModeButton.setBackground(lightButtonBG);
        nightModeButton.setForeground(lightButtonFG);
        nightModeButton.addActionListener(e -> toggleNightMode());
        add(nightModeButton);

        labels = new JLabel[]{name, fname, dob, salary, address, phone, email, education, designation, aadhar, empid};

        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
    }

    private void toggleNightMode() {
        if (!nightModeOn) {
            getContentPane().setBackground(darkBG);
            heading.setForeground(darkFont);
            tempid.setForeground(darkFont);
            for (JLabel label : labels) label.setForeground(darkFont);
            nightModeButton.setBackground(darkButtonBG);
            nightModeButton.setForeground(darkButtonFG);
            add.setBackground(darkButtonBG);
            add.setForeground(darkButtonFG);
            back.setBackground(darkButtonBG);
            back.setForeground(darkButtonFG);
            setFormFieldColors(darkFieldBG, darkFont);
        } else {
            heading.setForeground(lightFont);
            tempid.setForeground(lightFont);
            for (JLabel label : labels) label.setForeground(lightFont);
            nightModeButton.setBackground(lightButtonBG);
            nightModeButton.setForeground(lightButtonFG);
            add.setBackground(lightButtonBG);
            add.setForeground(lightButtonFG);
            back.setBackground(lightButtonBG);
            back.setForeground(lightButtonFG);
            setFormFieldColors(lightFieldBG, lightFont);
        }

        nightModeOn = !nightModeOn;
        repaint(); // Refresh background
    }

    private void setFormFieldColors(Color bgColor, Color fgColor) {
        JTextField[] fields = {tname, tfname, tsalary, taddress, tphone, temail, tdesignation, taadhar};
        for (JTextField field : fields) {
            field.setBackground(bgColor);
            field.setForeground(fgColor);
        }
        JTextField dobField = (JTextField) tdob.getDateEditor().getUiComponent();
        dobField.setBackground(bgColor);
        dobField.setForeground(fgColor);
        tdob.getCalendarButton().setBackground(bgColor);
        tdob.getCalendarButton().setForeground(fgColor);
        Boxeducation.setBackground(bgColor);
        Boxeducation.setForeground(fgColor);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String name = tname.getText();
            String fname = tfname.getText();
            String dob = ((JTextField) tdob.getDateEditor().getUiComponent()).getText();
            String salary = tsalary.getText();
            String address = taddress.getText();
            String aadhar = taadhar.getText();
            String phone = tphone.getText();
            String email = temail.getText();
            String education = (String) Boxeducation.getSelectedItem();
            String designation = tdesignation.getText();
            String empID = tempid.getText();

            try {
                conn c = new conn();
                String query = "insert into employee values('" + name + "','" + fname + "','" + dob + "','" + salary + "','" + address + "','" + phone + "','" + email + "','" + education + "','" + designation + "','" + aadhar + "','" + empID + "')";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details Added Successfully");
                setVisible(false);
                new Main_class();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            new Main_class();
        }
    }

    public static void main(String[] args) {
        new AddEmployee();
    }

}