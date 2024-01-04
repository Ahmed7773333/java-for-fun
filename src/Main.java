import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel(new BorderLayout());

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Age");

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            StudentDAO studentDAO = new StudentDAO();
            List<Student> students = studentDAO.getAllStudents();

            // Clear existing data
            tableModel.setRowCount(0);

            for (Student student : students) {
                Object[] rowData = {student.getId(), student.getName(), student.getAge()};
                tableModel.addRow(rowData);
            }
        });

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> {
            new AddStudentPage();
        });

        JButton deleteButton = new JButton("Delete Selected");
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int studentId = (int) tableModel.getValueAt(selectedRow, 0);
                StudentDAO studentDAO = new StudentDAO();
                studentDAO.deleteStudentById(studentId);
                refreshButton.doClick(); // Refresh the table after deletion
            } else {
                JOptionPane.showMessageDialog(frame, "Select a row to delete");
            }
        });

        JButton getByIdButton = new JButton("Get Student by ID");
        getByIdButton.addActionListener(e -> {
            String inputId = JOptionPane.showInputDialog(frame, "Enter Student ID:");
            if (inputId != null && !inputId.isEmpty()) {
                int studentId = Integer.parseInt(inputId);
                StudentDAO studentDAO = new StudentDAO();
                Student student = studentDAO.getStudentById(studentId);

                if (student != null) {
                    // Clear existing data
                    tableModel.setRowCount(0);
                    Object[] rowData = {student.getId(), student.getName(), student.getAge()};
                    tableModel.addRow(rowData);
                } else {
                    JOptionPane.showMessageDialog(frame, "Student not found");
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(getByIdButton);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
