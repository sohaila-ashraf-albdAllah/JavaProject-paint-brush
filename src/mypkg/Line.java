package mypkg;

import java.awt.*;

class Line extends Geoshape{


    Line(){super();}
    Line(int x1, int y1, int x2, int y2,int dotted) {
        super(x1,y1, x2, y2,dotted);
    }

    @Override
    protected void draw(Graphics g) {

            g.drawLine(x1,y1,x2,y2);


    }
}