package lab2;

import org.junit.Test;

import static org.junit.Assert.*;
public class TermTest {

    //  @Test
    //  public void toString() {
    //}
    Term term1=new Term(8,05, Day.MON,90);
    Term term11=new Term(8,05, Day.MON,90);
    Term term2=new Term(8,15, Day.TUE,90);
    Term term3=new Term(9,00, Day.THU,90);

    @Test
    public void earlierThan() {
        assertTrue(term1.earlierThan(term2));
        assertFalse(term2.earlierThan(term1));
        assertFalse(term3.earlierThan(term1));
        assertFalse(term1.earlierThan(term11));
    }

    @Test
    public void laterThan() {
        assertTrue(term2.laterThan(term1));
        assertFalse(term1.laterThan(term2));
        assertFalse(term1.laterThan(term3));
    }

    @Test
    public void endTerm() {
        assertEquals(10, term1.endTerm(term2).getDuration());
        assertEquals(45, term2.endTerm(term3).getDuration());
    }

    @Test
    public void endTerm1() {
        assertEquals(9, term1.endTerm().getHour());
        assertEquals(35, term1.endTerm().getMinute());
    }

    @Test
    public void equals() {
        assertFalse(term1.equals(term2));
        assertTrue(term1.equals(term11));
    }

}
