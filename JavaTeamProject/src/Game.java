import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

import org.omg.Messaging.SyncScopeHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
public class Game extends JFrame {
	
	JLabel charter;
	JLabel socre_board;
	JLabel talk;
	JLabel life;
	String[] zz = {"1@천국1.png","1@천국2.png","1@천국3.png","1@천국4.png","1@천국5.png","2@지옥1.png","2@지옥2.png","2@지옥3.png","2@지옥4.png","2@지옥5.png"};
	static int socre = 0;
	static int life_n  = 3;
	String key;
	JFXPanel panel = new JFXPanel();
	String[] comment = new String[1260];
	static int count = 60;
	
	public Game() throws IOException {
		
		
		 
		Container c = this.getContentPane();
		c.setLayout(null);
		
		key = zz[(int) (Math.random() * 10)];
	    ImageIcon cimg  = new ImageIcon(key);
	    charter  = new JLabel(cimg);
	    charter.setLocation(700,300); // 위치지정
		charter.setSize(200,400); // 크기 지정 
	    add(charter);
	    
	    life = new JLabel("LIFE : ♥ ♥ ♥ ♥");
	    life.setBounds(1200,10,400,100);
	    life.setFont(new Font("", Font.PLAIN, 50));
	    add(life);
	    
	    socre_board = new JLabel("score : 0");
	    socre_board.setBounds(700,10,400,100);
	    socre_board.setFont(new Font("", Font.PLAIN, 50));
		add(socre_board);
	    
	    ImageIcon bimg  = new ImageIcon("background_gameplay.jpg");
	    JLabel background  = new JLabel(bimg);
	    background.setBounds(0, 0, 1600, 1000);
	    
	    
		JLabel cnt = new JLabel("60");
		cnt.setFont(new Font("", Font.PLAIN, 50));
		cnt.setBounds(800,50,100,100);
		add(cnt);
		Timer a = new Timer();
		TimerTask b = new TimerTask() {
			
			@Override
			public void run() {
				if(count == 0)
				{
					dispose();
					new End();
					Game.life_n = 3;
					Game.socre = 0;
				}
				else {
					count -=1 ;
					cnt.setText(count+"");
				}
				
			}
		};
		a.schedule(b, 6000,1000);

		BufferedReader file = new BufferedReader(new FileReader("comment.txt"));
		String data;
		int i = 0;
		
		while(true) {
	            String line = file.readLine();
	            if (line==null) break;
	            comment[i] = line;	            
	            i++;
	    }
		file.close();
		
		String asd =comment[(int) (Math.random() * 1259)];
		System.out.println(asd);
		talk = new JLabel(comment[(int) (Math.random() * 1259)]);
		talk.setFont(new Font("", Font.PLAIN, 30));
		talk.setBounds(600,100,1000,100);
		add(talk);
		add(background);
		
	    JFXPanel panel = new JFXPanel();	   
        Media m = new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/" + "BGM/playing.mp3");
        MediaPlayer p = new MediaPlayer(m);
        p.play();
        
        
		setTitle("신과 함께");
		setSize(1600,1000);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.addMouseListener(new MyMouseListener()); // 마우스리스너 
		this.addMouseMotionListener(new MyMouseListener()); // 모션리스너 
		
	}
	
	class MyMouseListener extends MouseAdapter implements MouseMotionListener{ 
		
	
		public void mouseDragged(MouseEvent e){ // 드래그일시
			int x = e.getX();
			int y = e.getY();
			if((charter.getX() < x && charter.getX()+200 > x) && (charter.getY()+80 < y && charter.getY()+370 > y))
			charter.setLocation(x-100, y-250); // 위치 조정

			if(charter.getX() < 100 )
			{	   
				Media m = new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/" + "BGM/heaven.mp3");
		        MediaPlayer p = new MediaPlayer(m);
		        p.play();
				Check (key.charAt(0) , '1');
			}
			else if(charter.getX() > 1300) 
			{	   
		        Media m = new Media("file:///" + System.getProperty("user.dir").replace('\\', '/') + "/" + "BGM/hell.mp3");
		        MediaPlayer p = new MediaPlayer(m);
		        p.play();
				Check (key.charAt(0) , '2');
			}
		}
	}
	
	public void Check (char a , char b) {
		if(a == b) {
			
			Game.socre += 100;
			socre_board.setText("socre : "+Game.socre);
			
		}
		else {
			if(Game.life_n == 0) {
				dispose();
				new End();
				Game.life_n = 3;
				Game.socre = 0;
			}
			else
				Game.life_n -= 1;
				if(life_n ==2)
					life.setText("LIFE : ♥ ♥ ♥");
				else if(life_n == 1)
					life.setText("LIFE : ♥ ♥");
				else
					life.setText("LIFE : ♥");
		}
		key = zz[(int) (Math.random() * 10)];
		charter.setIcon(new ImageIcon(key));
		charter.setLocation(700,300);
		talk.setText(comment[(int) (Math.random() * 1259)]);
	}
	
	public static void main(String[] args) {
		try {
			new Game();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
