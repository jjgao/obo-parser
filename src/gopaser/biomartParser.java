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
        List<ENS2GO> result = new ArrayList<ENS2GO>();
        List<String> lst = readFile(strFile);
        for(Iterator it = lst.iterator(); it.hasNext();)
        {
            String item = it.next().toString();
            String[] content = item.split(",");
            if(content.length<2)
            {
                it.remove();
                continue;
            }
            
            int index = findMapperIndex(result, content[0]);
            if(index<0)
            {
                ENS2GO mapper = new ENS2GO();
                mapper.strEnsemblID = content[0];
                mapper.GOList.add(content[1]);
                result.add(mapper);
            }
            else
            {
                result.get(index).GOList.add(content[1]);
            }
            it.remove();
        }
        return result;
    }
    
    private List<String> readFile(String strEnsFile)
    {
        FileReader fr;
        BufferedReader br;
        List<String> result = new ArrayList<String>();
        
        try
        {
            fr = new FileReader(strEnsFile);
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
            while(br.ready())
            {
                strLine = br.readLine();
                if (strLine.isEmpty())
                    continue;                
                result.add(strLine);
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

        return result;
    }
    
    private int findMapperIndex(List<ENS2GO> mapperList, String strEnsemblID)
    {
        int result = -1;
        for (int i=0; i<mapperList.size(); i++)
        {
            ENS2GO item = mapperList.get(i);
            if (item.strEnsemblID.equals(strEnsemblID))
            {
                result = i;
                return result;
            }
        }
        return result;
    }
    
    private int getGeneNumber(List<String> lst)
    {
        int iNum = 0;
        Set set = new HashSet();
        for (Iterator iter = lst.iterator(); iter.hasNext();)
        {
            String str = iter.next().toString();
            if(set.add(str))
                iNum++;
        }
        return iNum;
    }
    
}
