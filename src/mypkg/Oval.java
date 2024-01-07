package mypkg;

import java.awt.*;

 class Oval extends Geoshape{
     private int width,length,xstart,ystart,fill;


    Oval(){super();
        width=0;
        length=0;
        xstart=ystart=0;}
    Oval(int x1, int y1, int x2, int y2,int fill,int dotted, Color col) {
        super(x1, y1, x2, y2,dotted, col);
        this.xstart=Math.min(x1,x2);
        this.ystart=Math.min(y1,y2);
        width=Math.abs(x2-x1);
        length=Math.abs(y2-y1);
        this.fill=fill;
    }
    void setFill(int fill){
        this.fill=fill;
    }
     private void setxstart(){
         xstart=Math.min(x2,x1);
     }
     private  void setystart(){
         ystart=Math.min(y2,y1);
     }


     private void setWidth(){
         width=Math.abs(x2-x1);
     }
     private void setLength(){
         length=Math.abs(y2-y1);
     }

     @Override
     public void draw(Graphics g) {
         setxstart();
         setystart();
         setWidth();
         setLength();

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
             g2d.drawOval(xstart,ystart,width,length);

             g2d.setStroke(new BasicStroke());//----->علشان ارجعه solid

         }else{
             g.drawOval(xstart,ystart,width,length);
         }
         if(fill==1){
             g.fillOval(xstart,ystart,width,length);
         }
     }
}