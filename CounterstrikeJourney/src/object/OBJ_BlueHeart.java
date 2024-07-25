/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import entity.Entity;
import oneplayer.O_GamePanel;

/**
 *
 * @author LENOVO
 */
public class OBJ_BlueHeart extends Entity{
    
    O_GamePanel ogp;
    public static final String objName = "Trái tim bị thất lạc";
    
    public OBJ_BlueHeart(O_GamePanel ogp) {
        super(ogp);
        
        this.ogp = ogp;
        
        type = type_pickupOnly;
        name = objName;
        S1 = setup("/res/objects/blueheart", ogp.tileSizeW,ogp.tileSizeH);
        
        setDialogues();
        
    }
    
    public void setDialogues(){
        dialogues[0][0] = "Bạn đã nhận được Trái tim bị thất lạc";
        dialogues[0][1] = "Cuộc phiêu lưu của bạn đã hoàn thành!";
    }
    
    public boolean use(Entity entity){
        
        ogp.gameState = ogp.cutsceneState;
        ogp.csManager.sceneNum = ogp.csManager.ending;
        return true;
    }
    
}
