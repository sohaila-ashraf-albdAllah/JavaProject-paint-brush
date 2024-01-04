package mypkg;

import java.awt.*;

class Line extends Geoshape{
    
    Color color;
    
    Line(){super();}
    Line(int x1, int y1, int x2, int y2,int dotted, Color col) {
        super(x1,y1, x2, y2,dotted, col);
        color = col;
    }

    @Override
    protected void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(x1,y1,x2,y2);


    }
}