import java.util.Random;
import java.util.Scanner;
public class Minesweeper {
    void DisplayBoard(String[][] arr){
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
    void AdjacentTile(String[][]arr)
    {
        for(int i = 0;i < arr.length;i++)
        {
            for(int j = 0; j <arr[i].length;j++)
            {
                if (arr[i][j] == "*")
                {
                    if (i-1 != -1 && j-1 != -1) {
                        if (arr[i - 1][j - 1] != "*" && arr[i - 1][j - 1] != null) //North West
                        {
                            int n = Integer.parseInt(arr[i - 1][j - 1]);
                            n++;
                            arr[i - 1][j - 1] = n + "";
                        }
                        if (arr[i][j - 1] != "*" && arr[i][j - 1] != null) //North
                        {
                            int n = Integer.parseInt(arr[i][j - 1]);
                            n++;
                            arr[i][j - 1] = n + "";
                        }
                        if (arr[i+1][j - 1] != "*" && arr[i+1][j - 1] != null) //North East
                        {
                            int n = Integer.parseInt(arr[i+1][j - 1]);
                            n++;
                            arr[i+1][j - 1] = n + "";
                        }
                        if (arr[i - 1][j] != "*" && arr[i - 1][j] != null) //West
                        {
                            int n = Integer.parseInt(arr[i - 1][j]);
                            n++;
                            arr[i - 1][j] = n + "";
                        }
                        if (arr[i - 1][j+1] != "*" && arr[i - 1][j+1] != null) //South West
                        {
                            int n = Integer.parseInt(arr[i - 1][j+1]);
                            n++;
                            arr[i - 1][j+1] = n + "";
                        }
                    }
                    if(arr[i+1][j+1] != "*" && arr[i+1][j+1] != null) //South East
                    {
                        int n = Integer.parseInt(arr[i+1][j+1]);
                        n++;
                        arr[i+1][j+1] = n+"";
                    }
                    if(arr[i+1][j] != "*" && arr[i+1][j] != null) // South
                    {
                        int n = Integer.parseInt(arr[i+1][j]);
                        n++;
                        arr[i+1][j] = n+"";
                    }
                    if(arr[i][j+1] != "*" && arr[i][j+1] != null) // East
                    {
                        int n = Integer.parseInt(arr[i][j+1]);
                        n++;
                        arr[i][j+1] = n+"";
                    }
                }
            }
        }
    }
    private void tileReveal(int x, int y)
    {

    }
    private void SetBoard(String[][]arr)
    {
        for(int i = 0 ; i< arr.length;i++)
        {
            for(int j=0;j<arr[i].length;j++)
            {
                arr[i][j] = "0";
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
    public void Play()
    {
        Scanner input = new Scanner(System.in);
        String[][] gameBoard = new String[10][10];
        SetBoard(gameBoard);
        PlantBomb(10,10,10,gameBoard);
        AdjacentTile(gameBoard);
        System.out.println();
        DisplayBoard(gameBoard);
    }
}
