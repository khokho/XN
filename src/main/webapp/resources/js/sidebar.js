
const INVALID_TOPIC = -1
const POST_TOPIC = 0
const CHAT_TOPIC = 1
const QUEUE_TOPIC = 2

function getTopic(id){
    if(id.startsWith("posts-")){
        return POST_TOPIC
    }
    if(id.startsWith("chat-")){
        return CHAT_TOPIC
    }
    if(id.startsWith("queue-")){
        return QUEUE_TOPIC
    }
    return INVALID_TOPIC;
}

/**
 * @param {String} topic
 * topic of the sidebar element to notify
 * topic can be:
 * posts-{exam id}, chat-{other user id}
 */
function notifySidebar(topic, e){
    console.log("message received:" + topic)
    var id = getTopic(topic)
    if(id === INVALID_TOPIC) return

    this.setState((state)=>{
        if(topic in state.notifications)
            state.notifications[topic]++
        else
            state.notifications[topic]=1
        return state;
    })

    var text = 'Something happened please check!';
    var notification = new Notification('Exam Enviroment', { body: text, icon: undefined });

}


function listenForNotification(topics) {

    getStomp((stomp)=>{
        topics.map((topic)=>{
            var id = getTopic(topic)
            if(id === INVALID_TOPIC) return
            stomp.subscribe("/topic/"+topic, ()=>notifySidebar(topic), {});
        })
    })
}

class SidebarElements extends React.Component{
    constructor(props) {
        super(props);
        this.state = {elements:[], notifications:{}}
        var sidebarURL = window.location.protocol + "//" + window.location.host + "/getSidebar"
        fetch(sidebarURL)
            .then(resp=>resp.json())
            .then(elements=>{
                this.setState({elements:elements})
                listenForNotification(elements.map((el)=>el.id))
            })
        notifySidebar = notifySidebar.bind(this)

    }

    render(){
        return (
            <div>
                {this.state.elements.map(el=>
                    (<a key={el.path} href={el.path} className="w-100 list-group-item list-group-item-action bg-light" >
                        {el.name}
                        <span className={"align-middle float-right badge badge-success" + ((el.id in this.state.notifications) ? "":" d-none")}>
                            {this.state.notifications[el.id]}
                        </span>
                    </a>))
                }
            </div>
        );
    }
}

const inputContainer = document.querySelector('#sidebar-elements');


ReactDOM.render(<SidebarElements/>, inputContainer);

