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
            <td>Subject Name</td>
            <td>Subject description</td>
            <td>Subject group id</td>
            <td>Subject credits</td>
        </tr>
        </thead>
        <tbody class="pure-table-odd">
        <c:forEach var="subject" items="${subjects}">
            <tr style="border: black">
                <td>${subject.getId()}</td>
                <td>${subject.getName()}</td>
                <td>${subject.getDescription()}</td>
                <td>${subject.getGroup()}</td>
                <td>${subject.getCredits()}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="pure-u-2-5">
    <form class="pure-form pure-form-stacked pure-u-2-5" action="<c:url value="/subject"/>" method="POST">
        Subject name <label>
        <input type="text" name="subject_name">
    </label>
        Subject description <label>
        <input type="text" name="subject_description">
    </label>
        Subject group id <label>
        <input type="text" name="subject_group">
    </label> Subject credits <label>
        <input type="text" name="subject_credits">
    </label>
        <input type="submit" value="Submit"/>
    </form>
</div>

<div>
    <a href="index.jsp">Home</a>
</div>

</body>
</html>
