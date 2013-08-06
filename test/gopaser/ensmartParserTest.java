/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gopaser;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wang
 */
public class ensmartParserTest {
    
    public ensmartParserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of parser method, of class ensmartParser.
     */
    @Test
    public void testParser() {
        System.out.println("parser");
        String strEnsFile = "D:\\workspace\\GOParser\\GOPaser\\build\\test\\TestData\\MappingFile\\Sc\\Ensembl_to_Nested-GO.txt";
        ensmartParser instance = new ensmartParser();
        List expResult = null;
        List result = instance.parser(strEnsFile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}