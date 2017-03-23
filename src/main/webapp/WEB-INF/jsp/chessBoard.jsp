<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>
<head>
    <meta name="viewport" content="width=device-width" />
    <title>Toggle!</title>
    <script src="<c:url value='/js/jquery-1.6.4.js'/>"></script>
    <script src="<c:url value='/js/sockjs.min.js'/>"></script>
    <script src="<c:url value='/js/stomp.min.js'/>"></script>
    <script src="<c:url value='/js/toggle.js'/>"></script>
    <style>
        td {
            height: 25px;
            width: 25px;
        }

        .default {
            background-color: gainsboro;
        }

        .rounded-box {
            display: inline-block;
            width: 20px;
            height: 20px;
            border-radius: 4px;
        }

        .selected {
            border: 2px solid #000;
        }
    </style>
</head>
<body>
    <div>
        <div class="rounded-box" style="background-color: #FF0000;"></div>
        <div class="rounded-box" style="background-color: #FFC0CB;"></div>
        <div class="rounded-box" style="background-color: #FFA500;"></div>
        <div class="rounded-box" style="background-color: #FFFF00;"></div>
        <div class="rounded-box" style="background-color: #800080;"></div>
        <div class="rounded-box" style="background-color: #008000;"></div>
        <div class="rounded-box" style="background-color: #4682B4;"></div>
        <div class="rounded-box" style="background-color: #A52A2A;"></div>
    </div>

    <table>

		<c:forEach begin="0" end="9" var="row">
            <tr>
                <c:forEach begin="0" end="9" var="col">
                    <td class="default" data-row="${row}" data-col="${col}"></td>
                </c:forEach>
            </tr>
        </c:forEach>

    </table>
</body>
</html>