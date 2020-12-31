package myswing2;

//import org.apache.*;
import java.io.File;
import java.io.IOException;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;

import org.apache.pdfbox.*;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceCharacteristicsDictionary;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public class Swingwindow {

	private JFrame frame;
	private static JTextField StatusShow;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					Swingwindow window = new Swingwindow();
					window.frame.setVisible(true);
					//String a=new File("SimpleForm.pdf").getAbsolutePath();
					//writeStatusText(a);
					//
					/*
					File file = new File("C:/W/java/repo/myjava/myswing2/SimpleForm.pdf");
					PDDocument document =PDDocument.load(file);
					PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
					document.addPage(new PDPage());
					document.save("SimpleForm.pdf");
					document.close(); 
					*/
					try (PDDocument document = PDDocument.load(new File("SimpleForm.pdf")))
			        {
			            PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
			            
			            // Get the field and the widget associated to it.
			            // Note: there might be multiple widgets
			            PDField field = acroForm.getField("SampleField");
			            PDAnnotationWidget widget = field.getWidgets().get(0);
			            
			            // Create the definition for a red border
			            PDAppearanceCharacteristicsDictionary fieldAppearance =
			                    new PDAppearanceCharacteristicsDictionary(new COSDictionary());
			            PDColor red = new PDColor(new float[] { 1, 0, 0 }, PDDeviceRGB.INSTANCE);
			            fieldAppearance.setBorderColour(red);

			            // Set the information to be used by the widget which is responsible
			            // for the visual style of the form field.
			            widget.setAppearanceCharacteristics(fieldAppearance);
			            
			            document.save("target/AddBorderToField.pdf");
			        }
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					
					//
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Swingwindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		Point test=new Point(0,0); 
		Dimension practice_btn_size = new Dimension(100,100);
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("新細明體", Font.PLAIN, 30));
		frame.getContentPane().setLayout(null);
		frame.setMaximumSize(new Dimension(1600, 1000));
		frame.setPreferredSize(new Dimension(1920, 1000));
		frame.setBounds(new Rectangle(0, 0, 3000, 1600));
		frame.setForeground(SystemColor.windowBorder);
		frame.setBackground(UIManager.getColor("Button.disabledForeground"));
		frame.setType(Type.POPUP);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Swingwindow.class.getResource("/myswing2/images.png")));
		//frame.setIconImage(icon.getImage());
		frame.setTitle("\u65E5\u6587\u8907\u7FD2/\u6E2C\u9A57");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1920, 1000);
		frame.setLocation(test);
		//JButton btnNewButton = new JButton("\u7DF4\u7FD2");
		JButton PracticeButton = new JButton("開始練習");
		PracticeButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				StatusShow.setText("按下BTN");
			}
		});
		PracticeButton.setFont(new Font("Dialog", Font.PLAIN, 15));
		PracticeButton.setSize(practice_btn_size);
		PracticeButton.setLocation(1800, 800);
		PracticeButton.setBounds(1650, 800, 100, 30);
		//frame.add(PracticeButton);
		frame.getContentPane().add(PracticeButton);
		
		StatusShow = new JTextField();
		StatusShow.setBounds(1325, 800, 300, 100);
		frame.getContentPane().add(StatusShow);
		StatusShow.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Dialog", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(1760, 800, 66, 30);
		frame.getContentPane().add(comboBox);
		
		JButton ExamButton = new JButton("\u958B\u59CB\u6E2C\u8A66");
		ExamButton.setFont(new Font("Dialog", Font.PLAIN, 15));
		ExamButton.setBounds(1650, 848, 100, 30);
		frame.getContentPane().add(ExamButton);
		
		JComboBox ExamWaitSec = new JComboBox();
		ExamWaitSec.setModel(new DefaultComboBoxModel(new String[] {"45", "60", "75", "90", "105", "120", "135", "150"}));
		ExamWaitSec.setBounds(1760, 848, 66, 30);
		frame.getContentPane().add(ExamWaitSec);
		
		JLabel lblNewLabel = new JLabel("\u6B21");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel.setBounds(1840, 805, 46, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u79D2");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(1840, 853, 46, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
	}
	
	private static void writeStatusText(String a)
	{
		StatusShow.setText(a);
	}

}
