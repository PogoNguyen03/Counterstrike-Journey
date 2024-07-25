/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiles_interactive;

import entity.Entity;
import java.awt.Color;
import oneplayer.O_GamePanel;

/**
 *
 * @author LENOVO
 */
public class IT_DryTree extends InteractiveTiles{
    
    O_GamePanel ogp;
    
    public IT_DryTree(O_GamePanel ogp, int col, int row) {
        super(ogp,col,row);
        this.ogp = ogp;
        
        this.worldX = ogp.tileSizeW * col;
        this.worldY = ogp.tileSizeH * row;
        
        S1 = setup("/res/tiles_interactive/drytree", ogp.tileSizeW,ogp.tileSizeH);
        destructible = true;
        life = 1;
    }
    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;
        
        if(entity.currentWeapon.type == type_axe){
            isCorrectItem = true;
        }
        
        return isCorrectItem;
    }
    public void playSE(){
        ogp.playSE(11);
    }
    public InteractiveTiles getDestroyedForm(){
        InteractiveTiles tile = new IT_Trunk(ogp,worldX/ogp.tileSizeW, worldY/ogp.tileSizeH);
        return tile;
    }
    public Color getParticleColor(){
        Color color = new Color(65,50,30);
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
}
