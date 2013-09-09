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
import gopaser.ensmartParser.ens2goMapper;
import java.util.HashMap;

/**
 *
 * @author wang
 */
public class GOMapping 
{
    public GOMapping()
    {
        terms = null;
        map = null;
    }
    public void init(String strOBOFile)
    {
        terms = null;
        map = null;
        oboParser parser = new oboParser();
        terms = parser.parser(strOBOFile);
        map = parser.getMap();
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
    
    public void createMappingFile(String ensFile, String strOutputFile, boolean bNested)
    {
        ensmartParser ens = new ensmartParser();
        List<ens2goMapper> mapper = ens.parser(ensFile);
        
        if (mapper.isEmpty())
            return;
        
        if (terms==null  || terms.isEmpty())
            return;
        
        if (map==null  || map.isEmpty())
            return;
        
        try
        {
            File f = new File(strOutputFile);
            f.createNewFile();
            FileWriter fw = new FileWriter(strOutputFile);

            String strLine = "Ensembl	annotation.GO BIOLOGICAL_PROCESS	annotation.GO CELLULAR_COMPONENT	annotation.GO MOLECULAR_FUNCTION" + "\n";
            fw.write(strLine);
            for (int i=0; i<mapper.size(); i++)
            {
                ens2goMapper item = mapper.get(i);
                strLine = item.strEnsemblID + "\t";
                String strBP = "";
                String strCC = "";
                String strMF = "";
                for (int j=0; j<item.GOList.size(); j++)
                {
                    String strGOID = item.GOList.get(j);
                    int index = map.get(strGOID);
                    if (index<0)
                        continue;
                    
                    GOTerm term = terms.get(index); 
                    if (term.GOType.equals("biological_process"))
                        strBP += "," + strGOID;
                    if (term.GOType.equals("cellular_component"))
                        strCC += "," + strGOID;
                    if (term.GOType.equals("molecular_function"))
                        strMF += "," + strGOID;
                }
                if(!strBP.isEmpty() && strBP.charAt(0)==',')
                    strBP = strBP.substring(1, strBP.length());
                if(!strCC.isEmpty() && strCC.charAt(0)==',')
                    strCC = strCC.substring(1, strCC.length());
                if(!strMF.isEmpty() && strMF.charAt(0)==',')
                    strMF = strMF.substring(1, strMF.length());
                strLine += strBP + "\t" + strCC + "\t" + strMF + "\n";
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
    private HashMap<String, Integer> map; 
}
