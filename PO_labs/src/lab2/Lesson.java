package lab2;

public class Lesson {
    private Term term;
    private String name;
    private String teacherName;
    private int year;
    private boolean fulltime;
    private ITimetable timetable;


    public Lesson(ITimetable timetable, Term term, String name, String teacherName, int year){
        this.setTimetable(timetable);
        this.setName(name);
        this.setTerm(term);
        this.setTeacherName(teacherName);
        this.setYear(year);
        if(this.getTerm().getDay() ==Day.FRI){
            if(this.getTerm().getHour() <17)
                this.setFulltime(true);
            else
                this.setFulltime(false);
        }
        else {
            if (this.getTerm().getDay() == Day.SAT || this.getTerm().getDay() == Day.SUN)
                this.setFulltime(false);
            else
                this.setFulltime(true);
        }
    }

    public String toString(){
        String year="";
        String fulltime="";
        if (this.getYear() ==1) year="Pierwszy rok";
        if(this.getYear() ==2) year="Drugi rok";
        if(this.getYear() ==3) year="Trzeci rok";
        if(this.getYear() ==4) year="Czwarty rok";
        if(this.isFulltime()) fulltime="studiow stacjonarnych";
        else fulltime="studiow niestacjonarnych";
        return this.getName()+" ("+this.getTerm().getDay().toString()+" "+
                this.getTerm().getHour()+":"+this.getTerm().getMinute()+"-"+
                this.getTerm().endTerm().getHour()+":"+this.getTerm().endTerm().getMinute()+")\n"
                +year+" "+fulltime+"\n"+
                "Prowadzacy: "+this.getTeacherName();
      /*
        return this.getName() +" ("+ this.getTerm().getDay().toString()+" "
                + this.getTerm().getHour() +":"+ this.getTerm().getMinute() +"-"+
                this.getTerm().endTerm().getHour() +":"+ this.getTerm().endTerm().getMinute() +")";
    */}

    public boolean earlearDay(){
        /*if(this.isFulltime()){
            if(this.getTerm().getDay() ==Day.MON) return false;
        }
        else{
            if(this.getTerm().getDay() ==Day.FRI) return false;
            if(this.getTerm().getDay() ==Day.SAT && this.getTerm().getHour() <17) return false;
        }*/
        Term term=this.getTerm();
        term.setDay(this.getTerm().getDay().prevDay());
        if(this.getTimetable().canBeTransferredTo(term,this.isFulltime())){
            this.getTerm().setDay(this.getTerm().getDay().prevDay());
            return true;
        }
        return false;
    }

    public boolean laterDay(){
       /* if(this.isFulltime()){
            if(this.getTerm().getDay() ==Day.FRI) return false;
            if(this.getTerm().getDay() ==Day.THU && (this.getTerm().endTerm().getHour() >17 ||
                    (this.getTerm().endTerm().getHour() ==17
                            && this.getTerm().endTerm().getMinute() >0))) return false;
        }
        else{
            if(this.getTerm().getDay() ==Day.SUN) return false;
        }
        this.getTerm().setDay(this.getTerm().getDay().nextDay());
        return true;
        */
       Term term=this.getTerm();
       term.setDay(this.getTerm().getDay().nextDay());
       if(this.getTimetable().canBeTransferredTo(term,this.isFulltime())){
           this.getTerm().setDay(this.getTerm().getDay().nextDay());
           return true;
        }
        return false;
    }

    public boolean earlearTime(){
        int h= this.getTerm().getHour() -(this.getTerm().getDuration())/60;
        int m= this.getTerm().getMinute() -(this.getTerm().getDuration())%60;
        if(m<0) {
            h--;
            m+=60;
        }
     Term newTerm=new Term(h,m,this.getTerm().getDay(), this.getTerm().getDuration());
     if(this.getTimetable().canBeTransferredTo(newTerm,this.isFulltime())){
         this.getTerm().setHour(h);
         this.getTerm().setMinute(m);
         return true;
     }
     return false;
    }

    public boolean laterTime(){
        int h= this.getTerm().endTerm().getHour();
        int m= this.getTerm().endTerm().getMinute();
        Term newTerm=new Term(h,m,this.getTerm().getDay(),this.getTerm().getDuration());
        if(this.getTimetable().canBeTransferredTo(newTerm,this.isFulltime())){
            this.getTerm().setHour(h);
            this.getTerm().setMinute(m);
            return true;
        }
        return false;
    }

    public Term getTerm() { return term;    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isFulltime() {
        return fulltime;
    }

    public void setFulltime(boolean fulltime) {
        this.fulltime = fulltime;
    }

    public void setTimetable(ITimetable timetable) {
        this.timetable = timetable;
    }

    public ITimetable getTimetable() {
        return timetable;
    }

}
