package projetooriginal;

import java.util.Vector;

public class Venda {
	private Vector listaItens;
	private float valorTotal;
	private int codCliente;
	private String loja;
	private java.util.Date data;

	public Venda(){novaVenda();}
	
	//Mais uma definição de valores padrão manual
	public void novaVenda(){
		listaItens = new Vector();
		valorTotal = 0;
		codCliente = 0;
		loja = new String();
		data = new java.util.Date();
	}

	public void setItem(ItemVenda item){listaItens.add(item);}

	public String getLoja(){return loja;}
	
	public void setLoja(String temp){loja = temp;}

	public void setListaItens(Vector listaItens) {this.listaItens = listaItens;}
	
	public void setValorTotal(float valorTotal) {this.valorTotal = valorTotal;}

	public void setCodCliente(int codCliente) {this.codCliente = codCliente;}
	
	public Vector getListaItens() {return (this.listaItens);}

	public float getValorTotal() {
		for (int i = 0; i < this.listaItens.size(); i++){
			ItemVenda item = (ItemVenda)listaItens.elementAt(i);
			valorTotal = item.getValorTotal();
		}
		return (this.valorTotal);
	}

	public int getCodCliente() {return (this.codCliente);}

	public void adicionaItem(ItemVenda item){
		this.valorTotal += item.getValorProduto();
		this.listaItens.add(item);
	}

	public void removeItem(int codigo,int qtde){
		
		for (int i=0 ; i < this.listaItens.size(); i++){
			ItemVenda item = (ItemVenda)listaItens.elementAt(i);
			
			if ( item.getQuantidade() == qtde 
			&& item.getCodigoProduto() == codigo){	
				listaItens.removeElementAt(i);

			}
		}
	}
	
	public void setData(java.util.Date data) {this.data = data;}
	
	public java.util.Date getData() {return (this.data);}
}