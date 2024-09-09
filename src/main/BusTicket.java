package main;

import lombok.*;
import main.annotations.NullableWarning;
import main.enums.TicketClass;
import main.enums.TicketType;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class BusTicket {
    @NullableWarning
    private TicketClass ticketClass;
    @NullableWarning
    private TicketType ticketType;
    private long startDate;
    private BigDecimal price;
}
