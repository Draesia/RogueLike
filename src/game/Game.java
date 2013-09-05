package game;

import game.Entities.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;



public class Game extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean[] mouseDown = new boolean[4];
	public static Image[] images = new Image[30];
	public static int cx, cy, sx, sy;
	public static Level l;
	public static Player p;
	
	public Game()
	{
		l = new Level(0);
		p = l.getPlayer();
		
		addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent mEvt) {
				sx = mEvt.getX();
				sy = mEvt.getY();
				System.out.println("KEY PRESSED: ");
			}

		});
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, Frame.maxX, Frame.maxY);
		repaint();
	}
}
