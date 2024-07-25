/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiles_interactive;

import oneplayer.O_GamePanel;

/**
 *
 * @author LENOVO
 */
public class IT_Trunk extends InteractiveTiles{
    O_GamePanel ogp;
    public IT_Trunk(O_GamePanel ogp, int col, int row) {
        super(ogp,col,row);
        this.ogp = ogp;
        
        this.worldX = ogp.tileSizeW * col;
        this.worldY = ogp.tileSizeH * row;
        
        S1 = setup("/res/tiles_interactive/trunk", ogp.tileSizeW,ogp.tileSizeH);
        
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    
}
