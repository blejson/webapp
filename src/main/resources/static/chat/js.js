var client = null;

function showMessage(value, user){
    var newResponse = document.createElement('p');
    newResponse.appendChild(document.createTextNode(user));
    newResponse.appendChild(document.createTextNode(": "));
    newResponse.appendChild(document.createTextNode(value));
    var response = document.getElementById('response');
    response.appendChild(newResponse);
}

function connect(){
    client = Stomp.client('ws://localhost:8080/chat');
    client.connect({}, function (frame) {
        client.subscribe("/topic/messages", function(message){
            showMessage(JSON.parse(message.body).value, JSON.parse(message.body).user)
        });
    })
}

function sendMessage(){
    var messageToSend = document.getElementById('messageToSend').value;
    //var user = document.getElementById('user').value;
    client.send("/app/chat", {}, JSON.stringify({'value': messageToSend, 'user': user}) );
    document.getElementById('messageToSend').value = "";
}