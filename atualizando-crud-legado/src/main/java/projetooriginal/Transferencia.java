package projetooriginal;

import java.util.Date;
import java.util.Vector;

/*
Classe Transferencia
Modela uma transferencia de produtos entre lojas
Guarda informacoes de quais produtos foram de uma loja pra outra,
e guarda quais foram as lojas envolvidas na tranferencia.
*/
public class Transferencia {
	
	String deLoja;
	String paraLoja;
	Date data;

	int listaProdutos[][];
	public Transferencia(){inicializa();}

	//Valores padrão definidos pelo desenvolvedor
	//sem atribuição de valores padrão automática
	public void inicializa(){
		deLoja = new String();
		paraLoja = new String();
		data = new Date();
		listaProdutos = new int[100][2];
	}

	public void addProduto(int codigo,int qtde){
		int count = 0;

		while (listaProdutos[count][0] != 0){count++;}
		
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
	
	public void setDeLoja(String deLoja) {this.deLoja = deLoja;}

	public void setParaLoja(String paraLoja) {this.paraLoja = paraLoja;}
	
	public void setData(Date data) {this.data = data;}

	public void setListaProdutos(int[][] listaProdutos) {this.listaProdutos = listaProdutos;}
	
	public String getDeLoja() {return this.deLoja;}

	public String getParaLoja() {return this.paraLoja;}
	
	public Date getData() {return this.data;}
	
	public int[][] getListaProdutos() {return this.listaProdutos;}
}