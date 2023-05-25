package 패키지인데이게뭔지모를패키지;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MineSweeperGame extends JFrame {
    private JButton[][] buttons;
    private int[][] board;
    private boolean[][] visited;
    private int boardSize = 10;
    private int mineCount = 10;
    private int remainingTiles;
    private ImageIcon imageIcon;
    public MineSweeperGame() {
        
     

    	  
        setTitle("지뢰찾기");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
      
        buttons = new JButton[boardSize][boardSize];
        board = new int[boardSize][boardSize];
        visited = new boolean[boardSize][boardSize];
        remainingTiles = boardSize * boardSize - mineCount;
       
        
        initializeBoard();
        placeMines();
        calculateAdjacentMines();
      
        

        JPanel panel = new JPanel(new GridLayout(boardSize, boardSize));
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                buttons[i][j] = new JButton();
                imageIcon = new ImageIcon("icon1.png");
                setButtonImageOnRightClick(buttons[i][j], imageIcon);
                buttons[i][j].setMargin(new Insets(0, 0, 0, 0));
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                panel.add(buttons[i][j]);
                
                
            }
        }
     
        add(panel);
        setVisible(true);
    }
    private void setButtonImageOnRightClick(JButton button, Icon imageIcon) {
    	Icon originalIcon = button.getIcon();
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    button.setIcon(imageIcon);
                } else {
                    button.setIcon(originalIcon); // 원래의 아이콘 복원
                }
            }
        });
    }
  

    private void initializeBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = 0;
                visited[i][j] = false;
            }
        }
    }

    private void placeMines() {
        int count = 0;
        while (count < mineCount) {
            int x = (int) (Math.random() * boardSize);
            int y = (int) (Math.random() * boardSize);

            if (board[x][y] != -1) {
                board[x][y] = -1;
                count++;
            }
        }
    }
    

    private void calculateAdjacentMines() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] != -1) {
                    int count = 0;
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            int nx = i + dx;
                            int ny = j + dy;
                            if (nx >= 0 && nx < boardSize && ny >= 0 && ny < boardSize && board[nx][ny] == -1) {
                                count++;
                            }
                        }
                    }
                    board[i][j] = count;
                }
            }
        }
    }

    private void revealTile(int x, int y) {
    	
        if (visited[x][y]) {	
   
            return;
            
        }else {
        	floodFill(x,y);
        	
        }

        visited[x][y] = true;
        remainingTiles--;

        if (board[x][y] == -1) {
            gameOver();
        } else if (board[x][y] == 0) {
            floodFill(x, y);
        } else {
            buttons[x][y].setText(String.valueOf(board[x][y]));
        }

        if (remainingTiles == 0) {
            gameWon();
        }
    }

    private void floodFill(int x, int y) {
    	
        if (x < 0 || x >= boardSize || y < 0 || y >= boardSize || visited[x][y]) {
            return;
        }

        visited[x][y] = true;
        remainingTiles--;

        if (board[x][y] == 0) {
            buttons[x][y].setEnabled(false);

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    int nx = x + dx;
                    int ny = y + dy;
                    floodFill(nx, ny);
                }
            }
        } else {
            buttons[x][y].setText(String.valueOf(board[x][y]));
        }

        if (remainingTiles == 0) {
           gameWon();
        }
    }

    private void gameOver() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == -1) {
                    buttons[i][j].setText("X");
                }
                buttons[i][j].setEnabled(false);
            }
        }

        JOptionPane.showMessageDialog(this, "Game Over", "지뢰찾기", JOptionPane.INFORMATION_MESSAGE);
    }

    private void gameWon() {
    	 for (int i = 0; i < boardSize; i++) {
             for (int j = 0; j < boardSize; j++) {
                 if (board[i][j] == -1) {
                     
                 }
                 buttons[i][j].setEnabled(false);
             }
         }
    	
    	
        JOptionPane.showMessageDialog(this, "다 찾았어요~!", "지뢰찾기", JOptionPane.INFORMATION_MESSAGE);
    }

    private class ButtonClickListener implements ActionListener {
        private int x;
        private int y;

        public ButtonClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        	
            revealTile(x, y);
        }
   
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MineSweeperGame();
        });
    }
}
