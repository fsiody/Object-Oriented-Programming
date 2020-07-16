package lab2;

import java.util.Objects;

public class Term extends BasicTerm implements Comparable<Term> {
    private Day day;

    Term(int h, int m, Day d, int dur) {
        super(h,m,dur);
        this.setDay(d);
    }

    public boolean equals(Term term) {
        if (super.equals(term) && term.getDay() == this.getDay()) return true;
        return false;
    }

    @Override
    public int compareTo(Term t2) {
        Term t1 = this;
        if (t1.equals(t2)) return 0;
        else if (t1.earlierThan(t2)) return -1;
        else return 1;
    }

    public Term endTerm(Term term) {
        int delta;
        delta = term.getMinute() - this.getMinute() + 60 * (term.getHour() - this.getHour());
        Term endTime = new Term(this.getHour(), this.getMinute(), this.getDay(), this.getDuration());
        endTime.setDuration(delta);
        return endTime;
    }

    public Term endTerm() {
        int m = (this.getMinute() + this.getDuration()) % 60;
        int h = this.getHour() + (this.getMinute() + this.getDuration()) / 60;
        Term term = new Term(h, m, this.getDay(),this.getDuration());       //null !!!!!!!!!!!!
        term.setDuration(this.getDuration());
        return term;
    }

   // @Override
    public boolean earlierThan(Term term) {
        Day d1=this.getDay();
        Day d2=term.getDay();
        if(this.compareTo(term)<0) return true;
        else
            if(this.compareTo(term)>0) return false;
        if (term.getHour() > this.getHour()) return true;
        else {
            if (term.getHour() == this.getHour() && term.getMinute() >= this.getMinute()) return true;
        }
        return false;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

}
/*
   */