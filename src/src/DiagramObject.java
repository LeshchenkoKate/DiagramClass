package src;

import org.json.simple.JSONObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.function.Consumer;

/** ������� ����� �������, ����������������� �� ���������. */
public abstract class DiagramObject {

    private static final int BIG_VALUE = 0xFFFF;

    /**
     * ������ ������ ��������, ������������� ��������� �� ���������.
     */
    private static final int L = 4;

    private DiagramObject previous;
    private DiagramObject next;
    private DiagramObject first;
    private DiagramObject last;
    private DiagramObject parent;
    private Font font;
    private Color color;

    private Graphics canvas;
    private double scale;
    private double dX;
    private double dY;

    private final double radout = 9;
    private final double radin = 5;
    private final double shiftout = radout/2;
    private final double shiftin = radin/2;

    public int id;

    protected ArrayList<DiagramGeneralization> lines_in = new ArrayList<>();
    protected ArrayList<DiagramGeneralization> lines_out = new ArrayList<>();
    protected ArrayList<DiagramGeneralization> getLines_in() {return  lines_in;}
    protected ArrayList<DiagramGeneralization> getLines_out() {return  lines_out;}
    protected JSONObject getJSON(){return null;}
    public int already_draw = 0;



    protected ArrayList<Vertex> vertices = new ArrayList<>();

    protected ArrayList<Vertex> getVertices (){
        return vertices;
    }

    private static boolean assigned(Object x) {
        return x != null;
    }

    /** ��������� �������� ������������� ������� TCanvas. */
    private void saveCanvasSetup() {
        assert assigned(canvas);
        font = canvas.getFont();
        color = canvas.getColor();
        // TODO: �������� ���� ���������� ���� ��������� ������� Canvas
    }

    /** ��������������� �������� ������������� ������� TCanvas. */
    private void restoreCanvasSetup() {
        canvas.setFont(font);
        canvas.setColor(color);
        // TODO: �������� ���� �������������� ���� ��������� ������� Canvas

    }

    /**
     * ���������� �������� �������� Hint.
     *
     * @param hintStr
     *            �����, � ������� �������� ������ ��� �����
     */
    protected boolean internalGetHint(StringBuilder hintStr) {
        return false;
    }

    /**
     * ��������� ������� ����� � �������� �� ������� �� X.
     *
     * @param value
     *            �������� ������� �����
     * @return �������� �����
     */
    protected final int scaleX(double value) {
        return scale(value - dX);
    }

    /**
     * ��������� ������� ����� � �������� �� ������� �� Y.
     *
     * @param value
     *            �������� ������� �����
     * @return �������� �����
     */

    protected final int scaleY(double value) {
        return scale(value - dY);
    }

    /**
     * ��������� ������� ����� � ��������.
     *
     * @param value
     *            �������� ������� �����
     * @return �������� �����
     */
    protected final int scale(double value) {
        return (int) Math.round(value * scale);
    }

    /**
     * ��������� � ����� ������� ��������� ����� ���������.
     *
     * @param subObj
     *            ����������� ���������
     */


    public final void addToQueue(DiagramObject subObj) {
        if (assigned(last)) {
            assert assigned(first);
            assert!assigned(last.next);
            assert!assigned(first.previous);
            last.next = subObj;
            subObj.previous = last;

        } else {
            assert!assigned(first);
            first = subObj;
            subObj.previous = null;
        }
        subObj.next = null;
        subObj.parent = this;
        last = subObj;

    }





    /**
     * ���������� ��������� ��������� �������.
     *
     * @desc �������������� ������ �����, ����� ����������� ���������,
     *       ����������� ��� ������� �������
     *
     */

    final protected void drawVertices(ArrayList<Vertex> vertices){

        for (int i = 0; i < vertices.size(); i++){
        	/*
            canvas.setColor(Color.CYAN);
            canvas.fillOval(  (int)vertices.get(i).x-scale(shiftout),   (int)vertices.get(i).y-scale(shiftout), scale(radout), scale(radout));
            canvas.setColor(Color.WHITE);
            canvas.setXORMode(Color.CYAN);
            canvas.fillOval(  (int)vertices.get(i).x-scale(shiftin),   (int)vertices.get(i).y-scale(shiftin), scale(radin), scale(radin));
            canvas.setPaintMode();
            */
        }

    }

    protected void internalDrawSelection(int dX, int dY) {
        canvas.setColor(Color.BLUE);
        canvas.setXORMode(Color.WHITE);
        // ���� ���
        canvas.fillRect(minX() + dX + 7*L, minY() + dY + 9*L, L, L);
        // ���� ����
        canvas.fillRect(maxX() + dX + 9*L, minY() + dY + 9*L, L, L);
        // ��� ���
        canvas.fillRect(minX() + dX + 7*L, maxY() + dY + 11*L, L, L);
        // ��� ����
        canvas.fillRect(maxX() + dX+ 9*L, maxY() + dY + 11*L, L, L);
        canvas.setPaintMode();
        drawVertices(getVertices());


    }

