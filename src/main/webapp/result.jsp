<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.example.wildfly_lab2.model.TableBean" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.wildfly_lab2.model.Attempt" %>
<%@ page import="java.util.stream.Collectors" %>
<jsp:useBean id="bean" class="com.example.wildfly_lab2.model.TableBean" scope="request"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>weblab_2</title>
    <style>
        :root {
            font-size: 20px;
            font-family: sans-serif;
            background-color: #7395AE;
        }

        * {
            margin: 0;
            padding: 0;
        }

        main {
            padding: 10px;
            text-align: center;
        }

        header {
            font-size: 20px;
            font-family: 'Gill Sans', monospace;
            color: #F6F836;
            text-align: left;
            background-color: #5D5C61;
            padding: 0;
            margin: 0;
            border: 16px solid #5D5C61;
            border-bottom-left-radius: 40px;
            border-bottom-right-radius: 40px;
        }

        .warning {
            margin: 30px;
        }

        .variable_name {
            margin: 30px;
            font-size: 30px;
        }

        table {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif, serif;
            border-collapse: separate;
            border-spacing: 10px;
            background: #5D5C61;
            color: black;
            border: 16px solid #5D5C61;
            border-radius: 40px;
            width: 90%;
            margin: auto;
            transition: 0.3s;
        }

        th {
            font-size: 20px;
            padding: 10px;
        }

        td {
            background: #E8E4E3;
            padding: 10px;
        }

        .card {
            position: relative;
            width: 360px;
            height: 360px;
            padding: 0 0 0 0;
            margin: 30px auto auto;
            align-items: center;
            display: flex;
            overflow: hidden;
        }

        #graph {
            position: absolute;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            padding: 0 0 0 0;
            cursor: pointer;
            margin: 5px;
            border-radius: 10px;
            background-color: #A5EFE7;
        }

        #graph-back {
            position: absolute;
            left: -100px;
            right: -100px;
            top: -100px;
            bottom: -100px;
            background-image: conic-gradient(from 0deg, blue, cyan, #38C0F6, #3856F6, #7368C3, #9668C3, #BF7DFF, blue);
            animation: rotate 3s linear infinite;
            overflow: hidden
        }

        button {
            background-color: #9A9A9A;
            font-size: 20px;
            margin: 10px;
            padding: 5px 15px;
            transition: 0.3s;
        }

        button:enabled:active {
            transition: 0s;
            box-shadow: inset 0 0 0 5px #2f6144;
        }

        input[type="radio"] {
            margin: 0 20px;
        }

        input[type="text"] {
            font-size: 20px;
        }

        h2::first-line, h1::first-line, .warning {
            color: #F6F836;
        }

        h1, h2 {
            transition: 0.3s;
        }

        header:hover h1 {
            opacity: 0;
        }

        header:hover h2 {
            text-align: center;
            transform: scale(2);
            transition-duration: 0.3s;
        }

        header:hover h2::after {
            content: '. ITMO, 2022';
        }

        @keyframes rotate {
            from {
                transform: rotate(0deg);
            }
            to {
                transform: rotate(360deg);
            }
        }
    </style>

</head>

<body>
<header>
    <h1>Egor Vereshchagin, P32312</h1>
    <h2>var. 6663121</h2>
</header>
<main>
    <div class="rainbow-box">
        <div class="card">
            <div id="graph-back"></div>

            <canvas id="graph" width="350" height="350"></canvas>
        </div>
    </div>
    <div id="results" class="table">
        <h1>Results</h1>
        <table>
            <tr>
                <td>Attempt</td>
                <td>X</td>
                <td>Y</td>
                <td>R</td>
                <td>Result</td>
                <td>Work time(in microseconds)</td>
                <td>Start time</td>
            </tr>

            <%!
                String printTable(TableBean tableBean) {
                    StringBuilder stringBuilder = new StringBuilder();
                    if (tableBean != null)
                        for (int i = 0; i < tableBean.getAttempts().size(); i++) {
                            stringBuilder.append("<tr>");
                            stringBuilder.append("<td>").append(i + 1).append("</td>");
                            stringBuilder.append("<td>").append(tableBean.getAttempts().get(i).getX()).append("</td>");
                            stringBuilder.append("<td>").append(tableBean.getAttempts().get(i).getY()).append("</td>");
                            stringBuilder.append("<td>").append(tableBean.getAttempts().get(i).getR()).append("</td>");
                            stringBuilder.append("<td>").append(tableBean.getAttempts().get(i).getHit()).append("</td>");
                            stringBuilder.append("<td>").append(tableBean.getAttempts().get(i).getWorkTime()).append("</td>");
                            stringBuilder.append("<td>").append(tableBean.getAttempts().get(i).getStartTime()).append("</td>");
                            stringBuilder.append("</tr>");
                        }
                    return stringBuilder.toString();
                }
            %>
            <%= printTable(bean) %>


            <%--
                        <%= request.getAttribute("table").toString() %>
            --%>

        </table>

        <form id="form" method="GET" action="./">
            <button>Back</button>
        </form>
    </div>

</main>

    <%
        if(bean == null || bean.getAttempts().size() == 0) {
            request.setAttribute("xList", "[]");
            request.setAttribute("yList", "[]");
            request.setAttribute("rList", "[]");
        } else {
            List<Double> xList = bean.getAttempts().stream().map(Attempt::getX).collect(Collectors.toList());
            List<Double> yList = bean.getAttempts().stream().map(Attempt::getY).collect(Collectors.toList());
            List<Double> rList = bean.getAttempts().stream().map(Attempt::getR).collect(Collectors.toList());

            request.setAttribute("xList", new Gson().toJson(xList));
            request.setAttribute("yList", new Gson().toJson(yList));
            request.setAttribute("rList", new Gson().toJson(rList));

        }
    %>




<script type="text/javascript">


    <%@include file="./src/grapher.js"%>


    let x = (<%=request.getAttribute("xList")%>)
    let y = (<%=request.getAttribute("yList")%>)
    let r = (<%=request.getAttribute("rList")%>)
    drawDots(x, y, r)
</script>


</body>