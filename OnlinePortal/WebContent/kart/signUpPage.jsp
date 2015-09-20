
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<s:form action="login" namespace="/" method="post">
		<table align="center" bgcolor="blue">
			<tr>
				
				<td><s:textfield id="name" type="text" label="Name" name="person.name"  autocomplete="off"></s:textfield></td>
			</tr>
			<tr>
				<td><label id="ageLabel">Age</label></td>
				<td><input type="text" title="Age" name="person.age" id="age" autocomplete="off"/></td>
			</tr>
			<tr>
				<td align="center"><input type="submit" value="Submit" ></td>
			</tr>
		</table>
	</s:form>
</body>
</html>