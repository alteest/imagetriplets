package com.imagetriplets.components.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.imagetriplets.DisplayImage;

@SuppressWarnings("serial")
public class ButtonSave extends JButton implements ActionListener {
	
	public ButtonSave() {
		super("Save");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		DisplayImage.triplets.save();
	}
}
