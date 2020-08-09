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
    fontSize: 30,
    marginRight:'22px',
};

const enqueueButtonStyle = {
    background:'seagreen',
    fontSize: 25,
    marginRight:'22px',
};

const cancelButtonStyle = {
    background:'darkgreen',
    fontSize: 25,
};

class StudentButtons extends React.Component {
    constructor(props) {
        super(props);
        this.queueName = props.queueName;
        this.state = {disabled: false, count: 0};
        this.handleClickEnqueue = this.handleClickEnqueue.bind(this);
        this.handleClickCancel = this.handleClickCancel.bind(this);
        this.update();
    }

    startListen(){
        const my_onmessage = onmessage.bind(this);
        stomp.subscribe("/topic/queue-" + this.queueName, my_onmessage, {});
    }

    handleClickEnqueue() {
        console.log(this.queueName);
        fetch('http://' + window.location.host + '/enqueue/'+this.queueName)
    }

    handleClickCancel(){
        console.log(this.queueName);
        fetch('http://' + window.location.host + '/cancel-waiting/'+this.queueName)
    }

    update(){
        var button = this
        fetch('http://' + window.location.host + '/get-anticipants/'+button.queueName)
            .then(resp=>{
                return resp.json()
            })
            .then(res=>{
                console.log(res)
                button.setState({count:res})
            })


        fetch('http://' + window.location.host + '/get-disabled/'+button.queueName)
            .then(resp=>{
                return resp.json()
            })
            .then(res=>{
                console.log(res)
                button.setState({disabled:res})
            })
    }

    render() {
        return(<div style={divStyle} >
            <div style={{ display:'inline-block'}}>
                <button style={enqueueButtonStyle} disabled={this.state.disabled} onClick={this.handleClickEnqueue}> რიგში ჩადგომა </button>
            </div>

            <div style={{ display:'inline-block'}}>
                <p style={fontStyle} >რიგში არის {this.state.count} სტუდენტი</p>
            </div>

            <div style={{ display:'inline-block'}}>
                <button style={cancelButtonStyle} disabled={!this.state.disabled} onClick={this.handleClickCancel}> მოთხოვნის გაუქმება </button>
            </div>
        </div>);
    }
}

var paperStudent = ReactDOM.render(
    <StudentButtons queueName='blank-paper' />,
    document.getElementById('paper-student')
);

var examerStudent = ReactDOM.render(
    <StudentButtons queueName='call-examer' />,
    document.getElementById('examer-student')
);

var wcStudent = ReactDOM.render(
    <StudentButtons queueName='wc'/>,
    document.getElementById('wc-student')
);


stomp.connect({}, function () {
    paperStudent.startListen()
    examerStudent.startListen()
    wcStudent.startListen()
});
