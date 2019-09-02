import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime byLDT;
    protected String by;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

    public Deadline(String description, String by) {
        super(description);
        String[] simpleDateTime = by.trim().split(" ",2);
        /*
        The program assumes the following formats for date and time:
        dd/MM/yyyy HHmm
        dd/MM/yyyy hh:mm a
        dd/MM/yyyy          (time assumed as 2359)
                   HHmm
                   hh:mm a  (date assumed as today)
         */
        try {
            this.byLDT = convertToLocalDateTime(simpleDateTime);
            this.by = this.byLDT.format(dateTimeFormatter);
        } catch (Exception e){
            this.by = by; // custom deadline
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    private LocalDateTime convertToLocalDateTime(String[] simpleDateTime){

        LocalDateTime now = LocalDateTime.now();
        String defaultDate = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String defaultTime = "2359";

        if (simpleDateTime.length == 1){
            String s = simpleDateTime[0];
            if (s.split(" ").length == 2) {
                // hh:mm a
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
                return LocalDateTime.parse(defaultDate + " " + s, formatter);
            } else if (s.length() == 4) {
                // HHmm
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                return LocalDateTime.parse(defaultDate + " " + s, formatter);
            } else {
                // dd/MM/yyyy
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                return LocalDateTime.parse(s + " " + defaultTime, formatter);
            }
        } else {
            String date = simpleDateTime[0];
            String time = simpleDateTime[1];
            DateTimeFormatter formatter;
            if (time.length() >= 7) {
                // dd/MM/yyyy hh:mm a
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
            } else {
                // dd/MM/yyyy HHmm
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            }
            return LocalDateTime.parse(date + " " + time, formatter);
        }
    }
}