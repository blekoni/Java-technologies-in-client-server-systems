<%--
  Created by IntelliJ IDEA.
  User: anduwik
  Date: 08.04.2020
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h2 >Бардан Андрій</h2>
  <h3 >Варіант 11</h3>
  <p >Здійснити форматування вибраного користувачем текстового файла, так щоб усі абзаци мали відступ рівно 3 пробіли, а довжина кожного рядка була рівно 80 символів і не мала початковим і кінцевим символами пробіл.

  </p>
  File: <br />
  <form action="${pageContext.request.contextPath}/UploadServlet" method="post"
        enctype="multipart/form-data">
    <input type="file" name="guru_file" size="50" />
    <br />
    <input type="submit" value="Upload" />
  </form>
  </body>
</html>
