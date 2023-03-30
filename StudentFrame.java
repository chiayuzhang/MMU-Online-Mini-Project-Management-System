import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/*
    This class provides the main screen for the user student
    written by chia yu zhang
 */

public class StudentFrame extends JFrame{
    private JButton logoutButton = new JButton("Logout");
    private JButton viewProjectButton = new JButton("View Same Spec Project");
    private JButton viewMyProjectButton = new JButton("View My Project");
    private ArrayList<MiniProject> mpArr = new ArrayList<MiniProject>();
    private Student currentStudent;

    StudentFrame(String id){
        
        super("Student : " + id);
        setSize(500,300);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0; gbc.gridy = 0; 
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 1;
        add(viewProjectButton, gbc);
        gbc.gridy = 2;
        add(viewMyProjectButton, gbc);
        gbc.gridy = 3;
        add(logoutButton, gbc);

        Reader reader = Reader.getInstance();
        ArrayList<Student> studList =  reader.readStudents();
        for (Student student : studList) {
            if(student.getUserID().equals(id)){
                currentStudent =  student;
                break;
            }
        }

        if(reader.readProjectExist() == true){
            // load in all projects so that later
            // we can search for project assigned to this student's project
            ArrayList<String> filenames = reader.readProjectIDs();
            for (String name : filenames) {
                mpArr.add(reader.readMiniProject(name+".txt"));
            }
        }
        

        logoutButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		new MainFrame();
        	}
        });

        viewProjectButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
                if(reader.readProjectExist() == false){
                    JOptionPane.showMessageDialog(null,"Cant View, No Mini Project record found!");
                }
                else{
                    dispose();
        		    new StudentViewProjectFrame(currentStudent);
                }
        		
        	}
        });

        viewMyProjectButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                boolean haveProject = false;
        		for (MiniProject project : mpArr) {
                    if(project.getStudent().equals(id)){
                        new ViewProjectFrame(id, "Student-my", project.getID()+".txt");
                        haveProject = true;
                    }
                }
                if(haveProject == false){
                    JOptionPane.showMessageDialog(null,"No project assigned to you!");
                    new StudentFrame(id);
                }
        	}
        });

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
