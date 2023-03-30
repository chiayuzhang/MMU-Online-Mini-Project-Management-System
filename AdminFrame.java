import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
    This class provides the main screen for the user admin
    written by eugene teng zi han
*/
public class AdminFrame extends JFrame{
    private JButton createAccButton = new JButton("Create Account");
    private JButton logoutButton = new JButton("Logout");
    private JButton addProjectButton = new JButton("Add New Project");
    private JButton viewProjectButton = new JButton("View Project");
    private JButton viewReportButton = new JButton("View Report");
    
    AdminFrame(String id){

        super("Admin : " + id);
        setSize(500,300);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0; gbc.gridy = 0; 
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(createAccButton, gbc);
        gbc.gridy = 1;
        add(viewProjectButton, gbc);
        gbc.gridy = 2;
        add(addProjectButton, gbc);
        gbc.gridy = 3;
        add(viewReportButton, gbc);
        gbc.gridy = 4;
        add(logoutButton, gbc);

        createAccButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		new RegisterFrame(id);
        	}
        });

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
                    new ViewProjectListFrame(id, "Administrator");
                }
        		
        	}
        });

        addProjectButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		new AddProjectFrame(id, "Administrator");
        	}
        });

        viewReportButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
                Reader reader = Reader.getInstance();
                if(reader.readProjectExist()==false){ // if no project exist yet
                    JOptionPane.showMessageDialog(null,"Cant View, No Mini Project record found!");
                }
                else{
                    dispose();
                    new AdminViewReportFrame(id);
                }
        	}
        });

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