    protected String getCaption() {
        return null;
    }

    /**
     * ���������� ��������� �������.
     *
     */
    protected void internalDraw() {
        // ������ �� ������ �� ������ DrawObject
    }

    protected void addVertices() {
        // ������ �� ������ �� ������ DrawObject
    }


    /**
     * ���������� �������� ��������� ������� �� ������.
     *
     * @desc �� ������ DrawObject ���������� False
     * @param x
     *            ������� ��������
     * @param y
     *            ������� ��������
     */
    protected boolean internalTestHit(double x, double y) {
        return false;
    }

    /**
     * ����������� ������� � ����� �����.
     *
     * @param dX
     *            �������� �� X
     * @param dY
     *            �������� �� Y
     */
    protected void internalDrop(double dX, double dY) {
        // ������ �� ������ �� ������ DrawObject
    }

    /**
     * ����� ������� � ������� ������� ��������� ����� �������, ����� ��
     * �������������� �������.
     */
    protected final void moveUp() {
        if (assigned(next)) {
            if (assigned(previous))
                previous.next = next;
            else if (assigned(parent))
                parent.first = next;
            next.previous = previous;
            previous = next;
            next = next.next;
            previous.next = this;
            if (assigned(next))
                next.previous = this;
            else if (assigned(parent))
                parent.last = this;
        }

    }

    /**
     * ����� ������� � ������� ������� ��������� ����� �������, ����� ��
     * �������������� �����.
     */
    protected final void moveDown() {
        if (assigned(previous))
            previous.moveUp();
    }

    /** ������ ��������� ������� ���������. */
    protected final DiagramObject getFirstSubObj() {
        return first;
    }

    /** ��������� ��������� ������� ���������. */
    protected final DiagramObject getLastSubObj() {
        return last;
    }

    /** ��������� ������. */
    protected final DiagramObject getNext() {
        return next;
    }

    /** ���������� ������. */
    protected final DiagramObject getPrevious() {
        return previous;
    }

    /**
     * ������ ����������.
     */
    protected final double getScale() {
        return scale;
    }

    /**
     * ����������� ������� ��������.
     */
    protected double getMinX() {
        return Double.NaN;
    }

    /**
     * ����������� ������� ��������.
     */
    protected double getMinY() {
        return Double.NaN;
    }

    /**
     * ������������ ������� ��������.
     */
    protected double getMaxX() {
        return Double.NaN;
    }

    /**
     * ������������ ������� ��������.
     */
    protected double getMaxY() {
        return Double.NaN;
    }

    /**
     * ���������� ����� ����������� ���������, ������������ ��� ������� �������.
     *
     * @desc �������� InternalGetHint. ���� InternalGetHint �� �������������
     *       (����������� false), �������� ������� Hint �������, ��������������
     *       �� ���������
     */
    public String getHint() {
        StringBuilder hintStr = new StringBuilder();
        if (internalGetHint(hintStr))
            return hintStr.toString();
        else if (assigned(parent))
            return parent.getHint();
        else {
            return "";
        }
    }

    /**
     * �������� ������� � ����������, ������� ���������� � �������������.
     *
     * @param aMinX
     *            ����� ���
     * @param aMinY
     *            ���
     * @param aMaxX
     *            ������ ���
     * @param aMaxY
     *            �����
     * @param addProc
     *            ���������, ���������� �������
     */
    public final void collect(int aMinX, int aMinY, int aMaxX, int aMaxY, Consumer<DiagramObject> addProc) {

        if (isCollectable() && (aMinX < minX()) && (aMaxX > maxX()) && (aMinY < minY()) && (aMaxY > maxY()))
            addProc.accept(this);

        DiagramObject curObj = first;
        while (assigned(curObj)) {
            curObj.collect(aMinX, aMinY, aMaxX, aMaxY, addProc);
            curObj = curObj.getNext();
        }

    }

    /**
     * ������������ ������, � ������� ��������� �������� ��������� ������
     * ������.
     */
    protected final DiagramObject getParent() {
        return parent;
    }

    /**
     * ������� ������ � ���������.
     *
     * @desc �� ����� �������� ������ ������������� ��������� ���� �� �������.
     */
    public void removeFromQueue() {
        if (assigned(previous))
            previous.next = next;
        else if (assigned(parent))
            parent.first = next;
        if (assigned(next))
            next.previous = previous;
        else if (assigned(parent))
            parent.last = previous;
        while (assigned(first)) {
            first.removeFromQueue();
        }
    }

