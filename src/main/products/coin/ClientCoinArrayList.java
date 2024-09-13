package main.products.coin;

import lombok.Getter;
import main.enums.CoinType;

/**
 * A custom collection implementation for storing Coin objects.
 * Provides methods to put, to get, delete coins
 * and exchange coins for bonus when 10 coins of the same type are accumulated.
 * Handles dynamic resizing with an increment of +10.
 */
@Getter
public class ClientCoinArrayList {
    private Coin[] coins;
    private int size;
    private int capacity;

    private int goldCount = 0;
    private int silverCount = 0;
    private int bronzeCount = 0;

    public ClientCoinArrayList() {
        capacity = 10; // 10 - min count of coins to take bonus
        coins = new Coin[capacity];
        size = 0;
    }

    public void putCoin(Coin coin) {
        if (size == capacity) {
            resize();
        }
        coins[size++] = coin;
    }

    public Coin getCoin(int index) {
        checkIndexInBound(index);
        return coins[index];
    }

    public void deleteCoin(int index) {
        checkIndexInBound(index);
        for (int i = index; i < size - 1; i++) {
            coins[i] = coins[i + 1];
        }
        size--;
    }

    public boolean exchangeCoinsForBonus(CoinType coinType) {
        int coinsForGift = 10; // Necessary count of coins for a bonus

        countCoinsByType();

        switch (coinType) {
            case GOLD:
                if (goldCount >= coinsForGift) {
                    System.out.println("You exchanged 10 gold coins for a bonus!");
                    removeCoins(CoinType.GOLD, 10);
                    return true;
                }
                break;
            case SILVER:
                if (silverCount >= coinsForGift) {
                    System.out.println("You exchanged 10 silver coins for a bonus!");
                    removeCoins(CoinType.SILVER, 10);
                    return true;
                }
                break;
            case BRONZE:
                if (bronzeCount >= coinsForGift) {
                    System.out.println("You exchanged 10 bronze coins for a bonus!");
                    removeCoins(CoinType.BRONZE, 10);
                    return true;
                }
                break;
            default:
                System.out.println("No such type of coins");
                return false;
        }
        System.out.println("Not enough coins for a bonus.");
        return false;
    }

    private void checkIndexInBound(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void removeCoins(CoinType coinType, int count) {
        int removed = 0;
        for (int i = 0; i < coins.length && removed < count; i++) {
            if (coinType.equals(getCoin(i).getType())) {
                deleteCoin(i);
                i--;
                removed++;
            }
        }
    }

    private void countCoinsByType() {
        for (int i = 0; i < size; i++) {
            CoinType coinType = coins[i].getType();
            if (CoinType.GOLD.equals(coinType)) goldCount++;
            else if (CoinType.SILVER.equals(coinType)) silverCount++;
            else if (CoinType.BRONZE.equals(coinType)) bronzeCount++;
        }
    }

    private void resize() {
        capacity += 10;
        Coin[] newCoins = new Coin[capacity];
        System.arraycopy(coins, 0, newCoins, 0, size);
        coins = newCoins;
        System.out.println("Capacity increased to " + capacity);
    }
}