package projeto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JPanel;



public class Interface implements Serializable {
	
	private static final long serialVersionUID = 1L;
	JFrame frame;
	private JTextField txtClassificador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the application.
	 */
	public Interface() {
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
		
		JFileChooser fileChooser= new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV file","csv");
		fileChooser.setFileFilter(filter);
		
		JButton btnNewButton = new JButton("Choose dataset");
		btnNewButton.setBounds(328, 296, 292, 82);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = fileChooser.showOpenDialog((Component)e.getSource());
				if (returnValue==fileChooser.APPROVE_OPTION) {
					try {
					Dataset B = new Dataset(fileChooser.getSelectedFile().getAbsolutePath());
					int tam=B.dom().length-1;
					MRFT[] mrft= new MRFT[B.dom()[tam]+1];
					double[] freqs= new double[B.dom()[tam]+1];
					for (int i = 0; i <= B.dom()[tam]; i++) {
						mrft[i]=new MRFT(B.Fiber(i),0.2);
						freqs[i]= B.Fiber(i).Size();
						System.out.println(mrft[i].toString());
					}
					classifier classi = new classifier(mrft,freqs);
					FileOutputStream f= new FileOutputStream(new File ("classifier.txt"));
					ObjectOutputStream o = new ObjectOutputStream(f);
					o.writeObject(classi);
					o.close();
					f.close();
					frame.setVisible(false);
					Interface2 inter2 = new Interface2();
					inter2.showi();
				}catch(IOException e1) {
					e1.printStackTrace();
				}
			}
			}});
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Select an excel file");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(328, 268, 181, 31);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 960, 640);
		lblNewLabel.setIcon(new ImageIcon("medicina-tecnologia.jpg"));
		frame.getContentPane().add(lblNewLabel);
		
		
		
	}
}
