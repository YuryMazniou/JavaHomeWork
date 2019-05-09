package by.it.mazniou.tasks.largest_square;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {
        int [][]matrix={
                {0,0,0,0,0,0,0,0,0,0},
                {0,1,1,0,0,0,0,1,0,0},
                {0,1,1,0,0,0,0,0,0,0},
                {0,0,0,0,1,1,1,1,0,0},
                {0,0,0,0,1,1,1,1,0,0},
                {0,1,1,0,1,1,1,1,0,0},
                {0,1,0,0,1,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,1,1,0,0,0,1,1,1},
                {0,0,1,1,0,0,0,0,1,1}};
        System.out.println(maxSquare(matrix));
    }
    public static int maxSquare(int[][] matrix){
        int[][]cache=matrix.clone();
        int result=0;
        for (int i = 0; i <matrix.length; i++) {
            for (int j = 0; j <matrix[i].length; j++) {
                if(i!=0&&j!=0) {
                    if (matrix[i][j] > 0) {
                        cache[i][j] = 1 + min(cache[i][j - 1], cache[i - 1][j], cache[i - 1][j - 1]);
                    }
                }
                if(cache[i][j]>result)result=cache[i][j];
            }
        }
        return result*result;
    }
    public static int min(int a,int b,int c){
        return Math.min(Math.min(a,b),Math.min(b,c));
    }
    /*public static int maxSquare(int[][] matrix) {
        if(matrix!=null) {
            int resultSquare=0;
            int countSide=0;
            int jStart=-1;
            int jFinish=-1;
            boolean[][] booleansMatrix = new boolean[matrix.length][matrix[0].length];
            for (int i = 0; i <matrix.length; i++) {
                for (int j = 0; j <matrix[i].length; j++) {
                    if(!booleansMatrix[i][j]){
                        if(matrix[i][j]==1){
                            if(jStart<0){
                                countSide++;
                                jStart=j;
                                jFinish=j;
                            }
                            else {
                                jFinish=j;
                                countSide++;
                            }
                        }
                        else{
                            if((jFinish>0)&&(jStart>0)){
                                if(jFinish!=jStart){
                                    if(checkSquare(matrix,jStart,jFinish,i)){
                                        fillBooleanMatrix(booleansMatrix,jStart,jFinish,i);
                                        int result=countSide*countSide;
                                        if(result>resultSquare)resultSquare=result;
                                        countSide=0;
                                        jStart=-1;
                                        jFinish=-1;
                                    }
                                    else{
                                        countSide=0;
                                        jStart=-1;
                                        jFinish=-1;
                                    }
                                }
                                else {
                                    if (resultSquare == 0) {
                                        resultSquare = 1;
                                        countSide = 0;
                                        jStart = -1;
                                        jFinish = -1;
                                    } else {
                                        countSide = 0;
                                        jStart = -1;
                                        jFinish = -1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return resultSquare;
        }
        return 0;
    }
    public static boolean checkSquare(int[][] matrix,int jStart,int jFinish,int I){
        boolean flag=false;
        int length=I+(jFinish-jStart);
        if(length>=matrix.length)return flag;
        for (int i = I+1; i <=length; i++) {
            for (int j = jStart; j <=jFinish; j++) {
                if(matrix[i][j]==1)flag=true;
                else
                    return false;
            }
        }
        return flag;
    }
    public static void fillBooleanMatrix(boolean[][]booleansMatrix,int jStart,int jFinish,int I){
        for (int i = I; i <=I+(jFinish-jStart); i++) {
            for (int j = jStart; j <=jFinish; j++) {
                booleansMatrix[i][j]=true;
            }
        }
    }*/
}
