<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:import url="../templates/header.jsp"/>
<div class="model-view">
    <sec:authorize access="hasRole('ADMIN')">
        <div class="admin-model-menu">
            <a href="/animal/update/${animal.id}"><button>Изменить</button></a>
            <form action="<c:url value="/animal/remove"/>" method="POST">
                <input id="remove-animal-id" type="hidden" name="id" value="${animal.id}">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="Удалить"
                    onclick="return confirm('Вы точно хотите удалить эту страницу?')">
            </form>
        </div>
    </sec:authorize>
    <h1>Товар</h1>
    <h2>Основная информация</h2>
    <div class="form-model">
        <sec:authorize access="hasRole('ADMIN')">
            <h3>ID</h3>
            <p>${animal.id}</p>
        </sec:authorize>
        <h3>Наименование</h3>
        <p>${animal.name}</p>
        <h3>Тип</h3>
        <p>${animal.subsidiary.name}</p>
        <p>${animal.type.name}</p>
        <h3>Номер</h3>
        <p>${animal.article}</p>
        <h3>Количество</h3>
        <p>${animal.number}</p>
        <h3>Цена</h3>
        <p>${animal.price}</p>
        <h3>Аванс</h3>
        <p>${animal.deposit}</p>
        <h3>Описание</h3>
        <p>${animal.content}</p>
    </div>
    <h2>Свойства</h2>
    <div class="form-model">
        <h3>Ширина</h3>
        <p>${animal.width}</p>
        <h3>Высота</h3>
        <p>${animal.height}</p>
        <h3>Вес</h3>
        <p>${animal.weight}</p>
    </div>
    <sec:authorize access="hasRole('ADMIN')">
        <h2>Клиенты</h2>
        <c:choose>
            <c:when test = "${animal.clients.size() > 0}">
                <div class="form-model">
                    <ul>
                        <c:forEach var="item" items="${animal.clients}">
                            <li><a href="/client/id/${item.id}"
                                target="_blank">${item.surname} ${item.name}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </c:when>
            <c:otherwise>
                <p>Клиентов нет</p>
            </c:otherwise>
        </c:choose>
        <h2>Комплектующие</h2>
        <c:choose>
            <c:when test = "${animal.products.size() > 0}">
                <div class="form-model">
                    <ul>
                        <c:forEach var="item" items="${animal.products}">
                            <li><a href="/product/${item.id}" target="_blank">${item.name}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </c:when>
            <c:otherwise>
                <p>Комплектующих нет</p>
            </c:otherwise>
        </c:choose>
    </sec:authorize>
    <h2>Изображения</h2>
    <c:choose>
        <c:when test = "${animal.images.size() > 0}">
            <div class="form-model">
                <ul>
                    <c:forEach var="item" items="${animal.encodedImages}">
                        <li><img src="data:image/jpg;base64,<c:out value='${item}'/>" alt="img"/></li>
                    </c:forEach>
                </ul>
            </div>
        </c:when>
        <c:otherwise>
            <p>Изображений нет</p>
        </c:otherwise>
    </c:choose>
    <h2>Заметки</h2>
    <c:choose>
        <c:when test = "${animal.notes.size() > 0}">
            <div class="form-model">
                <ul>
                    <c:forEach var="item" items="${animal.notes}">
                        <li>${item}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:when>
        <c:otherwise>
            <p>Заметок нет</p>
        </c:otherwise>
    </c:choose>
</div>
<c:import url="../templates/footer.jsp"/>