import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*
    This class provides the window for the user admin
    to view generated report after they selected
    what kind of report to be generated

    written by chia yu zhang
*/

public class ViewSelectedReport extends JFrame{
    private JPanel panel = new JPanel(new FlowLayout()); 
    private JPanel panel2 = new JPanel(new FlowLayout()); 
    private JButton backButton = new JButton("Back");
    private ArrayList<MiniProject> mpArr = new ArrayList<MiniProject>();
    private DefaultTableModel model = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int mColIndex) {
          return false;
        }
    };
    private JTable table = new JTable(model); 

    ViewSelectedReport(String id, String reportType){
        setSize(500,300);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        Reader reader = Reader.getInstance();
        ArrayList<String> filenames = reader.readProjectIDs();
        for (String name : filenames) {
            mpArr.add(reader.readMiniProject(name+".txt"));
        }


        switch(reportType){
            case "all": allProject();break;
            case "spec": accordingSpec();break;
            case "lec": accordingLec();break;
            case "inactive": inactive();break;
            case "active": active();break;
            case "assigned": assigned();break;
            case "unassigned": unassigned();break;
            case "comment": comment();break;
        }

        JScrollPane sp = new JScrollPane(table);
        this.add(panel);
        this.add(panel2);
        panel.add(sp);
        panel2.add(backButton);


        backButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		new AdminViewReportFrame(id);
        	}
        });

 
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void allProject(){
        setTitle("View All Project");
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
    }
    public void accordingSpec(){
        setTitle("View Project According to Specializations");
        model.addColumn("ID");
        model.addColumn("Project Name");
        model.addColumn("Specialization");
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.setAutoCreateRowSorter(true);

        for (int i = 0; i <mpArr.size();i++){
            model.addRow(new Object[]{mpArr.get(i).getID(), mpArr.get(i).getprojectName(),
                mpArr.get(i).getSpecialization()
            });
        }
    }
    public void accordingLec(){
        setTitle("View Project According to Lecturer");
        model.addColumn("No");
        model.addColumn("ID");
        model.addColumn("Project Name");
        model.addColumn("Lecturer");
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);
        for (int i = 0; i <mpArr.size();i++){
            model.addRow(new Object[]{i+1, mpArr.get(i).getID(), mpArr.get(i).getprojectName(),
                mpArr.get(i).getCreator()
            });
        }
    }
    public void inactive(){
        setTitle("View Inactive Project");
        model.addColumn("No");
        model.addColumn("ID");
        model.addColumn("Project Name");
        model.addColumn("Status");
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for (int i = 0; i <mpArr.size();i++){
            if(mpArr.get(i).getStatus().equals("Not Active"))
                model.addRow(new Object[]{i+1, mpArr.get(i).getID(), mpArr.get(i).getprojectName(),
                    mpArr.get(i).getStatus()
                });
        }
    }
    public void active(){
        setTitle("View Active Project");
        model.addColumn("No");
        model.addColumn("ID");
        model.addColumn("Project Name");
        model.addColumn("Status");
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for (int i = 0; i <mpArr.size();i++){
            if(mpArr.get(i).getStatus().equals("Active"))
                model.addRow(new Object[]{i+1, mpArr.get(i).getID(), mpArr.get(i).getprojectName(),
                    mpArr.get(i).getStatus()
                });
        }
    }
    public void assigned(){
        setTitle("View Assigned Project");
        model.addColumn("No");
        model.addColumn("ID");
        model.addColumn("Project Name");
        model.addColumn("Assigned To");
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for (int i = 0; i <mpArr.size();i++){
            if(!mpArr.get(i).getStudent().equals("None"))
                model.addRow(new Object[]{i+1, mpArr.get(i).getID(), mpArr.get(i).getprojectName(), mpArr.get(i).getStudent()} );
        }
    }
    public void unassigned(){
        setTitle("View Unassigned Project");
        model.addColumn("No");
        model.addColumn("ID");
        model.addColumn("Project Name");
        model.addColumn("Assigned To");
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for (int i = 0; i <mpArr.size();i++){
            if(mpArr.get(i).getStudent().equals("None"))
                model.addRow(new Object[]{i+1, mpArr.get(i).getID(), mpArr.get(i).getprojectName(), mpArr.get(i).getStudent() } );
        }
    }
    public void comment(){
        setTitle("View Commented Project");
        model.addColumn("No");
        model.addColumn("ID");
        model.addColumn("Project Name");
        model.addColumn("With comment");
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        for (int i = 0; i <mpArr.size();i++){
            if(!mpArr.get(i).getComment().equals("null"))
                model.addRow(new Object[]{i+1, mpArr.get(i).getID(), mpArr.get(i).getprojectName(), mpArr.get(i).getComment()} );
        }
    }

}