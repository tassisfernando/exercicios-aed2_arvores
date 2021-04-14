/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b_hash;

/**
 *
 * @author tassi
 */
public class ListaDinamica {

    private Celula head;
    private int size;

    private class Celula {

        Object item;
        Celula prox;
    }

    public ListaDinamica() {
        head = null;
        size = 0;
    }

    void add(Object item) {
        if (item == null) {
            throw new NullPointerException("NÃ£o Ã© permitido inserir um elemento nulo.");
        }
        Celula novo = new Celula();
        novo.item = item;
        novo.prox = head;
        head = novo;
        size++;
    }

    void addOrder(Object item) {
        if (item == null) {
            throw new NullPointerException("NÃ£o Ã© permitido inserir um elemento nulo.");
        }

        if (head == null) {
            head = new Celula();
            head.item = item;
            size++;
        } else {
            Celula aux = head;
            int chave = (Integer) item;

            while (aux.prox != null && chave > (Integer) aux.item) {
                aux = aux.prox;
            }

            Celula novo = new Celula();
            novo.item = item;
            novo.prox = aux.prox;
            aux.prox = novo;
            size++;
        }
    }

    void addEnd(Object item) {
        if (item == null) {
            throw new NullPointerException("NÃ£o Ã© permitido inserir um elemento nulo.");
        }

        if (head == null) {
            head = new Celula();
            head.item = item;
            size++;
        } else {
            Celula aux = head;
            while (aux.prox != null) {
                aux = aux.prox;
            }

            aux.prox = new Celula();
            aux.prox.item = item;
            size++;
        }

    }

    void add(int pos, Object item) {
        if (pos < 0 || pos > size) {
            throw new IllegalArgumentException("PosiÃ§Ã£o informada invÃ¡lida.");
        }
        if (item == null) {
            throw new NullPointerException("O item passado Ã© nulo.");
        }
        Celula aux = head, ant = null;
        int i = 0;

        while (aux != null && i < pos) {
            i++;
            ant = aux;
            aux = aux.prox;
        }

        if (aux != null && pos >= 0) {
            if (aux == head) {
                head = new Celula();
                head.item = item;
                head.prox = aux;
            } else {
                Celula nova = new Celula();
                nova.item = item;
                nova.prox = aux;
                ant.prox = nova;
            }
            size++;
        } else if (pos == size) {
            Celula nova = new Celula();
            nova.item = item;
            ant.prox = nova;
            size++;
        }
    }

    Object excluir(int pos) {
        int i = 0;
        Celula aux = head, ant = null;

        while (aux != null && i < pos) {
            i++;
            ant = aux;
            aux = aux.prox;
        }

        if (aux != null && pos >= 0) {
            if (aux == head) {
                head = head.prox;
            } else {
                ant.prox = aux.prox;
            }
            size--;
            return aux.item;
        }
        return -1;
    }

    public int getSize() {
        return size;
    }

    public Object getItem(int pos) {
        int cont = 0;
        Celula aux = head;

        while (aux != null && cont < pos) {
            aux = aux.prox;
            if (cont == pos) {
                return aux;
            }
            cont++;
        }

        return aux;
    }

    void imprimir() {
        Celula aux = head;

        while (aux != null) {
            System.out.print(aux.item + " -> ");
            aux = aux.prox;
        }
        System.out.println("\\");
    }

}
