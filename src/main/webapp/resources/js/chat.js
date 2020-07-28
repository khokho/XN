/**
 * Check for pressed key
 */
func

/**
 * Possibly paste feature, but now nothing.
 * @param event
 */
document.getElementById("field").onpaste = function(event){
    let a = event.clipboardData.items;

}

var elem = document.getElementById('image-input');
/**
 * Check if uploaded file is an image.
 */
elem.onchange = function(){
    const file = elem.files[0];
    const  fileType = file['type'];
    const validImageTypes = ['image/gif', 'image/jpeg', 'image/png'];
    if (!validImageTypes.includes(fileType)) {
        alert("ატვირთული ფაილი სურათი უნდა იყოს");
    }
};

const sentStart = "                    <div class=\"outgoing_msg\">\n" +
                  "                        <div class=\"sent_msg\">\n",
      sentEnd = "                            <span class=\"time_date\">"+new Date()+"</span> </div>\n" +
                "                    </div>",
      recStart =  "               <div class=\"incoming_msg\">\n" +
                 // "                        <div class=\"incoming_msg_img\"> <i class=\"fa fa-user\" aria-hidden=\"true\"> </div>\n" +
                  "                        <div class=\"received_msg\">\n" +
                  "                            <div class=\"received_withd_msg\">\n",
      recEnd = "                            <span class=\"time_date\">"+new Date()+"</span> </div>\n" +
               "                        </div>\n" +
               "                    </div>\n";

function displayImage(img, author){
    let diva = document.createElement("div");
    if(author === 0){
        diva.innerHTML = sentStart +
            "            <p><img src='"+ img +"' alt='image'></p>\n" +
                         sentEnd;
    }
    else{
        diva.innerHTML = recStart +
            "                                <p><img src='"+img+"' alt='image'></p>\n" +
                         recEnd;
    }
    return diva;
}

function displayMessage(author, msg){
    if(msg == null || msg === "") return null;
    let diva = document.createElement("div");
    if(author === 0){
        diva.innerHTML = sentStart +
            "                            <p>"+msg+"</p>\n" +
                         sentEnd;
    }
    else{
        diva.innerHTML = recStart +
            "                                <p>"+msg+"</p>\n" +
                         recEnd;
    }
    return diva;
}

let ws;

function initWebSocket() {
    ws = new WebSocket("ws://localhost:8080/messenger");
    ws.onmessage = function (e) {
        let msg = e.data;
        let author;
        // get author
        if(msg.charAt(0) === '1') author = 0;
        else author = 1;
        msg = msg.substring(1);

        let diva;


        diva = displayMessage(author, msg);
        //else diva = displayImage(msg, author);

        if(diva != null) {
            document.getElementById("messages").appendChild(diva);
            diva.scrollIntoView();
        }
    }
}

/**
 * Sends message and, if present, uploaded image
 */
function sendMessage() {
    ws.send(document.getElementById("field").value);
    document.getElementById("field").value = "";
    if(elem.value !== ""){
        let xhr = new XMLHttpRequest();
        let fd = new FormData();

        fd.append("image", elem.files[0]);
        fd.append("chat_id", document.getElementById("chat-id").value);
        xhr.open("POST", "/sendImage", true);
        xhr.send(fd);
        elem.value = "";
    }
}


window.addEventListener("beforeunload", function(){
    ws.close();
}, false);
