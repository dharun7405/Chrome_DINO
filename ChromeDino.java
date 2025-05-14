import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChromeDino extends JPanel implements ActionListener {
    int boardWidth=800;
    int boardHeight=300;

    Image dinoImg;
    Image dinoDeadImg;
    Image dinoJumpImg;
    Image cactus1Img;
    Image cactus2Img;
    Image cactus3Img;

    class Block{
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

    public ChromeDino(){
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        setBackground(Color.lightGray);

        dinoImg=new ImageIcon(getClass().getResource("./img/dino-run.gif")).getImage();
        dinoDeadImg=new ImageIcon(getClass().getResource("./img/dino-dead.png")).getImage();
        dinoJumpImg=new ImageIcon(getClass().getResource("./img/dino-jump.png")).getImage();
        cactus1Img=new ImageIcon(getClass().getResource("./img/cactus1.png")).getImage();
        cactus2Img=new ImageIcon(getClass().getResource("./img/cactus2.png")).getImage();
        cactus3Img=new ImageIcon(getClass().getResource("./img/cactus3.png")).getImage();

        //DINO
        dino=new Block(dinoX,dinoY,dinoWidth,dinoHeight,dinoImg);

        //GAME TIMER
        gameLoop=new Timer(1000/60,this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.drawImage(dino.img,dino.x,dino.y,dino.width,dino.height,null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