    /**
     * ��������� �������.
     *
     * @desc �� ������ DrawObject, ����� Draw �������� �� ���� �������
     *       ����������� � �������� ��� ��� ����� Draw
     *
     * @param canvas
     *            �����
     * @param aDX
     *            �������� �� X
     * @param aDY
     *            �������� �� Y
     * @param scale
     *            ������ ���������������
     */
    public final void draw(Graphics canvas, double aDX, double aDY, double scale) {
        assert assigned(canvas);
        this.canvas = canvas;
        this.scale = scale;
        dX = aDX;
        dY = aDY;
        //System.out.println(aDX + " " + aDY);
        saveCanvasSetup();
        addVertices();
        internalDraw();
        restoreCanvasSetup();
        DiagramObject curObj = first;
        while (assigned(curObj)) {
            curObj.draw(canvas, aDX, aDY, scale);
            curObj = curObj.next;

        }
    }



/*    public final void fill(Graphics canvas, double aDX, double aDY, double scale) {
        assert assigned(canvas);
        this.canvas = canvas;
        this.scale = scale;
        dX = aDX;
        dY = aDY;
        saveCanvasSetup();
        fillFigure(canvas);
        restoreCanvasSetup();
        DiagramObject curObj = first;
        while (assigned(curObj)) {
            curObj.draw(canvas, aDX, aDY, scale);
            curObj = curObj.next;
        }
    }
*/



    public Graphics getCanvas() {
        return canvas;
    }

    /**
     * ��������� ��������� �������.
     *
     * @desc ������ ����� ��������� ��������� ��������, ��������� ����������
     *       ��������� ������������� ������.
     */
    public final void drawSelection() {
        drawSelection(0, 0);
    }

    /**
     * ��������� ��������� ������� �� ���������.
     *
     * @param aDX
     *            �������� �������� �� X
     * @param aDY
     *            �������� �������� �� Y
     */
    public final void drawSelection(int aDX, int aDY) {
        saveCanvasSetup();
        internalDrawSelection(aDX, aDY);
        restoreCanvasSetup();
    }

    /**
     * ����������� ������� � ����� �����.
     *
     * @param aDX
     *            �������� �������� �� X
     * @param aDY
     *            �������� �������� �� Y
     *
     */
    public final void drop(int aDX, int aDY) {
        internalDrop(aDX / scale, aDY / scale);


    }

    /**
     * �������� ��������� �����.
     *
     * @desc ������ ����� �������� �� ������� ����������� DrawObject � �������,
     *       �������� ������� ���������, � �������� ��� ��� ����� TestHit. ����
     *       �����-���� �� ������� ���� ��������� <> null, ����� ���������������
     *       � ���������� ���������. ���� ����� ������� ���� ������� �� �������
     *       ���������, �������� �� null -- ���������� ����� InternalTestHit.
     * @result ������, ��������� �����, ��� null, ���� ������ �� �������
     *
     * @param x
     *            �������� ��������
     * @param y
     *            �������� ��������
     */
    public final DiagramObject testHit(int x, int y) {

        DiagramObject result;
        DiagramObject curObj = last;

        while (assigned(curObj)) {
            result = curObj.testHit(x, y);
            if (assigned(result))
                return result;
            curObj = curObj.previous;
        }
        assert scale > 0;
        if (internalTestHit(x / scale + dX, y / scale + dY)){

            result = this;}

        else {

            result = null;
        }
        return result;
    }

    /**
     * ����������� �������� � �������� �����������.
     */
    public final int minX() {
        double buf = getMinX();
        return Double.isNaN(buf) ? -BIG_VALUE : scaleX(buf);
    }

    /**
     * ����������� �������� � �������� �����������.
     */
    public final int minY() {
        double buf = getMinY();
        return Double.isNaN(buf) ? -BIG_VALUE : scaleY(buf);
    }

    /**
     * ������������ �������� � �������� �����������.
     */
    public final int maxX() {
        double buf = getMaxX();
        return Double.isNaN(buf) ? BIG_VALUE : scaleX(buf);
    }

    /**
     * ������������ �������� � �������� �����������.
     */
    public final int maxY() {
        double buf = getMaxY();
        return Double.isNaN(buf) ? BIG_VALUE : scaleY(buf);
    }

    /** �������� �� ������ ������������ � ������� Drag and Drop. */
    public boolean isMoveable() {
        return true;
    }

    /** ����� �� ��������� ������ � ������� �����. */
    public boolean isCollectable() {
        return true;
    }

    /** �������� �� ��� �������, � ������� ��� ��������� ������. */
    public final double getDX() {
        return dX;
    }

    /** �������� �� ��� �������, � ������� ��� ��������� ������. */
    public final double getDY() {
        return dY;
    }

    /**
     * ������� ��� ��������� �����. �� ��������� ���������� ��������� ������.
     *
     * @param context
     *            �����, �� ������� ���������� ���������.
     */
    public void mouseOver(Graphics context) {
        // canvas.getCanvas().getStyle().setCursor(Cursor.DEFAULT);
    }

    /**
     * ������� ��� ����� �����. ����������� ������ �� ������.
     *
     * @param y
     *            �������� �����.
     * @param x
     *            �������� �����.
     **/
    public void mouseDown(double x, double y) {
    }

}
