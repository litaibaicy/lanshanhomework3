package airwar;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Vector;

public class GameFrame extends JFrame {
        HeroPlane heroPlane;

        Vector<Bullet> bullets = new Vector<>();

        Vector<EnemyPlane> enemys = new Vector<>();

        GameFrame frame;
    public GameFrame(){
        frame = this;
        //创建英雄机
        heroPlane = new HeroPlane(frame);
        heroPlane.start();

        //窗口设置
        this.setSize(500,760);
        this.setTitle("雷霆战机");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }){

        }.start();

        new Thread(new Runnable() {
            Random r = new Random();
            @Override
            public void run() {
                while (true){
                    try {
                    EnemyPlane enemyPlane = new EnemyPlane(r.nextInt(500), 0, frame);
                    enemyPlane.start();
                    enemys.add(enemyPlane);


                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }){

        }.start();
    }

    public void paint(Graphics g){
//        System.out.println("绘制画板");

        BufferedImage image = (BufferedImage) this.createImage(this.getSize().width, this.getSize().height);

        Graphics bi = image.getGraphics();
        //背景
        bi.drawImage(new ImageIcon("homework3/src/airwar/back.jpg").getImage(), 0, 0, null);

        //飞机
        if (heroPlane.x < 500 && heroPlane.x > 0 && heroPlane.y > 0 && heroPlane.y < 760){
            bi.drawImage(heroPlane.img, heroPlane.x, heroPlane.y,heroPlane.width,heroPlane.height, null);
        }else {
            System.out.println("you die");
        }

        //子弹
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            if (bullet.y > 0){
                bi.drawImage(bullet.image, bullet.x, bullet.y -= bullet.speed,bullet.width,bullet.height,null );
            }else {
                bullets.remove(bullet);
            }
        }

        //敌机
        for (int i = 0; i < enemys.size(); i++) {
            EnemyPlane ep = enemys.get(i);
            if(ep.first){
                bi.drawImage(ep.img, ep.x, ep.y +=40,ep.width,ep.height,null );
                ep.first=false;
                continue;
            }else if(ep.timer<200){
                ep.timer++;
                bi.drawImage(ep.img, ep.x, ep.y ,ep.width,ep.height,null );
                continue;
            }
            else if (ep.y < 760){
                bi.drawImage(ep.img, ep.x, ep.y += ep.speed,ep.width,ep.height,null );

            }else {
                enemys.remove(ep);
            }
        }



        g.drawImage(image, 0, 0, null);
    }


    public static void main(String[] args) {
        GameFrame frame = new GameFrame();

        Player player = new Player(frame);
        frame.addKeyListener(player);
    }
}
