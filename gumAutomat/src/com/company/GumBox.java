package com.company;

public class GumBox {
    private int amount;
    private int maxgum;
    private boolean emptyTray;

    public GumBox(){
        this.maxgum=25;
        this.amount=0;
        this.emptyTray=true;
    }

    protected void addGum(int n){
        if(n>0) {
            if (this.amount + n <= maxgum) {
                System.out.println("Successful ");
                this.amount += n;
            } else {
                System.out.println("Error, " + (n - this.maxgum + this.amount) + " gums was not added, gumbox is full"); //println ?
                this.amount = this.maxgum;
            }
        }
        else{
            System.out.println("Error, " + n + " you have to add gums, not take them!!! D:[ "); //println ?
        }
    }

    protected void getGum(){
        System.out.println("Take your gum! ");
        this.emptyTray=false;
        this.amount-=1;
    }

    protected void takeGum() {
        if (this.emptyTray) {

            System.out.println("You should buy a gum to take it! :)  ");
        } else {
            System.out.println("You've taken gum! :)  ");
            this.emptyTray = true;
        }
    }

    protected boolean isEmpty(){
        if(this.amount<=0) return true;
        return false;
    }


}
