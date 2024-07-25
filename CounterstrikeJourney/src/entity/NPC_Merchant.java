/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Random;
import object.OBJ_Axe;
import object.OBJ_Key;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;
import object.OBJ_lantern;
import oneplayer.O_GamePanel;

/**
 *
 * @author LENOVO
 */
public class NPC_Merchant extends Entity{
    public NPC_Merchant(O_GamePanel ogp) {
        super(ogp);
        direction = "S";
        speed = 0;
        getNPCImage();
        setDialogue();
        setItems();
    }

    public void getNPCImage() {

            this.A1 = setup("/res/NPC/merchant_down_1", ogp.tileSizeW,ogp.tileSizeH);
            this.A2 = setup("/res/NPC/merchant_down_2", ogp.tileSizeW,ogp.tileSizeH);

            this.D1 = setup("/res/NPC/merchant_down_1", ogp.tileSizeW,ogp.tileSizeH);
            this.D2 = setup("/res/NPC/merchant_down_2", ogp.tileSizeW,ogp.tileSizeH);

            this.S1 = setup("/res/NPC/merchant_down_1", ogp.tileSizeW,ogp.tileSizeH);
            this.S2 = setup("/res/NPC/merchant_down_2", ogp.tileSizeW,ogp.tileSizeH);

            this.W1 = setup("/res/NPC/merchant_down_1", ogp.tileSizeW,ogp.tileSizeH);
            this.W2 = setup("/res/NPC/merchant_down_2", ogp.tileSizeW,ogp.tileSizeH);

    }

    public void setDialogue() {
        dialogues[0][0] = "Chà chà, bạn cũng đã tìm được tôi rồi.\nTôi có vài hàng ngon. \nBạn có muốn đổi với tôi không?";
        dialogues[1][0] = "Nhớ ghé lại nha cục cưng!!!";
        dialogues[2][0] = "Bạn cần có đủ tiền để mua!";
        dialogues[3][0] = "Bạn không còn chỗ trống để chứa thêm \nnữa!";
        dialogues[4][0] = "Bạn không thể bán một mặt hàng được \ntrang bị!";
    }

    public void setItems(){
        
        inventory.add(new OBJ_Potion_Red(ogp));
        inventory.add(new OBJ_Key(ogp));
        inventory.add(new OBJ_Sword_Normal(ogp));
        inventory.add(new OBJ_Shield_Wood(ogp));
        inventory.add(new OBJ_Shield_Blue(ogp));
        inventory.add(new OBJ_Axe(ogp));
        inventory.add(new OBJ_Tent(ogp));
        inventory.add(new OBJ_lantern(ogp));
        
    }
    public void speak(){
        
        facePlayer();
//        super.speak();
        ogp.gameState = ogp.tradeState;
        ogp.ui.npc = this;
    }
}
