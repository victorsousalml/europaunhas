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
public class Funcionario {
    private int idfuncionario;
    private String nome;
    private String dataNasc;
    private String cpf;
    private String sexo;
    private String email;
    private String endereco;
    private String telefone;
    private Usuario usuario;

    public Funcionario() {
    }

    public Funcionario(int idfuncionario, String nome, String dataNasc, String cpf, String sexo, String email, String endereco, String telefone, Usuario usuario) {
        this.idfuncionario = idfuncionario;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.cpf = cpf;
        this.sexo = sexo;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.usuario = usuario;
    }

   

    
    public int getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(int idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
     public ArrayList<Funcionario> getLista() throws Exception{
        FuncionarioDAO DAO = new FuncionarioDAO();
        return DAO.getLista();
    }
    
    public boolean gravar(Funcionario f, int u) throws Exception{
        FuncionarioDAO DAO = new FuncionarioDAO();
        return DAO.gravar(f, u);
    }
    
    public Funcionario getCarregaPorID(int idfuncionario) throws Exception{
        FuncionarioDAO DAO = new FuncionarioDAO();
        return DAO.getCarregaPorID(idfuncionario);
    }
    
   public Funcionario getCarregaPorUsuario(int idUsuario) throws Exception{
        FuncionarioDAO DAO = new FuncionarioDAO();
        return DAO.getCarregaPorUsuario(idUsuario);
    }
    
    
    public boolean excluir() throws Exception{
        FuncionarioDAO DAO = new FuncionarioDAO();
        return DAO.excluir(this);
    }
    
    public int getQtdFuncionario() throws Exception{
        FuncionarioDAO DAO = new FuncionarioDAO();
        return DAO.getQtdFuncionario();
    }

    

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
}
