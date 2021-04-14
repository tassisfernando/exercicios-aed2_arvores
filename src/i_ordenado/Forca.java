/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i_ordenado;

import java.util.Scanner;

public class Forca {

    static int vidas = 6;
    static int acabou = 0;
    static int sair = 0;

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Digite a palavra (sem acento ou cedilha): ");
        String str = scn.next();
        
        char[] letrasCertas = new char[str.length()]; //AQUI

        for(int i = 0; i < str.length(); i++) { //AQUI
            letrasCertas[i] = '_';
        }
        
        StringBuilder letrasUsadas = new StringBuilder();
        while (vidas != 0) {
            System.out.println("Digite uma letra: ");
            char letra = scn.next().charAt(0);
            while (JaFoiUsada(letra, letrasUsadas)) {
                System.out.println("Letra ja foi usada. Escolha outra");
                letra = scn.next().charAt(0);
            }
            
            //System.out.println(Imprime(Compara(str, letra, letrasCertas))); COMENTEI AQUI OLHA COMO FICOU
            
            if (acabou == 1) {
                System.out.println("Parabens, voce ganhou!!!");
                String palavra = new String(letrasCertas); //AQUII
                System.out.println("Palavra: " + palavra);
                break;
            }
            letrasUsadas.append(letra + " ");
            
            String palavraAtual = new String(letrasCertas); //AQUI
            System.out.println(palavraAtual);
            
            System.out.print("Letras usadas: ");
            System.out.println(letrasUsadas.toString());
            System.out.print("Vidas restantes: ");
            System.out.println(vidas);

        }
        if (acabou == 1) {

        } else {
            System.out.println("Forca. O jogo acabou.");
        }
    }

    public static char[] Compara(String str, char letra, char[] letrasCertas) {
        char[] charvet = str.toCharArray();
        char[] charaux = new char[charvet.length];
        int achou = 0;

        for (int i = 0; i < charvet.length; i++) {
            if (letra == charvet[i]) {
                charaux[i] = charvet[i];
                letrasCertas[i] = charvet[i]; //AQUII
                achou++;
                sair++;
            }
        }
        if (sair == charaux.length) {
            acabou = 1;
        }
        if (achou == 0) {
            vidas--;
        }

        return charaux;
    }

    public static String Imprime(char[] charaux) {
        String ch1 = new String(charaux);
        return ch1;
    }

    public static boolean JaFoiUsada(char letra, StringBuilder letrasUsadas) {
        for (int i = 0; i < letrasUsadas.length(); i += 2) {
            if (letrasUsadas.charAt(i) == letra) {
                return true;
            }
        }
        return false;
    }
}
