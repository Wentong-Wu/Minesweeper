import java.util.Random;
import java.util.Scanner;
public class Minesweeper {
    String[][] gameBoard = new String[10][10];
    String[][] hiddenBoard = new String[10][10];
    boolean play = false;
    String bomb="*";
    private void DisplayBoard(String[][] arr){
        for(int i = 0; i < arr.length-1;i++)
        {
            for(int j=0;j< arr[i].length-1;j++)
            {
                System.out.print("|"+arr[i][j]+"|");
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
                    if (i-1 != -1 && j-1 != -1) //Making sure that the array is not out of bound.
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
    private void PlantBomb(int rowSize, int colSize, int numBomb, String[][]arr)
    {
        Random r = new Random();
        for(int i = 0; i < numBomb;i++) {
            int row = r.nextInt(rowSize - 1);
            int col = r.nextInt(colSize - 1);
            //Can store all the possible value into a list, then shuffle it, and choose the first list out of the shuffle.
            while (arr[row][col] == "*") // <- I DONT LIKE THIS :(
            {
                row = r.nextInt(rowSize-1);
                col = r.nextInt(colSize-1);
            }
            arr[row][col] = "*";
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
        tileReveal(col, row);

        PlantBomb(10,10,10,hiddenBoard);
        AdjacentTile(hiddenBoard,bomb);
        //AdjacentTile(gameBoard,"0");
        DisplayBoard(gameBoard);
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
