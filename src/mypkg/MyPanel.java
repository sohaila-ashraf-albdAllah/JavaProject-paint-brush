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
    private int shapeflag, fill, dotted, undoflag,clearallflag;
    //BasicStroke b;
    JButton linebtn, rectbtn, ovalbtn, undobtn, savebtn, openbtn, freeHandDraw, erasebtn, clearAllbtn;

    JComboBox colors;
    Color color;

    Checkbox fillckbox, dottedckbox;
    ArrayList<Geoshape> shapeslist = new ArrayList<>();
    ArrayList<Geoshape> shapescopy = new ArrayList<>();

    Rect rect = new Rect();
    Line line = new Line();
    Oval oval = new Oval();
    FreeHand freehand = new FreeHand();

    private String colorOptions[]
            = {"Black","Gray", "Bule", "Green", "Yellow", "Cyne", "Magenta", "Red", "Orange"};

    private Color colorArray[]
            = {Color.BLACK,Color.GRAY, Color.BLUE, Color.GREEN, Color.YELLOW, Color.cyan, Color.MAGENTA, Color.RED, Color.orange};

    public MyPanel() {
        this.setBackground(Color.white);
        shapeflag = 0;
        fill = 0;
        undoflag = 0;
        clearallflag=0;
        linebtn = new JButton("Line");
        rectbtn = new JButton("Rectangle");
        ovalbtn = new JButton("Oval");
        undobtn = new JButton("undo");
        savebtn = new JButton("save");
        openbtn = new JButton("open");
        fillckbox = new Checkbox("fill");
        dottedckbox = new Checkbox("dotted");
        colors = new JComboBox(colorOptions);
        freeHandDraw = new JButton("Pencil");
        erasebtn = new JButton("Erase");
        clearAllbtn = new JButton("clear All");

        this.setFocusable(true);//مهم علشان مصدر  الليسنر هو البانيل(panel)
        /*The arrow operator is used to create lambda expressions, linking/separating parameters with the lambda body.
syntax: (parameters) -> {expression}; It is also an efficient way of implementing functional interfaces
 like onClickListeners in java.
             e->  replacement of new ActionListener(){
             @override
              public void actionPerformed(ActionEvent e){my implementation}
        }*/
        linebtn.addActionListener(e -> {
                    shapeflag = 1;
        }
        );
        rectbtn.addActionListener(e -> {
                    shapeflag = 2;
        }
        );
        ovalbtn.addActionListener(e -> {
                    shapeflag = 3;
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

                try {
                    // static method  so it is called by class name
                    ImageIO.write(image, "jpeg", new File("mypaint" + (int) (random() * 100) + ".jpeg"));
                } catch (IOException ex) {
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
                undoflag = 1;
                if(clearallflag==1){
                    repaint();
                }
                if (shapeslist.size() > 0) {
                    System.out.println(shapeslist.size());
                    shapeslist.remove((shapeslist.size()) - 1);
                    repaint();
                }

            }
        });
        colors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // Handle the selected item
                if (event.getSource() == colors) {
                    color = colorArray[colors.getSelectedIndex()];
                    System.out.println(color);
                }
            }
        });
        freeHandDraw.addActionListener(e -> {
                    shapeflag = 4;
        }
        );
        erasebtn.addActionListener(e -> {
                    shapeflag = 5;
        }
        );
        clearAllbtn.addActionListener(e -> {
            clearallflag=1;
            shapescopy=(ArrayList)shapeslist.clone();//copy arraylist
            clearAll clear = new clearAll(shapeslist);
            clear.clearPanel();
            repaint();
        }
        );
        fillckbox.addItemListener(e -> fill = e.getStateChange());
        dottedckbox.addItemListener(e -> dotted = e.getStateChange());
        this.add(linebtn);
        this.add(rectbtn);
        this.add(ovalbtn);
        this.add(undobtn);
        this.add(savebtn);
        this.add(openbtn);
        this.add(colors);
        this.add(freeHandDraw);
        this.add(erasebtn);
        this.add(clearAllbtn);
        this.add(fillckbox);
        this.add(dottedckbox);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("pressed");
                if(clearallflag==1){
                    clearallflag=0;  
                }
                x1 = e.getX();
                y1 = e.getY();
                line.setx1(x1);
                line.sety1(y1);
                rect.setx1(x1);
                rect.sety1(y1);
                oval.setx1(x1);
                oval.sety1(y1);
                line.setcolor(color);
                oval.setcolor(color);
                rect.setcolor(color);
                line.setdotted(dotted);
                rect.setdotted(dotted);
                oval.setdotted(dotted);
                freehand.setdotted(dotted);
                rect.setFill(fill);
                oval.setFill(fill);
                
                //reset freehand to avoid connecting lines on a new press
                freehand = new FreeHand(x1, y1, x1, y1, fill, dotted, color);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("released");
                x2 = e.getX();
                y2 = e.getY();
                Geoshape shape = switch (shapeflag) {
                    case 1 ->
                        new Line(x1, y1, x2, y2, dotted, color);
                    case 2 ->
                        new Rect(x1, y1, x2, y2, fill, dotted, color);
                    case 3 ->
                        new Oval(x1, y1, x2, y2, fill, dotted, color);
                    case 4 ->
                        freehand;  // Use the updated freehand shape
                    case 5 ->
                        new Erase(x1, y1, x2, y2, fill, dotted, color);
                    default ->
                        null;
                };
                if (shape != null) {
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

                System.out.println("dragged");
                x2 = e.getX();
                y2 =  e.getY();
                line.setx2(x2);
                line.sety2(y2);
                rect.setx2(x2);
                rect.sety2(y2);
                oval.setx2(x2);
                oval.sety2(y2);
                freehand.addPoint(x2, y2);
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
        //g.drawImage(image, 10, 10, this);

        if((clearallflag==1)&&(undoflag==1)){
            shapeslist=(ArrayList)shapescopy.clone();
            for (Geoshape shape : shapescopy) {
                shape.draw(g);
            }
            clearallflag=0;
        }
        for (Geoshape shape : shapeslist) {
            shape.draw(g);
        }
        System.out.println(undoflag);
        if (undoflag == 0&&clearallflag==0) {
            switch (shapeflag) {
                case 1 ->
                    line.draw(g);
                case 2 ->
                    rect.draw(g);
                case 3 ->
                    oval.draw(g);
            }
        }
        if(undoflag==1){
            undoflag=0;
        }
    }
}
