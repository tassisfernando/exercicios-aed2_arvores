package g_happy; 

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Happy2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<String> strings = new LinkedList();

        int students = input.nextInt();
        for(int i = 0; i < students * 6; i++){
            strings.add(input.next());
        }

        checkRepeat(strings, students);
    }

    private static void checkRepeat(List<String> strings, int students){
        String tot = "";
        if(students == 1)
            print(strings);
        else {
            for (int i = 0; i < strings.size(); i++) {
                int cont = 0;
                int CONST = 0;
                int[] vet = new int[12];
                while(cont < students){
                    if(CONST + i < strings.size()){
                        String value = strings.get(i + CONST);
                        for(int j = 0; j < value.length(); j++){
                            switch (value.charAt(j)) {
                                case 'A':
                                    vet[0]++;
                                    break;
                                case 'B':
                                    vet[1]++;
                                    break;
                                case 'C':
                                    vet[2]++;
                                    break;
                                case 'D':
                                    vet[3]++;
                                    break;
                                case 'E':
                                    vet[4]++;
                                    break;
                                case 'F':
                                    vet[5]++;
                                    break;
                                case 'G':
                                    vet[6]++;
                                    break;
                                case 'H':
                                    vet[7]++;
                                    break;
                                case 'I':
                                    vet[8]++;
                                    break;
                                case 'J':
                                    vet[9]++;
                                    break;
                                case 'K':
                                    vet[10]++;
                                    break;
                                case 'L':
                                    vet[11]++;
                                    break;
                            }
                        }
                    }
                    CONST += 6;
                    cont++;
                }
                tot += fill(vet, students);
            }
        }
        System.out.println(tot.trim());
    }

    private static String  fill(int[] vet, int students) {
        String junt = "";
       if(vet[0] == students)
           junt = junt + "A";

       if(vet[1] == students)
           junt = junt + "B";

       if(vet[2] == students)
           junt = junt + "C";

       if(vet[3] == students)
           junt = junt + "D";

       if(vet[4] == students)
           junt = junt + "E";

       if(vet[5] == students)
           junt = junt + "F";

       if(vet[6] == students)
           junt = junt + "G";

       if(vet[7] == students)
           junt = junt + "H";

       if(vet[8] == students)
           junt = junt + "I";

       if(vet[9] == students)
           junt = junt + "J";

       if(vet[10] == students)
           junt = junt + "K";

       if(vet[11] == students)
           junt = junt + "L";

       junt = junt + " ";
       return junt;
    }

    private static void print(List<String> strings){
        for(String x: strings){
            System.out.print(x + " ");
        }
    }
}
