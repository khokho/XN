'use strict';

const e = React.createElement;

//var ws = new SockJS("/ws");
//var stomp = Stomp.over(ws);


class DequeueButton extends React.Component {
    constructor(props) {
        super(props);
        this.state = {disabled: false};
        this.buttonName = props.buttonName;
        this.queueName = props.queueName;
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        console.log(this.queueName)
        fetch('http://' + window.location.host + '/admin/dequeue/' + this.queueName)
        this.setState(state => ({
            disabled: !state.disabled
        }));
    }

    render() {
        return (
            <button disabled={this.state.disabled} onClick={this.handleClick}> {this.buttonName} </button>
        );
    }
}
class ClearButton extends React.Component {
    constructor(props) {
        super(props);
        this.state = {disabled: false};
        this.buttonName = props.buttonName;
        this.queueName = props.queueName;
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        console.log(this.queueName)
        fetch('http://' + window.location.host + '/admin/clear-queue/' + this.queueName)
        this.setState(state => ({
            disabled: !state.disabled
        }));
    }

    render() {
        return (
            <button disabled={this.state.disabled} onClick={this.handleClick}> რიგის გასუფთავება</button>
        );
    }
}

ReactDOM.render(
    <DequeueButton queueName='blank paper' buttonName = 'შავი ფურცლის მიცემა' />,
    document.getElementById('paper-dequeue')
);

ReactDOM.render(
    <ClearButton queueName='blank paper' />,
    document.getElementById('paper-clear')
);

ReactDOM.render(
    <DequeueButton queueName='call examer' buttonName = 'დამკვირვებლის მისვლა'/>,
    document.getElementById('examer-dequeue')
);

ReactDOM.render(
    <ClearButton queueName='call examer'/>,
    document.getElementById('examer-clear')
);

ReactDOM.render(
    <DequeueButton queueName='wc' buttonName = 'სტუდენტის გაშვება'/>,
    document.getElementById('wc-dequeue')
);

ReactDOM.render(
    <ClearButton queueName='wc' />,
    document.getElementById('wc-clear')
);