package com.imagetriplets.ao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.imagetriplets.DisplayImage;

public class TripletList {

	private ArrayList<Triplet> triplets;
	
	public TripletList() {
		this.triplets = new ArrayList<Triplet>();
	}
	
	public void readFromCSV(String filename) {
		File file = new File(filename);
		FileReader reader = null;
		try {
			reader = new FileReader(file);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		BufferedReader infile = new BufferedReader(reader);
		String line = "";
		try {
			boolean done = false;
			while (!done) {
				line = infile.readLine();
				if (line == null) {
					done = true;
				} else {
	               String[] tokens = line.trim().split(",");
	               if (tokens.length == 3) {
	            	   String name = tokens[0].trim();
	            	   String similar = tokens[1].trim();
	            	   String negative = tokens[2].trim();
	            	   Triplet triplet = new Triplet(name, similar, negative);
	            	   triplets.add(triplet);
	               }
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}		
	}
	
	public Object[][] convert2Data() {
		Object[][] data = new Object[triplets.size()][6];
		for (int i = 0; i < triplets.size(); i++) {
			data[i][0] = triplets.get(i).getName();
			data[i][1] = triplets.get(i).getSimilar();
			data[i][2] = triplets.get(i).getNegative();
		}
		return data;
	}
	
	public void add(String name, String similar, String negative) {
		triplets.add(new Triplet(name, similar, negative));
		
        DefaultTableModel tableModel = (DefaultTableModel) DisplayImage.tripletsTable.getModel();
        String[] data = new String[3];
        data[0] = name;
        data[1] = similar;
        data[2] = negative;
        tableModel.addRow(data);
        tableModel.fireTableDataChanged();
	}
	
	public void save() {
	    FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("test.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    for (Triplet triplet: triplets) {
	    	printWriter.println(triplet.getLine());
	    }
	    printWriter.close();
		
	}
}