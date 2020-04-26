<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head></head>
<body>
<style>
    hr {
        margin-top: 15px;
        margin-bottom: 15px;
    }

    p {
        text-align: justify;
        margin-bottom: 15px;
    }

    h2 {
        margin-top: 35px;
        margin-bottom: 25px;
    }

    h3 {
        margin-top: 20px;
        margin-bottom: 20px;
    }

    h5 {
        margin-top: 20px;
        margin-bottom: 20px;
    }


    label {
        width: 100%;
        margin-top: 20px;
    }
    input {
        margin-top: 15px;
    }

    input.invalid {
        background-color: #ffdddd;
    }

    .part {
        display: none;
    }

    .step {
        height: 15px;
        width: 15px;
        margin: 0 2px;
        background-color: #bbbbbb;
        border: none;
        border-radius: 50%;
        display: inline-block;
        opacity: 0.5;
    }

    /* Mark the active step: */
    .step.active {
        opacity: 1;
    }

    /* Mark the steps that are finished and valid: */
    .step.finish {
        background-color: #4CAF50;
    }
</style>
</body>
</html>
