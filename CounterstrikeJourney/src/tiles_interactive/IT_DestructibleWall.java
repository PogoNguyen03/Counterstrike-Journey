/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiles_interactive;

import entity.Entity;
import java.awt.Color;
import java.util.Random;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import oneplayer.O_GamePanel;

/**
 *
 * @author LENOVO
 */
public class IT_DestructibleWall extends InteractiveTiles{
    
    O_GamePanel ogp;
    
    public IT_DestructibleWall(O_GamePanel ogp, int col, int row) {
        super(ogp,col,row);
        this.ogp = ogp;
        
        this.worldX = ogp.tileSizeW * col;
        this.worldY = ogp.tileSizeH * row;
        
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width  = 48;
        solidArea.height  = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        S1 = setup("/res/tiles_interactive/destructiblewall", ogp.tileSizeW,ogp.tileSizeH);
        destructible = true;
        life = 3;
    }
    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;
        
        if(entity.currentWeapon.type == type_pickaxe){
            isCorrectItem = true;
        }
        
        return isCorrectItem;
    }
    public void playSE(){
        ogp.playSE(20);
    }
    public InteractiveTiles getDestroyedForm(){
        InteractiveTiles tile = null;
        return tile;
    }
    public Color getParticleColor(){
        Color color = new Color(65,65,65);
        return color;
    }
    
    public int getParticleSize(){
        int size = 6; //6 pixels
        return size;
    }
    
    public int getParticleSpeed(){
        int speed = 1;
        return speed;
    }
    
    public int getParticleMaxLife(){
        int maxLife = 20;
        return maxLife;
    }
//    public void checkDrop(){
//        
//        // CAST A DIE
//        int i = new Random().nextInt(100)+1;
//        
//        // SET THE MONSTER DROP
//        //tỉ lệ rơi đồ 
//        if(i < 50){
//            dropItem(new OBJ_Coin_Bronze(ogp));
//        }
//        if(i >= 50 && i < 75){
//            dropItem(new OBJ_Heart(ogp));
//        }
//        if(i >= 75 && i < 100){
//            dropItem(new OBJ_ManaCrystal(ogp));
//        }
//    }
}
