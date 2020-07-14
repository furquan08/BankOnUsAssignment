<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<body>
	<div class="container">


		<form:form action="/temperatureConvert" method="post">
			<h2>Temperature Conversion page</h2>
         Property <input type="text" required="required" name="property">
			<br>
			<br>
          Value   <input type="text" required="required" name="val" />
			<br>
			<br>
			<input type="submit" value="Submit">
		</form:form>

	</div>
</body>
<%@ include file="common/footer.jspf"%>