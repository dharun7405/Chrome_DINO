import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

public class ChromeDino extends JPanel implements ActionListener, KeyListener {
    int boardWidth=800;
    int boardHeight=300;

    Image dinoImg;
    Image dinoDeadImg;
    Image dinoJumpImg;
    Image cactus1Img;
    Image cactus2Img;
    Image cactus3Img;

    static class Block{
        int x;
        int y;
        int width;
        int height;
        Image img;

        Block(int x,int y,int width,int height,Image img){
            this.x=x;
            this.y=y;
            this.width=width;
            this.height=height;
            this.img=img;
        }
    }

    //DINO
    int dinoWidth=88;
    int dinoHeight=94;
    int dinoX=50;
    int dinoY=boardHeight-dinoHeight;

    Block dino;
    Timer gameLoop;
    Timer placeCactusTimer;

    //CACTUS
    int cactus1Width=34;
    int cactus2Width=69;
    int cactus3Width=102;
    int cactusHeight=70;
    int cactusX=700;
    int cactusY=boardHeight-cactusHeight;
    ArrayList<Block> cactusArray;

    //PHYSICS
    int velocityX = -12;  // CACTUS SPEED
    int velocityY = 0;  // DINO JUMP SPEED
    int gravity = 1;

    public ChromeDino(){
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        setBackground(Color.lightGray);
        setFocusable(true);
        addKeyListener(this);

        dinoImg=new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/dino-run.gif"))).getImage();
        dinoDeadImg=new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/dino-dead.png"))).getImage();
        dinoJumpImg=new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/dino-jump.png"))).getImage();
        cactus1Img=new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/cactus1.png"))).getImage();
        cactus2Img=new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/cactus2.png"))).getImage();
        cactus3Img=new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/cactus3.png"))).getImage();

        //DINO
        dino=new Block(dinoX,dinoY,dinoWidth,dinoHeight,dinoImg);
        //CACTUS
        cactusArray=new ArrayList<>();

        //GAME TIMER
        gameLoop=new Timer(1000/60,this); // 60 FPS
        gameLoop.start();
        //CACTUS TIMER
        placeCactusTimer=new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeCactus();
            }
        });
        placeCactusTimer.start();
    }

    void placeCactus(){
        double placeCactusChance=Math.random(); // RANGE:0-0.99
        if(placeCactusChance > .90){   // 10% CHANCE
            Block cactus = new Block(cactusX,cactusY,cactus3Width,cactusHeight,cactus3Img);
            cactusArray.add(cactus);
        }
        else if(placeCactusChance >.70){  // 20% CHANCE
            Block cactus=new Block(cactusX,cactusY,cactus2Width,cactusHeight,cactus2Img);
            cactusArray.add(cactus);
        }
        else if(placeCactusChance > .50){  // 20% CHANCE
            Block cactus=new Block(cactusX,cactusY,cactus1Width,cactusHeight,cactus1Img);
            cactusArray.add(cactus);
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        //DINO
        g.drawImage(dino.img,dino.x,dino.y,dino.width,dino.height,null);
        //CACTUS
        for(int i=0;i<cactusArray.size();i++){
            Block cactus = cactusArray.get(i);
            g.drawImage(cactus.img,cactus.x,cactus.y,cactus.width,cactus.height,null);
        }
    }

    public void move(){
        //dino
        velocityY += gravity;
        dino.y += velocityY;
        if(dino.y>dinoY){
            dino.y=dinoY;
            velocityY=0;
            dino.img=dinoImg;
        }

        //CACTUS
        for(int i=0;i<cactusArray.size();i++){
            Block cactus =cactusArray.get(i);
            cactus.x += velocityX;

            if(collision(dino,cactus)){
                dino.img=dinoDeadImg;
            }
        }
    }

    boolean collision(Block a,Block b){
        return  a.x < b.x + b.width &&
                a.x + a.width > b.x &&
                a.y < b.y + b.height &&
                a.y + a.height >b.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            if(dino.y==dinoY){
                velocityY = -17;
                dino.img=dinoJumpImg;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
