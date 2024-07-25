package oneplayer;

import entity.Entity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;

public class UI {

    O_GamePanel ogp;
    Graphics2D g2;
    Font arial_40;
    Font arial_30;
    Font arial_80B;
    Font candara, purisa_bold, long_beach, mangoberry;
    public Font maruMonica = new Font("Arial", Font.PLAIN, 12);
    BufferedImage keyImage, screenImage;
    BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank, coin;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    ArrayList<String> message1 = new ArrayList<>();
    ArrayList<Integer> messageCounter1 = new ArrayList<>();
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public String currentDialogue = "";
    //Hiển thị vị trí đang chọn của người chơi trên các option title screen 
    public int commandNum = 0;
    //Thêm các title screen phụ như chọn nhân vật,...
    public int titleScreenState = 0;
    //Bản giao diện item
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;

    //State cho subScreen
    int subState = 0;
    int counter = 0;
    int charIndex = 0;
    String combinedText = "";
    
    public Entity npc;
    

    public UI(O_GamePanel gp) {
        this.ogp = gp;
        arial_30 = new Font("Candara", Font.PLAIN, 30);
        arial_40 = new Font("Candara", Font.PLAIN, 40);
        arial_80B = new Font("Candara", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
        //Add font vo file code

        try {
            InputStream is = getClass().getResourceAsStream("/res/font/Purisa_Bold.ttf");
            purisa_bold = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/res/font/LNTH-TwistyPixel.ttf");
            long_beach = Font.createFont(Font.TRUETYPE_FONT, is);
            
            InputStream as = getClass().getResourceAsStream("/res/font/SVN-Mangoberry.otf");
            mangoberry=Font.createFont(Font.TRUETYPE_FONT, as);
        } catch (FontFormatException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //CREATE HUD OBJECT
        Entity heart = new OBJ_Heart(ogp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        Entity crystal = new OBJ_ManaCrystal(ogp);
        crystal_full = crystal.image;
        crystal_blank = crystal.image2;
        Entity bronzeCoin = new OBJ_Coin_Bronze(ogp);
        coin = bronzeCoin.S1;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    
    public void addMessage(String text) {
        message1.add(text);
        messageCounter1.add(0);
    }

    //Instantiating objetcs in game loop is not recommended 
    //Because creating instance take time and draw method will be included in the game loop, so this will get called 60times/sec 
    //=> it will consume time and resource 
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(mangoberry);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);
        //TITLE STATE
        if (ogp.gameState == ogp.titleState) {
            drawTitleScreen();//Tạo màn hình 
        }
        //STATE: PLAY
        if (ogp.gameState == ogp.playState) {
            if (gameFinished == true) {
                String text;
                int textLength;
                int x, y;

                //Display: You found treasure
                g2.setFont(mangoberry);
                g2.setColor(Color.white);

                text = "You found the treasure";
                textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = ogp.screenWidth / 2 - textLength / 2;
                y = ogp.screenHeight / 2 - (ogp.tileSizeH * 3);
                g2.drawString(text, x, y);

                //Display: Time
                text = "Your time is: " + dFormat.format(playTime) + "!";
                textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = ogp.screenWidth / 2 - textLength / 2;
                y = ogp.screenHeight / 2 + (ogp.tileSizeH * 4);
                g2.drawString(text, x, y);

                //Display: Congratulation
                g2.setFont(mangoberry);
                g2.setColor(Color.PINK);

                text = "Congratulations!";
                textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = ogp.screenWidth / 2 - textLength / 2;
                y = ogp.screenHeight / 2 + (ogp.tileSizeH * 2);
                g2.drawString(text, x, y);

                //Stop game
                ogp.gameThread = null;

            } else {
                //Heart-Thanh sinh lực
                drawPlayerLife();
                drawMonsterLife();
                //Key
//                g2.setFont(arial_40);
//                g2.setColor(Color.BLUE);
//                g2.drawImage(keyImage, ogp.tileSizeW / 2, ogp.tileSizeH / 2, ogp.tileSizeW, ogp.tileSizeH, null);
//                g2.drawString("x " + ogp.player.hasKey, 74, 105);

                //DISPLAY TIME
                playTime += (double) 1 / 60;//thêm 1/60s vào playTime sau 1 lần lặp, phương thức gọi 60 lần/s
                g2.drawString("Giờ: " + dFormat.format(playTime), ogp.tileSizeW * 15, 65);

                //MESSAGE
                if (messageOn == true) {
                    g2.setFont(g2.getFont().deriveFont(30F));
                    g2.drawString(message, ogp.tileSizeW / 2, ogp.tileSizeH * 5);
                    messageCounter++;

                    if (messageCounter > 120) {//khi gọi 120 khung hình thì message biến mất, mà mỗi khung hình 1s => 2s
                        messageCounter = 0;
                        messageOn = false;
                    }
                }

            }
            drawMessage();
        }
        //STATE: PAUSE
        if (ogp.gameState == ogp.pauseState) {
            drawPauseScreen();
            drawPlayerLife();
        }

        //STATE:DIALOGUE
        if (ogp.gameState == ogp.dialogueState) {
//            drawPlayerLife();
            drawDialogueScreen();
        }

        //CHARACTER STATE
        if (ogp.gameState == ogp.characterState) {
            drawCharacterScreen();
            drawInventory(ogp.player,true);
        }
        
        //OPTION STATE
        if (ogp.gameState == ogp.optionState) {
            drawOptionScreen();
        }
        
        //GAME OVER STATE
        if (ogp.gameState == ogp.gameOverState) {
            drawGameOverScreen();
        }
        
        //TRANSITION STATE
        if (ogp.gameState == ogp.transitionState) {
            drawTransition();
        }
        //TRADE STATE
        if (ogp.gameState == ogp.tradeState) {
            drawTradeScreen();
        }
        //SLEEP STATE
        if (ogp.gameState == ogp.sleepState) {
            drawSleepScreen();
        }
    }

    public void drawMonsterLife(){
        //Monster HP Bar : thanh máu
        for(int i = 0; i < ogp.monster[1].length;i++){
            
            Entity monster = ogp.monster[ogp.currentMap][i];
            
                    if(monster != null && monster.inCamera() == true){
                        if(monster.hpBarOn == true && monster.boss == false){
                        //biến chia chiều dài thanh máu vs số máu tối đa
                        double oneScale = (double)ogp.tileSizeW/monster.maxLife;
                        //Biến từng khoảng máu theo số máu hiện tại
                        double hpBarValue = oneScale*monster.life;
        //                Ví dụ thanh máu dài 48 mà số máu là 2 thì sẽ có 2 khoảng 24
        //                 Nếu thanh máu là 4 thì sẽ có 4 khoảng 12



                        g2.setColor(new Color(35,35,35));
                        g2.fillRect(monster.getScreenX()-1, monster.getScreenY()-16, ogp.tileSizeH+2, 12);
                        g2.setColor(new Color(255,0,30));
                        g2.fillRect(monster.getScreenX() , monster.getScreenY()- 15, (int)hpBarValue,10);

                        monster.hpBarCounter++;
                        if(monster.hpBarCounter > 300){
                            monster.hpBarCounter = 0;
                            monster.hpBarOn = false;
                        }
                    }
                    else if(monster.boss == true){
                         double oneScale = (double)ogp.tileSizeW*8/monster.maxLife;
                        //Biến từng khoảng máu theo số máu hiện tại
                        double hpBarValue = oneScale*monster.life;
                        
                        int x = ogp.screenWidth/2 - ogp.tileSizeW*4;
                        int y = ogp.tileSizeH*10;
                        
                        g2.setColor(new Color(35,35,35));
                        g2.fillRect(x-1,y-1 , ogp.tileSizeH*8+2, 22);
                        g2.setColor(new Color(255,0,30));
                        g2.fillRect( x,y , (int)hpBarValue,20);
                        
                        g2.setFont(g2.getFont().deriveFont(Font.BOLD,24f));
                        g2.setColor(Color.white);
                        g2.drawString(monster.name, x+4, y-10);
                    }
            }
        }
            
    }
    
    public void drawMessage(){
        int messageX = ogp.tileSizeW;
        int messageY = ogp.tileSizeH*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25F));
        
        for(int i = 0; i < message1.size(); i++){
            g2.setColor(Color.black);
            g2.drawString(message1.get(i),messageX+2, messageY+2);
            g2.setColor(Color.red);
            g2.drawString(message1.get(i),messageX, messageY);
            
            int counter = messageCounter1.get(i) + 1;
            messageCounter1.set(i, counter);
            messageY += 50;
            
            if(messageCounter1.get(i) > 180){
                message1.remove(i);
                messageCounter1.remove(i);
            }
        }
    }
    
