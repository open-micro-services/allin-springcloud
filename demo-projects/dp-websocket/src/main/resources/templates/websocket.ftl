<!DOCTYPE html>
<html>
<meta charset="utf-8"/>
<title>WebSocket Test</title>
<script language="javascript" type="text/javascript" src="/js/uuid.js"></script>
<script language="javascript" type="text/javascript">
    //https://www.tutorialspoint.com/websockets/websockets_send_receive_messages.htm
    var clientId = Utils.uuid.guid();

    // Freemarker 传递主机地址和端口--适用于本机多环境测试，如果是其他服务器地址需要重新配置
    var wsUri = "ws://${host_port}/websocket/" + clientId;

    console.info("WS服务请求地址:" + wsUri);

    var output;

    function init() {
        output = document.getElementById("output");
        testWebSocket();
    }

    function testWebSocket() {
        websocket = new WebSocket(wsUri);

        websocket.onopen = function (evt) {
            onOpen(evt)
        };

        websocket.onmessage = function (evt) {
            onMessage(evt)
        };

        websocket.onerror = function (evt) {
            onError(evt)
        };

        websocket.onclose = function (evt) {
            onClose(evt)
        };
    }

    function onOpen(evt) {
        writeToScreen("CONNECTED");
    }

    function onClose(evt) {
        writeToScreen('<span style = "color: blue;">RESPONSE: ' + evt.data + '</span>');
        websocket.close();
    }

    function onMessage(evt) {
        writeToScreen('<span style = "color: blue;">RESPONSE: ' + evt.data + '</span>');
    }

    function onError(evt) {
        writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
    }

    function doSend(message) {
        writeToScreen("SENT: " + message);
        websocket.send(message);
    }

    function writeToScreen(message) {
        var pre = document.createElement("p");
        pre.style.wordWrap = "break-word";
        pre.innerHTML = message;
        output.appendChild(pre);
    }

    window.addEventListener("load", init, false);


    function sendMessage() {
        var message = document.getElementById("msg").value;
        writeToScreen("SENT: " + message);
        websocket.send(message);
    }

</script>

<h2>WebSocket Test</h2>
<input id="msg" type="text" value=""/> <input type="button" value="SendMessage" onclick="sendMessage()"/> For example: {"service":"statistics_events"}
<div id="output"></div>

</html>