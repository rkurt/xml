<!doctype html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--@elvariable id="message" type="java.lang.String"--%>
<html lang="en">

<head>
    <jsp:include page="header.jsp"/>
</head>

<body>

<jsp:include page="navigation.jsp"/>

<main role="main">
    <div class="jumbotron">
        <div class="container">
            <form method="POST" action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data">
                <div class="form-group">
                    <div class="row">
                        <h2><label for="file">XML file input</label></h2>
                    </div>
                    <div class="row alert alert-danger" role="alert" id="message"
                         <c:if test="${empty message}">hidden</c:if>>
                        ${message}
                    </div>
                    <div class="row">
                        <label class="custom-file">
                            <input type="file" id="file" name="file" class="custom-file-input">
                            <span class="custom-file-control"></span>
                        </label>
                    </div>
                    <div class="mt-4 row">
                        <h2><label for="productType">Product types</label></h2>
                    </div>
                    <div class="row">
                        <select class="custom-select" name="productType" id="productType">
                            <c:forEach items="${productTypes}" var="productType">
                                <option value="${productType}">${productType.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mt-4 row">
                        <input class="mt-3 btn btn-primary" type="submit" value="Submit"/>
                    </div>
                </div>
            </form>

        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h2>Instruction</h2>
                <p>Use XML demo files to test the operation of parsing and validating files for individual products.
                    Each product has its own parser and validator defined (operating under the common interface).</p>
                <p>Currently, there are two types of products: Star Wars and Marvel.
                    If you select the wrong XML file for the selected product type, you will receive an error message.
                    If the validation proceeds correctly, a list of products will be displayed.</p>
                <p><a target="_blank" class="btn btn-secondary" href="https://drive.google.com/drive/folders/1LwgFs_kwbc-7aAWT2XbCfv0ZIR2idf4X?usp=sharing" role="button">
                    Download XML demo files
                    <img src="${pageContext.request.contextPath}/resources/open-iconic/share-2x.png"
                         alt="View details">
                </a></p>
            </div>
        </div>
        <hr>
    </div>
</main>

<jsp:include page="footer.jsp"/>

<jsp:include page="scripts.jsp"/>

</body>

</html>