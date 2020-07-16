package lab2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

public class Timetable2 extends AbstractTimetable {

    private static boolean skipBreaks=true;
    private Break[] breaks;

    Timetable2(Break[] breaks){
        this.setBreaks(breaks);
        this.timetable = new TreeMap<>();
    }

    public boolean canBeTransferredTo(Term term, Boolean full_time){
        if(super.canBeTransferredTo(term,full_time)) {
            if (Timetable2.isSkipBreaks()) {
                for (int i = 0; i < breaks.length; i++) {
                    if (term.earlierThan(breaks[i]) && term.endTerm().laterThan(breaks[i])) {
                        term.setHour(breaks[i].endTerm().getHour());
                        term.setMinute(breaks[i].endTerm().getMinute());
                    }
                    if (term.laterThan(breaks[i]) && breaks[i].endTerm().laterThan(term)) {
                        term.setHour(breaks[i].endTerm().getHour());
                        term.setMinute(breaks[i].endTerm().getMinute());
                    }
                }
            } else {
                for (int i = 0; i < breaks.length; i++) {
                    if (term.earlierThan(breaks[i]) && term.endTerm().laterThan(breaks[i])) {
                        return false;
                    }
                }
            }
            return true;
        }
        else return false;
    }

    public void perform(Action [] actions) {
        Lesson lesson;
        Term term;
        for (int i = 0; i < actions.length && timetable.size() != 0; i++) {
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
                for (int j = 0; j < breaks.length; j++) {
                    if ((term.earlierThan(breaks[j]) && term.endTerm().laterThan(breaks[j])) ||
                            ((term.laterThan(breaks[j]) && breaks[j].endTerm().laterThan(term)))) {
                        h = breaks[j].getHour() - (term.getDuration()) / 60;
                        m = breaks[j].getMinute() - (term.getDuration()) % 60;
                    }
                }
                if (m < 0) {
                    h--;
                    m += 60;
                }
                term.setHour(h);
                term.setMinute(m);
                if (canBeTransferredTo(term, lesson.isFulltime()) && !busy(term))
                    lesson.setTerm(term);
            }
        }
    }






    public Break[] getBreaks() {
        return breaks;
    }

    public void setBreaks(Break[] breaks) {
        this.breaks = breaks;
    }

    public static boolean isSkipBreaks() {
        return skipBreaks;
    }

    public static void setSkipBreaks(boolean skipBreaks) {
        Timetable2.skipBreaks = skipBreaks;
    }
    /*    public String toString(){
        ITimetable timetable = this;
        Day firstDay = Day.MON;
        Day lastDay = Day.SUN;
        Term firstTerm = new Term(8,0,firstDay,90);
        Term lastTerm = new Term(20,0,lastDay,90);
        Day day = firstDay;
        Term term = null;
        String res="            ";
        int br=-1;
        for(int i=1 ; i<8 ; i++ ){
            res+="*"+day.toSting();
            day = day.nextDay();
        }
        for(term = firstTerm ; term.endTerm().earlierThan(lastTerm) ; term = term.endTerm()) {
            res += "\n" + "            ************************************************" +
                    "**********************************************" + "\n";
            if(br<breaks.length && br>=0) {
                term.setHour(breaks[br].getTerm().endTerm().getHour());
                term.setMinute(breaks[br].getTerm().endTerm().getMinute());
            }
            br++;
            res += term.toString()+" - "+term.endTerm().toString()+"   ";
            for(int i=1 ; i<8 ; i++ ){
                term.setDay(day);
                if (timetable.busy(term) && timetable.get(term)!=null) {
                    res += "* "+((Timetable2) timetable).get(term).getName() + "     ";  // MOZE BYC NULL!!!!!!!!!
                }
                else
                    res += "*            ";
                day = day.nextDay();
            }
            if(br<breaks.length){
                //if (((Timetable2) timetable).breaks[br].getTerm().getHour() < term.getHour() ||
                //   (((Timetable2) timetable).breaks[br].getTerm().getHour() == term.getHour() &&
                //   term.getMinute() >= ((Timetable2) timetable).breaks[br].getTerm().getMinute())) {
                // res+=((Timetable2) timetable).breaks[br].getTerm()+"  \n";
                res += "\n" + "            **********************************************************************************************" + "\n";
                res+="\n------------------------------------------------------------------------------------------------------------\n";
                  //    }
            }
        }
        return res;
    }
*/

}
