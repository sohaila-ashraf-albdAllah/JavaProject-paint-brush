package mypkg;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Sohaila Ashraf
 */
public class Erase extends Geoshape {
    
    public Erase() {}
    public Erase(int x1, int y1, int x2, int y2, int fill, int dotted, Color col) 
    {
        super(x1, y1, x2, y2, dotted, col);
    }
    
    @Override
    public void draw(Graphics g) 
    {
        g.setColor(Color.WHITE);
        g.fillRect(x1, y1, 50, 50);
    }
}
