package projetooriginal;

import java.sql.*;
import java.util.Vector;

public class Aplicacao {
	private BancoDados banco;
	private Venda venda;
	private EntradaProdutos entrada;
	private Transferencia transferencia;
	private Relatorio relatorio;



	public Aplicacao()throws Exception{banco = new BancoDados();}

	public boolean validaLogin(String nome, String senha) throws Exception{
		
		String consulta = new String("select * from usuarios where nome ='"+nome+"' && senha = '"+senha+"'");
		ResultSet resultado = banco.processeQuery(consulta);
		if(resultado.next()){
			if ( nome.equals(resultado.getString(1))
			&& senha.equals(resultado.getString(2) )) {
				return true;
			}
		}
		return false;
	}

	public Venda getVenda(){
		
		if (venda == null) {venda = new Venda();}

		return venda;
	}

	public Connection getConexao(){return banco.getConexao();}
	
	public Relatorio getRelatorio(){
		if (relatorio == null) {relatorio = new Relatorio();}
		return relatorio;
	}
	
	public EntradaProdutos getEntrada(){
		if (entrada == null){entrada = new EntradaProdutos();}
		return entrada;
	}

	public Transferencia getTransferencia(){
		if (transferencia == null){transferencia = new Transferencia();}
		return transferencia;
	}

	public ResultSet processeQuery(String query) throws Exception {
		return banco.processeQuery(query);
	}
	
	public String getNomeBone(int codigo) throws Exception{
		String consulta = new String("select nome from bones where codigo ="+codigo);
		ResultSet resultado = banco.processeQuery(consulta);
		resultado.first();
		String nome = resultado.getString(1);
		return nome;
	}

	//retorna os nomes das colunas de uma determinada tabela 
	public Vector getNomeColunas(String tabela)throws Exception{
		Vector listaNomes = new Vector();
		String consulta = new String("select * from clientes");
		ResultSet resultado = banco.processeQuery(consulta);
		ResultSetMetaData metadata = resultado.getMetaData();
		
		for (int i = 1; i <= metadata.getColumnCount(); i++){

			listaNomes.add(new String(metadata.getColumnName(i)));
		}
		return listaNomes;
	}

	//Metodos para o tratamento do cliente
	public Vector getClientes() throws Exception{
		String consulta = new String("select * from clientes order by nome");
		ResultSet resultado = banco.processeQuery(consulta);
		return clienteParaVector(resultado);
	}

	private Vector clienteParaVector(ResultSet resultado)throws Exception{
		Vector lista = new Vector();
		while(resultado.next()){
			Cliente cli = new Cliente();
			cli.setCodigo(resultado.getInt(1));
			cli.setNome(resultado.getString(2));
			cli.setTelefone(resultado.getString(3));
			cli.setCpf(resultado.getString(5));
			cli.setEndereco(resultado.getString(6));
			cli.setBairro(resultado.getString(7));
			cli.setCidade(resultado.getString(8));
			cli.setCep(resultado.getString(9));
			lista.add(cli);
		}
		return lista;
	}

	public Cliente getCliente(int cod) throws Exception{
		String consulta = new String("select * from clientes where codigo ="+cod);
		ResultSet resultado = banco.processeQuery(consulta);
		resultado.first();
		
		Cliente cli = new Cliente();
		cli.setCodigo(resultado.getInt(1));
		cli.setNome(resultado.getString(2));
		cli.setTelefone(resultado.getString(3));
		cli.setCpf(resultado.getString(4));
		cli.setEndereco(resultado.getString(5));
		cli.setBairro(resultado.getString(6));
		cli.setCidade(resultado.getString(7));
		cli.setCep(resultado.getString(8));
		
		return cli;
	}

	public Cliente getClienteNome(String nome)throws Exception{
		String consulta = new String("select * from clientes where nome ='+nome+' order by name");
		ResultSet resultado = banco.processeQuery(consulta);
		resultado.first();

		Cliente cli = new Cliente();
		cli.setCodigo(resultado.getInt(1));
		cli.setNome(resultado.getString(2));
		cli.setTelefone(resultado.getString(3));
		cli.setCpf(resultado.getString(4));
		cli.setEndereco(resultado.getString(5));
		cli.setBairro(resultado.getString(6));
		cli.setCidade(resultado.getString(7));
		cli.setCep(resultado.getString(8));
		return cli;
	}
	public void cadastraCliente(Cliente cliente) throws Exception{
		banco.cadastraCliente(cliente);
		//pega o ultimo codigo inserido e seta para o cliente.
		String consulta = new String("select LAST_INSERT_ID()");
		ResultSet resultado = banco.processeQuery(consulta);
		resultado.first();
		cliente.setCodigo(resultado.getInt(1));
	}

