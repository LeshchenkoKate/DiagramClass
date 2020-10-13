package src;


import java.awt.geom.Point2D;
import org.json.simple.JSONObject;
import src.DiagramObject;

/**
 * Base class for connection lines and arrows.
 */
public abstract class AbstractDiagramLink extends DiagramObject {
	 protected AbstractDiagramNode nFrom;
	    protected AbstractDiagramNode nTo;

	    protected AbstractDiagramNode getnFrom() {
	        return nFrom;
	    }


	protected AbstractDiagramNode getnTo() {
		return nTo;
	}
	
	Point2D.Double getClassBorder(double dX, double dY, AbstractDiagramNode abstractDiagramNode) {
		double dXFrom;

		if (dY == 0) {
            dXFrom = abstractDiagramNode.getWIDTH();
        } else {
            dXFrom = Math.min(abstractDiagramNode.getHEIGHT() * Math.abs(dX), abstractDiagramNode.getWIDTH() * Math.abs(dY))
                    / Math.abs(dY);
        }		dXFrom = Math.signum(dX) * dXFrom / 2;

		double dYFrom;
		if (dX == 0) {
			dYFrom = DiagramClass.CLASS_HEIGHT;
		} else {
			dYFrom = Math.min(DiagramClass.CLASS_WIDTH * Math.abs(dY), DiagramClass.CLASS_HEIGHT * Math.abs(dX))
					/ Math.abs(dX);
		}
		dYFrom = -Math.signum(dY) * dYFrom / 2;

		return new Point2D.Double(dXFrom, dYFrom);
	}
	
    protected JSONObject getJSON(){
        JSONObject elementDetails = new JSONObject();
        elementDetails.put("idstart", this.getnFrom().id);
        elementDetails.put("idend", this.getnTo().id);
        elementDetails.put("text", this.getCaption());
        elementDetails.put("shape", this.getClass().getName());
        elementDetails.put(("id"), this.id);
        return elementDetails;
    }

	void getArrowPoints(Point2D.Double from, Point2D.Double to, int[] abscissae, int[] ordinates) {
		final double tgAngle = 0.577; // tg 30 degr.
		final double arLength = 10;
		final double x1 = from.x;
		final double x2 = to.x;
		final double y1 = from.y;
		final double y2 = to.y;

		final double len = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

		double ax1;
		double ay1;
		double ax2;
		double ay2;

		if ((y2 != y1) && (x2 != x1)) {
			double k = -(x2 - x1) / (y2 - y1);
			double d = y1 - k * x1;
			double a = 1 + k * k;
			double b = k * d - x1 - k * y1;
			double c = x1 * x1 + d * d - 2 * d * y1 + y1 * y1 - tgAngle * tgAngle * len * len;
			if ((b * b - a * c) > 0) {
				ax1 = (-b - Math.sqrt(b * b - a * c)) / a;
				ax2 = (-b + Math.sqrt(b * b - a * c)) / a;
			} else {
				c = x1 * x1 + d * d - 2 * d * y1 + y1 * y1 + tgAngle * tgAngle * len * len;
				ax1 = (-b - Math.sqrt(b * b - a * c)) / a;
				ax2 = (-b + Math.sqrt(b * b - a * c)) / a;
			}
			ay1 = k * ax1 + d;
			ay2 = k * ax2 + d;
			ax1 = arLength * (ax1 - x2) / len + x2;
			ax2 = arLength * (ax2 - x2) / len + x2;
			ay1 = arLength * (ay1 - y2) / len + y2;
			ay2 = arLength * (ay2 - y2) / len + y2;
		} else {
			if (y1 == y2) {
				ax1 = x2 - Math.signum(x2 - x1) * arLength;
				ax2 = ax1;
				ay1 = y2 - arLength * tgAngle;
				ay2 = y2 + arLength * tgAngle;
			} else if (x1 == x2) {
				ay1 = y2 - Math.signum(y2 - y1) * arLength;
				ay2 = ay1;
				ax1 = x2 - arLength * tgAngle;
				ax2 = x2 + arLength * tgAngle;
			} else { // this should never happen
				return;
			}
		}

		abscissae[0] = scaleX(ax1);
		abscissae[1] = scaleX(x2);
		abscissae[2] = scaleX(ax2);

		ordinates[0] = scaleY(ay1);
		ordinates[1] = scaleY(y2);
		ordinates[2] = scaleY(ay2);
	}
}