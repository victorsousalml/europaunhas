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
import model.Agendamento;
import model.Cliente;
import model.Funcionario;

/**
 *
 * @author BNB - SOLUÇÕES
 */
public class GerenciarAgendamento extends HttpServlet {

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
        int idagendamento;
        idagendamento = Integer.valueOf(request.getParameter("idagendamento"));
        Agendamento a = new Agendamento();
        try {
            if (acao.equals("alterar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    Agendamento novo;
                    novo = a.getCarregaPorID(idagendamento);
                    if (novo.getIdagendamento() > 0) {
                        RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_agendamento.jsp");
                        request.setAttribute("agendamento", novo);
                        disp.forward(request, response);
                    } else {
                        mensagem = "Agendamento não encontrado";
                    }
                } else {
                    mensagem = "Acesso Negado";
                }
            }
            if (acao.equals("excluir")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    if (idagendamento > 0) {
                        a.setIdagendamento(idagendamento);
                        if (a.excluir()) {
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
        out.println("location.href='listar_agendamento.jsp';");
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
        //RECEBENDO OS VALORES DO AGENDAMENTO
        String idagendamento = request.getParameter("idagendamento");
        String data = request.getParameter("data");
        String valor = request.getParameter("valor");
        String status = request.getParameter("status");

        //RECEBENDO AS CHAVES ESTRANGEIRAS DO FORMULÁRIO
        int idcliente = Integer.parseInt(request.getParameter("idcliente"));
        int idfuncionario = Integer.parseInt(request.getParameter("idfuncionario"));
        int idservico = Integer.parseInt(request.getParameter("idservico"));
        
        String mensagem = "";

        //SETANDO OS VALORES DO AGENDAMENTO
        Agendamento a = new Agendamento();
        if (!idagendamento.isEmpty()) {
            a.setIdagendamento(Integer.parseInt(idagendamento));
        }
        a.setData(data);
        if(valor == null)
            a.setValor(0);
        else
            a.setValor(Float.parseFloat(valor));
        a.setStatus(Integer.parseInt(status));
        //SETANDO VALOR DA CHAVE ESTRANGEIRA DE CLIENTE
        
        Cliente c = new Cliente();
        c.setIdcliente(idcliente);

        //SETANDO VALOR DA CHAVE ESTRANGEIRA DE FUNCIONÁRIO
        Funcionario f = new Funcionario();
        f.setIdfuncionario(idfuncionario);
        
        
        a.setCliente(c);
        a.setFuncionario(f);
        
        try {
            if (data.equals("") || idfuncionario == 0 || idcliente == 0) {
                mensagem = "Campos obrigatorios devem ser preenchidos";
            } else {
                if (a.gravar()){
                    if (a.vincular(a.getIdagendamento(), idservico)) {
                        mensagem = "Gravado com sucesso";
                    }else{
                        mensagem= "Erro ao vincular o agendamento!"+idservico+"  "+a.getIdagendamento();
                    }
                } else {
                    mensagem = "Erro ao gravar o agendamento";
                }
            }
        } catch (Exception e) {
            out.println(e);
            mensagem = "Erro ao acessar o banco";
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='listar_agendamento.jsp';");
        out.println("</script>");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
