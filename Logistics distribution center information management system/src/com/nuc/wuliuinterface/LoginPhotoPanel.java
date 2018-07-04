package com.nuc.wuliuinterface;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


class LoginPhotoPanel extends JPanel{

	private static final long serialVersionUID = 1L;//eclipse¼ÓµÄ
	
	protected ImageIcon icon = new ImageIcon("photo/zbdx.png");
	public int width = icon.getIconWidth();
	public int height = icon.getIconHeight();
	public LoginPhotoPanel() {
		super();
		setSize(width, height);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img = icon.getImage();
		g.drawImage(img, 0, 0, icon.getIconWidth()
				,icon.getIconHeight(), icon.getImageObserver());
	}
}