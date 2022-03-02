package projetooriginal;

import java.sql.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

public class Controlador{

	private Aplicacao aplicacao;
	private boolean redirecionar;
	private String proxPagina;

	public Controlador(){
		redirecionar = false;
		proxPagina = null;
	}
	
	public void processaOpcao(HttpServletRequest request, HttpSession session)
	throws Exception{

		redirecionar = false;

		if (request.getAttribute("acessoIlegal") != null) {
		proxPagina = "loginInvalido.jsp";
		redirecionar = true;
		} 
		
		else {
			String opcao = request.getParameter("opcao");
		
			if (opcao == null) {
		
			proxPagina = "index.jsp";
			redirecionar = true;
		
			} else if(opcao.equals("venda")){
				processaOpcaoVenda(request,session);
		
			} else if(opcao.equals("clientes")){
				processaOpcaoClientes(request,session);
		
			} else if(opcao.equals("estoque")){
				processaOpcaoEstoque(request,session);
		
			} else if(opcao.equals("relatorios")){
				processaOpcaoRelatorios(request,session);
		
			} else if(opcao.equals("login")){
				processeLogin(request,session);

			} else {
				proxPagina = "index.jsp";
				redirecionar = true;
			}
		}
	}

	private void processaOpcaoRelatorios(HttpServletRequest request,
	HttpSession session)throws Exception{

		String subOpcao = request.getParameter("subOpcao");
		
		if (subOpcao == null){
			proxPagina = "relatorios/relatorios.jsp";
			redirecionar = true;
		
		} else if (subOpcao.equals("porLoja")){
			proxPagina = "relatorios/relatorio1.jsp";
			redirecionar = true;
		
		} else if (subOpcao.equals("selLoja")){
			String loja = (String)request.getParameter("loja");
			proxPagina = "relatorios/relatorioLoja.jsp?loja="+loja;
			redirecionar = true;
		}
	}
	
	private void processaOpcaoEstoque(HttpServletRequest request,
	 HttpSession session)throws Exception{
	
		String subOpcao = request.getParameter("subOpcao");
		
		if (subOpcao == null){
			proxPagina = "estoque/estoque.jsp";
			redirecionar = true;

		} else if(subOpcao.equals("completa")){
			proxPagina = "estoque/completa.jsp";
			redirecionar = true;
		
		} else if(subOpcao.equals("entrada")){
			proxPagina = "estoque/entrada.jsp";
			redirecionar = true;
		
		} else if(subOpcao.equals("fimEntrada")){
			aplicacao.processeEntrada();
			proxPagina = "estoque/estoque.jsp";
			redirecionar = true;
		
		} else if(subOpcao.equals("remover")){
			int codigo = Integer.parseInt(request.getParameter("cod"));
			aplicacao.getEntrada().removeProduto(codigo);
			proxPagina = "estoque/entrada.jsp";
			redirecionar = true;

		} else if(subOpcao.equals("adicionar")){
			EntradaProdutos entrada = aplicacao.getEntrada();
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			int qtde = Integer.parseInt(request.getParameter("qtde"));
			entrada.addProduto(codigo,qtde);
			proxPagina = "estoque/entrada.jsp";
			redirecionar = true;
		}

		//funcoes referentes à tranferencia de produtos
		else if(subOpcao.equals("transferencia")){
			proxPagina = "estoque/transferencia.jsp";
			redirecionar = true;
		
		} else if(subOpcao.equals("transAdicionar")){
			Transferencia transferencia = aplicacao.getTransferencia();
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			int qtde = Integer.parseInt(request.getParameter("qtde"));
			transferencia.addProduto(codigo,qtde);
			proxPagina = "estoque/transferencia.jsp";
			redirecionar = true;
		
		} else if(subOpcao.equals("transRemover")){
			
			int codigo = Integer.parseInt(request.getParameter("cod"));
			aplicacao.getTransferencia().removeProduto(codigo);
			proxPagina = "estoque/transferencia.jsp";
			redirecionar = true;
		
		} else if(subOpcao.equals("transFinaliza")){
			Transferencia trans = aplicacao.getTransferencia();
			trans.setDeLoja(request.getParameter("deLoja"));
			trans.setParaLoja(request.getParameter("paraLoja"));
			aplicacao.processaTransferencia();
			proxPagina = "estoque/estoque.jsp";
			redirecionar = true;
		}
	}

	private void processeLogin(HttpServletRequest request,
	HttpSession session)throws Exception{
	
		session.removeAttribute("usuarioAtual");
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
	
		if (aplicacao.validaLogin(nome,senha)) {
			session.setAttribute("usuarioAtual", nome);
			proxPagina = "principal.jsp";
			redirecionar = true;
		
		} else {
			proxPagina = "loginInvalido.jsp";
			redirecionar = true;
		}
	}

