/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gopaser;
import java.io.*;
import java.util.*;
/**
 *
 * @author wang
 */
public class ensmartParser 
{
    public class ens2goMapper
    {
        public ens2goMapper()
        {
            this.strEnsemblID = "";
            this.GOList = new ArrayList<String>();
        }
        public String strEnsemblID;
        public List<String> GOList;
    }
    
    public List<ens2goMapper> parser(String strEnsFile)
    {
        List<ens2goMapper> result = new ArrayList<ens2goMapper>();
        List<String> lst = readFile(strEnsFile);
        for(Iterator it = lst.iterator(); it.hasNext();)
        {
            String item = it.next().toString();
            String[] content = item.split("\\s+");
            int index = findMapperIndex(result, content[0]);
            if(index<0)
            {
                ens2goMapper mapper = new ens2goMapper();
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

            while(br.ready())
            {
                strLine = br.readLine();
                if (strLine.isEmpty())
                    continue;
                if (strLine.equals("Ensembl	GOID") || strLine.endsWith("GeneID	GO ID"))
                    continue;
                
//                String[] content = strLine.split("\\s+");
//                result.add(content[0]);
                
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
        
        //int iNum = getGeneNumber(result);
        return result;
    }
    
    private int findMapperIndex(List<ens2goMapper> mapperList, String strEnsemblID)
    {
        int result = -1;
        for (int i=0; i<mapperList.size(); i++)
        {
            ens2goMapper item = mapperList.get(i);
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
