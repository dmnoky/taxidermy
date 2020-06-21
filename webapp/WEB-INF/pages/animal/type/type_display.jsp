<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:import url="../../templates/header.jsp"/>
<div class="model-view">
    <sec:authorize access="hasRole('ADMIN')">
        <div class="admin-model-menu">
            <a href="/type/update/${type.id}"><button>Изменить</button></a>
            <form action="<c:url value="/type/remove"/>" method="POST">
                <input id="remove-type-id" type="hidden" name="id" value="${type.id}">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="Удалить"
                    onclick="return confirm('Вы точно хотите удалить эту страницу?')">
            </form>
        </div>
    </sec:authorize>
    <h1>Семейство</h1>
    <h2>Основная информация</h2>
    <div class="form-model">
        <h3>ID</h3>
        <p>${type.id}</p>
        <h3>Наименование</h3>
        <p>${type.name}</p>
        <h3>Описание</h3>
        <p>${type.content}</p>
    </div>
</div>
<c:import url="../../templates/footer.jsp"/>
