/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gopaser;

import java.io.*;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author WANG
 */
public class biomartParserTest {
    
    public biomartParserTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of parser method, of class biomartParser.
     */
    @Test
    public void testParser() {
        System.out.println("parser");
        String strFile = "";
        
        biomartParser instance = new biomartParser();
        String dir = "D:\\workspace\\GOParser\\GO_Database\\BioMart20130911\\";
        File directory = new File(dir);
        File[] files = directory.listFiles();
        for(File file: files)
        {
            System.out.println(dir + file.getName());
            String prefix = file.getName().substring(file.getName().lastIndexOf(".")+1);
            if(!prefix.equals("txt"))
                continue;
            
            List<ENS2GO> result = instance.parser(dir + file.getName());
            for(ENS2GO item: result)
            {
                System.out.print(item.strEnsemblID + "\t");
                for(String id: item.GOList)
                {
                    System.out.print(id + ",");
                }
                System.out.println();
            }
        }

        List expResult = null;
        
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
