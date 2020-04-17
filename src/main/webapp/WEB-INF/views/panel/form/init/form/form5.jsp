<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<div class="form-group">
    <form action="<c:url value="/panel/ankieta-tworzenie/krok-6"/>" method="post">
        <label>Nazwa kryterium</label>
        <input type="text" class="form-control" id="name" name="name"
               placeholder="np. " required>
        <label>Liczba subkryteriów</label>
        <input type="number" class="form-control" id="noOfSubCriterion" name="noOfSubCriterion"
               placeholder="od 0 do 5" required>
        <button type="submit" class="btn btn-dark">dalej</button>
    </form>
</div>
</body>
</html>
