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
 * @author Li Zhang
 */
public class oboParser 
{
    class tracePath
    {
        int index;
        String path;
    }
    
    public oboParser() {
        this.terms = new ArrayList<GOTerm>();
        this.path = new ArrayList<String>();
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
                        tmpTerm.index = terms.size();
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
                        tmpTerm.index = terms.size();
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
        
        fillChildren();
        buildAllPath();     
        return terms;
    }
    
    public HashMap<String, Integer> getMap(String strFile)
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
                    
                    //System.out.println(content[1] + "\t" + Integer.toString(iNum));
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
    
    private void fillChildren()
    {
       //System.out.println("Filling Children");
        if(terms==null || terms.isEmpty())
            return;
        
        for(GOTerm term: terms)
        {
            for(int index: term.parents)
            {
                terms.get(index).children.add(term.index);
            }
        }
        
        for(GOTerm term: terms)
        {
            if(term.children.size()==0 && term.parents.size()>0)
                term.bLeaf = true;
        }
    }
   
    private void buildAllPath()
    {
        if(terms==null || terms.isEmpty())
            return;
        
        for (int index=0; index<terms.size(); index++)
        {
            GOTerm term = terms.get(index);
            if (term.bLeaf)
                buildPath(term.index, term.path);
            for(String str: term.path)
                System.out.println(str + "\t" + term.GOID);
        }
    }
    
    
    
    private void buildPath(int termIndex, List<String> lstPath)
    {       
        if(terms==null || terms.isEmpty())
            return ;
                
        tracePath trace = new tracePath();
        trace.index = termIndex;
        trace.path = "";
        Queue<tracePath> items = new LinkedBlockingQueue<tracePath>(); 
        items.offer(trace);
     
        while (items.peek()!=null)
        {
            tracePath item = items.peek();
            
            GOTerm thisTerm = terms.get(item.index);
            //System.out.println("Working in " + thisTerm.GOID + " >>");
            
            if (thisTerm.GOName.equals(thisTerm.GOType))
            {
                if(thisTerm.GOName.equals("biological_process"))
                    lstPath.add("0."+ item.path);
                if(thisTerm.GOName.equals("molecular_function"))
                    lstPath.add("1."+ item.path);
                if(thisTerm.GOName.equals("cellular_component"))
                    lstPath.add("2."+ item.path);
                
                items.poll();
                continue;
            }
            
            for(int parent: thisTerm.parents)
            {
                GOTerm parentTerm = terms.get(parent);
                
                //Find the order of this term in its father
                int iOrder = -1;
                for(int index=0; index<parentTerm.children.size(); index++)
                {
                    GOTerm term = terms.get(parentTerm.children.get(index));
                    if(term.index == thisTerm.index)
                        iOrder = index;
                }
                if(iOrder >= 0)
                {
                    tracePath newTrace = new tracePath();
                    newTrace.index = parentTerm.index;
                    newTrace.path = Integer.toString(iOrder)+"."+item.path;
                    items.offer(newTrace);
                    //System.out.println("Pushed " + parentTerm.GOID + "\t" + Integer.toString(parentTerm.index));
                }
                else 
                    System.out.println("Relationship error between: " + thisTerm.GOID + " and " + parentTerm.GOID);
            }
            items.poll();
            //System.out.print("---------------Polled " + thisTerm.GOID + "\t" + Integer.toString(thisTerm.index));
            //System.out.println("\t" + "Current number of items in the queue is : " + Integer.toString(items.size()));
        }
    }
    
    public void buildPath(int termIndex, String currentPath)
    {
        if(terms==null || terms.isEmpty())
            return;
        
        GOTerm thisTerm = terms.get(termIndex);
        
        if (thisTerm.GOName.equals(thisTerm.GOType))
        {
            if(thisTerm.GOName.equals("biological_process"))
            {
                path.add(Integer.toString(0)+ "." + currentPath);
                return;
            }
            if(thisTerm.GOName.equals("molecular_function"))
            {
                path.add(Integer.toString(1)+ "." + currentPath);
                return;
            }
            if(thisTerm.GOName.equals("cellular_component"))
            {
                path.add(Integer.toString(2)+ "." + currentPath);
                return;
            }
        }
        
        System.out.println("Term " + thisTerm.GOID + "is reached");
        for(int parent: thisTerm.parents)
        {
            GOTerm parentTerm = terms.get(parent);
            //System.out.println("Term " + parentTerm.GOID + "have been searched");
            int iOrder = -1;
            for(int index=0; index<parentTerm.children.size(); index++)
            {
                GOTerm term = terms.get(index);
                if(term.index == thisTerm.index)
                    iOrder = index;
            }
            if(iOrder >= 0)
                buildPath(parent, Integer.toString(iOrder)+"."+currentPath);
        }
        System.out.println("Term " + thisTerm.GOID + "have been searched");
    }
   
    private List<GOTerm> terms;
    private List<String> path;
}
