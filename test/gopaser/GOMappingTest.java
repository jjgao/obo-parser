/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gopaser;

import java.util.ArrayList;
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
     
    @Test
    public void testCreateNameFile() {
        System.out.println("createNameFile");
        String strOBOFile = "D:\\workspace\\GOParser\\GO Database\\GO_Database_20130720\\gene_ontology.1_2.obo";
        String strNameFile = "";
        GOMapping instance = new GOMapping();
        instance.createNameFile(strOBOFile, strNameFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of createPathFile method, of class GOMapping.
     
    @Test
    public void testCreatePathFile() {
        System.out.println("createPathFile");
        String strOBOFile = "D:\\workspace\\GOParser\\GO Database\\GO_Database_20130720\\gene_ontology.1_2.obo";
        String strPathFile = "";
        GOMapping instance = new GOMapping();
        instance.createPathFile(strPathFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/


    /**
     * Test of init method, of class GOMapping.
     
    @Test
    public void testInit() {
        System.out.println("init");
        String strOBOFile = "D:\\workspace\\GOParser\\GO Database\\GO_Database_20130720\\gene_ontology.1_2.obo";
        List<String> list = new ArrayList<String>();
        list.add("D:\\workspace\\GOPaser\\GO_Database\\GO_Database_20130720\\gene_ontology.1_2.obo");
        list.add("D:\\workspace\\GOPaser\\GO_Database\\GO_Database_20130720\\gene_ontology_ext.obo");
        list.add("D:\\workspace\\GOPaser\\GO_Database\\GO_Database_20130720\\goslim_aspergillus.obo");
        list.add("D:\\workspace\\GOPaser\\GO_Database\\GO_Database_20130720\\goslim_candida.obo");
        list.add("D:\\workspace\\GOPaser\\GO_Database\\GO_Database_20130720\\goslim_generic.obo");
        list.add("D:\\workspace\\GOPaser\\GO_Database\\GO_Database_20130720\\goslim_metagenomics.obo");
        list.add("D:\\workspace\\GOPaser\\GO_Database\\GO_Database_20130720\\goslim_pir.obo");
        list.add("D:\\workspace\\GOPaser\\GO_Database\\GO_Database_20130720\\goslim_plant.obo");
        list.add("D:\\workspace\\GOPaser\\GO_Database\\GO_Database_20130720\\goslim_pombe.obo");
        list.add("D:\\workspace\\GOPaser\\GO_Database\\GO_Database_20130720\\goslim_yeast.obo");
        
        GOMapping instance = new GOMapping();
        for (String str: list)
        {
            String nameFile = str.substring(0, str.length()-4) + "_name.txt";
            String pathFile = str.substring(0, str.length()-4) + "_path.txt";     
            instance.init(str);
            instance.createNameFile(nameFile);
            instance.createPathFile(pathFile);
            
        }
        //instance.init(strOBOFile);
        //instance.createNameFile("D:\\workspace\\GOParser\\GO Database\\GO_Database_20130720\\NameFile.txt");
        //instance.createPathFile("D:\\workspace\\GOParser\\GO Database\\GO_Database_20130720\\AllPathFile.txt");
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

   

    /**
     * Test of test method, of class GOMapping.
     
    @Test
    public void testTest() {
        System.out.println("test");
        GOMapping instance = new GOMapping();
        List<String> list = new ArrayList<String>();
        list.add("D:\\workspace\\GOParser\\GO Database\\GO_Database_20130720\\gene_ontology.1_2.obo");
        list.add("D:\\workspace\\GOParser\\GO Database\\GO_Database_20130720\\gene_ontology_ext.obo");
        list.add("D:\\workspace\\GOParser\\GO Database\\GO_Database_20130720\\goslim_aspergillus.obo");
        list.add("D:\\workspace\\GOParser\\GO Database\\GO_Database_20130720\\goslim_candida.obo");
        list.add("D:\\workspace\\GOParser\\GO Database\\GO_Database_20130720\\goslim_generic.obo");
        list.add("D:\\workspace\\GOParser\\GO Database\\GO_Database_20130720\\goslim_metagenomics.obo");
        list.add("D:\\workspace\\GOParser\\GO Database\\GO_Database_20130720\\goslim_pir.obo");
        list.add("D:\\workspace\\GOParser\\GO Database\\GO_Database_20130720\\goslim_plant.obo");
        list.add("D:\\workspace\\GOParser\\GO Database\\GO_Database_20130720\\goslim_pombe.obo");
        list.add("D:\\workspace\\GOParser\\GO Database\\GO_Database_20130720\\goslim_yeast.obo");
        for (String str: list)
        {
            instance.test(str);
        }
        
        //String strOBOFile = "D:\\workspace\\GOParser\\GO Database\\GO_Database_20130720\\gene_ontology.1_2.obo";
        //instance.test(strOBOFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of createMappingFile method, of class GOMapping.
     
    @Test
    public void testCreateMappingFile_String_String() {
        System.out.println("createMappingFile");
        String ensFile = "";
        String strOutputFile = "";
        GOMapping instance = new GOMapping();
        instance.createMappingFile(ensFile, strOutputFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of createMappingFile method, of class GOMapping.
     */
    @Test
    public void testCreateMappingFile_3args() {
        System.out.println("createMappingFile");
        String biomartFile = "";
        String strOutputFile = "";
        boolean bNested = false;
        GOMapping instance = new GOMapping();
        instance.init("D:\\workspace\\GOParser\\GO_Database\\GO_Database_20130720\\goslim_generic.obo");
        instance.createMappingFile("D:\\workspace\\GOParser\\GO_Database\\BioMart20130911\\Arabidopsis thaliana genes (TAIR10 (2010-09-TAIR10)).txt", "D:\\workspace\\GOParser\\TestingResults\\At_generic_mapping.txt", bNested);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
