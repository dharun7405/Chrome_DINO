import javax.swing.*;

public class Main {
    public static void main(String[] args){
        int boardWidth=800;
        int boardHeight=300;

        JFrame frame =new JFrame("Chrome Dino");
        frame.setSize(boardWidth,boardHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        ChromeDino chromeDino = new ChromeDino();
        frame.add(chromeDino);
        frame.pack();
        frame.setVisible(true);
    }
}
