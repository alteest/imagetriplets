package com.imagetriplets.components;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.imagetriplets.DisplayImage;

public class TripletsTableListSelectionListener implements ListSelectionListener {

	private JTable table;
		
	public TripletsTableListSelectionListener(JTable table) {
		this.table = table;
	}
		
	@Override
	public void valueChanged(ListSelectionEvent event) {
		if (event.getValueIsAdjusting() && (!DisplayImage.isEditMode())) {
		
			int row = table.getSelectedRow();
			if (row > -1) {
				String name = (String) table.getValueAt(row, 0);
				String similar = (String) table.getValueAt(row, 1);
				String negative = (String) table.getValueAt(row, 2);
				DisplayImage.image.setImage(name);
				DisplayImage.similarImage.setImage(similar);
				DisplayImage.negativeImage.setImage(negative);
			}
		}
	}
}
