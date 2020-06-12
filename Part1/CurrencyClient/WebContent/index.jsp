<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.util.List" %>
<html>
<head>
<meta charset="UTF-8">
<title>My lab 4</title>
</head>
<body>
<form method="POST" action="">
	Оберіть валюту:
        <select id="currency1" name="currency1">
            <option value="UAH">₴ UAH</option>
            <option value="USD">$ USD</option>
            <option value="EUR">€ EUR</option>
          </select>
          <select id="currency2" name="currency2">
            <option value="USD">$ USD</option>
            <option value="EUR">€ EUR</option>
            <option value="UAH">₴ UAH</option>
          </select>
          <input type="submit" value="Дізнатися курс">
</form>
	<%
			List banks = (List)request.getAttribute("banksInfo"); 
			if(banks!=null)
			{
				out.println("<table border ='1'>");
				for(int i=0;i<banks.size();i++)
				{
					out.println("<tr><td>"+banks.get(i)+"</td></tr>");
				}
				out.println("</table>");
			}
	%>
</body>
</html>