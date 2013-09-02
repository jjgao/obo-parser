/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gopaser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author wang
 */
public class GOMapping 
{
    public GOMapping()
    {
        terms = null;
    }
    public void init(String strOBOFile)
    {
        terms = null;
        oboParser parser = new oboParser();
        terms = parser.parser(strOBOFile);
        
    }
    
    public void createNameFile(String strNameFile)
    {
        if (terms == null)
            return;
        
         try
        {
            File f = new File(strNameFile);
            f.createNewFile();
            FileWriter fw = new FileWriter(strNameFile);

            String strLine = "GOID" + "\t" + "GO Name" + "\t" + "GO Type" + "\n";
            fw.write(strLine);
            for (GOTerm term: terms)
            {
                strLine = "";
                strLine = term.GOID + "\t" + term.GOName + "\t" + term.GOType + "\n";
                fw.write(strLine);
            }
            fw.flush();

            fw.close();            
        }
        catch(IOException ex)
        {
            System.out.println(ex.toString());
        }
    }    
    
    
    public void createPathFile(String strPathFile)
    {
        if (terms == null)
            return;
        
        try
        {
            File f = new File(strPathFile);
            f.createNewFile();
            FileWriter fw = new FileWriter(strPathFile);

            String strLine = "Path" + "\t" + "GOID" + "\n";
            fw.write(strLine);
            for (GOTerm term: terms)
            {
                for(String path: term.path)
                {
                    strLine = "";
                    strLine = path.substring(0, path.length()-1) + "\t" + term.GOID + "\n";
                    fw.write(strLine);
                }
            }
            fw.flush();
            fw.close();            
        }
        catch(IOException ex)
        {
            System.out.println(ex.toString());
        }
    }
    
    public void test(String strOBOFile)
    {
        oboParser parser = new oboParser();
        List<GOTerm> terms = parser.parser(strOBOFile);

        for(GOTerm term: terms)
        {
            if(term.GOID.equals("GO:0046467"))
            {
                System.out.println();
            }
        }
        
        int iNum = 0;
        System.out.println("Testing of GO file" + "\t" + strOBOFile);
        System.out.println("TOTLE NUMBER: " + Integer.toString(terms.size()));
        System.out.println("--------------------------------------------------------------------------------------");
        
        //How many isolate terms
        System.out.println("The following terms are isolate:");
        System.out.println("GOID" + "\t" + "Children Number" + "\t" + "Parents Number");
        for(GOTerm term: terms)
        {
            if (term.children.size()==0 && term.parents.size()==0)
            {
                iNum++;
                //System.out.println(term.GOID + "\t" + Integer.toString(term.children.size()) + "\t" + Integer.toString(term.parents.size()));
            }
        }
        System.out.println("TOTLE NUMBER: " + Integer.toString(iNum));
        System.out.println("--------------------------------------------------------------------------------------");
                
        iNum = 0;
        System.out.println("The following terms have no parent but have children:");
        System.out.println("GOID" + "\t" + "Children Number" + "\t" + "Parents Number");
        for(GOTerm term: terms)
        {
            if (term.children.size()>0 && term.parents.size()==0)
            {
                iNum++;
                //System.out.println(term.GOID + "\t" + Integer.toString(term.children.size()) + "\t" + Integer.toString(term.parents.size()));
            }
        }
        System.out.println("TOTLE NUMBER: " + Integer.toString(iNum));
        System.out.println("--------------------------------------------------------------------------------------");
        
        iNum = 0;
        System.out.println("The following terms are leavies:");
        System.out.println("GOID" + "\t" + "Children Number" + "\t" + "Parents Number");
        for(GOTerm term: terms)
        {
            if (term.children.size()==0 && term.parents.size()>0)
            {
                iNum++;
                //System.out.println(term.GOID + "\t" + Integer.toString(term.children.size()) + "\t" + Integer.toString(term.parents.size()));
            }
        }
        System.out.println("TOTLE NUMBER: " + Integer.toString(iNum));
        System.out.println("--------------------------------------------------------------------------------------");
    }

    private List<GOTerm> terms;
}
