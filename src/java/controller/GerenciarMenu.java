/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Menu;

/**
 *
 * @author Administrador
 */
public class GerenciarMenu extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String mensagem = "";
        int idmenu = Integer.parseInt(request.getParameter("idmenu"));
        Menu m = new Menu();
        try {
            if (acao.equals("alterar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    Menu novo = new Menu();
                    novo = m.getCarregaPorID(idmenu);
                    if (novo.getIdmenu() > 0) {
                        RequestDispatcher disp
                                = getServletContext().getRequestDispatcher("/form_menu.jsp");
                        request.setAttribute("menu", novo);
                        disp.forward(request, response);
                    } else {
                        mensagem = "Menu não encontrado";
                    }
                } else {
                    mensagem = "Acesso Negado";
                }
            }
            if (acao.equals("excluir")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    if (idmenu > 0) {
                        m.setIdmenu(idmenu);
                        if (m.excluir()) {
                            mensagem = "Excluido com sucesso!";
                        } else {
                            mensagem = "Erro ao excluir";
                        }
                    }
                } else {
                    mensagem = "Acesso Negado";
                }
            }
        } catch (Exception e) {
            out.println(e);
            mensagem = "Erro ao acessar o banco";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='listar_menu.jsp';");
        out.println("</script>");

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String idmenu = request.getParameter("idmenu");
        String menu = request.getParameter("menu");
        String link = request.getParameter("link");
        String icone = request.getParameter("icone");
        String exibir = request.getParameter("exibir");

        String mensagem = "";
        Menu m = new Menu();
        if (!idmenu.isEmpty()) {
            m.setIdmenu(Integer.parseInt(idmenu));
        }
        m.setMenu(menu);
        m.setLink(link);
        m.setIcone(icone);
        m.setExibir(Integer.parseInt(exibir));

        try {
            if (menu.equals("") || link.equals("") || exibir.isEmpty()) {
                mensagem = "Campos obrigatorios devem ser preenchidos";
            } else {
                if (m.gravar()) {
                    mensagem = "Gravado com sucesso";
                } else {
                    mensagem = "Erro ao gravar";
                }
            }
        } catch (Exception e) {
            out.println(e);
            mensagem = "Erro ao acessar o banco";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='listar_menu.jsp';");
        out.println("</script>");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
