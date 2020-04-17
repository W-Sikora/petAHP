<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<div class="form-group">
    <form:form action="/panel/ankieta-tworzenie/krok-1" method="post" modelAttribute="poll">
        <form:input path="creator" name="creator" value="${currentUser.id}" hidden="true"/>
        <form:input path="creationDate" name="creationDate" value="${currentDate}" hidden="true"/>

        <label>Nazwa ankiety</label>:
        <form:input path="name" name="name" class="form-control" placeholder="np. wybór zwierzaka"
                    type="text" minlength="1" required="true"/>

        <label>Liczba dopuszczonych do ankiety użytkowników</label>
        <form:input path="numberOfVotes" name="numberOfVotes" class="form-control" placeholder="od 2 do 10"
                    type="number" min="2" max="10" required="true"/>

        <label>Data do której można oddać odpowiedź</label>:
        <form:input path="dateToVote" name="dateToVote" class="form-control" type="date" required="true"/>

        <button type="submit" class="btn btn-dark">dalej</button>
    </form:form>
</div>
</body>
</html>
