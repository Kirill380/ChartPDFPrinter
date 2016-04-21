<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" lang="en">
    <title>Upload Image</title>
    <style>
        #container {
            max-width: 800px;
            height: 400px;
            margin: 50px auto;
        }

        .upload {
            margin-top: 10px;
            padding: 5px 8px;
            cursor: pointer;
            color: white;
            background-color: #417aed;
            text-align: center;
            width: 100px;
        }

        .upload:hover {
            background-color: #3863d6;
        }
        #canvas {
            max-width: 800px;
            height: 400px;
        }
    </style>
</head>
<body>
<div id="container"></div>
<div class="upload jsLoad">Load Image</div>
<div id="png-container"></div>
<canvas id="canvas"></canvas>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.0/jquery.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="../../resources/project/chart.js"></script>
</body>
</html>
