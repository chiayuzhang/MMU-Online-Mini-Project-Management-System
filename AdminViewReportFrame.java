import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.GridBagLayout;

/*
    This class provides the window for the user admin
    to choose what type of report to be generated
    written by wong kee shern
*/

public class AdminViewReportFrame extends JFrame{
    private JButton viewProject = new JButton("View All Project");
    private JButton viewProjectSpe = new JButton("View Project According Specialization");
    private JButton viewProjectLec = new JButton("View Project According Lecturers");
    private JButton viewInactiveProject = new JButton("View Inactive Project");
    private JButton viewActiveProject = new JButton("View Active Project");
    private JButton viewProjectAssStu = new JButton("View Project Assigned to Student");
    private JButton viewProjectUnassStu = new JButton("View Project Unassigned to Student");
    private JButton viewProjectComment = new JButton("View Project Comment");
    private JButton backButton = new JButton("Back");

    private ArrayList<MiniProject> mpArr = new ArrayList<MiniProject>();

    private Reader reader = Reader.getInstance();
    AdminViewReportFrame(String id){
        super("View Reports Frame");
        setSize(500,300);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        

        ArrayList<String> filenames = reader.readProjectIDs();
        for (String name : filenames) {
            mpArr.add(reader.readMiniProject(name+".txt"));
        }

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0; gbc.gridy = 0; 
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 1;
        add(viewProject,gbc);
        gbc.gridy = 2;
        add(viewProjectSpe,gbc);
        gbc.gridy = 3;
        add(viewProjectLec,gbc);
        gbc.gridy = 4;
        add(viewInactiveProject,gbc);
        gbc.gridy = 5;
        add(viewActiveProject,gbc);
        gbc.gridy = 6;
        add(viewProjectAssStu,gbc);
        gbc.gridy = 7;
        add(viewProjectUnassStu,gbc);
        gbc.gridy = 8;
        add(viewProjectComment,gbc);
        gbc.gridy = 9;
        add(backButton,gbc);

        backButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		new AdminFrame(id);
        	}
        });

        viewProject.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		new ViewSelectedReport(id, "all");
        	}
        });

        viewProjectSpe.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                new ViewSelectedReport(id, "spec");
        	}
        });

        viewProjectLec.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                new ViewSelectedReport(id, "lec");
        	}
        });

        viewInactiveProject.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                new ViewSelectedReport(id, "inactive");
        	}
        });

        viewActiveProject.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                new ViewSelectedReport(id, "active");
        	}
        });

        viewProjectAssStu.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                new ViewSelectedReport(id, "assigned");
        	}
        });
        
        viewProjectUnassStu.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                new ViewSelectedReport(id, "unassigned");
        	}
        });

        viewProjectComment.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
                new ViewSelectedReport(id, "comment");
        	}
        });

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}