package states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.GamePanel;

import util.MouseHandle;

public class Card extends GameStates {
 
 int x=100,y=100;
 int height=GamePanel.height,width=GamePanel.width;
 private int click = 0;
 public boolean card1 = false;
 public boolean card2 = false;
 public boolean card3 = false;
 public boolean card4 = false;
 public boolean pressed = false;
 private ArrayList<Image> pic = new ArrayList<Image>();
 int[] cardOrderArray = {0,1,2,3,4,5,6,7,8};//1:red 2:orange 3:yellow 4:green 5:blue 6:pink 7:black 8:gray
 int usedCard = 0;
 boolean cardSelected = false;
 
 
 public void cardOrder (int cardNum) {
  
  if(cardNum==1) {
   cardOrderArray[0] = cardOrderArray[1];
   cardOrderArray[1] = cardOrderArray[5];
   for(int i=5;i<8;i++) {
    cardOrderArray[i] = cardOrderArray[i+1];
   }
   cardOrderArray[8] = cardOrderArray[0];
   cardOrderArray[0] = 0;
  }
  if(cardNum==2) {
   cardOrderArray[0] = cardOrderArray[2];
   cardOrderArray[2] = cardOrderArray[5];
   for(int i=5;i<8;i++) {
    cardOrderArray[i] = cardOrderArray[i+1];
   }
   cardOrderArray[8] = cardOrderArray[0];
   cardOrderArray[0] = 0;
  }
  if(cardNum==3) {
   cardOrderArray[0] = cardOrderArray[3];
   cardOrderArray[3] = cardOrderArray[5];
   for(int i=5;i<8;i++) {
    cardOrderArray[i] = cardOrderArray[i+1];
   }
   cardOrderArray[8] = cardOrderArray[0];
   cardOrderArray[0] = 0;
  }
  if(cardNum==4) {
   cardOrderArray[0] = cardOrderArray[4];
   cardOrderArray[4] = cardOrderArray[5];
   for(int i=5;i<8;i++) {
    cardOrderArray[i] = cardOrderArray[i+1];
   }
   cardOrderArray[8] = cardOrderArray[0];
   cardOrderArray[0] = 0;
  }
 }
 
 public void draw (Graphics2D g) {
  
  
 }
 

 public Card(GameStateManager gsm) {
  super(gsm);
  pic.add(new ImageIcon("skeleton.jpg").getImage());
  pic.add(new ImageIcon("bomer.jpg").getImage());
  pic.add(new ImageIcon("dragon.jpg").getImage());
  pic.add(new ImageIcon("musketeer.jpg").getImage());
  pic.add(new ImageIcon("miner.jpg").getImage());
  pic.add(new ImageIcon("wood.jpg").getImage());
  pic.add(new ImageIcon("fireball.jpg").getImage());
  pic.add(new ImageIcon("barrel.jpg").getImage());
  // TODO Auto-generated constructor stub
 }

 @Override
 public void update() {
  // TODO Auto-generated method stub
  
 }

