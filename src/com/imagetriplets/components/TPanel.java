package com.imagetriplets.components;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class TPanel extends JPanel {
	
	private TImage image = new TImage("");
	private JLabel filename = new JLabel("");
	
	public TPanel() {
		super(new BorderLayout());
        image.setBorder(new LineBorder(Color.BLUE, 3));
		add(image, BorderLayout.NORTH);
		add(filename, BorderLayout.CENTER);
	}

	public TPanel(JButton button) {
		super(new BorderLayout());
        image.setBorder(new LineBorder(Color.BLUE, 3));
		add(image, BorderLayout.NORTH);
		add(filename, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
	}
	
	public String getFilename() {
		return image.getFilename();
	}
	
	public void setImage(String filename) {
		this.filename.setText(filename);
		image.setFilename(filename);
	}

	public boolean isSet() {
		return image.isSet();
	}
}
