package projetooriginal;

public class ItemVenda {
	private int quantidade;
	private int codigoProduto;
	private float valorProduto;
	private float valorServico;
	private int codigoInterno;
	public String nome;

	public ItemVenda(){inicializa();}

	//Aparentemente o java não definia um valor padrão para os atributos
	public void inicializa(){
		quantidade = 0;
		codigoProduto = 0;
		valorProduto = 0;
		valorServico = 0;
		codigoInterno = 0;
		nome = new String();
	}

	public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
	
	public void setCodigoProduto(int codigoProduto) throws Exception{this.codigoProduto = codigoProduto;}

	public void setCodigoInterno(int codigoInterno) {this.codigoInterno = codigoInterno;}
	
	public int getQuantidade() {return (this.quantidade);}

	public int getCodigoProduto() {return (this.codigoProduto);}
	
	public int getCodigoInterno() {return (this.codigoInterno);}

	public void setValorProduto(float valorProduto) {this.valorProduto = valorProduto;}
	
	public float getValorProduto() {return (this.valorProduto);}

	public void setValorServico(float valorServico) {this.valorServico = valorServico;}

	public float getValorServico() {return (this.valorServico);}
	
	public void setNome(String nome) {this.nome = nome;}
	
	public String getNome() {return (this.nome);}
	
	public float getValorTotal(){
		
		//variavel desnecessária
		//float valorTotal = (valorProduto+valorServico) * this.quantidade;

		return (valorProduto + valorServico) * this.quantidade;
	}
}