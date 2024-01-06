package mypkg;

import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author Sohaila Ashraf
 */
public class FreeHand extends Geoshape {

    Color color;
    ArrayList<Integer> arrx1 = new ArrayList<>();
    ArrayList<Integer> arry1 = new ArrayList<>();
    ArrayList<Integer> arrx2 = new ArrayList<>();
    ArrayList<Integer> arry2 = new ArrayList<>();

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
        arrx1.add(x1);
        arry1.add(y1);
        arrx2.add(x2);
        arry2.add(y2);
        for(int i = 0; i < arrx1.size(); i++)
        {
            g.drawLine(arrx1.get(i), arry1.get(i), arrx2.get(i), arry2.get(i));
        }
        
    }
}
