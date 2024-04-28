<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>API Home Page</title>
    <link href="output.css" rel="stylesheet">
    <link href="custom.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body class="bg-gray-100">
<div class="flex">
    <nav class="bg-gray-200 w-64 h-screen overflow-auto">
        @template.navbar()
    </nav>
    <div class="main-content flex-grow">
        @template.header()
        <div class="console bg-black text-white p-4 rounded-lg mb-4 overflow-auto" style="max-height: 75vh; width: 100%; max-width: 100%;">
            <pre id="console-output" class="max-w-full mx-auto break-words whitespace-pre-wrap"></pre>
        </div>



        <form id="commandForm">
            <textarea id="commandInput" class="w-full p-2 rounded-lg" rows="2"></textarea>
        </form>

        <script src="websocket.js"></script>
        @template.footer()
    </div>
</div>

<script>
    $(document).ready(function(){
        $('#commandForm').on('submit', function(e){
            e.preventDefault();
        });
    });
</script>
</body>
</html>