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
public class Menu {
    private int idmenu;
    private String menu;
    private String link;
    private String icone;
    private int exibir;

    public Menu() {
    }

    public Menu(int idmenu, String menu, String link, String icone, int exibir) {
        this.idmenu = idmenu;
        this.menu = menu;
        this.link = link;
        this.icone = icone;
        this.exibir = exibir;
    }

    public int getExibir() {
        return exibir;
    }

    public void setExibir(int exibir) {
        this.exibir = exibir;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public int getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
    
     public ArrayList<Menu> getLista() throws Exception{
        MenuDAO DAO = new MenuDAO();
        return DAO.getLista();
    }
    
    public boolean gravar() throws Exception{
        MenuDAO DAO = new MenuDAO();
        return DAO.gravar(this);
    }
    
    public Menu getCarregaPorID(int idmenu) throws Exception{
        MenuDAO DAO = new MenuDAO();
        return DAO.getCarregaPorID(idmenu);
    }
    
    public boolean excluir() throws Exception{
        MenuDAO DAO = new MenuDAO();
        return DAO.excluir(this);
    }
    
    
}
