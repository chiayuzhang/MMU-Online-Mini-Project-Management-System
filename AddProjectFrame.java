import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
    This class provide the window for user to add a new miniproject with its details
    
    Written by wongkeeshern
*/

public class AddProjectFrame extends JFrame{
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
    private JComboBox<String> cBox = new JComboBox<>();    

    private JLabel projectDescriptionLabel = new JLabel("Project Description:");
    private JTextArea projecDescArea = new JTextArea(10,30);
    private JScrollPane sp1 = new JScrollPane(projecDescArea);
    private JButton cancelButton = new JButton("Cancel");
    private JButton saveButton = new JButton("Save");

    private MiniProject m = new MiniProject();
    AddProjectFrame(String id, String usertype){
        super("Create New Project");
        setSize(900,450);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        add(panel);
        panel.setLayout(new GridLayout(6,2));
        panel.add(idLabel); panel.add(idField); 
        idField.setText(m.getID());
        idField.setEditable(false);
        panel.add(projectNameLabel); panel.add(projectNameField);
        panel.add(creatorLabel); panel.add(creatorField);
        creatorField.setText(id);
        creatorField.setEditable(false);
        panel.add(specializationLabel); panel.add(specializationField);
        panel.add(statusLabel); 
        cBox.addItem("Active"); cBox.addItem("Not Active");
        panel.add(cBox);

        add(panel2);
        panel2.add(projectDescriptionLabel); panel2.add(sp1);

        add(panel3);
        panel3.add(cancelButton);
        panel3.add(saveButton);
        
        saveButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
                if(checkInput()){
                    m.setProjectName(projectNameField.getText());
                    m.setCreator(creatorField.getText());
                    m.setSpecialization(specializationField.getText());
                    m.setStudent("None");
                    m.setStatus(String.valueOf(cBox.getSelectedItem()));
                    m.setDescription(projecDescArea.getText());
                    Writer writer = Writer.getInstance();
                    writer.writeMiniProject(m);
                    JOptionPane.showMessageDialog(null,"SUCCESS!");
                    dispose();
                    if(usertype == "Administrator") 
                        new AdminFrame(id);
                    else if(usertype == "Lecturer")
                        new LecturerFrame(id);
                }
        	}
        });

        cancelButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		if(usertype == "Administrator") 
                    new AdminFrame(id);
                else if(usertype == "Lecturer")
                    new LecturerFrame(id);
        	}
        });

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // This function checks whether the input fields are empty before saving it into the database
    public boolean checkInput(){
        if(projectNameField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Project Name can't be empty!");
            return false;
        }
        if(specializationField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Specialization can't be empty!");
            return false;
        }
        return true;
    }
}