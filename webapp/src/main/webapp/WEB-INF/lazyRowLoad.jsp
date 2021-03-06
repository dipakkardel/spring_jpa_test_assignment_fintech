<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
  <script type="text/javascript"
          src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.1.1.min.js"></script>
  <script type="text/javascript"
          src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>

</head>

<body>

<div class="container">
  <div class="panel panel-default">
    <div class="panel-heading">
      <h3 class="panel-title">
        Test JS validation
      </h3>
    </div>
    <div class="panel-body">
      <form action="lazyRowAdd.web" class="form-horizontal" method="post" id="lazyList">
      	<div class="form-group">
      		<div class="col-sm-5">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		        <input type="hidden" name="acc_id" value="${theWatchListDesc.account.getId()}"/>
		    </div>
	    </div>
        
        <div class="form-group">
          <label class="col-sm-4 control-label" for="watchListName">watchListName</label>
          <div class="col-sm-5">
            <input class="form-control" name="watchListName" id="watchListName" value="watch List Name" />
          </div>
        </div>
        
        <div class="form-group">
          <label class="col-sm-4 control-label" for="watchListDetails">watchListDetails</label>
          <div class="col-sm-5">
            <input class="form-control" name="watchListDetails" id="watchListDetails" value="watch List Details" />
          </div>
        </div>
        
        <div class="form-group">
          <label class="col-sm-4 control-label" for="marketDataFrequency">marketDataFrequency</label>
          <div class="col-sm-5">
            <input class="form-control" name="marketDataFrequency" id="marketDataFrequency" value="1" type="number" />
          </div>
        </div>
		<div class="form-group">
			<div class="col-sm-5" >
				<input type="button" value="Add Row" onclick="addRow('dataTable')"/>
	          	<input type="button" value="Delete Row" onclick="deleteRow('dataTable')"/>
	       	</div>
		</div>
          
          <div class="form-group">
            <table id="dataTable" border="1">
              <tr>
                <td><input type="checkbox" name="chk"/></td>
                <td>1</td>
                <td><input name="operationParameterses[0].name" class="name"/></td>
              </tr>
            </table>
          </div>

        <fieldset><button>Save User Details!</button></fieldset>
      </form>
    </div>
  </div>

</div>


</body>
</html>

<script type="text/javascript">

    function addRow(tableID) {

        var table = document.getElementById(tableID);

        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);

        var cell1 = row.insertCell(0);
        var element1 = document.createElement("input");
        element1.type = "checkbox";
        element1.name = "chkbox[]";
        cell1.appendChild(element1);

        var cell2 = row.insertCell(1);
        cell2.innerHTML = rowCount + 1;
        var cell3 = row.insertCell(2);
        var element2 = document.createElement("input");
        element2.type = "text";
        var length = (table.rows.length) - 1;
        // element2.setAttribute("name","username1" );
        element2.name = "operationParameterses[" + length + "].name";
        element2.className = "name";
        element2.setAttribute("path", "name");
        cell3.appendChild(element2);

        $('.name').each(function () {
            $(this).rules("add",
                {
                    required: true,
                    minlength: 1,
                    email: true
                })
        });
    }

    function deleteRow(tableID) {
        try {
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;

            for (var i = 0; i < rowCount; i++) {
                var row = table.rows[i];
                var chkbox = row.cells[0].childNodes[0];
                if (null !== chkbox && true === chkbox.checked) {
                    table.deleteRow(i);
                    rowCount--;
                    i--;
                }
            }
        } catch (e) {
            alert(e);
        }
    }

    $(document).ready(function () {


        $('#lazyList').validate({

            rules: {
                name: {
                    required: true,
                    minlength: 2
                },
                username1: {
                    required: true,
                    minlength: 2
                },
                email1: {
                    required: true,
                    email: true
                }
            }
        });
        $('.name').each(function () {
            $(this).rules("add",
                {
                    required: true,
                    minlength: 2
                })
        });
        $(".table").rules("add", {
            required: true,
            minlength: 3
        });
    });
</script>
