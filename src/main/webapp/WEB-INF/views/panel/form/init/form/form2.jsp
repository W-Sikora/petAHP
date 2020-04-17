<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<div class="form-group">
    <form action="<c:url value="/panel/ankieta-tworzenie/krok-2"/>" method="post">
        <label>Liczba rozpatrywanych zwierząt</label>
        <input type="number" class="form-control" id="numberOfAnimals" name="numberOfAnimals"
               placeholder="od 2 do 10" min="2" max="10" required>
        <button type="submit" class="btn btn-dark">dalej</button>
    </form>
</div>
</body>
</html>
