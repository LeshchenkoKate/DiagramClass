package src;


import java.awt.Graphics;
import java.awt.geom.Point2D;

/**
 * Actor-UseCase association line.
 */
class DiagramAssociation extends AbstractDiagramLink {
	private final AbstractDiagramNode nFrom;
	private final AbstractDiagramNode nTo;

	DiagramAssociation(AbstractDiagramNode nFrom, AbstractDiagramNode nTo) {
		this.nFrom = nFrom;
		this.nTo = nTo;
	}

	//@Override
	protected void internalDraw() {
		final double dX = nTo.getmX() - nFrom.getmX();
		final double dY = nFrom.getmY() - nTo.getmY();

		final Point2D.Double from = getClassBorder(dX, dY, nFrom);
		final Point2D.Double to = getClassBorder(dX, dY, nTo);

		final double x1 = nFrom.getmX() + from.getX();
		final double y1 = nFrom.getmY() + from.getY();
		final double x2 = nTo.getmX() - to.getX();
		final double y2 = nTo.getmY() - to.getY();

		getCanvas().drawLine(scaleX(x1), scaleY(y1), scaleX(x2), scaleY(y2));
	}

}