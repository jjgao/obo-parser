/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gopaser;

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
public class GOMappingTest {
    
    public GOMappingTest() {
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
     * Test of createNameFile method, of class GOMapping.
     */
    @Test
    public void testCreateNameFile() {
        System.out.println("createNameFile");
        String strOBOFile = "D:\\workspace\\GOParser\\GO Database\\GO_Database_20130720\\gene_ontology.1_2.obo";
        String strNameFile = "";
        GOMapping instance = new GOMapping();
        instance.createNameFile(strOBOFile, strNameFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createPathFile method, of class GOMapping.
     */
    @Test
    public void testCreatePathFile() {
        System.out.println("createPathFile");
        String strOBOFile = "";
        String strPathFile = "";
        GOMapping instance = new GOMapping();
        instance.createPathFile(strOBOFile, strPathFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
