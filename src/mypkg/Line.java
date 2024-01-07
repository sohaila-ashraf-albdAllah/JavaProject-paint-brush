package mypkg;

import java.awt.*;

class Line extends Geoshape{
    

    Line(){super();}
    Line(int x1, int y1, int x2, int y2,int dotted, Color col) {
        super(x1,y1, x2, y2,dotted, col);
    }



    @Override
    protected void draw(Graphics g) {
      g.setColor(col);
        if(dotted==1){
            g2d = (Graphics2D) g;
            float[] dash1 = { 2f, 0f,2f};
            BasicStroke bs1 = new BasicStroke(1,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_ROUND,
                    1.0f,
                    dash1,
                    2f);
            g2d.setStroke(bs1);
            g2d.drawLine(x1, y1, x2, y2);
            g2d.setStroke(new BasicStroke());//----->علشان ارجعه solid

        }
        else{
            g.drawLine(x1,y1,x2,y2);}

    }
}