<%--
  Created by IntelliJ IDEA.
  User: lucas
  Date: 28/04/2023
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <%@ include file="/WEB-INF/views/common/header.jsp" %>
  <!-- Left side column. contains the logo and sidebar -->
  <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Utilisateur
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-12">
          <!-- Horizontal Form -->
          <div class="box">
            <!-- form start -->
            <form class="form-horizontal" method="post" action="/rentmanager/users/update">
              <div class="box-body">
                <div class="form-group">
                  <label for="lastName" class="col-sm-2 control-label">Nom</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="lastName" name="lastName" value="${client.lastName}">
                  </div>
                </div>
                <div class="form-group">
                  <label for="firstName" class="col-sm-2 control-label">Prenom</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="firstName" name="firstName" value="${client.firstName}">
                  </div>
                </div>
                <div class="form-group">
                  <label for="email" class="col-sm-2 control-label">Email</label>
                  <div class="col-sm-10">
                    <input type="email" class="form-control" id="email" name="email" value="${client.email}">
                  </div>
                </div>
                <div class="form-group">
                  <label for="birthDate" class="col-sm-2 control-label">Date de birthDate</label>

                  <div class="col-sm-10">
                    <input type="date" class="form-control" id="birthDate" name="birthDate" required value="${client.birthDate}">
                  </div>
                </div>
              </div>
              <!-- /.box-body -->
              <div class="box-footer">
                <button type="submit" class="btn btn-info pull-right">Modifier</button>
              </div>
              <!-- /.box-footer -->
            </form>
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
    </section>
    <!-- /.content -->
  </div>

  <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script>
  $(function () {
    $('[data-mask]').inputmask()
  });
</script>
</body>
</html>