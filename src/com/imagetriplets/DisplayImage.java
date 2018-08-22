package com.imagetriplets;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.imagetriplets.ao.TripletList;
import com.imagetriplets.components.TPanel;
import com.imagetriplets.components.TripletsTableListSelectionListener;
import com.imagetriplets.components.button.ButtonAdd;
import com.imagetriplets.components.button.ButtonNext;
import com.imagetriplets.components.button.ButtonRandom;
import com.imagetriplets.components.button.ButtonSave;

@SuppressWarnings("serial")
public class DisplayImage extends JFrame {
	
	//public static Path imagesDirectory = Paths.get("src", "images");
	public static Path imagesDirectory = Paths.get("C:", "img");
	
	public static ArrayList<String> filenames = new ArrayList<String>();
	public static TripletList triplets = new TripletList();
	
	public static JTable tripletsTable;
	public static ButtonSave saveButton;
	public static TPanel image = new TPanel(new ButtonAdd());
	public static TPanel similarImage = new TPanel(new ButtonNext());
	public static TPanel negativeImage = new TPanel(new ButtonRandom());

	static private JCheckBox editCheckBox = new JCheckBox("Edit mode");

	public DisplayImage() {
		addFiles(imagesDirectory.toString());
        initUI();
    }

    private void initUI() {       
        
        createLayout();

        setSize(1500,  900);
        setTitle("Triplets");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void createLayout() {
    	setLayout(new FlowLayout());
    	
        triplets.readFromCSV("src/triplets.csv");

        initTripletTable();

        JPanel leftPanel = new JPanel(new BorderLayout());

        JScrollPane tripletsScrollPane = new JScrollPane(tripletsTable); 
        tripletsScrollPane.setPreferredSize(new Dimension(360, 800));
        
        saveButton = new ButtonSave();
        
        leftPanel.add(editCheckBox, BorderLayout.NORTH);
        leftPanel.add(tripletsScrollPane, BorderLayout.CENTER);
        leftPanel.add(saveButton, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel(new BorderLayout());
        
        JPanel imagePanel = new JPanel(new BorderLayout());
        //imagePanel.setMinimumSize(new Dimension(1080, 400));
        //imagePanel.setPreferredSize(new Dimension(1080, 400));
        //imagePanel.setMaximumSize(new Dimension(1080, 400));

        image.addImageMouseListener(this);
        imagePanel.add(image, BorderLayout.WEST);
        imagePanel.add(similarImage, BorderLayout.CENTER);
        imagePanel.add(negativeImage, BorderLayout.EAST);

        JPanel controlPanel = new JPanel(new BorderLayout());
        
        rightPanel.add(imagePanel, BorderLayout.NORTH);
        rightPanel.add(controlPanel, BorderLayout.SOUTH);
        
//        JPanel tablePanel = new JPanel(new BorderLayout()); 
//        tablePanel.add(tripletsScrollPane, BorderLayout.WEST);
        
        JPanel panel = new JPanel(new BorderLayout()); 
        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.EAST);

        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.add(panel, BorderLayout.BEFORE_FIRST_LINE);
        
        add(outerPanel);
    }
    
    protected void addFiles(String directoryName) {
        File directory = new File(directoryName);
    	for (File file : directory.listFiles()) {
    		if (file.isFile()) {
    			filenames.add(file.getPath().substring((int) imagesDirectory.toString().length() + 1));
    		} else if (file.isDirectory()) {
    			addFiles(file.getAbsolutePath());
    		}
    	}
    }
    
    public static void initTripletTable() {
        Object[][] data = triplets.convert2Data();
    	String[] columnNames = {"Name","Original","Negative"}; 
    	DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        tripletsTable = new JTable(tableModel);
        tripletsTable.getColumnModel().getColumn(0).setPreferredWidth(120);
        tripletsTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        tripletsTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        tripletsTable.setAutoCreateRowSorter(true);
        tripletsTable.getSelectionModel().addListSelectionListener(new TripletsTableListSelectionListener(tripletsTable));
    }

    public static boolean isEditMode() {
    	return editCheckBox.isSelected();
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            DisplayImage ex = new DisplayImage();
            ex.setVisible(true);
        });
    }
}