package projetooriginal;

/**
 * 
 * A classe tinha aproximadamente 65 linhas que foram reduzidas naturalmente
 * com a aplicação de uma identação um pouco mais intuitiva, organização dos atributos
 * e a remoção do atributo (int) celular.
 * Sem os comentários a classe tem 33 linhas.
 * 
 **/
public class Cliente {
	/**
	* Alterei os valores de cep, cpf, endereco e telefone para String
	* cpf estava como long e os demais como int.
	* Retirei o atributo (int) celular, pareceu desnecessário
	* mantive o atributo codigo como int pois ainda não analizei o projeto
	* e não identifiquei a função do atributo.
	**/
	private int codigo;
	private String nome, bairro, cidade, endereco, cpf, telefone, cep;

	public Cliente(){}

	public void setCodigo(int codigo) {this.codigo = codigo;}
	public int getCodigo() {return (this.codigo);}
	
	public void setNome(String nome) {this.nome = nome;}
	public String getNome() {return (this.nome);}
	
	public void setTelefone(String telefone) {this.telefone = telefone;}
	public String getTelefone() {return (this.telefone);}
	
	public void setBairro(String bairro) {this.bairro = bairro;}
	public String getBairro() {return (this.bairro);}

	public void setCidade(String cidade) {this.cidade = cidade;}
	public String getCidade() {return (this.cidade);}

	public void setEndereco(String endereco) {this.endereco = endereco;}
	public String getEndereco() {return (this.endereco);}

	public void setCep(String cep) {this.cep = cep;}
	public String getCep() {return (this.cep);}

	public void setCpf(String cpf){this.cpf = cpf;}
	public String getCpf() {return (this.cpf);}
}