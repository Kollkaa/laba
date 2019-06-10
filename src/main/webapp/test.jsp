<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <td>User Id</td>
            <td>Student Id</td>
            <td>Subject id</td>
            <td>Mark</td>
        </tr>
        </thead>
        <tbody class="pure-table-odd">
        <c:forEach var="test" items="${tests}">
            <tr style="border: black">
                <td>${test.getId()}</td>
                <td>${test.getUserId()}</td>
                <td>${test.getStudentId()}</td>
                <td>${test.getSubjectId()}</td>
                <td>${test.getMark()}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="pure-u-2-5">
    <form class="pure-form pure-form-stacked pure-u-2-5" action="<c:url value="/test"/>" method="POST">
        User id <label>
        <input type="text" name="user_id" value="${cookie.get("userid").value}" readonly>
    </label>
        Student id <label>
        <input type="text" name="student_id">
    </label>
        Subject id <label>
        <input type="text" name="subject_id">
    </label> Mark <label>
        <input type="text" name="mark">
    </label>
        <input type="submit" value="Submit"/>
    </form>
</div>

<div>
    <a href="index.jsp">Home</a>
</div>


</body>
</html>
