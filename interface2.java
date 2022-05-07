package projeto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;



public class Interface2 implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField txtClassificador;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	
	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
	/*public void showi() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	public static void showi() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface2 window = new Interface2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public Interface2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 960, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("CLASSIFIER");
		lblNewLabel_1.setBounds(342, 65, 311, 90);
		lblNewLabel_1.setOpaque(false);
		lblNewLabel_1.setFont(new Font("Georgia Pro Cond", Font.PLAIN, 54));
		lblNewLabel_1.setForeground(Color.WHITE);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(276, 264, 451, 39);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Evaluate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileInputStream fi;
				try {
					textField_1.setText("");
					fi = new FileInputStream(new File ("classifier.txt"));
					ObjectInputStream oi=new ObjectInputStream(fi);
					classifier classi = (classifier)oi.readObject();
					fi.close();
					oi.close();
					String values = textField.getText().replaceAll("\\s","");
					String[] strValues = values.split(",");
					int[] intValues = new int[strValues.length];
					boolean haErro= false;
					System.out.println(Arrays.toString(strValues));
					if (strValues.length!=classi.markov[0].data.dom().length) {
						haErro=true;
						textField_1.setText("Error: incorrect data size" );
					}
					for(int i = 0; i < strValues.length && !haErro; i++) {
					    try {
					    	intValues[i] = Integer.parseInt(strValues[i]);
					    	System.out.println(strValues[i] + "= "+intValues[i]);
					    	if (intValues[i] > classi.markov[0].data.dom()[i]) {
					    		haErro=true;
					    		textField_1.setText("Error: data exceeds the domain" );
					    	}
					    } catch (NumberFormatException nfe) {
					       // The string does not contain a parsable integer.
					    	textField_1.setText("Error: incorrect data type" );
					    	haErro=true;
					    }

					}
					System.out.println(Arrays.toString(intValues));
					if(!haErro) {
						int v = classi.Classify(intValues);
						textField_1.setText("You likely have "+ v + ". Have a good day! ;)" );
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(458, 341, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(276, 405, 451, 39);
		frame.getContentPane().add(textField_1);
		
		lblNewLabel_2 = new JLabel("Enter your values here: (no parenthesis and separated by commas)");
		lblNewLabel_2.setOpaque(false);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Georgia Pro Cond", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(276, 230, 451, 39);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Results:");
		lblNewLabel_3.setOpaque(false);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Georgia Pro Cond", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(276, 366, 219, 39);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("medicina-tecnologia.jpg"));
		lblNewLabel.setBounds(0, 0, 960, 640);
		frame.getContentPane().add(lblNewLabel);
		
		
		
		
		
	}
}
