package com.company;

public class Main {

    public static void main(String[] args) {
        Automat gumAutomat=new Automat();
        gumAutomat.buyGum(); System.out.println();
        gumAutomat.insertCoin(); System.out.println();
        gumAutomat.buyGum();System.out.println();

        gumAutomat.authorization(); System.out.println();
        gumAutomat.buyGum();System.out.println();
        gumAutomat.insertCoin();System.out.println();
        gumAutomat.buyGum();System.out.println();

        gumAutomat.insertCoin();System.out.println();
        gumAutomat.removeCoin();System.out.println();

        gumAutomat.authorization(); System.out.println();
    }
}
