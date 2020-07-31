'use strict';

const e = React.createElement;

var ws = new SockJS("/ws");
var stomp = Stomp.over(ws);

class LikeButton extends React.Component {
    constructor(props) {
        super(props);
        this.state = { liked: false };
    }

    render() {
        if (this.state.liked) {
            return 'You liked this.';
        }

        return e(
            'button',
            { onClick: () => this.setState({ liked: true }) },
            'Like'
        );
    }
}

function Message(props){
    if(props.me){
        return (
            <div className={'outgoing_msg'}>
                <div className={'sent_msg'}>
                    <p>{props.message}</p>
                </div>
            </div>
        );
    }
    else {
        return (
            <div className={'incoming_msg'}>
                <div className={'received_msg'}>
                    <p>{props.message}</p>
                </div>
            </div>
        );
    }
}


class Chat extends React.Component {



    constructor(props) {
        super(props);
        this.state = {messages:[]}
        this.chatId = props.chatId
        this.me = props.userId
        console.log(this.chatId, this.me)


        fetch('http://'+window.location.host+'/getMessages/' + props.chatId + '?from=0&to=10')
            .then(resp => {
                console.log("hiihhihihihihihih")
                console.log(resp)
                return resp.json()
            })
            .then((jsonData) => {
                console.log(jsonData)
                this.setState({messages:jsonData})
            })

        onmessage = (messageJSON) => {
            console.log("here broz")
            console.log(messageJSON)
            var message = JSON.parse(messageJSON.body)
            console.log("teeext:" + message.text)
            this.setState((state)=>{
                return {messages: [message].concat(state.messages)}
            })
        }
        stomp.connect({}, function () {
            console.log("connected");
            stomp.subscribe("/topic/chat-" + chatId, onmessage, {});
        });
    }

    render(){
        return this.state.messages.map((message)=>(
            <Message message={message.text} me={this.me === message.from}/>
        ))
    }
}




const domContainer = document.querySelector('#messages');

ReactDOM.render(<Chat chatId={window.chatId} userId={window.userId}/>, domContainer);


