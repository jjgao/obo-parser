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
        //System.out.println("Perser:" + strOBOFile);
        List<GOTerm> terms = parser.parser(strOBOFile);
        buildTermTree(terms);
//        for(GOTerm term: terms)
//        {
//            if(term.children.size()>0)
//            {
//                for(String str: term.path)
//                    System.out.println(term.GOID + "\t" + str);
//            }
//        }
           
        //System.out.println();
    }    
    
    
    public void createPathFile(String strOBOFile, String strPathFile)
    {
        
    }
    
    public void test(String strOBOFile)
    {
        oboParser parser = new oboParser();
        List<GOTerm> terms = parser.parser(strOBOFile);
        fillChildren(terms);
        
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
    
    private void buildTermTree(List<GOTerm> terms)
    {
        fillChildren(terms);
        fillPath(terms);
    }
    
    private void fillChildren(List<GOTerm> terms)
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
        //System.out.println("Filled Children");
    }
    
    private void fillChildrenPath(int index, List<GOTerm> terms)
    {
        GOTerm father = terms.get(index);
                
        for(String path: father.path)
        {
            int iOrder = 0;
            for(int i=0; i<father.children.size(); i++)
            {
                GOTerm child = terms.get(father.children.get(i));
                child.path.add(path + "." + Integer.toString(i));
            }
        }
        
        for(int child: father.children)
        {
            fillChildrenPath(child, terms);
        }
    }
    
    private void fillPath(List<GOTerm> terms)
    {
        //System.out.println("Filling Path");
        if(terms==null || terms.isEmpty())
            return;
        
        int indexBP = -1;
        int indexMF = -1;
        int indexCC = -1;
        for(GOTerm term: terms)
        {
            if(term.GOName.equals("biological_process"))
            {
                term.path.add("0");
                indexBP = term.index;
            }
            if(term.GOName.equals("molecular_function"))
            {
                term.path.add("1");
                indexMF = term.index;                
            }
            if(term.GOName.equals("cellular_component"))
            {
                term.path.add("2");
                indexCC = term.index;                
            }
        }
        
        fillChildrenPath(indexBP, terms);
        fillChildrenPath(indexMF, terms);
        fillChildrenPath(indexCC, terms);
        
        
//        Queue<Integer> qTerms = new LinkedBlockingQueue<Integer>();
//        for(GOTerm term: terms)
//        {
//            if(term.GOName.equals("biological_process"))
//            {
//                term.path.add("0");
//                qTerms.offer(term.index);
//            }
//            if(term.GOName.equals("molecular_function"))
//            {
//                term.path.add("1");
//                qTerms.offer(term.index);
//            }
//            if(term.GOName.equals("cellular_component"))
//            {
//                term.path.add("2");
//                qTerms.offer(term.index);
//            }
//        }
//        
//        while (qTerms.peek()!=null)
//        {
//            int index = qTerms.peek();
//            GOTerm father = terms.get(index);
//
//            for (int i=0; i<father.children.size(); i++)
//            {
//                int childIndex = father.children.get(i);
//                for(String fatherPath: father.path)
//                {
//                    terms.get(childIndex).path.add(fatherPath + "." + Integer.toString(i));
//                }
//                if (terms.get(childIndex).children.size() > 0)
//                    qTerms.offer(childIndex);
//            }
//           
//            qTerms.poll();
//        }
        //System.out.println("Filled Path");
    }
}
