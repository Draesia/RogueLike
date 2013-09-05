package game;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
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
	
	public Game()
	{
		addKeyListener(new AL());
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent mEvt) {
				sx = mEvt.getX();
				sy = mEvt.getY();
			}

		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent evt) {
				if(evt.getModifiers() != 16) return;
				int maxCenterX = Tile.SIZE*Level.sizeX - Frame.maxX() + 32;
				int maxCenterY = Tile.SIZE*Level.sizeY - Frame.maxY() + 32;
				int differenceX = (int) (((sx) - evt.getX())*1.5);
				int differenceY = (int) (((sy) - evt.getY())*1.5);
				sx = evt.getX();
				sy = evt.getY();

				if(cx+differenceX < 0) 
					cx = 0;
				else if(cx+differenceX > maxCenterX)
					cx = maxCenterX;
				else 
					cx = cx + differenceX;

				if(cy+differenceY < 0)
					cy = 0;
				else if(cy+differenceY > maxCenterY) 
					cy = maxCenterY;
				else
					cy = cy + differenceY;
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	private class AL extends KeyAdapter
	{
	}
}
