package main.products.busTicket;

import lombok.*;
import main.enums.TicketClass;
import main.enums.TicketType;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class BusTicket {
    private TicketClass ticketClass;
    private TicketType ticketType;
    private LocalDate startDate;
    private BigDecimal price;
}
