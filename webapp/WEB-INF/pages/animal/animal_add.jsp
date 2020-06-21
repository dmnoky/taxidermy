<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="../templates/header.jsp"/>
<div class="container">
    <form:form method="POST" action="/animal/add" modelAttribute="animal"
            enctype="multipart/form-data" class="form-signin">
        <h2 class="form-signin-heading">Добавить товар</h2>
        <!--                          Animal animal                          -->
        <h3>Основная информация</h3>
        <div class="form-model">
            <spring:bind path="name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="name" class="form-control"
                                placeholder="Наименование"/>
                    <form:errors path="name" delimiter=" "/>
                </div>
            </spring:bind>
            <spring:bind path="subsidiary">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:select path="subsidiary.id" class="form-control">
                        <form:options items="${subsidiaryList}" itemValue="id"
                                      itemLabel="name"/>
                    </form:select>
                    <form:errors path="subsidiary.id" delimiter=" "/>
                </div>
            </spring:bind>
            <spring:bind path="type">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:select path="type.id" class="form-control">
                        <form:options items="${typeList}" itemValue="id"
                                      itemLabel="name"/>
                    </form:select>
                    <form:errors path="type.id" delimiter=" "/>
                </div>
            </spring:bind>
            <spring:bind path="article">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="article" class="form-control"
                                placeholder="Номер"/>
                    <form:errors path="article" delimiter=" "/>
                </div>
            </spring:bind>
            <spring:bind path="number">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="number" class="form-control"
                                placeholder="Количество"/>
                    <form:errors path="number" delimiter=" "/>
                </div>
            </spring:bind>
            <spring:bind path="price">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="price" class="form-control"
                                placeholder="Цена"/>
                    <form:errors path="price" delimiter=" "/>
                </div>
            </spring:bind>
            <spring:bind path="deposit">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="deposit" class="form-control"
                                placeholder="Аванс"/>
                    <form:errors path="deposit" delimiter=" "/>
                </div>
            </spring:bind>
            <spring:bind path="content">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:textarea path="content" class="form-control"
                                placeholder="Описание"/>
                    <form:errors path="content" delimiter=" "/>
                </div>
            </spring:bind>
        </div>
        <h3>Свойства</h3>
        <button class="button-outside-form" style="display:none" type="button" onClick=
                    "$('#hidden-form-properties').show(); $('#button-outside-properties').hide();"
                    id="button-outside-properties">Открыть</button>
        <div class="form-model" id="hidden-form-properties">
            <spring:bind path="width">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="width" class="form-control"
                                placeholder="Ширина"/>
                    <form:errors path="width" delimiter=" "/>
                </div>
            </spring:bind>
            <spring:bind path="height">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="height" class="form-control"
                                placeholder="Высота"/>
                    <form:errors path="height" delimiter=" "/>
                </div>
            </spring:bind>
            <spring:bind path="weight">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="weight" class="form-control"
                                placeholder="Вес"/>
                    <form:errors path="weight" delimiter=" "/>
                </div>
            </spring:bind>
        <button class="button-outside-form" type="button" onClick=
                        "$('#hidden-form-properties').hide();
                        $('#button-outside-properties').show();">Закрыть</button>
        </div>
        <!--                     List<Product> products                     -->
        <h3>Комплектующие</h3>
            <button class="button-outside-form" style="display:none" type="button" onClick=
                    "$('#hidden-form-products').show(); $('#button-outside-products').hide();"
                    id="button-outside-products">Открыть</button>
        <div class="form-model" id="hidden-form-products">
            <h4>Выбрать из текущего списка по ID</h4>
                <spring:bind path="products">
                    <form:input type="text" path="products[0].id" class="form-control"
                                placeholder="ID 1"/>
                    <form:errors path="products[0].id" delimiter=" " cssClass="has-error"/>
                    <button class="button-inside-form" type="button" onClick=
                            "$('#hidden-inform-products-1').show(); $('#button-inform-products-1').hide();"
                            id="button-inform-products-1">Новое поле</button>
                    <c:forEach var="i" begin="1" end="4">
                        <div style="display:none" id="hidden-inform-products-${i}">
                            <form:input type="text" path="products[${i}].id" class="form-control"
                                        placeholder="ID ${i+1}"/>
                            <form:errors path="products[${i}].id" delimiter=" " cssClass="has-error"/>
                            <button class="button-inside-form" type="button" onClick=
                                    "$('#hidden-inform-products-${i+1}').show(); $('#button-inform-products-${i+1}').hide();"
                                    id="button-inform-products-${i+1}">Новое поле</button>
                        </div>
                    </c:forEach>
                </spring:bind>
            <h4>Создать</h4>
            <div><a href="/product/add" target="_blank">Переход на форму</a></div>
                <button class="button-outside-form" type="button" onClick=
                        "$('#hidden-form-products').hide();
                        $('#button-outside-products').show();">Закрыть</button>
        </div>
        <!--                          List<byte[]> images                          -->
        <h3>Изображения</h3>
        <button class="button-outside-form" style="display:none" type="button" onClick=
                "$('#hidden-form-images').show(); $('#button-outside-images').hide();"
                id="button-outside-images">Открыть</button>
        <div class="form-model" id="hidden-form-images">
            <spring:bind path="images">
                <input type="file" name="images" />
                <div class="has-error">${fileErrors.get('images[0]')}</div>
                <input type="file" name="images" />
                <div class="has-error">${fileErrors.get('images[1]')}</div>
                <input type="file" name="images" />
                <div class="has-error">${fileErrors.get('images[2]')}</div>
                <input type="file" name="images" />
                <div class="has-error">${fileErrors.get('images[3]')}</div>
            </spring:bind>
            <button class="button-outside-form" type="button" onClick=
                    "$('#hidden-form-images').hide(); $('#button-outside-images').show();">
                Закрыть
            </button>
        </div>
        <!--                          List<String> notes                          -->
        <h3>Заметки</h3>
        <button class="button-outside-form" style="display:none" type="button" onClick=
                "$('#hidden-form-notes').show(); $('#button-outside-notes').hide();"
                id="button-outside-notes">Открыть</button>
        <div class="form-model" id="hidden-form-notes">
            <spring:bind path="notes">
                <form:input type="text" path="notes[0]" class="form-control" placeholder="1"/><br/>
                <form:input type="text" path="notes[1]" class="form-control" placeholder="2"/><br/>
                <form:input type="text" path="notes[2]" class="form-control" placeholder="3"/><br/>
                <form:input type="text" path="notes[3]" class="form-control" placeholder="4"/><br/>
            </spring:bind>
            <button class="button-outside-form" type="button" onClick=
                    "$('#hidden-form-notes').hide(); $('#button-outside-notes').show();">
                Закрыть
            </button>
        </div>
        <br/><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Отправить" style="width: auto" class="btn btn-lg btn-primary btn-block"/>
    </form:form>
</div>
<c:import url="../templates/footer.jsp"/>