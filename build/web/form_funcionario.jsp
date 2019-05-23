<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html dir="ltr">
    <%@include file="required_meta.jsp" %>

    <!-- ============================================================== -->
    <!-- DIV PRINCIAL - pages.scss -->
    <!-- ============================================================== -->
    <div id="main-wrapper">
        <!-- ============================================================== -->
        <!-- MENU SUPERIOR -  pages.scss -->
        <!-- ============================================================== -->

        <header class="topbar" data-navbarbg="skin5">
            <nav class="navbar top-navbar navbar-expand-md navbar-dark">
                <div class="navbar-header" data-logobg="skin5">
                    <!-- PARA VER SOMENTE NO CELULAR -->
                    <a class="nav-toggler waves-effect waves-light d-block d-md-none" href="javascript:void(0)"><i class="ti-menu ti-close"></i></a>
                    <!-- ============================================================== -->
                    <!-- Logo -->
                    <!-- ============================================================== -->
                    <%@include file="logo.jsp" %>
                    <!-- ============================================================== -->
                    <!-- FIM DA LOGO -->
                    <!-- ============================================================== -->
                    <!-- ============================================================== -->
                    <!-- VISIVEL NO CELULAR -->
                    <!-- ============================================================== -->
                    <a class="topbartoggler d-block d-md-none waves-effect waves-light" href="" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><i class="ti-more"></i></a>
                </div>
                <!-- ============================================================== -->
                <!-- FIM DA LOGO -->
                <!-- ============================================================== -->
                <div class="navbar-collapse collapse" id="navbarSupportedContent" data-navbarbg="skin5">
                    <!-- ============================================================== -->
                    <!-- NAV ITENS -->
                    <!-- ============================================================== -->
                    <ul class="navbar-nav float-left mr-auto">
                        <li class="nav-item d-none d-md-block"><a class="nav-link sidebartoggler waves-effect waves-light" href="javascript:void(0)" data-sidebartype="mini-sidebar"><i class="mdi mdi-menu font-24"></i></a></li>

                        <!-- ============================================================== -->
                        <!-- PESQUISAR -->
                        <!-- ============================================================== -->
                        <li class="nav-item search-box"> <a class="nav-link waves-effect waves-dark" ><i class="ti-search"></i></a>
                            <form class="app-search position-absolute">
                                <input type="text" class="form-control" placeholder="Search &amp; enter"> <a class="srh-btn"><i class="ti-close"></i></a>
                            </form>
                        </li>
                    </ul>
                    <!-- ============================================================== -->
                    <!-- MENU SUPERIO DA DIREITA -->
                    <!-- ============================================================== -->
                    <ul class="navbar-nav float-right">
                        <!-- ============================================================== -->
                        <!-- NOTIFICAÇÕES -->
                        <!-- ============================================================== -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle waves-effect waves-dark" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="mdi mdi-bell font-24"></i>
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">

                            </div>
                        </li>
                        <!-- ============================================================== -->
                        <!-- NOTIFICAÇÃO -->
                        <!-- ============================================================== -->
                        <!-- ============================================================== -->
                        <!-- MENSAGEM -->
                        <!-- ============================================================== -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle waves-effect waves-dark" href="" id="2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="font-24 mdi mdi-comment-processing"></i>
                            </a>

                        </li>
                        <!-- ============================================================== -->
                        <!-- FIM MENSAGEM -->
                        <!-- ============================================================== -->

                        <!-- ============================================================== -->
                        <!-- MINHA CONTA -->
                        <!-- ============================================================== -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark pro-pic" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="assets/images/users/1.jpg" alt="user" class="rounded-circle" width="31"></a>
                            <div class="dropdown-menu dropdown-menu-right user-dd animated">
                                <a class="dropdown-item" href="minhaconta.jsp"><i class="ti-user m-r-5 m-l-5"></i> Minha Conta</a>
                                <ul class="navbar-nav">
                                    <c:if test="${ulogado!=null}"> </c:if>
                                        <a class="dropdown-item" href="gerenciar_login.do"><i class="fa fa-power-off m-r-5 m-l-5"></i> Sair</a>
                                    </ul>


                                </div>
                            </li>
                            <!-- ============================================================== -->
                            <!-- MINHA CONTA -->
                            <!-- ============================================================== -->
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- ============================================================== -->
            <!-- FIM DO MENU SUPERIOR -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- MENU PRINCIPAL DA ESQUERDA - sidebar.scss  -->
            <!-- ============================================================== -->

        <%@include file="menu_admin.jsp" %>
        <!-- ============================================================== -->
        <!-- CONTEUDO  -->
        <!-- ============================================================== -->
        <div class="page-wrapper">
            <!-- ============================================================== -->
            <!-- INFORMAÇÃO DE QUEM ESTÁ LOGADO -->
            <!-- ============================================================== -->
            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-12 d-flex no-block align-items-center">
                        <h4 class="page-title">${ulogado.perfil} - ${usando.nome}</h4>
                        <div class="ml-auto text-right">

                        </div>
                    </div>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- INFORMAÇÃO DE QUEM ESTÁ LOGADO -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- CONTAINER DOS MINI BOTÕES  -->
            <!-- ============================================================== -->
            <div class="container-fluid">
                <!-- ============================================================== -->
                <!-- Start Page Content -->
                <!-- ============================================================== -->
                <div class="card">
                    <div class="card-body wizard-content">
                        <form name="form_funcionario" action="gerenciar_funcionario.do" method="POST">
                            <div>
                                <h3>Dados Pessoais</h3>
                                <section>

                                    <input type="hidden" name="idfuncionario" id="idfuncionario"
                                           class="form-control" value="${funcionario.idfuncionario}"/>


                                    <input type="hidden" name="idusuario" id="idfuncionario"
                                           class="form-control" value="${funcionario.usuario.idusuario}"/>

                                    <input type="hidden" name="escolha" id="acao" class="form-control" 
                                           <c:if test="${funcionario.idfuncionario > 0}">
                                               value="alterar"
                                           </c:if>
                                           <c:if test="${funcionario.idfuncionario == null}">
                                               value="cadastrar"
                                           </c:if> /> 

                                    <label for="nome" class="control-label">Nome</label>
                                    <input type="text" name="nome" id="nome" class="form-control" required=""  value="${funcionario.nome}"/> 

                                    <label for="dataNasc" class="control-label" >Data de Nascimento</label>
                                    <input type="date" name="dataNasc" id="dataNasc" class="form-control" required="" value="${funcionario.dataNasc}"/>

                                    <label for="cpf" class="control-label">CPF</label>
                                    <input type="text" name="cpf" id="cpf" class="form-control" value="${funcionario.cpf}"/>

                                    <label for="sexo" class="control-label">Sexo</label>
                                    <select name="sexo" required="" class="form-control">
                                        <c:if test="${funcionario.sexo==null}">
                                            <option value="n">Escolha uma opção</option>
                                            <option value="m">Masculino</option>
                                            <option value="f">Feminino</option>
                                        </c:if>  
                                        <c:if test="${funcionario.sexo=='m'}">
                                            <option value="m" selected="">Masculino</option>
                                            <option value="f">Feminino</option>
                                        </c:if>
                                        <c:if test="${funcionario.sexo=='f'}">
                                            <option value="m">Masculino</option>
                                            <option value="f" selected="">Feminino</option>
                                        </c:if>
                                    </select>

                                    <label for="email" class="control-label">Email</label>
                                    <input type="email" name="email" id="email" class="form-control" value="${funcionario.email}"/>

                                    <label for="endereco" class="control-label">
                                        Endereco
                                    </label>
                                    <input type="text" name="endereco" id="endereco"
                                           class="form-control" value="${funcionario.endereco}"/>

                                    <label for="telefone" class="control-label">
                                        Telefone
                                    </label>
                                    <input type="text" name="telefone" id="telefone"
                                           class="form-control" value="${funcionario.telefone}"/>


                                    <input type="hidden" name="status" id="status" value="1"/>
                                    <input type="hidden" name="idusuario" id="status" value="${usuario.idusuario}"/>


                                </section>
                                <p></p>
                                <h3>Dados de Login</h3>
                                <section>
                                    <label for="login" class="control-label">
                                        Login
                                    </label>
                                    <input type="text" name="login" id="login"
                                           class="form-control" required=""
                                           value="${funcionario.usuario.login}"/>

                                    <label for="senha" class="control-label">
                                        Senha
                                    </label>
                                    <input type="password" name="senha" id="senha"
                                           class="form-control" value="${funcionario.usuario.senha}"/>

                                    <label for="perfil" class="control-label">
                                        Perfil
                                    </label>
                                    <select id="idperfil" name="idperfil" required="" class="form-control">
                                        <option value="">Selecione o Perfil</option>
                                        <jsp:useBean class="model.Perfil" id="perfil"/>
                                        <c:forEach var="p" items="${perfil.lista}">
                                            <option value="${p.idperfil}"
                                                    <c:if test="${p.idperfil==funcionario.usuario.perfil.idperfil}">
                                                        selected=""
                                                    </c:if>        
                                                    >${p.perfil}</option>
                                        </c:forEach>

                                    </select>   

                                </section>
                                <p></p>
                                <section>
                                    <button class="btn btn-success" value="submit">Gravar</button> &nbsp;
                                    <a  href="funcionario.jsp" class="btn btn-warning">
                                        Voltar
                                    </a>  
                                </section>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- SEGUNDO - BLOCO -->
                <!-- ============================================================== -->
            </div>
            <!-- ============================================================== -->
            <!-- FIM DO CONTAINER - FLUID  -->
            <!-- ============================================================== -->


            <!-- ============================================================== -->
            <!-- RODAPÉ -->
            <!-- ============================================================== -->
            <footer class="footer text-center">
                Gabriel Marques &COPY;
            </footer>
            <!-- ============================================================== -->
            <!-- RODAPÉ -->
            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- FIM DA PÁGINA  -->
        <!-- ============================================================== -->
    </div>
    <!-- ============================================================== -->
    <!-- FIM DA DIV PRINCIPAL -->
    <!-- ============================================================== -->


    <!-- ============================================================== -->
    <!-- JQUERY UTILIZADO-->
    <!-- ============================================================== -->
    <script src="assets/libs/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- slimscrollbar scrollbar JavaScript -->
    <script src="assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
    <script src="assets/extra-libs/sparkline/sparkline.js"></script>
    <!--Wave Effects -->
    <script src="dist/js/waves.js"></script>
    <!--Menu sidebar -->
    <script src="dist/js/sidebarmenu.js"></script>
    <!--Custom JavaScript -->
    <script src="dist/js/custom.min.js"></script>
    <!-- this page js -->
    <script src="assets/libs/jquery-steps/build/jquery.steps.min.js"></script>
    <script src="assets/libs/jquery-validation/dist/jquery.validate.min.js"></script>

</script>
</body>

</html>



