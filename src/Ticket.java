import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {
    private String id; //max 4 digits and/or char
    private String concertHall; //max 10 chars
    private String eventCode; // 3 digits
    private long time; // Unix timestamp in milliseconds, a specific event time
    private boolean isPromo;
    private char stadiumSector; // from A to C
    private double maxBackpackWeightInKg; // with prams precision

    //* ability to automatically detect and save creation time
    private long creationTime = System.currentTimeMillis();

    //** ticket price
    BigDecimal price;


    public Ticket() {
        setCreationTime(this.creationTime);
    }

    public Ticket(String id,
                  String concertHall,
                  String eventCode,
                  long time,
                  boolean isPromo,
                  char stadiumSector,
                  double maxBackpackWeightInKg) {
        setId(id);
        setConcertHall(concertHall);
        setEventCode(eventCode);
        this.time = time;
        this.isPromo = isPromo;
        setStadiumSector(stadiumSector);
        this.maxBackpackWeightInKg = maxBackpackWeightInKg;
        setCreationTime(this.creationTime);
    }

    public Ticket(String concertHall,
                  String eventCode,
                  long time) {
        setConcertHall(concertHall);
        setEventCode(eventCode);
        this.time = time;
        setCreationTime(this.creationTime);
    }

    public Ticket(String id) {
        setId(id);
    }

    public void setId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Identifier cannot be null");
        } else if (id.length() > 4) {
            throw new IllegalArgumentException("Identifier must not exceed 4 digits and/or characters.");
        }
        this.id = id;
    }

    public void setConcertHall(String concertHall) {
        if (concertHall == null) {
            throw new IllegalArgumentException("Concert hall cannot be null");
        } else if (concertHall.length() > 10) {
            throw new IllegalArgumentException("Concert hall must not exceed 10 characters.");
        }
        this.concertHall = concertHall;
    }

    public void setEventCode(String eventCode) {
        if (eventCode == null) {
            throw new IllegalArgumentException("Event code cannot be null.");
        } else if (eventCode.length() != 3 || !eventCode.matches("\\d{3}")) {
            throw new IllegalArgumentException("Event code must be exactly 3 digits.");
        }
        this.eventCode = eventCode;
    }


    public void setStadiumSector(char sector) {
        if (sector < 'A' || sector > 'C') {
            throw new IllegalArgumentException("Stadium sector must be between 'A' and 'C'.");
        }
        this.stadiumSector = sector;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    //**ability to save ticket price
    public void toSaveTicketPrice(double price) {
        BigDecimal ticketPrice = new BigDecimal(price);
        ticketPrice = ticketPrice.setScale(2, RoundingMode.HALF_UP);
        this.price = ticketPrice;
        System.out.println("The ticket id: "+ this.id+" price is: " + this.price);
    }

    @Override
    public String toString() {
        Date time = new Date(this.time);
        Date creationTime = new Date(this.creationTime);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = formatter.format(time);
        String formattedCreationTime = formatter.format(creationTime);
        return "Ticket{" +
                "id='" + id + '\'' +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode='" + eventCode + '\'' +
                ", time=" + formattedDateTime +
                ", isPromo=" + isPromo +
                ", stadiumSector=" + stadiumSector +
                ", maxBackpackWeightInKg=" + maxBackpackWeightInKg +
                ", creationTime=" + formattedCreationTime +
                '}';
    }
}
