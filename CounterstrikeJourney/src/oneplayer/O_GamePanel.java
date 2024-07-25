package oneplayer;

//import counterstrikejourney.CounterstrikeJourney;
import ai.PathFinder;
import entity.Entity;
import entity.Player;
import environment.EnviromentManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;
import object.EntityGenerator;
import data.SaveLoad;
import tilemap.Map;
import tilemap.TileManager;
import tiles_interactive.InteractiveTiles;

public class O_GamePanel extends JPanel implements Runnable { 


//implements xong thì quickfix chọn unimplements menthods
    //Cài đặt màn hình hiển thị gamme

    public final int oriaginalTitleSizeH = 16;
    public final int originalTitleSizeW = 16; //Nhân vật sẽ có độ lớn 16x16 pixels

    //Vì máy tính hiện tại đó độ phân giải rất lớn nên sẽ dùng tỉ lệ để phóng nó lên thay vì set thẳng nhân vật to
    final int scale = 3; //tỉ lệ là gấp 3 lần original
    public final int tileSizeH = oriaginalTitleSizeH * scale; //khi này nhân vật sẽ có độ lớn 48x48 pixels
    public final int tileSizeW = originalTitleSizeW * scale; //96x72

    public final int maxScreenCol = 20; //default 16 -> tỉ lệ chiều rộng hiện tại là 768 pixels
    public final int maxScreenRow = 12; //default 12 -> tỉ lệ chiều cao hiện tại là 576 pixels
    //Tỉ lệ màn hình 4:3
    public final int screenWidth = tileSizeW * maxScreenCol; //tỉ lệ chiều rộng hiện tại là 1056 pixels
    public final int screenHeight = tileSizeH * maxScreenRow;//tỉ lệ chiều cao hiện tại là 768 pixels
    //WORLD SETTINGs
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxMap = 20;
    public int currentMap = 0;

    //For full screen
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;

    public boolean fullScreenOn = false;

    //FPS
    int FPS = 60; //Fps tức là số khung hình trên giây thì game này cần set nó ở 60FPS mà thgian thực nó chạy cả tỷ frame trên giây nên p giới hạn lại

    //TileManager tạo map, KeyHandler tạo event nút nhấn -> không cần khởi tạo riêng cho 2 player vì là dùng chung map, còn trong KeyHandler set luôn cả 2 bộ phím
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);

    //Sound
    Sound music = new Sound();
    Sound se = new Sound();

    public CollisionChecker cChecker = new CollisionChecker(this);

    public AssetSetter aSetter = new AssetSetter(this);

    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    
    Config config = new Config(this);
    
    public PathFinder pFinder = new PathFinder(this);
    
    EnviromentManager eManager = new EnviromentManager(this);
    
    Map map = new Map(this);
    
    SaveLoad saveLoad = new SaveLoad(this);
    public EntityGenerator eGenerator = new EntityGenerator(this);
    public CutsceneManager csManager = new CutsceneManager(this);
    
    public Thread gameThread;

    //ENTITY AND OBJECTS
    //Player
    public Player player = new Player(this, keyH);

    //Use array, it means prepare 10 slots for obj, can replace the content during the game, can display up to 10 objs at the same time
    //This doesn' mean we can only create 10 objs in the game
    //Displaying too many objs at the same time can slow down game
    public Entity obj[][] = new Entity[maxMap][30];

    //NPC
    public Entity npc[][] = new Entity[maxMap][10];
    //Monster
    public Entity monster[][] = new Entity[maxMap][20];
    public Entity projectile[][] = new Entity[maxMap][20];
    //Chặt cây
    public InteractiveTiles iTile[][] = new InteractiveTiles[maxMap][50];
