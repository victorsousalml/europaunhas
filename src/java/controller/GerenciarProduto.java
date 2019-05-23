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
import model.Produto;

/**
 *
 * @author BNB - SOLUÇÕES
 */
public class GerenciarProduto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String mensagem = "";
        int idproduto = Integer.parseInt(request.getParameter("idproduto"));
        Produto p = new Produto();
        try {
            if (acao.equals("alterar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    Produto novo = new Produto();
                    novo = p.getCarregaPorID(idproduto);
                    if (novo.getIdproduto() > 0) {
                        RequestDispatcher disp
                                = getServletContext().getRequestDispatcher("/form_produto.jsp");
                        request.setAttribute("produto", novo);
                        disp.forward(request, response);
                    } else {
                        mensagem = "Produto não encontrado";
                    }
                } else {
                    mensagem = "Acesso Negado";
                }
            }
            if (acao.equals("excluir")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    if (idproduto > 0) {
                        p.setIdproduto(idproduto);
                        if (p.excluir()) {
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
        out.println("location.href='listar_produto.jsp';");
        out.println("</script>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*RECEBENDO VALORES PARA O PRODUTO*/
        PrintWriter out = response.getWriter();
        String idproduto = request.getParameter("idproduto");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String preco = request.getParameter("preco");
        String quantidade = request.getParameter("quantidade");

        String mensagem = "";

        /*SETANDO VALORES PARA O PRODUTO*/
        Produto p = new Produto();
        if (!idproduto.isEmpty()) {
            p.setIdproduto(Integer.parseInt(idproduto));
        }
        p.setNome(nome);
        p.setDescricao(descricao);
        p.setPreco(Float.parseFloat(preco));
        p.setQuantidade(Integer.parseInt(quantidade));

        try {
            if (nome.equals("") || descricao.equals("") || preco.isEmpty()) {
                mensagem = "Campos obrigatorios devem ser preenchidos";
            } else {
                if (p.gravar()) {
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
        out.println("location.href='listar_produto.jsp';");
        out.println("</script>");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
