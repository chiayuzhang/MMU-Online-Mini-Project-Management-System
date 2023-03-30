/*
 * Created by CHIA YU ZHANG
 * extends from the User class
 * make the constructor
 */
public class Student extends User{
    private String specialization;
    private String projectName;

    public Student(String specialization, String projectName, String userID, String password){
        super(userID,password);
        updateSpe(specialization);
        updateProject(projectName);
    }


    // update/set the information of the student by using the update function
    public void updateProject(String projectName){
        this.projectName = projectName;
    }

    public void updateSpe(String specialization){
        this.specialization = specialization;
    }

    // get the information of the student by using the get function
    public String getSpecialization(){
        return specialization;
    }

    public String getProject(){
        return projectName;
    }
}