//    public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    //GAME STATE(trạng thái trò chơi: trong màn hình chính, trong màn hình menu,...)
    //Tuỳ thuộc vào state mà vẽ giao diện khác nhau trên màn hình
    public int gameState;
    public final int titleState = 0;//màn hình khởi động game. Sau đó sửa paint component
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    public final int characterState = 4;//màn hình trạng thái nhân vật
    public final int optionState = 5;//Màn hình option: tăng giảm âm lượng, fullscreen
    public final int gameOverState = 6; // màn hình game over
    public final int transitionState = 7;// màn hình chuyển map
    public final int tradeState = 8; //màn hình trao đổi vật phẩm
    public final int sleepState = 9; //đi ngủ
    public final int mapState = 10; //bản đồ
    public final int cutsceneState = 11; 
    
    //OTHERS
    public boolean bossBattleOn = false;
    
    //AREA
    public int currentArea;
    public int nextArea;
    public final int outside = 50;
    public final int indoor = 51;
    public final int dungeon = 52;
    
    //Luồng kiểu như set thời gian trong window vì máy hiện tại quá mạnh nên dùng thời gian thực sẽ là quá nhanh đói với game 2d
    //Khởi tạo 2 luồng khác nhau cho 2 nhân vật
    public O_GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTiles();
        eManager.setup();
        
        //Phát nhạc nền
        playMusic(5);
        //Phát nhạc nền trong màn hình khởi động game tuỳ nha
        gameState = playState;
        gameState = titleState;//khởi đầu sẽ là màn hình khởi động thay vì màn hình game
        //phân vùng độ sáng của từng bảng đồ
        currentArea = outside;
        //Tạo ra 1 bufferimage rỗng để lưu màn hình hiện tại
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics(); //means every thing g2 draw will be recorded in temp screen
        //Lúc trước là vẽ trực tiếp toàn bộ lên JPanel
        //Bây giờ phải qua 2 bước, b1: vẽ mọi thứ lên temp screen và đều "behind the sence", b2: vẽ từ temp screen lên JPanel
        //Reasons: if resize every single object one by one -> a lot of work and also it's not effecient performance
        //-> draw every thing to bufferedImage -> resize -> do this in every loop -> but the method is a bit heavier than  
        
        if(fullScreenOn == true){
            setFullScreen();
        }
    }
    
    public void resetGame(boolean restart){
        
//        stopMusic();
        currentArea = outside;
        removeTempEntity();
        bossBattleOn = false;
        player.setDefaultPositions();
        player.restoreStatus();
        player.resetCounter();
        aSetter.setNPC();
        aSetter.setMonster();
        
        if(restart == true){
            player.setDefaultValuesP1();
            aSetter.setObject();
            aSetter.setInteractiveTiles();
            eManager.lighting.resetDay();
        }
    }

