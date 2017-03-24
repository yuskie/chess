<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>
<head>
    <meta name="viewport" content="width=device-width" />
    <title>Chess!</title>
    <script src="<c:url value='/js/jquery-1.6.4.js'/>"></script>
    <script src="<c:url value='/js/sockjs.min.js'/>"></script>
    <script src="<c:url value='/js/stomp.min.js'/>"></script>
    <script src="<c:url value='/js/movement.js'/>"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/chess.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/bootstrap.min.css'/>">
 
</head>
<body>
    <table class="table-bordered">
		<c:set var="xValues" value="${['a','b','c','d','e','f','g','h']}"/>
		<c:set var="flip" value="false"/>
		<c:forEach begin="1" end="8" var="y">
            <tr data="row-${y}">
                <c:forEach items="${xValues}" var="x">
                	<c:choose>
                		<c:when test="${flip}">
                			<c:set var="color" value="black"/>
                 			<c:set var="flip" value="${not flip}"/>               			
                		</c:when>
                		<c:otherwise>
							<c:set var="color" value="white" />
                 			<c:set var="flip" value="${not flip}"/>               			
						</c:otherwise>
                	</c:choose>
                    <td class="default ${color}" data="${x}${9-y}"></td>
                </c:forEach>
				<c:choose>
					<c:when test="${flip}">
						<c:set var="color" value="black" />
						<c:set var="flip" value="${not flip}" />
					</c:when>
					<c:otherwise>
						<c:set var="color" value="white" />
						<c:set var="flip" value="${not flip}" />
					</c:otherwise>
				</c:choose>
			</tr>
        </c:forEach>

    </table>
</body>
</html>