// 
// Decompiled by Procyon v0.5.36
// 

package object;


import entity.Entity;
import oneplayer.O_GamePanel;

public class OBJ_Boots extends Entity
{ 
    public static final String objName = "Giày trung cấp";
    
    public OBJ_Boots(O_GamePanel ogp) {
        super(ogp);
        name = objName;
        S1 = setup("/res/objects/boots", ogp.tileSizeW,ogp.tileSizeH);
        description = "[" + name + "]\nCảm thấy thân thể linh hoạt gấp bội";
    }

}