//    public void retry(){
//        
//        player.setDefaultPositions();
//        player.setDefaultLifeAndMan();
//        aSetter.setNPC();
//        aSetter.setMonster();
//    }
//    
//    public void restart(){
//        
//        player.setDefaultValuesP1();
//        player.setDefaultPositions();
//        player.setDefaultLifeAndMan();
//        player.selectItem();
//        aSetter.setObject();
//        aSetter.setNPC();
//        aSetter.setMonster();
//        aSetter.setInteractiveTiles();
//    }
    
    public void setFullScreen() {
        //Get local screen device information (device screen)
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(CounterstrikeJourney.window);//cần truyền vào kiểu dữ liệu Window -> JFrame

        //Get full screen width and height
        screenWidth2 = CounterstrikeJourney.window.getWidth();
        screenHeight2 = CounterstrikeJourney.window.getHeight();

    }

    public void startGameThread() {//kh chạy luồng sẽ chạy run
        gameThread = new Thread(this);
        gameThread.start();

    }
    //khi chạy run thì có 2 công việc lặp lại liên tục từng frame
    //đó là update và draw (cập nhật và vẽ lại các cập nhật đó lên màn hình)
    //update là khi nhân vật di chuyển thì mỗi frame sẽ lưu 1 vị trí mới và vẽ vị trí đó trên màn hình/Có 60 frame 1 giây nên mắt thường nhìn nhân vật giống như di chuyển
    //draw vẽ lên những gì đã update

    @Override
    public void run() {
        //tạo display FPS lên màn hình
        long timer = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        int drawCount = 0;
        //tạo ra môi trường 60FPS
        //Cách 1: 
        double drawInterval = 1000000000 / FPS; //Tạo 1 biến lưu giá trị khoảng 0.01 giây sẽ vẽ 1 frame chạy đến 60 frame là đc 1 giây (Nó là biến chỉ thời gian vẽ 1 frame)
        double nextDrawTime = System.nanoTime() + drawInterval; //lấy thời gian hiện tại + thêm thời gian để vẽ 1 frame
        while (gameThread != null) {
            currentTime = System.nanoTime();
            update();//gọi update
            //repaint();//gọi paintComponent
            drawToTempScreen();// fullscreen chuyển sang xài hàm mới, vẽ vào BufferedImage tmpScreen
            drawToScreen();//vẽ từ BufferedImage lên Screen
            drawCount++;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            try {
                //long currentTime =  System.nanoTime();  //1.000.000.000 nano = 1s
                //long currentTime2 = System.currentTimeMillis(); //1.000 milis  = 1s   
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);//sleep chỉ nhận biến milis nên phải đổi từ nano về mili khúc này
                nextDrawTime += drawInterval;
                if (timer > 1000000000) {
                    System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                    timer = 0;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //thay đổi vị trí nhân vật sẽ thực hiện tron update
    public void update() {
        if (gameState == playState) {
            //PLAYER
            player.updateP1();

            //NPC
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }
            //Monster
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    if (monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
                        monster[currentMap][i].update();
                    }
                    if (monster[currentMap][i].alive == false) {
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                }
            }
            for (int i = 0; i < projectile[1].length; i++) {
                if (projectile[currentMap][i] != null) {
                    if (projectile[currentMap][i].alive == true ) {
                        projectile[currentMap][i].update();
                    }
                    if (projectile[currentMap][i].alive == false) {
                        projectile[currentMap][i] = null;
                    }
                }
            }
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    if (particleList.get(i).alive == true ) {
                        particleList.get(i).update();
                    }
                    if (particleList.get(i).alive == false) {
                        particleList.remove(i);
                    }
                }
            }
            for(int i = 0; i < iTile[1].length; i++){
                if(iTile[currentMap][i] != null){
                    iTile[currentMap][i].update();
                }
            }

            eManager.update();
        }
        if (gameState == pauseState) {

        }

    }

    //Hàm vẽ vào tmpScreen
    public void drawToTempScreen() {
        //Debug
        long drawStart = 0;
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }
        //Thêm điều kiện chỉ định khi nào thì vẽ cái gì
        //Title screen: Màn hình khởi động game
        if (gameState == titleState) {
            ui.draw(g2);
        } //Nếu không thì 
        //MAP SCREEN
        else if(gameState == mapState){
            map.drawFullMapScreen(g2);
        }
        else {
            //Draw map: Dùng chung 1 map
            tileM.draw1P(g2);
            //Vẽ nhân vật
            player.draw1P(g2);
            //vẽ Cảnh vật nv có thể tác động
            for (int i = 0; i < iTile[1].length; i++) {
                if (iTile[currentMap][i] != null) {
                    iTile[currentMap][i].draw(g2);
                }
            }
            //Vẽ NPC
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            //Vẽ các vật phẩm
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }
            //Vẽ monster
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }
            for(int i = 0 ; i < projectile[1].length; i++){ 
                if(projectile[currentMap][i] != null){
                    entityList.add(projectile[currentMap][i]);
                }
            }
            for(int i = 0 ; i < particleList.size();i++){ 
                if(particleList.get(i) != null){
                    entityList.add(particleList.get(i));
                }
            }

            //SORT
            Collections.sort(entityList, (Entity e1, Entity e2) -> {
                int result = Integer.compare(e1.worldY, e2.worldY);
                return result;
            });
            //DRAW ENTITIES
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            //Empty entity list
            entityList.clear();
            //ENVIROMENT
            eManager.draw(g2);
            
            //MINI MAP
            map.drawMiniMap(g2);
            
            //CUTSCENE
            csManager.draw(g2);
            
            //UI
            ui.draw(g2);
        }
        //Debug
        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Thời gian: " + passed, 10, 400);
            System.out.println("Thời gian: " + passed);
            g2.drawString("Chế độ bất tử: " + keyH.godModeOn, 10, 400);
        }
    }

    //Hàm vẽ lên screen
    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();

    }
    //Fullscreen: Không xài nữa vì nó vẽ thẳng lê JPanel (tự hàm phải khởi tạo 1 g2 mới, nhưng ở đây phải dùng cái g2 temp)
