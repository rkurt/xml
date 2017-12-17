<!doctype html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">

<head>
    <jsp:include page="header.jsp"/>
</head>

<body>

<jsp:include page="navigation.jsp"/>

<main role="main">
    <div class="jumbotron">
        <div class="container">

            <div class="row">
                <h2>Products list - ${productType.name}</h2>
            </div>

            <div class="row">

                <table class="table table-hover">
                    <thead>
                    <tr class="table-primary">
                        <c:forEach items="${productType.fieldsNames}" var="field">
                            <th scope="col">${field}</th>
                        </c:forEach>
                    </tr>
                    </thead>
                    <c:forEach items="${products}" var="product">
                        <tr class="table-light">
                            <c:forEach items="${product.printableValues}" var="item">
                                <td>${item}</td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </table>

                <a class="btn btn-secondary" href="${pageContext.request.contextPath}" role="button">
                    Back
                    <img src="${pageContext.request.contextPath}/resources/open-iconic/action-undo-2x.png"
                         alt="Back">
                </a>

            </div>

        </div>
    </div>
</main>

<jsp:include page="footer.jsp"/>

<jsp:include page="scripts.jsp"/>

</body>

</html>