/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oneplayer;

import entity.Entity;
import entity.PlayerDummy;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import monster.Mon_SkeletonLord;
import object.OBJ_BlueHeart;
import object.OBJ_Door_Iron;

/**
 *
 * @author LENOVO
 */
public class CutsceneManager {
    
    O_GamePanel ogp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    int counter = 0;
    float alpha = 0f;
    int y;
    
    //Scene Number
    public final int NA = 0;
    public final int skeletonLord = 1;
    public final int ending = 2;
    
    public CutsceneManager(O_GamePanel ogp) {
        this.ogp = ogp;
        
    }
    
    public void draw(Graphics2D g2){
        this.g2 = g2;
        
        switch(sceneNum){
            case skeletonLord: scene_SkeletoonLord();break;
            case ending: scene_ending(); break;
        }
    }
    
    public void scene_SkeletoonLord(){
        
        if(scenePhase == 0){
            ogp.bossBattleOn = true;
            
            //đong của sắt khi gpặ boss
            for( int i = 0; i < ogp.obj[1].length; i++){
                
                if(ogp.obj[ogp.currentMap][i] == null){
                    ogp.obj[ogp.currentMap][i] = new OBJ_Door_Iron(ogp);
                    ogp.obj[ogp.currentMap][i].worldX = ogp.tileSizeW*25;
                    ogp.obj[ogp.currentMap][i].worldY = ogp.tileSizeH*28;
                    ogp.obj[ogp.currentMap][i].temp = true;
                    ogp.playSE(21);
                    break;
                }
            }
            //Search a vacant slot for the dummy
            for(int i = 0; i < ogp.npc[1].length; i++){
                
                if(ogp.npc[ogp.currentMap][i] == null){
                    ogp.npc[ogp.currentMap][i] = new PlayerDummy(ogp);
                    ogp.npc[ogp.currentMap][i].worldX = ogp.player.worldX;
                    ogp.npc[ogp.currentMap][i].worldY = ogp.player.worldY;
                    ogp.npc[ogp.currentMap][i].direction = ogp.player.direction;
                    break;
                }
            }
            
            ogp.player.drawing = false;
            
            scenePhase++;
        }
        if(scenePhase == 1){
            
            ogp.player.worldY -=2;
            
            if(ogp.player.worldY < ogp.tileSizeH*16){
                scenePhase++;
            }
        }
        if(scenePhase == 2){
            
            //search the boss
            for(int i = 0; i < ogp.monster[1].length;i++){
                
                if(ogp.monster[ogp.currentMap][i] != null &&
                    ogp.monster[ogp.currentMap][i].name == Mon_SkeletonLord.monName){
                    ogp.monster[ogp.currentMap][i].sleep = false;
                    ogp.ui.npc = ogp.monster[ogp.currentMap][i];
                    scenePhase++;
                    break;
                }
            }
        }
        if(scenePhase == 3){
            
            //the boss speaks
            ogp.ui.drawDialogueScreen();
        }
        if(scenePhase == 4){
            
            //camera quay về người chơi
            
            //search the dummy
            for(int i = 0; i < ogp.npc[1].length; i++){
                
                if(ogp.npc[ogp.cutsceneState] != null && ogp.npc[ogp.currentMap][i].name == PlayerDummy.npcName){
                    
                    //restore the player positon
                    ogp.player.worldX = ogp.npc[ogp.currentMap][i].worldX;
                    ogp.player.worldY = ogp.npc[ogp.currentMap][i].worldY;
                    //Xóa dummy
                    ogp.npc[ogp.currentMap][i] = null;
                    break;
                }
            }
            
            //Star drawing the player
            ogp.player.drawing = true;
            
            //Reset
            sceneNum = NA;
            scenePhase = 0;
            ogp.gameState = ogp.playState;
            
            //change the music
            ogp.stopMusicSound();
            ogp.playMusic(22);
        }
    }
    public void scene_ending(){
        
        if(scenePhase == 0){
            
            ogp.stopMusic();
            ogp.ui.npc = new OBJ_BlueHeart(ogp);
            scenePhase++;
        }
        if(scenePhase == 1){
            
            //DIsplay dialogues
            ogp.ui.drawDialogueScreen();
        }
        if(scenePhase == 2){
            
            //Play the fanfare
            ogp.playSE(4);
            scenePhase++;
        }
        if(scenePhase == 3){
            
            //chờ sau khi hết nhạc và sau đó kết thúc
            if(counterReached(300) == true){
                scenePhase++;
            }
        }
        if(scenePhase == 4){
            
            //màn hình đen
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }
            drawBlackBackground(alpha);
            
            if(alpha == 1f){
                alpha = 0;
                scenePhase++;
            }
        }
        if(scenePhase == 5){
            
            drawBlackBackground(1f);
            
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }
            
            String text = "Đồ án Lập trình Java - Trò chơi đánh quái"
                    + "\n Thành viên nhóm: "
                    + "\nTrần Thy Bình "
                    + "\nNguyễn Cảnh Phong "
                    + "\nTrần Hải Đăng "
                    + "\nCảm ơn thầy cô vì đã chơi game ạ <3";
            drawString(alpha,38f, 100,text,70);
            
            if(counterReached(480) == true){
                scenePhase++;
            }
        }
        if(scenePhase ==  6){
            
            drawBlackBackground(1f);
            
            drawString(1f,120f, ogp.screenHeight/2, "Counterstrike Journey",40);
        }
        
    }
    
    public boolean counterReached(int target){
        
        boolean counterReached = false;
        
        counter++;
        if(counter > target){
            counterReached = true;
            counter = 0;
        }
        return counterReached;
    }
    
    public void drawBlackBackground(float alpha){
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.black);
        g2.fillRect(0, 0, ogp.screenWidth, ogp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        
    }
    
    public void drawString(float alpha, float fontSize,int y ,String text, int lineHeight){
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));
        
        for(String line: text.split("\n")){
            int x = ogp.ui.getXforCenteredText(line);
            g2.drawString(line, x, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
