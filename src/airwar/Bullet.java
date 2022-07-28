package airwar;

import javax.swing.*;
import java.awt.*;

public class Bullet {
    int x,y;
    int width = 50,height =50;



    int speed = 10;

    Image image = new ImageIcon("homework3/src/airwar/bullet.jpg").getImage();


    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Bullet(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
