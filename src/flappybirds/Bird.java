package flappybirds;


import java.awt.Rectangle;
import java.io.File;

import pkg2dgamesframework.Objects;
import pkg2dgamesframework.SoundPlayer;


public class Bird extends Objects{
    
    private float vt = 0;
    
    private boolean isFlying = false;

    private Rectangle rect;
    
    private boolean isLive = true;
   public SoundPlayer flappySound,tingSound,pubSound;
   
    
    public Bird(int x, int y, int w, int h){
        super(x, y, w, h);  
        rect = new Rectangle(x, y, w, h);
        flappySound = new SoundPlayer(new File("Assets/bay1.wav"));
        tingSound = new SoundPlayer(new File("Assets/bayqua.wav"));
        pubSound = new SoundPlayer(new File("Assets/chet.wav"));
        
    }
    
    public void setLive(boolean b){
        isLive = b;
     
        
    }
    
    public boolean getLive(){
        return isLive;
        
    }
    public Rectangle getRect(){
        return rect;
    }
    
    public void setVt(float vt){
        this.vt = vt;
    }
    public void update(long deltaTime){
        
        vt+=FlappyBirds.g;
        
        this.setPosY(this.getPosY()+vt);
        this.rect.setLocation((int) this.getPosX(),(int) this.getPosY());
        
        if(vt < 0) isFlying = true;
        else isFlying = false;
        
    }
    
    public void fly(){
        vt = (float)-2.5;
        flappySound.play();
       
    }
    
    
    public boolean getIsFlying(){
        return isFlying;
    }
}
