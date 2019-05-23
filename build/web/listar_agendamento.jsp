<script type="text/javascript">
    function confirmarExclusao(id, login) {
        if (confirm('Deseja realmente excluir o agendamento ' + login + '?')) {
            location.href = 'gerenciar_agendamento.do?acao=excluir&idagendamento=' + id;
        }
    }
</script>  

<div class="container">
    <h3>Lista de Agendamentos</h3>  
    <a href="form_agendamento.jsp" 
       class="btn btn-primary">Novo Cadastro</a>
    <p>
    <table class="table table-striped table-bordered 
           table-hover display" id="listaAgendamento">
        <thead>
            <tr>
                <th>ID</th>
                <th>Data</th>
                <th>Status</th>
                <th>Serviço</th>
                <th>Valor</th>
                <th>Cliente</th>
                <th>Funcionário</th>
                <th>Opções</th>
            </tr>
        </thead>

        <jsp:useBean class="model.Agendamento" id="agendamento"/>
        <tbody>
        <c:forEach var="a" items="${agendamento.lista}">
            <tr>
                <td>${a.idagendamento}</td>
                <td>${a.data}</td>
                <td>
            <c:if test="${a.status==1}">Marcado</c:if>
            <c:if test="${a.status==2}">Realizado</c:if>
            </td>
            <td><c:forEach var="v" items="${a.vinculado}">${v.nome_servico}</c:forEach></td>
            <td>${a.valor}</td>
            <td>${a.cliente.nome}</td>
            <td>${a.funcionario.nome}</td>
            <td>
                <a class="btn btn-primary" href="gerenciar_agendamento.do?acao=alterar&idagendamento=${a.idagendamento}">
                    <i class="glyphicon glyphicon-pencil">sdfdsf</i>
                </a>
                <button class="btn btn-danger" onclick="confirmarExclusao(${a.idagendamento}, '${a.idagendamento}')">
                    <i class="glyphicon glyphicon-trash"></i>
                </button>    
            </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>   
</div>

<script type="text/javascript" src="../datatables/jquery.js"></script>
<script type="text/javascript" src="../datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript">
                    $(document).ready(function () {
                        $("#listaAgendamento").dataTable({
                            "bJQueryUI": true,
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

