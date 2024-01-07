package mypkg;

import java.awt.*;

abstract  class Geoshape {
    protected int x1,y1,x2,y2,dotted;
   protected Graphics2D g2d;

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
    void setx1(int x1){
        this.x1=x1;
    }
    void sety1(int y1){
        this.y1=y1;
    }
    void setx2(int x2){
        this.x2=x2;
    }
    void sety2(int y2){
        this.y2=y2;
    }
    int getx1(){
        return x1;
    }
    int gety1(){
        return y1;
    }
    int getx2(){
        return x2;
    }
    int gety2(){
        return y2;
    }
    void setdotted(int dotted){
        this.dotted=dotted;
    }
    void setcolor( Color col){
        this.col=col;
    }


    protected abstract void draw(Graphics g);
}