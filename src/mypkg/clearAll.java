package mypkg;

import java.util.ArrayList;

/**
 *
 * @author Sohaila Ashraf
 */
public class clearAll {
    private ArrayList<Geoshape> shapeslist;
            
    public clearAll() {}
    public clearAll(ArrayList<Geoshape> shapeslist) {
        this.shapeslist = shapeslist;
    }
    
    public void clearPanel() {
        shapeslist.clear();
    }
}
