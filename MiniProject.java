import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

/*
    This class defines the mini project object
    written by wong kee shern
 */

public class MiniProject {
    private String ID;
    private String projectName;
    private String creator;
    private String specialization;
    private String student;
    private String status;
    private String projectDescription;
    private String comment;
    
    MiniProject(){
        assignID();
    }
    MiniProject(String name, String creator, String spec, String stud, String stat, String desc, String cmt){
        setProjectName(name);
        setCreator(creator);
        setSpecialization(spec);
        setStudent(stud);
        setStatus(stat);
        setDescription(desc);
        setComment(cmt);
    }
    // getters
    public String getprojectName(){
        return projectName;
    }
    public String getCreator() {
        return creator;
    }
    public String getID(){
        return ID;
    }
    public String getSpecialization(){
        return specialization;
    }
    public String getStudent(){
        return student;
    }
    public String getStatus(){
        return status;
    }
    public String getProjectDescription(){
        return projectDescription;
    }
    public String getComment(){
        return comment;
    }

    // setters
    public void setProjectName(String name){
        this.projectName = name;
    }
    public void setCreator(String creator){
        this.creator = creator;
    }
    public void setID(String id){
        this.ID = id;
    }
    public void setSpecialization(String sp){
        this.specialization = sp;
    }
    public void setStudent(String stud){
        this.student = stud;
    }
    public void setStatus(String a){
        this.status = a;
    }
    public void setDescription(String d){
        this.projectDescription = d;
    }
    public void setComment(String cmt){
        this.comment = cmt;
    }

    // this function assign ID to projects, and then updates the name of the project
    public void assignID(){
        Reader reader = Reader.getInstance();
        
        if(reader.readProjectExist()==false){
            setID("P0");    // the first project
        }
        else{
            ArrayList<String> names = reader.readProjectIDs();
            String cwd = Path.of("").toAbsolutePath().toString(); // get current path
            File folder = new File(cwd+"/MiniProject");  
            File[] listOfFiles = folder.listFiles();
            int counter =0; // count how many project already in here.
            for (int i = 0; i < listOfFiles.length; i++) {
                counter += 1;
            }
            String givenName = "P"+(counter+1);
            while(true){
                if(names.contains(givenName)){
                    counter+=1;
                    givenName = "P"+counter;
                }
                else
                    break;
            }
            setID(givenName);
        }
    }
}
