package navalha_batal;

import java.util.Scanner;

public class NavalhaBatal {

    /*
        3 4 1 2 2 3 3 1 3 2

        2 3 1 1 1 2 2 2

        4 3 1 2 3 2 4 4
     */
    private static class MatrizEsparsa {

        //head sentinela principal na posição (-1, -1)
        private Celula head;

        //número de linhas e colunas de toda a matriz
        private final int nLinhas;
        private final int nColunas;

        //número de células não nulas
        private long numCelulas;

        /*
        Classe Célula: cada elemento diferente de 0 da matriz esparsa será salvo como uma célula
         */
        private class Celula {

            private Celula direita, abaixo; //referência das células a direita e abaixo
            private Integer valor; //valor da cor 
            private int linha, coluna; //linha e coluna da célula na matriz esparsa

            //construtor apenas com linha e coluna para criar as sentinelas
            public Celula(int linha, int coluna) {
                this.linha = linha;
                this.coluna = coluna;
            }

            public Integer getValor() {
                return valor;
            }

            //construtor completo para criar células com valores de cor
            public Celula(int linha, int coluna, Integer valor) {
                this.linha = linha;
                this.coluna = coluna;
                this.valor = valor;
            }
        }

        //construtor da matriz que inicializa a sentinela primária (-1, -1) e chama o método de adicionar as outras
        public MatrizEsparsa(int lin, int col) {
            this.numCelulas = 0;
            this.nLinhas = lin;
            this.nColunas = col;
            this.head = new Celula(-1, -1);
            adicionarHeads();
        }

        //método que cria as uma sentinela para cada linha e coluna
        private void adicionarHeads() {
            Celula aux = head;
            for (int lin = 0; lin < nLinhas; lin++) { //percorre toda a coluna -1
                aux.abaixo = new Celula(lin, -1);
                aux = aux.abaixo;
            }

            aux = head;
            for (int col = 0; col < nColunas; col++) { //percorre toda a linha -1
                aux.direita = new Celula(-1, col);
                aux = aux.direita;
            }
        }

        //método que retorna a sentinela de acordo com as coordenadas passadas
        public Celula getHead(int lin, int col) {
            Celula aux = head, aux2;
            while (aux != null && aux.coluna != col) { //verifica se chegou na coluna desejada 
                aux = aux.direita;
            }

            aux2 = aux;
            while (aux2 != null && aux2.linha != lin) { //verifica se chegou na linha desejada
                aux2 = aux2.abaixo;
            }

            return aux2;
        }

        //retorna a célula (caso exista) da posição (lin, col)
        public Celula getCelulaAt(int lin, int col) {
            Celula aux = head;

            while (aux != null && aux.linha != lin) { //chega na linha passada por parâmetro
                aux = aux.abaixo;
            }

            if (aux != null) {
                while (aux != null && aux.coluna != col) { //chega na coluna passada por parâmetro
                    aux = aux.direita;
                }
                if (aux != null) {
                    return aux;
                }
            }
            return null; //se não achar, retorna null
        }

        //retorna a célula (caso exista) da posição (lin, col)
        public Integer getValorCelulaAt(int lin, int col) {
            Celula aux = head;

            while (aux != null && aux.linha != lin) { //chega na linha passada por parâmetro
                aux = aux.abaixo;
            }

            if (aux != null) {
                while (aux != null && aux.coluna != col) { //chega na coluna passada por parâmetro
                    aux = aux.direita;
                }
                if (aux != null) {
                    return aux.valor;
                }
            }
            return null; //se não achar, retorna null
        }

        //método que insere uma célula com um valor válido na lista
        public void inserir(Integer valor, int lin, int col) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {

            if (valor == null) {
                throw new IllegalArgumentException("Tentativa de inserir um valor nulo.");
            }
            if (lin >= this.nLinhas || lin < 0 || col >= this.nColunas || col < 0) {
                throw new ArrayIndexOutOfBoundsException("Tentativa de inserir um elemento numa posição inválida.");
            }

            //cria a nova célula com os valores passados por parâmetro e também pega as sentinelas da linha e coluna
            Celula nova = new Celula(lin, col, valor), celHoriz = this.getHead(lin, -1), celVert = this.getHead(-1, col), auxH = null, auxV = null;

            while (celHoriz != null && celHoriz.coluna != nova.coluna) {
                auxH = celHoriz;
                celHoriz = celHoriz.direita;
            }

            while (celVert != null && celVert.linha != nova.linha) {
                auxV = celVert;
                celVert = celVert.abaixo;
            }

            if (celHoriz == null || celVert == null) { //se tiver na última célula da linha/coluna 
                auxH.direita = nova;
                auxV.abaixo = nova;
            } else {
                auxH.direita = nova;
                nova.direita = celHoriz.direita;
                auxV.abaixo = nova;
                nova.abaixo = celVert.abaixo;
            }

            this.numCelulas++;
        }

        //método toString() que lista os elementos válidos da lista e escreve um "." onde vale 0
        @Override
        public String toString() {
            StringBuilder sbImagem = new StringBuilder();

            for (int lin = 0; lin < this.nLinhas; lin++) {
                for (int col = 0; col < this.nColunas; col++) {
                    Celula aux = getCelulaAt(lin, col);
                    if (aux == null) {
                        sbImagem.append(".");
                    } else if (aux.valor != null) {
                        sbImagem.append(aux.valor);
                    }
                    sbImagem.append("\t");
                }
                sbImagem.append("\n");
            }
            return sbImagem.toString();
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int q = scn.nextInt();
        MatrizEsparsa tabuleiro = new MatrizEsparsa(n, n);

        try {
            for (int i = 1; i <= q; i++) {
                int x = scn.nextInt();
                int y = scn.nextInt();

                tabuleiro.inserir(1, x - 1, y - 1);
            }

            int navios = 0;
            int sequencia = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    Integer cel = tabuleiro.getValorCelulaAt(i, j);
                    if (cel == null) {
                        tabuleiro.inserir(0, i, j);
                        sequencia++;
                    } else {
                        while (sequencia > 0) {
                            navios += sequencia;
                            sequencia--;
                        }
                        sequencia = 0;
                    }
                }
                while (sequencia > 0) {
                    navios += sequencia;
                    sequencia--;
                }
                sequencia = 0;
            }

            sequencia = 0;
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < n; i++) {
                    Integer cel = tabuleiro.getValorCelulaAt(i, j);
                    if (cel == 0) {
                        sequencia++;
                    } else {
                        sequencia--;
                        while (sequencia > 0) {
                            navios += sequencia;
                            sequencia--;
                        }
                        sequencia = 0;
                    }
                }
                sequencia--;
                while (sequencia > 0) {
                    navios += sequencia;
                    sequencia--;
                }
                sequencia = 0;
            }
            System.out.print(navios);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.print("Erro ao inserir bavio: " + ex.getMessage());
        }

        scn.close();
    }
}
