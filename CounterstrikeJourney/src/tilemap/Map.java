/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tilemap;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import oneplayer.O_GamePanel;

/**
 *
 * @author LENOVO
 */
public class Map extends TileManager{
    
    O_GamePanel ogp;
    BufferedImage worldMap[];
    public  boolean miniMapOn = false;
    
    public Map(O_GamePanel ogp) {
        super(ogp);
        this.ogp = ogp;
        createWorldMap();
    }
    
    public void createWorldMap(){
        worldMap = new BufferedImage[ogp.maxMap];
        int worldMapWidth = ogp.tileSizeW * ogp.maxWorldCol;
        int worldMapHeight = ogp.tileSizeH * ogp.maxWorldRow;
        
        for(int i = 0; i < ogp.maxMap; i++){
            
            worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight,BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = (Graphics2D)worldMap[i].createGraphics();
            
            int col = 0;
            int row = 0;
            
            while(col < ogp.maxWorldCol && row < ogp.maxWorldRow){
                int tileNum = mapTileNum[i][col][row];
                int x = ogp.tileSizeW * col;
                int y = ogp.tileSizeH * row;
                g2.drawImage(tile[tileNum].image, x, y, null);
                
                col++;
                if(col == ogp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            g2.dispose();
        }
    }
    
    public void drawFullMapScreen(Graphics2D g2){
        
        //Background Color
        g2.setColor(Color.black);
        g2.fillRect(0, 0, ogp.screenWidth, ogp.screenHeight);
        
        //Draw Map
        int width = 500;
        int height = 500;
        int x = ogp.screenWidth/2 - width/2;
        int y = ogp.screenHeight/2 - height/2;
        g2.drawImage(worldMap[ogp.currentMap], x, y, width, height,null);
        
        //Draw Player
        double scale = (double)(ogp.tileSizeH * ogp.maxWorldCol)/width;
        int playerX = (int)(x + ogp.player.worldX/scale);
        int playerY = (int)(y  + ogp.player.worldY/scale);
        int playerSize = (int)(ogp.tileSizeW/scale);
        g2.drawImage(ogp.player.S1, playerX, playerY,playerSize, playerSize, null);
        
        //Hint
        g2.setFont(ogp.ui.maruMonica.deriveFont(20f));
        g2.setColor(Color.white);
        g2.drawString("Press M to closs", 750, 550);
    }
    public void drawMiniMap(Graphics2D g2){
        
        if(miniMapOn == true){
            
            //Draw Map
            int width = 200;
            int height = 200;
            int x = ogp.screenWidth - width - 50;
            int y = 50;
            
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            g2.drawImage(worldMap[ogp.currentMap],x, y, width, height,null);
            
            //Draw Player
            double scale = (double)(ogp.tileSizeH * ogp.maxWorldCol)/width;
            int playerX = (int)(x + ogp.player.worldX/scale);
            int playerY = (int)(y  + ogp.player.worldY/scale);
            int playerSize = (int)(ogp.tileSizeW/scale);
            g2.drawImage(ogp.player.S1, playerX, playerY,playerSize, playerSize, null);
            
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }
}
