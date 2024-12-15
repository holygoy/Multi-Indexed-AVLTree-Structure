import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyDate {

    private LocalDate date;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");


    public MyDate(String dateString) throws IllegalArgumentException {

        this.date = LocalDate.parse(dateString, FORMATTER);

    }


    public MyDate() {
        this.date = LocalDate.now();
    }


    public int getYear() {
        return date.getYear();
    }


    public int getMonth() {
        return date.getMonthValue();
    }


    public int getDay() {
        return date.getDayOfMonth();
    }

    @Override
    public String toString() {
        return getDay() + "/" + getMonth() + "/" + getYear();
    }
}

