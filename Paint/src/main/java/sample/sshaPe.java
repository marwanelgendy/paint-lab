package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import sun.security.util.SecurityConstants;

import java.awt.*;
import java.util.Map;

public class sshaPe  implements Shape {
    protected double x1;
    protected double y1;
    protected double x2;
    protected double y2;
    protected double w;
    protected double h;
    protected double size;

    Map prop;

    GraphicsContext brushTool;


    /** For Swing **/
    Point c ;
    Color co ;
    Color fi;

    public void setStartX(double x1) {
        this.x1 = x1;
    }

    public void setStartY(double y1) {
        this.y1 = y1;
    }

    public void setEndX(double x2 ) {
        this.x2 = x2;
    }

    public void setEndY(double y2 ) {
        this.y2 = y2;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }


    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setPosition(Point position)
    {
        c = position;
    }

    public Point getPosition()
    {
        return  c;
    }

    public void setColor(Color color)
    {
        this.co = color;
    }

    public java.awt.Color getColor()
    {
        return co;
    }

    public void setFillColor(java.awt.Color color)
    {
        this.fi = color;
    }

    public java.awt.Color getFillColor()
    {
        return fi;
    }

    public void setProperties(Map<String, Double> properties)
    {
        this.prop = properties;
    }

    public Map<String, Double> getProperties()
    {
        return prop;
    }

    public void draw(java.awt.Graphics canvas)
    {

    }

   public Object clone() throws CloneNotSupportedException
   {
       sshaPe s = new sshaPe();
       return s ;
   }

    public Color FXtoAWTColor(javafx.scene.paint.Color fx)
    {
        return new Color((float)fx.getRed(),(float)fx.getGreen(),(float)fx.getBlue(),(float)fx.getOpacity());
    }

    public javafx.scene.paint.Color AWTtoFX(Color c)
    {
        return javafx.scene.paint.Color.rgb(c.getRed(),c.getGreen(),c.getBlue(),c.getAlpha()/255.0);
    }

    public void setBrushTool(GraphicsContext brushTool) {
        this.brushTool = brushTool;
    }


}

class LIne extends sshaPe
{
    public  LIne()
    {}
    public LIne(double s1 ,double s2 ,double e1,double e2)
    {
        setStartX(s1);
        setStartY(s2);
        setEndX(e1);
        setEndY(e2);
    }

    @Override
    public void draw(java.awt.Graphics canvas)
    {
        brushTool.setLineWidth(getSize());
        brushTool.setStroke(AWTtoFX(getColor()));
        brushTool.strokeLine(getX1(), getY1(), getX2(), getY2());
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        LIne x = new LIne(getX1(),getY1(),getX2(),getX2());
        x.setSize(getSize());
        x.setColor(x.getColor());
        x.setProperties(x.getProperties());
        return x;
    }

    @Override
    public void setFillColor(java.awt.Color color)
    {
        this.fi = null;
    }
}

class REctangle extends sshaPe
{
    public  REctangle()
    {}
    public REctangle(double s1 ,double s2 ,double e1,double e2)
    {
        setStartX(s1);
        setStartY(s2);
        setW( Math.abs(s1-e1) );
        setH( Math.abs(s2-e2) );
        setEndX(e1);
        setEndY(e2);
    }

    @Override
    public void draw(java.awt.Graphics canvas)
    {
        brushTool.setLineWidth(getSize());
        if (getFillColor() != null) {
            brushTool.setFill(AWTtoFX(getFillColor()));
            brushTool.fillRect(getX1(), getY1(), getW(), getH());
        }
        brushTool.setStroke(AWTtoFX(getColor()));
        brushTool.strokeRect(getX1(), getY1(), getW(), getH());
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        REctangle x = new REctangle(getX1(),getY1(),getX2(),getX2());
        x.setSize(getSize());
        x.setColor(x.getColor());
        x.setFillColor(x.getFillColor());
        x.setProperties(x.getProperties());
        return x;
    }
}

class SQuare extends sshaPe
{
    public  SQuare()
    {}
    public SQuare(double s1 ,double s2 ,double e1,double e2)
    {
        setStartX(s1);
        setStartY(s2);
        setW( ( Math.abs(s1-e1) + Math.abs(s2-e2) ) / 2 );
        setH( ( Math.abs(s1-e1) + Math.abs(s2-e2) ) / 2 );
        setEndX(e1);
        setEndY(e2);
    }

