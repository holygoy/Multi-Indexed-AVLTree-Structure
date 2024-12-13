public class Student {
    private String firstName;
    private String lastName;
    private String uniLevel;
    private int id;
    private MyDate birthDate;


    public Student(String birthDate) {
        if (birthDate != null && !birthDate.startsWith("#")) {
            this.birthDate = new MyDate(birthDate);
        } else {
            this.birthDate = null;
        }
    }

    public Student(int id, String firstName, String lastName, String uniLevel, String birthDate) {
        this(birthDate);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.uniLevel = uniLevel;
    }


    public int getId(){
        return id;
    }

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

    @Override
    public String toString() {
        return id + " | " +
                firstName + "  " +
                lastName + " | " +
                birthDate + " | " +
                uniLevel + "\n";
    }

}