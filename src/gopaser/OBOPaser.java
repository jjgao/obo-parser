/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gopaser;
import java.io.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue; 
/**
 *
 * @author wang
 */


public class OBOPaser
{

    public OBOPaser() {
        this.terms = new ArrayList<Term>();
    }

    /**
     * Obtain all terms recorded in OBO file that given by strFile
     * @param strFile: The full path of OBO file
     * @return
     */
    public void parser(String strFile)
    {
        readTermsFromFile(strFile);
        completeChildrenIDs();
        createPaths();
    }
    
    
    public void createNameFile(String strOutputFile)
    {
        if (terms == null)
            return;
        
        try
        {
            File f = new File(strOutputFile);
            f.createNewFile();
            FileWriter fw = new FileWriter(strOutputFile);

            String strLine = "GOID" + "\t" + "GO Name" + "\t" + "GO Type" + "\n";
            fw.write(strLine);
            for (int i=0; i<terms.size(); i++)
            {
                Term term = terms.get(i);
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
    
    
    public void createPathFile(String strOutputFile)
    {
        if (terms == null)
            return;
        
        try
        {
            File f = new File(strOutputFile);
            f.createNewFile();
            FileWriter fw = new FileWriter(strOutputFile);

            String strLine = "Path" + "\t" + "GOID" + "\n";
            fw.write(strLine);
            for (int i=0; i<terms.size(); i++)
            {
                Term term = terms.get(i);
                strLine = "";
                strLine = term.path + "\t" + term.GOID + "\n";
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
    
    
    public void createMappingFile(String ensFile, String outputFile, boolean bNested)
    {
        
    }
    
    private void readTermsFromFile(String strFile)
    {
        FileReader fr;
        BufferedReader br;

        try
        {
            fr = new FileReader(strFile);
            br = new BufferedReader(fr);

            int iNum = 0;
            boolean bReady = false;
            String strLine;
            String strSec = "";
            Term term = new Term();
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
                        Term tmpTerm = new Term();
                        tmpTerm.clone(term);
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
                        Term tmpTerm = new Term();
                        tmpTerm = term;
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
                    term.insertParent(content[1]);
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
    }
    
    private void completeChildrenIDs()
    {
        for (int i=0; i<terms.size(); i++)
        {
            Term tmpTerm = terms.get(i);
            List<String> parents = tmpTerm.getAllParents();
            for (int j=0; j<parents.size(); j++)
            {
                String parent = parents.get(j);
                for(int k=0; k<terms.size(); k++)
                {
                    String GOID = terms.get(k).GOID;
                    if(GOID.equals(parent))
                    {
                        terms.get(k).insertChild(tmpTerm.GOID);
                    }
                }
            }
        }
    }
    
    private void createPaths()
    {
        // Find the indexes of root nodes in all the terms
        Queue<Term> qTerm = new LinkedBlockingQueue<Term>(); 
        for (int i=0; i<terms.size(); i++)
        {
            Term tmpTerm = terms.get(i);
            if (tmpTerm.GOName.equals("biological_process"))
            {
                tmpTerm.path = "0";
                qTerm.offer(tmpTerm);
            }
            if (tmpTerm.GOName.equals("molecular_function"))
            {
                tmpTerm.path = "1";
                qTerm.offer(tmpTerm);
            }
            if (tmpTerm.GOName.equals("cellular_component"))
            {
                tmpTerm.path = "2";
                qTerm.offer(tmpTerm);
            }
        }
 
        while (qTerm.peek()!=null)
        {
            Term tmpTerm = qTerm.peek();
            //if (tmpTerm.childrenID.size()>1)

            List<String> children = tmpTerm.getAllChildren();
            for (int i=0; i<children.size(); i++)
            {
                String GOID = children.get(i);
                int index = findIndexbyID(GOID);
                Term term = terms.get(index);
                term.path = tmpTerm.path + "." + Integer.toString(i);
                if (term.getAllChildren().size() > 0)
                    qTerm.offer(term);
            }
            qTerm.poll();
        }
        
//        for (int i=0; i<terms.size(); i++)
//        {
//            Term term = terms.get(i);
//            System.out.println(term.GOID + "  " + term.GOType + "  " + term.path);
//        }
    }
    
    private List<String> getAllParents(String GOID)
    {
        Term term;
        int index;
        List<String> tmpList;
        List<String> result = new ArrayList<String>();
        Queue<String> qParents = new LinkedBlockingQueue<String>();
        qParents.offer(GOID);
        
        while (qParents.peek()!=null)
        {
            String id = qParents.peek();
            index = findIndexbyID(id);
            term = terms.get(index);
            tmpList = term.getAllParents();
            for (int i=0; i<tmpList.size(); i++)
            {
                result.add(tmpList.get(i));
                qParents.offer(tmpList.get(i));
            }
            tmpList.clear();
            term.clear();
            
            qParents.poll();
        }

        return result;
    }
    
    private int findIndexbyID(String GOID)
    {
        int index = -1;
        for(int i=0; i<terms.size(); i++)
        {
            if (GOID.equals(terms.get(i).GOID))
            {
                index = i;
                return index;
            }
        }
        return index;
    }
    
    private List<Term> terms;
}
