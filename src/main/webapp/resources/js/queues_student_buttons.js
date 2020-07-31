'use strict';

const e = React.createElement;

//var ws = new SockJS("/ws");
//var stomp = Stomp.over(ws);



function enableButton(obj, disabled) {
    obj.setState({disabled})
}

class EnqueueButton extends React.Component {
    constructor(props) {
        super(props);
        this.state = {disabled: false};
        this.queueName = props.queueName;
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        console.log(this.queueName);
        fetch('http://' + window.location.host + '/enqueue/'+this.queueName)
        this.setState(state => ({
            disabled: !state.disabled
        }));
        switch (this.queueName) {
            case "blank paper":
                enableButton(paperCancel,false)
                break;
            case "call examer":
                enableButton(examerCancel,false)
                break;
            case "wc":
                enableButton(wcCancel,false)
                break;
        }
    }

    render() {
        return (
            <button disabled={this.state.disabled} onClick={this.handleClick}> რიგში ჩადგომა </button>
        );
    }
}

class CancelButton extends React.Component {
    constructor(props) {
        super(props);
        this.state = {disabled: true};
        this.queueName = props.queueName;
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        console.log(this.queueName);
        fetch('http://' + window.location.host + '/cancel-waiting/' + this.queueName)
        this.setState(state => ({
            disabled: !state.disabled
        }));
        switch (this.queueName) {
            case "blank paper":
                enableButton(paperEnq,false)
                break;
            case "call examer":
                enableButton(examerEnq,false)
                break;
            case "wc":
                enableButton(wcEnq,false)
                break;
        }
    }

    render() {
        return (
            <button disabled={this.state.disabled} onClick={this.handleClick}> "მოთხოვნის გაუქმება" </button>
        );
    }
}



var paperEnq = ReactDOM.render(
    <EnqueueButton queueName='blank paper' />,
    document.getElementById('paper-enqueue')
);

var paperCancel = ReactDOM.render(
    <CancelButton queueName='blank paper' />,
    document.getElementById('paper-cancel')
);

var examerEnq = ReactDOM.render(
    <EnqueueButton queueName='call examer' />,
    document.getElementById('examer-enqueue')
);


var examerCancel = ReactDOM.render(
    <CancelButton queueName='call examer'/>,
    document.getElementById('examer-cancel')
);

var wcEnq = ReactDOM.render(
    <EnqueueButton queueName='wc'/>,
    document.getElementById('wc-enqueue')
);

var wcCancel = ReactDOM.render(
    <CancelButton queueName='wc' />,
    document.getElementById('wc-cancel')
);
