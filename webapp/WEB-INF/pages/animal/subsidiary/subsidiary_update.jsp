<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="../../templates/header.jsp"/>
<div class="container">
    <form:form method="POST" action="/subsidiary/update"
               modelAttribute="subsidiary" class="form-signin" >
        <h2 class="form-signin-heading">Обновить семейство</h2>
        <!--                          Subsidiary subsidiary                          -->
        <h3>Основная информация</h3>
        <div class="form-model">
            <spring:bind path="name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="name" class="form-control"
                                placeholder="Наименование"/>
                    <form:errors path="name" delimiter=" "/>
                </div>
            </spring:bind>
            <spring:bind path="content">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="content" class="form-control"
                                placeholder="Описание"/>
                    <form:errors path="content" delimiter=" "/>
                </div>
            </spring:bind>
        </div>
        <br/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Отправить" style="width: auto" class="btn btn-lg btn-primary btn-block"/>
    </form:form>
</div>
<c:import url="../../templates/footer.jsp"/>
