package main;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class BusTicket {
    private TicketClass ticketClass;
    private TicketType ticketType;
    private long startDate;
    private BigDecimal price;
}
