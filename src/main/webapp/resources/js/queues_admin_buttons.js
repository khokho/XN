'use strict';

const e = React.createElement;

var ws = new SockJS("/ws");
var stomp = Stomp.over(ws);

//this function will be bound to button !this!
function onmessage(){
    this.update()
}

class AdminButtons extends React.Component {
    constructor(props) {
        super(props);
        this.state = {disabled: true, count:0};
        this.buttonName = props.buttonName;
        this.queueName = props.queueName;
        this.handleClickDequeue = this.handleClickDequeue.bind(this);
        this.handleClickClear = this.handleClickClear.bind(this);
        this.update();
    }

    startListen(){
        const my_onmessage = onmessage.bind(this);
        stomp.subscribe("/topic/queue-" + this.queueName, my_onmessage, {});
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
        fetch('http://' + window.location.host + '/admin/get-disabled/'+button.queueName)
            .then(resp=>{
                return resp.json()
            })
            .then(res=>{
                console.log(res)
                button.setState({disabled:res})
            })

    }

    render() {
        return (<div  className={"d-flex"}>
                <div className={"flex-fill"}>
                    <button className={"btn btn-warning btn-lg btn-block"} disabled={this.state.disabled} onClick={this.handleClickDequeue}> {this.buttonName} </button>
                </div>

                <div className={"flex-fill"}>
                    <p className={"p-2 text-center"}>რიგში არის {this.state.count} სტუდენტი</p>
                </div>

                <div className={"flex-fill"}>
                    <button className={"btn btn-warning btn-lg btn-block"} disabled={this.state.disabled} onClick={this.handleClickClear}> რიგის გასუფთავება </button>
                </div>
        </div>);
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

stomp.connect({}, function () {
    paperAdmin.startListen()
    examerAdmin.startListen()
    wcAdmin.startListen()

});
