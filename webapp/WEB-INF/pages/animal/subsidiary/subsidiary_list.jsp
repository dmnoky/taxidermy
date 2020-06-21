<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../../templates/header.jsp"/>
<h1>Список</h1>
<ul>
    <c:forEach var="item" items="${subsidiaryList}">
        <li><a href="/subsidiary/${item.id}" target="_blank">${item.name}</a></li>
    </c:forEach>
</ul>
<c:import url="../../templates/footer.jsp"/>
