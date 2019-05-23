/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import model.Servico;

/**
 *
 * @author BNB - SOLUÇÕES
 */
public class GerenciarServico extends HttpServlet {

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
        int idservico = Integer.parseInt(request.getParameter("idservico"));
        Servico s = new Servico();
        try {
            if (acao.equals("alterar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    Servico novo = new Servico();
                    novo = s.getCarregaPorID(idservico);
                    if (novo.getIdservico() > 0) {
                        RequestDispatcher disp
                                = getServletContext().getRequestDispatcher("/form_servico.jsp");
                        request.setAttribute("servico", novo);
                        disp.forward(request, response);
                    } else {
                        mensagem = "Servico não encontrado";
                    }
                } else {
                    mensagem = "Acesso Negado";
                }
            }
            if (acao.equals("excluir")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    if (idservico > 0) {
                        s.setIdservico(idservico);
                        if (s.excluir()) {
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
        out.println("location.href='listar_servico.jsp';");
        out.println("</script>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String idservico = request.getParameter("idservico");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String preco = request.getParameter("preco");
        String link = request.getParameter("link_img");

        String mensagem = "";
        Servico s = new Servico();
        if (!idservico.isEmpty()) {
            s.setIdservico(Integer.parseInt(idservico));
        }
        s.setNome_servico(nome);
        s.setDescricao(descricao);
        s.setPreco(Float.parseFloat(preco));
        s.setLink_img(link);

        try {
            if (nome.equals("") || descricao.equals("") || preco.isEmpty() || link.equals("")) {
                mensagem = "Campos obrigatorios devem ser preenchidos";
            } else {
                if (s.gravar()) {
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
        out.println("location.href='listar_servico.jsp';");
        out.println("</script>");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
