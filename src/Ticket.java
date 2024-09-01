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
    private Sector sector; // from A to C
    private double maxBackpackWeightInKg; // with prams precision
    //* ability to automatically detect and save creation time
    private static long creationTime = System.currentTimeMillis()/1000; //Creation time in seconds
    //** ticket price
    private BigDecimal price;

    public Ticket() {
    }

    public Ticket(String id,
                  String concertHall,
                  String eventCode,
                  long time,
                  boolean isPromo,
                  Sector sector,
                  double maxBackpackWeightInKg) {
        setId(id);
        setConcertHall(concertHall);
        setEventCode(eventCode);
        this.time = time;
        this.isPromo = isPromo;
        this.sector = sector;
        this.maxBackpackWeightInKg = maxBackpackWeightInKg;
    }

    public Ticket(String concertHall,
                  String eventCode,
                  long time) {
        setConcertHall(concertHall);
        setEventCode(eventCode);
        this.time = time;
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

    //**ability to save ticket price
    public void toSaveTicketPrice(double price) {
        BigDecimal ticketPrice = new BigDecimal(price);
        ticketPrice = ticketPrice.setScale(2, RoundingMode.HALF_UP);
        this.price = ticketPrice;
          }

    public String toStringTicketPrice() {
       return "The ticket id: " + this.id + " price is: " + this.price;
    }

    @Override
    public String toString() {
        Date time = new Date(this.time);
        Date creationTime = new Date(Ticket.creationTime*1000);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = formatter.format(time);
        String formattedCreationTime = formatter.format(creationTime);
        return "Ticket{" +
                "id='" + id + '\'' +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode='" + eventCode + '\'' +
                ", time=" + formattedDateTime +
                ", isPromo=" + isPromo +
                ", stadiumSector=" + sector +
                ", maxBackpackWeightInKg=" + maxBackpackWeightInKg +
                ", creationTime=" + formattedCreationTime +
                '}';
    }
}
