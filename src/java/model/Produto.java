/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author BNB - SOLUÇÕES
 */
public class Produto {
    private int idproduto;
    private String nome;
    private String descricao;
    private float preco;
    private int quantidade;

    public Produto(String nome, String descricao, float preco, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Produto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }
    
    public ArrayList<Produto> getLista() throws Exception{
        ProdutoDAO dao = new ProdutoDAO();
        return dao.getLista();
    }
    
    public boolean gravar() throws Exception{
         ProdutoDAO dao = new ProdutoDAO();
         return dao.gravar(this);
    }
    
    public Produto getCarregaPorID(int idproduto) throws Exception{
        ProdutoDAO dao = new ProdutoDAO();
        return dao.getCarregaPorID(idproduto);
    }
    
    public boolean excluir() throws Exception{
        ProdutoDAO dao = new ProdutoDAO();
        return dao.excluir(this);
    }
    
    public int getQtdTodosProdutos() throws Exception{
        ProdutoDAO DAO = new ProdutoDAO();
        return DAO.getQtdTodosProdutos();
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    
    
    
}
