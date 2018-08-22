package com.imagetriplets.components.button;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;

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
		String nextFilename = getNextFilename(filename);
		while (nextFilename == null) {
			nextFilename = getNextFilename(filename);
		}
		DisplayImage.similarImage.setImage(nextFilename);
	}
	
	private String getNextFilename(String filename) {
		int index = DisplayImage.filenames.indexOf(filename);
		if (index > -1) {
			Path f1 = Paths.get(DisplayImage.imagesDirectory.toString(), filename).getParent();
			String newfilename = null;
			while (newfilename == null) {
				index++;
				if (index == DisplayImage.filenames.size()) {
					index = 0;
				}
				newfilename = DisplayImage.filenames.get(index);
				if (newfilename.equals(DisplayImage.image.getFilename())) {
					newfilename = null;
				} else {
					Path f2 = Paths.get(DisplayImage.imagesDirectory.toString(), newfilename).getParent();
					if (!f1.equals(f2)) {
						newfilename = null;
					}
				}
			}
			return newfilename;
		}
		return filename;
	}
}
