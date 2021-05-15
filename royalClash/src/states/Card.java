package states;

import java.awt.Color;
import java.awt.Graphics2D;
import game.GamePanel;

import util.MouseHandle;

public class Card extends GameStates {
 
 int x=100,y=100;
 int height=GamePanel.height,width=GamePanel.width;
 public boolean card1 = false;
 public boolean card2 = false;
 public boolean card3 = false;
 public boolean card4 = false;
 public boolean pressed = false;
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
  // TODO Auto-generated constructor stub
 }

 @Override
 public void update() {
  // TODO Auto-generated method stub
  
 }

 @Override
 public void input(MouseHandle mouse) {
  if(mouse.pressed()) {
   x = mouse.getX();
   y = mouse.getY();
   pressed = true;
  }
  if(mouse.pressed()==false) {
   card1=false;
   card2=false;
   card3=false;
   card4=false;
   pressed = false;
   
  }
  if(mouse.pressed()==true&&x>(width)*1/5&&x<(width)*2/5&&y>(height)*2/3&&y<(height)*3/4) {
   card1=true;
   cardSelected=true;
   usedCard=4;
  }
  else if(mouse.pressed()==true&&x>(width)*2/5&&x<(width)*3/5&&y>(height)*2/3&&y<(height)*3/4) {
   card2=true;
   cardSelected=true;
   usedCard=3;
  }
  else if(mouse.pressed()==true&&x>(width)*3/5&&x<(width)*4/5&&y>(height)*2/3&&y<(height)*3/4) {
   card3=true;
   cardSelected=true;
   usedCard=2;
  }
  else if(mouse.pressed()==true&&x>(width)*4/5&&x<(width)&&y>(height)*2/3&&y<(height)*3/4) {
   card4=true;
   cardSelected=true;
   usedCard=1;
   
  }
  if(mouse.pressed()==false&&cardSelected==true&&x>0&&x<(width)&&y<(height)*2/3&&y>0) {
   cardOrder(usedCard);
   System.out.println("[");
   for(int i=0;i<9;i++) {
    System.out.println(cardOrderArray[i]+" ");    
   }
   System.out.println("]");
   cardSelected=false;
  }
  
 }

 @Override
 public void render(Graphics2D g) {
  for(int i=1;i<6;i++) {
   switch (cardOrderArray[i]) {
    case 1:
     g.setColor(Color.red);
     break;
    case 2:
     g.setColor(Color.orange);
     break;
    case 3:
     g.setColor(Color.yellow);
     break;
    case 4:
     g.setColor(Color.green);
     break;
    case 5:
     g.setColor(Color.blue);
     break;
    case 6:
     g.setColor(Color.pink);
     break;
    case 7:
     g.setColor(Color.black);
     break;
    case 8:
     g.setColor(Color.gray);
     break;
   }
   g.fillRect((width)*(5-i)/5,(height)*2/3,(width)*1/5,(height)*1/4);
  }
  /*g.setColor(Color.BLUE);
  g.fillRect((width)*1/5,(height)*2/3,(width)*4/5,(height)*1/4);
  
  g.setColor(Color.RED);
  g.fillRect(0,(height)*2/3, (width)*1/5,(height)*1/4);*/
  
  g.setColor(Color.BLACK);
  g.drawLine(0,(height)*2/3,width,(height)*2/3);
  g.drawLine(0,(height)*11/12,width,(height)*11/12);
  
  g.drawLine(0,(height)*2/3,0,(height)*11/12);
  g.drawLine((width)*1/5,(height)*2/3,(width)*1/5,(height)*11/12);
  g.drawLine((width)*2/5,(height)*2/3,(width)*2/5,(height)*11/12);
  g.drawLine((width)*3/5,(height)*2/3,(width)*3/5,(height)*11/12);
  g.drawLine((width)*4/5,(height)*2/3,(width)*4/5,(height)*11/12);
  g.drawLine(width,(height)*2/3,width,(height)*11/12);
  
  if(card1||card2||card3||card4) {
   g.setColor(Color.yellow);
   g.fillOval(x-20, y-20, 40, 40);
  }
  else if(card2) {
   g.setColor(Color.yellow);
   g.fillOval(x-20, y-20, 40, 40);

  }
  else if(card3) {
   g.setColor(Color.yellow);
   g.fillOval(x-20, y-20, 40, 40);

  }
  else if(card4) {
   g.setColor(Color.yellow);
   g.fillOval(x-20, y-20, 40, 40);

  }
  
 }

}