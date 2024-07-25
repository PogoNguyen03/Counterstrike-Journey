package oneplayer;

//import counterstrikejourney.*;
import javax.swing.ImageIcon;
import oneplayer.O_GamePanel;
import javax.swing.JFrame;

public class CounterstrikeJourney {

    
    public static JFrame window; //chuyển thành static để truyền vào FullScreen

    public static void main(String[] args) {
        window = new JFrame(); //Tạo 1 cửa sổ mới
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //tạo nút tắt mặc định
        window.setResizable(false); //Không cho thay đổi kích thước cửa sổ
        window.setTitle("Counterstrike Journey"); //Tên của game

        
        //2Player
        //T_GamePanel gamePanel = new T_GamePanel();
        //1Player
        O_GamePanel gamePanel = new O_GamePanel();

        window.add(gamePanel);

        gamePanel.config.loadConfig();
        if(gamePanel.fullScreenOn == true){
            window.setUndecorated(true);
        }
        
        window.pack();

        window.setLocationRelativeTo(null); //Khi run, cửa sổ game sẽ mặc định hiện giữa màn hình desktop
        window.setVisible(true); //Hiển thị window

        //Setup map before start game
        gamePanel.setupGame();

        gamePanel.startGameThread();//Chạy luồng
    }
    
}


