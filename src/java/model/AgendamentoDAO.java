/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author BNB - SOLUÇÕES
 */
public class AgendamentoDAO extends DataBaseDAO {
    /* public ArrayList<Agendamento> getLista() throws Exception{
        ArrayList<Agendamento> lista = new ArrayList<Agendamento>();
        String sql = "SELECT a.*, p.perfil FROM agendamento u "
                + " INNER JOIN perfil p ON "
                + " p.idperfil = a.idperfil ";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Agendamento u = new Agendamento();
            a.setIdagendamento(rs.getInt("a.idagendamento"));
            a.setNome(rs.getString("a.nome"));
            a.setLogin(rs.getString("a.login"));
            a.setSenha(rs.getString("a.senha"));
            a.setStatus(rs.getInt("a.status"));
            Perfil p = new Perfil();
            p.setIdperfil(rs.getInt("a.idperfil"));
            p.setPerfil(rs.getString("p.perfil"));
            a.setPerfil(p);
            lista.add(u);
        }
        this.desconectar();
        return lista;
    }*/
    public ArrayList<Agendamento> getLista() throws Exception {
        ArrayList<Agendamento> lista = new ArrayList<Agendamento>();
        String sql = "SELECT * FROM agendamento ";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            Agendamento a = new Agendamento();
            a.setIdagendamento(rs.getInt("idagendamento"));
            a.setData(rs.getString("data"));
            a.setHorario(rs.getString("horario"));
            a.setValor(rs.getFloat("valor"));
            a.setStatus(rs.getInt("status"));
            Cliente c = new Cliente();
            Funcionario f = new Funcionario();
            a.setFuncionario(f.getCarregaPorID(rs.getInt("idfuncionario")));
            a.setCliente(c.getCarregaPorID(rs.getInt("idcliente")));
            a.setVinculado(servicosVinculadoPorAgendamento(a.getIdagendamento()));
            a.setNaoVinculado(servicosNaoVinculadosPorAgendamento(a.getIdagendamento()));
            
            lista.add(a);
        }
        this.desconectar();
        return lista;
    }

    public boolean gravar(Agendamento a) {
        try {
            this.conectar();
            String sql;
            if (a.getIdagendamento() == 0) {
                sql = "INSERT INTO agendamento (data,horario, valor, status, idfuncionario, idcliente)  VALUES (?,?,?,?,?,?)";
            } else {
                sql = "UPDATE agendamento SET  data=?,horario=?, valor=?, status=?, idfuncionario=?, idcliente=? WHERE idagendamento=?";
            }
            pstm = conn.prepareStatement(sql);
            pstm.setDate(1, Date.valueOf(a.getData()));
            pstm.setString(2, a.getHorario());
            pstm.setFloat(3, a.getValor());
            pstm.setInt(4, a.getStatus());
            pstm.setInt(5, a.getFuncionario().getIdfuncionario());
            pstm.setInt(6, a.getCliente().getIdcliente());
            if (a.getIdagendamento() > 0) {
                pstm.setInt(7, a.getIdagendamento());
            }
            pstm.execute();
        
            this.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Agendamento getCarregaPorID(int idagendamento) throws Exception {
        Agendamento a = new Agendamento();
        String sql = "SELECT * FROM agendamento WHERE idagendamento= ?";

        this.conectar();
        pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idagendamento);
        rs = pstm.executeQuery();
        if (rs.next()) {
            a.setIdagendamento(rs.getInt("idagendamento"));
            a.setData(rs.getString("data"));
            a.setHorario(rs.getString("horario"));
            a.setValor(rs.getFloat("valor"));
            a.setStatus(rs.getInt("status"));
            Cliente c = new Cliente();
            Funcionario f = new Funcionario();
            a.setFuncionario(f.getCarregaPorID(rs.getInt("idfuncionario")));
            a.setCliente(c.getCarregaPorID(rs.getInt("idcliente")));
            a.setVinculado(servicosVinculadoPorAgendamento(idagendamento));
            a.setNaoVinculado(servicosNaoVinculadosPorAgendamento(idagendamento));
        }
        this.desconectar();
        return a;

    }
    
    public int getQtdAgendamento() throws Exception{
        String sql = "SELECT count(*) 'qtd' FROM agendamento ";
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

    public boolean excluir(Agendamento a) {
        try {
            
            //deletar o agendamento
            desvincularTodosOsServicos(a.getIdagendamento());
            this.conectar();
            String sql = "DELETE FROM agendamento WHERE idagendamento=?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, a.getIdagendamento());
            pstm.execute();
            this.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    //AGENDAMENTO SERVIÇO-------------------------------------------------------
    public ArrayList<Servico> servicosVinculadoPorAgendamento(int idagendamento) throws Exception {
        ArrayList<Servico> lista = new ArrayList<>();
        String sql = "SELECT s.* FROM agendamento_servico as sp, servico as s WHERE sp.idservico = s.idservico AND sp.idagendamento=?";
        this.conectar();
        pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idagendamento);
        rs = pstm.executeQuery();
        while (rs.next()) {
            Servico s = new Servico();
            s.setIdservico(rs.getInt("s.idservico"));
            s.setNome_servico(rs.getString("s.nome_servico"));
            s.setDescricao(rs.getString("s.descricao"));
            s.setPreco(rs.getFloat("s.preco"));
            s.setLink_img(rs.getString("s.link_img"));
            lista.add(s);
        }
        this.desconectar();
        return lista;

    }

    public ArrayList<Servico> servicosNaoVinculadosPorAgendamento(int idAgendamento)
            throws Exception {

        ArrayList<Servico> lista = new ArrayList<>();
        String sql = " SELECT * FROM servico WHERE idservico NOT IN (SELECT idservico FROM agendamento_servico WHERE idagendamento = ?)";
        this.conectar();
        pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idAgendamento);
        rs = pstm.executeQuery();
        while (rs.next()) {
            Servico s = new Servico();
            s.setIdservico(rs.getInt("idservico"));
            s.setNome_servico(rs.getString("nome_servico"));
            s.setDescricao(rs.getString("descricao"));
            s.setPreco(rs.getFloat("preco"));
            s.setLink_img(rs.getString("link_img"));
            lista.add(s);
        }
        this.desconectar();
        return lista;

    }

    public boolean vincular(int idagendamento, int idservico) {

        try {
            String sql = "INSERT INTO agendamento_servico (idagendamento, idservico) "
                    + "VALUES (?,?)";
            this.conectar();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idagendamento);
            pstm.setInt(2, idservico);
            pstm.execute();
            this.desconectar();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    public boolean desvincularUmServico(int idagendamento, int idservico) {

        try {
            String sql = "DELETE FROM agendamento_servico WHERE "
                    + " idagendamento=? AND idservico=? ";
            this.conectar();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idagendamento);
            pstm.setInt(2, idservico);
            pstm.execute();
            this.desconectar();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    public boolean desvincularTodosOsServicos(int idagendamento) {
        try {
            String sql = "DELETE FROM agendamento_servico WHERE "
                    + " idagendamento=? ";
            this.conectar();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idagendamento);
            pstm.execute();
            this.desconectar();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }
    
   

}
