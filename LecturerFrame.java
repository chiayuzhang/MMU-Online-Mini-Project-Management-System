import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
    This class provides the main screen for the user lecturer
    written by cheng jer seng
 */
public class LecturerFrame extends JFrame{
    private JButton logoutButton = new JButton("Logout");
    private JButton addProjectButton = new JButton("Add New Project");
    private JButton viewProjectButton = new JButton("View Project");
    LecturerFrame(String id){

        super("Hi " + id +", Welcome.");
        setSize(500,300);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0; gbc.gridy = 0; 
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 1;
        add(viewProjectButton, gbc);
        gbc.gridy = 2;
        add(addProjectButton, gbc);
        gbc.gridy = 3;
        add(logoutButton, gbc);

        logoutButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		new MainFrame();
        	}
        });

        viewProjectButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
                Reader reader = Reader.getInstance();
                if(reader.readProjectExist()==false){ // if no project exist yet
                    JOptionPane.showMessageDialog(null,"Cant View, No Mini Project record found!");
                }
                else{
                    dispose();
                    new ViewProjectListFrame(id, "Lecturer");
                }
        	}
        });

        addProjectButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		new AddProjectFrame(id, "Lecturer");
        	}
        });


        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
