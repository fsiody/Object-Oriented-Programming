package lab2;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Categories;
import lab2.Day;
import lab2.Term;
import lab2.Action;
import lab2.Lesson;
import lab2.ActionsParser;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;

@Category(IntegrationTest.class)
public class ITTest {
    public Term termFT1, termFT2, termFT3, termE1, termE2, termE3;
    public Lesson lessonFT1, lessonFT2, lessonFT3, lessonE1, lessonE2, lessonE3;
    Lesson[] lessons = {lessonFT1, lessonFT2, lessonFT3, lessonE1, lessonE2, lessonE3};

    @Before
    public void preparation() {
        Timetable1 timetable=new Timetable1();
        termFT1 = new Term(10, 0, Day.MON,90);
        lessonFT1 = new Lesson(timetable,termFT1, "", "", 1);
        termFT2 = new Term(16, 0, Day.WED,90);
        lessonFT2 = new Lesson(timetable,termFT2, "", "", 1);
        termFT3 = new Term(15, 0, Day.FRI,90);
        lessonFT3 = new Lesson(timetable,termFT3, "", "", 1);

        termE1 = new Term(18, 0, Day.FRI,90);
        lessonE1 = new Lesson(timetable,termE1, "", "", 1);
        termE2 = new Term(18, 0, Day.SAT,90);
        lessonE2 = new Lesson(timetable,termE2, "", "", 1);
        termE3 = new Term(18, 0, Day.SUN,90);
        lessonE3 = new Lesson(timetable,termE3, "", "", 1);
    }

    @Test
    public void ActionParserTest() {
        String[] arguments = {"d+", "d-", "d-", "t+", "t-", "t-"};
        Action[] actions = new ActionsParser().parse(arguments);
        for (Lesson lesson : lessons) {
            for (Action act : actions) {
                if (act == Action.DAY_EARLIER) lesson.earlearDay();
                if (act == Action.DAY_LATER) lesson.laterDay();
                if (act == Action.TIME_EARLIER) lesson.earlearTime();
                if (act == Action.TIME_LATER) lesson.laterTime();
            }
            System.out.println(lesson.toString());
            System.out.println();
        }
    }
}
