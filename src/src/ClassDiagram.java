package src;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import src.DiagramObject;
import src.Button;
import src.DependencyStereotype;
import src.DiagramClass;
import java.util.*;
//import src.DiagramAssociation;
//import src.DiagramDependency;
//import src.DiagramGeneralization;
//import src.DiagramUseCase;

/**
 * UML Use Case Diagram prototype.
 */
public class ClassDiagram extends DiagramObject {
	 public HashMap <Integer, DiagramObject> diagramObjects = new HashMap<>();
	//public double mouseXcl;
	//public double mouseYcl;

	public ClassDiagram(boolean empty) {
		if(empty) {
			
		}
		else {
			DiagramClass a1 = new DiagramClass(40, 40, "Class", new ArrayList<String>(Arrays.asList("-int count;", "-string name;")),  new ArrayList<String>(Arrays.asList("+void main();")));
			addToQueue(a1);
			DiagramClass a2 = new DiagramClass(40, 140, "Class2", new ArrayList<String>(Arrays.asList("-double x;", "-double y;")),  new ArrayList<String>(Arrays.asList("+int getsum();", "+void setsum();")));
			addToQueue(a2);
			DiagramClass a3 = new DiagramClass(40, 240, "Class3", new ArrayList<String>(Arrays.asList("-int x;", "-int y;")),  new ArrayList<String>(Arrays.asList("~int getmult();", "~void setmult();")));
			addToQueue(a3);

			DiagramClass a6 = new DiagramClass(140, 40, "Class6", new ArrayList<String>(Arrays.asList("-double x;", "-double y;")),  new ArrayList<String>(Arrays.asList("~int getdiv();", "~void setdiv();")));
			addToQueue(a6);

			DiagramDependency diagramDependency = new DiagramDependency(a1, a2, "test");
			diagramDependency.id = 4;
			addToQueue(diagramDependency);
			DiagramAssociation diagramAssociation = new DiagramAssociation(a3, a6);
			diagramAssociation.id = 5;
			addToQueue(diagramAssociation);
			DiagramGeneralization diagramGeneralization = new DiagramGeneralization(a3, a2, "test2");
			diagramGeneralization.id = 7;
			addToQueue(diagramGeneralization);



			/*
			DiagramGeneralization diagramGeneralization1 = new DiagramGeneralization(a1, a2);
			DiagramAssociation diagramGeneralization2 = new DiagramAssociation(a1, a3);
            DiagramGeneralization diagramGeneralization3 = new DiagramGeneralization(a3, a4);
            DiagramGeneralization diagramGeneralization4 = new DiagramGeneralization(a4, a5);
            DiagramGeneralization diagramGeneralization5 = new DiagramGeneralization(a5, a6);
            DiagramGeneralization diagramGeneralization6 = new DiagramGeneralization(a6, a1);
            
            diagramGeneralization1.id = 9;
            diagramGeneralization2.id = 10;
            diagramGeneralization3.id = 11;
            diagramGeneralization4.id = 12;
            diagramGeneralization5.id = 13;
            diagramGeneralization6.id = 14;
            
            addToQueue(diagramGeneralization1);
            addToQueue(diagramGeneralization2);
            addToQueue(diagramGeneralization3);
            addToQueue(diagramGeneralization4);
            addToQueue(diagramGeneralization5);
            addToQueue(diagramGeneralization6);
            */
            a1.id = 1;
            a2.id = 2;
            a3.id = 3;

            a6.id = 6;
            
            diagramObjects.put(a1.id, a1);
            diagramObjects.put(a2.id, a2);
            diagramObjects.put(a3.id, a3);

            diagramObjects.put(a6.id, a6);

			diagramObjects.put(diagramDependency.id, diagramDependency);
			diagramObjects.put(diagramAssociation.id, diagramAssociation);
			diagramObjects.put(diagramGeneralization.id, diagramGeneralization);

            /*
            diagramObjects.put(diagramGeneralization1.id, diagramGeneralization1);
            diagramObjects.put(diagramGeneralization2.id, diagramGeneralization2);
            diagramObjects.put(diagramGeneralization3.id, diagramGeneralization3);
            diagramObjects.put(diagramGeneralization4.id, diagramGeneralization4);
            diagramObjects.put(diagramGeneralization5.id, diagramGeneralization5);
            diagramObjects.put(diagramGeneralization6.id, diagramGeneralization6);
            */
            
		}

		//this.mouseXcl = mouseXcl;
		//this.mouseYcl = mouseYcl;
		//DiagramClass a1 = new DiagramClass(40, 40, "Class");
		//addToQueue(a1);
		
		/*addToQueue(new DiagramAssociation(a1, uc1));
		addToQueue(new DiagramAssociation(a1, uc2));
		addToQueue(new DiagramAssociation(a1, uc3));

		addToQueue(new DiagramAssociation(a2, uc4));

		addToQueue(new DiagramAssociation(a3, uc1));

		addToQueue(new DiagramDependency(uc2, uc5, DependencyStereotype.EXTEND));
		addToQueue(new DiagramDependency(uc2, uc6, DependencyStereotype.INCLUDE));

		addToQueue(new DiagramDependency(uc3, uc6, DependencyStereotype.INCLUDE));

		addToQueue(new DiagramGeneralization(a2, a1));*/

	}
	 public void addObjectsToQueue(){
         for (DiagramObject diagramObject : diagramObjects.values()) {
             System.out.println("Class: " + diagramObject);
             addToQueue(diagramObject);
         }
	}
	//@Override
	protected void internalDraw(Graphics canvas) {
		// canvas.drawRect(scaleX(0), scaleY(0), scale(getMaxX()),
		// scale(getMaxY()));
	}

	@Override
	protected double getMaxX() {
		return 700;
	}

	@Override
	protected double getMinX() {
		return 0;
	}

	@Override
	protected double getMaxY() {
		return 400;
	}

	@Override
	protected double getMinY() {
		return 0;
	}

}