	private void processaOpcaoClientes(HttpServletRequest request,
	HttpSession session)throws Exception{

		String subOpcao = request.getParameter("subOpcao");
		
		if (subOpcao == null){
			proxPagina = "clientes/clientes.jsp";
			redirecionar = true;

		} else if(subOpcao.equals("listar")) {
			proxPagina = "clientes/listaClientes.jsp";
			redirecionar = true;
		
		} else if(subOpcao.equals("procura")) {
			String tipo = request.getParameter("tipo");
			String procura = request.getParameter("procura");
			proxPagina = "clientes/listaClientes.jsp?procura="+procura+"&tipo="+tipo;
			redirecionar = true;

		} else if(subOpcao.equals("editar")) {
			proxPagina = "clientes/editaCliente.jsp?codigo="+request.getParameter("select");
			redirecionar = true;
		
		} else if(subOpcao.equals("mostra")) {
			proxPagina = "clientes/mostraCliente.jsp?codigo="+request.getParameter("cod");
			redirecionar = true;
		
		} else if(subOpcao.equals("remover")) {
			aplicacao.removeCliente(Integer.parseInt(request.getParameter("select")));
			proxPagina = "clientes/listaClientes.jsp";
			redirecionar = true;
		
		} else if(subOpcao.equals("adicionar")) {
			proxPagina = "clientes/adicionarClientes.jsp";
			redirecionar = true;
		
		} else if(subOpcao.equals("alterar")){
			Cliente cliente = new Cliente();
			cliente.setCodigo(Integer.parseInt(request.getParameter("codigo")));
			cliente.setNome(request.getParameter("nome"));
			cliente.setTelefone(request.getParameter("telefone"));
			cliente.setCpf(request.getParameter("cpf"));
			cliente.setEndereco(request.getParameter("endereco"));
			cliente.setBairro(request.getParameter("bairro"));
				cliente.setCidade(request.getParameter("cidade"));
			cliente.setCep(request.getParameter("cep"));
			aplicacao.atualizaCliente(cliente);
			proxPagina = "clientes/listaClientes.jsp";
			redirecionar = true;
		
		} else if (subOpcao.equals("fimAdicionar")){
			Cliente cliente = new Cliente();
			cliente.setNome(request.getParameter("nome"));
			cliente.setTelefone(request.getParameter("telefone"));
			cliente.setCpf(request.getParameter("cpf"));
			cliente.setEndereco(request.getParameter("endereco"));
			cliente.setBairro(request.getParameter("bairro"));
			cliente.setCidade(request.getParameter("cidade"));
			cliente.setCep(request.getParameter("cep"));
			aplicacao.cadastraCliente(cliente);
			proxPagina = "clientes/listaClientes.jsp";
			redirecionar = true;
		}
	}
	
	private void processaOpcaoVenda(HttpServletRequest request, 
	HttpSession session)throws Exception{
		
		String subOpcao = request.getParameter("subOpcao");
		
		if (subOpcao == null){
			proxPagina = "venda/formVenda.jsp";
			redirecionar = true;
		
		} else if (subOpcao.equals("selProduto")){
			Venda venda = aplicacao.getVenda();
			ItemVenda item = new ItemVenda();
			int codigo = Integer.parseInt(request.getParameter("codigo") );
			item.setCodigoProduto(codigo);
			ResultSet rs = aplicacao.processeQuery("select * from bones where codigo = " + codigo);
			if(rs.next()){
				item.setNome(rs.getString(2));
				item.setValorProduto(rs.getFloat(4));
			}
			venda.setItem(item);
			proxPagina = "venda/formVenda2.jsp";
			redirecionar = true;
		
		} else if (subOpcao.equals("adicionar")){
			int quantidade = Integer.parseInt(request.getParameter("quantidade"));
			float valorServico = Float.parseFloat(request.getParameter("valorservico"));
			ItemVenda item = (ItemVenda)aplicacao.getVenda().getListaItens().lastElement();
			item.setQuantidade(quantidade);
			item.setValorServico(valorServico);
			proxPagina = "venda/verItens.jsp";
			redirecionar = true;
		
		} else if (subOpcao.equals("preFechar")){
			proxPagina = "venda/fechaVenda.jsp";
			redirecionar = true;
		
		} else if (subOpcao.equals("remover")){
			int codigo = Integer.parseInt(request.getParameter("item"));
			int qtde = Integer.parseInt(request.getParameter("qtde"));
			Venda venda = aplicacao.getVenda();
			venda.removeItem(codigo,qtde);
			proxPagina = "venda/verItens.jsp";
			redirecionar = true;

		} else if (subOpcao.equals("fechar")){
			Venda venda = aplicacao.getVenda();
			venda.setCodCliente(Integer.parseInt(request.getParameter("select")));
			String loja = (String) session.getAttribute("usuarioAtual");
			venda.setLoja(loja);
			aplicacao.processaVenda();
			proxPagina = "venda/formVenda.jsp";
			redirecionar = true;
		}
	}
	
	public void setAplicacao(Aplicacao aplicacao) {
		this.aplicacao = aplicacao;
	}
	
	public Aplicacao getAplicacao() {
		return (this.aplicacao);
	}
	
	public void setRedirecionar(boolean redirecionar) {
		this.redirecionar = redirecionar;
	}
	
	public boolean getRedirecionar() {
		return (this.redirecionar);
	}

	public void setProxPagina(String proxPagina) {
		this.proxPagina = proxPagina;
	}
	
	public String getProxPagina() {
		return (this.proxPagina);
	}
}