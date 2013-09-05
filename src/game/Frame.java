package game;

import game.Entities.Player;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class Frame {


	public static JFrame sframe;
	public static int maxX = 1408;
	public static int maxY = 768;

	public static void main(String[] args)
	{
		sframe = new JFrame("RogueLike");
		sframe.setSize(640+16, 640+32);
		sframe.getContentPane().setLayout(new BorderLayout(0, 0));
		

		BackgroundPanel backgroundPanel = new BackgroundPanel();
		sframe.getContentPane().add(backgroundPanel);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { sframe.dispose(); start(); } });
		sframe.getContentPane().add(btnStart, BorderLayout.SOUTH);
		sframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sframe.setVisible(true);
	}
	public static void start()
	{
		sframe = new JFrame("RogueLike");
		sframe.setSize(maxX+16, maxY+32);
		Game g = new Game();
		sframe.getContentPane().add(g);
		sframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sframe.setVisible(true);
		sframe.setResizable(true);
	}
	public static int maxX()
	{
		return sframe.getWidth();
	}
	public static int maxY()
	{
		return sframe.getHeight();
	}
}

class BackgroundPanel extends JPanel
{
	Image img;
	public BackgroundPanel()
	{
		img = Toolkit.getDefaultToolkit().getImage(Toolkit.getDefaultToolkit().getClass().getResource("/images/start.png"));
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
}
