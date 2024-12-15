public class Student  {// a class representing every attribute in the csv file
    private String firstName;
    private String lastName;
    private String uniLevel;
    private int id;
    private MyDate birthDate;


    public Student(String birthDate) {//To deal with undated DOBs in the csv, helper constructor
        if (birthDate != null && !birthDate.startsWith("#")) {
            this.birthDate = new MyDate(birthDate);
        } else {
            this.birthDate = null;
        }
    }

    public Student(int id, String firstName, String lastName, String uniLevel, String birthDate) { //Main constructor
        this(birthDate);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.uniLevel = uniLevel;
    }

    public Student(int id, String firstName, String lastName, String uniLevel, MyDate birthDate) { //Second constructor for processed DOB of type myDate
        this.firstName = firstName;
        this.lastName = lastName;
        this.uniLevel = uniLevel;
        this.id = id;
        this.birthDate = birthDate;
    }

    public int getId(){
        return id;
    } //getters and setters

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getUniLevel(){
        return uniLevel;
    }
    public MyDate getBirthDate(){
        return birthDate;
    }
    public int getYear(){
        return birthDate.getYear();
    }
    public int getMonth(){
        return birthDate.getMonth();
    }
    public int getDay(){
        return birthDate.getDay();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUniLevel(String uniLevel) {
        this.uniLevel = uniLevel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBirthDate(MyDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return id + " | " +
                firstName + "  " +
                lastName + " | " +
                birthDate + " | " +
                uniLevel + "\n";
    }
}