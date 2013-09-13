/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gopaser;
import java.util.*;

/**
 *
 * @author WANG
 */
public class ENS2GO implements Cloneable
{
    public ENS2GO()
    {
        this.strEnsemblID = "";
        this.GOList = new ArrayList<String>();
    }
    
    public void clear()
    {
        this.strEnsemblID = "";
        this.GOList.clear();
    }
    
    public void clone(ENS2GO item)
    {
        this.strEnsemblID = item.strEnsemblID;
        this.GOList.addAll(item.GOList);
    }
    
    public String strEnsemblID;
    public List<String> GOList;
}
