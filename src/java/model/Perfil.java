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
public class Perfil {
    private int idperfil;
    private String perfil;
    private ArrayList<Menu> menus;
    private ArrayList<Menu> naoMenus;

    public Perfil() {
    }

    public Perfil(int idperfil, String perfil) {
        this.idperfil = idperfil;
        this.perfil = perfil;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    public ArrayList<Menu> getNaoMenus() {
        return naoMenus;
    }

    public void setNaoMenus(ArrayList<Menu> naoMenus) {
        this.naoMenus = naoMenus;
    }

    public int getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(int idperfil) {
        this.idperfil = idperfil;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return getPerfil();
    }
    
    public ArrayList<Perfil> getLista() throws Exception{
        PerfilDAO DAO = new PerfilDAO();
        return DAO.getLista();
    }
    
    public boolean gravar() throws Exception{
        PerfilDAO DAO = new PerfilDAO();
        return DAO.gravar(this);
    }
    
    public Perfil getCarregaPorID(int idperfil) throws Exception{
        PerfilDAO DAO = new PerfilDAO();
        return DAO.getCarregaPorID(idperfil);
    }
    
    public boolean excluir() throws Exception{
        PerfilDAO DAO = new PerfilDAO();
        return DAO.excluir(this);
    }
    
    public boolean vincular(int idmenu, int idperfil) throws Exception{
        PerfilDAO DAO = new PerfilDAO();
        return DAO.vincular(idmenu,idperfil);
    }
    public boolean desvincular(int idmenu, int idperfil) throws Exception{
        PerfilDAO DAO = new PerfilDAO();
        return DAO.desvincular(idmenu,idperfil);
    }
    
    
}
