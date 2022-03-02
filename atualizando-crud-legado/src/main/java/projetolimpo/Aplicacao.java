package projetolimpo;

import projetooriginal.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class Aplicacao {
    private final BancoDados banco;
    private Venda venda;
    private EntradaProdutos entrada;
    private Transferencia transferencia;

    public Aplicacao() throws Exception {
        banco = new BancoDados();
    }

    public boolean login(String name, String senha) throws Exception {
         return ValidaLogin.validaLogin(name,senha,banco);
    }

    public Venda getVenda(){
        if (venda == null) venda = new Venda();
        return venda;
    }

    public Connection getConexao(){ return banco.getConexao();}

    public EntradaProdutos getEntrada() {
        if (entrada == null) {
            entrada = new EntradaProdutos();
        }
        return entrada;
    }

    public Transferencia transferencia(){
        if (transferencia == null){transferencia = new Transferencia();}
        return transferencia;
    }

    public ResultSet processeQuery(String query) throws Exception {
        return banco.processeQuery(query);
    }

    public String getNomeBone(int codigo) throws Exception{
        String consulta = String.format("select nome from bones where codigo =%d",codigo);
        ResultSet resultado = banco.processeQuery(consulta);
        resultado.first();
        return resultado.getString(1);
    }

    //retorna os nomes das colunas de uma determinada tabela
    public Vector<String> getNomeColunas(String tabela)throws Exception{
        Vector<String> listaNomes = new Vector<>();
        String consulta = "select * from clientes";
        ResultSet resultado = banco.processeQuery(consulta);
        ResultSetMetaData metaData = resultado.getMetaData();

        for (int i = 1; i < metaData.getColumnCount(); i++){
            listaNomes.add(metaData.getColumnName(i));
        }
        return listaNomes;
    }

    //Metodos para o tratamento do cliente
    public Vector<Cliente> getClientes() throws Exception{
        String consulta = "select * from clientes order by nome";
        ResultSet resultado = banco.processeQuery(consulta);
        return clienteParaVector(resultado);
    }
    private Vector<Cliente> clienteParaVector(ResultSet resultado)throws Exception{
        Vector<Cliente> lista = new Vector<Cliente>();
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
}
