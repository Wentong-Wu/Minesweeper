import java.util.*;

public class Minesweeper {
    String[][] gameBoard = new String[10][10];
    String[][] hiddenBoard = new String[10][10];
    boolean play = false;
    String bomb="*";
    ArrayList boardList = new ArrayList<>();
    private void DisplayBoard(String[][] arr){
        for(int i = 0; i < arr.length;i++)
        {
            for(int j=0;j< arr[i].length;j++)
            {
                System.out.print("|"+arr[j][i]+"|");
                //System.out.print('ඞ'+" ");
            }
            System.out.println("");
        }
    }
    private void AdjacentTile(String[][]arr, String n)
    {
        for(int i = 0;i < arr.length;i++)
        {
            for(int j = 0; j <arr[i].length;j++)
            {
                if (arr[i][j] == n) //Look for bombs
                {
                    if (i-1 != -1 && j-1 != -1 && i+1 != arr.length && j+1 != arr.length) //Making sure that the array is not out of bound.
                    {
                        if (arr[i - 1][j - 1] != n && arr[i - 1][j - 1] != null) //North West
                        {
                            int num = Integer.parseInt(arr[i - 1][j - 1]);
                            num++;
                            arr[i - 1][j - 1] = num + "";
                        }
                        if (arr[i][j - 1] != n && arr[i][j - 1] != null) //North
                        {
                            int num = Integer.parseInt(arr[i][j - 1]);
                            num++;
                            arr[i][j - 1] = num + "";
                        }
                        if (arr[i+1][j - 1] != n && arr[i+1][j - 1] != null) //North East
                        {
                            int num = Integer.parseInt(arr[i+1][j - 1]);
                            num++;
                            arr[i+1][j - 1] = num + "";
                        }
                        if (arr[i - 1][j] != n && arr[i - 1][j] != null) //West
                        {
                            int num = Integer.parseInt(arr[i - 1][j]);
                            num++;
                            arr[i - 1][j] = num + "";
                        }
                        if (arr[i - 1][j+1] != n && arr[i - 1][j+1] != null) //South West
                        {
                            int num = Integer.parseInt(arr[i - 1][j+1]);
                            num++;
                            arr[i - 1][j+1] = num + "";
                        }
                    }
                    if(arr[i+1][j+1] != n && arr[i+1][j+1] != null) //South East
                    {
                        int num = Integer.parseInt(arr[i+1][j+1]);
                        num++;
                        arr[i+1][j+1] = num+"";
                    }
                    if(arr[i+1][j] != n && arr[i+1][j] != null) // South
                    {
                        int num = Integer.parseInt(arr[i+1][j]);
                        num++;
                        arr[i+1][j] = num+"";
                    }
                    if(arr[i][j+1] != n && arr[i][j+1] != null) // East
                    {
                        int num = Integer.parseInt(arr[i][j+1]);
                        num++;
                        arr[i][j+1] = num+"";
                    }
                }
            }
        }
    }
    private void tileReveal(int row, int col)
    {
        if (gameBoard[row][col] == "-")
        {
            gameBoard[row][col] = hiddenBoard[row][col];
        }
        if(gameBoard[row][col] == "0")
        {
            if (row-1 != -1 && col-1 != -1)
            {
                if (hiddenBoard[row-1][col-1] == "0") //North West
                {
                    gameBoard[row - 1][col - 1] = hiddenBoard[row - 1][col - 1];
                    tileReveal(row-1,col-1);
                }
                if (hiddenBoard[row][col-1] == "0") //North
                {
                    gameBoard[row][col - 1] = hiddenBoard[row][col - 1];
                    tileReveal(row,col-1);
                }
            }
        }
    }
    private void SetBoard(String[][]arr,String item)
    {
        for(int i = 0 ; i< arr.length;i++)
        {
            for(int j=0;j<arr[i].length;j++)
            {
                arr[i][j] = item;
            }
        }
    }
    private void GenerateBoard()
    {

        for(int i = 0;i<hiddenBoard.length;i++)
        {
            for(int j =0; j<hiddenBoard[i].length;j++)
            {
                boardList.add(i+","+j);
            }
        }
    }
    private void PlantBomb(int rowSize, int colSize, int numBomb)
    {
        Random r = new Random();
        GenerateBoard();
        Collections.shuffle(boardList);
        System.out.println(boardList);
        for(int i = 0; i < numBomb;i++) {
            //boardList.set(i,bomb);
            String boardCoord = (String) boardList.get(0);
            String[] coord = boardCoord.split(",");
            int row = Integer.parseInt(coord[0]);
            int col = Integer.parseInt(coord[1]);
            hiddenBoard[row][col] = bomb;
            boardList.remove(0);
        }
    }
    private void SettingBoard(int row, int col)
    {
        this.gameBoard = new String[row][col];
        this.hiddenBoard = new String[row][col];
    }
    public void Play()
    {
        play = true;
        SetBoard(gameBoard,"-");
        SetBoard(hiddenBoard,"0");
        DisplayBoard(gameBoard);
        Scanner input = new Scanner(System.in);

        System.out.println("Enter row coord: ");
        int row = input.nextInt();
        System.out.println("Enter column coord: ");
        int col = input.nextInt();
        System.out.println();

        PlantBomb(10,10,10);
        AdjacentTile(hiddenBoard,bomb);
        //tileReveal(col, row);
        DisplayBoard(hiddenBoard);
        while (play) {
            System.out.println("Enter row coord: ");
            row = input.nextInt();
            System.out.println("Enter column coord: ");
            col = input.nextInt();
            System.out.println();
            tileReveal(col, row);
            DisplayBoard(gameBoard);
        }
    }
    public void BoardSetting()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of rows: ");
        int row = input.nextInt();
        System.out.print("Enter number of column: ");
        int col = input.nextInt();
        SettingBoard(row,col);
    }
}
