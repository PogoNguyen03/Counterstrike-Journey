/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package environment;

import java.awt.Graphics2D;
import oneplayer.O_GamePanel;

/**
 *
 * @author LENOVO
 */
public class EnviromentManager {
    O_GamePanel ogp;
    public Lighting lighting;

    public EnviromentManager(O_GamePanel ogp) {
        this.ogp = ogp;
        
        
    }
    
    public void setup(){
        
        lighting = new Lighting(ogp);
    }
    
    public void update(){
        
        lighting.update();
    }
    
    public  void draw(Graphics2D g2){
        
        lighting.draw(g2);
    }
}
