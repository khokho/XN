'use strict';

const e = React.createElement;
/*
var ws = new SockJS("/ws");
var stomp = Stomp.over(ws);

//this function will be bound to button !this!
function onmessage(responseJSON){
    console.log("Hi my name is bob and I'm from wesocket")
    this.updateBtn()
}
 */
class AdminButtons extends React.Component {
    constructor(props) {
        super(props);
        this.state = {disabled: false, count:0};
        this.buttonName = props.buttonName;
        this.queueName = props.queueName;
        this.handleClickDequeue = this.handleClickDequeue.bind(this);
        this.handleClickClear = this.handleClickClear.bind(this);
    }

    handleClickDequeue() {
        console.log(this.queueName)
        fetch('http://' + window.location.host + '/admin/dequeue/' + this.queueName)
    }

    handleClickClear(){
        console.log(this.queueName)
        fetch('http://' + window.location.host + '/admin/clear-queue/' + this.queueName)
    }

    update(){
        var button = this
        fetch('http://' + window.location.host + '/get-anticipants/'+button.queueName)
            .then(resp=>{
                console.log(resp)
                return resp.json()
            })
            .then(res=>{
                console.log(res)
                button.setState({count:res})
            })

    }

    render() {
        return (
            <div>
                <button disabled={this.state.disabled} onClick={this.handleClickDequeue}> {this.buttonName} </button>
                <p>რიგში არის {this.state.count} სტუდენტი</p>
                <button disabled={this.state.disabled} onClick={this.handleClickClear}> რიგის გასუფთავება </button>
            </div>
        );
    }
}


var paperAdmin = ReactDOM.render(
    <AdminButtons queueName='blank-paper' buttonName = 'შავი ფურცლის მიცემა' />,
    document.getElementById('paper-admin')
);

var examerAdmin = ReactDOM.render(
    <AdminButtons queueName='call-examer' buttonName = 'დამკვირვებლის მისვლა'/>,
    document.getElementById('examer-admin')
);

var wcAdmin= ReactDOM.render(
    <AdminButtons queueName='wc' buttonName = 'სტუდენტის გაშვება'/>,
    document.getElementById('wc-admin')
);

/*
stomp.connect({}, function () {
    paperAdmin.startListen()
    examerAdmin.startListen()
    wcAdmin.startListen()

});

 */
