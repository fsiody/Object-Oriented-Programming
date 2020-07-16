package lab2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ITimetableTest {

    public Break[] breaks = {
            new Break(9, 30,5),
            new Break(11, 5, 10),
            new Break(12, 45, 5),
            new Break(14, 20, 20),
            new Break(16, 10, 5),
            new Break(17, 45, 5)};
    public String[] arg1 = {"t-", "d+", "t+", "d-", "t-", "d-"};
    public String[] arg2 = {"t-", "t+", "d+", "d-", "d-"};
    public String[] arg3 = {"d+", "t+", "d-", "t+", "t+", "t+"};
    public String[] arg4 = {"t-", "d-", "t+", "d+", "t-", "d+", "d+", "t+"};
    public Action[] actions;
    public ITimetable timetable;
    public Lesson l1, l2, l3, l4;

    @Before
    public void preparation(){
        timetable = new Timetable2(breaks);
        l1 = new Lesson(timetable, new Term(8, 0, Day.MON, 90), "Fizyka", "Kakol", 2);
        l2 = new Lesson(timetable,new Term(8,0,Day.WED,90),"PBD","Zygmund",2);
        l3 = new Lesson(timetable,new Term(14,40,Day.FRI,90),"TC","Dlugopolski",2);
        l4 = new Lesson(timetable,new Term(17,50,Day.FRI,90),"Rurki","Los",2);
        timetable.put(l1); timetable.put(l2); timetable.put(l3); timetable.put(l4);
    }


    @Test
    public void canBeTransferredTo() {
        assertTrue(timetable.canBeTransferredTo(l1.getTerm(),l1.isFulltime()));
        //assertFalse(timetable.canBeTransferredTo(l1.getTerm(),l1.isFulltime()));
        assertFalse(timetable.canBeTransferredTo(new Term(7,0,Day.MON,90), true));
        assertFalse(timetable.canBeTransferredTo(new Term(14,0,Day.SUN,90), true));
        assertTrue(timetable.canBeTransferredTo(new Term(14,0,Day.SUN,90),false));
        assertFalse(timetable.canBeTransferredTo(new Term(16,0,Day.FRI,90),false));
        assertFalse(timetable.canBeTransferredTo(new Term(17,0,Day.FRI,90),true));
    }

    @Test
    public void busy() {
        assertTrue(timetable.busy(l1.getTerm()));
        assertFalse(timetable.busy(new Term(8,0,Day.TUE,90)));
        assertFalse(timetable.busy(new Term(9,35,Day.SUN,90)));
    }

    @Test
    public void put() {
    }

    @Test
    public void perform() {
        actions = new ActionsParser().parse(arg1);
        ITimetable timetable1 = new Timetable2(breaks);
        timetable1.put(l1);
        timetable1.perform(actions);
        assertTrue(l1.getTerm().equals(new Term(8,0,Day.MON,90)));

        actions = new ActionsParser().parse(arg2);
        ITimetable timetable2 = new Timetable2(breaks);
        timetable2.put(l2);
        timetable2.perform(actions);
        assertTrue(l2.getTerm().equals(new Term(9,35,Day.TUE,90)));

        actions = new ActionsParser().parse(arg3);
        ITimetable timetable3 = new Timetable2(breaks);
        timetable3.put(l3);
        timetable3.perform(actions);
        assertTrue(l3.getTerm().equals(new Term(17,50,Day.THU,90)));

        actions = new ActionsParser().parse(arg4);
        ITimetable timetable4 = new Timetable2(breaks);
        timetable4.put(l4);
        timetable4.perform(actions);
        assertTrue(l4.getTerm().equals(new Term(17,50,Day.SUN,90)));
            }

    @Test
    public void get() {
        assertEquals(l1,timetable.get(l1.getTerm()));
        assertEquals(l2,timetable.get(l2.getTerm()));
        assertEquals(l3,timetable.get(l3.getTerm()));
        assertEquals(l4,timetable.get(l4.getTerm()));
        assertNotEquals(l1,timetable.get(l2.getTerm()));
    }
}