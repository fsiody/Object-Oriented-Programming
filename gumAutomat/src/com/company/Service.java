package com.company;

import java.util.Scanner;

public class Service {

    public int addGum(){
        System.out.println("Insert amount of gums: \n");
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        return n;
    }

}
