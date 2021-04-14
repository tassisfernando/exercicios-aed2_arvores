package navalha_batal;

import java.util.Scanner;

public class Batal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int lengthMatrix = input.nextInt();
        int celula = input.nextInt();
        int[][] matrix = new int[lengthMatrix][lengthMatrix];
        fill(matrix, celula);
        System.out.println(empty(matrix, celula));
    }

    public static void fill(int[][] matrix, int celula){
        Scanner input = new Scanner(System.in);
        int cont = 0;
        while(cont < celula) {
            int valueOne = input.nextInt();
            int valueTwo = input.nextInt();
            matrix[valueOne - 1][valueTwo - 1] = 1;
            cont++;
        }
    }

    public static int empty(int[][] matrix, int celula){
        int totalEmpty = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 0) {
                    totalEmpty++;
                    int cont = 1;
                    int cont2 = 0;
                    boolean top1 = false;
                    boolean top2 = false;
                    while(cont <= celula) {
                        if (i + cont <= matrix.length - 1) {
                            if (matrix[i + cont][j] == 0 && matrix[i + cont2][j] == 0 && !top2) {
                                totalEmpty++;
                            } else {
                                top2 = true;
                            }
                        }
                        if (j + cont <= matrix.length - 1) {
                            if (matrix[i][j + cont] == 0 &&  matrix[i][j + cont2] == 0  && !top1) {
                                totalEmpty++;
                            } else {
                                top1 = true;
                            }
                        }
                            cont2++;
                            cont++;
                        }
                    }
                }
            }
        return totalEmpty;
        }
    }
