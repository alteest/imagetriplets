package com.imagetriplets.components.button;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.imagetriplets.DisplayImage;

@SuppressWarnings("serial")
public class ButtonAdd extends JButton implements ActionListener {
	
	public ButtonAdd() {
		super("Add");
        setMinimumSize(new Dimension(100, 40));
        setPreferredSize(new Dimension(100, 40));
        setMaximumSize(new Dimension(100, 40));
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (DisplayImage.image.isSet() && DisplayImage.similarImage.isSet() && DisplayImage.negativeImage.isSet()) {
			DisplayImage.triplets.add(DisplayImage.image.getFilename(),
					DisplayImage.similarImage.getFilename(),
					DisplayImage.negativeImage.getFilename());
		}
	}

}
