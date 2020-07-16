package lab2;

public class DeanerySystem {
    public static void main(String[] args) {
        Break[] breaks = {
                new Break(9, 30,5),
                new Break(11, 5, 10),
                new Break(12, 45, 5),
                new Break(14, 20, 20),
                new Break(16, 10, 5),
                new Break(17, 45, 5)};
        String[] arg = {"t-", "d+", " illegal arg ", "t+", "d-", "t-", "d-"};
        Action[] actions;
        ITimetable timetable;
        Lesson l1, l2, l3, l4;
        timetable = new Timetable2(breaks);
        l1 = new Lesson(timetable, new Term(8, 0, Day.MON, 90), "Fizyka", "Kakol", 2);
        l2 = new Lesson(timetable,new Term(8,0,Day.WED,90),"PBD","Zygmund",2);
        l3 = new Lesson(timetable,new Term(14,40,Day.FRI,90),"TC","Dlugopolski",2);
        l4 = new Lesson(timetable,new Term(17,50,Day.FRI,90),"Rurki","Los",2);
        timetable.put(l1); timetable.put(l2); timetable.put(l3); timetable.put(l4);

        Lesson l1Dublicate= new Lesson(timetable, new Term(8, 0, Day.MON, 90), "Fizyka2", "Kakol2", 2);



        try {
            actions = new ActionsParser().parse(arg);
            timetable.put(l1Dublicate);
        }
        catch (IllegalArgumentException exception){
            System.out.println(exception.getMessage());
        }




   /*     String[] arg = {"d-", "t-", "d+", "t+"};
        Break[] breaks = {new Break(new Term(9, 30, Day.MON, 5)),
                new Break(new Term(11, 5, Day.MON, 10)),
                new Break(new Term(12, 45, Day.MON, 5)),
                new Break(new Term(14, 20, Day.MON, 20)),
                new Break(new Term(16, 10, Day.MON, 5)),
                new Break(new Term(17, 45, Day.MON, 5))};

        Action[] actions = new ActionsParser().parse(args);
        ITimetable timetable = new Timetable2(breaks);
        Lesson l1 = new Lesson(timetable, new Term(8, 0, Day.MON, 90), "Fizyka", "Kakol", 2);
        Lesson l2 = new Lesson(timetable, new Term(9, 30, Day.MON, 90), "PO", "Polak", 2);
        Lesson l3 = new Lesson(timetable, new Term(17, 50, Day.MON, 90), "TC", "Dlugopolski", 2);
        Lesson l4 = new Lesson(timetable,new Term(9,30,Day.TUE,90),"PSI","Zabinsks",2);
        Lesson l5 = new Lesson(timetable,new Term(11,15,Day.TUE,90),"PO","Pohl",2);
        Lesson l6 = new Lesson(timetable,new Term(14,40,Day.TUE,90),"MOwNiT","Funika",2);
        Lesson l7 = new Lesson(timetable,new Term(17,50,Day.TUE,90),"Fizyka lab","Ciechanowski",2);
        Lesson l8 = new Lesson(timetable,new Term(9,30,Day.WED,90),"PBD","Zygmund",2);
        Lesson l9 = new Lesson(timetable,new Term(11,15,Day.WED,90),"Rurki","Schaefer",2);
        Lesson l10 = new Lesson(timetable,new Term(12,50,Day.THU,90),"PF","Debski",2);

        timetable.put(l1); timetable.put(l2);
        timetable.put(l3);
        timetable.put(l4); timetable.put(l5); timetable.put(l6);
        timetable.put(l7); timetable.put(l8); timetable.put(l9); timetable.put(l10);// timetable.put(l5);
        System.out.println(timetable.toString());
        */
      //  timetable.perform(actions);
      //  System.out.println(timetable.toString());
        //   ((Timetable2) timetable).toString1();
        /*
        Term term =new Term(18,0,Day.WED);
        Lesson lesson =new Lesson(term,"","",1);
        String[] arguments = {"d+","d-","d-","t+","t-","t-"};
        Action[] actions=new ActionsParser().parse(arguments);
        for(Action act:actions){
            if(act==Action.DAY_EARLIER) System.out.println(lesson.earlearDay());
            if(act==Action.DAY_LATER) System.out.println(lesson.laterDay());
            if(act==Action.TIME_EARLIER) System.out.println(lesson.earlearTime());
            if(act==Action.TIME_LATER) System.out.println(lesson.laterTime());
            System.out.println(lesson.toString());
        }

        Term term1 = new Term(9,45, Day.MON);
        Term term2 = new Term(18,15, Day.THU);
        Lesson lesson1 = new Lesson(term1,"","",1);
        Lesson lesson2 = new Lesson(term2,"","",1);
        Term term3 = new Term(17,0, Day.FRI);
        Term term4 = new Term(18,15, Day.SUN);
        Lesson lesson3 = new Lesson(term3,"","",1);
        Lesson lesson4 = new Lesson(term4,"","",1);

        System.out.println(lesson1.laterDay()); //Ma się wypisać: +
        System.out.println(lesson2.laterDay()); //Ma się wypisać: -
        System.out.println(lesson3.laterDay()); //Ma się wypisać: +
        System.out.println(lesson4.laterDay()); //Ma się wypisać: -
*/
    }
}