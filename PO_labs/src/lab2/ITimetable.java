package lab2;

import java.util.ArrayList;
import java.util.List;

public interface ITimetable {
 //   public List<Lesson> ITimetable =new ArrayList<>();
    boolean canBeTransferredTo(Term term, Boolean full_time);
    boolean busy(Term term);
    boolean put(Lesson lesson);
    void perform(Action [] actions);
    Object get(Term term);
}
