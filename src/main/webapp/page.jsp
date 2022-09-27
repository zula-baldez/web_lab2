<%@ page import="com.google.gson.Gson" %>
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
    <form id="form" method="GET" action="./">
        <p class="variable_name">X</p>
        <input type="hidden" id='x' name='x'>


        <button name="xButton" type="button" id="button1" class="button" onclick=
                'xHidden = document.getElementById("x")
                         xHidden.value = -2;
                        '>-2
        </button>
        <button name="xButton" type="button" id="button2" class="button" onclick=
                'xHidden = document.getElementById("x")
                         xHidden.value = -1.5;'>-1.5
        </button>
        <button name="xButton" type="button" id="button3" class="button" onclick=
                'xHidden = document.getElementById("x")
                         xHidden.value = -1;'>-1
        </button>
        <button name="xButton" type="button" id="button4" class="button" onclick=
                'xHidden = document.getElementById("x")
                         xHidden.value = -0.5;'>-0.5
        </button>
        <button name="xButton" type="button" id="button6" class="button" onclick=
                'xHidden = document.getElementById("x")
                         xHidden.value = 0'>0
        </button>
        <button name="xButton" type="button" id="button7" class="button" onclick=
                'xHidden = document.getElementById("x")
                         xHidden.value = 0.5'>0.5
        </button>
        <button name="xButton" type="button" id="button8" class="button" onclick=
                'xHidden = document.getElementById("x")
                         xHidden.value = 1'>1
        </button>
        <button name="xButton" type="button" id="button9" class="button" onclick=
                'xHidden = document.getElementById("x")
                         xHidden.value = 1.5'>1.5
        </button>
        <button name="xButton" type="button" id="button10" class="button" onclick=
                'xHidden = document.getElementById("x")
                         xHidden.value = 2'>2
        </button>


        <p class="variable_name">Y</p>
        <input type="text" name="y" id="yInput">
        <p id="y-warning" class="warning"></p>


        <div class="rInput">
            <p class="variable_name">R</p>
            <input name="r" type="radio" value="1" id=rb1 onchange="drawDots()">
            <label for="rb1"> 1 </label>

            <input name="r" type="radio" value="2" id=rb2 onchange="drawDots()">
            <label for="rb2"> 2 </label>

            <input name="r" type="radio" value="3" id=rb3 onchange="drawDots()">
            <label for="rb3"> 3 </label>

            <input name="r" type="radio" value="4" id=rb4 onchange="drawDots()">
            <label for="rb4"> 4 </label>

            <input name="r" type="radio" value="5" id=rb5 onchange="drawDots()">
            <label for="rb5"> 5 </label>

        </div>


        <div>
            <button id="form-submit" type="submit" class="button">Send</button>
        </div>
    </form>
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

<%--            <%!
                String printTable(TableBean tableBean) {
                    StringBuilder stringBuilder = new StringBuilder();
                    if (tableBean != null)
                        for (int i = 0; i < tableBean.getHit().size(); i++) {
                            stringBuilder.append("<tr>");
                            stringBuilder.append("<td>").append(i + 1).append("</td>");
                            stringBuilder.append("<td>").append(tableBean.getX().get(i)).append("</td>");
                            stringBuilder.append("<td>").append(tableBean.getY().get(i)).append("</td>");
                            stringBuilder.append("<td>").append(tableBean.getR().get(i)).append("</td>");
                            stringBuilder.append("<td>").append(tableBean.getHit().get(i)).append("</td>");
                            stringBuilder.append("<td>").append(tableBean.getWorkTime().get(i)).append("</td>");
                            stringBuilder.append("<td>").append(tableBean.getStartTime().get(i)).append("</td>");
                            stringBuilder.append("</tr>");
                        }
                    return stringBuilder.toString();
                }
            %>
            <%= printTable(bean) %>--%>


            <%= request.getAttribute("table").toString() %>

        </table>


    </div>

</main>


<script type="text/javascript" src = "./src/validator.js"></script>


<script type="text/javascript">
    function drawDots() {
        <%@include file="./src/grapher.js"%>
        /*        <%
          if(bean == null || bean.getX() == null || bean.getY() == null || bean.getR() == null) {
              request.setAttribute("xList", "[]");
              request.setAttribute("yList", "[]");
              request.setAttribute("rList", "[]");
          } else {
              request.setAttribute("xList", new Gson().toJson(bean.getX()));
              request.setAttribute("yList", new Gson().toJson(bean.getY()));
              request.setAttribute("rList", new Gson().toJson(bean.getR()));
          }
      %>*/
        let x = (<%=request.getAttribute("xList")%>)
        let y = (<%=request.getAttribute("yList")%>)
        let r = (<%=request.getAttribute("rList")%>)
        let realR = 5
        let form = document.getElementById('form')
        let formData = new FormData(form)
        if (r.length !== 0) {
            realR = r[r.length - 1]
        }
        if (formData.has('r')) {
            realR = formData.get('r')
        }
        ctx.fillStyle = '#000'
        for (let i = 0; i < x.length; i++) {
            let realX = width / 2 + x[i] / (realR * 3 / 2) * width / 2
            let realY = height / 2 - y[i] / (realR * 3 / 2) * height / 2
            ctx.moveTo(realX, realY)
            ctx.arc(realX, realY, 5, 0, Math.PI * 2)
            ctx.fill()
        }
    }
    drawDots()
</script>


</body>