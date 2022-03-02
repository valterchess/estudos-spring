package projetooriginal;

import java.sql.*;
import java.util.*;

public class BancoDados{
	private Connection conexao;
	private ResultSet resultado = null;
	private PreparedStatement query = null;
	
	public BancoDados() throws Exception{
		Class.forName("org.gjt.mm.mysql.Driver");
		conexao = DriverManager.getConnection("jdbc:mysql://localhost/bone?user=henrique&password=2002henrique&autoReconnect=true");
	}
	
	public Connection getConexao(){return conexao;}

	public synchronized ResultSet processeQuery(String query) throws Exception{
		PreparedStatement stt = conexao.prepareStatement(query);
		return stt.executeQuery();
	}	

	public synchronized void cadastraCliente(Cliente cliente) throws Exception{
		query = conexao.prepareStatement("insert into clientes values('',?,?,?,?,?,?,?,?)");
		query.setString(1,cliente.getNome());
		query.setString(2,cliente.getTelefone());
		query.setString(4,cliente.getCpf());
		query.setString(5,cliente.getEndereco());
		query.setString(6,cliente.getBairro());
		query.setString(7,cliente.getCidade());
		query.setString(8,cliente.getCep());
		query.execute();
	}
	
	public synchronized void atualizaCliente(Cliente cliente) throws Exception{
		query = conexao.prepareStatement("update clientes set nome = ?,"+
		"telefone = ?,celular = ?,cpf = ?,endereco = ?,bairro = ?,"+
		"cidade = ?,cep = ? where codigo = ?");
		query.setString(1,cliente.getNome());
		query.setString(2,cliente.getTelefone());
		query.setString(4,cliente.getCpf());
		query.setString(5,cliente.getEndereco());
		query.setString(6,cliente.getBairro());
		query.setString(7,cliente.getCidade());
		query.setString(8,cliente.getCep());
		query.setInt(9,cliente.getCodigo());
		query.execute();
	}
	public synchronized void cadastraItemVenda(ItemVenda item,int
		codCliente,String loja,java.util.Date data)throws Exception{
		PreparedStatement query = conexao.prepareStatement("insert into vendavalues (?,?,?,?,?,'',?,?,?)");
		query.setInt(1,codCliente);
		query.setInt(2,item.getCodigoProduto());
		query.setFloat(3,item.getValorProduto());
		query.setFloat(4,item.getValorServico());
		query.setFloat(5,item.getValorTotal());
		query.setString(6,loja);
		query.setDate(7,new java.sql.Date(data.getTime()));
		query.setInt(8,item.getQuantidade());
		query.execute();
	}
}