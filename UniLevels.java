import java.util.ArrayList;
public class UniLevels {
    ArrayList<Student>[] levels = new ArrayList[]{new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()};
    String[] labels = {"FR","SO","SR","JR"};//Todo: mapping

    UniLevels(ArrayList<Student> students){
        for(Student student: students){
            System.out.println(student.getUniLevel());
            getList(student).add(student);
        }
    }

    public UniLevels() {

    }

    public ArrayList<Student> getList(String level){
        switch (level){
            case "FR" : return levels[0];
            case "SO" : return levels[1];
            case "JR" : return levels[2];
            case "SR" : return levels[3];
            default: return null;
        }
    }
    public ArrayList<Student> getList(Student student){
        return getList(student.getUniLevel());
    }
    public void delete(Student student){
        getList(student).remove(student);
    }
    public void insert(Student student){
        getList(student).add(student);
    }
}
