<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: andrii
  Date: 3/1/19
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="css/main.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css"
          integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
</head>
<body>

<div class="pure-u-3-5">
    <table class="pure-table pure-table-bordered pure-u-3-5">
        <thead>
        <tr>
            <td>ID</td>
            <td>Agent Name</td>
            <td>Agency Id</td>

        </tr>
        </thead>
        <tbody class="pure-table-odd">
        <c:forEach var="user" items="${agents}">
            <tr style="border: black">
                <td>${user.getId()}</td>
                <td>${user.getAgentName()}</td>
                <td>${user.getAgencyId()}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="pure-u-2-5">
    <form class="pure-form pure-form-stacked pure-u-2-5" action="<c:url value="/agent"/>" method="POST">
        Agent name <label>
        <input type="text" name="agent_name">
    </label>
        Id of agency <label>
        <input type="text" name="agency_id">
    </label>
        <input type="submit" value="Submit"/>
    </form>
</div>

<div>
    <a href="index.jsp">Home</a>
</div>

</body>
</html>
