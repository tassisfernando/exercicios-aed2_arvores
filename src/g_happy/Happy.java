package happy;

import java.util.Scanner;

public class Happy {

    /*
3 
ABEL ABIJKL AJKL ABEL JKL EFGHIJKL
ABEFGL CIJKL ABH AKL ABIJKL EFGHIJKL
ABL HIJKL AIL AFGJKL AFGJKL EFGHIJK

1
ABEL ABIJKL AJKL ABEL JKL EFGHIJKL
     */
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int alunos = scn.nextInt();

        String[][] horario = new String[alunos][6];

        for (int al = 0; al < alunos; al++) {
            for (int dia = 0; dia < 6; dia++) {
                horario[al][dia] = scn.next();
            }
        }

        String[] livre = new String[6];

        if (alunos == 1) {
            for (int dia = 0; dia < 6; dia++) {
                livre[dia] = horario[0][dia];
            }
        } else {
            for (int dia = 0; dia < 6; dia++) {
                String horDia = horario[0][dia];
                for (int al = 1; al < alunos; al++) {
                    String livre1 = "";
                    for (int i = 0; i < horDia.length(); i++) {
                        if (contains(horario[al][dia], horDia.charAt(i))) {
                            livre1 += horDia.charAt(i);
                        }
                    }
                    horDia = livre1;
                    livre[dia] = livre1;
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            System.out.print(livre[i] + " ");
        }
    }

    public static boolean contains(String st, char c) {
        if (st != null) {
            for (int i = 0; i < st.length(); i++) {
                if (st.charAt(i) == c) {
                    return true;
                }
            }
        }
        return false;
    }

}
