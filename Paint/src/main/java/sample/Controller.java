package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    boolean first = true;
    boolean isDrawn = false;
    boolean toEdit = false;
    boolean toDraw = false;
    boolean whileEditing = false;

    int k = 0;
    String x;
    Map<String,Double> prop ;

    double topleftx = 0, toplefty = 0, width = 0, height = 0;
    double x1 = 0;
    double y1 = 0;
    double x2 = 0;
    double y2 = 0, x3 = 0, y3 = 0, x4 = 0, y4 = 0;

    int cnt = 1, counter = -1;
    ObservableList<String> go = FXCollections.observableArrayList();
    FileChooser sayed ;

    @FXML
    ColorPicker ayman;
    @FXML
    ChoiceBox<String> shapes = new ChoiceBox();
    @FXML
    ColorPicker ahmed;
    @FXML
    ChoiceBox<String> mido = new ChoiceBox();


    DDrawingEngine mohsen ;
    @FXML
    Canvas canvas ;


    @FXML
    public void HandleButton(ActionEvent event) {
        LIne line = new LIne();
        REctangle rect = new REctangle();
        Triangle tri = new Triangle();
        SQuare squ = new SQuare();
        CiRcle circ = new CiRcle();
        ELlipse elps = new ELlipse();
        mohsen.setBrushTool(mohsen.getCanvas().getGraphicsContext2D());
        x = ((Button) event.getSource()).getText();
        if (x.equals("ellipse")) {
            prop = new Map<String, Double>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean containsKey(Object key) {
                    return false;
                }

                @Override
                public boolean containsValue(Object value) {
                    return false;
                }

                @Override
                public Double get(Object key) {
                    return null;
                }

                @Override
                public Double put(String key, Double value) {
                    return null;
                }

                @Override
                public Double remove(Object key) {
                    return null;
                }

                @Override
                public void putAll(Map<? extends String, ? extends Double> m) {

                }

                @Override
                public void clear() {

                }

                @Override
                public Set<String> keySet() {
                    return null;
                }

                @Override
                public Collection<Double> values() {
                    return null;
                }

                @Override
                public Set<Entry<String, Double>> entrySet() {
                    return null;
                }
            };
            whileEditing = false;
            toEdit = false;
            toDraw = true;
            first = true;
            canvas.setOnMouseClicked(e -> {
                if (first && toDraw) {
                    mohsen.setMax(mohsen.getCounterH());
                    elps.setStartX(e.getX());
                    elps.setStartY(e.getY());
                    first = false;
                } else if (!first && toDraw) {
                    elps.setEndX(e.getX());
                    elps.setEndY(e.getY());
                    elps.setW(Math.abs(elps.getX1() - elps.getX2()));
                    elps.setH(Math.abs(elps.getY1() - elps.getY2()));
                    if (elps.getX1() > elps.getX2()) {
                        double temp = elps.getX1();
                        elps.setStartX(elps.getX2());
                        elps.setEndX(temp);
                    }
                    if (elps.getY1() > elps.getY2()) {
                        double temp = elps.getY1();
                        elps.setStartY(elps.getY2());
                        elps.setEndY(temp);
                    }

                    double size = Double.parseDouble(mido.getValue());
                    mohsen.getBrushTool().setLineWidth(size);
                    mohsen.getBrushTool().setStroke(ahmed.getValue());
                    ELlipse x = new ELlipse(elps.getX1(), elps.getY1(), elps.getX2(), elps.getY2());
                    x.setSize(mohsen.getBrushTool().getLineWidth());
                    x.setColor(x.FXtoAWTColor((Color) mohsen.getBrushTool().getStroke()));
                    x.setFillColor(null);
                    x.setPosition(new Point((int)x.getX1() , (int)x.getY1()));
                    prop.put("Width",x.getW());
                    prop.put("Hight",x.getH());
                    x.setProperties(prop);
                    mohsen.addShape(x);
                    first = true;
                    isDrawn = true;
                }
            });
        } else if (x.equals("circle")) {
            prop = new Map<String, Double>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean containsKey(Object key) {
                    return false;
                }

                @Override
                public boolean containsValue(Object value) {
                    return false;
                }

                @Override
                public Double get(Object key) {
                    return null;
                }

                @Override
                public Double put(String key, Double value) {
                    return null;
                }

                @Override
                public Double remove(Object key) {
                    return null;
                }

                @Override
                public void putAll(Map<? extends String, ? extends Double> m) {

                }

                @Override
                public void clear() {

                }

                @Override
                public Set<String> keySet() {
                    return null;
                }

                @Override
                public Collection<Double> values() {
                    return null;
                }

                @Override
                public Set<Entry<String, Double>> entrySet() {
                    return null;
                }
            };
            whileEditing = false;
            first = true;
            toEdit = false;
            toDraw = true;
            mohsen.getCanvas().setOnMouseClicked(e -> {
                if (first && toDraw) {
                    mohsen.setMax(mohsen.getCounterH());
                    circ.setStartX(e.getX());
                    circ.setStartY(e.getY());
                    first = false;
                } else if (!first && toDraw) {
                    circ.setEndX(e.getX());
                    circ.setEndY(e.getY());
                    circ.setW((Math.abs(circ.getX1() - circ.getX2()) + Math.abs(circ.getY1() - circ.getY2())) / 2);
                    circ.setH((Math.abs(circ.getX1() - circ.getX2()) + Math.abs(circ.getY1() - circ.getY2())) / 2);
                    if (circ.getX1() > circ.getX2()) {
                        double temp = circ.getX1();
                        circ.setStartX(circ.getX2());
                        circ.setEndX(temp);
                    }
                    if (circ.getY1() > circ.getY2()) {
                        double temp = circ.getY1();
                        circ.setStartY(circ.getY2());
                        circ.setEndY(temp);
                    }
                    double size = Double.parseDouble(mido.getValue());
                    mohsen.getBrushTool().setLineWidth(size);
                    mohsen.getBrushTool().setStroke(ahmed.getValue());
                    CiRcle x = new CiRcle(circ.getX1(), circ.getY1(), circ.getX2(), circ.getY2());
                    x.setSize(mohsen.getBrushTool().getLineWidth());
                    x.setColor(x.FXtoAWTColor((Color) mohsen.getBrushTool().getStroke()));
                    x.setFillColor(null);
                    x.setPosition(new Point((int)x.getX1() , (int)x.getY1()));
                    prop.put("radius",x.getH()/2);
                    x.setProperties(prop);
                    mohsen.addShape(x);
                    isDrawn = true;
                    first = true;
                }
            });
        } else if (x.equals("rectangle")) {
            prop = new Map<String, Double>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean containsKey(Object key) {
                    return false;
                }

                @Override
                public boolean containsValue(Object value) {
                    return false;
                }

                @Override
                public Double get(Object key) {
                    return null;
                }

                @Override
                public Double put(String key, Double value) {
                    return null;
                }

                @Override
                public Double remove(Object key) {
                    return null;
                }

                @Override
                public void putAll(Map<? extends String, ? extends Double> m) {

                }

                @Override
                public void clear() {

                }

                @Override
                public Set<String> keySet() {
                    return null;
                }

                @Override
                public Collection<Double> values() {
                    return null;
                }

                @Override
                public Set<Entry<String, Double>> entrySet() {
                    return null;
                }
            };
            whileEditing = false;
            first = true;
            toEdit = false;
            toDraw = true;
            mohsen.getCanvas().setOnMouseClicked(e -> {
                if (first && toDraw) {
                    mohsen.setMax(mohsen.getCounterH());
                    rect.setStartX(e.getX());
                    rect.setStartY(e.getY());
                    first = false;
                } else if (!first && toDraw) {
                    rect.setEndX(e.getX());
                    rect.setEndY(e.getY());
                    rect.setW(Math.abs(rect.getX1() - rect.getX2()));
                    rect.setH(Math.abs(rect.getY1() - rect.getY2()));
                    double size = Double.parseDouble(mido.getValue());

                    if (rect.getX1() > rect.getX2()) {
                        double temp = rect.getX1();
                        rect.setStartX(rect.getX2());
                        rect.setEndX(temp);
                    }
                    if (rect.getY1() > rect.getY2()) {
                        double temp = rect.getY1();
                        rect.setStartY(rect.getY2());
                        rect.setEndY(temp);
                    }
                    mohsen.getBrushTool().setLineWidth(size);
                    mohsen.getBrushTool().setStroke(ahmed.getValue());
                    REctangle x = new REctangle(rect.getX1(), rect.getY1(), rect.getX2(), rect.getY2());
                    x.setSize(mohsen.getBrushTool().getLineWidth());
                    x.setColor(x.FXtoAWTColor((Color) mohsen.getBrushTool().getStroke()));
                    x.setFillColor(null);
                    x.setPosition(new Point((int)x.getX1() , (int)x.getY1()));
                    prop.put("Width",x.getW());
                    prop.put("Hight",x.getH());
                    x.setProperties(prop);
                    mohsen.addShape(x);
                    first = true;
                    isDrawn = true;
                }
            });
        } else if (x.equals("square")) {
            prop = new Map<String, Double>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean containsKey(Object key) {
                    return false;
                }

                @Override
                public boolean containsValue(Object value) {
                    return false;
                }

                @Override
                public Double get(Object key) {
                    return null;
                }

                @Override
                public Double put(String key, Double value) {
                    return null;
                }

                @Override
                public Double remove(Object key) {
                    return null;
                }

                @Override
                public void putAll(Map<? extends String, ? extends Double> m) {

                }

                @Override
                public void clear() {

                }

                @Override
                public Set<String> keySet() {
                    return null;
                }

                @Override
                public Collection<Double> values() {
                    return null;
                }

                @Override
                public Set<Entry<String, Double>> entrySet() {
                    return null;
                }
            };
            whileEditing = false;
            first = true;
            toEdit = false;
            toDraw = true;
            mohsen.getCanvas().setOnMouseClicked(e -> {
                if (first && toDraw) {
                    mohsen.setMax(mohsen.getCounterH());
                    squ.setStartX(e.getX());
                    squ.setStartY(e.getY());
                    first = false;
                } else if (!first && toDraw) {
                    squ.setEndX(e.getX());
                    squ.setEndY(e.getY());
                    double size = Double.parseDouble(mido.getValue());

                    if (squ.getX1() > squ.getX2()) {
                        double temp = squ.getX1();
                        squ.setStartX(squ.getX2());
                        squ.setEndX(temp);
                    }
                    if (squ.getY1() > squ.getY2()) {
                        double temp = squ.getY1();
                        squ.setStartY(squ.getY2());
                        squ.setEndY(temp);
                    }

                    squ.setW((Math.abs(squ.getX1() - squ.getX2()) + Math.abs(squ.getY1() - squ.getY2())) / 2);
                    squ.setH((Math.abs(squ.getX1() - squ.getX2()) + Math.abs(squ.getY1() - squ.getY2())) / 2);
                    mohsen.getBrushTool().setLineWidth(size);
                    mohsen.getBrushTool().setStroke(ahmed.getValue());
                    SQuare x = new SQuare(squ.getX1(), squ.getY1(), squ.getX2(), squ.getY2());
                    x.setSize(mohsen.getBrushTool().getLineWidth());
                    x.setColor(x.FXtoAWTColor((Color) mohsen.getBrushTool().getStroke()));
                    x.setFillColor(null);
                    x.setPosition(new Point((int)x.getX1() , (int)x.getY1()));
                    prop.put("Length",x.getW());
                    x.setProperties(prop);
                    mohsen.addShape(x);
                    isDrawn = true;
                    first = true;
                }
            });
        } else if (x.equals("line")) {
            prop = new Map<String, Double>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean containsKey(Object key) {
                    return false;
                }

                @Override
                public boolean containsValue(Object value) {
                    return false;
                }

                @Override
                public Double get(Object key) {
                    return null;
                }

                @Override
                public Double put(String key, Double value) {
                    return null;
                }

                @Override
                public Double remove(Object key) {
                    return null;
                }

                @Override
                public void putAll(Map<? extends String, ? extends Double> m) {

                }

                @Override
                public void clear() {

                }

                @Override
                public Set<String> keySet() {
                    return null;
                }

                @Override
                public Collection<Double> values() {
                    return null;
                }

                @Override
                public Set<Entry<String, Double>> entrySet() {
                    return null;
                }
            };
            whileEditing = false;
            first = true;
            toEdit = false;
            toDraw = true;
            mohsen.getCanvas().setOnMouseClicked(e -> {
                if (first && toDraw) {
                    mohsen.setMax(mohsen.getCounterH());
                    line.setStartX(e.getX());
                    line.setStartY(e.getY());
                    first = false;
                } else if (!first && toDraw) {
                    double size = Double.parseDouble(mido.getValue());
                    line.setEndX(e.getX());
                    line.setEndY(e.getY());
                    mohsen.getBrushTool().setLineWidth(size);
                    mohsen.getBrushTool().setStroke(ahmed.getValue());
                    LIne x = new LIne(line.getX1(), line.getY1(), line.getX2(), line.getY2());
                    x.setSize(mohsen.getBrushTool().getLineWidth());
                    x.setColor(x.FXtoAWTColor((Color) mohsen.getBrushTool().getStroke()));
                    x.setPosition(new Point((int)x.getX1() , (int)x.getY1()));
                    prop.put("Length",distance(x.getX1(), line.getY1(),x.getX2(),x.getY2()));
                    x.setProperties(prop);
                    mohsen.addShape(x);
                    isDrawn = true;
                    first = true;
                }
            });
        } else if (x.equals("triangle")) {
            prop = new Map<String, Double>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean containsKey(Object key) {
                    return false;
                }

                @Override
                public boolean containsValue(Object value) {
                    return false;
                }

                @Override
                public Double get(Object key) {
                    return null;
                }

                @Override
                public Double put(String key, Double value) {
                    return null;
                }

                @Override
                public Double remove(Object key) {
                    return null;
                }

                @Override
                public void putAll(Map<? extends String, ? extends Double> m) {

                }

                @Override
                public void clear() {

                }

                @Override
                public Set<String> keySet() {
                    return null;
                }

                @Override
                public Collection<Double> values() {
                    return null;
                }

                @Override
                public Set<Entry<String, Double>> entrySet() {
                    return null;
                }
            };
            whileEditing = false;
            toDraw = true;
            toEdit = false;
            cnt = 1;
            mohsen.getCanvas().setOnMouseClicked(e -> {
                if (cnt == 1 && toDraw) {
                    mohsen.setMax(mohsen.getCounterH());
                    tri.setStartX(e.getX());
                    tri.setStartY(e.getY());
                    cnt++;
                } else if (cnt == 2 && toDraw) {

                    tri.setEndX(e.getX());
                    tri.setEndY(e.getY());
                    cnt++;
                } else if (cnt == 3 && toDraw) {
                    tri.setX3(e.getX());
                    tri.setY3(e.getY());
                    double size = Double.parseDouble(mido.getValue());
                    Triangle x = new Triangle(tri.getX1(), tri.getY1(), tri.getX2(), tri.getY2(), tri.getX3(), tri.getY3());
                    x.setSize(size);
                    x.setColor(x.FXtoAWTColor((Color) mohsen.getBrushTool().getStroke()));
                    x.setFillColor(null);
                    x.setPosition(new Point((int)x.getX1() , (int)x.getY1()));
                    prop.put("X1",x.getX1());
                    prop.put("X2",x.getX2());
                    prop.put("X3",x.getX3());
                    prop.put("Y1",x.getY1());
                    prop.put("Y2",x.getY2());
                    prop.put("Y3",x.getY3());
                    x.setProperties(prop);
                    mohsen.addShape(x);
                    cnt = 1;
                }

            });
        }
    }

    public void HandleButton2(ActionEvent event) {
        toDraw = false;
        whileEditing = false;
        toEdit = false;
        String x = ((Button) event.getSource()).getText();
        if (x.equals("undo")) {
            toDraw = false;
            mohsen.undo();
        } else if (x.equals("redo")) {
            toDraw = false;
            mohsen.redo();
        }
    }

    @FXML
    public void HandleButton3(ActionEvent event) {

        toDraw = false;
        whileEditing = false;
        toEdit = false;
        mohsen.setBrushTool(mohsen.getCanvas().getGraphicsContext2D());
        x = ((Button) event.getSource()).getText();
        if (shapes.getValue() == null) {
            return;
        }
        String temp = shapes.getValue();
        int cout1 = 0;
        int cout2 = Integer.parseInt(temp.substring(1));
        if (x.equals("move&resize")) {
            if (temp.charAt(0) == 'E' || temp.charAt(0) == 'C' || temp.charAt(0) == 'R' || temp.charAt(0) == 'S') {
                if (temp.charAt(0) == 'E') {
                    for (k = 0; k < mohsen.getRefresh().size(); k++) {
                        if (mohsen.getRefresh().get(k).getClass() == ELlipse.class) {
                            cout1++;
                        }
                        if (cout1 == cout2) {
                            topleftx = mohsen.getRefresh().get(k).getX1();
                            toplefty = mohsen.getRefresh().get(k).getY1();
                            x3 = mohsen.getRefresh().get(k).getX2();
                            y3 = mohsen.getRefresh().get(k).getY2();
                            width = mohsen.getRefresh().get(k).getW();
                            height = mohsen.getRefresh().get(k).getH();
                            break;
                        }
                    }
                } else if (temp.charAt(0) == 'R') {
                    for (k = 0; k < mohsen.getRefresh().size(); k++) {
                        if (mohsen.getRefresh().get(k).getClass() == REctangle.class) {
                            cout1++;
                        }
                        if (cout1 == cout2) {
                            topleftx = mohsen.getRefresh().get(k).getX1();
                            toplefty = mohsen.getRefresh().get(k).getY1();
                            x3 = mohsen.getRefresh().get(k).getX2();
                            y3 = mohsen.getRefresh().get(k).getY2();
                            width = mohsen.getRefresh().get(k).getW();
                            height = mohsen.getRefresh().get(k).getH();
                            break;
                        }
                    }
                } else if (temp.charAt(0) == 'S') {
                    for (k = 0; k < mohsen.getRefresh().size(); k++) {
                        if (mohsen.getRefresh().get(k).getClass() == SQuare.class) {
                            cout1++;
                        }
                        if (cout1 == cout2) {
                            topleftx = mohsen.getRefresh().get(k).getX1();
                            toplefty = mohsen.getRefresh().get(k).getY1();
                            x3 = mohsen.getRefresh().get(k).getX2();
                            y3 = mohsen.getRefresh().get(k).getY2();
                            width = mohsen.getRefresh().get(k).getW();
                            height = mohsen.getRefresh().get(k).getH();
                            break;
                        }
                    }
                } else if (temp.charAt(0) == 'C') {
                    for (k = 0; k < mohsen.getRefresh().size(); k++) {
                        if (mohsen.getRefresh().get(k).getClass() == CiRcle.class) {
                            cout1++;
                        }
                        if (cout1 == cout2) {
                            topleftx = mohsen.getRefresh().get(k).getX1();
                            toplefty = mohsen.getRefresh().get(k).getY1();
                            x3 = mohsen.getRefresh().get(k).getX2();
                            y3 = mohsen.getRefresh().get(k).getY2();
                            width = mohsen.getRefresh().get(k).getW();
                            height = mohsen.getRefresh().get(k).getH();
                            break;
                        }
                    }
                }
                toEdit = true;
                mohsen.getCanvas().setOnMouseMoved(e -> {
                    if (toEdit) {
                        if ((e.getX() > topleftx + 5 && e.getX() < topleftx + width - 5) && (e.getY() < toplefty + 15 && e.getY() > toplefty - 15)) {
                            mohsen.getCanvas().setCursor(Cursor.N_RESIZE);
                        } else if ((e.getX() < topleftx + 15 && e.getX() > topleftx - 15) && (e.getY() < toplefty + height - 5 && e.getY() > toplefty + 5)) {
                            mohsen.getCanvas().setCursor(Cursor.W_RESIZE);
                        } else if ((e.getX() > topleftx + width - 15 && e.getX() < topleftx + width + 15) && (e.getY() < toplefty + height - 5 && e.getY() > toplefty + 15)) {
                            mohsen.getCanvas().setCursor(Cursor.E_RESIZE);
                        } else if ((e.getX() > topleftx + 5 && e.getX() < topleftx + width - 5) && (e.getY() < toplefty + height + 15 && e.getY() > toplefty + height - 15)) {
                            mohsen.getCanvas().setCursor(Cursor.S_RESIZE);
                        } else if ((e.getX() > topleftx && e.getX() < topleftx + width) && (e.getY() > toplefty && e.getY() < toplefty + height)) {
                            mohsen.getCanvas().setCursor(Cursor.MOVE);
                        } else {
                            mohsen.getCanvas().setCursor(Cursor.DEFAULT);
                        }
                    } else {
                        mohsen.getCanvas().setCursor(Cursor.DEFAULT);
                    }
                });
                mohsen.getCanvas().setOnMousePressed(e -> {
                    x4 = e.getX();
                    y4 = e.getY();
                    check();
                });
                mohsen.getCanvas().setOnMouseReleased(e -> {
                    if (whileEditing) {
                        if (counter == 0) {
                            if (e.getX() - topleftx < 9) {
                                width = 9;
                            } else {
                                width = e.getX() - topleftx;
                                x3=e.getX();
                            }
                        } else if (counter == 1) {
                            if ((topleftx + width) - e.getX() < 9) {
                                topleftx = (topleftx + width - 9);
                                width = 9;
                            } else {
                                width = (topleftx + width - e.getX());
                                topleftx = e.getX();
                            }
                        } else if (counter == 2) {
                            if (toplefty + height - e.getY() < 9) {
                                toplefty = (toplefty + height - 9);
                                height = 9;
                            } else {
                                height = toplefty + height - e.getY();
                                toplefty = e.getY();
                            }
                        } else if (counter == 3) {
                            if (e.getY() - toplefty < 9) {
                                height = 9;
                            } else {
                                height = e.getY() - toplefty;
                                y3=e.getY();
                            }
                        } else if (counter == 4) {
                            topleftx = topleftx + e.getX() - x4;
                            toplefty = toplefty + e.getY() - y4;
                            x3 = x3 + e.getX() -x4;
                            y3 = y3 + e.getY() -y4;
                        }
                        if (temp.charAt(0) == 'E') {
                            prop = new Map<String, Double>() {
                                @Override
                                public int size() {
                                    return 0;
                                }

                                @Override
                                public boolean isEmpty() {
                                    return false;
                                }

                                @Override
                                public boolean containsKey(Object key) {
                                    return false;
                                }

                                @Override
                                public boolean containsValue(Object value) {
                                    return false;
                                }

                                @Override
                                public Double get(Object key) {
                                    return null;
                                }

                                @Override
                                public Double put(String key, Double value) {
                                    return null;
                                }

                                @Override
                                public Double remove(Object key) {
                                    return null;
                                }

                                @Override
                                public void putAll(Map<? extends String, ? extends Double> m) {

                                }

                                @Override
                                public void clear() {

                                }

                                @Override
                                public Set<String> keySet() {
                                    return null;
                                }

                                @Override
                                public Collection<Double> values() {
                                    return null;
                                }

                                @Override
                                public Set<Entry<String, Double>> entrySet() {
                                    return null;
                                }
                            };
                            ELlipse l = new ELlipse();
                            l.setStartX(topleftx);
                            l.setStartY(toplefty);
                            l.setEndX(x3);
                            l.setEndY(y3);
                            l.setW(width);
                            l.setH(height);
                            l.setSize(mohsen.getRefresh().get(k).size);
                            l.setPosition(new Point((int)l.getX1() , (int)l.getY1()));
                            l.setColor(mohsen.getRefresh().get(k).getColor());
                            if(mohsen.getRefresh().get(k).getFillColor()!=null)
                                l.setFillColor(mohsen.getRefresh().get(k).getFillColor());
                            prop.put("Width",l.getW());
                            prop.put("Hight",l.getH());
                            l.setProperties(prop);
                            mohsen.setNameOfshape(temp);
                            mohsen.updateShape(mohsen.getRefresh().get(k),l);
                        } else if (temp.charAt(0) == 'R') {
                            prop = new Map<String, Double>() {
                                @Override
                                public int size() {
                                    return 0;
                                }

                                @Override
                                public boolean isEmpty() {
                                    return false;
                                }

                                @Override
                                public boolean containsKey(Object key) {
                                    return false;
                                }

                                @Override
                                public boolean containsValue(Object value) {
                                    return false;
                                }

                                @Override
                                public Double get(Object key) {
                                    return null;
                                }

                                @Override
                                public Double put(String key, Double value) {
                                    return null;
                                }

                                @Override
                                public Double remove(Object key) {
                                    return null;
                                }

                                @Override
                                public void putAll(Map<? extends String, ? extends Double> m) {

                                }

                                @Override
                                public void clear() {

                                }

                                @Override
                                public Set<String> keySet() {
                                    return null;
                                }

                                @Override
                                public Collection<Double> values() {
                                    return null;
                                }

                                @Override
                                public Set<Entry<String, Double>> entrySet() {
                                    return null;
                                }
                            };
                            REctangle l = new REctangle();
                            l.setStartX(topleftx);
                            l.setStartY(toplefty);
                            l.setEndX(x3);
                            l.setEndY(y3);
                            l.setW(width);
                            l.setH(height);
                            l.setSize(mohsen.getRefresh().get(k).size);
                            l.setPosition(new Point((int)l.getX1() , (int)l.getY1()));
                            l.setColor(mohsen.getRefresh().get(k).getColor());
                            if(mohsen.getRefresh().get(k).getFillColor()!=null)
                                l.setFillColor(mohsen.getRefresh().get(k).getFillColor());
                            prop.put("Width",l.getW());
                            prop.put("Hight",l.getH());
                            l.setProperties(prop);
                            mohsen.setNameOfshape(temp);
                            mohsen.updateShape(mohsen.getRefresh().get(k),l);
                        } else if (temp.charAt(0) == 'S') {
                            prop = new Map<String, Double>() {
                                @Override
                                public int size() {
                                    return 0;
                                }

                                @Override
                                public boolean isEmpty() {
                                    return false;
                                }

                                @Override
                                public boolean containsKey(Object key) {
                                    return false;
                                }

                                @Override
                                public boolean containsValue(Object value) {
                                    return false;
                                }

                                @Override
                                public Double get(Object key) {
                                    return null;
                                }

                                @Override
                                public Double put(String key, Double value) {
                                    return null;
                                }

                                @Override
                                public Double remove(Object key) {
                                    return null;
                                }

                                @Override
                                public void putAll(Map<? extends String, ? extends Double> m) {

                                }

                                @Override
                                public void clear() {

                                }

                                @Override
                                public Set<String> keySet() {
                                    return null;
                                }

                                @Override
                                public Collection<Double> values() {
                                    return null;
                                }

                                @Override
                                public Set<Entry<String, Double>> entrySet() {
                                    return null;
                                }
                            };
                            SQuare l = new SQuare();
                            l.setStartX(topleftx);
                            l.setStartY(toplefty);
                            l.setEndX(x3);
                            l.setEndY(y3);
                            l.setW(width);
                            l.setH(height);
                            l.setSize(mohsen.getRefresh().get(k).size);
                            l.setPosition(new Point((int)l.getX1() , (int)l.getY1()));
                            l.setColor(mohsen.getRefresh().get(k).getColor());
                            if(mohsen.getRefresh().get(k).getFillColor()!=null)
                                l.setFillColor(mohsen.getRefresh().get(k).getFillColor());
                            prop.put("Length",l.getH());
                            l.setProperties(prop);
                            mohsen.setNameOfshape(temp);
                            mohsen.updateShape(mohsen.getRefresh().get(k),l);
                        } else if (temp.charAt(0) == 'C') {
                            prop = new Map<String, Double>() {
                                @Override
                                public int size() {
                                    return 0;
                                }

                                @Override
                                public boolean isEmpty() {
                                    return false;
                                }

                                @Override
                                public boolean containsKey(Object key) {
                                    return false;
                                }

                                @Override
                                public boolean containsValue(Object value) {
                                    return false;
                                }

                                @Override
                                public Double get(Object key) {
                                    return null;
                                }

                                @Override
                                public Double put(String key, Double value) {
                                    return null;
                                }

                                @Override
                                public Double remove(Object key) {
                                    return null;
                                }

                                @Override
                                public void putAll(Map<? extends String, ? extends Double> m) {

                                }

                                @Override
                                public void clear() {

                                }

                                @Override
                                public Set<String> keySet() {
                                    return null;
                                }

                                @Override
                                public Collection<Double> values() {
                                    return null;
                                }

                                @Override
                                public Set<Entry<String, Double>> entrySet() {
                                    return null;
                                }
                            };
                            CiRcle l = new CiRcle();
                            l.setStartX(topleftx);
                            l.setStartY(toplefty);
                            l.setEndX(x3);
                            l.setEndY(y3);
                            l.setW(width);
                            l.setH(height);
                            l.setSize(mohsen.getRefresh().get(k).size);
                            l.setPosition(new Point((int)l.getX1() , (int)l.getY1()));
                            l.setColor(mohsen.getRefresh().get(k).getColor());
                            if(mohsen.getRefresh().get(k).getFillColor()!=null)
                                l.setFillColor(mohsen.getRefresh().get(k).getFillColor());
                            prop.put("radius",l.getH()/2);
                            l.setProperties(prop);
                            mohsen.setNameOfshape(temp);
                            mohsen.updateShape(mohsen.getRefresh().get(k),l);
                        }
                        toEdit = false;
                        whileEditing = false;
                    }
                });
            } else if (temp.charAt(0) == 'L') {
                prop = new Map<String, Double>() {
                    @Override
                    public int size() {
                        return 0;
                    }

                    @Override
                    public boolean isEmpty() {
                        return false;
                    }

                    @Override
                    public boolean containsKey(Object key) {
                        return false;
                    }

                    @Override
                    public boolean containsValue(Object value) {
                        return false;
                    }

                    @Override
                    public Double get(Object key) {
                        return null;
                    }

                    @Override
                    public Double put(String key, Double value) {
                        return null;
                    }

                    @Override
                    public Double remove(Object key) {
                        return null;
                    }

                    @Override
                    public void putAll(Map<? extends String, ? extends Double> m) {

                    }

                    @Override
                    public void clear() {

                    }

                    @Override
                    public Set<String> keySet() {
                        return null;
                    }

                    @Override
                    public Collection<Double> values() {
                        return null;
                    }

                    @Override
                    public Set<Entry<String, Double>> entrySet() {
                        return null;
                    }
                };
                for (k = 0; k < mohsen.getRefresh().size(); k++) {
                    if (mohsen.getRefresh().get(k).getClass() == LIne.class) {
                        cout1++;
                    }
                    if (cout1 == cout2) {
                        x1 = mohsen.getRefresh().get(k).getX1();
                        y1 = mohsen.getRefresh().get(k).getY1();
                        x2 = mohsen.getRefresh().get(k).getX2();
                        y2 = mohsen.getRefresh().get(k).getY2();
                        mohsen.getBrushTool().beginPath();
                        mohsen.getBrushTool().moveTo(x1 + 10, y1);
                        mohsen.getBrushTool().lineTo(x2 + 10, y2);
                        mohsen.getBrushTool().lineTo(x2 - 10, y2);
                        mohsen.getBrushTool().lineTo(x1 - 10, y1);
                        mohsen.getBrushTool().closePath();
                        break;
                    }
                }
                toEdit = true;
                mohsen.getCanvas().setOnMouseMoved(e -> {
                    if (toEdit) {
                        double distance1 = Math.sqrt(Math.pow(e.getX() - x1, 2) + Math.pow(e.getY() - y1, 2));
                        double distance2 = Math.sqrt(Math.pow(e.getX() - x2, 2) + Math.pow(e.getY() - y2, 2));
                        if (distance1 < 5) {
                            mohsen.getCanvas().setCursor(Cursor.N_RESIZE);
                        } else if (distance2 < 5) {
                            mohsen.getCanvas().setCursor(Cursor.S_RESIZE);
                        } else if (mohsen.getBrushTool().isPointInPath(e.getX(), e.getY())) {
                            mohsen.getCanvas().setCursor(Cursor.MOVE);
                        } else {
                            mohsen.getCanvas().setCursor(Cursor.DEFAULT);
                        }
                    } else {
                        mohsen.getCanvas().setCursor(Cursor.DEFAULT);
                    }
                });
                mohsen.getCanvas().setOnMousePressed(e -> {
                    x4 = e.getX();
                    y4 = e.getY();
                    check();
                });
                mohsen.getCanvas().setOnMouseReleased(e -> {
                    if (whileEditing) {
                        if (counter == 2) {
                            x1 = e.getX();
                            y1 = e.getY();
                        } else if (counter == 3) {
                            x2 = e.getX();
                            y2 = e.getY();
                        } else if (counter == 4) {
                            x1 = x1 + e.getX() - x4;
                            x2 = x2 + e.getX() - x4;
                            y1 = y1 + e.getY() - y4;
                            y2 = y2 + e.getY() - y4;
                        }
                        LIne l = new LIne();
                        l.setStartX(x1);
                        l.setEndX(x2);
                        l.setStartY(y1);
                        l.setEndY(y2);
                        l.setSize(mohsen.getRefresh().get(k).getSize());
                        l.setPosition(new Point((int)l.getX1() , (int)l.getY1()));
                        l.setColor(mohsen.getRefresh().get(k).getColor());
                        prop.put("Length",distance(l.getX1(),l.getY1(),l.getX2(),l.getY2()));
                        l.setProperties(prop);
                        mohsen.setNameOfshape(temp);
                        mohsen.updateShape(mohsen.getRefresh().get(k),l);
                        toEdit = false;
                        whileEditing = false;
                    }
                });
            } else if (temp.charAt(0) == 'T') {
                prop = new Map<String, Double>() {
                    @Override
                    public int size() {
                        return 0;
                    }

                    @Override
                    public boolean isEmpty() {
                        return false;
                    }

                    @Override
                    public boolean containsKey(Object key) {
                        return false;
                    }

                    @Override
                    public boolean containsValue(Object value) {
                        return false;
                    }

                    @Override
                    public Double get(Object key) {
                        return null;
                    }

                    @Override
                    public Double put(String key, Double value) {
                        return null;
                    }

                    @Override
                    public Double remove(Object key) {
                        return null;
                    }

                    @Override
                    public void putAll(Map<? extends String, ? extends Double> m) {

                    }

                    @Override
                    public void clear() {

                    }

                    @Override
                    public Set<String> keySet() {
                        return null;
                    }

                    @Override
                    public Collection<Double> values() {
                        return null;
                    }

                    @Override
                    public Set<Entry<String, Double>> entrySet() {
                        return null;
                    }
                };
                for (k = 0; k < mohsen.getRefresh().size(); k++) {
                    if (mohsen.getRefresh().get(k).getClass() == Triangle.class) {
                        cout1++;
                    }
                    if (cout1 == cout2) {
                        Triangle l = (Triangle) mohsen.getRefresh().get(k);
                        mohsen.getBrushTool().beginPath();
                        x1 = l.getX1();
                        x2 = l.getX2();
                        x3 = l.getX3();
                        y1 = l.getY1();
                        y2 = l.getY2();
                        y3 = l.getY3();
                        mohsen.getBrushTool().moveTo(l.getX1(), l.getY1());
                        mohsen.getBrushTool().lineTo(l.getX1(), l.getY2());
                        mohsen.getBrushTool().lineTo(l.getX2(), l.getY2());
                        mohsen.getBrushTool().lineTo(l.getX3(), l.getY3());
                        mohsen.getBrushTool().closePath();
                        break;
                    }
                }
                toEdit = true;
                mohsen.getCanvas().setOnMouseMoved(e -> {
                    if (toEdit) {
                        double distance1 = distance(e.getX(), e.getY(), x1, y1);
                        double distance2 = distance(e.getX(), e.getY(), x2, y2);
                        double distance3 = distance(e.getX(), e.getY(), x3, y3);
                        if (distance1 < 5 || distance2 < 5 || distance3 < 5) {
                            mohsen.getCanvas().setCursor(Cursor.CLOSED_HAND);
                        } else if (mohsen.getBrushTool().isPointInPath(e.getX(), e.getY())) {
                            mohsen.getCanvas().setCursor(Cursor.MOVE);
                        } else {
                            mohsen.getCanvas().setCursor(Cursor.DEFAULT);
                        }
                    } else {
                        mohsen.getCanvas().setCursor(Cursor.DEFAULT);
                    }
                });
                mohsen.getCanvas().setOnMousePressed(e -> {
                    x4 = e.getX();
                    y4 = e.getY();
                    check();
                });
                mohsen.getCanvas().setOnMouseReleased(e -> {
                    if (whileEditing) {
                        if (counter == 4) {
                            x1 = x1 + e.getX() - x4;
                            x2 = x2 + e.getX() - x4;
                            x3 = x3 + e.getX() - x4;
                            y1 = y1 + e.getY() - y4;
                            y2 = y2 + e.getY() - y4;
                            y3 = y3 + e.getY() - y4;
                        } else if (counter == 5) {
                            if (distance(x4, y4, x1, y1) < 5) {
                                x1 = e.getX();
                                y1 = e.getY();
                            } else if (distance(x4, y4, x2, y2) < 5) {
                                x2 = e.getX();
                                y2 = e.getY();
                            } else if (distance(x4, y4, x3, y3) < 5) {
                                x3 = e.getX();
                                y3 = e.getY();
                            }
                        }
                        Triangle l = new Triangle(x1, y1, x2, y2, x3, y3);
                        l.setSize(mohsen.getRefresh().get(k).size);
                        l.setPosition(new Point((int)l.getX1() , (int)l.getY1()));
                        l.setColor(mohsen.getRefresh().get(k).getColor());
                        if(mohsen.getRefresh().get(k).getFillColor()!=null)
                            l.setFillColor( mohsen.getRefresh().get(k).getFillColor());
                        prop.put("X1",l.getX1());
                        prop.put("X2",l.getX2());
                        prop.put("X3",l.getX3());
                        prop.put("Y1",l.getY1());
                        prop.put("Y2",l.getY2());
                        prop.put("Y3",l.getY3());
                        l.setProperties(prop);
                        mohsen.setNameOfshape(temp);
                        mohsen.updateShape(mohsen.getRefresh().get(k),l);
                        whileEditing = false;
                        toEdit = false;
                    }
                });
            }
        } else if (x.equals("remove")) {
            toDraw = false;
            toEdit = false;
            whileEditing = false;
            if (temp.charAt(0) == 'E') {
                for (k = 0; k < mohsen.getRefresh().size(); k++) {
                    if (mohsen.getRefresh().get(k).getClass() == ELlipse.class) {
                        cout1++;
                    }
                    if (cout1 == cout2) {
                        mohsen.setNameOfshape(temp);
                        mohsen.removeShape(mohsen.getRefresh().get(k));
                        break;
                    }
                }
            } else if (temp.charAt(0) == 'R') {
                for (k = 0; k < mohsen.getRefresh().size(); k++) {
                    if (mohsen.getRefresh().get(k).getClass() == REctangle.class) {
                        cout1++;
                    }
                    if (cout1 == cout2) {
                        mohsen.setNameOfshape(temp);
                        mohsen.removeShape(mohsen.getRefresh().get(k));
                        break;
                    }
                }
            } else if (temp.charAt(0) == 'S') {
                for (k = 0; k < mohsen.getRefresh().size(); k++) {
                    if (mohsen.getRefresh().get(k).getClass() == SQuare.class) {
                        cout1++;
                    }
                    if (cout1 == cout2) {
                        mohsen.setNameOfshape(temp);
                        mohsen.removeShape(mohsen.getRefresh().get(k));
                        break;
                    }
                }
            } else if (temp.charAt(0) == 'C') {
                for (k = 0; k < mohsen.getRefresh().size(); k++) {
                    if (mohsen.getRefresh().get(k).getClass() == CiRcle.class) {
                        cout1++;
                    }
                    if (cout1 == cout2) {
                        mohsen.setNameOfshape(temp);
                        mohsen.removeShape(mohsen.getRefresh().get(k));
                        break;
                    }
                }
            } else if (temp.charAt(0) == 'L') {
                for (k = 0; k < mohsen.getRefresh().size(); k++) {
                    if (mohsen.getRefresh().get(k).getClass() == LIne.class) {
                        cout1++;
                    }
                    if (cout1 == cout2) {
                        mohsen.setNameOfshape(temp);
                        mohsen.removeShape(mohsen.getRefresh().get(k));
                        break;
                    }
                }
            } else if (temp.charAt(0) == 'T') {
                for (k = 0; k < mohsen.getRefresh().size(); k++) {
                    if (mohsen.getRefresh().get(k).getClass() == Triangle.class) {
                        cout1++;
                    }
                    if (cout1 == cout2) {
                        mohsen.setNameOfshape(temp);
                        mohsen.removeShape(mohsen.getRefresh().get(k));
                        break;
                    }
                }
            }
        } else if (x.equals("fill")) {
            toDraw = false;
            toEdit = false;
            whileEditing = false;
            if (temp.charAt(0) == 'E') {
                for (k = 0; k < mohsen.getRefresh().size(); k++) {
                    if (mohsen.getRefresh().get(k).getClass() == ELlipse.class) {
                        cout1++;
                    }
                    if (cout1 == cout2) {
                        ELlipse l = new ELlipse(mohsen.getRefresh().get(k).getX1(), mohsen.getRefresh().get(k).getY1(), mohsen.getRefresh().get(k).getX2(), mohsen.getRefresh().get(k).getY2());
                        l.setSize(mohsen.getRefresh().get(k).getSize());
                        l.setPosition(mohsen.getRefresh().get(k).getPosition());
                        l.setColor(mohsen.getRefresh().get(k).getColor());
                        l.setFillColor(l.FXtoAWTColor(ayman.getValue()));
                        l.setProperties(mohsen.getRefresh().get(k).getProperties());
                        mohsen.setNameOfshape(temp);
                        mohsen.updateShape(mohsen.getRefresh().get(k),l);
                        break;
                    }
                }
            } else if (temp.charAt(0) == 'R') {
                for (k = 0; k < mohsen.getRefresh().size(); k++) {
                    if (mohsen.getRefresh().get(k).getClass() == REctangle.class) {
                        cout1++;
                    }
                    if (cout1 == cout2) {
                        REctangle l = new REctangle(mohsen.getRefresh().get(k).getX1(), mohsen.getRefresh().get(k).getY1(), mohsen.getRefresh().get(k).getX2(), mohsen.getRefresh().get(k).getY2());
                        l.setSize(mohsen.getRefresh().get(k).getSize());
                        l.setPosition(mohsen.getRefresh().get(k).getPosition());
                        l.setColor(mohsen.getRefresh().get(k).getColor());
                        l.setFillColor(l.FXtoAWTColor(ayman.getValue()));
                        l.setProperties(mohsen.getRefresh().get(k).getProperties());
                        mohsen.setNameOfshape(temp);
                        mohsen.updateShape(mohsen.getRefresh().get(k),l);
                        break;
                    }
                }
            } else if (temp.charAt(0) == 'S') {
                for (k = 0; k < mohsen.getRefresh().size(); k++) {
                    if (mohsen.getRefresh().get(k).getClass() == SQuare.class) {
                        cout1++;
                    }
                    if (cout1 == cout2) {
                        SQuare l = new SQuare(mohsen.getRefresh().get(k).getX1(), mohsen.getRefresh().get(k).getY1(), mohsen.getRefresh().get(k).getX2(), mohsen.getRefresh().get(k).getY2());
                        l.setSize(mohsen.getRefresh().get(k).getSize());
                        l.setPosition(mohsen.getRefresh().get(k).getPosition());
                        l.setColor(mohsen.getRefresh().get(k).getColor());
                        l.setFillColor(l.FXtoAWTColor(ayman.getValue()));
                        l.setProperties(mohsen.getRefresh().get(k).getProperties());
                        mohsen.setNameOfshape(temp);
                        mohsen.updateShape(mohsen.getRefresh().get(k),l);
                        break;
                    }
                }
            } else if (temp.charAt(0) == 'C') {
                for (k = 0; k < mohsen.getRefresh().size(); k++) {
                    if (mohsen.getRefresh().get(k).getClass() == CiRcle.class) {
                        cout1++;
                    }
                    if (cout1 == cout2) {
                        CiRcle l = new CiRcle(mohsen.getRefresh().get(k).getX1(), mohsen.getRefresh().get(k).getY1(), mohsen.getRefresh().get(k).getX2(), mohsen.getRefresh().get(k).getY2());
                        l.setSize(mohsen.getRefresh().get(k).getSize());
                        l.setPosition(mohsen.getRefresh().get(k).getPosition());
                        l.setColor(mohsen.getRefresh().get(k).getColor());
                        l.setFillColor(l.FXtoAWTColor(ayman.getValue()));
                        l.setProperties(mohsen.getRefresh().get(k).getProperties());
                        mohsen.setNameOfshape(temp);
                        mohsen.updateShape(mohsen.getRefresh().get(k),l);
                        break;
                    }
                }
            } else if (temp.charAt(0) == 'L') {
                return;
            } else if (temp.charAt(0) == 'T') {
                for (k = 0; k < mohsen.getRefresh().size(); k++) {
                    if (mohsen.getRefresh().get(k).getClass() == Triangle.class) {
                        cout1++;
                    }
                    if (cout1 == cout2) {
                        Triangle r = (Triangle) mohsen.getRefresh().get(k);
                        Triangle l = new Triangle(r.getX1(), r.getY1(), r.getX2(), r.getY2(), r.getX3(), r.getY3());
                        l.setSize(mohsen.getRefresh().get(k).getSize());
                        l.setPosition(mohsen.getRefresh().get(k).getPosition());
                        l.setColor(mohsen.getRefresh().get(k).getColor());
                        l.setFillColor(l.FXtoAWTColor(ayman.getValue()));
                        l.setProperties(mohsen.getRefresh().get(k).getProperties());
                        mohsen.setNameOfshape(temp);
                        mohsen.updateShape(mohsen.getRefresh().get(k),l);
                        break;
                    }
                }
            }
        }
    }

    public  void HandleButton4 ( ActionEvent event){
        toDraw = false;
        whileEditing = false;
        toEdit = false;
        x = ((Button) event.getSource()).getText();
        Window stage = canvas.getScene().getWindow();
        File file = null ;
        sayed.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("xml file","*.xml"),
                new FileChooser.ExtensionFilter("json file","*.json"));
        if(x.equals("save")){
            sayed.setTitle("save");
            sayed.setInitialFileName("MySave");
            try {
                file = sayed.showSaveDialog(stage);
                sayed.setInitialDirectory(file.getParentFile());
            }catch (Exception e){

            }
            try {
                mohsen.save(file.getPath());
            }catch (Exception e){

            }
        }
        else if (x.equals("load")){
            sayed.setTitle("load");
            try {
                file = sayed.showOpenDialog(stage);
                sayed.setInitialDirectory(file.getParentFile());
            }catch (Exception e){

            }
            try {
                mohsen.load(file.getPath());
            }catch (Exception e){

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sayed = new FileChooser();
        sayed.setInitialDirectory(new File("C:\\Users\\mohamed\\Desktop\\projecttt\\src"));
        mohsen = new DDrawingEngine();
        mohsen.canvas = canvas ;
        mohsen.brushTool = mohsen.getCanvas().getGraphicsContext2D();
        mohsen.getBrushTool().clearRect(0, 0, 770, 458);
        mohsen.getBrushTool().setFill(Color.WHITE);
        mohsen.getBrushTool().fillRect(0, 0, 770, 458);
        for (int i = 1; i <= 100; i++) {
            go.add(i + "");
        }
        mido.setValue("5");
        mido.setItems(go);
        mohsen.setBrushTool(mohsen.getCanvas().getGraphicsContext2D());
        mohsen.getBrushTool().setFill(Color.WHITE);
        mohsen.getBrushTool().fillRect(0, 0, 770, 458);
        LinkedList<sshaPe> temp = new LinkedList();
        mohsen.addtoTOtal(temp);
        mohsen.setRefresh(mohsen.total.get(0));
        shapes.setItems(mohsen.getMenu());
        mohsen.initialize();
    }

    public void check() {
        if (mohsen.getCanvas().getCursor() == Cursor.E_RESIZE) {
            counter = 0;
            whileEditing = true;
        } else if (mohsen.getCanvas().getCursor() == Cursor.W_RESIZE) {
            counter = 1;
            whileEditing = true;
        } else if (mohsen.getCanvas().getCursor() == Cursor.N_RESIZE) {
            counter = 2;
            whileEditing = true;
        } else if (mohsen.getCanvas().getCursor() == Cursor.S_RESIZE) {
            counter = 3;
            whileEditing = true;
        } else if (mohsen.getCanvas().getCursor() == Cursor.MOVE) {
            counter = 4;
            whileEditing = true;
        } else if (mohsen.getCanvas().getCursor() == Cursor.CLOSED_HAND) {
            counter = 5;
            whileEditing = true;
        } else {
            whileEditing = false;
        }
    }


    public double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

}