/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gopaser;

import java.io.*;
import java.util.*;


/**
 *
 * @author WANG
 */
public class biomartParser {
        
    public List<ENS2GO> parser(String strFile)
    {
        List<ENS2GO> result = readFile(strFile);
        return result;
    }
    
    private List<ENS2GO> readFile(String strFile)
    {
        FileReader fr;
        BufferedReader br;
        List<ENS2GO> result = new ArrayList<ENS2GO>();
        
        try
        {
            fr = new FileReader(strFile);
            br = new BufferedReader(fr);

            boolean bReady = false;
            String strLine;

            //if the first line is not in the right label, than give up to read file.
            if(br.ready())
            {
                strLine = br.readLine();
                if (!strLine.equals("Ensembl Gene ID,GO Term Accession"))
                {
                    br.close();
                    fr.close();
                    return result;
                }
            }
            
            // Read each line
            String strLast = "";
            ENS2GO tmpE2G = new ENS2GO();
            while(br.ready())
            {
                strLine = br.readLine();
                if (strLine.isEmpty())
                    continue;   
                String[] content = strLine.split(",");
                if(content.length<2)
                    continue;
                if(content[0].equals(tmpE2G.strEnsemblID))
                    tmpE2G.GOList.add(content[1]);
                else
                {
                    ENS2GO newE2G = new ENS2GO();
                    newE2G.clone(tmpE2G);
                    result.add(newE2G);
                    tmpE2G.clear();
                    tmpE2G.strEnsemblID = content[0];
                    tmpE2G.GOList.add(content[1]);
                }
                    
            } 

            br.close();
            fr.close();
        }
        catch (FileNotFoundException ex) 
        {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

        result.remove(0);
        return result;
    }
}
