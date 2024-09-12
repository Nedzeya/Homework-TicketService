package main.products.coin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.enums.CoinType;

@AllArgsConstructor
@Getter
public class Coin {
    private CoinType type;
}
