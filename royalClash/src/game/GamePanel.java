package game;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import states.GameStateManager;
import util.MouseHandle;

import java.awt.image.BufferedImage;


public class GamePanel extends JPanel implements Runnable{ // because GamePanel already extend JPanel so must use Runnable 
	
	public static int width;
	public static int height;
	public static int updateTimes;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage img;
	private Graphics2D g;
	
	private MouseHandle mouse;
	
	private GameStateManager gsm;
	
	public GamePanel(int width, int height) {
		this.width = width;
		this.height = height;
		setPreferredSize(new Dimension(width, height));
		setFocusable(true); 
		requestFocus(); //allow JPanel have input as soon as the JFrame is made;
	}
	
	public void addNotify() { // Called when JPanel is created
		super.addNotify(); // for input
		if(thread == null) {
			thread = new Thread(this, "GameThread");
			thread.start();
		}
	}
	
	public void init() {
		running = true ;
		
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) img.getGraphics();
		
		mouse = new MouseHandle(this);
		
		gsm = new GameStateManager();
	}
	
	public void run() {
		init();
		
		
		final double Game_Hertz = 60;//Hz   (how many update in a sec)
		final double TBU = 1000000000/Game_Hertz; // Time before update;
		
		final int MUBR = 5; // Must update before render;
		
		double lastUpdateTime = System.nanoTime();
		double lastRenderTime;
		
		final double Target_FPS = 60; //(how many frame in a sec);
		final double TTBR = 1000000000/Target_FPS; // Total time before render;
		
		int frameCount = 0;
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);
		
		while(running) { // better loop //update more times than render -> react fast
			
			double now = System.nanoTime();
			while(now - lastUpdateTime > TBU) {// update 5 times per frame if possible
				update();
				input(mouse);
				lastUpdateTime += TBU;
				updateTimes++;
			}
			
			if(now-lastUpdateTime > TBU) {
				lastUpdateTime = now-TBU;
			}
			input(mouse);
			render();
			draw();
			lastRenderTime = now;
			frameCount++;
			
			int thisSecond = (int) (lastUpdateTime / 1000000000); // reset the frameCount every sec;
			if(thisSecond > lastSecondTime) { // showing the fps;
				System.out.println("NEW Second " + thisSecond + " " + frameCount);
				frameCount = 0;
				lastSecondTime = thisSecond;
			}
			
			while(now - lastRenderTime < TTBR && now - lastUpdateTime < TBU) {// make sure the fps(render) and hz(fps) stay at 60
				Thread.yield();
				
				try {
					Thread.sleep(1);
				}catch(Exception e) {
					System.out.println("ERROR: yielding thread");
				}
				now = System.nanoTime();
			}
		}
		/*while(running) { // whatever loop(); // update times == render times react slow?
			input(mouse, key);
			update();
			lastUpdateTime = System.nanoTime();
			frameCount++;
			render();
			draw();
			int thisSecond = (int) (lastUpdateTime / 1000000000); // reset the frameCount every sec;
			if(thisSecond > lastSecondTime) {
				System.out.println("NEW Second " + thisSecond + " " + frameCount);
				frameCount = 0;
				lastSecondTime = thisSecond;
			}
			try {
				Thread.sleep(5);
			}catch(Exception e) {
				System.out.println("ERROR: yielding thread");
			}
		}*/
		// but I feel no difference XD
	}
	
	
	public void update() {
		gsm.update();
	}
	
	public void input(MouseHandle mouse) {
		gsm.input(mouse);
	}
	
	public void render() {
		if(g != null) {
			gsm.render(g);
		}
	}

	public void draw() {
		Graphics g2 = (Graphics) this.getGraphics();
		g2.drawImage(img, 0, 0, width, height, null);
		g2.dispose();
	}
}// graphic -> img -> screan;
