package lab2;

public enum Day implements Comparable<Day>{
    MON, TUE, WED, THU, FRI, SAT, SUN;

    public String toSting() {
        String d="";
        if (this == MON) d = " Poniedzialek";
        if (this == TUE) d = " Wtorek      ";
        if (this == WED) d = " Sroda       ";
        if (this == THU) d = " Czwartek    ";
        if (this == FRI) d = " Piątek      ";
        if (this == SAT) d = " Sobota      ";
        if (this == SUN) d = " Niedziela   ";
        return d;
    }

    public Day nextDay() {
        Day d=MON;
        if(this == MON) d = TUE;
        if (this == TUE) d = WED;
        if (this == WED) d = THU;
        if (this == THU) d = FRI;
        if (this == FRI) d = SAT;
        if (this == SAT) d = SUN;
        return d;
    }

    public Day prevDay() {
        Day d=Day.MON;
        if (this == WED) d = TUE;
        if (this == THU) d = WED;
        if (this == FRI) d = THU;
        if (this == SAT) d = FRI;
        if (this == SUN) d = SAT;
        if (this == MON) d = SUN;
        return d;
    }

   /* @Override
    public int compareTo(Day d2){
        if(this==MON){
            if(d2==MON)return 0;
            else return -1;
        }
        if(this==TUE){
            if(d2==MON)return 1;
            if(d2==TUE)return 0;
            else return -1;
        }
        if(this==WED){
            if(d2==MON || d2==TUE) return 1;
            if(d2==WED) return 0;
            else return -1;
        }
        if(this==THU){
            if(d2==MON||d2==TUE||d2==WED)return 1;
            if(d2==THU)return 0;
            else return -1;
        }
        if(this==FRI){
            if(d2==FRI)return 0;
            if(d2==SAT||d2==SUN)return -1;
            else return 1;
        }
        if(this==SAT){
            if(d2==SAT) return 0;
            if(d2==SUN) return -1;
            else return 1;

        }
        if(this==SUN){
            if(d2==SUN) return 0;
            else return 1;
        }
        return 1;
    }
*/
}
