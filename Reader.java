import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

/*
    This class provide a singleton Reader object to perform tasks 
    related to reading data from the databases.
    
    Written by wongkeeshern & tan xun liang
*/

public class Reader {
    // Singleton
    private Reader(){}; // private constructor, to disable the creation of another Reader Object.
    public static Reader instance = new Reader();
    public static Reader getInstance(){ 
        return instance;
    }

    // This function reads account.txt and returns user type, if not found returns empty string
    // written by wong kee shern
    public String findAccount(String id, String password){

        try{
            BufferedReader buffReader = new BufferedReader(new FileReader("account.txt"));
            String line;
            while ( (line = buffReader.readLine()) != null) {   // read file line by line
                String[] account = line.split(",");     // split line separated by comma
                if(account[0].equals(id) && account[1].equals(password)){
                    return account[2];
                }
            }
            buffReader.close();
        }
        catch (IOException e) {
            System.out.println("File Read Error");
        }
        return "";
    }
    
    // this function reads a mini project of the given filename and returns it as an object.
    // written by wong kee shern
    public MiniProject readMiniProject(String filename){
        String cwd = Path.of("").toAbsolutePath().toString(); // get current path
        File file = new File(cwd+"/MiniProject", filename);

        String projectName="";
        String creator="";
        String ID = "";
        String specialization = "";
        String student = "";
        String status  = "";
        String descpLine ="";
        String comment= "";
        try {
            BufferedReader buffReader = new BufferedReader(new FileReader(file));
            String line;
            while((line = buffReader.readLine()) != null){
                String[] string = line.split(": ");
                switch(string[0]){
                    case "ID" : ID = string[1]; break;
                    case "Project": projectName = string[1]; break;
                    case "Creator": creator = string[1]; break;
                    case "Specialization": specialization = string[1]; break;
                    case "Assigned To": student = string[1]; break;
                    case "Status": status = string[1]; break;
                    case "Project Description": {
                        if(string.length != 1)
                            descpLine = string[1];
                        break;
                    }
                    case "Comment":{
                        if(string.length != 1)
                            comment = string[1];
                        break;
                    }
                }
            }
            buffReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MiniProject m = new MiniProject(projectName,  creator,  specialization, student, status, descpLine, comment);
        m.setID(ID);
        return m;
    }
    
    // this function reads the project IDs that is created.
    // written by wong kee shern
    public ArrayList<String> readProjectIDs(){
        String cwd = Path.of("").toAbsolutePath().toString(); // get current path

        File[] files = new File(cwd+"/MiniProject").listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null. 
        ArrayList<String> fileNames = new ArrayList<String>();
        for (File file : files) {
            if (file.isFile()) {
                String[] tokens = file.getName().split("\\.(?=[^\\.]+$)");
                fileNames.add(tokens[0]);
            }
        }
        return fileNames;
    }

    // this function read and return a list of student of a given specialization.
    // written by tan xun liang
    public ArrayList<String> readSameSpecStud(String spec){
        ArrayList<String> studs = new ArrayList<String>();
        try{
            BufferedReader buffReader = new BufferedReader(new FileReader("account.txt"));
            String line;
            while ( (line = buffReader.readLine()) != null) {   // read file line by line
                String[] account = line.split(",");     // split line separated by comma
                if(account[2].equals("Student")){
                    if(account[3].equals(spec)){
                        studs.add(account[0]);
                    }
                }
            }
            buffReader.close();
        }
        catch (IOException e) {
            System.out.println("File Read Error");
        }
        return studs;
    }

    // this function read students account and then return it as a list.
    // written by tan xun liang
    public ArrayList<Student> readStudents(){
        ArrayList<Student> studentsList = new ArrayList<Student>();
        try{
            BufferedReader buffReader = new BufferedReader(new FileReader("account.txt"));
            String line;
            while ( (line = buffReader.readLine()) != null) {   // read file line by line
                String[] account = line.split(",");     // split line separated by comma
                if(account[2].equals("Student")){
                    studentsList.add(new Student(account[3], "None", account[0], account[1]));
                }
            }
            buffReader.close();
        }
        catch (IOException e) {
            System.out.println("File Read Error");
        }
        return studentsList;
    }     

//  This function read all of the created id and will return true,
//  if given id is already existed in the database
// written tan xun liang
    public boolean readIDexist(String id){
        try{
            BufferedReader buffReader = new BufferedReader(new FileReader("account.txt"));
            String line;
            while ( (line = buffReader.readLine()) != null) {   // read file line by line
                String[] account = line.split(",");     // split line separated by comma
                if(account[0].equals(id)){
                    return true;
                }
            }
            buffReader.close();
        }
        catch (IOException e) {
            System.out.println("File Read Error");
        }
        return false;
    }  

    public boolean readProjectExist(){
        String cwd = Path.of("").toAbsolutePath().toString(); // get current path
        File[] files = new File(cwd+"/MiniProject").listFiles();
        if(files == null) {
            return false;
        }
        else{
            return true;
        } 
    }
}
