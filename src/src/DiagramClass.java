package src;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.*;
import src.DiagramLabel;
import src.ElementClass;

import javax.swing.*;

/**
 * UML Actor on a diagram.
 */
public class DiagramClass extends AbstractDiagramNode {
	 static Graphics canva;

	static final double CLASS_WIDTH = 65.0;
	static final double CLASS_HEIGHT = 85.0;
	//InvisibleTextField atribute = new InvisibleTextField();
	String atribute;
	String method;
    static int counter=0;
	int n;
	int m;
	boolean a = false;

	public DiagramClass(double mX, double mY, String caption, ArrayList<String> attributes, ArrayList<String> methods) {


		super(mX, mY, caption, attributes, methods);
		vertices.clear();

		if (a == false){
			//
			if(getAttributes().size() == 0){

				throw new IllegalArgumentException("Num не должен быть null");
			}
			if(n>=1){
				a = true;
			}
		}
		
			vertices.add(new Vertex((mX+ scale(CLASS_WIDTH / 2)),    scale(mY), "bot-middle"));
	        vertices.add(new Vertex((mX+ scale(CLASS_WIDTH / 4)),    scale(mY), "bot-left"));
	        vertices.add(new Vertex((mX+ scale(CLASS_WIDTH / 4 * 3)),scale(mY), "bot-right"));
	        vertices.add(new Vertex((mX+ scale(CLASS_WIDTH / 2)),    scale(mY + CLASS_HEIGHT), "top-middle"));
	        vertices.add(new Vertex((mX+ scale(CLASS_WIDTH / 4)),    scale(mY + CLASS_HEIGHT), "top-left"));
	        vertices.add(new Vertex((mX+ scale(CLASS_WIDTH / 4 * 3)),scale(mY + CLASS_HEIGHT), "top-right"));
	        vertices.add(new Vertex((mX),             scale(mY + CLASS_HEIGHT / 3), "left-bot"));
	        vertices.add(new Vertex((mX),             scale(mY + CLASS_HEIGHT / 3 * 2), "left-top"));
	        vertices.add(new Vertex((mX+ scale(CLASS_WIDTH)),        scale(mY +CLASS_HEIGHT / 3), "right-bot"));
	        vertices.add(new Vertex((mX+ scale(CLASS_WIDTH)),        scale(mY + CLASS_HEIGHT / 3 * 2), "right-top"));


		addToQueue(new DiagramLabel(this));
		
		
	}

	 @Override
	protected void addVertices(){

		 canva = getCanvas();
		 
	}
	
