package sample;

public interface DrawingEngine {

    /* redraw all shapes on the canvas */
     void refresh(java.awt.Graphics canvas);

     void addShape(Shape shape);
     void removeShape(Shape shape);
     void updateShape(Shape oldShape, Shape newShape);

    /* return the created shapes objects */
    Shape[] getShapes();

    /* return the classes (types) of supported shapes already exist and the
     * ones that can be dynamically loaded at runtime (see Part 3) */
    java.util.List<Class<? extends Shape>> getSupportedShapes();

    /* add to the supported shapes the new shape class (see Part 3) */
    // public void installPluginShape(String jarPath);

    /* limited to 20 steps. Only consider in undo & redo
     * these actions: addShape, removeShape, updateShape */
     void undo();
     void redo();

    /* use the file extension to determine the type,
     * or throw runtime exception when unexpected extension */
     void save(String path);
     void load(String path);
}
