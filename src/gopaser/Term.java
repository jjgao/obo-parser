/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gopaser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wang
 */
public class Term implements Cloneable
{
    public Term() {
        this.parentsID = new ArrayList<String>();
        this.childrenID = new ArrayList<String>();
        this.clear();
    }

    public void clone(Term term)
    {
        this.GOID = term.GOID;
        this.GOName = term.GOName;
        this.GOType = term.GOType;

        List<String> parents = term.getAllParents();
        for (int i=0; i<parents.size(); i++)
        {
            this.parentsID.add(parents.get(i));
        }

        List<String> children = term.getAllChildren();
        for (int i=0; i<children.size(); i++)
        {
            this.parentsID.add(children.get(i));
        }
    }

    public void clear()
    {
        GOID = "";
        GOName = "";
        GOType = "";
        path  = "-1";
        parentsID.clear();
        childrenID.clear();
    }

    //Insert a parent to this term
    public void insertParent(String ID)
    {
        parentsID.add(ID);
    }

    //Insert a child to this term
    public void insertChild(String ID)
    {
        childrenID.add(ID);
    }

    //Return all the parents' ID of this term
    public List<String> getAllParents()
    {
        List<String> result = new ArrayList<String>();
        for(int i=0; i<parentsID.size(); i++)
            result.add(parentsID.get(i));
        return result;
    }


    /**
     * Return all the children's ID of this term
     * @return
     */
    public List<String> getAllChildren()
    {
        List<String> result = new ArrayList<String>();
        for(int i=0; i<childrenID.size(); i++)
            result.add(childrenID.get(i));
        return result;
    }

    public String GOID;
    public String GOName;
    public String GOType;
    public String path;
    private List<String> parentsID; 
    private List<String> childrenID;
}