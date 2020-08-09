
/**
 * @param {String} id
 * id of the sidebar element to notify
 * id can be:
 * exam, posts, login, register, newExam, examList, chat-{other user id}
 */
function notifySidebar(id){
    console.log("message received:" + id)
    if(id[0]==='!')return
    this.setState((state)=>{
        if(id in state.notifications)
            state.notifications[id]++
        else
            state.notifications[id]=1

        var text = 'Something happened please check!';
        var notification = new Notification('Exam Enviroment', { body: text, icon: undefined });

        return state;
    })
}


function listenForNotification(topics) {
    getStomp((stomp)=>{
        topics.map((topic)=>{
            stomp.subscribe("/topic/"+topic, ()=>notifySidebar(topic), {});
        })
    })
}

class SidebarElements extends React.Component{
    constructor(props) {
        super(props);
        this.state = {elements:[], notifications:{'posts':2}}
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