	public void atualizaCliente(Cliente cliente) throws Exception{banco.atualizaCliente(cliente);}

	public void removeCliente(Cliente cliente) throws Exception{
		String consulta = new String("delete from clientes where codigo ="+cliente.getCodigo());
		banco.processeQuery(consulta);
	}

	public void removeCliente(int codigo) throws Exception{
		String consulta = new String("delete from clientes where codigo ="+codigo);
		banco.processeQuery(consulta);
	}

	public Vector procuraCliente(String procura,String tipo) throws Exception{
			String consulta = new String("select * from clientes where "+tipo+"like '%"+procura+"%' order by nome");
			return clienteParaVector( banco.processeQuery(consulta) );
	}
	
	/**********************************************************************
	Métodos para cuidar das transferencias de produtos
	**************************************/
	public ResultSet getEstoque() throws Exception{
		String consulta = new String("select bones.codigo,nome,estoque_.* from "
			+ "bones, estoque_ where bones.codigo = estoque_.codigo order by bones.codigo");
		return banco.processeQuery(consulta);
	}
	public Vector getNomeLojas() throws Exception{
		String consulta = new String("select * from estoque_ where codigo =100");
		
		ResultSet resultado = banco.processeQuery(consulta);
		ResultSetMetaData rsmeta = resultado.getMetaData();
		
		Vector nomes = new Vector();
		
		for(int i = 2 ; i <= rsmeta.getColumnCount() ; i++){
				nomes.add(rsmeta.getColumnName(i));
		}
		return nomes;
	}

	public void processeEntrada() throws Exception {
		int cod,qtde;
		int i = 0;
		int listaProdutos[][] = entrada.getListaProdutos();
		
		while(listaProdutos[i][0] != 0){
			cod = listaProdutos[i][0];
			qtde = listaProdutos[i][1];
			//arruma coluna do QG
			String query = new String("update estoque_ set qg = qg +"+qtde+" where codigo = "+cod);
			banco.processeQuery(query);
				
			//arruma a transferencia
			query = new String("update estoque_ set tqg = tqg + "+qtde+"where codigo = "+cod);
			banco.processeQuery(query);
		
			//armazena na tabela entradaEstoque
			Date datasql = new Date(entrada.getData().getTime());
			
			query = new String("insert into entradaEstoque values('"+datasql+"',"+cod+",'"+qtde+"')");
			banco.processeQuery(query);
			i++;
		}
	}

	//entrada.inicializa();

	public boolean processaTransferencia()throws Exception{
		String aux = new String();
		String deLoja = transferencia.getDeLoja().toLowerCase();
		String paraLoja = transferencia.getParaLoja().toLowerCase();
		int listaProdutos[][] = transferencia.getListaProdutos();
		int cod,qtde;
		int i = 0;
		while(listaProdutos[i][0] != 0){
			cod = listaProdutos[i][0];
			qtde = listaProdutos[i][1];
			
			//baixa da loja inicial
			aux = "update estoque_ set "+deLoja+" = "+deLoja+" - "+qtde+"where codigo = "+cod;
			banco.processeQuery(aux);
			
			// baixa da transferencia mensal da loja inicial
			// se for na loja QG então não mexe
			if (!deLoja.equals("qg")){
				aux = "update estoque_ set t"+deLoja+" = t"+deLoja+" -"+qtde+" where codigo = "+cod;
				banco.processeQuery(aux);
			}
			//adiciona na loja final
		aux = "update estoque_ set "+paraLoja+" = "+paraLoja+" +"+qtde+" where codigo = "+cod;
		banco.processeQuery(aux);
		
		//adiciona na transferencia mensal da loja final
		// se for na loja QG então não mexe
			if (!paraLoja.equals("qg")){
				aux = "update estoque_ set t"+paraLoja+" = t"+paraLoja+"+ "+qtde+" where codigo = "+cod;
				banco.processeQuery(aux);
			}
			i++;
		}
		transferencia.inicializa();
		return true;
	}

	public void processaVenda() throws Exception{
		for (int i=0 ; i < venda.getListaItens().size(); i++){
			ItemVenda item = (ItemVenda)venda.getListaItens().elementAt(i);
			banco.cadastraItemVenda(item,venda.getCodCliente(),venda.getLoja(),venda.getData());
			
			// atualiza o estoque da venda.
			processaEstoqueVenda(item,venda.getLoja());
		}
		venda.novaVenda();
	}
	private void processaEstoqueVenda(ItemVenda item,String loja) throws Exception{
		int codigo = item.getCodigoProduto();
		int quantidade = item.getQuantidade();
		loja = loja.substring(0,1);
		String query = new String("update estoque_ set "+loja+" = "+loja+" -"+quantidade+" where codigo = "+codigo);
		banco.processeQuery(query);
	}
}