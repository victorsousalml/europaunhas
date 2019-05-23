/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author BNB - SOLUÇÕES
 */
public class ProdutoDAO extends DataBaseDAO{

    public ProdutoDAO()throws Exception{}
    
    public boolean gravar(Produto p){
        String sql;    
        try{
            this.conectar();
            if(p.getIdproduto()==0)
                sql ="INSERT INTO produto (nome, descricao, preco, quantidade) values (?,?,?,?);";
            else
                sql = "UPDATE produto SET nome=? , descricao=?, preco=? , quantidade=? WHERE idproduto=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, p.getNome());
            pstm.setString(2, p.getDescricao());
            pstm.setFloat(3, p.getPreco());
            pstm.setInt(4, p.getQuantidade());
            if(p.getIdproduto()>0)
                pstm.setInt(5, p.getIdproduto());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }    
    }
    
    public ArrayList<Produto> getLista(){
        ArrayList<Produto> listaProd = new ArrayList<>();
        String sql="SELECT * FROM produto";
        try{
            this.conectar();
            Statement stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()){
                Produto p = new Produto();
                p.setIdproduto(rs.getInt("idproduto"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setPreco(rs.getFloat("preco"));
                p.setQuantidade(rs.getInt("quantidade"));
                
                listaProd.add(p);
            }
           this.desconectar();
           return listaProd;
        }catch(Exception e){
            System.out.println(e);
            return listaProd;
        }
        
    }
    
    public Produto getCarregaPorID(int idProduto){
        Produto p = new Produto();
        String sql = "SELECT * FROM produto WHERE idproduto = ?";
        try{
            this.conectar();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idProduto);
            rs = pstm.executeQuery();
            while (rs.next()){
                p.setIdproduto(rs.getInt("idproduto"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setPreco(rs.getFloat("preco"));
                p.setQuantidade(rs.getInt("quantidade"));
            }
            this.desconectar();
            return p;
        }catch(Exception e){
            System.out.println(e);
            return p;
        }
    }
    
    public boolean excluir (Produto p){
        try{
            this.conectar();
            String sql = "DELETE FROM produto WHERE idproduto = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, p.getIdproduto());
            pstm.execute();
            this.desconectar();
            return true;
                    
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public int getQtdTodosProdutos() throws Exception{
        String sql="SELECT count(*) 'produto' FROM produto";
        int qtd = 0 ;
            this.conectar();
            Statement stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()){
               qtd = (rs.getInt("produto"));
            }
           this.desconectar();
           return qtd;
    }
   
}

