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
public class OBJ_Door_Iron extends Entity{
    
    O_GamePanel ogp;
    
    public static final String objName = "Iron Door";
    
    public OBJ_Door_Iron(O_GamePanel ogp) {
        super(ogp);
        this.ogp = ogp;
        
        type = type_obstacle;
        name = objName;
        S1 = setup("/res/objects/door_iron", ogp.tileSizeW,ogp.tileSizeH);
        collision = true;
        
        solidArea.x = 0;
        solidArea.y = 30;
        solidArea.width  = 48;
        solidArea.height  = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        setDialogue();
    }
    
    public void setDialogue(){
        dialogues[0][0] = "Cửa sắt không thể nhúc nhích.";
    }
    
    public void interact(){
        
        startDialogue(this,0);
//        ogp.gameState = ogp.dialogueState;
//        ogp.ui.currentDialogue = "Bạn cần chìa khóa để mở cửa";
    }
}
