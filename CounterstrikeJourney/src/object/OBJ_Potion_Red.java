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
public class OBJ_Potion_Red extends Entity{
    
    O_GamePanel ogp;
    
    public static final String objName = "Bình sinh lực";
    
    public OBJ_Potion_Red(O_GamePanel ogp){
        super(ogp);
    
        this.ogp = ogp;
        
        type = type_consumable;
        name = objName;
        value = 5;
        S1 = setup("/res/objects/potion_red",ogp.tileSizeW,ogp.tileSizeH);
        description = "[" + name + "]\nHồi phục sinh lực.";
        price = 25;
        stackable = true;
        
        setDialogue();
    }
    
    public void setDialogue(){
        dialogues[0][0] = "Bạn đang uống " + name + "!\n"
                + "Sinh lực hồi phục + " + value + ".";
    }
    
    public boolean use(Entity entity){
        startDialogue(this,0);
//        ogp.gameState = ogp.dialogueState;
//        ogp.ui.currentDialogue = "You drink the " + name + "!\n"
//                + "Your life has been recovered by " + value + ".";
        entity.life += value;
//        if(ogp.player.life > ogp.player.maxLife){
//            ogp.player.life = ogp.player.maxLife;
//        }
        ogp.playSE(2);
        return true;
    }
}
