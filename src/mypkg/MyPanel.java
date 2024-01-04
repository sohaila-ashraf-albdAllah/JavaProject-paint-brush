package mypkg;




import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.random;

public class MyPanel extends JPanel {
    private int x1, y1, x2, y2;
    private int flag,fill,dotted,undoflag;
    //BasicStroke b;
    JButton linebtn, rectbtn, ovalbtn,undobtn,savebtn,openbtn;
    Checkbox fillckbox,dottedckbox;
    ArrayList<Geoshape> shapeslist = new ArrayList<>();
    Rect rect = new Rect();
    Line line = new Line();
    Oval  oval = new Oval();

    public MyPanel() {
        this.setBackground(Color.white);
        flag = 0;
        fill=0;
        undoflag=0;
        linebtn = new JButton("Line");
        rectbtn = new JButton("Rectangle");
        ovalbtn = new JButton("Oval");
        undobtn=new JButton("undo");
        savebtn=new JButton("save");
        openbtn=new JButton("open");
        fillckbox= new Checkbox("fill");
        dottedckbox=new Checkbox("dotted");
        this.setFocusable(true);//مهم علشان مصدر  الليسنر هو البانيل(panel)
         /*The arrow operator is used to create lambda expressions, linking/separating parameters with the lambda body.
syntax: (parameters) -> {expression}; It is also an efficient way of implementing functional interfaces
 like onClickListeners in java.
             e->  replacement of new ActionListener(){
             @override
              public void actionPerformed(ActionEvent e){my implementation}
        }*/
        linebtn.addActionListener(e -> {
                    flag = 1;
                }
        );
        rectbtn.addActionListener(e -> {
            flag = 2;
                }
        );
        ovalbtn.addActionListener(e -> {
            flag = 3;
                }
        );
        savebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int w = getWidth(), h = getHeight();
                /* from class documentation
                   – width of the created image height
                   – height of the created image imageType
                   – type of the created image*/
                BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

               //createGraphics()------------>return Graphics2D object
                Graphics g = image.createGraphics();
                // to print the components to the specified Graphics
                print(g);
                //to release any system resources that in use
                g.dispose();

                try{
                    // static method  so it is called by class name
                    ImageIO.write(image, "jpeg", new File("mypaint"+(int)(random()*100)+".jpeg"));
                }catch(IOException ex){
                 ex.printStackTrace();
                }

            }
        });
        openbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("open");

            }
        });
        undobtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undoflag=1;
                 if(shapeslist.size()>0){
                     System.out.println(shapeslist.size());
                     shapeslist.remove((shapeslist.size())-1);
                     repaint();
                      }

            }
        });
        fillckbox.addItemListener(e -> fill=e.getStateChange());
        dottedckbox.addItemListener(e -> dotted=e.getStateChange());
        this.add(linebtn);
        this.add(rectbtn);
        this.add(ovalbtn);
        this.add(undobtn);
        this.add(savebtn);
        this.add(openbtn);
        this.add(fillckbox);
        this.add(dottedckbox);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                undoflag=0;
                System.out.println("pressed");
                x1  =line.x1=e.getX();
                y1 = line.y1=e.getY();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("released");
                x2 =e.getX();
                y2=e.getY();
              Geoshape shape = switch (flag) {
                  case 1 -> new Line(x1, y1, x2, y2, dotted);
                  case 2 -> new Rect(x1, y1, x2, y2, fill, dotted);
                  case 3 -> new Oval(x1, y1, x2, y2, fill, dotted);
                  default -> null;
              };
                if(shape!=null){
                  shapeslist.add(shape);
                  repaint();
              }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(fill==1){
                    rect.fill=oval.fill=1;
                }else{
                    rect.fill=oval.fill=0;
                }
                if(dotted==1){
                  line.dotted=1;
                }else{
                    line.dotted=0;
                }
                System.out.println("dragged");
                x2 = line.x2=e.getX();
                y2 = line.y2=e.getY();
                rect.setx1(x1,x2);
                rect.sety1(y1,y2);
                rect.setWidth(x1, x2);
                rect.setLength(y1, y2);
                oval.setx1(x1,x2);
                oval.sety1(y1,y2);
                oval.setWidth(x1, x2);
                oval.setLength(y1, y2);
                repaint();
            }
            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        //g.drawImage(image, 10, 10, this);

        for (Geoshape shape : shapeslist) {
            shape.draw(g);
           // System.out.println(dotted);
        }
        System.out.println(undoflag);
        if(undoflag==0){
        switch (flag) {
            case 1 -> line.draw(g);
            case 2 -> rect.draw(g);
            case 3 -> oval.draw(g);
        }
        }
    }
}
