<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
  <h3>Hi <c:out value="${account.getAccountName()}"/></h3>
  <strong>Your Email</strong>: <c:out value="${account.getEmail()}"/><br>
  <strong>Additional Info</strong>: <c:out value="${account.getAdditionalInfo()}"/><br>
  <img src="${pageContext.servletContext.contextPath}/getImage?accId=${account.getId()}" height="111px" width="111px"/>

  <form modelAttribute="account" action="uploadImage" method="post" enctype="multipart/form-data">
    <input type="file" name="image"/><br/><br/>
    <input type="submit"/>
    <input type="hidden" name="id" value=<c:out value="${account.getId()}"/>>
  </form>
  <strong>Available data sets</strong>:
  <h3>Available DataSets for current account </h3>
  <div>
    <a type="button" class="btn btn-success" href="<c:url value="/add-watchlist?id=${account.getId()}"/>">Add New watchlist</a>
  </div>
  <hr size="4" color="gray"/>

  <table class="table table-striped">
		<thead>
    		<tr>
    			<th>Data Set Name</th>
    			<th>Data Set Id</th>
    			<th>Action</th>
    		</tr>
    	</thead>
    	<tbody>
    		<tr>
			    <c:forEach var="theWatch" items="${watchLists}">
			      <tr>
			        <td>${theWatch.getWatchListName()}  </td>
			        <td>${theWatch.getWatchListId()}   </td>
			
			        <td><a type="button" class="btn btn-primary"
			               href="<c:url value="/view-watchlist?watchListId=${theWatch.watchListId}&accountId=${account.getId()}"/>">View</a>
			            <a type="button" class="btn btn-primary"
			               href="<c:url value="/delete-watchlist?watchListId=${theWatch.watchListId}&accountId=${account.getId()}"/>">Delete</a>
			        </td>
			      </tr>
			
			    </c:forEach>
			 </tr>
		</tbody>

    <!-- todo h make " dataSet name " header of the table "dataset_id" the same or delete it  -->
  </table>
  <br>
  <!-- <form action="Logout" method="post">
    <input type="submit" value="Logout">
  </form> -->

  <form action="search.jsp" method="get">
    <input type="submit" value="search">
  </form>

</div>
<%@ include file="common/footer.jspf" %>