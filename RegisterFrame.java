import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
    This class provide the window for admin 
	to register new user.
    
    Written by eugene teng zi han
*/

public class RegisterFrame extends JFrame{
	private JPanel panel=new JPanel();
	private JPanel panel2=new JPanel();
	private JPanel panel3=new JPanel();
	private JLabel label1 = new JLabel("ID(Number):");
	private JLabel label2 = new JLabel("Password  :");
	private JLabel errorLabel = new JLabel("ID & password cant be empty!");
	
	private JTextField textfield1= new JTextField(15);
	private JTextField textfield2= new JTextField(15);
	private JButton studButton = new JButton("Student");
	private JButton lecButton = new JButton("Lecturer");
	private JButton adminButton = new JButton("Adminstrator");
	private JButton backButton = new JButton("Back");
	private Writer writer = Writer.getInstance();
	private Reader reader = Reader.getInstance();

	RegisterFrame(String id){
		super("Register account");
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        add(panel);
		add(panel2);
		add(panel3);
		panel.setLayout(new GridBagLayout());
		panel2.setLayout(new FlowLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		setSize(500,300);
        
        errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);
		
        studButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent arg0) {
				if(checkInput()){
						String spec = JOptionPane.showInputDialog(null,"What is this student specialization?");
					if(spec != null){
						if(spec.isEmpty()){
							JOptionPane.showMessageDialog(null,"Specialization can't be empty!");
						}
						else{
							if(reader.readIDexist(textfield1.getText())){
								JOptionPane.showMessageDialog(null,"This ID is already taken!");
							}
							else{
								writer.writeAccount(textfield1.getText(), textfield2.getText(), "Student,"+ spec);
							}
						}
					}
				}
        	}
        });
        lecButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent arg0) {
				if(checkInput()){
					int n = JOptionPane.showConfirmDialog(null,"Are you sure to register account as a lecturer?", null, JOptionPane.YES_NO_OPTION);
					if (n==0) 
					{
						if(reader.readIDexist(textfield1.getText())){
							JOptionPane.showMessageDialog(null,"This ID is already taken!");
						}
						else{
							writer.writeAccount(textfield1.getText(), textfield2.getText(), "Lecturer");
						}
					}
					else 
					{
						dispose();
						new RegisterFrame(id);
					}
				}
        	}
        });
		adminButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent arg0) {
				if(checkInput()){
					int n = JOptionPane.showConfirmDialog(null,"Are you sure to register account as a adminstrator?", null, JOptionPane.YES_NO_OPTION);
					if (n==0) 
					{
						if(reader.readIDexist(textfield1.getText())){
							JOptionPane.showMessageDialog(null,"This ID is already taken!");
						}
						else{
							writer.writeAccount(textfield1.getText(), textfield2.getText(), "Administrator");
						}
					}
					else 
					{
						dispose();
						new RegisterFrame(id);
					}
				}
        		
        	}
        });
        backButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent arg0) {
        		dispose();
        		new AdminFrame(id);
        	}
        });
		gbc.gridx=0; gbc.gridy=0;
        panel.add(label1, gbc);
		gbc.gridx=1;
        panel.add(textfield1, gbc);
		gbc.gridx=0; gbc.gridy=1;
        panel.add(label2,gbc);
		gbc.gridx=1; gbc.gridy=1;
        panel.add(textfield2, gbc);
		gbc.gridx=1; gbc.gridy=2;
		panel.add(errorLabel, gbc);

        panel2.add(studButton);
        panel2.add(lecButton);
        panel2.add(adminButton);
		panel3.add(backButton);
		setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public boolean checkInput(){
		if(textfield1.getText().isEmpty() || textfield2.getText().isEmpty()){
			errorLabel.setVisible(true);
			return false;
		}
		else{
			errorLabel.setVisible(false);
			return true;
		}
	}
}