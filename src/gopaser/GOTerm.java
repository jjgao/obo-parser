/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gopaser;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author WANG
 */
public class GOTerm implements Cloneable
{
    public GOTerm() {
        this.parents = new ArrayList<Integer>();
        this.children = new ArrayList<Integer>();
        this.path = new ArrayList<String>();
        this.clear();
    }
    
    
    public void clear()
    {
        GOID = "";
        GOName = "";
        GOType = "";
        path.clear();
        parents.clear();
        children.clear();
    }
    
    
    public void clone(GOTerm term)
    {
        this.index = term.index;
        this.GOID = term.GOID;
        this.GOName = term.GOName;
        this.GOType = term.GOType;

        for (int i=0; i<term.parents.size(); i++)
        {
            this.parents.add(term.parents.get(i));
        }

        for (int i=0; i<term.children.size(); i++)
        {
            this.children.add(term.children.get(i));
        }
        
        for (int i=0; i<term.path.size(); i++)
        {
            this.path.add(term.path.get(i));
        }
    }
    
    public int index;
    public String GOID;
    public String GOName;
    public String GOType;
    public List<String> path;
    public List<Integer> parents; 
    public List<Integer> children;
}
