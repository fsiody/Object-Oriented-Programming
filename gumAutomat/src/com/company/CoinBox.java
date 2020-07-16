package com.company;

public class CoinBox {
    private boolean coin;

    public CoinBox(){
        this.coin=false;
    }

    protected boolean setCoin(){
        if(this.isInsertCoin()){
            System.out.println("Error, coin is already there! ");
            return false;
        }
        System.out.println("We've got your coin! ");
        this.coin=true;
        return true;
    }

    protected boolean useCoin(){
        if(this.isInsertCoin()) {
            this.coin = false;
            return true;
        }
        else{
            System.out.println("Error, no coin ");
            return false;
        }
    }

    protected boolean isInsertCoin(){
        if(this.coin) {
            return true;
        }
        else{
            return false;
        }

    }

    protected boolean returnCoin(){
        if(this.isInsertCoin()){
            System.out.println("Please take your coin back :) ");
            this.coin=false;
            return true;
        }
        else{
            System.out.println("Error, no coin! ");
            return false;
        }


    }


}
