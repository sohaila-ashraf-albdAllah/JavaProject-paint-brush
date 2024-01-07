package mypkg;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JPanel;

public class FreeHand extends Geoshape {

    private Color color;
    private ArrayList<Integer> arrx = new ArrayList<>();
    private ArrayList<Integer> arry = new ArrayList<>();

    public FreeHand() {
        super();
    }

    public FreeHand(int x1, int y1, int x2, int y2, int fill, int dotted, Color col) {
        super(x1, y1, x2, y2, dotted, col);
        color = col;
        arrx.add(x1);
        arry.add(y1);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (this.dotted == 1) {
            Graphics2D g2d = (Graphics2D) g;
            float[] dash1 = {2f, 0f, 2f};
            BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
            g2d.setStroke(bs1);
            for (int i = 0; i < arrx.size() - 1; i++) {
                g2d.drawLine(arrx.get(i), arry.get(i), arrx.get(i + 1), arry.get(i + 1));
            }
            g2d.setStroke(new BasicStroke());// Reset the stroke to solid
        } else {
            for (int i = 0; i < arrx.size() - 1; i++) {
                g.drawLine(arrx.get(i), arry.get(i), arrx.get(i + 1), arry.get(i + 1));
            }
        }
    }

    public void addPoint(int x, int y) {
        arrx.add(x);
        arry.add(y);
    }
}
