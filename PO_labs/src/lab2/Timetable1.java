package lab2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;


public class Timetable1 extends AbstractTimetable {

    public Timetable1() {
        this.timetable = new TreeMap<>(); //Term,Lesson>();
    }


    public void perform(Action [] actions) {
        Lesson lesson;
        Term term;
        for (int i = 0; i < actions.length; i++) {
            lesson = timetable.get((i % timetable.size()));
            term = new Term(lesson.getTerm().getHour(), lesson.getTerm().getMinute(), lesson.getTerm().getDay(), lesson.getTerm().getDuration());
            if (actions[i] == Action.DAY_EARLIER) {
                term.setDay(term.getDay().prevDay());
                if (canBeTransferredTo(term, lesson.isFulltime()) && !busy(term))
                    lesson.getTerm().setDay(term.getDay());
            }
            if (actions[i] == Action.DAY_LATER) {
                term.setDay(term.getDay().nextDay());
                if (canBeTransferredTo(term, lesson.isFulltime()) && !busy(term))
                    lesson.getTerm().setDay(term.getDay());
            }
            if (actions[i] == Action.TIME_LATER) {
                term = term.endTerm();
                if (canBeTransferredTo(term, lesson.isFulltime()) && !busy(term))
                    lesson.setTerm(term);
            }
            if (actions[i] == Action.TIME_EARLIER) {
                int h = term.getHour() - (term.getDuration()) / 60;
                int m = term.getMinute() - (term.getDuration()) % 60;
                if (m < 0) {
                    h--;
                    m += 60;
                }
                term.setHour(h);
                term.setMinute(m);
                if (canBeTransferredTo(term, lesson.isFulltime()) && !busy(term))
                    lesson.setTerm(term);
            }
            System.out.println(i);
            System.out.println(lesson.toString());
            System.out.println();

        }
    }


/*

  public Lesson get(Term term){
        for(Lesson lesson : timetable){
            if(lesson.getTerm().equals(term))
                return lesson;
        }
        return null;
    }
    public boolean canBeTransferredTo(Term term, Boolean full_time) {
        if(term.getHour()<8) return false;
        if(term.endTerm().getHour()>20 ||
                (term.endTerm().getHour()==20 && term.endTerm().getMinute()>0)) return false;
        if (full_time) {
            if ((term.getDay() == Day.FRI && (term.endTerm().getHour() > 17 ||
                    (term.endTerm().getHour() == 17 && term.endTerm().getMinute() > 0))) ||
                    term.getDay() == Day.SAT || term.getDay() == Day.SUN) return false;
            else return true;
        }
        else {
            if (term.getDay() == Day.FRI)
                if(term.getHour()<17) return false;
                else return true;
            if (term.getDay() == Day.SAT || term.getDay()==Day.SUN) return true;
            else return false;
        }
    }

public String toString() {
        Timetable1 timetable = this;
        Day firstDay = Day.MON;
        Day lastDay = Day.SUN;
        Term firstTerm = new Term(8,0,firstDay,90);
        Term lastTerm = new Term(20,0,lastDay,90);
        Day day = firstDay;
        Term term = null;
        String res="            ";
        for(int i=1 ; i<8 ; i++ ){
            res+="*"+day.toSting();
            day = day.nextDay();
        }
        for(term = firstTerm ; term.earlierThan(lastTerm) ; term = term.endTerm()) {
            res += "\n" + "            ************************************************" +
                    "**********************************************" + "\n";
            res += term.toString()+" - "+term.endTerm().toString()+"   ";
            for(int i=1 ; i<8 ; i++ ){
                term.setDay(day);
                if (timetable.busy(term)) {
                    res += "* "+((Timetable1) timetable).get(term).getName() + "     ";  // MOZE BYC NULL!!!!!!!!!
                }
                else
                    res += "*            ";
                day = day.nextDay();
            }
        }
        return res;
    }*/

}
