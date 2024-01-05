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
        this.color = Color.BLACK;
    }

    public FreeHand(int x1, int y1, int x2, int y2, int fill, int dotted, Color col) {
        super(x1, y1, x2, y2, dotted, col);
        x.add(x2);
        y.add(y2);
    }
    
    public void addPoint(int px, int py) {
        x.add(px);
        y.add(py);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (dotted == 1) {
            Graphics2D g2d = (Graphics2D) g.create();
            try {
                Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                g2d.setStroke(dashed);

                x.add(this.x2);
                y.add(this.y2);

                g2d.setColor(color);
                for (int i = 0; i < x.size() - 1; i++) {
                    g2d.drawLine(x.get(i), y.get(i), x.get(i + 1), y.get(i + 1));
                }
            } finally {
                g2d.dispose();
            }
        } else {
            x.add(this.x2);
            y.add(this.y2);
            for (int i = 0; i < x.size() - 1; i++) {
                g.drawLine(x.get(i), y.get(i), x.get(i + 1), y.get(i + 1));
            }
        }
    }
}
