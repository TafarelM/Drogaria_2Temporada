package br.com.drogaria.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.ItemVendaDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.ItemVenda;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.domain.Venda;
import br.com.drogaria.filter.VendaFilter;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean {

	private List<Produto> listaProdutos;
	private List<Produto> listaProdutosFiltrados;
	private List<ItemVenda> listaItensVenda;
	private Venda vendaCadastro;

	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;

	private VendaFilter filtro;
	private List<Venda> listaVendas;

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutosFiltrados(List<Produto> listaProdutosFiltrados) {
		this.listaProdutosFiltrados = listaProdutosFiltrados;
	}

	public List<Produto> getListaProdutosFiltrados() {
		return listaProdutosFiltrados;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public List<ItemVenda> getListaItensVenda() {
		if (listaItensVenda == null) {
			listaItensVenda = new ArrayList<>();
		}
		return listaItensVenda;
	}

	public void setListaItensVenda(List<ItemVenda> listaItensVenda) {
		this.listaItensVenda = listaItensVenda;
	}

	public Venda getVendaCadastro() {
		if (vendaCadastro == null) {
			vendaCadastro = new Venda();
			vendaCadastro.setValorTotal(new BigDecimal("0.00"));
			vendaCadastro.setQuantidade(0);
		}
		return vendaCadastro;
	}

	public void setVendaCadastro(Venda vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public VendaFilter getFiltro() {

		if (filtro == null) {
			filtro = new VendaFilter();
		}
		return filtro;
	}

	public void setFiltro(VendaFilter filtro) {
		this.filtro = filtro;
	}
	
	public List<Venda> getListaVendas() {
		return listaVendas;
	}
	
	public void setListaVendas(List<Venda> listaVendas) {
		this.listaVendas = listaVendas;
	}

	public void carregarProdutos() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listaProdutos = produtoDAO.listar();
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMgsErro("Erro ao tentar listar os Produtos. Detalhes: " + ex.getMessage());
		}
	}

	public void adicionar(Produto produto) {
		int posicaoEncontrada = -1;

		for (int pos = 0; pos < listaItensVenda.size() && posicaoEncontrada < 0; pos++) {
			ItemVenda itemTemp = listaItensVenda.get(pos);

			if (itemTemp.getProduto().equals(produto)) {
				posicaoEncontrada = pos;
			}
		}

		ItemVenda itemVenda = new ItemVenda();
		itemVenda.setProduto(produto);

		if (posicaoEncontrada < 0) {

			itemVenda.setQuantidade(1);
			itemVenda.setValor(produto.getPreco());

			listaItensVenda.add(itemVenda);
		} else {
			ItemVenda itemTemp = listaItensVenda.get(posicaoEncontrada);
			itemVenda.setQuantidade(itemTemp.getQuantidade() + 1);
			itemVenda.setValor(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
			listaItensVenda.set(posicaoEncontrada, itemVenda);
		}

		vendaCadastro.setValorTotal(vendaCadastro.getValorTotal().add(produto.getPreco()));
		vendaCadastro.setQuantidade(vendaCadastro.getQuantidade() + 1);
	}

	public void remover(ItemVenda itemVenda) {
		int posicaoEncontrada = -1;

		for (int pos = 0; pos < listaItensVenda.size() && posicaoEncontrada < 0; pos++) {
			ItemVenda itemTemp = listaItensVenda.get(pos);

			if (itemTemp.getProduto().equals(itemVenda.getProduto())) {
				posicaoEncontrada = pos;
			}
		}

		if (posicaoEncontrada > -1) {
			listaItensVenda.remove(posicaoEncontrada);
			vendaCadastro.setValorTotal(vendaCadastro.getValorTotal().subtract(itemVenda.getValor()));
			vendaCadastro.setQuantidade(vendaCadastro.getQuantidade() - itemVenda.getQuantidade());
		}
	}

	public void carregarDadosVenda() {
		vendaCadastro.setHorario(new Date());

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorId(autenticacaoBean.getFuncionarioLogado().getId());

		vendaCadastro.setFuncionario(funcionario);

		System.out.println("Funcionario :" + vendaCadastro.getFuncionario() + vendaCadastro.getHorario());
	}

	public void salvar() {
		try {
			VendaDAO vendaDAO = new VendaDAO();

			int idVenda = vendaDAO.salvar(vendaCadastro);
			Venda vendaFK = vendaDAO.buscarPorId(idVenda);

			for (ItemVenda itemVenda : listaItensVenda) {
				itemVenda.setVenda(vendaFK);

				ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
				itemVendaDAO.salvar(itemVenda);
			}

			vendaCadastro = new Venda();
			vendaCadastro.setValorTotal(new BigDecimal("0.00"));

			listaItensVenda = new ArrayList<>();

			FacesUtil.adicionarMgsInfo("Venda salva com sucesso.");
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMgsErro("Erro ao tentar salvar a venda. Detalhes: " + ex.getMessage());
		}
	}

	public void buscar() {		
		try {
			VendaDAO vendaDAO = new VendaDAO();
			listaVendas = vendaDAO.buscar(filtro);			
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMgsErro("Erro ao tentar Buscar uma venda. Detalhes: " + ex.getMessage());
		}
	}

}
