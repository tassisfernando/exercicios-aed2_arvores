package f_delacao.marco;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author Marco
 */
public class DelacaoPremiada {

    Boss root;
    List<Long> paths = new LinkedList();

    private class Boss {

        int altura = 1;
        long id;
        long distancia = 1;
        long pathId = 0;
        Boss pai;
        List<Boss> sub;

        public Boss(long id) {
            this.id = id;
            sub = new LinkedList();
        }

        private boolean isLeaf() {
            return sub.isEmpty();
        }
    }

    private class Lista implements Comparable<Lista> {

        long pos, value;

        @Override
        public int compareTo(Lista o) {
            if (this.value > o.value) {
                return 1;
            } else if (this.value < o.value) {
                return -1;
            } else {
                return 0;
            }
        }

    }

    public void Mafioso(long[] mafi, int vidente) {
        Map<Long, String> map = new HashMap();
        List<Lista> lista = new LinkedList<>();
        for (int i = 0; i < mafi.length; i++) {
            if (!map.containsKey(mafi[i])) {
                map.put(mafi[i], " " + mafi[i]);
            }

            Lista list = new Lista();
            list.pos = i + 2;
            list.value = mafi[i];
            lista.add(list);
        }
        Collections.sort(lista);
        tree(root = new Boss(1), lista, map);
        List<Boss> folhas = new LinkedList();
        folhas = achaFolhas(folhas, root);
//        addPath(FindPaths(root));
//        PriorityQueue<Long> prio = new PriorityQueue<>(paths);
//        int bestWay = 0;
//        for (int i = 0; i < vidente; i++) {
//          bestWay += prio.poll();
//        }
//        System.out.println(bestWay);
      System.out.println(FindWays(folhas, vidente));
    }

    public void tree(Boss boss, List<Lista> mafi, Map map) {
        if (mafi.isEmpty()) {
            return;
        }
        int j = 0;
        Lista aux;
        for (int i = 0; i < mafi.size(); i++) {
            aux = mafi.get(i);
            if (aux.value == boss.id) {
                boss.sub.add(new Boss(aux.pos));
                mafi.remove(i);
                i--;
                j++;
            }else if(mafi.get(i).value > boss.id)
                i = mafi.size() ;
        }
        if (j == 0) {
            boss.sub = null;
        } else {
            for (Boss newBoss : boss.sub) {
                if (map.get(newBoss.id) != null) { // concertar
                    newBoss.pai = boss;
                    newBoss.altura = boss.altura++;
                    tree(newBoss, mafi, map);
                } else{
                    newBoss.pai = boss;
                    newBoss.altura = boss.altura++;
                }

            }
        }
    }

    private List<Boss> achaFolhas(List<Boss> folhas, Boss raiz) {

        for (Boss newBoss : raiz.sub) {
            if (newBoss.sub.isEmpty()) {
                folhas.add(newBoss);
                return folhas;
            } else {
                achaFolhas(folhas, newBoss);
            }

        }
        return folhas;
    }

    private int FindWays(List<Boss> folhas, int vidente) {
        int distancia, presos = 0;
        Boss bigPath;
        for (int i = 0; i < vidente; i++) {
            bigPath = root;
            for (Boss folha : folhas) {
                distancia = 1;
                Boss aux = folha;
                while (aux.pai != null && aux.pai.pathId == 0) {
                    aux = aux.pai;
                    distancia++;
                }
                if (distancia > bigPath.distancia) {
                    bigPath = folha;
                    bigPath.distancia = distancia;
                }
            }

            presos += bigPath.distancia;
            while (bigPath != null) {
                bigPath.pathId = 1;
                bigPath = bigPath.pai;
            }
            folhas.remove(bigPath);
        }
        return presos;
    }

    private long FindPaths(Boss root) {
        //add o valor no caminho do pai
        //os outros  vao pro vetor de caminhos
        //retorna pro pai q chamou
        //
        //      

        for (Boss newBoss : root.sub) {
            if (newBoss.isLeaf() && root.sub.size() <= 1) {
                return newBoss.pai.distancia = 2;
            } else {
                newBoss.distancia = (FindPaths(newBoss));
        long m = 0, aux;
        for (int i = 0; i < root.sub.size(); i++) {
            aux = root.sub.get(i).distancia;
            if (i == 0) {
                m = aux;
            } else {
                if (m < aux) {
                    m = aux;
                }
                else
                    addPath(aux);
            }
        }
        return newBoss.pai.distancia = m + 1;
            }
        }
        System.out.println(root.distancia);
        return root.distancia ;
    }
    
    private void addPath(long path){
        paths.add(path);
    }

}
