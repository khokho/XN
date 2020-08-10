'use strict';

const e = React.createElement;

var ws = new SockJS("/ws");
var stomp = Stomp.over(ws);

function Message(props) {
    if (props.me) {
        return (
            <div className={'outgoing_msg'}>
                <div className={'sent_msg'}>
                    <p>{props.message}</p>
                </div>
            </div>
        );
    } else {
        return (
            <div className={'incoming_msg'}>
                <div className={'received_msg'}>
                    <div className={'received_withd_msg'}>
                        <p>{props.message}</p>
                    </div>
                </div>
            </div>
        );
    }
}


class Chat extends React.Component {
    constructor(props) {
        super(props);
        this.state = {messages: []}
        this.chatId = props.chatId
        this.me = props.userId
        console.log(this.chatId, this.me)


        fetch('http://' + window.location.host + '/getMessages/' + props.chatId + '?from=0&to=1000')
            .then(resp => {return resp.json()})
            .then((jsonData) => {
                jsonData.reverse()
                this.setState({messages: jsonData})
            })

        onmessage = (messageJSON) => {
            console.log("here broz")
            console.log(messageJSON)
            var message = JSON.parse(messageJSON.body)
            console.log("teeext:" + message.text)
            this.setState((state) => {
                return {messages: state.messages.concat([message])}
            })
        }
        stomp.connect({}, function () {
            console.log("connected");
            stomp.subscribe("/topic/chat-" + chatId, onmessage, {});
        });
    }

    scrollToBottom = () => {
        this.messagesEnd.scrollIntoView({ behavior: "smooth" });
    }

    componentDidMount() {
        this.scrollToBottom();
    }

    componentDidUpdate() {
        this.scrollToBottom();
    }

    renderMessages() {
        console.log(this.state.messages)
        return this.state.messages.map((message) => (
            <Message key={message.messageId} message={message.text} me={this.me === message.from}/>
        ))
    }

    render () {
        return (
            <div>
                <div className="MessageContainer" >
                    <div className="MessagesList">
                        {this.renderMessages()}
                    </div>
                    <div style={{ float:"left", clear: "both" }}
                         ref={(el) => { this.messagesEnd = el; }}>
                    </div>
                </div>
            </div>
        );
    }
}

class MessageInput extends React.Component{

    constructor() {
        super();
        this.state={message:''}
        this.messageChange = this.messageChange.bind(this);
        this.handleButton = this.handleButton.bind(this);
        this.sendMessage = this.sendMessage.bind(this);
        this.keyHandler = this.keyHandler.bind(this);
    }

    sendMessage(){
        stomp.send("/app/chat-"+window.chatId, {}, this.state.message)
        this.setState({message: ''})
    }

    messageChange(event) {
        this.setState({message: event.target.value});
    }

    keyHandler(event){
        if(event.key === 'Enter'){
            this.sendMessage()
        }
    }

    handleButton() {
        // alert('A name was submitted: ' + this.state.message);
        this.sendMessage()
    }

    render() {
        return (
            <div>
                <input autoComplete="off" type="text" className="write_msg" id="message" placeholder="Type a message"
                       value={this.state.message}
                       onChange={this.messageChange} onKeyDown={this.keyHandler}/>

                {/*<button className="msg_send_btn" type="button" style="margin-right: 40px"*/}
                {/*        onClick="document.getElementById('image-input').click();">*/}
                {/*    <i className="fa fa-camera" aria-hidden="true"/>*/}
                {/*</button>*/}
                {/*<input id="image-input" type="file" name="name" style="display: none;"/>*/}

                <button id="sendButton" className="msg_send_btn" type="button" onClick={this.handleButton}>
                    <i className="fa fa-paper-plane-o" aria-hidden="true"/>
                </button>
            </div>
        );
    }
}


const chatContainer = document.querySelector('#messages');
const inputContainer = document.querySelector('#MessageInput');

// ReactDOM.render(<LikeButton/>, chatContainer);
// ReactDOM.render(<MessageInput chatId={window.chatId} userId={window.userId}/>, inputContatiner);

ReactDOM.render(<Chat chatId={window.chatId} userId={window.userId}/>, chatContainer);
ReactDOM.render(<MessageInput/>, inputContainer);


function setW() {
    //document.getElementById("messagebar").style.left=(document.getElementById("sidebar-wrapper").offsetWidth-1).toString() + 'px';
    //document.getElementById("sendButton").style.marginRight=(document.getElementById("sidebar-wrapper").offsetWidth + 5).toString() + 'px';
    //document.getElementById("message").style.paddingRight=(document.getElementById("sidebar-wrapper").offsetWidth + 50).toString() + 'px';
}
setW();