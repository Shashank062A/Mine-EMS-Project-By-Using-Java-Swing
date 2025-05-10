package employee.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class View_Employee extends JFrame implements ActionListener {

    JTable table;
    JComboBox<String> choiceEMP;
    JButton searchbtn, print, update, back;
    JToggleButton nightModeToggle;
    JScrollPane scrollPane;

    boolean isNightMode = false;

    Color lightFont = Color.BLACK;
    Color lightButtonBG = Color.BLACK;
    Color lightButtonFG = Color.WHITE;
    Color lightHeaderBG = new Color(43, 43, 43);
    Color lightHeaderFG = Color.WHITE;

    Color darkBG = new Color(19, 19, 43);
    Color darkFont = Color.WHITE;
    Color darkButtonBG = new Color(190, 190, 190);
    Color darkButtonFG = Color.BLACK;

    Color gradientTop = new Color(220, 20, 60);
    Color gradientBottom = new Color(255, 99, 71);

    JLabel search;
    GradientPanel gradientPanel;

    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            GradientPaint gp;

            if (isNightMode) {
                Color darkLeft = new Color(0, 0, 0);
                Color darkRight = new Color(49, 10, 79);
                gp = new GradientPaint(0, 0, darkLeft, getWidth(), 0, darkRight);
            } else {
                gp = new GradientPaint(0, 0, gradientTop, getWidth(), 0, gradientBottom);
            }

            g2.setPaint(gp);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    View_Employee() {

        setLayout(null);

        gradientPanel = new GradientPanel();
        gradientPanel.setLayout(null);
        gradientPanel.setBounds(0, 0, 900, 700);
        add(gradientPanel);

        search = new JLabel("Search by employee id");
        search.setBounds(20, 20, 150, 20);
        gradientPanel.add(search);

        choiceEMP = new JComboBox<>();
        choiceEMP.setEditable(true);
        choiceEMP.setBounds(180, 20, 150, 20);
        gradientPanel.add(choiceEMP);

        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("select * from employee");
            while (rs.next()) {
                choiceEMP.addItem(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();
        loadTableData();
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 100, 900, 600);
        gradientPanel.add(scrollPane);

        searchbtn = new JButton("Search");
        print = new JButton("Print");
        update = new JButton("Update");
        back = new JButton("Back");

        searchbtn.setBounds(20, 70, 80, 20);
        print.setBounds(120, 70, 80, 20);
        update.setBounds(220, 70, 80, 20);
        back.setBounds(320, 70, 80, 20);

        for (JButton b : new JButton[]{searchbtn, print, update, back}) {
            b.addActionListener(this);
            gradientPanel.add(b);
        }

        nightModeToggle = new JToggleButton("Night Mode");
        nightModeToggle.setBounds(779, 7, 100, 20);
        nightModeToggle.addItemListener(e ->
                toggleNightMode(nightModeToggle.isSelected()));
        gradientPanel.add(nightModeToggle);

        applyTheme();


        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {

                    String empId = table.getValueAt(row, table.getColumnCount() - 1).toString();
                    choiceEMP.setSelectedItem(empId);
                }
            }
        });

        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }

    private void loadTableData() {
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        table.getTableHeader().setBackground(lightHeaderBG);
        table.getTableHeader().setForeground(lightHeaderFG);
    }

    private void toggleNightMode(boolean enabled) {
        isNightMode = enabled;
        applyTheme();
        repaint();
    }

    private void applyTheme() {

        Color fg = isNightMode ? darkFont : lightFont;
        Color btnBG = isNightMode ? darkButtonBG : lightButtonBG;
        Color btnFG = isNightMode ? darkButtonFG : lightButtonFG;
        Color headerBG = isNightMode ? btnBG : lightHeaderBG;
        Color headerFG = isNightMode ? btnFG : lightHeaderFG;
        Color tblBG = isNightMode ? new Color(47, 47, 47) : Color.WHITE;

        table.setBackground(tblBG);
        table.setForeground(fg);
        table.setGridColor(fg);
        table.getTableHeader().setBackground(headerBG);
        table.getTableHeader().setForeground(headerFG);


        if (isNightMode) {
            choiceEMP.setBackground(Color.BLACK);
            choiceEMP.setForeground(Color.WHITE);
        } else {
            choiceEMP.setBackground(Color.WHITE);
            choiceEMP.setForeground(Color.BLACK);
        }

        updateComponentTheme(gradientPanel, fg, btnBG, btnFG,
                isNightMode ? darkBG : null);
    }

    private void updateComponentTheme(Component comp,
                                      Color fg, Color btnBG, Color btnFG, Color bg) {

        if (comp instanceof JButton || comp instanceof JToggleButton) {
            comp.setBackground(btnBG);
            comp.setForeground(btnFG);
        } else if (comp instanceof JLabel) {
            comp.setForeground(fg);
        } else if (comp instanceof JPanel) {
            comp.setBackground(bg);
            for (Component child : ((JPanel) comp).getComponents()) {
                updateComponentTheme(child, fg, btnBG, btnFG, bg);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object src = e.getSource();

        if (src == searchbtn) {
            String q = "select * from employee where empId='"
                    + choiceEMP.getSelectedItem() + "'";
            try {
                conn c = new conn();
                ResultSet rs = c.statement.executeQuery(q);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (src == print) {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (src == update) {
            setVisible(false);
            new UpdateEmployee((String) choiceEMP.getSelectedItem());

        } else if (src == back) {
            setVisible(false);
            new Main_class();
        }
    }

    public static void main(String[] args) {
        new View_Employee();
    }
}