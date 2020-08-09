

var globalStomp = undefined
var isStompConnected = false

function getStomp(callback){
    if(isStompConnected)callback(globalStomp)
    var ws = new SockJS("/ws");
    var stomp = Stomp.over(ws);
    stomp.connect({}, function () {
        isStompConnected = true
        globalStomp = stomp
        callback(globalStomp);
    });
}

