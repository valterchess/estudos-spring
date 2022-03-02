package projetooriginal;

//Apenas alterações na identação
public class Usuario{
	private String nome;
	private String senha;

	//Aparentemente o java também não tinha um construtor automático
	public Usuario(){}

	public Usuario(String nome, String senha){
		this.nome = nome;
		this.senha = senha;
	}

	public void setNome(String nome) {this.nome = nome;}

	public void setSenha(String senha) {this.senha = senha;}

	public String getNome() {return (this.nome);}

	public String getSenha() {return (this.senha);}
}