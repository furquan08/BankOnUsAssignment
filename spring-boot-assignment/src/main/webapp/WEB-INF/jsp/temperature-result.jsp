<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<body>
	<div class="container">


		<div align="left">
			<table border="2" cellpadding="10">
				<caption>Conversion Result</caption>
				<tr>
					<th>Celsius</th>
					<th>Farenheit</th>

				</tr>
				<tr>
					<td><c:out value="${Celsius}" /></td>
					<td><c:out value="${Farenheit}" /></td>

				</tr>
			</table>
		</div>



	</div>
</body>
<%@ include file="common/footer.jspf"%>