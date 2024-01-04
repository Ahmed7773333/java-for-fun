import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentPage extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;

    public AddStudentPage() {
        setTitle("Add Student");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);

        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField();

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());

                // Create a new Student object
                Student student = new Student();
                student.setId(id);
                student.setName(name);
                student.setAge(age);

                // Add the student to the database
                StudentDAO studentDAO = new StudentDAO();
                studentDAO.addStudent(student);

                // Display a success message
                JOptionPane.showMessageDialog(AddStudentPage.this, "Student added successfully!");

                // Clear the fields
                idField.setText("");
                nameField.setText("");
                ageField.setText("");
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(addButton);

        getContentPane().add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
