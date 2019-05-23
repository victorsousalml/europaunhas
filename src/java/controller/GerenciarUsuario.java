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
import model.Perfil;
import model.Usuario;

/**
 *
 * @author Administrador
 */
public class GerenciarUsuario extends HttpServlet {

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
        int idusuario = Integer.parseInt(request.getParameter("idusuario"));
        Usuario u = new Usuario();
        try {
            if (acao.equals("alterar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    Usuario novo = new Usuario();
                    novo = u.getCarregaPorID(idusuario);
                    if (novo.getIdusuario() > 0) {
                        RequestDispatcher disp
                                = getServletContext().getRequestDispatcher("/form_usuario.jsp");
                        request.setAttribute("usuario", novo);
                        disp.forward(request, response);
                    } else {
                        mensagem = "Usuario não encontrado";
                    }
                } else {
                    mensagem = "Acesso Negado";
                }
            }
            if (acao.equals("excluir")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    if (idusuario != 0) {
                        u.setIdusuario(idusuario);
                        if (u.excluir()) {
                            mensagem = "Excluido com sucesso";
                        } else {
                            mensagem = "Erro ao excluir";
                        }
                    }
                } else {
                    mensagem = "Acesso negado";
                }
            }

        } catch (Exception e) {
            out.println(e);
            mensagem = "Erro ao acessar o banco";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='listar_usuario.jsp';");
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
        String idusuario = request.getParameter("idusuario");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String status = request.getParameter("status");
        String idperfil = request.getParameter("idperfil");

        String mensagem = "";
        Usuario u = new Usuario();
        if (!idusuario.isEmpty()) {
            u.setIdusuario(Integer.parseInt(idusuario));
        }
        u.setLogin(login);
        u.setSenha(senha);
        u.setStatus(Integer.parseInt(status));
        Perfil p = new Perfil();
        p.setIdperfil(Integer.parseInt(idperfil));
        u.setPerfil(p);

        try {
            if (login.equals("") || idperfil.isEmpty() || senha.equals("")) {
                mensagem = "Campos obrigatorios devem ser preenchidos";
            } else {
                if (u.gravar()) {
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
        out.println("location.href='listar_usuario.jsp';");
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
