package main.products.coin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import main.enums.CoinType;

@AllArgsConstructor
@Getter
@ToString
public class Coin {
    private CoinType type;
}
