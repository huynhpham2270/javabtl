package flappybirds;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.DefaultEditorKit.InsertContentAction;

import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.GameScreen;


public class FlappyBirds extends GameScreen{

    
	private static final long serialVersionUID = 1L;
	private BufferedImage birds;
    private Animation bird_anim;
    private Font font = new Font("Arial", Font.BOLD,40);
    private Font font1 = new Font("Arial", Font.BOLD,30);
    
    public static float g = 0.15f;
    
    private BackGround backGround;
    private Bird bird;
    private Ground ground;
        
    private ChimneyGroup chimneyGroup;
    
    public int score = 0;
    
    public String namePlayer="";
    
    private int BEGIN_SCREEN = 0;
    private int GAMEPLAY_SCREEN = 1;
    private int GAMEOVER_SCREEN = 2;
    private int CurrentScreen = BEGIN_SCREEN;
    
    private static final String URL = "jdbc:mysql://localhost:3306/score02"; 
    private static final String USERNAME = "root"; 
    private static final String PASSWORD = ""; 
    private JTextField scoreTextField;
    
    
    
    public FlappyBirds(){
        super(800,600);
        this.setLocationRelativeTo(null);
        try {
            birds = ImageIO.read(new File("Assets/bird_sprite.png"));
        } catch (IOException ex) {}
        
        bird_anim = new Animation(70);
        AFrameOnImage f;
        f = new AFrameOnImage(0,0,60,60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(60,0,60,60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(120,0,60,60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(60,0,60,60);
        bird_anim.AddFrame(f);
        
        bird = new Bird(350, 200, 50, 50);
        ground = new Ground();
        
        chimneyGroup = new ChimneyGroup(); 
        backGround = new BackGround();
        
        BeginGame();
        
    }   
    public static void main(String[] args) {
           
        try {
            // Tạo kết nối đến database
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Kiểm tra kết nối
            if (conn != null) {
                System.out.println("Kết nối thành công đến database");
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi kết nối đến database: " + ex.getMessage());
        }
        new FlappyBirds();
    }

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getNamePlayer() {
		return namePlayer;
	}


	public void setNamePlayer(String namePlayer) {
		this.namePlayer = namePlayer;
	}
	
	public void saveScore(String playerName, int score) {
		
	    try {
	        // Tạo kết nối đến database
	        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

	        // Thực hiện truy vấn để lưu kết quả
	        String sql = "INSERT INTO score (Name, Score) VALUES (?, ?)";
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, namePlayer);
	        statement.setInt(2, score);
	        statement.executeUpdate();

	        // Đóng kết nối
	        conn.close();
	    } catch (SQLException ex) {
	        System.out.println("Lỗi kết nối");
	    }
	}
	public void level() {
		// Hiển thị box để cho người chơi chọn độ khó
		String[] options = {"Dễ", "Trung bình", "Khó"};
		int choice = JOptionPane.showOptionDialog(this, "Chọn độ khó", "Độ khó", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		// Xử lý lựa chọn của người chơi
		switch (choice) {
			case 0: //  dễ
				g = 0.08f; // Cập nhật tốc độ rơi
				break;
			case 1: // trung bình
				g = 0.15f; 
				break;
			case 2: //  khó
				g = 0.2f; 
				break;
			default: 
				System.exit(0); // Thoát khỏi chương trình
				break;
		}
	}
	
	private void resetGame(){
        bird.setPos(350, 200);
        bird.setVt(0);
        bird.setLive(true);
        score = 0;
        chimneyGroup.resetChimneys();
    }
    
    @Override
    public void GAME_UPDATE(long deltaTime) {
        
        if(CurrentScreen == BEGIN_SCREEN){
            resetGame();
        }else if(CurrentScreen == GAMEPLAY_SCREEN){
            
            if(bird.getLive()) bird_anim.Update_Me(deltaTime);
            bird.update(deltaTime);
            ground.Update();
            backGround.Update();
            chimneyGroup.update();
            
            for(int i = 0;i<ChimneyGroup.SIZE;i++){
                if(bird.getRect().intersects(chimneyGroup.getChimney(i).getRect())){
                	if(bird.getLive()) bird.pubSound.play();
                    bird.setLive(false);
                    System.out.println("Set live = false");
                }
                    
                
            }
            
            for(int i = 0;i<ChimneyGroup.SIZE;i++){
                if(bird.getPosX() > chimneyGroup.getChimney(i).getPosX() && !chimneyGroup.getChimney(i).getIsBehindBird()
                        && i%2==0){
                    score++;                  
                    bird.tingSound.play();
                    chimneyGroup.getChimney(i).setIsBehindBird(true);
                }
                    
            }
            
            if(bird.getPosY() + bird.getH() > ground.getYGround()) 
            	{	
            	 
            	 if(bird.getLive()) bird.pubSound.play();
            	 
            	 namePlayer = JOptionPane.showInputDialog("Nhập tên của bạn:");
            	 scoreTextField = new JTextField(String.valueOf(score));
                 saveScore(USERNAME, score);
                 
                 CurrentScreen = GAMEOVER_SCREEN;
                
            	}
            
        }else{
            
        } 
        
    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {
    
        backGround.Paint(g2);
        
        chimneyGroup.paint(g2);
        
        ground.Paint(g2);
        
        if(bird.getIsFlying())
            bird_anim.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birds, g2, 0, -1);
        else 
            bird_anim.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birds, g2, 0, 0);
        
        
        if(CurrentScreen == BEGIN_SCREEN){
        	g2.setFont(font);
            g2.setColor(Color.red);
            g2.drawString("Nhấn Space để bắt đầu", 200, 350);
            
        }
        if(CurrentScreen == GAMEOVER_SCREEN){
        	g2.setFont(font);
            g2.setColor(Color.white);
            g2.drawString("Game Over", 270, 150);
            g2.setFont(font);
            g2.drawString("Score: "+ score, 300, 200);
            g2.setFont(font1);
            g2.drawString("Nhấn Space để trờ lại màn hình chính", 120, 250);
            
          
        }
        
       if(CurrentScreen == GAMEPLAY_SCREEN)
       {
    	   g2.setFont(font);
           g2.setColor(Color.white);
           g2.drawString(""+score, 400, 50);
       }
       else {
    	   
       }
    }

    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if(Event == KEY_PRESSED){
            
            if(CurrentScreen == BEGIN_SCREEN){
                level();
                CurrentScreen = GAMEPLAY_SCREEN;          
                
            }else if(CurrentScreen == GAMEPLAY_SCREEN){
                if(bird.getLive()) bird.fly();
            }else if(CurrentScreen == GAMEOVER_SCREEN){
                CurrentScreen = BEGIN_SCREEN;
            }
        }
    }
    
}
