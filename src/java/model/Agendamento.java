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
public class Agendamento {

    private int idagendamento;
    private String data;
    private String horario;
    private float valor;
    private int status;
    private Cliente cliente;
    private Funcionario funcionario;
    private ArrayList<Servico> vinculado;
    private ArrayList<Servico> naoVinculado;

    public int getIdagendamento() {
        return idagendamento;
    }

    public void setIdagendamento(int idagendamento) {
        this.idagendamento = idagendamento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public ArrayList<Servico> getVinculado() {
        return vinculado;
    }

    public void setVinculado(ArrayList<Servico> vinculado) {
        this.vinculado = vinculado;
    }

    public ArrayList<Servico> getNaoVinculado() {
        return naoVinculado;
    }

    public void setNaoVinculado(ArrayList<Servico> naoVinculado) {
        this.naoVinculado = naoVinculado;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public boolean gravar() throws Exception {
        AgendamentoDAO DAO = new AgendamentoDAO();
        return DAO.gravar(this);
    }

    public ArrayList<Agendamento> getLista() throws Exception {
        AgendamentoDAO dao = new AgendamentoDAO();
        return dao.getLista();
    }

    public Agendamento getCarregaPorID(int idAgendamento) throws Exception {
        AgendamentoDAO DAO = new AgendamentoDAO();
        return DAO.getCarregaPorID(idAgendamento);
    }

    public boolean excluir() throws Exception {
        AgendamentoDAO DAO = new AgendamentoDAO();
        return DAO.excluir(this);
    }

    public ArrayList<Servico> servicosVinculadoPorAgendamento(int idagendamento) throws Exception {
        AgendamentoDAO DAO = new AgendamentoDAO();
        return DAO.servicosVinculadoPorAgendamento(idagendamento);
    }

    public ArrayList<Servico> servicosNaoVinculadosPorAgendamento(int idAgendamento) throws Exception {
        AgendamentoDAO DAO = new AgendamentoDAO();
        return DAO.servicosNaoVinculadosPorAgendamento(idAgendamento);
    }

    public boolean vincular(int idagendamento, int idservico) {
        AgendamentoDAO DAO = new AgendamentoDAO();
        return DAO.vincular(idagendamento, idservico);
    }

    public boolean desvincularUmServico(int idagendamento, int idservico) {
        AgendamentoDAO DAO = new AgendamentoDAO();
        return DAO.desvincularUmServico(idagendamento, idservico);
    }

    public boolean desvincularTodosOsServicos(int idagendamento) {
        AgendamentoDAO DAO = new AgendamentoDAO();
        return DAO.desvincularTodosOsServicos(idagendamento);
    }
     public int getQtdAgendamento() throws Exception{
        AgendamentoDAO DAO = new AgendamentoDAO();
        return DAO.getQtdAgendamento();
    }


    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    

}
