package com.imagetriplets.components.button;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.imagetriplets.DisplayImage;

@SuppressWarnings("serial")
public class ButtonNext extends JButton implements ActionListener {
	
	public ButtonNext() {
		super("Next");
        setMinimumSize(new Dimension(100, 40));
        setPreferredSize(new Dimension(100, 40));
        setMaximumSize(new Dimension(100, 40));
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String filename = DisplayImage.similarImage.getFilename();
		if (filename == null) {
			filename = DisplayImage.image.getFilename();
		}
		DisplayImage.similarImage.setImage(getNextFilename(filename));
	}
	
	protected String getNextFilename(String filename) {
		int index = DisplayImage.filenames.indexOf(filename);
		if (index > -1) {
			index++;
			if (index == DisplayImage.filenames.size()) {
				index = 0;
			}
			String newfilename = DisplayImage.filenames.get(index);
			if (newfilename.equals(DisplayImage.image.getFilename())) {
				return getNextFilename(newfilename);
			}
			return newfilename;
		}
		return filename;
	}
}
