<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>


<div id="wrapper">
  <div id="header">
    <h2>Edit Account</h2>
  </div>
</div>

<div id="container">
  <h3>Add Account</h3>


  <div class="container">

    <form method="post" modelAttribute="account" action="edit-account" >
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      <fieldset class="form-group">
        <input type="hidden" class="form-control" name="id" value="${account.id}"
                    required="required"/>
      </fieldset>
      <fieldset class="form-group">
        <label path="accountName">Account Name</label>
        <input type="text" class="form-control" name="accountName" value="${account.accountName}"
                    required="required"/>
        <errors path="accountName" cssClass="text-warning"/>
      </fieldset>
      <fieldset class="form-group">
        <label path="email">email</label>
        <input path="email" type="text" class="form-control" name="email" value="${account.email}"
                    required="required"/>
        <errors path="email" cssClass="text-warning"/>
      </fieldset>
      <fieldset class="form-group">
        <label path="additionalInfo">additionalInfo</label>
        <input path="additionalInfo" type="text" class="form-control" name="additionalInfo" value="${account.additionalInfo}"
                    required="required"/>
        <errors path="additionalInfo" cssClass="text-warning"/>
      </fieldset>
      <button class="btn btn-success">Submit</button>
      <button class="btn btn-cancel" formmethod="get" formaction="admin/list-accounts" >Cancel</button>
    </form>
  </div>


  <div style="clear: both;"></div>

  <p><a href="login/login.jsp">Back to login</a></p>
</div>

<%@ include file="common/footer.jspf" %>

<script>
    $('#creationDate').datepicker({
        format: 'dd-mm-yyyy'
    });
</script>









