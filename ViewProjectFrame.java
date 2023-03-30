import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

// This class provides the window for user to view project details
// some buttons are hidden / showed, depends on which type of user
// is accessing the current frame.
//
// written by cheng jer seng

public class ViewProjectFrame extends JFrame{
    private JPanel panel = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JLabel idLabel = new JLabel("ID:");
    private JTextField idField = new JTextField(20);
    private JLabel projectNameLabel = new JLabel("Project Name:");
    private JTextField projectNameField = new JTextField(20);
    private JLabel creatorLabel = new JLabel("Creator:");
    private JTextField creatorField = new JTextField(20);
    private JLabel specializationLabel = new JLabel("Specialization:");
    private JTextField specializationField = new JTextField(20);
    private JLabel statusLabel = new JLabel("Status");
    private JTextField statusField = new JTextField(20);
    private JLabel assignToLabel = new JLabel("Assigned To: ");
    private JTextField assignToField = new JTextField(20);

    private JLabel projectDescriptionLabel = new JLabel("Project Description:");
    private JTextArea projecDescArea = new JTextArea(10,30);
    private JScrollPane sp1 = new JScrollPane(projecDescArea);
    private JLabel commentLabel = new JLabel("Comments:");
    private JTextArea commentArea = new JTextArea(10,30);
    private JScrollPane sp2 = new JScrollPane(commentArea);
    private JButton backButton = new JButton("Back?");

    // for admin
    private JButton deleteCommentButton = new JButton("Delete Comment");

    // for lecturer
    private JButton editButton = new JButton("Edit Project");
    private JButton saveChanges = new JButton("Save Changes");
    private JButton addCommentButton = new JButton("Add Comment");
    private JButton assignButton = new JButton("Assign Project");
    private JButton activateButton = new JButton("Activate Project");
    private JButton deactivateButton = new JButton("Deactivate Project");
    
    private MiniProject m = new MiniProject();

    Reader reader = Reader.getInstance();
    Writer writer = Writer.getInstance();

    ViewProjectFrame(String id, String usertype, String filename){
        super("Viewing Project");
        setSize(900,450);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        
        m = reader.readMiniProject(filename);

        add(panel);
        panel.setLayout(new GridLayout(6,2));
        panel.add(idLabel); panel.add(idField); 
        idField.setText(m.getID());
        idField.setEditable(false);
        panel.add(projectNameLabel); panel.add(projectNameField);
        projectNameField.setText(m.getprojectName());
        projectNameField.setEditable(false);
        panel.add(creatorLabel); panel.add(creatorField);
        creatorField.setText(m.getCreator());
        creatorField.setEditable(false);
        panel.add(specializationLabel); panel.add(specializationField);
        specializationField.setText(m.getSpecialization());
        specializationField.setEditable(false);
        panel.add(statusLabel); panel.add(statusField);
        statusField.setText(m.getStatus());
        statusField.setEditable(false);
        panel.add(assignToLabel); panel.add(assignToField);
        assignToField.setText(m.getStudent());
        assignToField.setEditable(false);

        add(panel2);
        panel2.add(projectDescriptionLabel); panel2.add(sp1);
        projecDescArea.setText(m.getProjectDescription());
        projecDescArea.setEditable(false);
        if(!usertype.equals("Student")){    // students cant see comment
            panel2.add(commentLabel); panel2.add(sp2);
                commentArea.setText(m.getComment());
                commentArea.setEditable(false);
        }
        

        add(panel3);
        panel3.add(backButton);
        if(usertype.equals("Administrator")){
            panel3.add(addCommentButton);
            panel3.add(deleteCommentButton);
        }
        if(usertype.equals("Lecturer")){
            panel3.add(editButton);
            panel3.add(saveChanges);
            saveChanges.setVisible(false);
            panel3.add(assignButton);
            panel3.add(activateButton);
            panel3.add(deactivateButton);
        }

        backButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                if(usertype.equals("Lecturer"))
                    new ViewProjectListFrame(id, "Lecturer");
                if(usertype.equals("Administrator"))
                    new ViewProjectListFrame(id, "Administrator");
                if(usertype.equals("Student")){
                    int num = 0;
                    ArrayList<Student> studList =  reader.readStudents();
                    for (int i = 0; i<studList.size(); i++) {
                        if(studList.get(i).getUserID().equals(id)){
                            num =  i;   // return Student object
                            break;
                        }
                    }
                    new StudentViewProjectFrame(studList.get(num));
                }
                if(usertype.equals("Student-my")){ // if student acces this page from viewMyProject
                    new StudentFrame(id);
                }
                    
        	}
        });
        addCommentButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		String cmt=JOptionPane.showInputDialog(null,"Enter your comment");   
                if(cmt != null){
                    m.setComment(cmt);
                    writer.writeMiniProject(m);
                    dispose();
                    new ViewProjectFrame(id, usertype, filename);
                } 
        	}
        });

        deleteCommentButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
                m.setComment(null);
                writer.writeMiniProject(m);
                dispose();
                new ViewProjectFrame(id, usertype, filename);
        	}
        });

        editButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		projectNameField.setEditable(true);
                specializationField.setEditable(true);
                projecDescArea.setEditable(true);
                saveChanges.setVisible(true);
                editButton.setVisible(false);
                assignButton.setVisible(false);
        	}
        });
        saveChanges.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
                m.setProjectName(projectNameField.getText());
                m.setSpecialization(specializationField.getText());
                m.setDescription(projecDescArea.getText());
                writer.writeMiniProject(m);
                dispose();
                new ViewProjectFrame(id, usertype, filename);
        	}
        });

        assignButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
                // change to student with same specialization
                ArrayList<String> arrList = reader.readSameSpecStud(m.getSpecialization());
                if(arrList.isEmpty()){ // no student with same specialization
                    JOptionPane.showMessageDialog(null,"There are no student in this specialization");
                }
                else{
                    String[] studs = new String[arrList.size()];
                    for(int i = 0; i < studs.length; i++) {
                        studs[i] = arrList.get(i);  // convert into normal Array
                    }
                    JComboBox<String> comboBox = new JComboBox<>(studs);
                    int a = JOptionPane.showConfirmDialog(null, comboBox, "Assign To Who?",
                    JOptionPane.CANCEL_OPTION);
                    if(a==JOptionPane.YES_OPTION){
                        m.setStudent(comboBox.getSelectedItem().toString());
                        writer.writeMiniProject(m);
                        dispose();
                        new ViewProjectFrame(id, usertype, filename);
                    }
                    
                }
        	}
        });

        activateButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Project " + m.getID() + " is activated!");
                    m.setStatus("Active");
                    writer.writeMiniProject(m);
                    dispose();
                    new ViewProjectFrame(id, usertype, filename);
                }
        });

        deactivateButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Project " + m.getID() + " is deactivated!");
                m.setStatus("Not Active");
                writer.writeMiniProject(m);
                dispose();
                new ViewProjectFrame(id, usertype, filename);
            }
    });

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}