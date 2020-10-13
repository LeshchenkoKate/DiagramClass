package src;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DiagramDependencyTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getText() {
        DiagramDependency diagramDependency = new DiagramDependency(new DiagramClass(40, 40, "Class", new ArrayList<String>(Arrays.asList("-int count;", "-string name;")),  new ArrayList<String>(Arrays.asList("+void main();"))), new DiagramClass(40, 140, "Class2", new ArrayList<String>(Arrays.asList("-double x;", "-double y;")),  new ArrayList<String>(Arrays.asList("+int getsum();", "+void setsum();"))), "<<include>>");
        String test1 = diagramDependency.getText();
        String result1 = "<<include>>";
        assertEquals(result1, test1);
        DiagramDependency diagramDependency2 = new DiagramDependency(new DiagramClass(40, 40, "Class", new ArrayList<String>(Arrays.asList("-int count;", "-string name;")),  new ArrayList<String>(Arrays.asList("+void main();"))), new DiagramClass(40, 140, "Class2", new ArrayList<String>(Arrays.asList("-double x;", "-double y;")),  new ArrayList<String>(Arrays.asList("+int getsum();", "+void setsum();"))), "<<extend>>");
        String test2 = diagramDependency2.getText();
        String result2 = "<<extend>>";
        assertEquals(result2, test2);
    }

    @Test
    public void getLabelX() {
        DiagramDependency diagramDependency = new DiagramDependency(new DiagramClass(40, 40, "Class", new ArrayList<String>(Arrays.asList("-int count;", "-string name;")),  new ArrayList<String>(Arrays.asList("+void main();"))), new DiagramClass(40, 140, "Class2", new ArrayList<String>(Arrays.asList("-double x;", "-double y;")),  new ArrayList<String>(Arrays.asList("+int getsum();", "+void setsum();"))), "<<include>>");
        double test1 = diagramDependency.getLabelX();
        double result1 = 40;
        assertEquals(result1, test1, 0.001);
    }

    @Test
    public void getLabelY() {
        DiagramDependency diagramDependency = new DiagramDependency(new DiagramClass(40, 40, "Class", new ArrayList<String>(Arrays.asList("-int count;", "-string name;")),  new ArrayList<String>(Arrays.asList("+void main();"))), new DiagramClass(40, 140, "Class2", new ArrayList<String>(Arrays.asList("-double x;", "-double y;")),  new ArrayList<String>(Arrays.asList("+int getsum();", "+void setsum();"))), "<<include>>");
        double test1 = diagramDependency.getLabelX();
        double result1 = 40;
        assertEquals(result1, test1, 0.001);
    }
}