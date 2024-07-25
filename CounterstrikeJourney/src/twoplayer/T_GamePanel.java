package TwoPlayer;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import tilemap.TileManager;
import oneplayer.KeyHandler;
import oneplayer.O_GamePanel;

public class T_GamePanel extends JPanel implements Runnable { //implements xong thì quickfix chọn unimplements menthods
    //Cài đặt màn hình hiển thị gamme

    final int originalTitleSizeH = 24;
    final int originalTitleSizeW = 18; //Nhân vật sẽ có độ lớn 16x16 pixels

    //Vì máy tính hiện tại đó độ phân giải rất lớn nên sẽ dùng tỉ lệ để phóng nó lên thay vì set thẳng nhân vật to
    final int scale = 4; //tỉ lệ là gấp 3 lần original
    public final int tileSizeH = originalTitleSizeH * scale; //khi này nhân vật sẽ có độ lớn 48x48 pixels
    public final int tileSizeW = originalTitleSizeW * scale;

    public final int maxScreenCol = 21; //default 16 -> tỉ lệ chiều rộng hiện tại là 768 pixels
    public final int maxScreenRow = 9; //default 12 -> tỉ lệ chiều cao hiện tại là 576 pixels
    //Tỉ lệ màn hình 4:3
    public final int screenWidth = tileSizeW * maxScreenCol; //tỉ lệ chiều rộng hiện tại là 1056 pixels
    public final int screenHeight = tileSizeH * maxScreenRow;//tỉ lệ chiều cao hiện tại là 768 pixels

    //FPS
    int FPS = 60; //Fps tức là số khung hình trên giây thì game này cần set nó ở 60FPS mà thgian thực nó chạy cả tỷ frame trên giây nên p giới hạn lại

    //TileManager tạo map, KeyHandler tạo event nút nhấn -> không cần khởi tạo riêng cho 2 player vì là dùng chung map, còn trong KeyHandler set luôn cả 2 bộ phím
    TileManager tileM = new TileManager(this);
    //KeyHandler keyH = new KeyHandler();

    //Khởi tạo 2 player
    //Player player1 = new Player(this, keyH, "1");
    //Player player2 = new Player(this, keyH, "2");

    //Luồng kiểu như set thời gian trong window vì máy hiện tại quá mạnh nên dùng thời gian thực sẽ là quá nhanh đói với game 2d
    //Khởi tạo 2 luồng khác nhau cho 2 nhân vật
    Thread gameThread;
    Thread gameThread1;

    public T_GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        //this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {//kh chạy luồng sẽ chạy run
        gameThread = new Thread(this);
        gameThread1 = new Thread(this);
        gameThread.start();
        gameThread1.start();
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
        while (gameThread != null && gameThread1 != null) {
            currentTime = System.nanoTime();
            update();//gọi update
            repaint();//gọi paintComponent
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
        //chuyển nội dung qua update của class player
        //player1.updateP1();
        //player2.updateP2();
    }

    @Override
    public void paintComponent(Graphics g) { //Có sẵn trong java dùng để vẽ 1 hình vuông tượng trưng cho nhân vật
        super.paintComponent(g);
        //Vẽ 2 Component detect cho 2 nhân vật
        Graphics2D g2 = (Graphics2D) g;
        Graphics2D g3 = (Graphics2D) g;

        //Dùng chung 1 map
        tileM.draw(g2);
        tileM.draw(g3);

        //2 ô detect khác nhau
       // player1.draw(g2);
       // player2.draw(g3);
        //Đã chuyển 2 dòng ở đây qua draw của class Player
        
        //chương trình vẫn hoạt động dù không có dòng này nhưng ghi để loại bỏ biến g2 ngay sau khi vẽ để còn update vẽ lại ở vị trí mới
        g2.dispose();
        g3.dispose();
    }
}
