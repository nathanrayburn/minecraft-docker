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
<body class="">
<div class="flex">
        @template.navbar()
    <div class="main-content flex-grow">
        @template.header()
        <div class="bg-gradient-to-r from-green-400 to-blue-500 text-white rounded-lg text-3xl text-center p-4 shadow-lg">Server Terminal</div>
        <div class="console bg-blue-500 text-white p-4 m-4 border border-blue-700 rounded">
            <pre id="console-output" class="max-w-full mx-auto break-words whitespace-pre-wrap"></pre>
        </div>

        <div class="input text-white p-4 m-4 rounded">
            <form id="commandForm">
                <textarea id="commandInput" placeholder="Press enter to send a command" class="w-full p-2 rounded-lg text-white" rows="2"></textarea>
            </form>
        </div>

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