
package oneplayer;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtililyTool {

    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        //Vì mỗi lần gọi .drawImage thì ảnh sẽ được method scale lại (chức năng tiện lợi của drawImage nhưng làm tốn thời gian)
        //Đặt biệt là khi đưa nó vào vòng lặp => scale trước để giảm thời gian
        //Instantiate Buffered Image -> this is basically like a blank canvas
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());

        //Whatever g2 is gonna draw will be saved in scaled image
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;

    }

}
