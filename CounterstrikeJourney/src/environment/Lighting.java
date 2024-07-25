/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package environment;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;
import oneplayer.O_GamePanel;

/**
 *
 * @author LENOVO
 */
public class Lighting {
    O_GamePanel ogp;
    BufferedImage darknessFilter;
    int dayCounter;
    public float filterAlpha = 0f;
    
    //Day State
    public final int day = 0;
    public final int dusk = 1;
    public final int night = 2;
    public final int dawn = 3;
    public int dayState = day;

    public Lighting(O_GamePanel ogp) {

        this.ogp = ogp;
        setLightSource();
    }
    
    public void setLightSource(){
        
        //create a buffered image
        darknessFilter = new BufferedImage(ogp.screenWidth, ogp.screenHeight,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();
        
        if(ogp.player.currentLight == null){
            g2.setColor(new Color(0,0,0,0.97f));
        }
        else{
            
            //get the center x and y of ther light circle
            int centerX = ogp.player.screenX + (ogp.tileSizeW)/2;
            int centerY = ogp.player.screenY + (ogp.tileSizeH)/2;
               
        
            //create a gradation effect eithin the light circle
            Color color[] = new Color[12];
            float fraction[] = new float[12];

            color[0] = new Color(0,0,0,0.1f);
            color[1] = new Color(0,0,0,0.42f);
            color[2] = new Color(0,0,0,0.52f);
            color[3] = new Color(0,0,0,0.61f);
            color[4] = new Color(0,0,0,0.69f);
            color[5] = new Color(0,0,0,0.76f);
            color[6] = new Color(0,0,0,0.82f);
            color[7] = new Color(0,0,0,0.87f);
            color[8] = new Color(0,0,0,0.91f);
            color[9] = new Color(0,0,0,0.92f);
            color[10] = new Color(0,0,0,0.93f);
            color[11] = new Color(0,0,0,0.94f);


            fraction[0] = 0f;
            fraction[1] = 0.4f;
            fraction[2] = 0.5f;
            fraction[3] = 0.6f;
            fraction[4] = 0.65f;
            fraction[5] = 0.7f;
            fraction[6] = 0.75f;
            fraction[7] = 0.8f;
            fraction[8] = 0.85f;
            fraction[9] = 0.9f;
            fraction[10] = 0.95f;
            fraction[11] = 1f;


            //create a gradation paint settings for the light circle
            RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, ogp.player.currentLight.lightRadius,fraction,color);

            //set the gradient data on g2
            g2.setPaint(gPaint);

        }
        
        g2.fillRect(0, 0, ogp.screenWidth, ogp.screenHeight);
        
        g2.dispose();
    }
    
    public void resetDay(){
        dayState = day;
        filterAlpha = 0f;
    }
    
    public void update(){
        if(ogp.player.lightUpdate == true){
            setLightSource();
            ogp.player.lightUpdate = false; 
        }
        
        //Check ngày đêm trong game
        //có thể thay đổi thời gian ngày đêm trong game tùy chỉnh
        if(dayState == day){
            
            dayCounter++;
            
            if(dayCounter > 3600){// <--- ở đây chu kỳ ngày đêm VD: 36000 = 10p
                dayState = dusk;
                dayCounter = 0;
            }
        }
        if(dayState == dusk){
            filterAlpha += 0.001f;// <---- ở đây là tốc độ chuyển đổi  VD: 0,0001 * 10000 = 1f và 10000/60 = 166s
            
            if(filterAlpha > 1f){
                filterAlpha = 1f;
                dayState = night;
            }
        }
        if(dayState == night){
            
            dayCounter++;
            
            if(dayCounter > 600){
                dayState = dawn;
                dayCounter = 0;
            }
        }
        if(dayState == dawn){
            
            filterAlpha -=0.001f;
            
            if(filterAlpha < 0f){
                filterAlpha = 0;
                dayState = day;
            }
        }
    }
    
    public void draw(Graphics2D g2){
        
        if(ogp.currentArea == ogp.outside){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        }
        if(ogp.currentArea == ogp.outside || ogp.currentArea == ogp.dungeon){
            g2.drawImage(darknessFilter, 0, 0, null);
        }
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        
        //DEBUG
        String situation = "";
        
        switch(dayState){
            case day: situation = "Ngày"; break;
            case dusk: situation = "Hoàng Hôn"; break;
            case night: situation = "Đêm"; break;
            case dawn: situation = "Bình Minh"; break;
        }
        g2.setColor(Color.black);
        g2.setFont(g2.getFont().deriveFont(50f));
        g2.drawString(situation, 752, 500);
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(50f));
        g2.drawString(situation, 750, 500);
        
    }
    
}
