package model;

import java.util.ArrayList;

/**
 *
 * @author Gabriel Marques
 */
public class ServicoProdutoDAO extends DataBaseDAO{
     public boolean vincularProduto(int idservico,int idproduto, int quantidade){
        try{
            String sql = "INSERT INTO servico_produto (idproduto, idservico, descontarqtd) VALUES (?,?,?)";
            this.conectar();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idproduto);
            pstm.setInt(2, idservico);
            pstm.setInt(3, quantidade);
            pstm.execute();
            this.desconectar();
            return true;
            
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
     
    public boolean desvincularProduto(int idservico, int idproduto){
        try{
            String sql = "DELETE FROM servico_produto WHERE idservico=? AND idproduto=?";
            this.conectar();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idservico);
            pstm.setInt(2, idproduto);
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    /*Método getCarregaVinculadoPorServico!
    
    Responsável por criar Objeto do tipo Serivo_Produto com os valores 
    sendo trazidos pelo id do serviço inserido.
    
    O método retorna um objeto do tipo Serviço Produto carregado com os valores
    de: 
        Produtos vinculados ao serviço;
        Produtos não vinculados ao serviço;
        Dados do serviço pesquisado.
    */
     public ServicoProduto getCarregaVinculadoPorServico(int idServico){
         ServicoProduto s = new ServicoProduto();
         try {
             Servico serv = new Servico();
             s.setProdutosVinculados(produtosVinculadosPorServicos(idServico));
             s.setProdutosNaoVinculados(produtosNaoVinculadosPorPerfil(idServico));
             s.setServico(serv.getCarregaPorID(idServico));
         } catch (Exception e) {
             System.out.println(e);
        }
         return s;
    }
     
     public ArrayList<ServicoProduto> produtosVinculadosPorServicos(int idservico) 
            throws Exception{
        ArrayList<ServicoProduto> lista = new ArrayList<>();
        String sql = "SELECT p.*, ip.descontarqtd FROM servico_produto as ip, produto as p WHERE ip.idproduto = p.idproduto AND ip.idservico= ?";
        this.conectar();
        pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idservico);
        rs = pstm.executeQuery();
        while(rs.next()){
            ServicoProduto sp = new ServicoProduto();
                Produto p = new Produto();
                p.setIdproduto(rs.getInt("p.idproduto"));
                p.setNome(rs.getString("p.nome"));
                p.setDescricao(rs.getString("p.descricao"));
                p.setPreco(rs.getFloat("p.preco"));
                p.setQuantidade(rs.getInt("p.quantidade"));
                sp.setDescontarqtd(rs.getInt("ip.descontarqtd"));
                sp.setProduto(p);
            lista.add(sp);
        }
        this.desconectar();
        return lista;
    
    }
     
    public ArrayList<Produto> produtosNaoVinculadosPorPerfil(int idservico) 
            throws Exception{
    
        ArrayList<Produto> lista = new ArrayList<>();
        String sql = " SELECT idproduto, nome FROM produto WHERE idproduto NOT IN (select idproduto from servico_produto where idservico=?)";
        this.conectar();
        pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idservico);
        rs = pstm.executeQuery();
        while(rs.next()){
            Produto p = new Produto();
            p.setIdproduto(rs.getInt("idproduto"));
            p.setNome(rs.getString("nome"));
            lista.add(p);
        }
        this.desconectar();
        return lista;
    
    
    }
    
    /*public boolean atualizarQuantidadeProdutos(int quantidade, int idservico, int idproduto){
        try{
            String sql = "UPDATE item_produto SET quantidade=? WHERE idservico=? AND idproduto=?";
            this.conectar();
            pstm =  conn.prepareStatement(sql);
            pstm.setInt(1, quantidade);
            pstm.setInt(2, idservico);
            pstm.setInt(3, idproduto);
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }*/
    
     /*public ArrayList<Produto> produtosVinculadosPorServicos(int idservico) 
            throws Exception{
    
        ArrayList<Produto> lista = new ArrayList<Produto>();
        String sql = "SELECT p.* FROM servico_produto as ip, produto as p WHERE ip.idproduto = p.idproduto AND ip.idservico= ?";
        this.conectar();
        pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idservico);
        rs = pstm.executeQuery();
        while(rs.next()){
            Produto p = new Produto();
            p.setIdproduto(rs.getInt("p.idproduto"));
            p.setNome(rs.getString("p.nome"));
            p.setDescricao(rs.getString("p.descricao"));
            p.setPreco(rs.getFloat("p.preco"));
            p.setQuantidade(rs.getInt("p.quantidade"));
            lista.add(p);
            
        }
        this.desconectar();
        return lista;
    
    
    }*/

}