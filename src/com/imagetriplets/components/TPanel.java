package com.imagetriplets.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

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
					Path directory = Paths.get("src", "images");
					String absolutePath = new File(".", directory.toString()).getAbsolutePath();
					FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());

					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(directory.toFile());
					fileChooser.setFileFilter(imageFilter);
					int result = fileChooser.showOpenDialog(component);
					if (result == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChooser.getSelectedFile();
						String filename = selectedFile.getAbsolutePath().substring(absolutePath.length() - 1);
						setImage(filename);
						DisplayImage.similarImage.setImage(null);
						DisplayImage.negativeImage.setImage(null);
					}            	
				}
			}
		});
	}
}