 @Override
 public void input(MouseHandle mouse) {
  if(mouse.clicked() > click) {
	  click++;
	  x = mouse.getX();
	  y = mouse.getY();
	  pressed = true;
	  if(x>(width)*1/5&&x<(width)*2/5&&y>(height)*2/3&&y<(height)*11/12) {
		   card1=true;
		   cardSelected=true;
		   usedCard=4;
	  }
	  else if(x>(width)*2/5&&x<(width)*3/5&&y>(height)*2/3&&y<(height)*11/12) {
		   card2=true;
		   cardSelected=true;
		   usedCard=3;
	  }
	  else if(x>(width)*3/5&&x<(width)*4/5&&y>(height)*2/3&&y<(height)*11/12) {
		   card3=true;
		   cardSelected=true;
		   usedCard=2;
	  }
	  else if(x>(width)*4/5&&x<(width)&&y>(height)*2/3&&y<(height)*11/12) {
		   card4=true;
		   cardSelected=true;
		   usedCard=1;
	  }
	  if(cardSelected==true&&x>0&&x<(width)&&y<(height)*2/3&&y>(height)/3&&cardOrderArray[usedCard] <= 4&&EnergeBar.useEnergy(cardOrderArray[usedCard])) {
		   cardOrder(usedCard);
		   System.out.println("wtf" + cardOrderArray[8]);
		   Main.UseCard(cardOrderArray[8],mouse.getMousePosition());
		   cardSelected=false;
	  }else if(cardSelected==true&&x>0&&x<(width)&&y<(height)*2/3&&y>0&&cardOrderArray[usedCard] > 4&&EnergeBar.useEnergy(cardOrderArray[usedCard])) {
		   cardOrder(usedCard);
		   System.out.println("wtf" + cardOrderArray[8]);
		   Main.UseCard(cardOrderArray[8],mouse.getMousePosition());
		   cardSelected=false;
	  }
  }
  if(mouse.pressed()==false) {
   card1=false;
   card2=false;
   card3=false;
   card4=false;
   pressed = false;
   
  }

  
 }

 @Override
 public void render(Graphics2D g) {
  for(int i=1;i<6;i++) {
   switch (cardOrderArray[i]) {
    case 1:
     g.drawImage(pic.get(0),(width)*(5-i)/5,(height)*2/3,(width)*1/5,(height)*1/4,null);
     break;
    case 2:
    g.drawImage(pic.get(1),(width)*(5-i)/5,(height)*2/3,(width)*1/5,(height)*1/4,null);
     break;
    case 3:
    g.drawImage(pic.get(2),(width)*(5-i)/5,(height)*2/3,(width)*1/5,(height)*1/4,null);
     break;
    case 4:
    g.drawImage(pic.get(3),(width)*(5-i)/5,(height)*2/3,(width)*1/5,(height)*1/4,null);
     break;
    case 5:
    g.drawImage(pic.get(4),(width)*(5-i)/5,(height)*2/3,(width)*1/5,(height)*1/4,null);
     break;
    case 6:
    g.drawImage(pic.get(5),(width)*(5-i)/5,(height)*2/3,(width)*1/5,(height)*1/4,null);
     break;
    case 7:
    g.drawImage(pic.get(6),(width)*(5-i)/5,(height)*2/3,(width)*1/5,(height)*1/4,null);
     break;
    case 8:
    g.drawImage(pic.get(7),(width)*(5-i)/5,(height)*2/3,(width)*1/5,(height)*1/4,null);
     break;
   }
  }
  /*g.setColor(Color.BLUE);
  g.fillRect((width)*1/5,(height)*2/3,(width)*4/5,(height)*1/4);
  
  g.setColor(Color.RED);
  g.fillRect(0,(height)*2/3, (width)*1/5,(height)*1/4);
  
  g.setColor(Color.BLACK);
  g.drawLine(0,(height)*2/3,width,(height)*2/3);
  g.drawLine(0,(height)*11/12,width,(height)*11/12);
  
  g.drawLine(0,(height)*2/3,0,(height)*11/12);
  g.drawLine((width)*1/5,(height)*2/3,(width)*1/5,(height)*11/12);
  g.drawLine((width)*2/5,(height)*2/3,(width)*2/5,(height)*11/12);
  g.drawLine((width)*3/5,(height)*2/3,(width)*3/5,(height)*11/12);
  g.drawLine((width)*4/5,(height)*2/3,(width)*4/5,(height)*11/12);
  g.drawLine(width,(height)*2/3,width,(height)*11/12);
  */
  
  if(cardSelected) {
	  g.setColor(Color.red);
	  g.fillRect((width)*(5-usedCard)/5,(height)*2/3,10,(height)*1/4);
	  g.fillRect((width)*(5-usedCard)/5,(height)*2/3,(width)/5,10);
	  g.fillRect((width)*(6-usedCard)/5-10,(height)*2/3,10,(height)*1/4);
	  g.fillRect((width)*(5-usedCard)/5,(height)*11/12-10,(width)/5,10);
  }
  
 }

}