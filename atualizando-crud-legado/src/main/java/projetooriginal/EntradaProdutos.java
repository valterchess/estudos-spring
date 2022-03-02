package projetooriginal;

import java.util.Vector;

public class EntradaProdutos {
	java.util.Date data;

	//erro de convenção
	int listaProdutos[][];

	public EntradaProdutos(){inicializa();}

	//private? necessário? bloco de inicialização?
	public void inicializa(){
		listaProdutos = new int[100][2];
		data = new java.util.Date();
	}

	public void addProduto(int codigo,int qtde){
		int count = 0;
		
		while (listaProdutos[count][0] != 0){
		count++;
		}
		
		listaProdutos[count][0] = codigo;
		listaProdutos[count][1] = qtde;
	}
	
	public void removeProduto(int codigo){
		int i = codigo;
		while(listaProdutos[i][0] != 0){
			listaProdutos[i][0] = listaProdutos[i+1][0];
			listaProdutos[i][1] = listaProdutos[i+1][0];
			i++;
		}
	}
	
	public void setData(java.util.Date data) {this.data = data;}

	public void setListaProdutos(int[][] listaProdutos) {this.listaProdutos = listaProdutos;}
	
	public java.util.Date getData() {return this.data;}
	
	public int[][] getListaProdutos() {return this.listaProdutos;}
}