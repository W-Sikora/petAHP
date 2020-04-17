<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<div class="form-group">
    <form action="<c:url value="/panel/ankieta-tworzenie/krok-3"/>" method="post">
        <c:forEach begin="1" end="${noOfAnimals}" varStatus="i">
            <label>Nazwa/gatunek ${i.index}-ego zwierzęcia</label>
            <input type="text" class="form-control" name="animal-name" placeholder="np. Owczarek niemiecki" min="1"
                   max="50" required/>
        </c:forEach>
        <button type="submit" class="btn btn-dark">dalej</button>
    </form>
</div>
</body>
</html>
