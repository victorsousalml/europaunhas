/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author BNB - SOLUÇÕES
 */
public class ClienteDAO extends DataBaseDAO{
     public ClienteDAO() {
    }
    
    /* public ArrayList<Cliente> getLista() throws Exception{
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        String sql = "SELECT c.*, p.perfil FROM cliente u "
                + " INNER JOIN perfil p ON "
                + " p.idperfil = c.idperfil ";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Cliente u = new Cliente();
            c.setIdcliente(rs.getInt("c.idcliente"));
            c.setNome(rs.getString("c.nome"));
            c.setLogin(rs.getString("c.login"));
            c.setSenha(rs.getString("c.senha"));
            c.setStatus(rs.getInt("c.status"));
            Perfil p = new Perfil();
            p.setIdperfil(rs.getInt("c.idperfil"));
            p.setPerfil(rs.getString("p.perfil"));
            c.setPerfil(p);
            lista.add(u);
        }
        this.desconectar();
        return lista;
    }*/
    public ArrayList<Cliente> getLista() throws Exception{
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        String sql = "SELECT * FROM cliente ";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Cliente c = new Cliente();
            c.setIdcliente(rs.getInt("idcliente"));
            c.setNome(rs.getString("nome"));
            c.setDataNasc(rs.getString("dataNasc"));
            c.setCpf(rs.getString("cpf"));
            c.setSexo(rs.getString("sexo"));
            c.setEmail(rs.getString("email"));
            c.setEndereco(rs.getString("endereco"));
            c.setTelefone(rs.getString("telefone"));
            Usuario u = new Usuario();
            c.setUsuario(u.getCarregaPorID(rs.getInt("idusuario")));
            lista.add(c);
        }
        this.desconectar();
        return lista;
    }
    
    public boolean gravar(Cliente c, int idusuario){
        try{
            this.conectar();
            String sql;
            if(c.getIdcliente()==0)
                    sql="INSERT INTO cliente (nome, dataNasc, cpf, sexo, email, endereco, telefone, idusuario) "
                    + " VALUES (?,?,?,?,?,?,?,?)";
            else
                  sql = "UPDATE cliente SET nome=?, dataNasc=?, cpf=?, sexo=?, email=?, endereco=?, telefone=?, idusuario=? WHERE idcliente=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, c.getNome());
            pstm.setDate(2,Date.valueOf(c.getDataNasc()));
            pstm.setString(3, c.getCpf());
            pstm.setString(4, c.getSexo());
            pstm.setString(5, c.getEmail());
            pstm.setString(6, c.getEndereco());
            pstm.setString(7, c.getTelefone());
            pstm.setInt(8,idusuario);
            if(c.getIdcliente()>0)
                pstm.setInt(9,c.getIdcliente());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public Cliente getCarregaPorID(int idcliente) throws Exception{
        Cliente c = new Cliente();
        String sql = "SELECT c.*, u.login, u.status FROM cliente c"
                + " INNER JOIN usuario u ON "
                + " u.idusuario = c.idusuario WHERE c.idcliente=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idcliente);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            c.setIdcliente(rs.getInt("c.idcliente"));
            c.setNome(rs.getString("c.nome"));
            c.setDataNasc(rs.getString("c.dataNasc"));
            c.setCpf(rs.getString("c.cpf"));
            c.setSexo(rs.getString("c.sexo"));
            c.setEmail(rs.getString("c.email"));
            c.setEndereco(rs.getString("c.endereco"));
            c.setTelefone(rs.getString("c.telefone"));
            Usuario u = new Usuario();
            c.setUsuario(u.getCarregaPorID(rs.getInt("c.idusuario")));
        }
        this.desconectar();
        return c;
    
    }
    
    public Cliente getCarregaPorUsuario(int idUsuario) throws Exception{
        Cliente c = new Cliente();
        String sql = "select * FROM cliente WHERE idUsuario = ?";
        this.conectar();
         pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idUsuario);
         rs = pstm.executeQuery();
        if(rs.wasNull()){
            c = null;
        }else{
            c.setIdcliente(rs.getInt("idcliente"));
            c.setNome(rs.getString("nome"));
            c.setDataNasc(rs.getString("dataNasc"));
            c.setCpf(rs.getString("cpf"));
            c.setSexo(rs.getString("sexo"));
            c.setEmail(rs.getString("email"));
            c.setEndereco(rs.getString("endereco"));
            c.setTelefone(rs.getString("telefone"));
        }
        this.desconectar();
        return c;
    
    }
    
    public boolean excluir(Cliente c){
        try{
            this.conectar();
            //deletar o cliente
            //String sql = "DELETE FROM cliente WHERE idcliente=?";
            
            String sql = "UPDATE usuario u SET u.status=2 WHERE u.idusuario = (SELECT idusuario FROM cliente WHERE idusuario = ?) ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,c.getUsuario().getIdusuario());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public int getQtdCliente() throws Exception{
        String sql = "SELECT count(*) 'qtd' FROM cliente ";
        int qtd = 0;
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        if(rs.next()){
            qtd = rs.getInt("qtd");
        }
        this.desconectar();
        return qtd;
    }
    public ArrayList<Cliente> getUltimos() throws Exception{
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        String sql = "SELECT * FROM cliente order by idcliente DESC LIMIT 4 ";
        this.conectar();
        Statement stm = conn.createStatement();
         rs = stm.executeQuery(sql);
        while(rs.next()){
            Cliente c = new Cliente();
            c.setIdcliente(rs.getInt("idcliente"));
            c.setNome(rs.getString("nome"));
            c.setEmail(rs.getString("email"));
            Usuario u = new Usuario();
            c.setUsuario(u.getCarregaPorID(rs.getInt("idusuario")));
            lista.add(c);
        }
        this.desconectar();
        return lista;
    }
}
