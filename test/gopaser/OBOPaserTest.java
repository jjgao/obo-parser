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
     
    @Test
    public void testGetTerms() {
        System.out.println("getTerms");
        String strFile = "D:\\\\Google Drive\\\\Cytoscape 2013 Summer Program\\\\GO Database\\\\GO_Database_20130720\\\\goslim_generic.obo";
        OBOPaser instance = new OBOPaser();
        List expResult = null;
        instance.parser(strFile);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of parser method, of class OBOPaser.
     
    @Test
    public void testParser() {
        System.out.println("parser");
        String strFile = "D:\\workspace\\GOParser\\GOPaser\\build\\test\\TestData\\GO_Database_20130720\\goslim_yeast.obo";
        OBOPaser instance = new OBOPaser();
        instance.parser(strFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of createNameFile method, of class OBOPaser.
     
    @Test
    public void testCreateNameFile() {
        System.out.println("createNameFile");
        String strOutputFile = "";
        OBOPaser instance = new OBOPaser();
        instance.createNameFile(strOutputFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of createPathFile method, of class OBOPaser.
     
    @Test
    public void testCreatePathFile() {
        System.out.println("createPathFile");
        String strOutputFile = "";
        OBOPaser instance = new OBOPaser();
        instance.createPathFile(strOutputFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of createMappingFile method, of class OBOPaser.
     */
    @Test
    public void testCreateMappingFile() {
        System.out.println("createMappingFile");
        String ensFile = "D:\\workspace\\GOParser\\GOPaser\\build\\test\\TestData\\MappingFile\\Sc\\Ensembl_to_Nested-GO.txt";
        String outputFile = "D:\\workspace\\GOParser\\GOPaser\\build\\test\\TestData\\MappingFile\\Sc\\Sc_GOslim_Current.txt";
        String strFile = "D:\\workspace\\GOParser\\GOPaser\\build\\test\\TestData\\GO_Database_20130720\\gene_ontology.1_2.obo";
        boolean bNested = false;
        OBOPaser instance = new OBOPaser();
        instance.parser(strFile);
        instance.createMappingFile(ensFile, outputFile, bNested);
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