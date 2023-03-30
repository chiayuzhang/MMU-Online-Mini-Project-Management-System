import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import javax.swing.JOptionPane;

/*
    This class provide a singleton Writer object to perform tasks 
    related to writing data into the databases.
    
    Written by tan xun liang & eugene teng zi han
*/

public class Writer {
    
    public static Writer instance = new Writer();
    // Singleton
    private Writer(){}; // private constructor, to disable the creation of another Reader Object.
    
    public static Writer getInstance(){ 
        return instance;
    }

    // this function writes into account.txt
    // written by tan xun liang
    public void writeAccount(String id, String pass, String type){
        try{
            FileWriter fw = new FileWriter("account.txt",true);
            fw.write(id+","+pass+","+ type);
            fw.write(System.getProperty("line.separator"));
            fw.close();
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error Occurred");
        }
    }

    // this function writes into a specific mini project txt file.
    // written by eugene teng zi han
    public void writeMiniProject(MiniProject m){
        String cwd = Path.of("").toAbsolutePath().toString(); // get current path
        File folder = new File(cwd+"/MiniProject");                   
        folder.mkdir();       // add mini project folder to current path, if no yet created.
        File project = new File(cwd+"/MiniProject", m.getID()+".txt");
        
        try {
            project.createNewFile();
            FileWriter fw = new FileWriter(project);
            fw.write("ID: " + m.getID() + "\n");
            fw.write("Project: " + m.getprojectName() + "\n");
            fw.write("Creator: " + m.getCreator() + "\n");
            fw.write("Specialization: " + m.getSpecialization() + "\n");
            fw.write("Assigned To: " + m.getStudent() + "\n");
            fw.write("Status: " + m.getStatus() + "\n\n");
            fw.write("Project Description: " + m.getProjectDescription() + "\n\n");
            fw.write("Comment: " + m.getComment());
            fw.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // this function deletes an miniproject of the given filename
    // written by tan xun liang
    public void deleteProject(String filename){
        String cwd = Path.of("").toAbsolutePath().toString(); // get current path
        File project = new File(cwd+"/MiniProject", filename);
        project.delete();
    }
}
