"use strict";

var stompClient = Stomp.over(new SockJS('http://localhost:8080/websocket'));
var room = window.location.pathname.split('/').pop();
stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    document.getElementById("sendButton").disabled = false;

    stompClient.subscribe('/topic/messages/'+room, function (response) {
        var message = JSON.parse(response.body);
        var li = document.createElement("li");
        document.getElementById("messageInput").value = null;
        document.getElementById("messagesList").appendChild(li);
        li.textContent = `${message.user} đã nhắn: ${message.message}`;
    });
});

document.getElementById("sendButton").addEventListener("click", function (event) {
    var user = document.getElementById("userInput").value;
    var message = document.getElementById("messageInput").value;

    stompClient.send("/app/sendMessage/"+room, {}, JSON.stringify({ 'user': user, 'message': message }));

    event.preventDefault();
});
