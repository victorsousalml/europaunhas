/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Menu;
import model.Usuario;

/**
 *
 * @author Administrador
 */
public class GerenciarLogin extends HttpServlet {

   private static HttpServletResponse response;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getSession().removeAttribute("ulogado");
        response.sendRedirect("form_login.jsp");
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        GerenciarLogin.response = response;
        
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        
        ArrayList<String> erros = new ArrayList<String>();
        
        if(login==null || login.trim().isEmpty())
            erros.add("Preencha o login");
        
        if(senha==null || senha.trim().isEmpty())
            erros.add("Preencha a senha");
        
        if(erros.size()>0){
            String campos ="";
            for(String erro : erros){
                campos +="\\n - "+erro;
            }
            exibirMensagem("Preecha o(s) campo(s):"+campos);
        }else{
            Usuario u = new Usuario();
            try{
                u = u.getRecuperarUsuario(login);
                if(u.getIdusuario()>0 && u.getSenha().equals(senha)){
                    HttpSession sessao = request.getSession();
                    sessao.setAttribute("ulogado", u);
                    response.sendRedirect("index.jsp");
                }else{
                    exibirMensagem("Usuário ou senha inválidos");
                }
                
            }catch(Exception e){
                exibirMensagem("Erro ao acessar o banco");
            }
        
        }
    }
    
    public static Usuario 
            verificarAcesso(HttpServletRequest request, HttpServletResponse response){
        
        Usuario u = null;
        GerenciarLogin.response = response;
        
        try{
            HttpSession sessao = request.getSession();
            if(sessao.getAttribute("ulogado")==null){
                response.sendRedirect("form_login.jsp");
            }else{
                String uri = request.getRequestURI();
                String queryString = request.getQueryString();
                if(queryString!=null){
                    uri += "?"+queryString;
                }
                u = (Usuario)request.getSession().getAttribute("ulogado");
                if(u==null){
                    sessao.setAttribute("mensagem", "Você não está autenticado");
                    response.sendRedirect("form_login.jsp");
                }else{
                    boolean possuiAcesso = false;
                    for(Menu m: u.getPerfil().getMenus()){
                        if(uri.contains(m.getLink())){
                            possuiAcesso = true;
                            break;
                        }
                    }
                    if(!possuiAcesso){
                        exibirMensagem("Acesso Negado");
                    }
                }
            }
        }catch(Exception e){
            exibirMensagem("Exceção: "+e.getMessage());
        
        }
        return u;
    
    }
    
    public static boolean 
            verificarPermissao(HttpServletRequest request, HttpServletResponse response){
        
        Usuario u = null;
        GerenciarLogin.response = response;
        boolean possuiAcesso=false;
        try{
            HttpSession sessao = request.getSession();
            if(sessao.getAttribute("ulogado")==null){
                response.sendRedirect("form_login.jsp");
            }else{
                String uri = request.getRequestURI();
                String queryString = request.getQueryString();
                if(queryString!=null){
                    uri += "?"+queryString;
                }
                u = (Usuario)request.getSession().getAttribute("ulogado");
                if(u==null){
                    sessao.setAttribute("mensagem", "Você não está autenticado");
                    response.sendRedirect("form_login.jsp");
                }else{
                    
                    for(Menu m: u.getPerfil().getMenus()){
                        if(uri.contains(m.getLink())){
                            possuiAcesso = true;
                            break;
                        }
                    }
                    if(!possuiAcesso){
                        exibirMensagem("Acesso Negado");
                    }
                }
            }
        }catch(Exception e){
            exibirMensagem("Exceção: "+e.getMessage());
        
        }
        return possuiAcesso;
    
    }
    
    private static void exibirMensagem(String mensagem){
        try{
            PrintWriter out = response.getWriter();
            out.print("<script>");
            out.print("alert('"+mensagem+"');");
            out.print("history.back();");
            out.print("</script>");
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    
        
    
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
