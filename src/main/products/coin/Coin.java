package main.products.coin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Coin {

  public enum CoinType {
        GOLD, SILVER, BRONZE
    }

    private CoinType type;
}
