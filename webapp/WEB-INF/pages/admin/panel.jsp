<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Админ панель</title>
        <base target="_blank"/>
        <link rel="stylesheet" type="text/css" href="/resources/css/admin.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    </head>
    <body class="admin-panel">
        <h1>Админ панель</h1>
        <h2>Добавить</h2>
        <select id="controller-option-selected-add" title="Выберите значение из списка">
            <option value="/animal/add">Товар</option>
            <option value="/client/add">Клиент</option>
            <option value="/worker/add">Работник</option>
            <option value="/product/add">Продукт</option>
            <option value="/subsidiary/add">Семейство</option>
            <option value="/type/add">Тип</option>
        </select>
        <button onclick="location.href = document.getElementById('controller-option-selected-add').value">
            Добавить
        </button>

        <h2>Выбрать</h2>
        <select id="controller-option-selected-get" onchange="valueOptionShow()" title="Выберите значение из списка">
            <option value="">------</option>
            <option value="/animal/">Товар</option>
            <option value="/client/">Клиент</option>
            <option value="/worker/">Работник</option>
            <option value="/product/">Продукт</option>
        </select>
        <select id="value-option-selected" title="Выберите тип значения">
          
        </select>
        <input type="text" name="Значение" placeholder="Значение" id="main-input-value" title="Введите значение"/>

        <button onclick="valueCheck()">
            Выбрать
        </button>
        
        <h2>Список товаров</h2>
        <c:choose>
            <c:when test = "${animalTheLastTen.size() > 0}">
                <a href="<c:url value="/animal/list/1"/>"><button>Просмотреть весь список</button></a>
                <h3>Последние 10 товаров</h3>
                <ul>
                    <c:forEach var="animal" items="${animalTheLastTen}">
                        <li><a href="/animal/id/${animal.id}" target="_blank">${animal.name}</a></li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <p>Товаров нет</p>
            </c:otherwise>
        </c:choose>
        <h2>Список клиентов</h2>
        <c:choose>
            <c:when test = "${clientTheLastTen.size() > 0}">
                <a href="<c:url value="/client/list/1"/>"><button>Просмотреть весь список</button></a>
                <h3>Последние 10 клиентов</h3>
                <ul>
                    <c:forEach var="client" items="${clientTheLastTen}">
                        <li><a href="/client/id/${client.id}" target="_blank">${client.name}</a></li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <p>Клиентов нет</p>
            </c:otherwise>
        </c:choose>
        <h2>Список продуктов</h2>
        <c:choose>
            <c:when test = "${productTheLastTen.size() > 0}">
                <a href="<c:url value="/product/list/1"/>"><button>Просмотреть весь список</button></a>
                <h3>Последние 10 продуктов</h3>
                <ul>
                    <c:forEach var="product" items="${productTheLastTen}">
                        <li><a href="/product/${product.id}" target="_blank">${product.name}</a></li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <p>Продуктов нет</p>
            </c:otherwise>
        </c:choose>
        <h2>Список семейств и типов товара</h2>
        <a href="<c:url value="/subsidiary/list"/>"><button>Семейства</button></a>
        <a href="<c:url value="/type/list"/>"><button>Типы</button></a>
        <h2>Работники</h2>
        <c:choose>
            <c:when test = "${workers.size() > 0}">
                <ul>
                    <c:forEach var="worker" items="${workers}">
                        <li><a href="/worker/id/${worker.id}" target="_blank">${worker.name}</a></li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <p>Работников нет</p>
            </c:otherwise>
        </c:choose>

        <script>
            var animalOptions = "<option value='id/'>ID</option><option value='article/'>Номер</option>";
            var userOptions = "<option value='id/'>ID</option><option value='article/'>Номер</option>";
            var productOptions = "<option value='id/'>ID</option>";

            function valueOptionShow() {
                switch (document.getElementById('controller-option-selected-get').value) {
                    case  "/animal/":
                        document.getElementById("value-option-selected").innerHTML = animalOptions;
                        break;
                    case  "/client/":
                        document.getElementById("value-option-selected").innerHTML = userOptions;
                        break;
                    case  "/worker/":
                        document.getElementById("value-option-selected").innerHTML = userOptions;
                        break;
                    case  "/product/":
                        document.getElementById("value-option-selected").innerHTML = productOptions;
                        break;
                }
            }
            function valueCheck() {
                if (document.getElementById('controller-option-selected-get').value === "") { return alert("Выберите значение из списка!"); }
                if (document.getElementById('value-option-selected').value === "") { return alert("Выберите значение из списка!"); }
                if (document.getElementById('main-input-value').value === "") { return alert("Введите значение!"); }
                else return location.href = 
                    document.getElementById('controller-option-selected-get').value + 
                    document.getElementById('value-option-selected').value +
                    document.getElementById('main-input-value').value;
            }
        </script>
    </body>
</html>
