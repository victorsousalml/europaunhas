package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ServicoProduto;

/**
 *
 * @author Gabriel Marques
 */
public class GerenciarServicoProduto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String mensagem = "";
        String idservico = request.getParameter("idservico");
        String acao = request.getParameter("acao");
        try {
            if (acao.equals("gerenciar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    ServicoProduto servico = new ServicoProduto();
                    servico = servico.getCarregaVinculadoPorServico(Integer.parseInt(idservico));
                    if (servico.getServico().getIdservico() > 0) {
                        RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_servico_produto.jsp");
                        request.setAttribute("servicop", servico);
                        disp.forward(request, response);
                    } else {
                        mensagem = "Servico não encontrado";
                    }
                } else {
                    mensagem = "Acesso Negado";
                }
            }
            
            
            if (acao.equals("desvincular")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    String idproduto = request.getParameter("idproduto");
                    if (idproduto.equals("") || idproduto.isEmpty() || idservico.equals("") || idservico.isEmpty()) {
                        mensagem = "Campos obrigatórios devem ser selecionados";
                    } else {
                        ServicoProduto s = new ServicoProduto();
                        if (s.desvincularProduto(Integer.parseInt(idservico), Integer.parseInt(idproduto))) {
                            mensagem = "Desvinculado com sucesso";
                        } else {
                            mensagem = "Erro ao desvincular";
                        }
                    }
                } else {
                    mensagem = "Acesso Negado";
                }
            }

        } catch (Exception e) {
            out.print(e);
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='gerenciar_servico_produto.do?acao=gerenciar&idservico=" + idservico + "';");
        out.println("</script>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String mensagem = "";
        String idproduto = request.getParameter("idproduto");
        String idservico = request.getParameter("idservico");
        String quantidade = request.getParameter("quantidade");
        try {
            if (idproduto.isEmpty() || idproduto.equals("") || idservico.equals("") || idservico.isEmpty()) {
                mensagem = "Campos obrigatórios devem ser selecionados";
            } else {
                ServicoProduto s = new ServicoProduto();
                if (s.vincularProduto(Integer.parseInt(idservico), Integer.parseInt(idproduto), Integer.parseInt(quantidade))) {
                    mensagem = "Vinculado com sucesso";
                } else {
                    mensagem = "Erro ao vincular";
                }
            }

        } catch (Exception e) {
            out.print(e);
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='gerenciar_servico_produto.do?acao=gerenciar&idservico=" + idservico + "';");
        out.println("</script>");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
