package src;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElementClassTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void attributes() {

        ElementClass elementClass = new ElementClass();
        String test1 = elementClass.attributes("int test");
        String result1 = "-int test;";
        assertEquals(result1, test1);
        String test2 = elementClass.attributes("protected int test");
        String result2 = "~int test;";
        assertEquals(result2, test2);
        String test3 = elementClass.attributes("public int test");
        String result3 = "+int test;";
        assertEquals(result3, test3);
        String test4 = elementClass.attributes("private int test");
        String result4 = "-int test;";
        assertEquals(result4, test4);

    }

    @Test
    public void methods() {
        ElementClass elementClass = new ElementClass();
        String test1 = elementClass.methods("int test");
        String result1 = "-int test();";
        assertEquals(result1, test1);
        String test2 = elementClass.methods("protected int test");
        String result2 = "~int test();";
        assertEquals(result2, test2);
        String test3 = elementClass.methods("public int test");
        String result3 = "+int test();";
        assertEquals(result3, test3);
        String test4 = elementClass.methods("private int test");
        String result4 = "-int test();";
        assertEquals(result4, test4);
    }
}