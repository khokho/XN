'use strict';

const e = React.createElement;

var ws = new SockJS("/ws");
var stomp = Stomp.over(ws);

//this function will be bound to button !this!
function onmessage(responseJSON){
    console.log("Hi my name is bob and I'm from wesocket")
    this.update()
}

const divStyle = {
    color: 'black',
    height: 16,
    margin: 0,
    top: 80,
    left: 20,
};

const fontStyle = {
    color : 'darkred',
    marginRight:'22px',
    fontSize: 30,
};

const dequeueButtonStyle = {
    background:'seagreen',
    marginRight:'22px',
    fontSize: 25,
};

const clearButtonStyle = {
    background:'darkgreen',
    fontSize: 25,
};

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
        return (<div  style={divStyle}>
                <div style={{ display:'inline-block'}}>
                    <button style={dequeueButtonStyle} disabled={this.state.disabled} onClick={this.handleClickDequeue}> {this.buttonName} </button>
                </div>

                <div style={{ display:'inline-block'}}>
                    <p style={fontStyle}>რიგში არის {this.state.count} სტუდენტი</p>
                </div>

                <div style={{ display:'inline-block'}}>
                    <button style={clearButtonStyle} disabled={this.state.disabled} onClick={this.handleClickClear}> რიგის გასუფთავება </button>
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
