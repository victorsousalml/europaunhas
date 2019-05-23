<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html dir="ltr">
    <%@include file="required_meta.jsp" %>
    <link rel="stylesheet" href="datatables/jquery.dataTables.min.css"/>

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
                                        <script type="text/javascript">
            function confirmarExclusao(idmenu, nome, idperfil) {
                if (confirm('Deseja realmente desvicular o menu ' + nome + '?')) {
                    location.href = 'gerenciar_menu_perfil.do?acao=desvincular&idmenu=' + idmenu +'&idperfil='+idperfil;
                }
            }



        </script>   

                                        
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
                        <form name="form_inserir_perfil" 
                              action="gerenciar_menu_perfil.do" method="POST">
                            <div class="row">
                                <input type="hidden" name="idperfil" id="idperfil"
                                       value="${perfilv.idperfil}"/>
                                <div class="form-group col-sm-8">
                                    <label for="perfil" class="control-label">
                                        Perfil: ${perfilv.perfil}
                                    </label>
                                    <br />
                                    <label for="idmenu" class="control-label">
                                        Menu
                                    </label>
                                    <select name="idmenu" id="idmenu" required=""  class="form-control">
                                        <option value="">Selecione o Menu</option>
                                        <c:forEach var="m" items="${perfilv.naoMenus}">
                                            <option value="${m.idmenu}">${m.menu}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <button class="btn btn-success">Vincular</button> &nbsp;
                                <a href="listar_perfil.jsp" class="btn btn-warning">
                                    Voltar
                                </a>    
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-body">
                                <h3 class="display-4">Menus Vinculados</h3>
                                <table class="table table-striped table-bordered 
                                       table-hover " id="listaMenu">
                                    <thead>
                                        <tr class="thead-dark text-center">
                                            <th>ID</th>
                                            <th>Menu</th>
                                            <th>Link</th>
                                            <th>Desvincular</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="m" items="${perfilv.menus}">
                                            <tr class="text-center">
                                                <td>${m.idmenu}</td>
                                                <td>${m.menu}</td>
                                                <td>${m.link}</td>
                                                <td>
                                                    <button class="btn btn-danger" 
                                                            onclick="confirmarExclusao(${m.idmenu}, '${m.menu}',${perfilv.idperfil})">
                                                        <i class="fas fa-unlink"> </i>
                                                    </button>    
                                                </td>
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>  
                            </div>
                        </div>
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
    <script type="text/javascript" src="datatables/jquery.js"></script>
    <script type="text/javascript" src="datatables/jquery.dataTables.min.js"></script>
    <script type="text/javascript">
                                                    $(document).ready(function () {
                                                        $("#listaMenu").dataTable({
                                                            bJQueryUI: true,
                                                            autoWidth: false,
                                                            scrollY: true,
                                                            scrollX: true,
                                                            scrollCollapse: true,
                                                            paging: true,
                                                            fixedColumns: {
                                                                heightMatch: 'none'
                                                            },
                                                            "oLanguage": {
                                                                "sProcessing": "Processando...",
                                                                "sLengthMenu": "Mostrar _MENU_ registros",
                                                                "sZeroRecords": "Não foram encontrados resultados",
                                                                "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                                                                "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                                                                "sInfoFiltered": "",
                                                                "sInfopostFix": "",
                                                                "sSearch": "Pesquisar:",
                                                                "sURL": "",
                                                                "oPaginate": {
                                                                    "sFirst": "Primeiro",
                                                                    "sPrevious": "Anterior",
                                                                    "sNext": "Próximos",
                                                                    "sLast": "Último"
                                                                }
                                                            }
                                                        });
                                                    });
    </script>  



</script>
</body>

</html>