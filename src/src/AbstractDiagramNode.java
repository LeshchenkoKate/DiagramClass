package src;

import src.DiagramLabel;
import org.json.simple.JSONObject;
import java.awt.*;
import java.util.ArrayList;
import src.DiagramObject;
import src.LabelParent;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Base class for diagram nodes, such as Actor and UseCase.
 */
abstract class AbstractDiagramNode extends DiagramObject implements LabelParent {
	
	private double mX;
	private double mY;

	private double dX;
	protected double dY;

	private String caption;
	private ArrayList<String> attributes;
	private String attribute;
	private String method;
	private ArrayList<String> methods;

	
    protected static int FONTSIZEPT = 10;
    protected static int HEIGHT = 3 * FONTSIZEPT;
    protected static double WIDTH = 50;
    protected double shift;


	protected Color colorFill = Color.cyan;
	protected Color colorFont = Color.black;
	protected Color colorBorder = Color.black;

    protected int getHEIGHT (){
    	return HEIGHT;
    }
    protected double getWIDTH (){
    	return WIDTH;
    }



	public void setColorFont (Color color) { this.colorFont = color;}
	public void setColorFill (Color color) { this.colorFill = color;}
	public void setColorBorder(Color color) {this.colorBorder = color;}

/*
	public ArrayList<DiagramGeneralization> get_lines_in() {
		HashMap <Integer, DiagramObject> diagramObjects = ((Scheme)getDiagramObject()).diagramObjects;
		ArrayList <DiagramGeneralization> lines = new ArrayList<>();
		Iterator<DiagramObject> iterator = diagramObjects.values().iterator();
		while (iterator.hasNext()){
			DiagramObject object = iterator.next();
			if(object.getClass().getName().equals("src.DiagramGeneralization")) {
				DiagramGeneralization link = (DiagramGeneralization)object;
				if(link.nTo == this){
					lines.add(link);
				}
			}
		}
		return lines;
    }

	public ArrayList<DiagramGeneralization> get_lines_out() {
		HashMap<Integer, DiagramObject> diagramObjects = ((Scheme) getDiagramObject()).diagramObjects;
		ArrayList<DiagramGeneralization> lines = new ArrayList<>();
		Iterator<DiagramObject> iterator = diagramObjects.values().iterator();
		while (iterator.hasNext()) {
			DiagramObject object = iterator.next();
			if (object.getClass().getName().equals("src.DiagramGeneralization")) {
				DiagramGeneralization link = (DiagramGeneralization) object;
				if (link.nFrom == this) {
					lines.add(link);
				}
			}
		}
		return lines;
	}
*/
	protected JSONObject getJSON(){
        JSONObject elementDetails = new JSONObject();

        elementDetails.put("id", this.id);
        elementDetails.put("x", this.getmX());
        elementDetails.put("y", this.getmY());
        elementDetails.put("text", this.getCaption());
        elementDetails.put("shape", this.getClass().getName());
		elementDetails.put("atributes", this.getAttributes());
		elementDetails.put("methods", this.getMethods());

        return elementDetails;
    }
	
	AbstractDiagramNode(double mX, double mY, String caption, ArrayList<String> attributes, ArrayList<String> methods) {
		this.mX = mX;
		this.mY = mY;
		this.caption = caption;
		this.attributes = attributes;
		this.methods = methods;

		dX = 0;
		dY = 10;
	}
	
	  @Override
	    protected ArrayList<DiagramGeneralization> getLines_in() {
	        return lines_in;
	    }

	    @Override
	    protected ArrayList<DiagramGeneralization> getLines_out() {
	        return lines_out;
	    }

	@Override
	public String getText() {
		return caption;
	}

	@Override
	public double getLabelX() {

		return mX + dX;
	}

	@Override
	public double getLabelY() {

		return mY + dY;
	}

	@Override
	public void setLabelX(double newX) {
		dX = newX - mX;

	}

	@Override
	public void setLabelY(double newY) {
		dY = newY - mY;
	}

	final double getmX() {
		return mX;
	}

	final double getmY() {
		return mY;
	}

	protected final String getCaption() {
		return caption;
	}

	protected final void setCaption(String caption) {
		this.caption = caption;
	}

	protected final void setAttributes(String newattribute) {
		//int count = attributes.size();
		attributes.add(newattribute);

	}
	protected final void setMethods(String newmethod) {
		//int count = attributes.size();
		methods.add(newmethod);

	}

	protected final ArrayList<String> getAttributes() {
		return attributes;
	}
	protected final ArrayList<String> getMethods() {
		return methods;
	}

	@Override
	protected final void internalDrop(double dX, double dY) {
		mX += dX;
		mY += dY;
	}

	protected abstract Vertex getTopMiddleVertex();
    protected abstract Vertex getBotMiddleVertex();
    protected abstract Vertex getLeftVertex();
    protected abstract Vertex getRightVertex();
	
	@Override
	protected boolean internalGetHint(StringBuilder hintStr) {
		hintStr.append(caption);
		return true;
	}
}