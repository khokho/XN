<!------ Include the above in your HEAD tag ---------->


<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css" rel="stylesheet">
    <link href="../../resources/css/chat.css" type="text/css" rel="stylesheet">
</head>
<body onload="initWebSocket()">
<div class="container">
    <h3 class=" text-center">Messaging</h3>
    <div class="messaging">
        <div class="inbox_msg">

            <div class="mesgs">
                <div class="msg_history">
                    <div id ="messages"></div>
                <div class="type_msg">
                    <div class="input_msg_write">
                        <input type="text" class="write_msg" id="field" placeholder="Type a message" />
                        <button class="msg_send_btn" type="button" onclick="sendMessage()"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
                    </div>
                </div>
            </div>
        </div>


        <p class="text-center top_spac"> Design by <a target="_blank" href="#">Sunil Rajput</a></p>
    <script>


        let ws;
        function initWebSocket() {
            ws = new WebSocket("ws://localhost:8080/messenger");
            ws.onmessage = function (e) {



                let msg = e.data;

                let author;
                if(msg.charAt(0) == '1') author = 0;
                else author = 1;
                msg = msg.substring(1);
                let diva = document.createElement("div");

                if(author == 0){
                    diva.innerHTML = "                    <div class=\"outgoing_msg\">\n" +
                        "                        <div class=\"sent_msg\">\n" +
                        "                            <p>"+msg+"</p>\n" +
                        "                            <span class=\"time_date\">"+new Date()+"</span> </div>\n" +
                        "                    </div>";
                }
                else{
                    diva.innerHTML = "               <div class=\"incoming_msg\">\n" +
                        "                        <div class=\"incoming_msg_img\"> <img src=\"https://ptetutorials.com/images/user-profile.png\" alt=\"sunil\"> </div>\n" +
                        "                        <div class=\"received_msg\">\n" +
                        "                            <div class=\"received_withd_msg\">\n" +
                        "                                <p>"+msg+"</p>\n" +
                        "                                <span class=\"time_date\">"+new Date() +"</span></div>\n" +
                        "                        </div>\n" +
                        "                    </div>\n";
                }
                document.getElementById("messages").appendChild(diva);
            }
        }
        function sendMessage() {
            console.log(document.getElementById("field").value);

            ws.send(document.getElementById("field").value);
        }

        window.addEventListener("beforeunload", function(e){
            ws.close();
        }, false);
    </script>
        </div></div></div>
</body>
</html>