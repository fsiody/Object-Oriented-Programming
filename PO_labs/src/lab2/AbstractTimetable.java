package lab2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

public abstract class AbstractTimetable implements ITimetable{
    //List<Lesson> timetable;
    //LessonComparator lcomp = new LessonComparator();
    TreeMap<Term,Lesson> timetable;

    public AbstractTimetable() {
    //    this.timetable = new ArrayList<>();
        this.timetable=new TreeMap<Term,Lesson>();
    }

    public String toString() {
        AbstractTimetable timetable = this;
        Day firstDay = Day.MON;
        Day lastDay = Day.SUN;
        Term firstTerm = new Term(8, 0, firstDay, 90);
        Term lastTerm = new Term(20, 0, lastDay, 90);
        Day day = firstDay;
        Term term = null;
        String res = "            ";
        for (int i = 1; i < 8; i++) {
            res += "*" + day.toSting();
            day = day.nextDay();
        }
        for (term = firstTerm; term.earlierThan(lastTerm); term = term.endTerm()) {
            res += "\n" + "            ************************************************" +
                    "**********************************************" + "\n";
            res += term.toString() + " - " + term.endTerm().toString() + "   ";
            for (int i = 1; i < 8; i++) {
                term.setDay(day);
                if (timetable.busy(term)) {
                    res += "* " + timetable.get(term).getName() + "     ";  // MOZE BYC NULL???!!!
                } else
                    res += "*            ";
                day = day.nextDay();
            }
        }
        return res;
    }

    @Override
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
            if (term.getDay() != Day.SAT && term.getDay()!=Day.SUN &&
                    (term.getDay() == Day.FRI && term.getHour()<17))
                return false;

        }
        return true;
    }

    @Override
    public boolean busy(Term term) throws IllegalArgumentException{
        Boolean busy=false;
        Term occuping=null;
        for (Term key:timetable.keySet()) {
            Term lTerm = key;
            if (lTerm.getDay() == term.getDay()) {
                if (lTerm.getHour() < term.getHour()) {
                    if (lTerm.endTerm().getHour() == term.getHour()) {
                        if (lTerm.endTerm().getMinute() > term.getMinute()) { busy = true; occuping=lTerm; }
                    }
                    if (lTerm.endTerm().getHour() > term.getHour()) { busy = true; occuping=lTerm; }
                }
                if (lTerm.getHour() == term.getHour()) {
                    if (lTerm.endTerm().getHour() == term.getHour() && lTerm.endTerm().getMinute() > term.getMinute())
                    { busy = true; occuping=lTerm; }
                    if (lTerm.endTerm().getHour() > term.getHour()) { busy = true; occuping=lTerm; }
                }
                if (lTerm.getHour() > term.getHour()) {
                    if (term.endTerm().getHour() == lTerm.getHour()) {
                        if (term.endTerm().getMinute() > lTerm.getMinute()){ busy = true; occuping=lTerm; }
                    }
                    if (term.endTerm().getHour() > lTerm.getHour()) { busy = true; occuping=lTerm; }
                }
            }
        }


        if(busy) throw new IllegalArgumentException(term.toString() + "  is busy by  " + occuping.toString());
        return busy;
    }

    @Override
    public boolean put(Lesson lesson) {
        if (!busy(lesson.getTerm()) && canBeTransferredTo(lesson.getTerm(), lesson.isFulltime())) {
            timetable.put(lesson.getTerm(),lesson);
            return true;
        }
        return false;
    }

    @Override
    public Lesson get(Term term) {
        for (Term key:timetable.keySet()) {
            if (timetable.get(key).getTerm().equals(term))
                return timetable.get(key);
        }
        return null;
    }

}
