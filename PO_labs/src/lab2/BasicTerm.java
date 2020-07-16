package lab2;

public class BasicTerm {
    protected int hour;
    protected int minute;
    protected int duration;

    BasicTerm(int h, int m, int dur) {
        this.setHour(h);
        this.setMinute(m);
        this.setDuration(dur);
    }

    public String toString() {

        return this.getHour() + ":" + this.getMinute();
    }



    public boolean earlierThan(BasicTerm term) {
        if (term.getHour() > this.getHour()) return true;
        else {
            if (term.getHour() == this.getHour() && term.getMinute() >= this.getMinute()) return true;
        }
        return false;
    }

    public boolean laterThan(BasicTerm term) {
        if (term.getHour() < this.getHour()) return true;
        else {
            if (term.getHour() == this.getHour() && term.getMinute() < this.getMinute()) return true;
        }
        return false;
    }

    public BasicTerm endTerm() {
        int m = (this.getMinute() + this.getDuration()) % 60;
        int h = this.getHour() + (this.getMinute() + this.getDuration()) / 60;
        BasicTerm term = new BasicTerm(h, m, this.getDuration());
        term.setDuration(this.getDuration());
        return term;
    }

    public BasicTerm endTerm(BasicTerm term) {   // ma sens?
        int delta;
        delta = term.getMinute() - this.getMinute() + 60 * (term.getHour() - this.getHour());
        BasicTerm endTime = new BasicTerm(this.getHour(), this.getMinute(), this.getDuration());
        endTime.setDuration(delta);
        return endTime;
    }



    public boolean equals(BasicTerm term) {
        if (term.getHour() != this.getHour()) return false;
        if (term.getMinute() != this.getMinute()) return false;
        if (term.getDuration() != this.getDuration()) return false;
        return true;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
