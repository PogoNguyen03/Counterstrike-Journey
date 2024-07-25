package oneplayer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    O_GamePanel ogp;
    //tạo biến lưu trạng thái nút đang được bấm
    public boolean upPressed, downPressed, leftPressed, rightPressed; //Biến cho các nút AWSD
    public boolean upArrowPressed, downArrowPressed, leftArrowPressed, rightArrowPressed, spacePressed; //Biến cho các nút mũi tên
    public boolean enterPressed;
    public boolean shotKeyPressed;
    
    //Debug
    boolean checkDrawTime = false;
    public boolean godModeOn = false;
    public KeyHandler(O_GamePanel ogp) {
        this.ogp = ogp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();//biến nhận giá trị bàn phím đc ấn 
        //TITLE SCREEN
        if (ogp.gameState == ogp.titleState) {
            titleState(code);
        } //PLAY STATE
        else if (ogp.gameState == ogp.playState) {
            playState(code);
        } //STATE: PAUSE
        else if (ogp.gameState == ogp.pauseState) {
            pauseState(code);
        } //STATE: DIALOG
        else if (ogp.gameState == ogp.dialogueState || ogp.gameState == ogp.cutsceneState) {
            dialogueState(code);
        } //CHARACTER STATE
        else if (ogp.gameState == ogp.characterState) {
            characterState(code);
        }//OPTION STATE
        else if (ogp.gameState == ogp.optionState) {
            optionState(code);
        }
        //GAME OVER STATE
        else if (ogp.gameState == ogp.gameOverState) {
            gameOverState(code);
        }
        //TRADE STATE
        else if (ogp.gameState == ogp.tradeState) {
            tradeState(code);
        }
        //MAP STATE
        else if (ogp.gameState == ogp.mapState) {
            mapState(code);
        }
    }

    public void titleState(int code) {
        if (ogp.ui.titleScreenState == 0) {
            if (code == KeyEvent.VK_W) {
                ogp.ui.commandNum--;
                if (ogp.ui.commandNum < 0) {
                    ogp.ui.commandNum = 2;
                }
            }
            //if lồng tạo hiệu ứng vòng lặp để giới hạn các option được chọn
            if (code == KeyEvent.VK_S) {
                ogp.ui.commandNum++;
                if (ogp.ui.commandNum > 2) {
                    ogp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                //enter vào 3 option
                if (ogp.ui.commandNum == 0) {
                    ogp.ui.titleScreenState = 1; //NewGame sẽ chuyển tới chọn nv
                    //ogp.playMusic(0); nếu tắt music trước đó thì h để đây
                }
                if (ogp.ui.commandNum == 1) {
                    ogp.saveLoad.load();
                    ogp.gameState = ogp.playState;
                }
                if (ogp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        } else if (ogp.ui.titleScreenState == 1) {//hiển thị trang chọn nv
            if (code == KeyEvent.VK_W) {
                ogp.ui.commandNum--;
                if (ogp.ui.commandNum < 0) {
                    ogp.ui.commandNum = 2;
                }
            }
            //if lồng tạo hiệu ứng vòng lặp để giới hạn các option được chọn
            if (code == KeyEvent.VK_S) {
                ogp.ui.commandNum++;
                if (ogp.ui.commandNum > 2) {
                    ogp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                //enter vào 3 option
                if (ogp.ui.commandNum == 0) {
                    ogp.gameState = ogp.playState;
                    ogp.player.setDefaultValuesP1();
                    ogp.player.getImageP1();
                    //ogp.playMusic(0); nếu tắt music trước đó thì h để đây
                }
                if (ogp.ui.commandNum == 1) {
                    ogp.gameState = ogp.playState;
                    ogp.player.setDefaultValuesP2();
                    ogp.player.getPlayerImageP2();
                }
                if (ogp.ui.commandNum == 2) {
                    ogp.ui.titleScreenState = 0;
                }
            }
        }
    }

    public void playState(int code) {
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_UP) {
            upArrowPressed = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            downArrowPressed = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftArrowPressed = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightArrowPressed = true;
        }
        if (code == KeyEvent.VK_C) {
            ogp.gameState = ogp.characterState;
        }

        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = true;
        }
        if (code == KeyEvent.VK_M) {
            ogp.gameState = ogp.mapState;
        }
        if (code == KeyEvent.VK_X) {
            if(ogp.map.miniMapOn == false){
                ogp.map.miniMapOn = true;
            }
            else{
                ogp.map.miniMapOn = false;
            }
        }
        if (code == KeyEvent.VK_SPACE) {
            if(ogp.player.name == "Windy"){
                spacePressed = true;
            }
            
        }
       
        //Key for Debug
        if (code == KeyEvent.VK_T) {
            if (checkDrawTime == true) {
                checkDrawTime = false;
            }
            checkDrawTime = true;
        }
        if(code == KeyEvent.VK_R){
            switch(ogp.currentMap){
                case 0: ogp.tileM.loadMap1P("/res/maps/world02.txt",0); break;
                case 1: ogp.tileM.loadMap1P("/res/maps/interior01.txt",1); break;
            }
        }
        if (code == KeyEvent.VK_G) {
            if (godModeOn == false) {
                godModeOn = true;
            }
            else if(godModeOn == true){
                godModeOn = false;
            }
        }
        //Key Pause
        if (code == KeyEvent.VK_P) {
            ogp.gameState = ogp.pauseState;
        }
        //Key for OptionMenu
        if (code == KeyEvent.VK_ESCAPE) {
            ogp.gameState = ogp.optionState;
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            ogp.gameState = ogp.playState;
        }
    }

    public void dialogueState(int code) {
        if (code == KeyEvent.VK_ENTER) {
//            ogp.gameState = ogp.playState;
            enterPressed = true;
        }
    }

    //âm thanh chuyển cho hộp item
    public void characterState(int code) {
        if (code == KeyEvent.VK_C) {
            ogp.gameState = ogp.playState;
        }
//        if(code == KeyEvent.VK_W){
//            if(ogp.ui.playerSlotRow != 0){
//                ogp.ui.playerSlotRow--;
//                ogp.playSE(8);
//            }
//        }
//        if(code == KeyEvent.VK_A){
//            if(ogp.ui.playerSlotCol != 0){
//                ogp.ui.playerSlotCol--;
//                ogp.playSE(8);
//            }         
//        }
//        if(code == KeyEvent.VK_S){
//            if(ogp.ui.playerSlotRow != 3){
//                ogp.ui.playerSlotRow++;
//                ogp.playSE(8);
//            }
//        }
//        if(code == KeyEvent.VK_D){
//            if(ogp.ui.playerSlotCol != 4){
//                ogp.ui.playerSlotCol++;
//                ogp.playSE(8);
//            }
//        }
        if(code == KeyEvent.VK_ENTER){
            ogp.player.selectItem();
        }
        playerInventory(code);
    }

    public void optionState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            ogp.gameState = ogp.playState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch (ogp.ui.subState) {
            case 0:
                maxCommandNum = 5;
                break;
            case 3:
                maxCommandNum = 1;
                break;
        }
        if (code == KeyEvent.VK_W) {
            ogp.ui.commandNum--;
            //ogp.playSE(9);
            if (ogp.ui.commandNum < 0) {
                ogp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_S) {
            ogp.ui.commandNum++;
            //ogp.playSE(9);
            if (ogp.ui.commandNum > maxCommandNum) {
                ogp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_A) {
            if (ogp.ui.subState == 0) {
                if (ogp.ui.commandNum == 1 && ogp.music.volumeScale > 0) {
                    ogp.music.volumeScale--;
                    ogp.music.checkVolumne();
                    //ogp.playSE(9);
                }
                if (ogp.ui.commandNum == 2 && ogp.se.volumeScale > 0) {
                    ogp.se.volumeScale--;

                    //ogp.playSE(9);
                }
            }
        }
        if (code == KeyEvent.VK_D) {
            if (ogp.ui.subState == 0) {
                if (ogp.ui.commandNum == 1 && ogp.music.volumeScale < 5) {
                    ogp.music.volumeScale++;
                    ogp.music.checkVolumne();
                    //ogp.playSE(9);
                }
                if (ogp.ui.commandNum == 2 && ogp.se.volumeScale < 5) {
                    ogp.se.volumeScale++;

                    //ogp.playSE(9);
                }
            }
        }
    }

    public void gameOverState(int code){
        if(code == KeyEvent.VK_W){
            ogp.ui.commandNum--;
            if(ogp.ui.commandNum < 0){
                ogp.ui.commandNum = 1;
            }
            ogp.playSE(8);
        }
        if(code == KeyEvent.VK_S){
            ogp.ui.commandNum++;
            if(ogp.ui.commandNum > 1){
                ogp.ui.commandNum = 0;
            }
            ogp.playSE(8);
        }
        if(code == KeyEvent.VK_ENTER){
            if(ogp.ui.commandNum == 0){
                ogp.gameState = ogp.playState;
                ogp.resetGame(false);
                ogp.playMusic(5);
            }
            else if(ogp.ui.commandNum == 1){
                ogp.gameState = ogp.titleState;
                ogp.resetGame(true);
                ogp.playMusic(5);
            }
        }
    }
    
    public void tradeState(int code){
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        
        if(ogp.ui.subState == 0){
            if(code == KeyEvent.VK_W){
                ogp.ui.commandNum--;
                if(ogp.ui.commandNum < 0){
                    ogp.ui.commandNum = 2;
                }
                ogp.playSE(8);
            }
            
            if(code == KeyEvent.VK_S){
                ogp.ui.commandNum++;
                if(ogp.ui.commandNum > 2){
                    ogp.ui.commandNum = 0;
                }
                ogp.playSE(8);
            }
        }
        if(ogp.ui.subState == 1){
            npcInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                ogp.ui.subState = 0;
            }
        }
        if(ogp.ui.subState == 2){
            playerInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                ogp.ui.subState = 0;
            }
        }
    }
    
    public void mapState(int code){
        
        if(code == KeyEvent.VK_M){
            ogp.gameState = ogp.playState;
        }
    }
    
    public void playerInventory(int code){
        
        if(code == KeyEvent.VK_W){
            if(ogp.ui.playerSlotRow != 0){
                ogp.ui.playerSlotRow--;
                ogp.playSE(8);
            }
        }
        if(code == KeyEvent.VK_A){
            if(ogp.ui.playerSlotCol != 0){
                ogp.ui.playerSlotCol--;
                ogp.playSE(8);
            }         
        }
        if(code == KeyEvent.VK_S){
            if(ogp.ui.playerSlotRow != 3){
                ogp.ui.playerSlotRow++;
                ogp.playSE(8);
            }
        }
        if(code == KeyEvent.VK_D){
            if(ogp.ui.playerSlotCol != 4){
                ogp.ui.playerSlotCol++;
                ogp.playSE(8);
            }
        }
    }
    
        public void npcInventory(int code){
        
        if(code == KeyEvent.VK_W){
            if(ogp.ui.npcSlotRow != 0){
                ogp.ui.npcSlotRow--;
                ogp.playSE(8);
            }
        }
        if(code == KeyEvent.VK_A){
            if(ogp.ui.npcSlotCol != 0){
                ogp.ui.npcSlotCol--;
                ogp.playSE(8);
            }         
        }
        if(code == KeyEvent.VK_S){
            if(ogp.ui.npcSlotRow != 3){
                ogp.ui.npcSlotRow++;
                ogp.playSE(8);
            }
        }
        if(code == KeyEvent.VK_D){
            if(ogp.ui.npcSlotCol != 4){
                ogp.ui.npcSlotCol++;
                ogp.playSE(8);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {//load lại trạng thái false để nhận phím mới
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_UP) {
            upArrowPressed = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downArrowPressed = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftArrowPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightArrowPressed = false;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }

}
