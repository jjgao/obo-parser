/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gopaser;
import java.util.ArrayList;
import java.util.HashMap;

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
public class oboParserTest {
    
    public oboParserTest() {
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
     * Test of parser method, of class oboParser.
     */
    @Test
    public void testParser() {
        System.out.println("parser");
        //String strFile = "D:\\workspace\\GOPaser\\GO_Database\\GO_Database_20130720\\gene_ontology.1_2.obo";
//        String strFile = "D:\\workspace\\GOPaser\\GO_Database\\GO_Database_20130720\\goslim_generic.obo";
//        oboParser instance = new oboParser();
//        List<GOTerm> expResult = null;
//        List<GOTerm> result = instance.parser(strFile);
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
        
        oboParser instance = new oboParser();
        for (String str: list)
        {
            String nameFile = str.substring(0, str.length()-4) + "_name.txt";
            String pathFile = str.substring(0, str.length()-4) + "_path.txt";     
            instance.parser(str);           
        }

        
        
        
//        for(int i= 0; i<result.size(); i++)
//        {
//            GOTerm term = result.get(i);
//            if(i != term.index)
//                System.out.println("The " + Integer.toString(i) + "term's index is: " + Integer.toString(term.index));
//        }
//        for(GOTerm term: result)
//        {
//            System.out.print(Integer.toString(term.index) + "\t" + term.GOID + "\t" + term.GOName + "\t" + term.GOType + "\t");
//            for (int index: term.parents)
//            {
//                System.out.print(Integer.toString(index) + "\t");
//            }
//            System.out.println();
//        }
//        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMap method, of class oboParser.
     
    @Test
    public void testGetMap() {
        System.out.println("getMap");
        String strFile = "";
        oboParser instance = new oboParser();
        HashMap expResult = null;
        HashMap result = instance.getMap(strFile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of getIndex method, of class oboParser.
     
    @Test
    public void testGetIndex() {
        System.out.println("getIndex");
        String strID = "";
        oboParser instance = new oboParser();
        int expResult = 0;
        int result = instance.getIndex(strID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
}
