/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class Usuario {
   
    private int idusuario;
    private String login;
    private String senha;
    private int status;
    private Perfil perfil;

    public Usuario() {
    }

    public Usuario(int idusuario, String login, String senha, int status, Perfil perfil) {
        this.idusuario = idusuario;
        this.login = login;
        this.senha = senha;
        this.status = status;
        this.perfil = perfil;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public ArrayList<Usuario> getLista() throws Exception{
        UsuarioDAO DAO = new UsuarioDAO();
        return DAO.getLista();
    }
    
    public boolean gravar() throws Exception{
        UsuarioDAO DAO = new UsuarioDAO();
        return DAO.gravar(this);
    }
    
    public Usuario getCarregaPorID(int idusuario) throws Exception{
        UsuarioDAO DAO = new UsuarioDAO();
        return DAO.getCarregaPorID(idusuario);
    }
    public boolean getCarregaPorLogin(Usuario u) throws Exception{
        UsuarioDAO DAO = new UsuarioDAO();
        return DAO.getCarregaPorLogin(this);
    }
    public int getCarregaPorLogin(String Login) throws Exception{
        UsuarioDAO DAO = new UsuarioDAO();
        return DAO.getCarregaPorLogin(Login);
    }
    
    public boolean excluir() throws Exception{
        UsuarioDAO DAO = new UsuarioDAO();
        return DAO.excluir(this);
    }
    
    public Usuario getRecuperarUsuario(String login) throws Exception{
        UsuarioDAO DAO = new UsuarioDAO();
        return DAO.getRecuperarUsuario(login);
    }
    
}
