<%-- 
    Document   : index
    Created on : 20/02/2019, 20:48:34
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <%@include  file="required_meta.jsp"%>

        <title> Studio Europa Unhas</title>

        <script src="jquery/jquery-3.4.1.min.js"></script>
        <script>

            var qtd = 0;

            function adicionarAgendamento() {
                if (qtd < 5) {
                    var clone = document.getElementById('informacao').cloneNode(true);
                    $(clone).add("<input type='hidde=' name='agendamento" + (qtd++) + "'>");
                    $("#destino").append(clone);
                    $("input#qtdAgendamento").attr("value",qtd);
                }else{
                    alert("Quantidade máxima de agendamentos!");
                }
            }
            
            

            function removerCampos(id) {
                var node1 = document.getElementById('destino');
                node1.removeChild(node1.childNodes[0]);
            }

        </script>

    </head>
    <body>
        <div class="container">
            <h3>Cadastrar um novo agendamento</h3>  

            <form name="form_agendamento" 
                  action="gerenciar_agendamento.do" method="POST">

                <input type="hidden" name="idagendamento" id="idagendamento"
                       class="form-control" value="${agendamento.idagendamento}"/>
                
                <input type="hidden" name="qtdAgendamento" id="qtdAgendamento"
                       class="form-control" >

                <div id="informacao" style="margin: 2px; background-color: #B0BED9; padding:5px;" >
                    <div class="row">
                        <div class="form-group col-sm-8">
                            <label for="data" class="control-label">
                                Data Desejada
                            </label>
                            <input type="date" name="data" id="data"
                                   class="form-control" required=""
                                   value="${agendamento.data}"/>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-sm-8">
                            <input type="hidden" name="status" id="status" value="1"/>
                        </div>
                    </div>

                    <p>

                    <div class="row">
                        <div class="form-group col-sm-8">
                            <label for="valor" class="control-label">
                                Valor
                            </label>
                            <input type="text" disabled=""  name="valor" id="valor" value="${agendamento.valor}"/>
                        </div>
                    </div>
                    <p>
                    <div class="row">
                        <div class="form-group col-sm-8">
                            <label for="idfuncionario" class="control-label">
                                Funcionário
                            </label>

                            <select id="idfuncionario" name="idfuncionario" required="" class="form-control">
                                <option value="">Selecione o Funcionário</option>
                                <jsp:useBean class="model.Funcionario" id="funcionario"/>
                                <c:forEach var="f" items="${funcionario.lista}">
                                    <option value="${f.idfuncionario}"
                                            <c:if test="${f.idfuncionario==agendamento.funcionario.idfuncionario}">
                                                selected=""
                                            </c:if>        
                                            >${f.nome}</option>
                                </c:forEach>

                            </select>    
                        </div>
                        <p>
                        <div class="form-group col-sm-8">
                            <label for="idcliente" class="control-label">
                                Cliente
                            </label>
                            <select id="idcliente" name="idcliente" required="" class="form-control">
                                <option value="">Selecione o Cliente</option>
                                <jsp:useBean class="model.Cliente" id="cliente"/>
                                <c:forEach var="c" items="${cliente.lista}">
                                    <option value="${c.idcliente}"
                                            <c:if test="${c.idcliente==agendamento.cliente.idcliente}">
                                                selected=""
                                            </c:if>        
                                            >${c.nome}</option>
                                </c:forEach>

                            </select>    

                        </div>
                        <div class="form-group col-sm-8">
                            <label for="idservico" class="control-label">
                                Serviço
                            </label>
                            <select id="idservico" name="idservico" required="" class="form-control">
                                <option value="">Selecione o Serviço</option>
                                <jsp:useBean class="model.Servico" id="servico"/>
                                <c:forEach var="s" items="${servico.lista}">
                                    <option value="${s.idservico}"

                                            >${s.nome_servico}</option>
                                </c:forEach>

                            </select>    
                        </div>
                    </div> 

                </div>
                <div id="destino"></div>

                <div class="row">
                    <button class="btn btn-success" value="submit">Gravar</button>&nbsp;

                    <c:if test="${agendamento.idagendamento==null}">
                        <button id="adicionar"onclick="adicionarAgendamento()" class="btn btn-danger">+</button>&nbsp;
                    </c:if>

                    <a href="listar_agendamento.jsp" class="btn btn-warning">
                        Voltar
                    </a>    
                </div>
            </form>
        </div>


    </body>
</html>

