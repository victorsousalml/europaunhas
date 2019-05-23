
<%-- 
    Document   : index
    Created on : 20/02/2019, 20:48:34
    Author     : Administrador
--%>

<%@page import="model.Usuario"%>
<%@page import="model.Cliente"%>
<%@page import="model.Funcionario"%>
<%@page import="model.Agendamento"%>
<%@page import="model.Servico"%>
<%@page import="model.Menu"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html dir="ltr" lang="pt-br">
    <head>
        <%@include file="required_meta.jsp" %>
    </head>
    <body>
        <!-- ============================================================== -->
        <!-- DIV PRINCIAL -->
        <!-- ============================================================== -->
        <div id="main-wrapper">
            <!-- ============================================================== -->
            <!-- MENU SUPERIOR -->
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
                <!-- MENU PRINCIPAL DA ESQUERDA  -->
                <!-- ============================================================== -->
            <%@include file="menu_admin.jsp" %>
            <!-- ============================================================== -->
            <!-- FIM MENU PRINCIPAL DA ESQUERDA  -->
            <!-- ============================================================== -->
            
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
                            <h4 class="page-title">${ulogado.perfil} - ${usuario.nome}</h4>
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
                <div class="container-fluid" >
                    <!-- ============================================================== -->
                    <!-- MINI BOTÕES -->
                    <!-- ============================================================== -->
                    <div class="row">
                        <!-- Column -->
                        <c:if test="${ulogado!=null && ulogado.perfil!=null}">
                            <c:forEach var="menu" items="${ulogado.perfil.menus}">
                                <c:if test="${menu.exibir==1}">
                                    <div class="col-md-6 col-lg-4 col-xlg-4">
                                        <div class="card card-hover">
                                            <a href="${menu.link}">
                                                <div class="box text-center"  style="background-color: #ff1a66;">
                                                <h1 class="font-light text-white"><i class="${menu.icone}"></i></h1>
                                                <h6 class="text-white">${menu.menu}</h6>
                                            </div>
                                            </a>
                                        </div>
                                    </div>

                                </c:if>
                            </c:forEach>
                        </c:if>
                    </div>
                    <!-- ============================================================== -->
                    <!-- CONTEUDO - PRIMEIRO BLOCO -->
                    <!-- ============================================================== -->
                    
                     <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="d-md-flex align-items-center">
                                        <div>
                                            <h4 class="card-title">Acompanhamento</h4>
                                            <h5 class="card-subtitle">Últimas atualizações</h5>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <!-- column -->
                                        <div class="col-lg-9">
                                            <div class="flot-chart">
                                                <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                                            <ol class="carousel-indicators">
                                                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                                                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                                            </ol>
                                            <div class="carousel-inner bg-dark">
                                                <div class="carousel-item active">
                                                    <!-- card -->
                                                    <div class="card  bg-dark p-10 text-white mb-0 ">
                                                        <div class="card-body  mb-5">
                                                            <h4 class="card-title m-b-0 font-16">Clientes Adicionados Recentemente</h4>
                                                            <div class="m-t-20 ">
                                                                <div class="d-flex no-block align-items-center">
                                                                    <jsp:useBean class="model.Cliente" id="clie">
                                                                        <table class="table table-striped table-bordered 
                                                                               display table-sm bg-light text-dark mb-0">
                                                                            <tr class="text-center"> <th>ID</th>
                                                                                <th>Nome</th>
                                                                                <th>Email</th>
                                                                                <th>Login</th></tr>
                                                                                    <c:forEach var="c" items="${clie.ultimos}">
                                                                                <tr class="text-center ">
                                                                                    <td>${c.idcliente}</td>
                                                                                    <td>${c.nome}</td>
                                                                                    <td>${c.email}</td>
                                                                                    <td>${c.usuario.login}</td>
                                                                                </tr>
                                                                            </c:forEach>
                                                                        </table>
                                                                    </jsp:useBean>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="carousel-item ">
                                                    <!-- card -->
                                                    <div class="card mt-2 mb-4 bg-dark p-10 text-white   ">
                                                        <div class="card-body  mb-5">
                                                            <h4 class="card-title m-b-0 font-16">Agendamentos</h4>
                                                            <div class="m-t-20 ">
                                                                <div class="d-flex no-block align-items-center">
                                                                    <span>50% Agendados</span>
                                                                    <div class="ml-auto">
                                                                        <span>50</span>
                                                                    </div>
                                                                </div>
                                                                <div class="progress">
                                                                    <div class="progress-bar progress-bar-striped bg-warning" role="progressbar" style="width: 100%" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"></div>
                                                                </div>
                                                            </div>
                                                            <div>
                                                                <div class="d-flex no-block align-items-center m-t-25">
                                                                    <span>40% Realizados</span>
                                                                    <div class="ml-auto">
                                                                        <span>40</span>
                                                                    </div>
                                                                </div>
                                                                <div class="progress">
                                                                    <div class="progress-bar progress-bar-striped bg-success" role="progressbar" style="width: 40%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                                                </div>
                                                            </div>
                                                            <div>
                                                                <div class="d-flex no-block align-items-center m-t-25">
                                                                    <span>10% Cancelados</span>
                                                                    <div class="ml-auto">
                                                                        <span>10</span>
                                                                    </div>
                                                                </div>
                                                                <div class="progress">
                                                                    <div class="progress-bar progress-bar-striped bg-danger" role="progressbar" style="width: 10%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                            </div>
                                        </div>
                                        <%@include file="mini_informacoes.jsp" %>
                                        <!-- column -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ============================================================== -->
                    <!-- CONTEUDO - PRIMEIRA BLOCO -->
                    <!-- ============================================================== -->



                    <!-- ============================================================== -->
                    <!-- CONTEUDO - SEGUNDO BLOCO -->
                    <!-- ============================================================== -->
                    <div class="row">
                        <!-- column -->
                        <div class="col-lg-6">

                            <!-- Card -->
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">To Do List</h4>
                                    <div class="todo-widget scrollable" style="height:450px;">
                                        <ul class="list-task todo-list list-group m-b-0" data-role="tasklist">
                                            <li class="list-group-item todo-item" data-role="task">
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck">
                                                    <label class="custom-control-label todo-label" for="customCheck">
                                                        <span class="todo-desc">Lorem Ipsum is simply dummy text of the printing and typesetting industry.</span> <span class="badge badge-pill badge-danger float-right">Today</span>
                                                    </label>
                                                </div>
                                                <ul class="list-style-none assignedto">
                                                    <li class="assignee"><img class="rounded-circle" width="40" src="assets/images/users/1.jpg" alt="user" data-toggle="tooltip" data-placement="top" title="" data-original-title="Steave"></li>
                                                    <li class="assignee"><img class="rounded-circle" width="40" src="assets/images/users/2.jpg" alt="user" data-toggle="tooltip" data-placement="top" title="" data-original-title="Jessica"></li>
                                                    <li class="assignee"><img class="rounded-circle" width="40" src="assets/images/users/3.jpg" alt="user" data-toggle="tooltip" data-placement="top" title="" data-original-title="Priyanka"></li>
                                                    <li class="assignee"><img class="rounded-circle" width="40" src="assets/images/users/4.jpg" alt="user" data-toggle="tooltip" data-placement="top" title="" data-original-title="Selina"></li>
                                                </ul>
                                            </li>
                                            <li class="list-group-item todo-item" data-role="task">
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck1">
                                                    <label class="custom-control-label todo-label" for="customCheck1">
                                                        <span class="todo-desc">Lorem Ipsum is simply dummy text of the printing</span><span class="badge badge-pill badge-primary float-right">1 week </span>
                                                    </label>
                                                </div>
                                                <div class="item-date"> 26 jun 2017</div>
                                            </li>
                                            <li class="list-group-item todo-item" data-role="task">
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck2">
                                                    <label class="custom-control-label todo-label" for="customCheck2">
                                                        <span class="todo-desc">Give Purchase report to</span> <span class="badge badge-pill badge-info float-right">Yesterday</span>
                                                    </label>
                                                </div>
                                                <ul class="list-style-none assignedto">
                                                    <li class="assignee"><img class="rounded-circle" width="40" src="assets/images/users/3.jpg" alt="user" data-toggle="tooltip" data-placement="top" title="" data-original-title="Priyanka"></li>
                                                    <li class="assignee"><img class="rounded-circle" width="40" src="assets/images/users/4.jpg" alt="user" data-toggle="tooltip" data-placement="top" title="" data-original-title="Selina"></li>
                                                </ul>
                                            </li>
                                            <li class="list-group-item todo-item" data-role="task">
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck3">
                                                    <label class="custom-control-label todo-label" for="customCheck3">
                                                        <span class="todo-desc">Lorem Ipsum is simply dummy text of the printing </span> <span class="badge badge-pill badge-warning float-right">2 weeks</span>
                                                    </label>
                                                </div>
                                                <div class="item-date"> 26 jun 2017</div>
                                            </li>
                                            <li class="list-group-item todo-item" data-role="task">
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck4">
                                                    <label class="custom-control-label todo-label" for="customCheck4">
                                                        <span class="todo-desc">Give Purchase report to</span> <span class="badge badge-pill badge-info float-right">Yesterday</span>
                                                    </label>
                                                </div>
                                                <ul class="list-style-none assignedto">
                                                    <li class="assignee"><img class="rounded-circle" width="40" src="assets/images/users/3.jpg" alt="user" data-toggle="tooltip" data-placement="top" title="" data-original-title="Priyanka"></li>
                                                    <li class="assignee"><img class="rounded-circle" width="40" src="assets/images/users/4.jpg" alt="user" data-toggle="tooltip" data-placement="top" title="" data-original-title="Selina"></li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <!-- card -->
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title m-b-0">Progress Box</h4>
                                    <div class="m-t-20">
                                        <div class="d-flex no-block align-items-center">
                                            <span>81% Agendados</span>
                                            <div class="ml-auto">
                                                <span>125</span>
                                            </div>
                                        </div>
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-striped" role="progressbar" style="width: 81%" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="d-flex no-block align-items-center m-t-25">
                                            <span>72% Realizados</span>
                                            <div class="ml-auto">
                                                <span>120</span>
                                            </div>
                                        </div>
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-striped bg-success" role="progressbar" style="width: 1%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="d-flex no-block align-items-center m-t-25">
                                            <span>53% Cancelados</span>
                                            <div class="ml-auto">
                                                <span>785</span>
                                            </div>
                                        </div>
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-striped bg-info" role="progressbar" style="width: 53%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <!-- card new -->
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title m-b-0">News Updates</h4>
                                </div>
                                <ul class="list-style-none">
                                    <li class="d-flex no-block card-body">
                                        <i class="fa fa-check-circle w-30px m-t-5"></i>
                                        <div>
                                            <a href="#" class="m-b-0 font-medium p-0">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</a>
                                            <span class="text-muted">dolor sit amet, consectetur adipiscing</span>
                                        </div>
                                        <div class="ml-auto">
                                            <div class="tetx-right">
                                                <h5 class="text-muted m-b-0">20</h5>
                                                <span class="text-muted font-16">Jan</span>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="d-flex no-block card-body border-top">
                                        <i class="fa fa-gift w-30px m-t-5"></i>
                                        <div>
                                            <a href="#" class="m-b-0 font-medium p-0">Congratulation Maruti, Happy Birthday</a>
                                            <span class="text-muted">many many happy returns of the day</span>
                                        </div>
                                        <div class="ml-auto">
                                            <div class="tetx-right">
                                                <h5 class="text-muted m-b-0">11</h5>
                                                <span class="text-muted font-16">Jan</span>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="d-flex no-block card-body border-top">
                                        <i class="fa fa-plus w-30px m-t-5"></i>
                                        <div>
                                            <a href="#" class="m-b-0 font-medium p-0">Maruti is a Responsive Admin theme</a>
                                            <span class="text-muted">But already everything was solved. It will ...</span>
                                        </div>
                                        <div class="ml-auto">
                                            <div class="tetx-right">
                                                <h5 class="text-muted m-b-0">19</h5>
                                                <span class="text-muted font-16">Jan</span>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="d-flex no-block card-body border-top">
                                        <i class="fa fa-leaf w-30px m-t-5"></i>
                                        <div>
                                            <a href="#" class="m-b-0 font-medium p-0">Envato approved Maruti Admin template</a>
                                            <span class="text-muted">i am very happy to approved by TF</span>
                                        </div>
                                        <div class="ml-auto">
                                            <div class="tetx-right">
                                                <h5 class="text-muted m-b-0">20</h5>
                                                <span class="text-muted font-16">Jan</span>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="d-flex no-block card-body border-top">
                                        <i class="fa fa-question-circle w-30px m-t-5"></i>
                                        <div>
                                            <a href="#" class="m-b-0 font-medium p-0"> I am alwayse here if you have any question</a>
                                            <span class="text-muted">we glad that you choose our template</span>
                                        </div>
                                        <div class="ml-auto">
                                            <div class="tetx-right">
                                                <h5 class="text-muted m-b-0">15</h5>
                                                <span class="text-muted font-16">Jan</span>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <!-- column -->

                        <div class="col-lg-6">
                            <!-- Card -->
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">Chat Option</h4>

                                </div>
                                <div class="card-body border-top">
                                    <div class="row">
                                        <div class="col-9">
                                            <div class="input-field m-t-0 m-b-0">
                                                <textarea id="textarea1" placeholder="Type and enter" class="form-control border-0"></textarea>
                                            </div>
                                        </div>
                                        <div class="col-3">
                                            <a class="btn-circle btn-lg btn-cyan float-right text-white" href="javascript:void(0)"><i class="fas fa-paper-plane"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- card -->
                            <div class="card">


                            </div>

                            <!-- toggle part -->
                            <div id="accordian-4">
                                <div class="card m-t-30">
                                    <a class="card-header link" data-toggle="collapse" data-parent="#accordian-4" href="#Toggle-1" aria-expanded="true" aria-controls="Toggle-1">
                                        <i class="seticon fa fa-arrow-right" aria-hidden="true"></i>
                                        <span>Toggle, Open by default</span>
                                    </a>
                                    <div id="Toggle-1" class="collapse show multi-collapse">
                                        <div class="card-body widget-content">
                                            This box is opened by default, paragraphs and is full of waffle to pad out the comment. Usually, you just wish these sorts of comments would come to an end.
                                        </div>
                                    </div>
                                    <a class="card-header link border-top" data-toggle="collapse" data-parent="#accordian-4" href="#Toggle-2" aria-expanded="false" aria-controls="Toggle-2">
                                        <i class="seticon fa fa-times" aria-hidden="true"></i>
                                        <span>Toggle, Closed by default</span>
                                    </a>
                                    <div id="Toggle-2" class="multi-collapse collapse" style="">
                                        <div class="card-body widget-content">
                                            This box is now open
                                        </div>
                                    </div>
                                    <a class="card-header collapsed link border-top" data-toggle="collapse" data-parent="#accordian-4" href="#Toggle-3" aria-expanded="false" aria-controls="Toggle-3">
                                        <i class="seticon fa fa-times" aria-hidden="true"></i>
                                        <span>Toggle, Closed by default</span>
                                    </a>
                                    <div id="Toggle-3" class="collapse multi-collapse">
                                        <div class="card-body widget-content">
                                            This box is now open
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Tabs -->
                            <div class="card">
                                <!-- Nav tabs -->
                                <ul class="nav nav-tabs" role="tablist">
                                    <li class="nav-item"> <a class="nav-link active" data-toggle="tab" href="#home" role="tab"><span class="hidden-sm-up"></span> <span class="hidden-xs-down">Tab1</span></a> </li>
                                    <li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#profile" role="tab"><span class="hidden-sm-up"></span> <span class="hidden-xs-down">Tab2</span></a> </li>
                                    <li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#messages" role="tab"><span class="hidden-sm-up"></span> <span class="hidden-xs-down">Tab3</span></a> </li>
                                </ul>
                                <!-- Tab panes -->
                                <div class="tab-content tabcontent-border">
                                    <div class="tab-pane active" id="home" role="tabpanel">
                                        <div class="p-20">
                                            <p>And is full of waffle to It has multiple paragraphs and is full of waffle to pad out the comment. Usually, you just wish these sorts of comments would come to an end.multiple paragraphs and is full of waffle to pad out the comment..</p>
                                            <img src="assets/images/background/img4.jpg" class="img-fluid">
                                        </div>
                                    </div>
                                    <div class="tab-pane  p-20" id="profile" role="tabpanel">
                                        <div class="p-20">
                                            <img src="assets/images/background/img4.jpg" class="img-fluid">
                                            <p class="m-t-10">And is full of waffle to It has multiple paragraphs and is full of waffle to pad out the comment. Usually, you just wish these sorts of comments would come to an end.multiple paragraphs and is full of waffle to pad out the comment..</p>
                                        </div>
                                    </div>
                                    <div class="tab-pane p-20" id="messages" role="tabpanel">
                                        <div class="p-20">
                                            <p>And is full of waffle to It has multiple paragraphs and is full of waffle to pad out the comment. Usually, you just wish these sorts of comments would come to an end.multiple paragraphs and is full of waffle to pad out the comment..</p>
                                            <img src="assets/images/background/img4.jpg" class="img-fluid">
                                        </div>
                                    </div>
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
        <script src="assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
        <script src="assets/extra-libs/sparkline/sparkline.js"></script>
        <!--Wave Effects -->
        <script src="dist/js/waves.js"></script>
        <!--Menu sidebar -->
        <script src="dist/js/sidebarmenu.js"></script>
        <!--Custom JavaScript -->
        <script src="dist/js/custom.min.js"></script>
        <!--This page JavaScript -->



    </body>

</html>
