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
import model.Funcionario;
import model.Perfil;
import model.Usuario;

/**
 *
 * @author BNB - SOLUÇÕES
 */
public class GerenciarFuncionario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String mensagem = "";

        int idfun;
        idfun = Integer.valueOf(request.getParameter("idfuncionario"));
        Funcionario f = new Funcionario();
        try {
            if (acao.equals("alterar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    Funcionario novo;
                    novo = f.getCarregaPorID(idfun);
                    if (novo.getIdfuncionario() > 0) {
                        RequestDispatcher disp
                                = getServletContext().getRequestDispatcher("/form_funcionario.jsp");
                        request.setAttribute("funcionario", novo);
                        disp.forward(request, response);
                    } else {
                        mensagem = "Funcionário não encontrado";
                    }
                } else {
                    mensagem = "Acesso Negado";
                }
            }
            
            if (acao.equals("excluir")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    if (idfun > 0) {
                        f.setIdfuncionario(idfun);
                        Funcionario fun;
                        fun = f.getCarregaPorID(idfun);
                        if (fun.excluir()) {
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
        out.println("location.href='listar_funcionario.jsp';");
        out.println("</script>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String mensagem = "";
        String escolha = request.getParameter("escolha");

        /*DADOS DO FUNCIONARIO*/
        String idfuncionario = request.getParameter("idfuncionario");
        String nome_fun = request.getParameter("nome");
        String dataNasc = request.getParameter("dataNasc");
        String cpf = request.getParameter("cpf");
        String sexo = request.getParameter("sexo");
        String email = request.getParameter("email");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");

        /*DADOS DO USUÁRIO QUE PERTENCERÁ AO FUNCIONÁRIO*/
        String idusuario = request.getParameter("idusuario");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String status = request.getParameter("status");
        String idperfil = request.getParameter("idperfil");

        /*SETANDO VALORES PARA O USUÁRIO*/
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

                /*CRIA NOVO FUNCIONÁRIO E USUÁRIO*/
            } else if (escolha.equals("cadastrar")) {

                /*VERIFICAR SE O LOGIN ESTÁ DISPONÍVEL*/
                if (u.getCarregaPorLogin(u)) {

                    /*INSERIR USUÁRIO*/
                    if (u.gravar()) {

                        /*SETANDO VALORES PARA O FUNCIONÁRIO*/
                        Funcionario f = new Funcionario();
                        if (!idfuncionario.isEmpty()) {
                            f.setIdfuncionario(Integer.parseInt(idfuncionario));
                        }
                        f.setNome(nome_fun);
                        f.setDataNasc(dataNasc);
                        f.setCpf(cpf);
                        f.setSexo(sexo);
                        f.setEmail(email);
                        f.setEndereco(endereco);
                        f.setTelefone(telefone);
                        try {

                            /*INSERIR FUNCIONÁRIO COM O USUÁRIO INFORMADO PELO LOGIN*/
                            if (f.gravar(f, u.getCarregaPorLogin(login))) {
                                mensagem = "Gravado com sucesso";
                            } else {
                                mensagem = "Erro ao gravar funcionário";
                            }
                        } catch (Exception e) {
                            out.println(e);
                            mensagem = "" + e;
                        }
                    } else {
                        mensagem = "Erro ao gravar o usuário!";
                    }
                } else {
                    mensagem = "O nome de login já está me uso, favor tente novamente";
                }

                /*ALTERA USUÁRIO E FUNCIONÁRIO JÁ EXISTENTES*/
            } else if (escolha.equals("alterar")) {

                /*ATUALIZA DADOS DO USUÁRIO*/
                if (u.gravar()) {

                    /*SETANDO ATUALZAÇÕES PARA O FUNCIONÁRIO*/
                    Funcionario f = new Funcionario();
                    if (!idusuario.isEmpty()) {
                        f.setIdfuncionario(Integer.parseInt(idfuncionario));
                    }
                    f.setNome(nome_fun);
                    f.setDataNasc(dataNasc);
                    f.setCpf(cpf);
                    f.setSexo(sexo);
                    f.setEmail(email);
                    f.setEndereco(endereco);
                    f.setTelefone(telefone);
                    try {

                        /*ATUALIZA DADOS DO FUNCIONÁRIO*/
                        if (f.gravar(f, u.getCarregaPorLogin(login))) {
                            mensagem = "Gravado com sucesso";
                        } else {
                            mensagem = "Erro ao gravar funcionário";
                        }
                    } catch (Exception e) {
                        out.println(e);
                        mensagem = "" + e;
                    }
                } else {
                    mensagem = "Erro ao gravar o usuário!";
                }
            }
        } catch (Exception e) {
            out.println(e);
            mensagem = "Erro ao acessar o banco";
        }

        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='funcionario.jsp';");
        out.println("</script>");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
