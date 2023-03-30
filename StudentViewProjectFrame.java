import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*
    This class provides the screen for the user student
    to view all active projects with same specialization
    
    written by chia yu zhang
 */

public class StudentViewProjectFrame extends JFrame{
    private JPanel panel =new JPanel(new FlowLayout());
    private JPanel panel2 = new JPanel(new FlowLayout());
    private JPanel panel3 = new JPanel(new FlowLayout());
    private JButton backButton = new JButton("Back?");
    private JButton viewButton = new JButton("View Project");

    private ArrayList<MiniProject> mpArr = new ArrayList<MiniProject>();
    
    private DefaultTableModel model = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int mColIndex) {
          return false;
        }
    };
    private JTable table = new JTable(model);     

    private Reader reader = Reader.getInstance();

    StudentViewProjectFrame(Student stud){
        super("Viewing Projects");
        setSize(500,500);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        ArrayList<String> filenames = reader.readProjectIDs();
        Reader reader = Reader.getInstance();
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
            if(mpArr.get(i).getSpecialization().equals(stud.getSpecialization()) && mpArr.get(i).getStatus().equals("Active")){
                model.addRow(new Object[]{i+1, mpArr.get(i).getID(), mpArr.get(i).getprojectName(), mpArr.get(i).getCreator(),
                    mpArr.get(i).getSpecialization(), mpArr.get(i).getStatus()
            });}
        }

        JScrollPane sp = new JScrollPane(table);
        this.add(panel);
        this.add(panel2);
        this.add(panel3);
        panel.add(sp);
        panel2.add(backButton);
        panel2.add(viewButton);

        backButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
                dispose();
                new StudentFrame(stud.getUserID());
        	}
        });
        viewButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
                if(table.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Please select a row first!");
                }
                else{
                    dispose();
                    int row = table.getSelectedRow();
                    new ViewProjectFrame(stud.getUserID(), "Student", table.getValueAt(row, 1)+".txt");
                }
        	}
        });

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
