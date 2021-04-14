/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b_hash;

import java.util.Scanner;

public class Hash2 {
    
    /*
2
13 9 
44 45 49 70 27 73 92 97 95 
7 8
35 12 2 17 19 51 88 86
    */

    int m;
    ListaDinamica[] lista;

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        for (int i = 0; i < n; i++) {

            int m = scn.nextInt();
            int c = scn.nextInt();
            while (m < 1 && m > 100) {
                m = scn.nextInt();
            }
            while (c < 1 && c > 200) {
                c = scn.nextInt();
            }

            ListaDinamica[] lista = new ListaDinamica[m];
            for (int j = 0; j < m; j++) {
                lista[j] = new ListaDinamica();
            }
            
            for (int j = 0; j < c; j++) {
                int chave = scn.nextInt();
                Insere(chave, lista, m);
            }

            //ordenação
            /*for (int lis = 0; lis < m; lis++) {
                
                for (int k = 0; k < lista[lis].getSize(); k++) {
                    for (int l = 1; l < lista[lis].getSize(); l++) {
                        if ((Integer) lista[lis].getItem(l) > (Integer) lista[lis].getItem(l+1)) {
                            Object aux = lista[lis].getItem(l);
                            lista[lis].add(lista[lis].getItem());
                            lista[lis].add(aux);
                        }
                    }
                }
                
            }*/

            for (int j = 0; j < lista.length; j++) {
                System.out.print(j + " -> ");
                lista[j].imprimir();
            }
            System.out.println("\n");

        }

    }

    public static ListaDinamica[] Insere(int chave, ListaDinamica[] lista, int m) {
        int x = chave % m;
        lista[x].addEnd(chave);
        return lista;
    }

    public Hash2(int m) {
        this.m = m;

    }

}
