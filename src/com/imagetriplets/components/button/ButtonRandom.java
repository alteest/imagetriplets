package com.imagetriplets.components.button;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;

import com.imagetriplets.DisplayImage;

@SuppressWarnings("serial")
public class ButtonRandom extends JButton implements ActionListener {
	Random generator = new Random(); 
	
	public ButtonRandom() {
		super("Random");
        setMinimumSize(new Dimension(100, 40));
        setPreferredSize(new Dimension(100, 40));
        setMaximumSize(new Dimension(100, 40));
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		DisplayImage.negativeImage.setImage(getRandomFilename());
	}

	protected String getRandomFilename() {
		int index = generator.nextInt(DisplayImage.filenames.size());
		String filename = DisplayImage.filenames.get(index);
		if ((filename.equals(DisplayImage.image.getFilename())) ||
				(filename.equals(DisplayImage.similarImage.getFilename()))) {
			return getRandomFilename();
		}
		return filename;
	}
}
