package src;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContextFrame extends JFrame {


    public ContextFrame(String title, DiagramObject item, Canvas canvas) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.getContentPane().add(new ContextPanel((AbstractDiagramNode) item, canvas, this));
        this.pack();
        this.setLocation(0, 0);
        this.setSize(300, 200);
        this.setVisible(true);

    }


    private class ContextPanel extends JPanel implements ActionListener{

        private int oldX;
        private int oldY;
        JTextField tfield;

        private ContextPanel(AbstractDiagramNode node, Canvas canvas, ContextFrame frame) {

            oldX = (int)node.getmX();
            oldY = (int)node.getmY();

            setSize(new Dimension(300, 200));
            setLayout(null);



            TextField textFieldX = new TextField(String.valueOf((int)node.getmX()));
            TextField textFieldY = new TextField(String.valueOf((int)node.getmY()));
            TextField textFieldExpression = new TextField(node.getCaption());

            Label labelX = new Label("X: ");
            Label labelY = new Label("Y: ");
            Label labelExpression = new Label("Выражение: ");


            JPanel jPanelCoordinates = new JPanel();
            jPanelCoordinates.setLayout(new FlowLayout());
            jPanelCoordinates.add(labelX);
            jPanelCoordinates.add(textFieldX);
            jPanelCoordinates.add(labelY);
            jPanelCoordinates.add(textFieldY);

            JPanel jPanelExpression = new JPanel();
            jPanelExpression.setLayout(new FlowLayout());
            jPanelExpression.add(labelExpression);
            jPanelExpression.add(textFieldExpression);


            tfield = new JTextField();
            tfield.setEditable(true);
            tfield.setBounds(30,10, 200,30);
            add(tfield);


            JButton atribute1 = new JButton("Add attribute");
            atribute1.setBounds(10,50, 120,30);
            add(atribute1);



            JButton method1 = new JButton("Add method");
            method1.setBounds(140,50, 120,30);
            add(method1);
            method1.addActionListener(this);

            JButton rename = new JButton("Rename class");
            rename.setBounds(100,90, 120,30);
            add(rename);
            //rename.addActionListener(this);

            atribute1.addActionListener((ActionEvent e) -> {
                String s1=tfield.getText();
                String s2=ElementClass.attributes(s1);
                System.out.println(s2);
                node.setAttributes(s2);
                canvas.repaint();
            });

            method1.addActionListener((ActionEvent e) -> {
                String s1=tfield.getText();
                String s2=ElementClass.methods(s1);
                System.out.println(s2);
                node.setMethods(s2);
                canvas.repaint();
            });

            rename.addActionListener((ActionEvent e) -> {
                node.setCaption(tfield.getText());
                canvas.repaint();
            });





            /*textFieldX.addKeyListener(new KeyListener()  {

                @Override
                public void keyPressed(KeyEvent event) {
                }

                @Override
                public void keyTyped(KeyEvent event) {
                }

                @Override
                public void keyReleased(KeyEvent event) {
                    //тут связь с элемент класс
                    if (textFieldX.getText().equals("") || (textFieldX.getText().equals("-"))){
                        return;
                    }
                    else try{
                        node.move(Double.valueOf(textFieldX.getText()), Double.valueOf(textFieldY.getText()));
                        canvas.repaint();}
                    catch (NumberFormatException e){
                        textFieldX.setText(String.valueOf(oldX));
                    }
                }


            });

            textFieldY.addKeyListener(new KeyListener() {

                @Override
                public void keyPressed(KeyEvent event) {
                }

                @Override
                public void keyTyped(KeyEvent event) {
                }

                @Override
                public void keyReleased(KeyEvent event) {
                    if (textFieldY.getText().equals("") || (textFieldY.getText().equals("-")))
                    {
                        return;
                    }
                    else try {
                        node.move(Double.valueOf(textFieldX.getText()), Double.valueOf(textFieldY.getText()));
                        canvas.repaint();
                    } catch (NumberFormatException e) {
                        {
                            textFieldY.setText(String.valueOf(oldY));
                        }
                    }
                }
            });

            textFieldExpression.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent event) {
                }

                @Override
                public void keyReleased(KeyEvent event) {
                    node.setCaption(textFieldExpression.getText());
                    canvas.repaint();
                }

                @Override
                public void keyPressed(KeyEvent event) {
                }
            });




            if (node.getClass().getName().contains("Terminator")) textFieldExpression.setEditable(false);



            JPanel jpanelLinks = new JPanel();



            add(Box.createRigidArea(new Dimension(0, 25)));
            add(jPanelCoordinates);
            add(Box.createRigidArea(new Dimension(0, 25)));
            add(jPanelExpression);




            add(Box.createRigidArea(new Dimension(0, 0)));

            add(jpanelLinks);


        }*/


    }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }}
