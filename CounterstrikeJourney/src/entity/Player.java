package entity;

import oneplayer.O_GamePanel;
import TwoPlayer.T_GamePanel;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_Rock;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import oneplayer.KeyHandler;
import oneplayer.UtililyTool;

/**
 *
 * @author DELL
 */
public final class Player extends Entity {

    T_GamePanel gp;
    O_GamePanel ogp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;// Số chìa khoá hiện tại player có
    int standCounter = 0;
    public boolean attackCanceled;
    public boolean lightUpdate = false;

    public Player(O_GamePanel ogp, KeyHandler keyH) {
        super(ogp);
        this.ogp = ogp;
        this.keyH = keyH;
        screenX = ogp.screenWidth / 2 - (ogp.tileSizeW / 2);
        screenY = ogp.screenHeight / 2 - (ogp.tileSizeH / 2);
        solidArea = new Rectangle();//15x35
        solidArea.x = 8;
        solidArea.y = 20;
        solidArea.width = 32;
        solidArea.height = 32;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
//        attackArea.width = 36;
//        attackArea.height = 36;

        
        setDefaultValuesP1();
        //dời vào setdefaultvaluesp1
//        getImageP1();
//        getAttackP1();
//        getGuardImage();
//        setItems();
    }

    public void setDefaultValuesP1() {
        worldX = ogp.tileSizeW * 23;
        worldY = ogp.tileSizeH * 21;
//        worldX = ogp.tileSizeW * 12;
//        worldY = ogp.tileSizeH * 13;
        name = "Windy";
        defaultSpeed = 4;
        speed = defaultSpeed;
        direction = "S";
     
        //PLAYER STATUS
        maxLife=3;
        life=maxLife;//1 mạng = nửa tim & 2 mạng= 1 tim
        maxMana = 4;
        mana = maxMana;
        ammo = 10;
        level = 1;
        strength = 5; //càng khoẻ đánh càng đau
        dexterity = 1; // càng khéo léo thì càng thủ mạnh
        exp = 0;
        nextLevelExp = 5;
        coin = 100;
        currentWeapon = new OBJ_Sword_Normal(ogp);
        currentShield = new OBJ_Shield_Wood(ogp);
        currentLight = null;
        projectile = new OBJ_Fireball(ogp);
        //projectile = new OBJ_Rock(ogp);
        
        attack = getAttack();// tổng giá trị tấn công được quyết định bởi strength và dame of weapon
        defense = getDefense(); //tổng giá trị phòng thủ quyết định bởi sự khéo léo và chỉ số từ khiên
        
        getImageP1();
        getAttackP1();
        getGuardImage();
        setItems();
        setDialogue();
    }
    //khởi tạo lại vị trí khi quay lại
    public void setDefaultPositions(){
        
        ogp.currentMap = 0;
        worldX = ogp.tileSizeW * 23;
        worldY = ogp.tileSizeH * 21;
        direction = "S";
    }
    
    public void setDialogue(){
        dialogues[0][0] = "Đã lên cấp " + level + "\n"
                    + "Cảm thấy mạnh mẽ hơn!";
    }
    
    public void restoreStatus(){
        
        life = maxLife;
        mana = maxMana;
        speed = defaultSpeed;
        invincible = false;
        transparent = false;
        attacking = false;
        guarding = false;
        knockBack = false;
        lightUpdate = true;
    }
    
