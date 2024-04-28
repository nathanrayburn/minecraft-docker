var ws = new WebSocket('ws://localhost:7000/console');
var commandInput = document.getElementById('commandInput');

ws.onopen = function(event) {
    console.log('WebSocket is connected.');
};

ws.onerror = function(error) {
    console.error('WebSocket encountered an error: ', error);
};

ws.onclose = function(event) {
    console.log('WebSocket is closed now.');
};

ws.onmessage = function(event) {
    var consoleOutputDiv = document.getElementById('console-output');
    var newLine = document.createElement('p');
    newLine.textContent = event.data;
    consoleOutputDiv.appendChild(newLine);
    setTimeout(() => {
        consoleOutputDiv.scrollTop = consoleOutputDiv.scrollHeight;
    }, 0);
};

commandInput.addEventListener('keydown', function(event) {
    if (event.key === 'Enter') {
        event.preventDefault();
        var command = commandInput.value;
        ws.send(command);
        commandInput.value = '';
    }
});