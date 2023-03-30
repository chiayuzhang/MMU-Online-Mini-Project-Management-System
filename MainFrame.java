import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
    This class provides the main screen 
    for all user to login
    written by tan xun liang
 */

public class MainFrame extends JFrame{
    
    MainFrame(){
        super("MMU System");

        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        JPanel panel =new JPanel(new FlowLayout());
        JPanel panel2 =new JPanel(new FlowLayout());
        JPanel panel3 =new JPanel(new FlowLayout());
        JPanel panel4 =new JPanel(new FlowLayout());
        add(panel);
        add(panel2);
        add(panel3);
        add(panel4);
        setSize(500,300);
        
        
        JLabel label = new JLabel("Mini Project Management System");
        label.setFont(new Font("Verdana", Font.PLAIN, 25));
        label.setBounds(170,10,200,20);
        JLabel label1 = new JLabel("ID(Number):");
        JLabel label2 = new JLabel("Password  :");
        JTextField textfield1= new JTextField(15);
        JTextField textfield2= new JTextField(15);
        JButton loginBtn = new JButton("Log in");

        loginBtn.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent arg0) {
                String id = textfield1.getText();
                String password = textfield2.getText();
                Reader reader = Reader.getInstance();
                String type = reader.findAccount(id, password);
                
                if(type.equals("Administrator")){
                    JOptionPane.showMessageDialog(null,"SUCCESS!");
                    dispose();
                    new AdminFrame(id);
                }
                else if(type.equals("Student")){
                    dispose();
                    new StudentFrame(id);
                }
                else if(type.equals("Lecturer")){
                    dispose();
                    new LecturerFrame(id);
                }
                else
                    JOptionPane.showMessageDialog(null,"ID and password does not match!");  
            }
        });
        panel.add(label);
        panel2.add(label1);
        panel2.add(textfield1);
        panel3.add(label2);
        panel3.add(textfield2);
        panel4.add(loginBtn);
        
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
  
    public static void main(String[] args) {
        new MainFrame();
    }
}
