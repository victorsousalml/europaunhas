package model;

import java.util.ArrayList;

/**
 *
 * @author Gabriel Marques
 */
public class ServicoProduto {
    private Produto produto;
    private Servico servico;
    private int descontarqtd;
    private ArrayList<ServicoProduto> produtosVinculados;
    private ArrayList<Produto> produtosNaoVinculados;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public int getDescontarqtd() {
        return descontarqtd;
    }

    public void setDescontarqtd(int descontarqtd) {
        this.descontarqtd = descontarqtd;
    }
    
    public ArrayList<ServicoProduto> getProdutosVinculados() {
        return produtosVinculados;
    }

    public void setProdutosVinculados(ArrayList<ServicoProduto> produtos) {
        this.produtosVinculados = produtos;
    }

    public ArrayList<Produto> getProdutosNaoVinculados() {
        return produtosNaoVinculados;
    }

    public void setProdutosNaoVinculados(ArrayList<Produto> produtosNaoVinculados) {
        this.produtosNaoVinculados = produtosNaoVinculados;
    }
    
    public boolean vincularProduto(int idservico,int idproduto,int quantidade){
        ServicoProdutoDAO dao = new ServicoProdutoDAO();
        return dao.vincularProduto(idservico, idproduto, quantidade);
    }
    
    public boolean desvincularProduto(int idservico, int idproduto){
        ServicoProdutoDAO dao = new ServicoProdutoDAO();
        return dao.desvincularProduto(idservico, idproduto);
    }
    
    public ServicoProduto getCarregaVinculadoPorServico(int idServico){
        ServicoProdutoDAO dao = new ServicoProdutoDAO();
        return dao.getCarregaVinculadoPorServico(idServico);
    }
    
   /* public boolean atualizarQuantidadeProdutos(int quantidade, int idservico, int idproduto){
       ServicoDAO dao = new ServicoDAO();
       return dao.atualizarQuantidadeProdutos(quantidade, idservico, idproduto);
    }
    */
}