	@Override
	protected void internalDraw() {

		double mX = getmX();
		double mY = getmY();

        vertices.clear();
        vertices.add(new Vertex((minX()+ scale(WIDTH / 2)),    (minY()), "bot-middle"));
        vertices.add(new Vertex((minX()+ scale(WIDTH / 4)),    (minY()), "bot-left"));
        vertices.add(new Vertex((minX()+ scale(WIDTH / 4 * 3)),(minY()), "bot-right"));
        vertices.add(new Vertex((minX()+ scale(WIDTH / 2)),    (minY() + scale(HEIGHT)), "top-middle"));
        vertices.add(new Vertex((minX()+ scale(WIDTH / 4)),    (minY() + scale(HEIGHT)), "top-left"));
        vertices.add(new Vertex((minX()+ scale(WIDTH / 4) * 3),(minY() + scale(HEIGHT)), "top-right"));
        vertices.add(new Vertex((minX()),                            (minY() + scale(HEIGHT / 3)), "left-bot"));
        vertices.add(new Vertex((minX()),                            (minY() + scale(HEIGHT / 3 )* 2), "left-top"));
        vertices.add(new Vertex((minX()+ scale(WIDTH)),              (minY() +scale(HEIGHT / 3)), "right-bot"));
        vertices.add(new Vertex((minX()+ scale(WIDTH)),              (minY() + scale(HEIGHT / 3 * 2)), "right-top"));
        
        canva.setFont(getCanvas().getFont().deriveFont((float) scale(FONTSIZEPT)));
        FontMetrics metrics2 = getCanvas().getFontMetrics();
        shift = metrics2.stringWidth(getCaption());
        getCanvas().setColor(Color.white);
        canva.fillRect(scaleX(getmX() -WIDTH/2)+1 , scaleY(getmY() - HEIGHT / 2)+1, scale(WIDTH)-1, scale(HEIGHT)-1);
        getCanvas().setColor(Color.black);
        //getCanvas().drawString(getCaption(), (int)(scaleX(getmX())+shift/2) , scaleY(getmY() + (FONTSIZEPT)));


		n = getAttributes().size();

		if (n != 0) {
			//n = n++;
			for (int i = 0; i < n; i++
			) {

				int length;
				
				atribute = getAttributes().get(i);
				getCanvas().drawString(ElementClass.attributes(atribute), (int) (scaleX(getmX()) + shift / 8), (int) (scaleY (getmY() + (3 + i) *(FONTSIZEPT))));
			}
		}

		m = getMethods().size();
		if (m!=0) {
			for (int j = 0; j < m; j++
			) {

				method = getMethods().get(j);
				getCanvas().drawString(ElementClass.methods(method), (int) (scaleX(getmX()) + shift / 8), scaleY(getmY() + (4 + j + n) * (FONTSIZEPT)));

			}
		}


		if(getAttributes().size()<3){
			//левая грань
			getCanvas().drawLine(scaleX(mX), scaleY(mY),
					scaleX(mX), scaleY(mY + CLASS_HEIGHT));
			//правая грань
			getCanvas().drawLine(scaleX(mX + CLASS_WIDTH), scaleY(mY),
					scaleX(mX + CLASS_WIDTH), scaleY(mY + CLASS_HEIGHT));
			//нижняя грань
			getCanvas().drawLine(scaleX(mX), scaleY(mY + CLASS_HEIGHT),
					scaleX(mX + CLASS_WIDTH), scaleY(mY + CLASS_HEIGHT));
			//верхняя
			getCanvas().drawLine(scaleX(mX), scaleY(mY),
					scaleX(mX + CLASS_WIDTH), scaleY(mY));
			//после класса
			getCanvas().drawLine(scaleX(mX), scaleY(mY + CLASS_HEIGHT / 4),
					scaleX(mX + CLASS_WIDTH), scaleY(mY + CLASS_HEIGHT / 4));
			//после атрибутов
			getCanvas().drawLine(scaleX(mX), scaleY(mY +  CLASS_HEIGHT / 2),
					scaleX(mX + CLASS_WIDTH), scaleY(mY +  CLASS_HEIGHT / 2));
		}else
		{
			n = getAttributes().size() - 2;
			//левая грань
			getCanvas().drawLine(scaleX(mX), scaleY(mY),
					scaleX(mX), scaleY(mY + CLASS_HEIGHT));
			//правая грань
			getCanvas().drawLine(scaleX(mX + CLASS_WIDTH), scaleY(mY),
					scaleX(mX + CLASS_WIDTH), scaleY(mY + CLASS_HEIGHT));
			//нижняя грань
			getCanvas().drawLine(scaleX(mX), scaleY(mY + CLASS_HEIGHT),
					scaleX(mX + CLASS_WIDTH), scaleY(mY + CLASS_HEIGHT));
			//верхняя
			getCanvas().drawLine(scaleX(mX), scaleY(mY),
					scaleX(mX + CLASS_WIDTH), scaleY(mY));
			//после класса
			getCanvas().drawLine(scaleX(mX), scaleY(mY + CLASS_HEIGHT / 4),
					scaleX(mX + CLASS_WIDTH), scaleY(mY + CLASS_HEIGHT / 4));
			//после атрибутов
			getCanvas().drawLine(scaleX(mX), scaleY(mY +  CLASS_HEIGHT / 2 + n *(FONTSIZEPT)),
					scaleX(mX + CLASS_WIDTH), scaleY(mY +  CLASS_HEIGHT / 2  + n *(FONTSIZEPT)));
		}





        }





		//getCanvas().setPaintMode();



	@Override
	protected boolean internalTestHit(double x, double y) {
		double dX = x - getmX();
		double dY = y - getmY();
		return dY > -CLASS_HEIGHT / 2 && dY < CLASS_HEIGHT / 2 && dX > -CLASS_WIDTH / 2 && dX < CLASS_WIDTH / 2;
	}

	@Override
	protected double getMinX() {
		return getmX() - CLASS_WIDTH / 2;
	}

	@Override
	protected double getMinY() {
		return getmY() - CLASS_HEIGHT / 2;
	}

	@Override
	protected double getMaxX() {
		return getmX() + CLASS_WIDTH / 2;
	}

	@Override
	protected double getMaxY() {
		return getmY() + CLASS_HEIGHT / 2;
	}


    @Override
    protected Vertex getTopMiddleVertex(){
        return vertices.get(1);
    }


    @Override
    protected Vertex getBotMiddleVertex(){
        return vertices.get(0);
    }


    @Override
    protected Vertex getLeftVertex(){
        return vertices.get(2);
    }


    @Override
    protected Vertex getRightVertex(){
        return vertices.get(3);
    }

	@Override
	protected boolean internalGetHint(StringBuilder hintStr) {
		hintStr.append("Class: " + getCaption());
		return true;
	}
}