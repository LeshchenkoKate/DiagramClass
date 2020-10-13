package src;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DiagramClassTest {

    @Test
    public void internalTestHit() {
        DiagramClass diagramClass = new DiagramClass(0, 0, "Test1",  new ArrayList<String>(Arrays.asList("-int count;")),  new ArrayList<String>(Arrays.asList("+void main();")));
        boolean test1 = diagramClass.internalTestHit(3,3);
        boolean result1 = true;
        assertEquals(result1, test1);
        boolean test2 = diagramClass.internalTestHit(35,30);
        boolean result2 = false;
        assertEquals(result2, test2);
    }

    @Test
    public void getMinX(){
        DiagramClass diagramClass = new DiagramClass(50, 50, "Test1",  new ArrayList<String>(Arrays.asList("-int count;")),  new ArrayList<String>(Arrays.asList("+void main();")));
        double test1 = diagramClass.getMinX();
        double result1 = 17.5;
        assertEquals(result1, test1, 0.001);
    }
    @Test
    public void getMinY(){
        DiagramClass diagramClass = new DiagramClass(50, 50, "Test1",  new ArrayList<String>(Arrays.asList("-int count;")),  new ArrayList<String>(Arrays.asList("+void main();")));
        double test1 = diagramClass.getMinY();
        double result1 = 7.5;
        assertEquals(result1, test1, 0.001);
    }
    @Test
    public void getMaxX(){
        DiagramClass diagramClass = new DiagramClass(50, 50, "Test1",  new ArrayList<String>(Arrays.asList("-int count;")),  new ArrayList<String>(Arrays.asList("+void main();")));
        double test1 = diagramClass.getMaxX();
        double result1 = 82.5;
        assertEquals(result1, test1, 0.001);
    }
    @Test
    public void getMaxY(){
        DiagramClass diagramClass = new DiagramClass(50, 50, "Test1",  new ArrayList<String>(Arrays.asList("-int count;")),  new ArrayList<String>(Arrays.asList("+void main();")));
        double test1 = diagramClass.getMaxY();
        double result1 = 92.5;
        assertEquals(result1, test1, 0.001);
    }
}