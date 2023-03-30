import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/*
 *  This class provide the window for user to view 
 *  a list of project saved in the system
 *  
 *  written by cheng jer seng
 */

public class ViewProjectListFrame extends JFrame{
    private JPanel panel =new JPanel(new FlowLayout());
    private JPanel panel2 = new JPanel(new FlowLayout());
    private JPanel panel3 = new JPanel(new FlowLayout());
    private JButton backButton = new JButton("Back?");
    private JButton viewButton = new JButton("View Project");
    private JButton deleteButton = new JButton("Delete Project");
    private ArrayList<MiniProject> mpArr = new ArrayList<MiniProject>();
    
    private DefaultTableModel model = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int mColIndex) {
          return false;
        }
    };
    private JTable table = new JTable(model);     

    ViewProjectListFrame(String id, String usertype){
        super("Viewing Projects");
        setSize(500,500);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        Reader reader = Reader.getInstance();
        ArrayList<String> filenames = reader.readProjectIDs();
        for (String name : filenames) {
            mpArr.add(reader.readMiniProject(name+".txt"));
        }
        
        model.addColumn("No");
        model.addColumn("ID");
        model.addColumn("Project Name");
        model.addColumn("Creator");
        model.addColumn("Specialization");
        model.addColumn("Status");
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 
        for (int i = 0; i <mpArr.size();i++){
            model.addRow(new Object[]{i+1, mpArr.get(i).getID(), mpArr.get(i).getprojectName(), mpArr.get(i).getCreator(),
                mpArr.get(i).getSpecialization(), mpArr.get(i).getStatus()
            });
        }

        JScrollPane sp = new JScrollPane(table);
        this.add(panel);
        this.add(panel2);
        this.add(panel3);
        panel.add(sp);
        panel2.add(backButton);
        panel2.add(viewButton);
        panel2.add(deleteButton);

        backButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		if(usertype == "Administrator") 
                    new AdminFrame(id);
                else if(usertype == "Lecturer")
                    new LecturerFrame(id);
        	}
        });
        viewButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
                if(table.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Please select a row first!");
                }
                else{
                    dispose();
                    new ViewProjectFrame(id, usertype, mpArr.get(table.getSelectedRow()).getID()+".txt");
                }
                
        	}
        });
        deleteButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
                if(table.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Please select a row first!");
                }
                else{
                    int a=JOptionPane.showConfirmDialog(null,"Are you sure?");
                    if(a == JOptionPane.YES_OPTION){
                        Writer writer = Writer.getInstance();
                        writer.deleteProject(mpArr.get(table.getSelectedRow()).getID()+".txt");
                        dispose();
                        new ViewProjectListFrame(id, usertype);
                    }
                }
        	}
        });
        

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        new ViewProjectListFrame("Mrs Malisa 1", "Lecturer");
    }
}
