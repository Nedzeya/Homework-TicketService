package main;

import main.enums.CoinType;
import main.enums.Sector;
import main.products.coin.ClientCoinArrayList;
import main.products.coin.Coin;
import main.products.fine.Fine;
import main.products.fine.FineHashSet;
import main.products.ticket.Ticket;
import main.products.busTicket.BusTicket;
import main.products.busTicket.BusTicketReader;
import main.products.busTicket.BusTicketsValidator;
import main.products.ticket.TicketManager;
import main.users.Admin;
import main.users.Client;
import main.users.AbstractUser;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            //ClientCoinArrayList testing

            testExchangingCoinsForBonus();

            // FineHashSet testing
            testFineHashSet();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void testFineHashSet() {
        System.out.println("FineHashSet testing.....................");
        FineHashSet fineSet = new FineHashSet();

        Fine fine1 = new Fine(1, "Traveling without a ticket");
        Fine fine2 = new Fine(2, "Traveling without a ticket for luggage");
        Fine fine3 = new Fine(3, "Traveling without a ticket for an animal");
        Fine fine4 = new Fine(4, "Drunken traveling");

        fineSet.putFine(fine1);
        fineSet.putFine(fine2);
        fineSet.putFine(fine3);
        fineSet.putFine(fine4);
        System.out.println(fineSet.containsFine(2));
        fineSet.deleteFine(3);
        fineSet.iterateFines();
    }

    private static void testExchangingCoinsForBonus() {
        System.out.println("ClientCoinArrayList testing.....................");
        ClientCoinArrayList coinArrayList = new ClientCoinArrayList();

        for (int i = 0; i < 10; i++) {
            coinArrayList.putCoin(new Coin(CoinType.GOLD));
        }
        System.out.println("Getting coin: " + coinArrayList.getCoin(0));

        coinArrayList.exchangeCoinsForBonus(CoinType.GOLD);

        for (int i = 0; i < 10; i++) {
            coinArrayList.putCoin(new Coin(CoinType.SILVER));
        }
        coinArrayList.deleteCoin(0);
        coinArrayList.exchangeCoinsForBonus(CoinType.SILVER);
    }

    private static void saveTicketPrice(Ticket ticket) {
        ticket.toSaveTicketPrice(49.99);
        ticket.printTicketPrice();
    }

    private static Ticket createLimitedTicket(long eventTime1) {
        Ticket limitedTicket = new Ticket("Hall",
                "002",
                eventTime1);
        System.out.println(limitedTicket);
        return limitedTicket;
    }

    private static Ticket createFullTicket(long eventTime) {
        Ticket fullTicket = new Ticket("ID01",
                "Concert",
                "001", eventTime,
                true,
                Sector.A,
                5.5);
        System.out.println(fullTicket);
        return fullTicket;
    }

    private static Ticket createEmptyTicket() {
        Ticket emptyTicket = new Ticket();
        System.out.println(emptyTicket);
        return emptyTicket;
    }
}