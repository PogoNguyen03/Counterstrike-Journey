/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import oneplayer.O_GamePanel;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(O_GamePanel ogp) {
        super(ogp);
        direction = "S";
        speed = 1;
        
        dialogueSet = -1;
        
        getNPCImage();
        setDialogue();

    }

    public void getNPCImage() {

            this.A1 = setup("/res/NPC/Oldman_A", ogp.tileSizeW,ogp.tileSizeH);
            this.A2 = setup("/res/NPC/Oldman_A2", ogp.tileSizeW,ogp.tileSizeH);

            this.D1 = setup("/res/NPC/Oldman_D", ogp.tileSizeW,ogp.tileSizeH);
            this.D2 = setup("/res/NPC/Oldman_D2", ogp.tileSizeW,ogp.tileSizeH);

            this.S1 = setup("/res/NPC/Oldman_S", ogp.tileSizeW,ogp.tileSizeH);
            this.S2 = setup("/res/NPC/Oldman_S2", ogp.tileSizeW,ogp.tileSizeH);

            this.W1 = setup("/res/NPC/Oldman_W", ogp.tileSizeW,ogp.tileSizeH);
            this.W2 = setup("/res/NPC/Oldman_W2", ogp.tileSizeW,ogp.tileSizeH);

    }

    public void setDialogue() {
        dialogues[0][0] = "Xin chào, Windy!";
        dialogues[0][1] = "Bạn đến hòn đảo này để tìm kho báu?";
        dialogues[0][2] = "Tôi đã từng là một pháp sư giỏi nhưng bây giờ... "
                + "\ntôi đã quá già để tham gia vào chuyến phiêu lưu \nnày";
        dialogues[0][3] = "Chúc bạn may mắn";
        
        dialogues[1][0] = "Nếu bạn trở nên yếu đi.";
        dialogues[1][1] = "Thì vẫn có cách cho bạn cảm thấy tốt hơn. "
                + "\nTôi cũng chả biết sao lại vậy";
        dialogues[1][2] = "Nhưng mà, đừng làm gì quá sức nha.";
        
        dialogues[2][0] = "Tôi thiết nghĩ làm thế nào để mở cách cửa đó nhỉ";
    }

    @Override
    public void setAction() {
        
//        if(onPath == true){
//            
////            int goalCol = 11;
////            int goalRow = 9;
//            int goalCol = (ogp.player.worldX + ogp.player.solidArea.x)/ogp.tileSizeW;
//            int goalRow = (ogp.player.worldY + ogp.player.solidArea.y)/ogp.tileSizeH;
//            
//            searchPath(goalCol, goalRow);
//        }
//        else{
//            actionLockCounter++;
//            if (actionLockCounter == 120) {
//                Random random = new Random();
//                int i = random.nextInt(100) + 1;//from 1 to 100
//                if (i <= 25) {
//                    direction = "W";
//                } else if (i <= 50) {
//                    direction = "S";
//                } else if (i <= 75) {
//                    direction = "A";
//                } else if (i <= 100) {
//                    direction = "D";
//                }
//                actionLockCounter = 0;
//    //            if (i <= 25) {
//    //                direction = "W";
//    //            }
//    //            if (i > 25 && i <= 50) {
//    //                direction = "S";
//    //            }
//    //            if (i > 50 && i <= 75) {
//    //                direction = "A";
//    //            }
//    //            if (i > 75 && i <= 100) {
//    //                direction = "D";
//    //            }
////            }
//
//        }
            actionLockCounter++;
            if (actionLockCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(100) + 1;//from 1 to 100
                if (i <= 25) {
                    direction = "W";
                } else if (i <= 50) {
                    direction = "S";
                } else if (i <= 75) {
                    direction = "A";
                } else if (i <= 100) {
                    direction = "D";
                }
                actionLockCounter = 0;
    //            if (i <= 25) {
    //                direction = "W";
    //            }
    //            if (i > 25 && i <= 50) {
    //                direction = "S";
    //            }
    //            if (i > 50 && i <= 75) {
    //                direction = "A";
    //            }
    //            if (i > 75 && i <= 100) {
    //                direction = "D";
    //            }
            }
    }

    @Override
    public void speak() {
//        super.speak();
        
        facePlayer();
        startDialogue(this,dialogueSet);
        
        dialogueSet++;
        
        if(dialogues[dialogueSet][0] == null){
            

            dialogueSet--;
        }
        
        onPath = true;
    }
}
