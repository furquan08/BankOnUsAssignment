<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
 <body>
<div class="container">
	
   <h1>Uploading file</h1>

        <form:form action="/file/doUpload" method="post" enctype="multipart/form-data">
            <label>Enter file</label>
            <input type="file" name="file" accept=".txt*"><br><br>
            <button type="submit">Upload</button>
        </form:form>
      

</div>
   </body>
<%@ include file="common/footer.jspf"%>