package entity;
//Đây là class cha của tất cả các nhân vật bao gồm người chơi
//quái vật, NPC,....

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import oneplayer.O_GamePanel;
import oneplayer.UtililyTool;

public class Entity {

    O_GamePanel ogp;
    
    //BufferedImage KIỂU LẤY DỮ LIỆU HÌNH ẢNH
    public BufferedImage SS1, A1, A2, A3, A4, AW1, AW2, AW3, AW4, D1, D2, D3, D4, S1, S2, S3, S4, SA1, SA2, SA3, SA4, SD1, SD2, SD3, SD4, W1, W2, W3, W4, WD1, WD2, WD3, WD4,
            UP1, UP2, UP3, UP4, UPLEFT1, UPLEFT2, UPLEFT3, UPLEFT4, UPRIGHT1, UPRIGHT2, UPRIGHT3, UPRIGHT4, DOWN1, DOWN2, DOWN3, DOWN4, DOWNLEFT1,
            DOWNLEFT2, DOWNLEFT3, DOWNLEFT4, DOWNRIGHT1, DOWNRIGHT2, DOWNRIGHT3, DOWNRIGHT4, LEFT1, LEFT2, LEFT3, LEFT4, RIGHT1, RIGHT2, RIGHT3, RIGHT4,
            guardUp,guardDown,guardLeft,guardRight,guardUpLeft,guardUpRight,guardDownLeft,guardDownRight;
    //Thêm biến lưu các hình ảnh tấn công
    public BufferedImage D1_AT,A1_AT,AW1_AT,S1_AT,SA1_AT,SD1_AT,W1_AT,WD1_AT,A2_AT,S2_AT,D2_AT,W2_AT;
    public int solidAreaDefaultX, solidAreaDefaultY;
    //tạo khu vực va chạm với các block khác. Nếu quy định toàn bộ 
    //block nhân vật là khu vực va chạm thì rất khó đi 
    //vì vậy thiết lập nhỏ hơn cho tiện di chuyển
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0,0,0,0); // Khu vực tác động để đánh ( nhận sát thương)
    //Khu vực tấn công của từng loại vũ khí là khác nhau nên k có thông số cụ thể mà sẽ Override theo từng loại sát thương khác nhau
    
    public String dialogues[][] = new String[20][20];//Dialogue
    public Entity attacker;
    public BufferedImage image, image2, image3;//thêm 2 3 cho đủ 3 heart
    public boolean collision= false;
    public Entity linkedEntity;
    public boolean temp = false;
    
    //STATE
    public int worldX, worldY;
    public String direction = "S";
    public int spiteNum = 1;
    public int dialogueSet = 0;
    public int dialogueIndex = 0;
    public boolean attacking = false;//biến  khi true thì get imageAttack
    public boolean alive = true; //biến tạo sự sống cho quái vật
    public boolean dying  = false;
    public boolean hpBarOn = false;//biến sẽ hiện thanh máu khi đánh nhau còn k thì k hiện nữa
    public boolean onPath = false;
    public boolean knockBack = false;//biến của hiệu ứng nốc out
    public String knockBackDirection;
    public boolean guarding = false;
    public boolean transparent = false;
    public boolean offBalance = false;
    public Entity loot;
    public boolean opened = false;
    public boolean inRage = false;
    public boolean sleep = false;
    public boolean drawing = true;   
    
    
    //Biến va chạm
    public boolean collisionOn = false;
    //Tạo biến giúp người chơi bất tử trong 1 khoảng thời gian sau khi nhận sát thương
    public boolean invincible = false;
    //COUNTER
    public int spiteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    public int dyingCounter = 0;//biến đếm máu quái vật
    public int hpBarCounter = 0;
    int knockBackCounter = 0;
    public int guardCounter = 0;
    int offBalanceCounter = 0;
    
    //CHARACTER ATTIBUTES
    //Tạo biến phân biệt lớp monster vs npc
    public String name;
    public int defaultSpeed;//Tốc độ đẩy
    public int speed;
    public int maxLife; //thanh máu max
    public int life;  //thanh máu 
    public int maxMana;
    public int mana;
    public int ammo;
    //Tạo thêm các thông số cơ bản nhằm hiển thị lên trạng thái nhân vật
    public int level;     //Cấp độ
    public int strength;  //Sức mạnh
    public int dexterity; //Khéo kéo
    public int attack;    //Lực chiến
    public int defense;   //Phòng thủ
    public int exp;       //Kinh nghiệm
    public int nextLevelExp;
    public int coin; // Tiền tệ
    public int motion1_duration;
    public int motion2_duration;
    public boolean boss;
    
    public Entity currentLight; //cái đèn
    public Entity currentWeapon; //Vũ khí default
    public Entity currentShield; //Khiên
    public Projectile projectile;
    
    //ITEM ATTRIBUTES
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public int price;
    public int knockBackPower = 0;
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius;
    public int durability = 100;
    //TYPE
    public int type; //0=player , 1= npc , 2= monster
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
    public final int type_obstacle = 8;
    public final int type_light = 9;
    public final int type_pickaxe = 10;
    
    public Entity(O_GamePanel ogp) {
        this.ogp = ogp;
    }

    public int getScreenX(){
        int screenX = worldX - ogp.player.worldX + ogp.player.screenX;
        return screenX;
    }
    
    public int getScreenY(){
        int screenY = worldY - ogp.player.worldY + ogp.player.screenY;
        return screenY;
    }
    
    public int getLeftX(){
        return worldX + solidArea.x;
    }
    
    public int getRightX(){
        return worldX + solidArea.x + solidArea.width;
    }
    
    public int getTopY(){
        return worldY + solidArea.y;
    }
    
    public int getBottomY(){
        return worldY + solidArea.y + solidArea.height;
    }
    
    public  int getCol(){
        return (worldX + solidArea.x)/ogp.tileSizeW;
    }
    
    public  int getRow(){
        return (worldY + solidArea.y)/ogp.tileSizeH;
    }
    
    public int getCenterX(){
        int centerX = worldX + A1.getWidth()/2;
        return centerX;
    }
    
    public int getCenterY(){
        int centerY = worldY + W1.getHeight()/2;
        return centerY;
    }
    
    public int getXdistance(Entity target){
        int xDistance = Math.abs(getCenterX() - target.getCenterX());
        return xDistance;
    }    
    
    public int getYdistance(Entity target){
        int yDistance = Math.abs(getCenterY() - target.getCenterY());
        return yDistance;
    }
    
    
    public int getTileDistance(Entity target){
        int tileDistance = (getXdistance(target) + getYdistance(target)/ogp.tileSizeH);
        return tileDistance;
    }
            
    public int getGoalCol(Entity target){
        int goalCol = (target.worldX + ogp.player.solidArea.x)/ogp.tileSizeW;
        return goalCol;
    }
    
    public int getGoalRow(Entity target){
        int goalRow = (target.worldY + ogp.player.solidArea.y)/ogp.tileSizeH;
        return goalRow;
    }
    
    public void resetCounter(){
        spiteCounter = 0;
        actionLockCounter = 0;
        invincibleCounter = 0;
        shotAvailableCounter = 0;
        dyingCounter = 0;//biến đếm máu quái vật
        hpBarCounter = 0;
        knockBackCounter = 0;
        guardCounter = 0;
        offBalanceCounter = 0;
    }
    
    public void setLoot(Entity loot){}
    
    public void setAction() {}

    public void move(String direction){}
    
    public void damageReaction(){
    }
    
    public void speak() {
    }

    public void facePlayer(){
        //NPC quay đối mặt với nhân vật khi trò chuyện
        switch (ogp.player.direction) {
            case "W":
                direction = "S";
                break;
            case "S":
                direction = "W";
                break;
            case "A":
                direction = "D";
                break;
            case "D":
                direction = "A";
                break;
            case "AW":
                direction = "SD";
                break;
            case "SD":
                direction = "AW";
                break;
            case "SA":
                direction = "WD";
                break;
            case "WD":
                direction = "SA";
                break;
        }
    }
    
    public void startDialogue(Entity entity, int setNum){
        ogp.gameState = ogp.dialogueState;
        ogp.ui.npc = entity;
        dialogueSet = setNum;
    }
    
    public void interact(){}
    
    public boolean use(Entity entity){return false;}
    
    //Kiểm tra vật phẩm rớt ra
    public void checkDrop(){
        
    }
    
    public void dropItem(Entity droppedItem) {
        for(int i = 0; i < ogp.obj[1].length; i++){
            if(ogp.obj[ogp.currentMap][i] == null){
                ogp.obj[ogp.currentMap][i] = droppedItem;
                ogp.obj[ogp.currentMap][i].worldX = worldX; //the dead monster's worldX
                ogp.obj[ogp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }
    
    public Color getParticleColor(){
        Color color = null;
        return color;
    }
    
    public int getParticleSize(){
        int size = 0; //0 pixels
        return size;
    }
    
    public int getParticleSpeed(){
        int speed = 0;
        return speed;
    }
    
    public int getParticleMaxLife(){
        int maxLife = 0;
        return maxLife;
    }
    
    public void generateParticle(Entity generator, Entity target){
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();
        
        Particle p1 = new Particle(ogp, target, color, size, speed, maxLife, -2,-1);
        Particle p2 = new Particle(ogp, target, color, size, speed, maxLife, 2,-1);
        Particle p3 = new Particle(ogp, target, color, size, speed, maxLife, -2,1);
        Particle p4 = new Particle(ogp, target, color, size, speed, maxLife, 2,1);
        
        ogp.particleList.add(p1);
        ogp.particleList.add(p2);
        ogp.particleList.add(p3);
        ogp.particleList.add(p4);
    }
    
    public void checkCollision(){
        collisionOn = false;
        ogp.cChecker.checkTile(this);//kiemtra co va cham khong
        ogp.cChecker.checkObject(this, false);
        
        boolean contactPlayer = ogp.cChecker.checkPlayer(this);
        
        if(this.type == type_monster && contactPlayer == true){
            if(ogp.player.invincible == false){
                //co the nhan sat thuong
                ogp.playSE(6);
                int damage = attack - ogp.player.defense;
                if(damage < 0){
                    damage = 0;
                }
                ogp.player.life -= damage;
//                ogp.player.life -= 1;
                ogp.player.invincible = true;
            }
              damagePlayer1(attack);
        }
        //nếu chỉ sử dụng như thế này thì npc hay monster đều tự va chạm với chính mình nên không di chuyển được
        ogp.cChecker.checkEntity(this, ogp.npc);
        ogp.cChecker.checkEntity(this, ogp.monster);
        ogp.cChecker.checkEntity(this, ogp.iTile);
    }
    
    public void update() {
        
        if(sleep == false){
            if(knockBack == true){
                checkCollision();

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
            else{
                setAction();
                checkCollision();

                //IF COLLISION = FALSE, PLAYER can move
                if (collisionOn == false) {
                    switch (direction) {
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
                spiteCounter++;
                if (spiteCounter > 24) {
                    if (spiteNum == 1) {
                        spiteNum = 2;
                    } else if (spiteNum == 2) {
                        spiteNum = 1;
                    }
                    spiteCounter = 0;
                }
            }

            //CHECK TILE COLOLLISION
    //        collisionOn = false;
    //        ogp.cChecker.checkTile(this);//kiemtra co va cham khong
    //        ogp.cChecker.checkObject(this, false);
    //        boolean contactPlayer = ogp.cChecker.checkPlayer(this);
    //        
    //        if(this.type == type_monster && contactPlayer == true){
    //            if(ogp.player.invincible == false){
    //                //co the nhan sat thuong
    //                ogp.playSE(6);
    //                int damage = attack - ogp.player.defense;
    //                if(damage < 0){
    //                    damage = 0;
    //                }
    //                ogp.player.life -= damage;
    ////                ogp.player.life -= 1;
    //                ogp.player.invincible = true;
    //            }
    //              damagePlayer1(attack);
    //        }

    //        //nếu chỉ sử dụng như thế này thì npc hay monster đều tự va chạm với chính mình nên không di chuyển được
    //        ogp.cChecker.checkEntity(this, ogp.npc);
    //        ogp.cChecker.checkEntity(this, ogp.monster);
    //        ogp.cChecker.checkEntity(this, ogp.iTile);



            //tao bat tu
            if(invincible == true){
                invincibleCounter++;
                if(invincibleCounter > 60){
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
            if(shotAvailableCounter < 30){
                shotAvailableCounter++;
            }
            if(offBalance == true){
                offBalanceCounter++;
                if(offBalanceCounter > 60){
                    offBalance = false;
                    offBalanceCounter = 0;
                }
            }
        }
    }

    public void checkAttackOrNot(int rate, int straight, int horizontal){
        
        boolean targetInRange = false;
        int xDis = getXdistance(ogp.player);
        int yDis = getYdistance(ogp.player);
        
        switch(direction){
            case "W":
                if(ogp.player.getCenterY() < getCenterY() && yDis < straight && xDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "S":
                if(ogp.player.getCenterY() > getCenterY() && yDis < straight && xDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "A":
                if(ogp.player.getCenterX() < getCenterX() && xDis < straight && yDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "D":
                if(ogp.player.getCenterX() > getCenterX() && xDis < straight && yDis < horizontal){
                    targetInRange = true;
                }
                break;
        }
        if(targetInRange == true){
            //kiểm tra tấn công
            int i = new Random().nextInt(rate);
            if(i == 0){
                attacking = true;
                spiteNum = 1;
                spiteCounter = 0;
                shotAvailableCounter = 0;
            }
        }
    }
    
    public void checkShootOrNot(int rate, int shotInterval){
        int i = new Random().nextInt(rate);
        if(i ==0 && projectile.alive == false && shotAvailableCounter == 30){
            projectile.set(worldX, worldY, direction, true, this);
            //CHECK VACANCY
            for(int ii = 0; ii < ogp.projectile[1].length; ii++){
                if(ogp.projectile[ogp.currentMap][ii] == null){
                    ogp.projectile[ogp.currentMap][ii] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
        }
    }
    
    public void checkStartChasingOrNot(Entity target, int distance, int rate){
       
        if(getTileDistance(target) > distance){
            int i = new Random().nextInt(rate);
            if(i == 0){
                onPath = true;
            }
        }
    }
    
    public void checkStopChasingOrNot(Entity target, int distance, int rate){
        
        if(getTileDistance(target) > distance){
            int i = new Random().nextInt(rate);
            if(i == 0){
                onPath = false;
            }
        }
    } 
    
    public void getRandomDirection(int interval){
        actionLockCounter++;
        if (actionLockCounter > interval) {
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
        }
    }
    
    public void moveTowardPlayer(int interval){
        actionLockCounter++;
        
        if (actionLockCounter > interval) {
            
            if(getXdistance(ogp.player)> getYdistance(ogp.player)){
                if(ogp.player.getCenterX() < getCenterX()){
                    direction = "A";
                }
                else{
                    direction = "D";
                }
            }
            else if(getXdistance(ogp.player) < getYdistance(ogp.player)){
                if(ogp.player.getCenterY() < getCenterY()){
                    direction = "W";
                }
                else{
                    direction = "S";
                }
            }
            actionLockCounter = 0;
        }
    }
    
    public String getoppositeDirection(String direction){
        
        String oppositeDirection = "";
        
        switch(direction){
            case "W": oppositeDirection = "S"; break;
            case "S": oppositeDirection = "W"; break;
            case "A": oppositeDirection = "D"; break;
            case "D": oppositeDirection = "A"; break;
            case "AW": oppositeDirection = "S"; break;
            case "SD": oppositeDirection = "W"; break;
            case "WD": oppositeDirection = "S"; break;
            case "SA": oppositeDirection = "W"; break;
        }
        return oppositeDirection;
    }
    
    public void attackingP(){
            spiteCounter++;
            if(spiteCounter <= motion1_duration){
                spiteNum = 1;
            }
            if(spiteCounter > motion1_duration && spiteCounter <= motion2_duration){
                spiteNum = 2;
                
                //Lưu vị trí hiện tại của player
                int currentWorldX = worldX;
                int currentWorldY = worldY;
                int solidAreaWidth = solidArea.width;
                int solidAreaWHeight = solidArea.height;//chuyển vị trí va chạm từ người chơi qua vị trí của vũ khí
                
                //Tạo vị trí mới theo vũ khí
                switch(direction){
                    case"W": worldY -= attackArea.height; break;
                    case"A": worldX -= attackArea.width; break;
                    case"S": worldY += attackArea.height; break;
                    case"D": worldX += attackArea.width; break; 
                    case"SD":
                    {
                        worldX += attackArea.width; 
                        worldY += attackArea.height;
                        break; 
                    }
                    case"SA": 
                    {
                        worldX -= attackArea.width ;
                        worldY += attackArea.height;
                        break; 
                    } 
                    case"WD":{
                        worldX += attackArea.width;
                        worldY -= attackArea.height;
                        break;
                    }  
                    case"AW":
                    {
                        worldX -= attackArea.width;
                        worldY -= attackArea.height;
                        break; 
                    } 
                    
                }
                //khu vực tấn công trờ thành khu vực va chạm, 
                solidArea.width = attackArea.width;
                solidArea.height = attackArea.height;
                
                if(type == type_monster){
                    if(ogp.cChecker.checkPlayer(this) == true){
                        damagePlayer1(attack);
                    }
                }
                else{
                    //player
                    //va chạm bị trừ máuu
                    int monsterIndex = ogp.cChecker.checkEntity(this, ogp.monster);
                    ogp.player.damageMonster(monsterIndex,this,attack,currentWeapon.knockBackPower);
                
                    int iTileIndex = ogp.cChecker.checkEntity(this, ogp.iTile);
                    ogp.player.damageInteractiveTile(iTileIndex);
                
                    int projectileIndex = ogp.cChecker.checkEntity(this, ogp.projectile);
                    ogp.player.damageProjectile(projectileIndex);
                }
                

                
                //sau khi kiem tra va chạm thì trả lại vị trí default người chơi
                worldX = currentWorldX;
                worldY = currentWorldY;
                solidArea.width  = solidAreaWidth;
                solidArea.height = solidAreaWHeight;
                
                
                
            }
            if(spiteCounter > motion2_duration){
                spiteNum = 1;
                spiteCounter = 0;
                attacking = false;
            }
            
  
    }
    
    public void damagePlayer1(int attack){
         if(ogp.player.invincible == false){
                int damage = attack - ogp.player.defense;
                
                //Có thể phòng thủ với đòn tấn công của quái
                String canGuardDirection = getoppositeDirection(direction);
                
                if(ogp.player.guarding == true && ogp.player.direction.equals(canGuardDirection)){
                    
                    //Parry(trạng thái choáng)
                    if(ogp.player.guardCounter < 10){
                        damage = 0;
                        ogp.playSE(16);
                        setKnockBack(this, ogp.player, knockBackPower);
                        spiteCounter =- 60;
                    }
                    //trạng thái bình thường
                    else{
                        damage /= 3;
                        ogp.playSE(15);
                    }
                }
                else{
                    //Không phòng thủ
                    //co the nhan sat thuong
                    ogp.playSE(6);

                    if(damage < 1){
                        damage = 1;
                    }
                }
                if(damage != 0){
                    ogp.player.transparent = true;
                    setKnockBack(ogp.player, this, knockBackPower);
                }
                
                ogp.player.life -= damage;
//                ogp.player.life -= 1;
                ogp.player.invincible = true;
            }
    }
    
    public  void setKnockBack(Entity target, Entity attacker ,int knockBackPower){
        
//        entity.direction = direction;
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
        
    }
    
    public boolean inCamera(){
        boolean inCamera = false;
        if (worldX + ogp.tileSizeW*5 > ogp.player.worldX - ogp.player.screenX
                && worldX - ogp.tileSizeW < ogp.player.worldX + ogp.player.screenX
                && worldY + ogp.tileSizeH*5 > ogp.player.worldY - ogp.player.screenY
                && worldY - ogp.tileSizeH < ogp.player.worldY + ogp.player.screenY){
            inCamera = true;
        }
        return inCamera;
    }
    
    //Hàm vẽ NPC Oldman
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
//        final int screenX = this.worldX - ogp.player.worldX + ogp.player.screenX;
//        final int screenY = this.worldY - ogp.player.worldY + ogp.player.screenY;
//        if (worldX + ogp.tileSizeW*5 > ogp.player.worldX - ogp.player.screenX
//                && worldX - ogp.tileSizeW < ogp.player.worldX + ogp.player.screenX
//                && worldY + ogp.tileSizeH*5 > ogp.player.worldY - ogp.player.screenY
//                && worldY - ogp.tileSizeH < ogp.player.worldY + ogp.player.screenY) {
            if (inCamera()== true) {
                int tempScreenX = getScreenX();
                int tempScreenY = getScreenY();

                switch (direction) {
                     case "W" -> {
                        if (spiteNum == 1) {
                            image = W1;
                        }
                        if (spiteNum == 2) {
                            image = W2;
                        }
                        if(attacking == true){
                             tempScreenY  = getScreenY() - W1.getHeight();
                        if (spiteNum == 1) {image = W1_AT;}
                        if (spiteNum == 2) {image = W2_AT;}
                    }
                }

                case "A" -> {
                    if (spiteNum == 1) {
                        image = A1;
                    }
                    if (spiteNum == 2) {
                        image = A2;
                    }
                    if(attacking == true){
                          tempScreenX = getScreenX() - A1.getWidth();
                        if (spiteNum == 1) {image = A1_AT;}
                        if (spiteNum == 2) {image = A2_AT;}
                    }
                }
                case "S" -> {
                    if (spiteNum == 1) {
                        image = S1;
                    }
                    if (spiteNum == 2) {
                        image = S2;
                    }
                     if(attacking == true){
                        if (spiteNum == 1) {image = S1_AT;}
                        if (spiteNum == 2) {image = S2_AT;}
                    }
                }
                case "D" -> {
                    if (spiteNum == 1) {
                        image = D1;
                    }
                    if (spiteNum == 2) {
                        image = D2;
                    }
                     if(attacking == true){
                        if (spiteNum == 1) {image = D1_AT;}
                        if (spiteNum == 2) {image = D2_AT;}
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
            
//            //Monster HP Bar : thanh máu
//            if(type == 2 && hpBarOn == true){
//                //biến chia chiều dài thanh máu vs số máu tối đa
//                double oneScale = (double)ogp.tileSizeW/maxLife;
//                //Biến từng khoảng máu theo số máu hiện tại
//                double hpBarValue = oneScale*life;
////                Ví dụ thanh máu dài 48 mà số máu là 2 thì sẽ có 2 khoảng 24
////                 Nếu thanh máu là 4 thì sẽ có 4 khoảng 12
//                
//                
//                
//                g2.setColor(new Color(35,35,35));
//                g2.fillRect(screenX-1, screenY-16, ogp.tileSizeH+2, 12);
//                g2.setColor(new Color(255,0,30));
//                g2.fillRect(screenX , screenY- 15, (int)hpBarValue,10);
//                
//                hpBarCounter++;
//                if(hpBarCounter > 300){
//                    hpBarCounter = 0;
//                    hpBarOn = false;
//                }
//            }

            
            //Hiệu ứng nhấp nháy
            if(invincible == true){
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2,0.4f);
            }
            if(dying == true){
                dyingAnimation(g2);
            }
            g2.drawImage(image, tempScreenX, tempScreenY, null);
            
            changeAlpha(g2,1f);
        }
    }
    
    //Hàm này hoạt động:
    //Cứ sau 5 frame , tạo hiệu ứng nhấp nháy cho quái vật
    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        
        int  i = 5;//sau chi can thay doi i 
        if(dyingCounter <= i){ changeAlpha(g2,0f);}
        if(dyingCounter > i && dyingCounter <= i*2){changeAlpha(g2,1f);}
        if(dyingCounter > i*2 && dyingCounter <= i*3){changeAlpha(g2,0f);}
        if(dyingCounter > i*3 && dyingCounter <= i*4){changeAlpha(g2,1f);}
        if(dyingCounter > i*4 && dyingCounter <= i*5){changeAlpha(g2,0f);}
        if(dyingCounter > i*5 && dyingCounter <= i*6){changeAlpha(g2,1f);}
        
        if(dyingCounter > 2*6){
//            dying = false;
            alive = false;
        }
        
    }
    public void changeAlpha(Graphics2D g2, float alphaValue){
         g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public BufferedImage setup(String imagePath, int width, int height){
        UtililyTool uTool = new UtililyTool();
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image , width, height);
        }catch(IOException e){
        }
        return image;
    }
    public void searchPath(int goalCol, int goalRow){
        int startCol = (worldX + solidArea.x)/ogp.tileSizeW;
        int startRow = (worldY + solidArea.y)/ogp.tileSizeH;
        
        ogp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);
        if(ogp.pFinder.search() == true){
            
            //next worldX & worldY
            int nextX = ogp.pFinder.pathList.get(0).col * ogp.tileSizeW;
            int nextY = ogp.pFinder.pathList.get(0).row * ogp.tileSizeH;
            
            //Entity's solidArea position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;
            
            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + ogp.tileSizeW){
                direction = "W";
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + ogp.tileSizeW){
                direction = "S";
            }
            else if(enTopY >= nextY && enBottomY < nextY + ogp.tileSizeH){
                //left or right
                if(enLeftX > nextX){
                    direction = "A";
                }
                if(enLeftX < nextX){
                    direction = "D";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX){
                // up or left 
                direction = "W";
                checkCollision();
                if(collisionOn == true){
                    direction = "A";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX){
                // up or right 
                direction = "W";
                checkCollision();
                if(collisionOn == true){
                    direction = "D";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX){
                // down or left 
                direction = "S";
                checkCollision();
                if(collisionOn == true){
                    direction = "A";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX){
                // down or right 
                direction = "S";
                checkCollision();
                if(collisionOn == true){
                    direction = "D";
                }
            }
//            int nextCol = ogp.pFinder.pathList.get(0).col;
//            int nextRow = ogp.pFinder.pathList.get(0).row;
//            if(nextCol == goalCol && nextRow == goalRow){
//                onPath = false;
//            }
        }
    }
    
    public int getDetected(Entity user, Entity target[][], String targetName){
        
        int index = 999;
        
        //Check the surrounding object
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();
        
        switch(user.direction){
            case "W": nextWorldY = user.getTopY()-ogp.player.speed; break;
            case "S": nextWorldY = user.getBottomY()+ogp.player.speed; break;
            case "A": nextWorldY = user.getLeftX()-ogp.player.speed; break;
            case "D": nextWorldY = user.getRightX()+ogp.player.speed; break;
        }
        int col = nextWorldX/ogp.tileSizeW;
        int row = nextWorldY/ogp.tileSizeH;
        
        for(int i = 0; i < target[1].length; i++){
            if(target[ogp.currentMap][i] != null){
                if(target[ogp.currentMap][i].getCol() == col &&
                        target[ogp.currentMap][i].getRow() == row &&
                        target[ogp.currentMap][i].name.equals(targetName)){
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    public boolean hasBeenCollected() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
