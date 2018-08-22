package com.imagetriplets.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.imagetriplets.DisplayImage;

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
	
	public void addImageMouseListener(Component component) {
		image.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getButton() == MouseEvent.BUTTON3) {
					FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());

					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(DisplayImage.imagesDirectory.toFile());
					fileChooser.setFileFilter(imageFilter);
					int result = fileChooser.showOpenDialog(component);
					if (result == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChooser.getSelectedFile();
						int l = (int) DisplayImage.imagesDirectory.toString().length() + 1;
						String filename = selectedFile.toString().substring(l);
						setImage(filename);
						DisplayImage.similarImage.setImage(null);
						DisplayImage.negativeImage.setImage(null);
					}            	
				}
			}
		});
	}
}
