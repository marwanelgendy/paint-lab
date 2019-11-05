package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.jfree.fx.FXGraphics2D;
import java.awt.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class DDrawingEngine {

    int k = 0;
    String NameOfshape;

    int  counterH = 0, counterR = 0, counterS = 0, counterT = 0, counterL = 0, counterC = 0, counterE = 0, max = 0;
    ArrayList<LinkedList> total = new ArrayList<>() ;
    LinkedList<sshaPe> refresh ;
    ObservableList<String> menu = FXCollections.observableArrayList();
    Canvas canvas ;
    GraphicsContext brushTool ;


    private FXGraphics2D Hamada ;
    public void initialize()
    {
        Hamada = new FXGraphics2D(canvas.getGraphicsContext2D());
        Hamada.setStroke(new BasicStroke(4));
    }

    public DDrawingEngine() {
        canvas = new Canvas();
        initialize();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setBrushTool(GraphicsContext brushTool) {
        this.brushTool = brushTool;
    }

    public GraphicsContext getBrushTool() {
        return brushTool;
    }

    public void addtoTOtal(LinkedList<sshaPe> t)
    {
        this.total.add(t);
    }

    public void setRefresh(LinkedList<sshaPe> refresh) {
        this.refresh = refresh;
    }

    public LinkedList<sshaPe> getRefresh() {
        return refresh;
    }


    public int getCounterH() {
        return counterH;
    }



    public void setMax(int max) {
        this.max = max;
    }

    public ObservableList<String> getMenu() {
        return menu;
    }


    public void refresh(Graphics canvas)
    {
        brushTool.clearRect(0, 0, 770, 458);
        brushTool.setFill(Color.WHITE);
        brushTool.fillRect(0, 0, 770, 458);
        menu.clear();
        counterC = counterE = counterL = counterR = counterS = counterT = 1;
        for (int i = 0; i < refresh.size(); i++) {
            if (refresh.get(i).getClass() == LIne.class) {
                LIne l = (LIne) refresh.get(i);
                l.setBrushTool(brushTool);
                l.draw(Hamada);
                menu.add("L" + counterL);
                counterL++;
            } else if (refresh.get(i).getClass() == REctangle.class) {
                REctangle l = (REctangle) refresh.get(i);
                l.setBrushTool(brushTool);
                l.draw(Hamada);
                menu.add("R" + counterR);
                counterR++;
            } else if (refresh.get(i).getClass() == SQuare.class) {
                SQuare l = (SQuare) refresh.get(i);
                l.setBrushTool(brushTool);
                l.draw(Hamada);
                menu.add("S" + counterS);
                counterS++;
            } else if (refresh.get(i).getClass() == Triangle.class) {
                Triangle l = (Triangle) refresh.get(i);
                double Xx[] = {l.getX1(), l.getX2(), l.getX3()};
                double Yy[] = {l.getY1(), l.getY2(), l.getY3()};
                l.Xx=Xx;
                l.Yy=Yy;
                l.setBrushTool(brushTool);
                l.draw(Hamada);
                menu.add("T" + counterT);
                counterT++;
            } else if (refresh.get(i).getClass() == CiRcle.class) {
                CiRcle l = (CiRcle) refresh.get(i);
                l.setBrushTool(brushTool);
                l.draw(Hamada);
                menu.add("C" + counterC);
                counterC++;
            } else if (refresh.get(i).getClass() == ELlipse.class) {
                ELlipse l = (ELlipse) refresh.get(i);
                l.setBrushTool(brushTool);
                l.draw(Hamada);
                menu.add("E" + counterE);
                counterE++;
            }
        }
    }

    public void addShape(sshaPe shape)
    {
        if (max == counterH)
            max++;
        counterH++;
        LinkedList<sshaPe> h;
        h = (LinkedList<sshaPe>) refresh.clone();
        h.add(shape);
        total.add(counterH, h);
        refresh = total.get(counterH);
        refresh(Hamada);
    }

    public void removeShape(sshaPe shape)
    {
        k = getIndx(shape);
        if (max == counterH)
            max++;
        counterH++;
        LinkedList<sshaPe> h;
        h = (LinkedList<sshaPe>) refresh.clone();
        h.remove(k);
        total.add(counterH, h);
        refresh = total.get(counterH);
        refresh(Hamada);
    }

    public  void updateShape(sshaPe oldShape, sshaPe newShape)
    {
        k = getIndx(oldShape);
        if (max == counterH)
            max++;
        counterH++;
        LinkedList<sshaPe> h;
        h = (LinkedList<sshaPe>) refresh.clone();
        h.remove(k);
        h.add(k, newShape);
        total.add(counterH, h);
        refresh = total.get(counterH);
        refresh(Hamada);
    }


    public sshaPe[] getShapes()
    {
        sshaPe s[] = new sshaPe[refresh.size()];
        for (int i=0; i<refresh.size() ;i++)
        {
            s[i] = refresh.get(i);
        }
        return s;
    }


    public  void undo()
    {
        if (counterH == 0)
            return;
        counterH--;
        refresh = total.get(counterH);
        refresh(Hamada);
    }


    public void redo()
    {
        if (counterH == max)
            return;
        counterH++;
        refresh = total.get(counterH);
        refresh(Hamada);
    }

    public void setNameOfshape(String NameOfshape) {
        this.NameOfshape = NameOfshape;
    }

    public int getIndx(sshaPe s)
    {
        int cout1 = 0;
        int cout2 = Integer.parseInt(NameOfshape.substring(1));


        if (s.getClass() == ELlipse.class) {
            for (int i = 0; i < refresh.size(); i++) {
                if (refresh.get(i).getClass() == ELlipse.class)
                    cout1++;

                if (cout1 == cout2) {
                    return i;
                }
            }
        }

        if (s.getClass() == CiRcle.class) {
            for (int i = 0; i < refresh.size(); i++) {
                if (refresh.get(i).getClass() == CiRcle.class)
                    cout1++;

                if (cout1 == cout2) {
                    return i;
                }
            }
        }

        if (s.getClass() == REctangle.class) {
            for (int i = 0; i < refresh.size(); i++) {
                if (refresh.get(i).getClass() == REctangle.class)
                    cout1++;

                if (cout1 == cout2) {
                    return i;
                }
            }
        }

        if (s.getClass() == SQuare.class) {
            for (int i = 0; i < refresh.size(); i++) {
                if (refresh.get(i).getClass() == SQuare.class)
                    cout1++;

                if (cout1 == cout2) {
                    return i;
                }
            }
        }

        if (s.getClass() == LIne.class) {
            for (int i = 0; i < refresh.size(); i++) {
                if (refresh.get(i).getClass() == LIne.class)
                    cout1++;

                if (cout1 == cout2) {
                    return i;
                }
            }
        }

        if (s.getClass() == Triangle.class) {
            for (int i = 0; i < refresh.size(); i++) {
                if (refresh.get(i).getClass() == Triangle.class)
                    cout1++;

                if (cout1 == cout2) {
                    return i;
                }
            }
        }

        return 0;
    }


    public void save(String path) {
        if(isXML(path)){
            try {
                XMLEncoder e = new XMLEncoder(new FileOutputStream(new File(path)));
                e.writeObject(this.total);
                e.writeObject(this.max);
                e.writeObject(this.counterH);
                e.close();
            }catch (Exception e){

            }
        }
        else{
        }
    }
    public void load(String path) {
        if(isXML(path)){
            try {
                XMLDecoder e = new XMLDecoder(new FileInputStream(new File(path)));
                this.total = (ArrayList<LinkedList>) e.readObject();
                this.max = (int) e.readObject();
                this.counterH = (int) e.readObject();
                e.close();
                this.refresh = total.get(counterH);
                this.refresh(Hamada);
            }catch (Exception e){

            }
        }else{
        }
    }

    public boolean isXML (String path){
        if(path.charAt(path.length()-1)=='l'){
            return true ;
        }
        return false ;
    }
}