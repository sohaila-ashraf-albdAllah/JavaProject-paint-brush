package mypkg;

import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author Sohaila Ashraf
 */
public class FreeHand extends Geoshape {

    Color color;
    ArrayList<Integer> x = new ArrayList<>();
    ArrayList<Integer> y = new ArrayList<>();

    public FreeHand() {
        super();
    }

    public FreeHand(int x1, int y1, int x2, int y2, int fill, int dotted, Color col) {
        super(x1, y1, x2, y2, dotted, col);
        color = col;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        x.add(x2);
        y.add(y2);   
        for (int i = 0; i < x.size() - 1; i++) 
        {
            g.drawLine(x.get(i), y.get(i), x.get(i + 1), y.get(i + 1));
        }
    }
}
