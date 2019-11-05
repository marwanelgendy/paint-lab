package sample;

public interface Shape{

    void setPosition(java.awt.Point position);
    java.awt.Point getPosition();

    // update shape specific properties (e.g., radius)
    void setProperties(java.util.Map<String, Double> properties);
    java.util.Map<String, Double> getProperties();

    void setColor(java.awt.Color color);
     java.awt.Color getColor();

     void setFillColor(java.awt.Color color);
     java.awt.Color getFillColor();

     void draw(java.awt.Graphics canvas); // redraw the shape on the canvas

     Object clone() throws CloneNotSupportedException; // create a deep clone of the shape
}
