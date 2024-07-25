

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import oneplayer.O_GamePanel;

public class Particle extends Entity{
    
    Entity generator;
    Color color;
    int size;
    int xd;
    int yd;
    
    public Particle(O_GamePanel ogp, Entity generator, Color color, int size, int speed, int maxLife, int xd, int yd) {
        super(ogp);
        
        this.color = color;
        this.size = size;
        this.speed = speed;
        this.maxLife = maxLife;
        this.xd = xd;
        this.yd = yd;
        
        life = maxLife;
        int offset = (ogp.tileSizeW/2) - (size/2);
        worldX = generator.worldX + offset;
        worldY = generator.worldY + offset;
    }
    
    public void update(){
        life--;
        
        if(life < maxLife/3){
            yd++;
        }
        
        worldX += xd*speed;
        worldY += yd*speed;
        
        if(life == 0){
            alive = false;
        }
    }
    
    public void draw(Graphics2D g2){
        int screenX = worldX - ogp.player.worldX + ogp.player.screenX;
        int screenY = worldY - ogp.player.worldY + ogp.player.screenY;
        
        g2.setColor(color);
        g2.fillRect(screenX, screenY, size, size);
    }
    
}
