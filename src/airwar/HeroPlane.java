package airwar;

import javax.swing.*;
import java.awt.*;

public class HeroPlane extends Thread{

    //
    private GameFrame gameFrame=null;
    //飞机初始位置
    int x = 230,y = 600;
    int width = 50,height = 50;
    int speed = 10;

    Image img = new ImageIcon("homework3/src/airwar/heroplane.jpg").getImage();
    //
    ImageIcon icon=new ImageIcon("homework3/src/airwar/heroplane.jpg");

    //飞机方向移动
    boolean up,down,left,right;

    //
    public HeroPlane(GameFrame frame) {
        this.gameFrame=frame;
    }

    public HeroPlane() {
    }

    public HeroPlane(int y, int width, int height, Image img) {
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }

    @Override
    public void run() {
        while (true){

                if (up) {

                    if(y> this.height/2 ){
                        y -= speed;
                    }
// 原始                   y -= speed;
                }
                if (down) {
//                    y += speed;
                    if(y <gameFrame.getHeight()-this.height){
                        y += speed;
                    }
                }
                if (left) {
                    if(x <gameFrame.getWidth()-this.width){
                        x += speed;
                    }
//                    x += speed;
                }
                if (right) {
//                    x -= speed;
                    if(x-speed>0)
                    {
                        x -= speed;
                    }else{
                        x=1;
                    }
                }



            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
