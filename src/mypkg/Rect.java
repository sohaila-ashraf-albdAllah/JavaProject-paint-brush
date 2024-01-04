package mypkg;

import java.awt.*;

 class  Rect extends Geoshape{
    int width;
    int length;
    int fill;
    Color color;

    Rect(){super();
        width=0;
        length=0;}
    Rect(int x1,int y1,int x2,int y2,int fill,int dotted, Color col){
        super(x1,y1,x2,y2,dotted, col);
        this.x1=Math.min(x1,x2);
        this.y1=Math.min(y1,y2);
        width=Math.abs(x2-x1);
        length=Math.abs(y2-y1);
        this.fill=fill;
        color = col;
    }
    void setx1(int x1,int x2){
        this.x1=Math.min(x1,x2);
    }
    void sety1(int y1,int y2){
        this.y1=Math.min(y1,y2);
    }


    void setWidth(int x1,int x2){
        width=Math.abs(x2-x1);
    }
    void setLength(int y1,int y2){
        length=Math.abs(y2-y1);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if(fill==1){
            g.fillRect(x1,y1,width,length);
        }
        else{
            g.drawRect(x1,y1,width,length);}
    }


}