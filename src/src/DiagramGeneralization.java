package src;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Actor-Actor generalization arrow.
 */
class DiagramGeneralization extends AbstractDiagramLink implements LabelParent {

        private static final float STROKE_LEN = 10.0f;
        private final AbstractDiagramNode nFrom;
        private final AbstractDiagramNode nTo;
        private final String caption;

        private double ldX;
        private double ldY;

        DiagramGeneralization(AbstractDiagramNode nFrom, AbstractDiagramNode nTo, String caption) {
            this.nFrom = nFrom;
            this.nTo = nTo;
            this.caption = caption;

            if (caption != null)
                addToQueue(new DiagramLabel(this));
        }

        protected void internalDraw() {

            final double dX = nTo.getmX() - nFrom.getmX();
            final double dY = nFrom.getmY() - nTo.getmY();

            final Point2D.Double from = getClassBorder(dX, dY, nFrom);
            final Point2D.Double to = getClassBorder(dX, dY, nTo);

            System.out.println(from);
            System.out.println(to);

            final double x1 = nFrom.getmX() + from.getX();
            final double y1 = nFrom.getmY() + from.getY();
            final double x2 = nTo.getmX() - to.getX();
            final double y2 = nTo.getmY() - to.getY();

            int[] abscissae = new int[3];
            int[] ordinates = new int[3];
            getArrowPoints(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), abscissae, ordinates);
            getCanvas().drawLine(scaleX(x1), scaleY(y1), scaleX(x2), scaleY(y2));
            getCanvas().drawPolyline(abscissae, ordinates, 3);
            Graphics2D g2 = (Graphics2D) getCanvas();
            float[] dash1 = { STROKE_LEN };
            BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, STROKE_LEN, dash1,
                    0.0f);
            Stroke s = g2.getStroke();
            g2.setStroke(dashed);
            g2.drawLine(scaleX(x1), scaleY(y1), scaleX(x2), scaleY(y2));
            g2.setStroke(s);
        }

        @Override
        public String getText() {
            return caption;
        }

        @Override
        public double getLabelX() {
            return (nTo.getmX() + nFrom.getmX()) / 2 + ldX;
        }

        @Override
        public double getLabelY() {
            return (nTo.getmY() + nFrom.getmY()) / 2 + ldY - 2;
        }

        @Override
        public void setLabelX(double newX) {
            ldX = newX - (nTo.getmX() + nFrom.getmX()) / 2;
        }

        @Override
        public void setLabelY(double newY) {
            ldY = newY - (nTo.getmY() + nFrom.getmY()) / 2 + 2;
        }

    }