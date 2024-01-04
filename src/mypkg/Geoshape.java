package mypkg;

import java.awt.*;

abstract  class Geoshape {
    protected int x1,y1,x2,y2,dotted;
    protected Color col;
    Geoshape(){ this.x1=0;
        this.y1=0;
        this.x2=0;
        this.y2=0;
        this.dotted=0;}
    Geoshape(int x1,int y1,int x2,int y2,int dotted, Color col){
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        this.dotted=dotted;
        this.col = col;
    }

    protected abstract void draw(Graphics g);
}