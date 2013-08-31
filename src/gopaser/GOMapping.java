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
    //public void mapping()
    public void createNameFile(String strOBOFile, String strNameFile)
    {
        oboParser parser = new oboParser();
        System.out.println("Perser:" + strOBOFile);
        List<GOTerm> terms = parser.parser(strOBOFile);
        buildTermTree(terms);
        for(GOTerm term: terms)
        {
            if(term.children.size()>0)
            {
                for(String str: term.path)
                    System.out.println(term.GOID + "\t" + str);
            }
        }
           
        System.out.println();
    }    
    
    
    public void createPathFile(String strOBOFile, String strPathFile)
    {
        
    }
    
    private void buildTermTree(List<GOTerm> terms)
    {
        fillChildren(terms);
        fillPath(terms);
    }
    
    private void fillChildren(List<GOTerm> terms)
    {
        System.out.println("Filling Children");
        if(terms==null || terms.isEmpty())
            return;
        
        for(GOTerm term: terms)
        {
            for(int index: term.parents)
            {
                terms.get(index).children.add(term.index);
            }
        }
        System.out.println("Filled Children");
    }
    
    private void fillPath(List<GOTerm> terms)
    {
        System.out.println("Filling Path");
        if(terms==null || terms.isEmpty())
            return;
        
        Queue<Integer> qTerms = new LinkedBlockingQueue<Integer>();
        for(GOTerm term: terms)
        {
            if(term.GOName.equals("biological_process"))
            {
                term.path.add("0");
                qTerms.offer(term.index);
            }
            if(term.GOName.equals("molecular_function"))
            {
                term.path.add("1");
                qTerms.offer(term.index);
            }
            if(term.GOName.equals("cellular_component"))
            {
                term.path.add("2");
                qTerms.offer(term.index);
            }
        }
        
        while (qTerms.peek()!=null)
        {
            int index = qTerms.peek();
            GOTerm father = terms.get(index);

            for (int i=0; i<father.children.size(); i++)
            {
                int childIndex = father.children.get(i);
                for(String fatherPath: father.path)
                {
                    terms.get(childIndex).path.add(fatherPath + "." + Integer.toString(i));
                }
                if (terms.get(childIndex).children.size() > 0)
                    qTerms.offer(childIndex);
            }
           
            qTerms.poll();
        }
        System.out.println("Filled Path");
    }
}
