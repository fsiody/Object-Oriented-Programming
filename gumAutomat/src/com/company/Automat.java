package com.company;

import java.util.Scanner;

public class Automat {
    private GumBox gumBox;
    private CoinBox coinBox;

    public Automat(){
        this.gumBox=new GumBox();
        this.coinBox=new CoinBox();
    }

    public void insertCoin(){
        System.out.println("You are trying to add a coin... ");
        if (!this.gumBox.isEmpty()) {
            this.coinBox.setCoin();
            System.out.println("Coin added");
        }
        else System.out.println("There is no gums left, you cannot add a coin :( ");


    }

    public void removeCoin(){
        this.coinBox.returnCoin();
        //System.out.println("Coin returned");
    }

    public void buyGum() {
        System.out.println("You've pressed a button *buy a gum* :) ");
        if (!this.gumBox.isEmpty()) {
            if (this.coinBox.useCoin()) {
                this.gumBox.getGum();
                System.out.println("Enjoy your gum! :) ");
            }
        }
        else System.out.println("There is no gums left :( ");

    }

    public void takeGum(){
        this.gumBox.takeGum();
    }

    public void authorization(){
        System.out.println("You've pressed a button *authorization* :) ");
        System.out.println("Insert password: ");
        Scanner pss =new Scanner(System.in);
        String passwd=pss.nextLine();

        if(passwd.equals("GumBoxService")){
            Service s=new Service();
            this.gumBox.addGum(s.addGum());
        }
        else{
            System.out.println("Your password is incorrect  ");
            System.out.println(passwd);
            System.out.println("You are not allowed to take money and add guns D:[ ");
        }

    }

}