    //Hiển thị các Item 
    public void setItems(){
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
    }
    
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        motion1_duration = currentWeapon.motion1_duration;
        motion2_duration = currentWeapon.motion2_duration;
        return attack = strength * currentWeapon.attackValue;
    }
    
        public int getDefense(){
        return defense = dexterity * currentShield.defenseValue;
    }
    
    
    public void setDefaultValuesP2() {
        worldX = ogp.tileSizeW * 23;
        worldY = ogp.tileSizeH * 21;
//        worldX = ogp.tileSizeW * 12;
//        worldY = ogp.tileSizeH * 13;
        name = "Aqua";
        defaultSpeed = 4;

        speed = defaultSpeed;
        direction = "S";
     
        //PLAYER STATUS
        maxLife=10;
        life=maxLife;//1 mạng = nửa tim & 2 mạng= 1 tim
        maxMana = 20;
        mana = maxMana;
        ammo = 10;
        level = 1;
        strength = 4; //càng khoẻ đánh càng đau
        dexterity = 1; // càng khéo léo thì càng thủ mạnh
        exp = 0;
        nextLevelExp = 5;
        coin = 500;
        projectile = new OBJ_Fireball(ogp);
        //projectile = new OBJ_Rock(ogp);

        attack = getAttack();// tổng giá trị tấn công được quyết định bởi strength và dame of weapon
        defense = getDefense(); //tổng giá trị phòng thủ quyết định bởi sự khéo léo và chỉ số từ khiên

    }

    public void pickUpObject(int i) {
        if (i != 999) {//nếu có xảy ra va chạm
//            final String objectName = this.ogp.obj[i].name;
//            switch (objectName) {
//                case "Key": {
//                    ogp.playSE(1);
//                    hasKey++;
//                    this.ogp.obj[i] = null; //xoá key trong array -> xoá key trên màn 
//                    ogp.ui.showMessage("You got a key!");
//                    break;
//                }
//                case "Door": {
//                    if (this.hasKey > 0) {
//                        ogp.playSE(3);
//                        this.ogp.obj[i] = null;
//                        hasKey--; //trừ 1 key cho mỗi lần mở cửa
//                        ogp.ui.showMessage("You opened the door!");
//                        break;
//                    } else {
//                        ogp.ui.showMessage("You need a key!");
//                    }
//                    break;
//                }
//                case "Boots": {
//                    ogp.playSE(2);
//                    speed += 2;
//                    this.ogp.obj[i] = null;
//                    ogp.ui.showMessage("Speed up!");
//                    break;
//                }
//                case "Chest": {
//                    ogp.ui.gameFinished = true;
//                    this.ogp.stopMusic();
//                    this.ogp.playSE(4);
//                    break;
//                }
//                default:
//                    break;
//                }
            //PICKUP ONLY ITEMS
            if(ogp.obj[ogp.currentMap][i].type == type_pickupOnly){
                
                ogp.obj[ogp.currentMap][i].use(this);
                ogp.obj[ogp.currentMap][i] = null;
            }
            //OBSTACLE
            else if(ogp.obj[ogp.currentMap][i].type == type_obstacle){
                if(keyH.enterPressed == true){
                    attackCanceled = true;
                    ogp.obj[ogp.currentMap][i].interact();
                }
            }
            
            //INVENTORY ITEMS
            else{
                String text;
                    if(canObtainItem(ogp.obj[ogp.currentMap][i]) ==  true){
                
//                  inventory.add(ogp.obj[ogp.currentMap][i]);//sửa
                    ogp.playSE(1);
                    text = "Đã nhận " + ogp.obj[ogp.currentMap][i].name + "!";//sửa
                }   
            else{
                text = "Túi đã đầy!";
            }
            ogp.ui.addMessage(text);
            ogp.obj[ogp.currentMap][i] = null;//sửa
            }
        }
    }

    public void interactNPC(int i) {
        
        if(ogp.keyH.enterPressed == true){
            if (i != 999) {//touch npc
                attackCanceled = true;
//                ogp.gameState = ogp.dialogueState;
                ogp.npc[ogp.currentMap][i].speak();
                ogp.npc[ogp.currentMap][i].move(direction);
            }
//            nếu có thể để được dưới đây chắc sẽ đẩy đc cục đá

//            ogp.npc[ogp.currentMap][i].move(direction);
        }
    }

    public void getAttackP1() {
        //hình ảnh nhân vật từ 35x70 => 70x70 Nên phải thay đổi setup 
        this.D1_AT = setup("/res/playerAT/Character1_D_AT4", ogp.tileSizeW*2,ogp.tileSizeH);
        this.S1_AT = setup("/res/playerAT/Character1_S_AT", ogp.tileSizeW,ogp.tileSizeH*2);
        this.A1_AT = setup("/res/playerAT/Character1_A_AT", ogp.tileSizeW*2,ogp.tileSizeH);
        this.W1_AT = setup("/res/playerAT/Character1_W_AT", ogp.tileSizeW,ogp.tileSizeH*2);
        
        this.AW1_AT = setup("/res/playerAT/Character1_WA_AT", ogp.tileSizeW*2,ogp.tileSizeH*2);
        this.SA1_AT = setup("/res/playerAT/Character1_SA_AT", ogp.tileSizeW*2,ogp.tileSizeH*2);
        this.SD1_AT = setup("/res/playerAT/Character1_SD2_AT", ogp.tileSizeW*2,ogp.tileSizeH*2);
        this.WD1_AT = setup("/res/playerAT/Character1_WD2_AT", ogp.tileSizeW*2,ogp.tileSizeH*2);
//        this.A1_AT = setup("/res/playerAT/Character1_D_AT4", ogp.tileSizeW*2,ogp.tileSizeH);
//        this.S1_AT = setup("/res/playerAT/Character1_D_AT4", ogp.tileSizeW,ogp.tileSizeH*2);
//        this.W1_AT = setup("/res/playerAT/Character1_D_AT4", ogp.tileSizeW,ogp.tileSizeH*2);
//        
//        this.AW1_AT = setup("/res/playerAT/Character1_D_AT4", ogp.tileSizeW*2,ogp.tileSizeH*2);
//        this.SA1_AT = setup("/res/playerAT/Character1_D_AT4", ogp.tileSizeW*2,ogp.tileSizeH*2);
//        this.SD1_AT = setup("/res/playerAT/Character1_D_AT4", ogp.tileSizeW*2,ogp.tileSizeH*2);
//        this.WD1_AT = setup("/res/playerAT/Character1_D_AT4", ogp.tileSizeW*2,ogp.tileSizeH*2);
    }
    
    public int getCurrentWeaponSlot(){
        int currentWeaponSlot = 0;
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i) == currentWeapon){
                currentWeaponSlot = i;
            }
        }
        return currentWeaponSlot;
    }
    
    public int getCurrentShieldSlot(){
        int currentShieldSlot = 0;
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i) == currentShield){
                currentShieldSlot = i;
            }
        }
        return currentShieldSlot;
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

    public void getSleepingImage(BufferedImage image){
        
        this.A1 = setup("/res/player/Character1_A", ogp.tileSizeW,ogp.tileSizeH);
            this.A2 = image;
            this.A3 = image;
            this.A4 = image;

            this.AW1 = image;
            this.AW2 = image;
            this.AW3 = image;
            this.AW4 = image;

            this.D1 = image;
            this.D2 = image;
            this.D3 = image;
            this.D4 = image;
            
            
            this.SS1 = image;
            
            
            this.S1 = image;
            this.S2 = image;
            this.S3 = image;
            this.S4 = image;

            this.SA1 = image;
            this.SA2 = image;
            this.SA3 = image;
            this.SA4 = image;

            this.SD1 = image;
            this.SD2 = image;
            this.SD3 = image;
            this.SD4 = image;

            this.W1 = image;
            this.W2 = image;
            this.W3 = image;
            this.W4 = image;

            this.WD1 = image;
            this.WD2 = image;
            this.WD3 = image;
            this.WD4 = image;

    }
    
    public void getPlayerImageP2() {
 
            this.A1 = setup("/res/player/Character2_A", ogp.tileSizeW,ogp.tileSizeH);
            this.A2 = setup("/res/player/Character2_A1", ogp.tileSizeW,ogp.tileSizeH);
            this.A3 = setup("/res/player/Character2_A", ogp.tileSizeW,ogp.tileSizeH);
            this.A4 = setup("/res/player/Character2_A2", ogp.tileSizeW,ogp.tileSizeH);

            this.AW1 = setup("/res/player/Character2_WA", ogp.tileSizeW,ogp.tileSizeH);
            this.AW2 = setup("/res/player/Character2_WA1", ogp.tileSizeW,ogp.tileSizeH);
            this.AW3 = setup("/res/player/Character2_WA", ogp.tileSizeW,ogp.tileSizeH);
            this.AW4 = setup("/res/player/Character2_WA2", ogp.tileSizeW,ogp.tileSizeH);

            this.D1 = setup("/res/player/Character2_D", ogp.tileSizeW,ogp.tileSizeH);
            this.D2 = setup("/res/player/Character2_D1", ogp.tileSizeW,ogp.tileSizeH);
            this.D3 = setup("/res/player/Character2_D", ogp.tileSizeW,ogp.tileSizeH);
            this.D4 = setup("/res/player/Character2_D2", ogp.tileSizeW,ogp.tileSizeH);

            this.S1 = setup("/res/player/Character2_S", ogp.tileSizeW,ogp.tileSizeH);
            this.S2 = setup("/res/player/Character2_S1", ogp.tileSizeW,ogp.tileSizeH);
            this.S3 = setup("/res/player/Character2_S", ogp.tileSizeW,ogp.tileSizeH);
            this.S4 = setup("/res/player/Character2_S2", ogp.tileSizeW,ogp.tileSizeH);

            this.SA1 = setup("/res/player/Character2_SA", ogp.tileSizeW,ogp.tileSizeH);
            this.SA2 = setup("/res/player/Character2_SA1", ogp.tileSizeW,ogp.tileSizeH);
            this.SA3 = setup("/res/player/Character2_SA", ogp.tileSizeW,ogp.tileSizeH);
            this.SA4 = setup("/res/player/Character2_SA2", ogp.tileSizeW,ogp.tileSizeH);

            this.SD1 = setup("/res/player/Character2_SD", ogp.tileSizeW,ogp.tileSizeH);
            this.SD2 = setup("/res/player/Character2_SD1", ogp.tileSizeW,ogp.tileSizeH);
            this.SD3 = setup("/res/player/Character2_SD", ogp.tileSizeW,ogp.tileSizeH);
            this.SD4 = setup("/res/player/Character2_SD2", ogp.tileSizeW,ogp.tileSizeH);

            this.W1 = setup("/res/player/Character2_W", ogp.tileSizeW,ogp.tileSizeH);
            this.W2 = setup("/res/player/Character2_W1", ogp.tileSizeW,ogp.tileSizeH);
            this.W3 = setup("/res/player/Character2_W", ogp.tileSizeW,ogp.tileSizeH);
            this.W4 = setup("/res/player/Character2_W2", ogp.tileSizeW,ogp.tileSizeH);

            this.WD1 = setup("/res/player/Character2_WD", ogp.tileSizeW,ogp.tileSizeH);
            this.WD2 = setup("/res/player/Character2_WD1", ogp.tileSizeW,ogp.tileSizeH);
            this.WD3 = setup("/res/player/Character2_WD", ogp.tileSizeW,ogp.tileSizeH);
            this.WD4 = setup("/res/player/Character2_WD2", ogp.tileSizeW,ogp.tileSizeH);
    }

    public  void getGuardImage(){
        guardUp = setup("/res/playerDF/Character1_W2", ogp.tileSizeW,ogp.tileSizeH);
        guardDown = setup("/res/playerDF/Character1_S", ogp.tileSizeW,ogp.tileSizeH);
        guardLeft = setup("/res/playerDF/Character1_A2", ogp.tileSizeW,ogp.tileSizeH);
        guardRight = setup("/res/playerDF/Character1_D", ogp.tileSizeW,ogp.tileSizeH);
        guardUpLeft = setup("/res/playerDF/Character1_WA2", ogp.tileSizeW,ogp.tileSizeH);
        guardUpRight = setup("/res/playerDF/Character1_WD", ogp.tileSizeW,ogp.tileSizeH);
        guardDownLeft = setup("/res/playerDF/Character1_SA2", ogp.tileSizeW,ogp.tileSizeH);
        guardDownRight = setup("/res/playerDF/Character1_SD", ogp.tileSizeW,ogp.tileSizeH);
    }
    
    //2Player - Cho nhân vật 1
    public void updateP1() {
        if(knockBack == true){
            
            collisionOn = false;
            ogp.cChecker.checkTile(this);//kiemtra co va cham khong
            ogp.cChecker.checkObject(this, true);
            ogp.cChecker.checkEntity(this, ogp.npc);
            ogp.cChecker.checkEntity(this, ogp.monster);
            ogp.cChecker.checkEntity(this, ogp.iTile);
            
            if(collisionOn == true){
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            else if(collisionOn == false){
                switch(knockBackDirection){
                    case "W":
                    worldY -= speed;
                    break;
                case "S":
                    worldY += speed;
                    break;
                case "A":
                    worldX -= speed;
                    break;
                case "D":
                    worldX += speed;
                    break;
                }
            }
            
            knockBackCounter++;
            if(knockBackCounter == 10){
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        }
        
        else if(attacking == true){
            attackingP();
        }
        else if(keyH.spacePressed == true){
            guarding = true;
            guardCounter++;
        }
        else if (keyH.upPressed == true || keyH.leftPressed == true || keyH.downPressed == true || keyH.rightPressed == true
                || keyH.enterPressed == true
                ) {
            if (keyH.upPressed == true) {
                if (keyH.leftPressed == true) {
                    direction = "AW";
                } else if (keyH.rightPressed == true) {
                    direction = "WD";
                } else {
                    direction = "W";
                }
            } else if (keyH.downPressed == true) {
                if (keyH.leftPressed == true) {
                    direction = "SA";
                } else if (keyH.rightPressed == true) {
                    direction = "SD";
                } else {
                    direction = "S";
                }

            } else if (keyH.leftPressed == true) {
                if (keyH.upPressed == true) {
                    direction = "AW";
                } else if (keyH.downPressed == true) {
                    direction = "SA";
                } else {
                    direction = "A";
                }
            } else if (keyH.rightPressed == true) {
                if (keyH.upPressed == true) {
                    direction = "WD";
                } else if (keyH.downPressed == true) {
                    direction = "SD";
                } else {
                    direction = "D";
                }
            }
            //CHECK TILE COLOLLISION
            collisionOn = false;
            ogp.cChecker.checkTile(this);//kiemtra co va cham khong

            //CHECK OBJECTS COLLISION
            int objIndex = ogp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = ogp.cChecker.checkEntity(this, ogp.npc);
            interactNPC(npcIndex);
            
            //CHECK MONSTER COLLISION
            int monsterIndex = ogp.cChecker.checkEntity(this, ogp.monster);
            contactMonster(monsterIndex);
            
            //CHECK INTERACTIVE TILE COLLISION
            int iTileIndex = ogp.cChecker.checkEntity(this, ogp.iTile);
            
            //CHECK  EVENT
            ogp.eHandler.checkEvent(); 
            
            //IF COLLISION = FALSE, PLAYER can move
            if (collisionOn == false && keyH.enterPressed == false) {
                switch (direction) {
                    case "AW" -> {
                        worldY -= speed;
                        worldX -= speed;
                    }
                    case "WD" -> {
                        worldY -= speed;
                        worldX += speed;
                    }
                    case "SA" -> {
                        worldY += speed;
                        worldX -= speed;
                    }
                    case "SD" -> {
                        worldY += speed;
                        worldX += speed;
                    }
                    case "W" ->
                        worldY -= speed;
                    case "S" ->
                        worldY += speed;

                    case "A" ->
                        worldX -= speed;

                    case "D" ->
                        worldX += speed;

                }
            }

            if(ogp.player.name == "Windy"){
                if(keyH.enterPressed == true && attackCanceled == false){
                    ogp.playSE(7);
                    attacking = true;
                    spiteCounter = 0;
                }
                //DECRASE DURABILITY
                currentWeapon.durability--;
            }

            attackCanceled = false;
            ogp.keyH.enterPressed = false;
            guarding = false;
            guardCounter = 0;
            
            spiteCounter++;
            if (spiteCounter > 12) {
                switch (spiteNum) {
                    case 1 ->
                        spiteNum = 2;
                    case 2 ->
                        spiteNum = 3;
                    case 3 ->
                        spiteNum = 4;
                    case 4 ->
                        spiteNum = 1;
                    default -> {
                    }
                }
                spiteCounter = 0;
            }
            else {
                standCounter++;
                if(standCounter == 20){
                    spiteNum = 1;
                    standCounter = 0;
                }
                guarding = false;
                guardCounter = 0;
            }
        }
        
        if(ogp.keyH.shotKeyPressed == true && projectile.alive == false 
                && shotAvailableCounter == 30 && projectile.haveResource(this) == true)
        {

        //SET DEFAULT COORDINATES, DIRECTION AND USER
        projectile.set(worldX, worldY, direction, true, this);

        //SUBTRACT THE COST (MANA, AMMO ETC.)
        projectile.subtractResource(this);
        
        //CHECK VACANCY
        for(int i = 0; i < ogp.projectile[1].length; i++){
            if(ogp.projectile[ogp.currentMap][i] == null){
                ogp.projectile[ogp.currentMap][i] = projectile;
                break;
            }
        }
        
        shotAvailableCounter = 0; //tranh lỗi 2 quả cầu ra cùng lúc khi dính quái
        
        ogp.playSE(10);
        }
        //tao bat tu
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                transparent = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
        if(life > maxLife){
            life = maxLife;
        }
        if(mana > maxMana){
            mana = maxMana;
        }
        if(keyH.godModeOn == false){
            if(life <= 0){
            ogp.gameState = ogp.gameOverState;
            ogp.ui.commandNum = -1;
//            ogp.playMusic(life);
            ogp.playSE(12);
//            ogp.stopMusic();  
            ogp.stopMusicSound();
            //có thể thêm nhạc khi nhân vật chết
            }
        }
    }
    
//    public void attackingP(){
//            spiteCounter++;
//            if(spiteCounter <=5){
//                spiteNum = 1;
//            }
//            if(spiteCounter >5 && spiteCounter<=25){
//                spiteNum = 2;
//                
//                //Lưu vị trí hiện tại của player
//                int currentWorldX = worldX;
//                int currentWorldY = worldY;
//                int solidAreaWidth = solidArea.width;
//                int solidAreaWHeight = solidArea.height;//chuyển vị trí va chạm từ người chơi qua vị trí của vũ khí
//                
//                //Tạo vị trí mới theo vũ khí
//                switch(direction){
//                    case"W": worldY -= attackArea.height; break;
//                    case"A": worldX -= attackArea.width; break;
//                    case"S": worldY += attackArea.height; break;
//                    case"D": worldX += attackArea.width; break; 
//                    case"SD":
//                    {
//                        worldX += attackArea.width; 
//                        worldY += attackArea.height;
//                        break; 
//                    }
//                    case"SA": 
//                    {
//                        worldX -= attackArea.width ;
//                        worldY += attackArea.height;
//                        break; 
//                    } 
//                    case"WD":{
//                        worldX += attackArea.width;
//                        worldY -= attackArea.height;
//                        break;
//                    }  
//                    case"AW":
//                    {
//                        worldX -= attackArea.width;
//                        worldY -= attackArea.height;
//                        break; 
//                    } 
//                    
//                }
//                //khu vực tấn công trờ thành khu vực va chạm, 
//                solidArea.width = attackArea.width;
//                solidArea.height = attackArea.height;
////                va chạm bị trừ máuu
//                int monsterIndex = ogp.cChecker.checkEntity(this, ogp.monster);
//                damageMonster(monsterIndex,this,attack,currentWeapon.knockBackPower);
//                
//                int iTileIndex = ogp.cChecker.checkEntity(this, ogp.iTile);
//                damageInteractiveTile(iTileIndex);
//                
//                int projectileIndex = ogp.cChecker.checkEntity(this, ogp.projectile);
//                damageProjectile(projectileIndex);
//                
//                //sau khi kiem tra va chạm thì trả lại vị trí default người chơi
//                worldX = currentWorldX;
//                worldY = currentWorldY;
//                solidArea.width  = solidAreaWidth;
//                solidArea.height = solidAreaWHeight;
//                
//                
//                
//            }
//            if(spiteCounter > 25){
//                spiteNum = 1;
//                spiteCounter = 0;
//                attacking = false;
//            }
//            
//  
//    }

    
    public void contactMonster(int monsterIndex) {
        if(monsterIndex != 999){
            if(invincible == false && ogp.monster[ogp.currentMap][monsterIndex].dying == false){//sửa
                ogp.playSE(7);
                
                int damage = ogp.monster[ogp.currentMap][monsterIndex].attack - defense;//sửa
                if(damage < 1){
                    damage = 1;
                }
                life -= damage;
                invincible = true;
                transparent = true;
            }
            //Vì hàm này được gọi 60 lần 1s nên sẽ đốt sạch máu của ng chơi
        }
    }
    
    public void damageMonster(int i, Entity attacker, int attack, int knockBackPower){
        if(i!= 999 ){
            //Check code
//            System.out.println("hit ");
            if(ogp.monster[ogp.currentMap][i].invincible == false){//sửa
                ogp.playSE(6);
                
                if(knockBackPower > 0){
                    setKnockBack(ogp.monster[ogp.currentMap][i],attacker ,knockBackPower);
                }
                if(ogp.monster[ogp.currentMap][i].offBalance == true){
                    attack*=5;
                }
                
                int damage = attack - ogp.monster[ogp.currentMap][i].defense;//sửa
                if(damage < 0){
                    damage = 0;
                }
                
                ogp.monster[ogp.currentMap][i].life -= damage;//sửa
                ogp.ui.addMessage(damage + " sát thương!");
                ogp.monster[ogp.currentMap][i].invincible = true;//sửa
                ogp.monster[ogp.currentMap][i].damageReaction();//sửa
                
                if(ogp.monster[ogp.currentMap][i].life <= 0){//sửa
                    ogp.monster[ogp.currentMap][i].dying  = true;//sửa
                    ogp.ui.addMessage("Đã hạ gục " + ogp.monster[ogp.currentMap][i].name + "!");//sửa
                    ogp.ui.addMessage("Kinh nghiệm nhân được: + " + ogp.monster[ogp.currentMap][i].exp);//sửa
                    exp += ogp.monster[ogp.currentMap][i].exp;//sửa
                    checkLevelUp();
                }
            }
        }
//        }else{
//            System.out.println("miss ");
//        }
    }
    
//    public  void knockBack(Entity entity, int knockBackPower){
//        
//        entity.direction = direction;
//        entity.speed += knockBackPower;
//        entity.knockBack = true;
//        
//    }
    
    public void damageInteractiveTile(int i){
        
        if(i != 999 && ogp.iTile[ogp.currentMap][i].destructible == true //sửa
                && ogp.iTile[ogp.currentMap][i].isCorrectItem(this) == true//sửa
                && ogp.iTile[ogp.currentMap][i].invincible == false){//sửa
            ogp.iTile[ogp.currentMap][i].playSE();//sửa
            ogp.iTile[ogp.currentMap][i].life--;//sửa
            ogp.iTile[ogp.currentMap][i].invincible = true;//sửa
            
            //Generate Particle
            generateParticle(ogp.iTile[ogp.currentMap][i],ogp.iTile[ogp.currentMap][i]);//sửa
            
            if(ogp.iTile[ogp.currentMap][i].life == 0){//sửa
//                ogp.iTile[ogp.currentMap][i].checkDrop();
                ogp.iTile[ogp.currentMap][i] = ogp.iTile[ogp.currentMap][i].getDestroyedForm();//sửa
            }
        }
    }
    
    public void damageProjectile(int i){
        
        if(i != 999){
            Entity projectile = ogp.projectile[ogp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile,projectile);
        }
    }
    
    public void checkLevelUp(){
        if(exp >= nextLevelExp){
            
            level++;
            nextLevelExp = nextLevelExp*2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();
            
            ogp.playSE(9);
            ogp.gameState = ogp.dialogueState;
//            dialogues[0][0] = "You are level " + level + " now\n"
//                    + "You fell stronger!";
            setDialogue();
            startDialogue(this,0);
        }
    }
    
    public void selectItem(){
        int itemIndex = ogp.ui.getItemIndexOnSlot(ogp.ui.playerSlotCol, ogp.ui.playerSlotRow);
        if(itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);
            if(selectedItem.type == 3 ||selectedItem.type == 4 || selectedItem.type == 10){
                currentWeapon = selectedItem;
                attack = getAttack();
                getAttackP1();
            }
            if(selectedItem.type == 5){
                currentShield = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == type_light){
                
                if(currentLight == selectedItem){
                    currentLight = null;
                }
                else{
                    currentLight = selectedItem;
                }
                lightUpdate = true;
            }
            if(selectedItem.type == 6){
                if(selectedItem.use(this) == true){
                    if(selectedItem.amount > 1){
                        selectedItem.amount--;
                    }
                    else{
                        
                        inventory.remove(itemIndex);
                    }
                }
            }
        }
    }
    //gộp các item giống nhau về 1 ô trong hộp đồ nhân vật
    public int searchItemInInventory(String itemName){
        
        int itemIndex = 999;
        
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i).name.equals(itemName)){
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }
    
    public boolean canObtainItem(Entity item){
        
        boolean canObtain = false;
        
        Entity newItem = ogp.eGenerator.getObject(item.name);
        
        //CHECK IF STACKABLE
        if(item.stackable == true){
            
            int index = searchItemInInventory(newItem.name);
            
            if(index != 999){
                inventory.get(index).amount++;
                canObtain = true;
            }
            else{//Now item so need to check vacancy
                if(inventory.size() != maxInventorySize){
                    inventory.add(newItem);
                    canObtain = true;
                }
            }
        }
        else{
            //NOT STACKABLE so check vancacy
            if(inventory.size() != maxInventorySize){
            inventory.add(newItem);
            canObtain = true;
            }
        }
        return canObtain;
        
    }
    
    //2Player - Cho nhân vật 2
    public void updateP2() {
        if (keyH.upArrowPressed == true || keyH.leftArrowPressed == true || keyH.downArrowPressed == true || keyH.rightArrowPressed == true) {
            if (keyH.upArrowPressed == true) {
                if (keyH.leftArrowPressed == true) {
                    direction = "UPLEFT";
                    worldY -= speed;
                    worldX -= speed;
                } else if (keyH.rightArrowPressed == true) {
                    direction = "UPRIGHT";
                    worldY -= speed;
                    worldX += speed;
                } else {
                    direction = "UP";
                    worldY -= speed;
                }
            } else if (keyH.downArrowPressed == true) {
                if (keyH.leftArrowPressed == true) {
                    direction = "DOWNLEFT";
                    worldY += speed;
                    worldX -= speed;
                } else if (keyH.rightArrowPressed == true) {
                    direction = "DOWNRIGHT";
                    worldY += speed;
                    worldX += speed;
                } else {
                    direction = "DOWN";
                    worldY += speed;
                }

            } else if (keyH.leftArrowPressed == true) {
                if (keyH.upArrowPressed == true) {
                    direction = "UPLEFT";
                    worldY -= speed;
                    worldX -= speed;
                } else if (keyH.downArrowPressed == true) {
                    direction = "DOWNLEFT";
                    worldY += speed;
                    worldX -= speed;
                } else {
                    direction = "LEFT";
                    worldX -= speed;
                }
            } else if (keyH.rightArrowPressed == true) {
                if (keyH.upArrowPressed == true) {
                    direction = "UPRIGHT";
                    worldY -= speed;
                    worldX += speed;
                } else if (keyH.downArrowPressed == true) {
                    direction = "DOWNRIGHT";
                    worldY += speed;
                    worldX += speed;
                } else {
                    direction = "RIGHT";
                    worldX += speed;
                }
            }
            spiteCounter++;
            if (spiteCounter > 12) {
                switch (spiteNum) {
                    case 1 ->
                        spiteNum = 2;
                    case 2 ->
                        spiteNum = 3;
                    case 3 ->
                        spiteNum = 4;
                    case 4 ->
                        spiteNum = 1;
                    default -> {
                    }
                }
                spiteCounter = 0;
            }
        }

    }

    //2Player
    public void draw(Graphics2D g2) {
//         g2.setColor(Color.white);
//         g2.fillRect(x,y,gp.tileSize ,gp.tileSize); //vẽ hình chữ nhật (toạ độ X, Toạ độ y , độ rộng, độ dài)

        BufferedImage image = null;

        int tempScreenX = screenX;
        int tempScreenY = screenY;
        
        switch (direction) {
            case "W" -> {
                if (spiteNum == 1) {
                    image = W1;
                }
                if (spiteNum == 2) {
                    image = W2;
                }
                if (spiteNum == 3) {
                    image = W3;
                }
                if (spiteNum == 4) {
                    image = W4;
                }
            }

            case "A" -> {
                if (spiteNum == 1) {
                    image = A1;
                }
                if (spiteNum == 2) {
                    image = A2;
                }
                if (spiteNum == 3) {
                    image = A3;
                }
                if (spiteNum == 4) {
                    image = A4;
                }
            }
            case "S" -> {
                if (spiteNum == 1) {
                    image = S1;
                }
                if (spiteNum == 2) {
                    image = S2;
                }
                if (spiteNum == 3) {
                    image = S3;
                }
                if (spiteNum == 4) {
                    image = S4;
                }
            }
            case "D" -> {
                if (spiteNum == 1) {
                    image = D1;
                }
                if (spiteNum == 2) {
                    image = D2;
                }
                if (spiteNum == 3) {
                    image = D3;
                }
                if (spiteNum == 4) {
                    image = D4;
                }
            }

            case "AW" -> {
                if (spiteNum == 1) {
                    image = AW1;
                }
                if (spiteNum == 2) {
                    image = AW2;
                }
                if (spiteNum == 3) {
                    image = AW3;
                }
                if (spiteNum == 4) {
                    image = AW4;
                }
            }
            case "WD" -> {
                if (spiteNum == 1) {
                    image = WD1;
                }
                if (spiteNum == 2) {
                    image = WD2;
                }
                if (spiteNum == 3) {
                    image = WD3;
                }
                if (spiteNum == 4) {
                    image = WD4;
                }
            }
            case "SA" -> {
                if (spiteNum == 1) {
                    image = SA1;
                }
                if (spiteNum == 2) {
                    image = SA2;
                }
                if (spiteNum == 3) {
                    image = SA3;
                }
                if (spiteNum == 4) {
                    image = SA4;
                }
            }
            case "SD" -> {
                if (spiteNum == 1) {
                    image = SD1;
                }
                if (spiteNum == 2) {
                    image = SD2;
                }
                if (spiteNum == 3) {
                    image = SD3;
                }
                if (spiteNum == 4) {
                    image = SD4;
                }
            }
            case "UP" -> {
                if (spiteNum == 1) {
                    image = UP1;
                }
                if (spiteNum == 2) {
                    image = UP2;
                }
                if (spiteNum == 3) {
                    image = UP3;
                }
                if (spiteNum == 4) {
                    image = UP4;
                }
            }

            case "LEFT" -> {
                if (spiteNum == 1) {
                    image = LEFT1;
                }
                if (spiteNum == 2) {
                    image = LEFT2;
                }
                if (spiteNum == 3) {
                    image = LEFT3;
                }
                if (spiteNum == 4) {
                    image = LEFT4;
                }
            }
            case "DOWN" -> {
                if (spiteNum == 1) {
                    image = DOWN1;
                }
                if (spiteNum == 2) {
                    image = DOWN2;
                }
                if (spiteNum == 3) {
                    image = DOWN3;
                }
                if (spiteNum == 4) {
                    image = DOWN4;
                }
            }
            case "RIGHT" -> {
                if (spiteNum == 1) {
                    image = RIGHT1;
                }
                if (spiteNum == 2) {
                    image = RIGHT2;
                }
                if (spiteNum == 3) {
                    image = RIGHT3;
                }
                if (spiteNum == 4) {
                    image = RIGHT4;
                }
            }

            case "UPLEFT" -> {
                if (spiteNum == 1) {
                    image = UPLEFT1;
                }
                if (spiteNum == 2) {
                    image = UPLEFT2;
                }
                if (spiteNum == 3) {
                    image = UPLEFT3;
                }
                if (spiteNum == 4) {
                    image = UPLEFT4;
                }
            }
            case "UPRIGHT" -> {
                if (spiteNum == 1) {
                    image = UPRIGHT1;
                }
                if (spiteNum == 2) {
                    image = UPRIGHT2;
                }
                if (spiteNum == 3) {
                    image = UPRIGHT3;
                }
                if (spiteNum == 4) {
                    image = UPRIGHT4;
                }
            }
            case "DOWNLEFT" -> {
                if (spiteNum == 1) {
                    image = DOWNLEFT1;
                }
                if (spiteNum == 2) {
                    image = DOWNLEFT2;
                }
                if (spiteNum == 3) {
                    image = DOWNLEFT3;
                }
                if (spiteNum == 4) {
                    image = DOWNLEFT4;
                }
            }
            case "DOWNRIGHT" -> {
                if (spiteNum == 1) {
                    image = DOWNRIGHT1;
                }
                if (spiteNum == 2) {
                    image = DOWNRIGHT2;
                }
                if (spiteNum == 3) {
                    image = DOWNRIGHT3;
                }
                if (spiteNum == 4) {
                    image = DOWNRIGHT4;
                }
            }

        }
        g2.drawImage(image, screenX, screenY, gp.tileSizeW, gp.tileSizeH, null);
    }

    //1Player
    public void draw1P(Graphics2D g2) {
//         g2.setColor(Color.white);
//         g2.fillRect(x,y,gp.tileSize ,gp.tileSize); //vẽ hình chữ nhật (toạ độ X, Toạ độ y , độ rộng, độ dài)

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        switch (direction) {
            case "W" -> {
                if(attacking == false){
                    if (spiteNum == 1) {image = W1;}
                    if (spiteNum == 2) {image = W2;}
                    if (spiteNum == 3) {image = W3;}
                    if (spiteNum == 4) {image = W4;}
                }
                if(attacking == true){
                     tempScreenY  = screenY - ogp.tileSizeW;
                    if (spiteNum == 2) {image = W1_AT;}
                }
                if(guarding == true){
                    image = guardUp;
                }
            }

            case "A" -> {
                if(attacking == false){
                    if (spiteNum == 1) {image = A1;}
                    if (spiteNum == 2) {image = A2;}
                    if (spiteNum == 3) {image = A3;}
                    if (spiteNum == 4) {image = A4;}
                }
                if(attacking == true){
                      tempScreenX = screenX - ogp.tileSizeH;
                    if (spiteNum == 2) {image = A1_AT;}
                }
                if(guarding == true){
                    image = guardLeft;
                }
            }
            case "S" -> {
               if(attacking == false){
                    if (spiteNum == 1) {image = S1;}
                    if (spiteNum == 2) {image = S2;}
                    if (spiteNum == 3) {image = S3;}
                    if (spiteNum == 4) {image = S4;}
                }
                if(attacking == true){
                    if (spiteNum == 2) {image = S1_AT;}
                }
                if(guarding == true){
                    image = guardDown;
                }
            }
            case "D" -> {
                if(attacking == false){
                    if (spiteNum == 1) {image = D1;}
                    if (spiteNum == 2) {image = D2;}
                    if (spiteNum == 3) {image = D3;}
                    if (spiteNum == 4) {image = D4;}
                }
                if(attacking == true){
                    if (spiteNum == 2) {image = D1_AT;}
                }
                if(guarding == true){
                    image = guardRight;
                }
            }
            case "AW" -> {
                if(attacking == false){
                    if (spiteNum == 1) {image = AW1;}
                    if (spiteNum == 2) {image = AW2;}
                    if (spiteNum == 3) {image = AW3;}
                    if (spiteNum == 4) {image = AW4;}
                }
                if(attacking == true){
                    tempScreenY  = screenY - ogp.tileSizeW;
                    tempScreenX = screenX - ogp.tileSizeH;
                    if (spiteNum == 2) {image = AW1_AT;}
                }
                if(guarding == true){
                    image = guardUpLeft;
                }
            }
            case "WD" -> {
                if(attacking == false){
                    if (spiteNum == 1) {image = WD1;}
                    if (spiteNum == 2) {image = WD2;}
                    if (spiteNum == 3) {image = WD3;}
                    if (spiteNum == 4) {image = WD4;}
                }
                if(attacking == true){
                    tempScreenY  = screenY - ogp.tileSizeW;
                    if (spiteNum == 2) {image = WD1_AT;}
                }
                if(guarding == true){
                    image = guardUpRight;
                }
            }
            case "SA" -> {
                if(attacking == false){
                    if (spiteNum == 1) {image = SA1;}
                    if (spiteNum == 2) {image = SA2;}
                    if (spiteNum == 3) {image = SA3;}
                    if (spiteNum == 4) {image = SA4;}
                }
                if(attacking == true){
                    tempScreenX = screenX - ogp.tileSizeH;
                    if (spiteNum == 2) {image = SA1_AT;}
                }
                if(guarding == true){
                    image = guardDownLeft;
                }
            }
            case "SD" -> {
                if(attacking == false){
                    if (spiteNum == 1) {image = SD1;}
                    if (spiteNum == 2) {image = SD2;}
                    if (spiteNum == 3) {image = SD3;}
                    if (spiteNum == 4) {image = SD4;}
                }
                if(attacking == true){
                    if (spiteNum == 2) {image = SD1_AT;}
                }
                if(guarding == true){
                    image = guardDownRight;
                }
            }
            case "UP" -> {
                if (spiteNum == 1) {
                    image = UP1;
                }   
                if (spiteNum == 2) {
                    image = UP2;
                }
                if (spiteNum == 3) {
                    image = UP3;
                }
                if (spiteNum == 4) {
                    image = UP4;
                }
            }

            case "LEFT" -> {
                if (spiteNum == 1) {
                    image = LEFT1;
                }
                if (spiteNum == 2) {
                    image = LEFT2;
                }
                if (spiteNum == 3) {
                    image = LEFT3;
                }
                if (spiteNum == 4) {
                    image = LEFT4;
                }
            }
            case "DOWN" -> {
                if (spiteNum == 1) {
                    image = DOWN1;
                }
                if (spiteNum == 2) {
                    image = DOWN2;
                }
                if (spiteNum == 3) {
                    image = DOWN3;
                }
                if (spiteNum == 4) {
                    image = DOWN4;
                }
            }
            case "RIGHT" -> {
                if (spiteNum == 1) {
                    image = RIGHT1;
                }
                if (spiteNum == 2) {
                    image = RIGHT2;
                }
                if (spiteNum == 3) {
                    image = RIGHT3;
                }
                if (spiteNum == 4) {
                    image = RIGHT4;
                }
            }

            case "UPLEFT" -> {
                if (spiteNum == 1) {
                    image = UPLEFT1;
                }
                if (spiteNum == 2) {
                    image = UPLEFT2;
                }
                if (spiteNum == 3) {
                    image = UPLEFT3;
                }
                if (spiteNum == 4) {
                    image = UPLEFT4;
                }
            }
            case "UPRIGHT" -> {
                if (spiteNum == 1) {
                    image = UPRIGHT1;
                }
                if (spiteNum == 2) {
                    image = UPRIGHT2;
                }
                if (spiteNum == 3) {
                    image = UPRIGHT3;
                }
                if (spiteNum == 4) {
                    image = UPRIGHT4;
                }
            }
            case "DOWNLEFT" -> {
                if (spiteNum == 1) {
                    image = DOWNLEFT1;
                }
                if (spiteNum == 2) {
                    image = DOWNLEFT2;
                }
                if (spiteNum == 3) {
                    image = DOWNLEFT3;
                }
                if (spiteNum == 4) {
                    image = DOWNLEFT4;
                }
            }
            case "DOWNRIGHT" -> {
                if (spiteNum == 1) {
                    image = DOWNRIGHT1;
                }
                if (spiteNum == 2) {
                    image = DOWNRIGHT2;
                }
                if (spiteNum == 3) {
                    image = DOWNRIGHT3;
                }
                if (spiteNum == 4) {
                    image = DOWNRIGHT4;
                }
            }

        }
        
        if(transparent == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        if(drawing == true){
            
            g2.drawImage(image, tempScreenX, tempScreenY, null);
        }
        //reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        //debug
//        g2.setFont(new Font("Arial", Font.PLAIN,26));
//        g2.setColor(Color.white);
//        g2.drawString("invincible:"+invincibleCounter,10,400);
    }

    

}
