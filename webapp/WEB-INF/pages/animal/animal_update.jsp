<%@ page import="com.dmnoky.taxidermy.model.animal.Animal" %>
<%@ page import="com.dmnoky.taxidermy.model.product.Product" %>
<%@ page import="org.springframework.web.multipart.commons.CommonsMultipartFile" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="../templates/header.jsp"/>
<div class="container">
    <form:form method="POST" action="/animal/update" modelAttribute="animal"
               enctype="multipart/form-data" class="form-signin" >
        <h2 class="form-signin-heading">Обновить товар</h2>
        <!--                          Animal animal                          -->
        <h3>Основная информация</h3>
        <div class="form-model">
            <spring:bind path="id">
                    <form:input type="hidden" path="id"/>
            </spring:bind>
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
                    <form:input type="hidden" path="article"/>
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
            <c:set var="size" value="${animal.products.size()}"/>
            <c:if test="${size > 0}">
                <c:forEach var="i" begin="0" end="${size-1}">
                    <spring:bind path="products[${i}].id">
                        <div id="hidden-inform-products-${i}">
                            <form:input type="text" path="products[${i}].id" class="form-control"
                                        placeholder="ID ${i+1}"/>
                            <form:errors path="products[${i}].id" delimiter=" " cssClass="has-error"/>
                        </div>
                    </spring:bind>
                </c:forEach>
            </c:if>
            <% ((Animal) request.getAttribute("animal")).getProducts().add(new Product()); %>
            <c:forEach var="i" begin="${size}" end="${size+2}">
                <% ((Animal) request.getAttribute("animal")).getProducts().add(new Product()); %>
                <spring:bind path="products[${i}].id">
                    <form:input type="text" path="products[${i}].id" class="form-control"
                                placeholder="ID ${i+1}"/>
                    <form:errors path="products[${i}].id" delimiter=" " cssClass="has-error"/>
                </spring:bind>
            </c:forEach>

            <!--
                <spring:bind path="products[${size}].id">
                <form:input type="text" path="products[${size}].id" class="form-control"
                            placeholder="ID ${size+1}"/>
                <form:errors path="products[${size}].id" delimiter=" " cssClass="has-error"/>
                <button class="button-inside-form" type="button" onClick=
                        "$('#hidden-inform-products-${size+1}').show(); $('#button-inform-products-${size+1}').hide();"
                        id="button-inform-products-${size+1}">Новое поле</button>
                </spring:bind>
                <c:forEach var="i" begin="${size+1}" end="${size+7}">
                <% ((Animal) request.getAttribute("animal")).getProducts().add(new Product()); %>
                <spring:bind path="products[${i}].id">
                    <div style="display:none" id="hidden-inform-products-${i}">
                        <form:input type="text" path="products[${i}].id" class="form-control"
                                    placeholder="ID ${i+1}"/>
                        <form:errors path="products[${i}].id" delimiter=" " cssClass="has-error"/>
                        <button class="button-inside-form" type="button" onClick=
                                "$('#hidden-inform-products-${i+1}').show(); $('#button-inform-products-${i+1}').hide();"
                                id="button-inform-products-${i+1}">Новое поле</button>
                    </div>
                </spring:bind>
            </c:forEach>
-->

            <h4>Создать</h4>
            <div><a href="/product/add" target="_blank">Переход на форму</a></div>
            <button class="button-outside-form" type="button" onClick=
                    "$('#hidden-form-products').hide();
                            $('#button-outside-products').show();">Закрыть</button>
        </div>
        <!--                     List<Client> clients                     -->
        <c:if test="${animal.clients.size() > 0}">
            <c:forEach var="i" begin="0" end="${animal.clients.size()-1}">
                <spring:bind path="clients[${i}]">
                    <form:input type="hidden" path="clients[${i}].id"/>
                </spring:bind>
            </c:forEach>
        </c:if>
        <!--                          List<byte[]> images                          -->
        <h3>Изображения</h3>
        <button class="button-outside-form" style="display:none" type="button" onClick=
                "$('#hidden-form-images').show(); $('#button-outside-images').hide();"
                id="button-outside-images">Открыть</button>
        <c:if test="${animal.images.size() == 0}">
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
        </c:if>

        <!--                          List<String> notes                          -->
        <h3>Заметки</h3>
        <button class="button-outside-form" style="display:none" type="button" onClick=
                "$('#hidden-form-notes').show(); $('#button-outside-notes').hide();"
                id="button-outside-notes">Открыть</button>
        <div class="form-model" id="hidden-form-notes">
            <c:set var="size" value="${animal.notes.size()}"/>
            <c:if test="${size > 0}">
                <spring:bind path="notes">
                    <c:forEach var="i" begin="0" end="${size-1}">
                        <form:input type="text" path="notes[${i}]" class="form-control"
                                    placeholder="${i+1}"/><br/>
                    </c:forEach>
                </spring:bind>
            </c:if>
            <c:forEach var="i" begin="${size}" end="${size+2}">
                <% ((Animal) request.getAttribute("animal")).getNotes().add(""); %>
                <spring:bind path="notes[${i}]">
                    <form:input type="text" path="notes[${i}]" class="form-control"
                                placeholder="${i+1}"/><br/>
                </spring:bind>
            </c:forEach>
            <button class="button-outside-form" type="button" onClick=
                    "$('#hidden-form-notes').hide(); $('#button-outside-notes').show();">
                Закрыть
            </button>
        </div>
        <br/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Отправить" style="width: auto" class="btn btn-lg btn-primary btn-block"/>
    </form:form>
</div>
<c:import url="../templates/footer.jsp"/>
