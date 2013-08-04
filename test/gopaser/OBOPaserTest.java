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
public class OBOPaserTest {
    
    public OBOPaserTest() {
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
     * Test of getTerms method, of class OBOPaser.
     */
    @Test
    public void testGetTerms() {
        System.out.println("getTerms");
        String strFile = "D:\\\\Google Drive\\\\Cytoscape 2013 Summer Program\\\\GO Database\\\\GO_Database_20130720\\\\goslim_generic.obo";
        OBOPaser instance = new OBOPaser();
        List expResult = null;
        instance.parser(strFile);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readTermsFromFile method, of class OBOPaser.
     
    @Test
    public void testReadTermsFromFile() {
        System.out.println("readTermsFromFile");
        String strFile;
        strFile = "D:\\Google Drive\\Cytoscape 2013 Summer Program\\GO Database\\GO_Database_20130720\\goslim_generic.obo";
        OBOPaser instance = new OBOPaser();
        instance.readTermsFromFile(strFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of completeChildrenIDs method, of class OBOPaser.
     
    @Test
    public void testCompleteChildrenIDs() {
        System.out.println("completeChildrenIDs");
        OBOPaser instance = new OBOPaser();
        instance.completeChildrenIDs();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
}