    public void drawPlayerLife() {
//      ogp.player.life = 3;  Test khi bị tấn công
        int x = ogp.tileSizeW / 2;
        int y = ogp.tileSizeH / 2;
        int i = 0;
        //while vẽ trái tim
        //DRAW BLANK HEART
        while (i < ogp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += ogp.tileSizeW;
        }
        //RESET
        x = ogp.tileSizeW / 2;
        y = ogp.tileSizeH / 2;
        i = 0;
        //DRAW CURRENT LIFE
        while (i < ogp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < ogp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += ogp.tileSizeH;
        }
        //DRAW MAX MANA
        x = (ogp.tileSizeW / 2) - 5;
        y = (int)(ogp.tileSizeH * 1.5);
        i = 0;
        while (i < ogp.player.maxMana) {
            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x += 35;
        }
        //DRAW MANA
        x = (ogp.tileSizeW / 2) - 5;
        y = (int)(ogp.tileSizeH * 1.5);
        i = 0;
        while (i < ogp.player.mana) {
            g2.drawImage(crystal_full, x, y, null);
            i++;
            x += 35;
        }
    }

    public void drawTitleScreen() {
        if (titleScreenState == 0) {
            BufferedImage imgBG = null;
            try {
                imgBG = ImageIO.read(getClass().getResourceAsStream("/res/maps/bg_screen.png"));
                imgBG.getScaledInstance(ogp.screenWidth, ogp.screenHeight, BufferedImage.SCALE_DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            g2.drawImage(imgBG, 0, 0, ogp.screenWidth, ogp.screenHeight, null);
//            g2.setColor(new Color(182,179,197));
//            g2.fillRect(0, 0, ogp.screenWidth, ogp.screenHeight);
            //TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
            String text = "COUNTERSTRIKE JOURNEY";
            int x = getXforCenteredText(text);
            int y = ogp.tileSizeH * 2;
            //TITLE SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text, x + 5, y + 5);
            //MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            //NHAN VAT CHINH tren TITLE SCREEN
            x = ogp.screenWidth / 2 - (ogp.tileSizeH * 2) / 2;
            y += ogp.tileSizeH * 2;
            g2.drawImage(ogp.player.S1, x, y, ogp.tileSizeW * 2, ogp.tileSizeH * 2, null);
            //MENU 
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
            text = "BẮT ĐẦU MỚI";
            x = getXforCenteredText(text);
            y += ogp.tileSizeH * 4;
            g2.drawString(text, x, y);
            //nếu đang ở vị trí option NEW GAME thì sẽ có mũi tên hoặc hình ảnh..
            //Việc thay đổi các lựa chọn sẽ ở trong key handler
            if (commandNum == 0) {
                g2.drawString(">", x - ogp.tileSizeW, y);
            }

            text = "TẢI LẠI";
            x = getXforCenteredText(text);
            y += ogp.tileSizeH * 1.5;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - ogp.tileSizeW, y);
            }
            text = "THOÁT";
            x = getXforCenteredText(text);
            y += ogp.tileSizeH * 1.5;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - ogp.tileSizeW, y);
            }
        } else if (titleScreenState == 1) {//1 là màn hình chọn nhân vật
            BufferedImage imgBG = null;
            try {
                imgBG = ImageIO.read(getClass().getResourceAsStream("/res/maps/bg_screen.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g2.drawImage(imgBG, 0, 0, ogp.screenWidth, ogp.screenHeight, null);

//            g2.setColor(new Color (179, 226, 205));
//            g2.fillRect(0, 0, ogp.screenWidth, ogp.screenHeight);
            //TITLE NAME
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(60F));
            String text = "Chọn nhân vật:";
            int x = getXforCenteredText(text);
            int y = ogp.tileSizeH * 3;
            g2.drawString(text, x, y);

            text = "Windy";
            x = getXforCenteredText(text);
            y += ogp.tileSizeH * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - ogp.tileSizeH * 2, y);
                g2.drawImage(ogp.player.S1, x + ogp.tileSizeH * 3, y - ogp.tileSizeW * 3 / 2, ogp.tileSizeW * 2, ogp.tileSizeH * 2, null);
            }
            text = "Aqua";
            x = getXforCenteredText(text);
            y += ogp.tileSizeH * 1.5;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - ogp.tileSizeH * 2, y);
                g2.drawImage(ogp.player.SS1, x + ogp.tileSizeH * 3, y - ogp.tileSizeW * 3 / 2, ogp.tileSizeW * 2, ogp.tileSizeH * 2, null);
            }
            text = "Quay lại";
            x = getXforCenteredText(text);
            y += ogp.tileSizeH * 1.5;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - ogp.tileSizeW, y);
            }
        }

    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "TẠM DỪNG";
        int x = getXforCenteredText(text);
        int y = ogp.screenHeight / 2;
        g2.drawString(text, x, y);

    }

    public void drawDialogueScreen() {
        //Create window
        int x = ogp.tileSizeW * 3; //ô tile thứ 2
        int y = ogp.tileSizeH / 2;
        int width = ogp.screenWidth - (ogp.tileSizeW * 6); //nhỏ hơn 4 tile so với screen
        int height = ogp.tileSizeH * 4;
        drawSubWindow(x, y, width, height);

        //Text
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));

        x += ogp.tileSizeW / 2;//tăng toạ độ x vào 1 ô
        y += ogp.tileSizeH;

        if(npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null){
            currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
            
            char characters[] = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();
            
            if(charIndex < characters.length){
                ogp.playSE(17);
                String s = String.valueOf(characters[charIndex]);
                combinedText = combinedText + s;
                currentDialogue = combinedText;
                charIndex++;
            }
            
            if(ogp.keyH.enterPressed == true){
                  charIndex = 0;
                combinedText = "";    
                if(ogp.gameState == ogp.dialogueState || ogp.gameState == ogp.cutsceneState){
                    
                    npc.dialogueIndex++;
                    ogp.keyH.enterPressed = false;
                }
            }
        }
        else{//If no text is in the array
            npc.dialogueIndex = 0;
            
            if(ogp.gameState == ogp.dialogueState){
                ogp.gameState = ogp.playState;
            }
            if(ogp.gameState == ogp.cutsceneState){
                ogp.csManager.scenePhase++;
            }
        }
        //Khi gặp \n thì cắt chuỗi vào tăng y lên
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    public void drawCharacterScreen() {
        //CREATE A FRAME
        final int frameX = ogp.tileSizeH * 2;
        final int frameY = ogp.tileSizeW;
        final int frameW = ogp.tileSizeW * 5;
        final int frameH = ogp.tileSizeH * 10;
        drawSubWindow(frameX, frameY, frameW, frameH);

        //TEXT ON FRAME
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25F));
        int textX = frameX + 20;
        int textY = frameY + ogp.tileSizeH;
        final int lineHeight = 35;

        //NAMES
        g2.drawString("Cấp độ", textX, textY);
        textY += lineHeight;
        g2.drawString("Sinh lực", textX, textY);
        textY += lineHeight;
        g2.drawString("Năng lượng", textX, textY);
        textY += lineHeight;
        g2.drawString("Sức mạnh", textX, textY);
        textY += lineHeight;
        g2.drawString("Khéo léo", textX, textY);
        textY += lineHeight;
        g2.drawString("Lực chiến", textX, textY);
        textY += lineHeight;
        g2.drawString("Phòng thủ", textX, textY);
        textY += lineHeight;
        g2.drawString("Kinh nghiệm", textX, textY);
        textY += lineHeight;
        g2.drawString("Cấp độ mới", textX, textY);
        textY += lineHeight;
        g2.drawString("Tiền", textX, textY);
        textY += lineHeight + 10;
        g2.drawString("Vũ khí", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Khiên", textX, textY);
        textY += lineHeight;

        //VALUES
        int tailX = (frameX + frameW) - 30;

        //Reset textY
        textY = frameY + ogp.tileSizeH;
        String value;

        value = String.valueOf(ogp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(ogp.player.life + "/" + ogp.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(ogp.player.mana + "/" + ogp.player.maxMana);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(ogp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(ogp.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(ogp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(ogp.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(ogp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(ogp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        value = String.valueOf(ogp.player.coin);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(ogp.player.currentWeapon.S1, tailX - ogp.tileSizeH, textY - 24, null);
        textY += ogp.tileSizeW;
        g2.drawImage(ogp.player.currentShield.S1, tailX - ogp.tileSizeH, textY - 24, null);
    }

    public void drawInventory(Entity entity, boolean cursor){
        
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;
        
        if(entity == ogp.player){
            frameX = ogp.tileSizeW*12;
            frameY = ogp.tileSizeH;
            frameWidth = ogp.tileSizeW*6;
            frameHeight = ogp.tileSizeH*5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        }
        else {
            frameX = ogp.tileSizeW*2;
            frameY = ogp.tileSizeH;
            frameWidth = ogp.tileSizeW*6;
            frameHeight = ogp.tileSizeH*5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }
        
        //FRAME
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);
        
        //SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = ogp.tileSizeW + 3;
        
        
        //DRAW PLAYER'S ITEMS
        for(int i = 0; i < entity.inventory.size(); i++){
            //EQUIP CURSOR
            
            if(entity.inventory.get(i) == entity.currentWeapon ||
                    entity.inventory.get(i ) == entity.currentShield ||
                    entity.inventory.get(i) == entity.currentLight){
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(slotX,slotY,ogp.tileSizeH,ogp.tileSizeW,10,10);
            }
            
            g2.drawImage(entity.inventory.get(i).S1, slotX, slotY, null);
            
            //DISPLAY AMOUNT
            if(entity == ogp.player && entity.inventory.get(i).amount > 1){
                
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25F));
                int amountX;
                int amountY;
                
                String s = "" + entity.inventory.get(i).amount;
                amountX = getXforAlignToRightText(s,slotX + 44);
                amountY = slotY + ogp.tileSizeH;
                
                //SHADOW
                g2.setColor(new Color(60,60,60));
                g2.drawString(s, amountX, amountY);
                
                //NUMBER
                g2.setColor(Color.WHITE);
                g2.drawString(s, amountX-3, amountY-3);
            }
            
            slotX += slotSize;
            
            if(i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }
        
        //CURSOR
        if(cursor == true){
            int cursorX = slotXstart + (slotSize * slotCol);
            int cursorY = slotYstart + (slotSize * slotRow);
            int cursorWidth = ogp.tileSizeW;
            int cursorHeight = ogp.tileSizeH;

            //DRAW CURSOR
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX,cursorY,cursorWidth,cursorHeight,10,10);

            //DESCRIPTION FRAME
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;
            int dFrameWidth = frameWidth;
            int dFrameHeight = ogp.tileSizeH*3;


            //DRAW DESCRIPTION TEXT
            int textX = dFrameX + 20;
            int textY = dFrameY + ogp.tileSizeH;
            g2.setFont(g2.getFont().deriveFont(20F));// kích thước chữ cho phần chi tiết item
            
            int itemIndex = getItemIndexOnSlot(slotCol, slotRow);

            if(itemIndex < entity.inventory.size()){
                drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);

                for(String line: entity.inventory.get(itemIndex).description.split("\n")){
                    g2.drawString(line,textX,textY);
                    textY += 32;
                }
                
                //DURABILITY
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25F));
                g2.setColor(Color.RED);
                g2.drawString("Độ bền: "+ entity.inventory.get(itemIndex).durability, textX, textY+100);
            }
        }
    }
    
    public void drawTransition(){
        
        counter++;
        g2.setColor(new Color(0,0,0,counter*5));
        g2.fillRect(0, 0, ogp.screenWidth, ogp.screenHeight);
        
        if(counter == 50){ //the transition is done
            counter = 0;
            ogp.gameState = ogp.playState;
            ogp.currentMap = ogp.eHandler.tempMap;
            ogp.player.worldX = ogp.tileSizeW * ogp.eHandler.tempCol;
            ogp.player.worldY = ogp.tileSizeH * ogp.eHandler.tempRow;
            ogp.eHandler.previousEventX = ogp.player.worldX;
            ogp.eHandler.previousEventY = ogp.player.worldY;
            ogp.changArea();
        }
    }
    
    public void drawTradeScreen(){
         switch(subState){
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_sell(); break;
        }
        ogp.keyH.enterPressed = false;
    }
    
    public void trade_select(){
        npc.dialogueSet = 0;
        drawDialogueScreen();
        
        //DRAW WINDOW
        int x = ogp.tileSizeW * 15;
        int y = ogp.tileSizeH * 4;
        int width = ogp.tileSizeW * 3;
        int height = (int)(ogp.tileSizeH*3.5);
        drawSubWindow(x,y,width,height);
        
        //DRAW TEXTS
        x += ogp.tileSizeW;
        y += ogp.tileSizeH;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25F));
        g2.drawString("Mua", x, y);
        if(commandNum == 0){
            g2.drawString(">", x-24, y);
            if(ogp.keyH.enterPressed == true){
                subState = 1;
            }
        }
        y += ogp.tileSizeH;
        
        g2.drawString("Bán", x, y);
        if(commandNum == 1){
            g2.drawString(">", x-24, y);
            if(ogp.keyH.enterPressed == true){
                subState = 2;
            }
        }
        y += ogp.tileSizeH;
        
        g2.drawString("Thoát", x, y);
        if(commandNum == 2){
            g2.drawString(">", x-24, y);
            if(ogp.keyH.enterPressed == true){
                commandNum = 0;
                npc.startDialogue(npc,1);
//                ogp.gameState = ogp.dialogueState;
//                currentDialogue = "Nhớ ghé lại nha cục cưng!!!";
            }
        }
        y += ogp.tileSizeH;
    }
    
    public void trade_buy(){
        
        //DRAW PLAYER INVENTORY
        drawInventory(ogp.player,false);
        //DRAW NPC INVENTORY
        drawInventory(npc,true);
        
        //DRAW HINT WINDOW
        int x = ogp.tileSizeW*2;
        int y = ogp.tileSizeH*9;
        int width = ogp.tileSizeW*6;
        int height = ogp.tileSizeH*2;
        drawSubWindow(x,y,width,height);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25F));
        g2.drawString("[ESC] Thoát", x+24, y+60);
        
         //DRAW PLAYER COIN WINDOW
        x = ogp.tileSizeW*12;
        y = ogp.tileSizeH*9;
        width = ogp.tileSizeW*6;
        height = ogp.tileSizeH*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("Tiền bạn đang sở hữu: " + ogp.player.coin, x+24, y+60);
        
        //DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
        if(itemIndex < npc.inventory.size()){
            
            x = (int)(ogp.tileSizeW*5.5);
            y = (int)(ogp.tileSizeH*5.5);
            width=(int)(ogp.tileSizeW*2.5);
            height = ogp.tileSizeH;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin, x+10, y+8,32,32,null);
            
            int price = npc.inventory.get(itemIndex).price;
            String text = "" + price; 
            x = getXforAlignToRightText(text, ogp.tileSizeW*8-20);
            g2.drawString(text, x, y+34);
            
            //BUY AN ITEM
            if(ogp.keyH.enterPressed == true){
                if(npc.inventory.get(itemIndex).price > ogp.player.coin){
                    subState = 0;
                    npc.startDialogue(npc,2);
                }
                else{
                    if(ogp.player.canObtainItem(npc.inventory.get(itemIndex)) == true){
                        ogp.player.coin -= npc.inventory.get(itemIndex).price;
                    }
                    else{
                        subState = 0;
                        npc.startDialogue(npc,3);

                    }
                }

            }
        }
    }
    
    public void trade_sell(){
        
        //DRAW PLAYER INVENTORY
        drawInventory(ogp.player, true);
        
        int x;
        int y;
        int width;
        int height;
        
                //DRAW HINT WINDOW
        x = ogp.tileSizeW*2;
        y = ogp.tileSizeH*9;
        width = ogp.tileSizeW*6;
        height = ogp.tileSizeH*2;
        drawSubWindow(x,y,width,height);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25F));
        g2.drawString("[ESC] Thoát", x+24, y+60);
        
         //DRAW PLAYER COIN WINDOW
        x = ogp.tileSizeW*12;
        y = ogp.tileSizeH*9;
        width = ogp.tileSizeW*6;
        height = ogp.tileSizeH*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("Tiền đang sở hữu: " + ogp.player.coin, x+24, y+60);
        
        //DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
        if(itemIndex < ogp.player.inventory.size()){
            
            x = (int)(ogp.tileSizeW*15.5);
            y = (int)(ogp.tileSizeH*5.5);
            width=(int)(ogp.tileSizeW*2.5);
            height = ogp.tileSizeH;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin, x+10, y+8,32,32,null);
            
            int price = ogp.player.inventory.get(itemIndex).price/2;
            String text = "" + price; 
            x = getXforAlignToRightText(text, ogp.tileSizeW*18-20);
            g2.drawString(text, x, y+34);
            
            //SELL AN ITEM
            if(ogp.keyH.enterPressed == true){
                
                if(ogp.player.inventory.get(itemIndex)== ogp.player.currentWeapon ||
                        ogp.player.inventory.get(itemIndex) == ogp.player.currentShield){
                    commandNum = 0;
                    subState = 0;
                    npc.startDialogue(npc,4);

                }
                else {
                    if(ogp.player.inventory.get(itemIndex).amount > 1){
                        ogp.player.inventory.get(itemIndex).amount--;
                    }
                    else{
                        ogp.player.inventory.remove(itemIndex);
                    }
                    
                    ogp.player.coin += price;
                }
            }
        }
        
    }
        
    public void drawSleepScreen(){
        counter++;
        
        if(counter < 120){
            
            ogp.eManager.lighting.filterAlpha += 0.01f;
            if(ogp.eManager.lighting.filterAlpha > 1f){
                ogp.eManager.lighting.filterAlpha = 1f;
            }
        }
        if(counter >= 120){
            ogp.eManager.lighting.filterAlpha -= 0.01f;
            if(ogp.eManager.lighting.filterAlpha <= 0f){
                ogp.eManager.lighting.filterAlpha = 0f;
                counter = 0;
                ogp.eManager.lighting.dayState = ogp.eManager.lighting.day;
                ogp.gameState = ogp.playState;
                if(ogp.player.name == "Windy"){
                    ogp.player.getImageP1();
                }
                else{
                    ogp.player.getPlayerImageP2();
                }

            }
        }
    }
    
    public int getItemIndexOnSlot(int slotCol, int slotRow){
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
    }
    
    public void drawGameOverScreen(){
        
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, ogp.screenWidth, ogp.screenHeight);
        
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));
        
        text = "Trò chơi kết thúc"; 
        
        //shadow
        x = getXforCenteredText(text);
        y = ogp.tileSizeH*4;
        
        //main
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);
        
        //retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Thử lại";
        x = getXforCenteredText(text);
        y += ogp.tileSizeH*4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x-40, y);
        }
        
        //back to the title screen
        text = "Thoát trò chơi";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x-40, y);
        }
    }
    
    public void drawOptionScreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        //SUB WINDOW
        int frameX = ogp.tileSizeW * 5 + 20;
        int frameY = ogp.tileSizeH;
        int frameWidth = ogp.tileSizeW * 9;
        int frameHeight = ogp.tileSizeH * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0:
                options_top(frameX, frameY);
                break;
            case 1:
                options_fullScreenNotification(frameX, frameY);
                break;
            case 2:
                options_control(frameX, frameY);
                break;
            case 3:
                options_endGameConfirmation(frameX, frameY);
                break;
        }
        ogp.keyH.enterPressed = false;

    }

    public void options_top(int frameX, int frameY) {
        int textX;
        int textY;
 g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        //TITLE
        String text = "Thiết lập";
        textX = getXforCenteredText(text);
        textY = frameY + ogp.tileSizeH;
        g2.drawString(text, textX, textY);

        //Full Screen On/Off
        textX = frameX + ogp.tileSizeW;
        textY += ogp.tileSizeH * 1+30;
        g2.drawString("Toàn màn hình", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (ogp.keyH.enterPressed == true) {
                if (ogp.fullScreenOn == false) {
                    ogp.fullScreenOn = true;
                } else if (ogp.fullScreenOn == true) {
                    ogp.fullScreenOn = false;
                }
                subState = 1;
            }

        }

        //Music    
        textY += ogp.tileSizeH+10;
        g2.drawString("Âm lượng nhạc nền", textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
        }
        //SE Volumn
        textY += ogp.tileSizeH+10;
        g2.drawString("Âm lượng trò chơi ", textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
        }
        //Control
         textY += ogp.tileSizeH+10;
        g2.drawString("Các phím", textX, textY);
        if (commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
            if (ogp.keyH.enterPressed == true) {
                subState = 2;
                commandNum = 0;
            }
        }
        //Endgame
       textY += ogp.tileSizeH+10;
        g2.drawString("Thoát trò chơi", textX, textY);
        if (commandNum == 4) {
            g2.drawString(">", textX - 25, textY);
            if (ogp.keyH.enterPressed == true) {
                subState = 3;
                commandNum = 0;
            }
        }
        //Back
        textY += ogp.tileSizeH * 2;
        g2.drawString("Quay lại", textX, textY);
        if (commandNum == 5) {
            g2.drawString(">", textX - 25, textY);
            if (ogp.keyH.enterPressed == true) {
                ogp.gameState = ogp.playState;
                commandNum = 0;
            }
        }

        //Full screen check box
        textX = frameX + ogp.tileSizeW * 6;
        textY = frameY + ogp.tileSizeH * 2 + 5;
        g2.setStroke(new BasicStroke(3));//thiết lập đường viền
        g2.drawRect(textX, textY, 24, 24);
        if (ogp.fullScreenOn == true) {
            g2.fillRect(textX, textY, 24, 24);
        }

        //Musio Volumne
        textY += ogp.tileSizeH+10;
        g2.drawRect(textX, textY, 120, 24); //->120/5 =24
        int volumneWidth = 24 * ogp.music.volumeScale;
        g2.fillRect(textX, textY, volumneWidth, 24);

        //SE Volumne
          textY += ogp.tileSizeH+10;
        g2.drawRect(textX, textY, 120, 24);
        volumneWidth = 24 * ogp.se.volumeScale;
        g2.fillRect(textX, textY, volumneWidth, 24);
        
        ogp.config.saveConfig();
    }

    public void options_fullScreenNotification(int frameX, int frameY) {
        int textX = frameX + ogp.tileSizeW;
        int textY = frameY + ogp.tileSizeH * 3;
        currentDialogue = "Thay đổi sẽ được áp dụng \n sau khi khởi động lại \n trò chơi";
        for (String line : currentDialogue.split("\n")) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        //Back
        textY = frameY + ogp.tileSizeH * 9;
        g2.drawString("Thoát", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (ogp.keyH.enterPressed == true) {
                subState = 0;
            }
        }

    }

    public void options_control(int frameX, int frameY) {
        int textX;
        int textY;

        //Title
        String text = "Các phím";
        textX = getXforCenteredText(text);
        textY = frameY + ogp.tileSizeH;
        g2.drawString(text, textX, textY);
        g2.setFont(g2.getFont().deriveFont(30F));
        textX = frameX + ogp.tileSizeW - 8;
        textY += ogp.tileSizeH;
        g2.drawString("Di chuyển", textX, textY);
        textY += ogp.tileSizeH;
        g2.drawString("Tương tác/Tấn công", textX, textY);
        textY += ogp.tileSizeH;
        g2.drawString("Bắn", textX, textY);
        textY += ogp.tileSizeH;
        g2.drawString("Trạng thái", textX, textY);
        textY += ogp.tileSizeH;
        g2.drawString("Tạm dừng", textX, textY);
        textY += ogp.tileSizeH;
        g2.drawString("Thiết lập", textX, textY);
        textY += ogp.tileSizeH;

        textX = frameX + ogp.tileSizeW * 6 + 29;
        textY = frameY + ogp.tileSizeH * 2;
        g2.drawString("WASD", textX, textY);
        textY += ogp.tileSizeH;
        g2.drawString("Enter", textX, textY);
        textY += ogp.tileSizeH;
        g2.drawString("F", textX, textY);
        textY += ogp.tileSizeH;
        g2.drawString("C", textX, textY);
        textY += ogp.tileSizeH;
        g2.drawString("P", textX, textY);
        textY += ogp.tileSizeH;
        g2.drawString("ESC", textX, textY);
        textY += ogp.tileSizeH;

        //Back
        textX = frameX + ogp.tileSizeW;
        textY = frameY + ogp.tileSizeH * 9;
        g2.drawString("Quay lại", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (ogp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 3;
            }
        }

    }

    public void options_endGameConfirmation(int frameX, int frameY) {
        int textX = frameX + ogp.tileSizeW;
        int textY = frameY + ogp.tileSizeH * 3;
        currentDialogue = "Thoát trò chơi và quay về \n màn hình khởi động?";
        for (String line : currentDialogue.split("\n")) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
            g2.drawString(line, textX, textY);
            textY += 40;

        }

        //Yes
        String text = "Đồng ý";
        textX = getXforCenteredText(text);
        textY += ogp.tileSizeH;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (ogp.keyH.enterPressed == true) {
                subState = 0;
                ogp.gameState = ogp.titleState;
                ogp.resetGame(true);
            }
        }
        //No
        text = "Quay lại";
        textX = getXforCenteredText(text);
        textY += ogp.tileSizeH;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (ogp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 4;
            }
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25F));
        Color c = new Color(0, 0, 0, 210);//Last number: adjust the opacity of the window
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5)); //Defines the width of outlines of graphics which are rendered
        g2.drawRoundRect(x + 3, y + 3, width - 6, height - 6, 25, 25);

    }

    //Hàm canh giữa màn hình theo X
    //Vì đối với chữ, sẽ lấy cạnh bên trái để canh, nên nếu chỉ để x=screenWidth/2 thì chữ sẽ lệch sang bên phải
    //Dùng hàm lấy ra chiều dài text sau đó xác định vị trí X giữa màn hình và trừ đi nửa chiều dài text là được
    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = ogp.screenWidth / 2 - length / 2;
        return x;

    }

    public int getXforAlignToRightText(String text, int tailX) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;

    }

}
