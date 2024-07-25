/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import oneplayer.O_GamePanel;

/**
 *
 * @author LENOVO
 */
public class PlayerDummy extends Entity{
    
    public static final String npcName = "Dummy";
    
    public PlayerDummy(O_GamePanel ogp) {
        super(ogp);
        
        name = npcName;
        getImageP1();
    }
    
    public void getImageP1() {
            this.A1 = setup("/res/player/Character1_A", ogp.tileSizeW,ogp.tileSizeH);
            this.A2 = setup("/res/player/Character1_A1", ogp.tileSizeW,ogp.tileSizeH);
            this.A3 = setup("/res/player/Character1_A", ogp.tileSizeW,ogp.tileSizeH);
            this.A4 = setup("/res/player/Character1_A2", ogp.tileSizeW,ogp.tileSizeH);

            this.AW1 = setup("/res/player/Character1_WA", ogp.tileSizeW,ogp.tileSizeH);
            this.AW2 = setup("/res/player/Character1_WA1", ogp.tileSizeW,ogp.tileSizeH);
            this.AW3 = setup("/res/player/Character1_WA", ogp.tileSizeW,ogp.tileSizeH);
            this.AW4 = setup("/res/player/Character1_WA2", ogp.tileSizeW,ogp.tileSizeH);

            this.D1 = setup("/res/player/Character1_D", ogp.tileSizeW,ogp.tileSizeH);
            this.D2 = setup("/res/player/Character1_D1", ogp.tileSizeW,ogp.tileSizeH);
            this.D3 = setup("/res/player/Character1_D", ogp.tileSizeW,ogp.tileSizeH);
            this.D4 = setup("/res/player/Character1_D2", ogp.tileSizeW,ogp.tileSizeH);
            
            
            this.SS1 = setup("/res/player/Character2_S", ogp.tileSizeW,ogp.tileSizeH);
            
            
            this.S1 = setup("/res/player/Character1_S", ogp.tileSizeW,ogp.tileSizeH);
            this.S2 = setup("/res/player/Character1_S1", ogp.tileSizeW,ogp.tileSizeH);
            this.S3 = setup("/res/player/Character1_S", ogp.tileSizeW,ogp.tileSizeH);
            this.S4 = setup("/res/player/Character1_S2", ogp.tileSizeW,ogp.tileSizeH);

            this.SA1 = setup("/res/player/Character1_SA", ogp.tileSizeW,ogp.tileSizeH);
            this.SA2 = setup("/res/player/Character1_SA1", ogp.tileSizeW,ogp.tileSizeH);
            this.SA3 = setup("/res/player/Character1_SA", ogp.tileSizeW,ogp.tileSizeH);
            this.SA4 = setup("/res/player/Character1_SA2", ogp.tileSizeW,ogp.tileSizeH);

            this.SD1 = setup("/res/player/Character1_SD", ogp.tileSizeW,ogp.tileSizeH);
            this.SD2 = setup("/res/player/Character1_SD1", ogp.tileSizeW,ogp.tileSizeH);
            this.SD3 = setup("/res/player/Character1_SD", ogp.tileSizeW,ogp.tileSizeH);
            this.SD4 = setup("/res/player/Character1_SD2", ogp.tileSizeW,ogp.tileSizeH);

            this.W1 = setup("/res/player/Character1_W", ogp.tileSizeW,ogp.tileSizeH);
            this.W2 = setup("/res/player/Character1_W1", ogp.tileSizeW,ogp.tileSizeH);
            this.W3 = setup("/res/player/Character1_W", ogp.tileSizeW,ogp.tileSizeH);
            this.W4 = setup("/res/player/Character1_W2", ogp.tileSizeW,ogp.tileSizeH);

            this.WD1 = setup("/res/player/Character1_WD", ogp.tileSizeW,ogp.tileSizeH);
            this.WD2 = setup("/res/player/Character1_WD1", ogp.tileSizeW,ogp.tileSizeH);
            this.WD3 = setup("/res/player/Character1_WD", ogp.tileSizeW,ogp.tileSizeH);
            this.WD4 = setup("/res/player/Character1_WD2", ogp.tileSizeW,ogp.tileSizeH);

    }
}
