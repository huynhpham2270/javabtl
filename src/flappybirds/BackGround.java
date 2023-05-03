package flappybirds;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackGround {
	
	 private BufferedImage backGround;
	    
	    private int x1, y1, x2, y2;
	    
	    public BackGround(){
	        try {
	        	backGround= ImageIO.read(new File("Assets/background.png"));
	        } catch (IOException ex) {}
	        
	        x1 = 0;
	        y1 = 0;
	        x2 = x1+900;
	        y2 = 0;
	    }
	    
	    public void Update(){
	        x1-=2;
	        x2-=2;
	        
	        if(x2 < 0) x1 = x2+900;
	        if(x1 < 0) x2 = x1+900;
	    }
	    
	    public void Paint(Graphics2D g2){
	        g2.drawImage(backGround, x1, y1, null);
	        g2.drawImage(backGround, x2, y2, null);
	    }
	    
	    public int getYGround(){
	        return y1;
	    }
}
