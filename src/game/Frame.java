package game;

import javax.swing.JFrame;

public class Frame {


	public static JFrame sframe;
	public static int maxX = 1440;
	public static int maxY = 720;
	public static void main(String[] args)
	{
		sframe = new JFrame("RogueLike");
		sframe.setSize(maxX, maxY);
		Game g = new Game();
		sframe.add(g);
		sframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sframe.setVisible(true);
		sframe.setResizable(true);
	}
	public static void start()
	{
		sframe = new JFrame("RogueLike");
		sframe.setSize(maxX, maxY);
		sframe.add(new Game());
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
