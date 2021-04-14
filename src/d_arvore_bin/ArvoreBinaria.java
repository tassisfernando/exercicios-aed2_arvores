package d_arvore_bin;

import java.util.*;

/*
2 
3 5 2 7
9 8 3 10 14 6 4 13 7 1
 */
class No {

    public long item;
    public No dir;
    public No esq;
}

class ArvoreBinaria {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int c = scn.nextInt();

        for (int i = 1; i <= c; i++) {
            int n = scn.nextInt();

            ArvoreBinaria arvore = new ArvoreBinaria();

            for (int j = 1; j <= n; j++) {
                long no = scn.nextLong();
                arvore.inserir(no);
            }

            System.out.print("Case " + i + ": ");
            arvore.caminhar();
            System.out.println("\n");
        }
    }

    private No raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

    public void inserir(long v) {
        No novo = new No();
        novo.item = v;
        novo.dir = null;
        novo.esq = null;

        if (raiz == null) {
            raiz = novo;
        } else {
            No atual = raiz;
            No anterior;
            while (true) {
                anterior = atual;
                if (v <= atual.item) {
                    atual = atual.esq;
                    if (atual == null) {
                        anterior.esq = novo;
                        return;
                    }
                } else {
                    atual = atual.dir;
                    if (atual == null) {
                        anterior.dir = novo;
                        return;
                    }
                }
            }
        }
    }

    public No buscar(long chave) {
        if (raiz == null) {
            return null;
        }
        No atual = raiz;
        while (atual.item != chave) {
            if (chave < atual.item) {
                atual = atual.esq;
            } else {
                atual = atual.dir;
            }
            if (atual == null) {
                return null;
            }
        }
        return atual;
    }

    public boolean remover(long v) {
        if (raiz == null) {
            return false;
        }
        No atual = raiz;
        No pai = raiz;
        boolean filho_esq = true;

        while (atual.item != v) {
            pai = atual;
            if (v < atual.item) {
                atual = atual.esq;
                filho_esq = true;
            } else {
                atual = atual.dir;
                filho_esq = false;
            }
            if (atual == null) {
                return false;
            }
        }

        if (atual.esq == null && atual.dir == null) {
            if (atual == raiz) {
                raiz = null;
            } else if (filho_esq) {
                pai.esq = null;
            } else {
                pai.dir = null;
            }
        } else if (atual.dir == null) {
            if (atual == raiz) {
                raiz = atual.esq;
            } else if (filho_esq) {
                pai.esq = atual.esq;
            } else {
                pai.dir = atual.esq;
            }
        } else if (atual.esq == null) {
            if (atual == raiz) {
                raiz = atual.dir;
            } else if (filho_esq) {
                pai.esq = atual.dir;
            } else {
                pai.dir = atual.dir;
            }
        } else {
            No sucessor = no_sucessor(atual);
            if (atual == raiz) {
                raiz = sucessor;
            } else if (filho_esq) {
                pai.esq = sucessor;
            } else {
                pai.dir = sucessor;
            }
            sucessor.esq = atual.esq;
        }

        return true;
    }

    public No no_sucessor(No apaga) {
        No paidosucessor = apaga;
        No sucessor = apaga;
        No atual = apaga.dir;

        while (atual != null) {
            paidosucessor = sucessor;
            sucessor = atual;
            atual = atual.esq;
        }

        if (sucessor != apaga.dir) {

            sucessor.dir = apaga.dir;
        }
        return sucessor;
    }

    public void caminhar() {
        System.out.print("\nPre.: ");
        preOrder(raiz);
        System.out.print("\nIn..: ");
        inOrder(raiz);
        System.out.print("\nPost: ");
        posOrder(raiz);
    }

    public void inOrder(No atual) {
        if (atual != null) {
            inOrder(atual.esq);
            System.out.print(atual.item + " ");
            inOrder(atual.dir);
        }
    }

    public void preOrder(No atual) {
        if (atual != null) {
            System.out.print(atual.item + " ");
            preOrder(atual.esq);
            preOrder(atual.dir);
        }
    }

    public void posOrder(No atual) {
        if (atual != null) {
            posOrder(atual.esq);
            posOrder(atual.dir);
            System.out.print(atual.item + " ");
        }
    }

    public int altura(No atual) {
        if (atual == null || (atual.esq == null && atual.dir == null)) {
            return 0;
        } else {
            if (altura(atual.esq) > altura(atual.dir)) {
                return (1 + altura(atual.esq));
            } else {
                return (1 + altura(atual.dir));
            }
        }
    }

    public int folhas(No atual) {
        if (atual == null) {
            return 0;
        }
        if (atual.esq == null && atual.dir == null) {
            return 1;
        }
        return folhas(atual.esq) + folhas(atual.dir);
    }

    public int contarNos(No atual) {
        if (atual == null) {
            return 0;
        } else {
            return (1 + contarNos(atual.esq) + contarNos(atual.dir));
        }
    }

    public No min() {
        No atual = raiz;
        No anterior = null;
        while (atual != null) {
            anterior = atual;
            atual = atual.esq;
        }
        return anterior;
    }

    public No max() {
        No atual = raiz;
        No anterior = null;
        while (atual != null) {
            anterior = atual;
            atual = atual.dir;
        }
        return anterior;
    }

}
