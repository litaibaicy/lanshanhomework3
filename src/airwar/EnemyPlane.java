package airwar;

import javax.swing.*;
import java.awt.*;

public class EnemyPlane extends Thread{
    public GameFrame gf;

    public int x,y;
    public int width = 50,height = 50,speed = 2,timer = 0;
    public boolean first=false;

    public Image img = new ImageIcon("homework3/src/airwar/enemyplane.jpg").getImage();

    public EnemyPlane( int x, int y,GameFrame gf) {
        this.gf = gf;
        this.x = x;
        this.y = y;
        this.first=true;
        this.timer=0;
    }

    public EnemyPlane( int x, int y, int width, int height,GameFrame gf) {
        this.gf = gf;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }



    public void run(){
        while (true){
            if (hit() || crash()){
                System.out.println("hit");
                this.speed = 0;
                this.img = new ImageIcon("homework3/src/airwar/boom.jpg").getImage();

                try {
                    this.sleep(500);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                gf.enemys.remove(this);

                break;
            }

            if(this.y >= 760){
                break;
            }

            try {
                this.sleep(10);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean hit(){
        Rectangle myrect = new Rectangle(this.x,this.y,this.width,this.height);


        Rectangle rect = null;

        for (int i = 0; i < gf.bullets.size(); i++) {
            Bullet bullet = gf.bullets.get(i);
            System.out.println("test hit");
            rect = new Rectangle(bullet.x,bullet.y - 1,bullet.width,bullet.height);
            if (myrect.intersects(rect)){
                return true;
            }
        }
       return false;
    }

    public boolean crash(){
        Rectangle myrect = new Rectangle(this.x,this.y,this.width,this.height);
        Rectangle myrect1 = new Rectangle(gf.heroPlane.x,gf.heroPlane.y,gf.heroPlane.width,gf.heroPlane.height);
        if (myrect.intersects(myrect1)){
            return true;
        }
        return false;
    }

}
