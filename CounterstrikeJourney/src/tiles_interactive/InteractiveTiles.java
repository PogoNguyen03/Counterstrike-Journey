/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiles_interactive;

import entity.Entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import oneplayer.O_GamePanel;

/**
 *
 * @author LENOVO
 */
public class InteractiveTiles extends Entity{
    
    O_GamePanel ogp;
    public boolean destructible = false;
    
    
    public InteractiveTiles(O_GamePanel ogp, int col, int row) {
        super(ogp);
        this.ogp = ogp;
    }
    
    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;
        return isCorrectItem;
    }
    public void playSE(){}
    public InteractiveTiles getDestroyedForm(){
        InteractiveTiles tile = null;
        return tile;
    }
    public void update(){
         if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 20){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    } 
    
    public void draw(Graphics2D g2){
        
        int screenX = worldX - ogp.player.worldX + ogp.player.screenX;
        int screenY = worldY - ogp.player.worldY + ogp.player.screenY;
        
        if (worldX + ogp.tileSizeW > ogp.player.worldX - ogp.player.screenX
                && worldX - ogp.tileSizeW < ogp.player.worldX + ogp.player.screenX
                && worldY + ogp.tileSizeH > ogp.player.worldY - ogp.player.screenY
                && worldY - ogp.tileSizeH < ogp.player.worldY + ogp.player.screenY){
            g2.drawImage(S1, screenX, screenY, null);
        }
    }
}
