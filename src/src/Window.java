package src;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;


	
	/**
	 * Create the frame.
	 */
	public Window() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we)
			{
				String ObjButtons[] = {"Yes","No"};
				int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?","Online Examination System",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
				if(PromptResult==JOptionPane.YES_OPTION)
				{
					System.exit(0);
				}
			}
		});
		setBounds(0, 0, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		Button panel = new Button();
		panel.setDiagramObject(new ClassDiagram(false));
		contentPane.add(panel);


		
		
			//ResultSet query = DBConection.statement.executeQuery("SELECT id, название FROM блюдо");
			//stmt = connection.createStatement();
			//System.out.println(connection);
			

		
	}

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);


					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
