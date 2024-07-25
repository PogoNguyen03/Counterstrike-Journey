
package oneplayer;

import data.Progress;
import entity.Entity;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;


public class EventHandler {
    O_GamePanel ogp; 
    EventRect eventRect[][][];
    Entity eventMaster;
    int previousEventX, previousEventY; //biến trước khi sự kiện bắt đầu
    boolean canTouchEvent = true;//khi 1 sự kiện xảy ra thì nó sẽ k được xảy ra cho đến khi nhân vật rời khỏi sự kiện đó hoàn toàn
    int tempMap, tempCol, tempRow;
    
    public EventHandler(O_GamePanel ogp) { 
        this.ogp = ogp;
        
        eventMaster = new Entity(ogp);
        //Tạo ô event ở giữa tileSize
        eventRect = new EventRect[ogp.maxMap][ogp.maxWorldCol][ogp.maxWorldRow];
        
        int map = 0;
        int col = 0;
        int row = 0;
        while(map < ogp.maxMap && col < ogp.maxWorldCol && row< ogp.maxWorldRow){
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
        
            col++;
            if(col == ogp.maxWorldCol){
                col = 0;
                row++;
                
                if(row == ogp.maxWorldRow){
                    row = 0;
                    map++;
                }
            }
        }
        setDialogue();
    }
    
    public void setDialogue(){
        
        eventMaster.dialogues[0][0]= "Rớt xuống hố rồi!!";
        
        eventMaster.dialogues[1][0]= "Bạn đã hồi máu!"
                    +"\n(Đã lưu màn chơi)";
        eventMaster.dialogues[1][1]= "Tuyệt! Nước này hữu dụng đấy.";
    }
    
    public void checkEvent(){
        //Check player 
        int xDistance = Math.abs(ogp.player.worldX - previousEventX);
        int yDistance = Math.abs(ogp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if( distance > ogp.tileSizeH ){
            canTouchEvent = true;
        }
        if(canTouchEvent == true){
            if(hit(0,27,16,"D")==true){//vị trí event rớt hố
                
             damagePit(ogp.dialogueState);   
            }
            else if(hit(0,23,12,"W")==true ||
                     hit(0,24,12,"W")==true ||
                     hit(0,22,12,"W")==true
                     ){//vị trí event hồi máu
              healingPool(ogp.dialogueState);
            }    
            else if(hit(0,12,20,"any") == true) {teleport(1,12,13,ogp.indoor);}//cửa hàng
            else if(hit(1,12,13,"any") == true) {teleport(0,12,20,ogp.outside);}//cửa hàng
            else if(hit(1,12,9,"W") == true) {speak(ogp.npc[1][0]);}
            else if(hit(0,10,9,"any") == true) {
                teleport(2,9,41,ogp.dungeon);
                ogp.stopMusicSound();
                ogp.playMusic(22);
            }//hầm ngục 1
            else if(hit(2,9,41,"any") == true) {
                teleport(0,10,9,ogp.outside);
                ogp.stopMusicSound();
                ogp.playMusic(5);
            }//hầm ngục 1
            else if(hit(2,8,7,"any") == true) {teleport(3,26,41,ogp.dungeon);}//hầm ngục 2 
            else if(hit(3,26,41,"any") == true) {teleport(2,8,7,ogp.dungeon);}//hầm ngục 2
            else if(hit(3,25,27,"any") == true) {skeletonLord();}//boss
        }
         
        
    }
    public boolean hit(int map, int col, int row , String reqDirection){
        boolean hit = false; 
        
        if(map == ogp.currentMap){
            ogp.player.solidArea.x = ogp.player.worldX + ogp.player.solidArea.x;
            ogp.player.solidArea.y = ogp.player.worldY + ogp.player.solidArea.y;
            eventRect[map][col][row].x = col*ogp.tileSizeW + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*ogp.tileSizeH + eventRect[map][col][row].y;
            if(ogp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false){
                //EventDone xác nhận đã xảy ra sự kiện nhằm tránh TH chưa ra khỏi sự kiện đó đã tiếp tục khởi động sự kiện đó
                if(ogp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                        hit = true;
                        previousEventX = ogp.player.worldX;
                        previousEventY = ogp.player.worldY;
                    }
            }
           ogp.player.solidArea.x = ogp.player.solidAreaDefaultX;
           ogp.player.solidArea.y = ogp.player.solidAreaDefaultY;
           eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
           eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        
       return hit;
    }
    public void damagePit(int gameState){// đã xóa int col, int row,
        ogp.gameState = gameState;
//        ogp.ui.currentDialogue = "Rớt xuống hố rồi!!";
        eventMaster.startDialogue(eventMaster, 0);
        ogp.player.life -= 1;
//        eventRect[col][row].eventDone = true;
        canTouchEvent = false;

    }
    public void healingPool (int gameState){// đã xóa int col, int row,
        if(ogp.keyH.enterPressed == true){
            ogp.gameState = gameState;
            ogp.playSE(2);
            eventMaster.startDialogue(eventMaster, 1);
//            ogp.ui.currentDialogue = "Bạn đã hồi máu!"
//                    +"\n(Đã lưu màn chơi)";
            ogp.player.life = ogp.player.maxLife;
            ogp.player.attackCanceled = true;
            ogp.player.mana = ogp.player.maxMana;
            ogp.aSetter.setMonster();
            ogp.saveLoad.save();
        }

    }
    public void teleport(int map, int col, int row, int area){
        
        ogp.gameState = ogp.transitionState;
        ogp.nextArea = area;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        
//        ogp.currentMap = map;
//        ogp.player.worldX = ogp.tileSizeW * col;
//        ogp.player.worldY = ogp.tileSizeH * row;
//        previousEventX = ogp.player.worldX;
//        previousEventY = ogp.player.worldY;
        canTouchEvent = false;
        ogp.playSE(13);
    }
    
    public void speak(Entity entity){
        if(ogp.keyH.enterPressed == true){
            ogp.gameState = ogp.dialogueState;
            ogp.player.attackCanceled = true;
            entity.speak();
        }
    }
    
    public void skeletonLord(){
        
        if(ogp.bossBattleOn == false && Progress.skeletonLordDefeated == false){
            ogp.gameState = ogp.cutsceneState;
            ogp.csManager.sceneNum = ogp.csManager.skeletonLord;
        }
    }
}