    @Override
    public void draw(java.awt.Graphics canvas)
    {
        brushTool.setLineWidth(getSize());
        if (getFillColor() != null) {
            brushTool.setFill(AWTtoFX(getFillColor()));
            brushTool.fillRect(getX1(), getY1(), getW(), getH());
        }
        brushTool.setStroke(AWTtoFX(getColor()));
        brushTool.strokeRect(getX1(), getY1(), getW(), getH());
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        SQuare x = new SQuare(getX1(),getY1(),getX2(),getX2());
        x.setSize(getSize());
        x.setColor(x.getColor());
        x.setFillColor(x.getFillColor());
        x.setProperties(x.getProperties());
        return x;
    }
}

class Triangle extends sshaPe
{
    protected double x3;
    protected double y3;
    protected double Xx[];
    protected double Yy[];

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public double getY3() {
        return y3;
    }

    public void setY3(double y3) {
        this.y3 = y3;
    }

    public  Triangle()
    {}
    public Triangle(double s1 ,double s2 ,double e1,double e2,double z1,double z2)
    {
        setStartX(s1);
        setStartY(s2);
        setEndX(e1);
        setEndY(e2);
        setX3(z1);
        setY3(z2);
    }

    @Override
    public void draw(java.awt.Graphics canvas)
    {
        brushTool.setLineWidth(getSize());
        if (getFillColor() != null) {
            brushTool.setFill(AWTtoFX(getFillColor()));
            brushTool.fillPolygon(Xx, Yy, 3);
        }
        brushTool.setStroke(AWTtoFX(getColor()));
        brushTool.strokePolygon(Xx, Yy, 3);
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Triangle x = new Triangle(getX1(),getY1(),getX2(),getX2(),getX3(),getY3());
        x.setSize(getSize());
        x.setColor(x.getColor());
        x.setFillColor(x.getFillColor());
        x.setProperties(x.getProperties());
        return x;
    }

}


class CiRcle extends sshaPe
{
    public  CiRcle()
    {}
    public CiRcle(double s1 ,double s2 ,double e1,double e2)
    {
        setStartX(s1);
        setStartY(s2);
        setW( ( Math.abs(s1-e1) + Math.abs(s2-e2) ) / 2 );
        setH( ( Math.abs(s1-e1) + Math.abs(s2-e2) ) / 2 );
        setEndX(e1);
        setEndY(e2);
    }

    @Override
    public void draw(java.awt.Graphics canvas)
    {
        brushTool.setLineWidth(getSize());
        if (getFillColor() != null) {
            brushTool.setFill(AWTtoFX(getFillColor()));
            brushTool.fillOval(getX1(), getY1(), getW(), getH());
        }
        brushTool.setStroke(AWTtoFX(getColor()));
        brushTool.strokeOval(getX1(), getY1(), getW(), getH());
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        CiRcle x = new CiRcle(getX1(),getY1(),getX2(),getX2());
        x.setSize(getSize());
        x.setColor(x.getColor());
        x.setFillColor(x.getFillColor());
        x.setProperties(x.getProperties());
        return x;
    }
}

class ELlipse extends sshaPe
{
    public  ELlipse()
    {}
    public ELlipse(double s1 ,double s2 ,double e1,double e2)
    {
        setStartX(s1);
        setStartY(s2);
        setW(Math.abs(s1-e1));
        setH(Math.abs(s2-e2));
        setEndX(e1);
        setEndY(e2);
    }

    @Override
    public void draw(java.awt.Graphics canvas)
    {
        brushTool.setLineWidth(getSize());
        if (getFillColor() != null) {
            brushTool.setFill(AWTtoFX(getFillColor()));
            brushTool.fillOval(getX1(), getY1(), getW(), getH());
        }
        brushTool.setStroke(AWTtoFX(getColor()));
        brushTool.strokeOval(getX1(), getY1(), getW(), getH());
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        ELlipse x = new ELlipse(getX1(),getY1(),getX2(),getX2());
        x.setSize(getSize());
        x.setColor(x.getColor());
        x.setFillColor(x.getFillColor());
        x.setProperties(x.getProperties());
        return x;
    }
}
