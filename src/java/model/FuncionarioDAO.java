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
public class FuncionarioDAO extends DataBaseDAO {

    public FuncionarioDAO() {
    }
    
    /* public ArrayList<Funcionario> getLista() throws Exception{
        ArrayList<Funcionario> lista = new ArrayList<Funcionario>();
        String sql = "SELECT f.*, p.perfil FROM funcionario u "
                + " INNER JOIN perfil p ON "
                + " p.idperfil = f.idperfil ";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Funcionario u = new Funcionario();
            f.setIdfuncionario(rs.getInt("f.idfuncionario"));
            f.setNome(rs.getString("f.nome"));
            f.setLogin(rs.getString("f.login"));
            f.setSenha(rs.getString("f.senha"));
            f.setStatus(rs.getInt("f.status"));
            Perfil p = new Perfil();
            p.setIdperfil(rs.getInt("f.idperfil"));
            p.setPerfil(rs.getString("p.perfil"));
            f.setPerfil(p);
            lista.add(u);
        }
        this.desconectar();
        return lista;
    }*/
    public ArrayList<Funcionario> getLista() throws Exception{
        ArrayList<Funcionario> lista = new ArrayList<Funcionario>();
        String sql = "SELECT * FROM funcionario ";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Funcionario f = new Funcionario();
            f.setIdfuncionario(rs.getInt("idfuncionario"));
            f.setNome(rs.getString("nome"));
            f.setDataNasc(rs.getString("dataNasc"));
            f.setCpf(rs.getString("cpf"));
            f.setSexo(rs.getString("sexo"));
            f.setEmail(rs.getString("email"));
            f.setEndereco(rs.getString("endereco"));
            f.setTelefone(rs.getString("telefone"));
            Usuario u = new Usuario();
            f.setUsuario(u.getCarregaPorID(rs.getInt("idusuario")));
            lista.add(f);
        }
        this.desconectar();
        return lista;
    }
    
    public boolean gravar(Funcionario f, int idusuario){
        try{
            this.conectar();
            String sql;
            if(f.getIdfuncionario()==0)
                    sql="INSERT INTO funcionario (nome, dataNasc, cpf, sexo, email, endereco, telefone, idusuario) "
                    + " VALUES (?,?,?,?,?,?,?,?)";
            else
                  sql = "UPDATE funcionario SET nome=?, dataNasc=?, cpf=?, sexo=?, email=?, endereco=?, telefone=?, idusuario=? WHERE idfuncionario=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, f.getNome());
            pstm.setDate(2,Date.valueOf(f.getDataNasc()));
            pstm.setString(3, f.getCpf());
            pstm.setString(4, f.getSexo());
            pstm.setString(5, f.getEmail());
            pstm.setString(6, f.getEndereco());
            pstm.setString(7, f.getTelefone());
            pstm.setInt(8,idusuario);
            if(f.getIdfuncionario()>0)
                pstm.setInt(9,f.getIdfuncionario());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public Funcionario getCarregaPorID(int idfuncionario) throws Exception{
        Funcionario f = new Funcionario();
        String sql = "SELECT f.*, u.login, u.status FROM funcionario f"
                + " INNER JOIN usuario u ON "
                + " u.idusuario = f.idusuario WHERE f.idfuncionario=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idfuncionario);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            f.setIdfuncionario(rs.getInt("f.idfuncionario"));
            f.setNome(rs.getString("f.nome"));
            f.setDataNasc(rs.getString("f.dataNasc"));
            f.setCpf(rs.getString("f.cpf"));
            f.setSexo(rs.getString("f.sexo"));
            f.setEmail(rs.getString("f.email"));
            f.setEndereco(rs.getString("f.endereco"));
            f.setTelefone(rs.getString("f.telefone"));
            Usuario u = new Usuario();
            f.setUsuario(u.getCarregaPorID(rs.getInt("f.idusuario")));
        }
        this.desconectar();
        return f;
    
    }
    public int getQtdFuncionario() throws Exception{
        String sql = "SELECT count(*) 'qtd' FROM funcionario ";
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
    
        public Funcionario getCarregaPorUsuario(int idUsuario) throws Exception{
        Funcionario f = new Funcionario();
        String sql = "select * FROM funcionario WHERE idusuario = ?";
        this.conectar();
        pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idUsuario);
        rs = pstm.executeQuery();
        if(rs.wasNull()){
            f = null;
        }else if(rs.next()){
            f.setIdfuncionario(rs.getInt("idfuncionario"));
            f.setNome(rs.getString("nome"));
            f.setDataNasc(rs.getString("dataNasc"));
            f.setCpf(rs.getString("cpf"));
            f.setSexo(rs.getString("sexo"));
            f.setEmail(rs.getString("email"));
            f.setEndereco(rs.getString("endereco"));
            f.setTelefone(rs.getString("telefone"));
        }
        this.desconectar();
        return f;
    
    }
    public boolean excluir(Funcionario f){
        try{
            this.conectar();
            //deletar o funcionario
            //String sql = "DELETE FROM funcionario WHERE idfuncionario=?";
            
            String sql = "UPDATE usuario u SET u.status=2 WHERE u.idusuario = (SELECT idusuario FROM funcionario WHERE idusuario = ?) ";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,f.getUsuario().getIdusuario());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
}
