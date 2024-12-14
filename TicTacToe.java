import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToe {
    int boardWidth = 600;
    int boardLength = 650;
    JFrame frame = new JFrame();
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardpanel = new JPanel();
    JButton[][] board = new JButton[3][3];
    String playerx ="X";
    String playero ="O";
    String currentPlayer = playerx;
    boolean gameOver = false;
    int turns=0;
    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(boardWidth,boardLength);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        textLabel.setBackground(Color.DARK_GRAY);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial",Font.BOLD,50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText(currentPlayer+"'s chance");
        textLabel.setOpaque(true);
        textPanel.add(textLabel);
        textPanel.setLayout(new BorderLayout());
        frame.add(textLabel,BorderLayout.NORTH);
        boardpanel.setLayout(new GridLayout(3,3));
        boardpanel.setBackground(Color.darkGray);
        frame.add(boardpanel);
        frame.setTitle("TicTacToe");

        for(int r=0;r<3;r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardpanel.add(tile);
                tile.setBackground(Color.white);
                tile.setForeground(Color.darkGray);
                tile.setFont(new Font("Times New Roman", Font.ITALIC, 120));
                tile.setFocusable(false);
                // tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == "") {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerx ? playero : playerx;
                                textLabel.setText(currentPlayer + "'s Chance");
                            }
                        }

                    }
                });
            }
        }
    }
    void checkWinner(){
        for(int r=0;r<3;r++){
           if(board[r][0].getText()=="") continue;
           if(board[r][0].getText()==board[r][1].getText() && board[r][1].getText()==board[r][2].getText()){
               for(int i=0;i<3;i++){
                   setWinner(board[r][i]);
               }
               gameOver=true;
               return;
           }
        }
        for(int c=0;c<3;c++){
            if(board[0][c].getText()=="") continue;
            if(board[0][c].getText()==board[1][c].getText() && board[1][c].getText()==board[2][c].getText()){
                for(int i=0;i<3;i++){
                    setWinner(board[i][c]);
                }
                gameOver=true;
                return;
            }
        }
        if(board[0][0].getText()==board[1][1].getText() && board[1][1].getText()==board[2][2].getText()&&board[0][0].getText()!=""){
            for(int i=0;i<3;i++){
                setWinner(board[i][i]);
            }
            gameOver=true;
            return;
        }
        if(board[0][2].getText()==board[1][1].getText() && board[1][1].getText()==board[2][0].getText()&&board[0][2].getText()!=""){
            for(int i=0;i<3;i++){
                setWinner(board[2-i][i]);
            }
            gameOver=true;
            return;
        }
        if(turns==9){
           for(int r=0;r<3;r++){
               for(int c=0;c<3;c++){
                   setTie(board[r][c]);
               }
           }
           gameOver = true;
        }
    }
    void setWinner(JButton tile){
        tile.setForeground(Color.YELLOW);
        tile.setBackground(Color.GRAY);
        textLabel.setText(currentPlayer + " Wins!!!");
    }
    void setTie(JButton tile){
        tile.setForeground(Color.RED);
        tile.setBackground(Color.white);
        textLabel.setText("Tie!!!");
    }
}
