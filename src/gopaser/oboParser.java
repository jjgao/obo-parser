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
public class oboParser 
{
    public oboParser() {
        this.terms = new ArrayList<GOTerm>();
    }
    
    
    public List<GOTerm> parser(String strFile)
    {
        FileReader fr;
        BufferedReader br;

        HashMap<String, Integer> map = getMap(strFile);
        
        try
        {
            fr = new FileReader(strFile);
            br = new BufferedReader(fr);

            int iNum = 0;
            boolean bReady = false;
            String strLine;
            String strSec = "";
            GOTerm term = new GOTerm();
            //Topology struct = new Topology();
            while(br.ready())
            {
                strLine = br.readLine();
                if (strLine.isEmpty())
                {
                    continue;
                }
                
                String[] content = strLine.split("\\s+");
                if (content[0].equals("[Term]"))
                {
                    iNum++;
                    if (bReady == false)
                    {
                        bReady = true; 
                        continue;
                    }
                    else
                    {
                        GOTerm tmpTerm = new GOTerm();
                        tmpTerm.clone(term);
                        tmpTerm.index = iNum-1;
                        terms.add(tmpTerm);
                        term.clear();
                        continue;
                    }
                }
                if (content[0].equals("[Typedef]"))
                {
                    if (bReady == true)
                    {
                        bReady = false;
                        GOTerm tmpTerm = new GOTerm();
                        tmpTerm = term;
                        tmpTerm.index = iNum;
                        terms.add(tmpTerm);
                        //term.clear();
                        continue;
                    }
                    if (bReady == false)
                        continue;
                }
                
                if (bReady == false)
                    continue;
                
                String strTmp = content[0];
                if (strTmp.equals("id:"))
                {
                    term.GOID = content[1];
                    continue;
                }
                if (strTmp.equals("name:"))
                {
                    term.GOName = content[1];
                    continue;
                }
                if (strTmp.equals("namespace:"))
                {
                    term.GOType = content[1];
                    continue;
                }
                if (strTmp.equals("is_a:"))
                {
                    int index = map.get(content[1]);
                    term.parents.add(index);
                }
            }
            br.close();
            fr.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        
        return terms;
    }
    
    private HashMap<String, Integer> getMap(String strFile)
    {
        HashMap<String, Integer> map = new HashMap();
        FileReader fr;
        BufferedReader br;
        
        try
        {
            fr = new FileReader(strFile);
            br = new BufferedReader(fr);

            int iNum = 0;
            boolean flag = false;
            String strLine;
            String strSec = "";
            GOTerm term = new GOTerm();
            //Topology struct = new Topology();
            while(br.ready())
            {
                strLine = br.readLine();
                if (strLine.isEmpty())
                {
                    continue;
                }
                
                String[] content = strLine.split("\\s+");
                if (flag)
                {
                    map.put(content[1], iNum);
                    iNum++;
                    flag = false;
                }
                 
                if (content[0].equals("[Term]"))
                    flag = true;
            }
            br.close();
            fr.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        
        return map;
    }
    
    private List<GOTerm> terms;
}
