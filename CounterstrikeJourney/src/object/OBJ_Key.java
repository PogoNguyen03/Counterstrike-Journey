// 
// Decompiled by Procyon v0.5.36
// 

package object;

import entity.Entity;
import oneplayer.O_GamePanel;

public class OBJ_Key extends Entity
{
    O_GamePanel ogp;
    
    public static final String objName = "Chìa khoá";
    
    public OBJ_Key(O_GamePanel ogp) {
        super(ogp);
        this.ogp = ogp;
        
        type = type_consumable;
        name = objName;
        S1 = setup("/res/objects/key", ogp.tileSizeW,ogp.tileSizeH);
        description = "[" + name + "]\nCó thể mở được cánh cửa.";
        price = 100;
        stackable = true;
        
        setDialogue();
    }
    
    public void setDialogue(){
        dialogues[0][0] = "Bạn dùng "+ name +" và mở cửa";
        dialogues[1][0] = "Bạn đang làm cái việc vô tri gì vậy???";
    }
    
    public boolean use(Entity entity){
        
//        ogp.gameState = ogp.dialogueState;
        
        int objIndex = getDetected(entity, ogp.obj, "Door");
        
        if(objIndex != 999){
            startDialogue(this, 0);
//            ogp.ui.currentDialogue = "Bạn dùng "+ name +" và mở cửa";
            ogp.playSE(3);
            ogp.obj[ogp.currentMap][objIndex] = null;
            return true;
        }
        else{
             startDialogue(this, 1);
//            ogp.ui.currentDialogue = "Bạn đang làm cái việc vô tri gì vậy???";
            return false;
        }
    }
}
 