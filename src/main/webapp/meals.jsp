<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Meals</title>
    <link rel="icon" href="favicon.ico" type="image/x-icon">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<form method="post" id="saveButton"></form>
<form method="get" id="editButton"></form>
<form method="post" id="deleteButton"></form>
<form method="get" id="cancelButton"></form>
<form method="get" id="createButton"></form>
<form method="post" id="addButton"></form>

<table>
    <tr>
        <th>id</th>
        <th>dateTime</th>
        <th>description</th>
        <th>calories</th>
    </tr>

    <c:forEach items="${requestScope.meals}" var="meal">
        <tr style="background-color:${meal.excess ? 'IndianRed' : 'Green'}">
            <c:choose>
                <c:when test="${requestScope.edit == meal.id}">
                    <td>${meal.id}
                    <td><label><fmt:parseDate pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" value="${meal.dateTime}"/>
                        <input form="saveButton" type="datetime-local" name="dateTime" value="<fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${parsedDateTime}"/>"></label>
                    <td><label><input form="saveButton" type="text" name="description" value="${meal.description}"></label>
                    <td><label><input form="saveButton" type="text" name="calories" value="${meal.calories}"></label>
                    <td><button form="saveButton" type="submit" name="save" value="${meal.id}">Сохранить</button>
                    <td><button form="cancelButton" type="submit">Отмена</button>
                </c:when>

                <c:otherwise>
                    <td>${meal.id}
                    <td><fmt:parseDate pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" value="${meal.dateTime}"/>
                        <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${parsedDateTime}"/>
                    <td>${meal.description}
                    <td>${meal.calories}
                    <td><button form="editButton" type="submit" name="edit" value="${meal.id}">Редактировать</button>
                    <td><button form="deleteButton" type="submit" name="delete" value="${meal.id}">Удалить</button>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>

    <c:if test="${requestScope.create == 0}">
        <tr style="background-color:DarkKhaki">
            <td>0
            <td><label><jsp:useBean id="now" class="java.util.Date"/>
                <input form="addButton" type="datetime-local" name="dateTime" value="<fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${now}"/>"></label>
            <td><label><input form="addButton" type="text" name="description" value=""></label>
            <td><label><input form="addButton" type="text" name="calories" value=""></label>
            <td><button form="addButton" type="submit" name="add" value="0">Добавить</button>
            <td><button form="cancelButton" type="submit">Отмена</button>
        </tr>
    </c:if>
</table>
<button form="createButton" type="submit" name="create" value="0">Создать</button>
</body>
</html>