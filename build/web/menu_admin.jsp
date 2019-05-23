

<%@page import="model.Funcionario"%>
<%@page import="model.Cliente"%>
<%@page import="model.Usuario"%>
<%@page import="controller.GerenciarLogin"%>

<%
    Funcionario funcionario = new Funcionario();

    Usuario ulogado = GerenciarLogin.verificarAcesso(request, response);
    request.setAttribute("ulogado", ulogado);

    Funcionario usu = funcionario.getCarregaPorUsuario(ulogado.getIdusuario());
    request.setAttribute("usando", usu);


%>


<aside class="left-sidebar" data-sidebarbg="skin5">

    <div class="scroll-sidebar">
        <!-- MENU ITENS GERENCIA -->
        <nav class="sidebar-nav">
            <ul id="sidebarnav" class="p-t-30">
                <c:if test="${ulogado!=null && ulogado.perfil!=null}">
                    <c:forEach var="menu" items="${ulogado.perfil.menus}">
                        <c:if test="${menu.exibir==1}">
                            <li class="sidebar-item"> <a class="sidebar-link waves waves-dark sidebar-link "  href="${menu.link}" aria-expanded="false">
                                    <i class="${menu.icone}"></i><span class="hide-menu">${menu.menu}</span></a></li>
                                    </c:if>
                                </c:forEach>
                            </c:if>



            </ul>
        </nav>
    </div>
</aside>