//    @Override
//    public void paintComponent(Graphics g) { //Có sẵn trong java dùng để vẽ 1 hình vuông tượng trưng cho nhân vật
//        super.paintComponent(g);
//        //Vẽ 2 Component detect cho 2 nhân vật
//        Graphics2D g2 = (Graphics2D) g;
//        //Debug
//        long drawStart = 0;
//        if (keyH.checkDrawTime == true) {
//            drawStart = System.nanoTime();
//        }
//        //Thêm điều kiện chỉ định khi nào thì vẽ cái gì
//        //Title screen: Màn hình khởi động game
//        if(gameState == titleState){
//            ui.draw(g2);
//        }
//        //Nếu không thì 
//        else{
//            //Draw map: Dùng chung 1 map
//            tileM.draw1P(g2);
//            //Vẽ nhân vật
//            player.draw1P(g2);
//            //Vẽ NPC
//            for(int i = 0 ; i < npc.length; i++){
//                if(npc[i] != null){
//                    entityList.add(npc[i]);
//                }
//            }
//            //Vẽ các vật phẩm
//            for(int i = 0 ; i < obj.length;i++){ 
//                if(obj[i] != null){
//                    entityList.add(obj[i]);
//                }
//            }
//            //Vẽ monster
//            for(int i = 0 ; i < monster.length;i++){ 
//                if(monster[i] != null){
//                    entityList.add(monster[i]);
//                }
//            }
//            
//            
//            
//                //SORT
//            Collections.sort(entityList, (Entity e1, Entity e2) -> {
//                int result = Integer.compare(e1.worldY, e2.worldY);
//                return result;
//            });
//                //DRAW ENTITIES
//            for(int i = 0 ;i< entityList.size();i++){
//                entityList.get(i).draw(g2);
//            }
//            //Empty entity list
//            entityList.clear(); 
//
//            //UI
//            ui.draw(g2);
//        }
//
//
//        //Debug
//        if (keyH.checkDrawTime == true) {
//            long drawEnd = System.nanoTime();
//            long passed = drawEnd - drawStart;
//            g2.setColor(Color.white);
//            g2.drawString("Draw time: " + passed, 10, 400);
//            System.out.println("Draw time: " + passed);
//        }
//        //Đã chuyển 2 dòng ở đây qua draw của class Player
//        //chương trình vẫn hoạt động dù không có dòng này nhưng ghi để loại bỏ biến g2 ngay sau khi vẽ để còn update vẽ lại ở vị trí mới
//        g2.dispose();
//
//    }
    
    //Nhạc nền
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        se.stop();
    }
    public void stopMusicSound() {
        music.stop();
    }
    //Short sound - Sound effect
    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
    public void changArea(){
        // Vẫn không tắt đc nhạc
        if(nextArea != currentArea){
            stopMusicSound();
            if(nextArea == outside){
                playMusic(5);
            }
            if(nextArea == indoor){
                playMusic(18);
            }
            if(nextArea == dungeon){
                playMusic(19);
            }
        }
        
        currentArea = nextArea;
        aSetter.setMonster();
    }
    
    public void removeTempEntity(){
        
        for(int mapNum = 0; mapNum < maxMap; mapNum++){
            for(int i = 0; i < obj[1].length; i++){
                if(obj[mapNum][i] != null && obj[mapNum][i].temp == true){
                    obj[mapNum][i] = null;
                }
            }
        }
    }
}
