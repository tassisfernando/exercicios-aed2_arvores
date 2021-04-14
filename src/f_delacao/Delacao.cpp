#include <iostream>
#include <vector>
using namespace std;

const int MAXN = 100000;

vector<int> adj[MAXN];
int prox[MAXN];
int qtd[MAXN];

int validos[MAXN], cont = 0;

void dfsCompute(int u);
void dfsCalc(int u, int type);

void countingSort(int *vet, int n);

int main() {
    int n, k, in;
    cin >> n >> k;

    for(int i = 1; i < n; i++) {
        cin >> in;
        adj[in-1].push_back(i);
    }

    dfsCompute(0);
    dfsCalc(0, 1);

    if(cont < k)
        k = cont;
    
    countingSort(validos, cont);
    int res = 0;
    for(int i = 0; i < k; i++) 
        res += validos[i];
    
    cout << res << endl;

    return 0;
}

void dfsCompute(int u) {
    int foco;
    prox[u] = -1;
    qtd[u] = 0;

    for(int k = 0; k < adj[u].size(); k++) {
        foco = adj[u][k];
        dfsCompute(foco);
        if(qtd[u] < qtd[foco]) {
            qtd[u] = qtd[foco];
            prox[u] = foco;
        }
    }
    qtd[u] = qtd[u] + 1;
}

void dfsCalc(int u, int type) {
    int foco;
    if(type) 
        validos[cont++] = qtd[u];
    
    for(int k = 0; k < adj[u].size(); k++) {
        foco = adj[u][k];
        dfsCalc(foco, (foco != prox[u]));
    }
}

void countingSort(int *vet, int n) {
    int contAux[MAXN], salv[MAXN];

    for(int i = 0; i < MAXN; i++)
        contAux[i] = 0;
    
    for(int i = 0; i < n; i++) {
        contAux[vet[i]]++;
        salv[i] = vet[i];
    }

    for(int i = MAXN-2; i>=0; i--)
        contAux[i] += contAux[i+1];
    
    for(int i = 0; i < n; i++) { 
        vet[contAux[salv[i]] - 1] = salv[i];
        contAux[salv[i]]--;
    }
}

