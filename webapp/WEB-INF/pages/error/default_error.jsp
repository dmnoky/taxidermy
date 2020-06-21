<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Упс, ошибка</title>
</head>
<body>
    <h1>Что-то пошло не так...</h1>
    <p>Свяжитесь с разработчиком</p>
    <p>Ошибка:  ${requestScope.get('exception').message}</p>
    <h3>Стек ошибки</h3>
    <ul>
        <c:forEach items="${requestScope.get('exception').stackTrace}" var="ste">
            <li>${ste}</li>
        </c:forEach>
    </ul>
    <a href="/">Вернуться на главную страницу</a>
</body>
</html>
