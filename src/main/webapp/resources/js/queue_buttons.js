'use strict';

const e = React.createElement;

//var ws = new SockJS("/ws");
//var stomp = Stomp.over(ws);



class EnqueueButton extends React.Component {
    constructor(props) {
        super(props);
        this.state = {disabled: false};
        this.queueName = props.queueName;
        this.buttonName = props.buttonName;
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        console.log(this.buttonName);
        console.log(this.queueName);
        /*
        fetch('http://' + window.location.host + '/enqueue/' + this.queueName)
        this.setState(state => ({
            disabled: !state.disabled
        }));

         */
    }

    render() {
        var name = this.buttonName
        return (
            <button onClick={this.handleClick}> name </button>
        );
    }
}

ReactDOM.render(
    <EnqueueButton queueName='blank paper' buttonName="რიგში ჩადგომა" />,
    document.getElementById('paper-enqueue')
);

ReactDOM.render(
    <EnqueueButton queueName='blank paper' buttonName="მოთხოვნის გაუქმება" />,
    document.getElementById('paper-cancel')
);

ReactDOM.render(
    <EnqueueButton queueName='call examer' buttonName="რიგში ჩადგომა" />,
    document.getElementById('examer-enqueue')
);

ReactDOM.render(
    <EnqueueButton queueName='call examer' buttonName="მოთხოვნის გაუქმება" />,
    document.getElementById('examer-cancel')
);

ReactDOM.render(
    <EnqueueButton queueName='wc' buttonName="რიგში ჩადგომა" />,
    document.getElementById('wc-enqueue')
);

ReactDOM.render(
    <EnqueueButton queueName='wc' buttonName="მოთხოვნის გაუქმება" />,
    document.getElementById('wc-cancel')
);


/*
const domContainer = document.querySelector('paper-stud-button');
ReactDOM.render(<button queueName="Blank Paper Queue" buttonName="შავი ფურცლის მოთოხვნა"/>, domContainer);

class CancelButton extends React.Component {
    constructor(props) {
        super(props);
        this.state = {disabled: false};
        this.queueName = props.queueName;
        this.buttonName = props.buttonName;
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        fetch('http://' + window.location.host + '/cancel-waiting/' + this.queueName)
        this.setState(state => ({
            disabled: !state.disabled
        }));
    }

    render() {
        return (
            <button onClick={this.handleClick}> name={this.buttonName} </button>
        );
    }
}


class DequeueButton extends React.Component {
    constructor(props) {
        super(props);
        this.state = {disabled: false};
        this.queueName = props.queueName;
        this.buttonName = props.buttonName;
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        fetch('http://' + window.location.host + '/admin/dequeue/' + this.queueName)
        this.setState(state => ({
            disabled: !state.disabled
        }));
    }

    render() {
        return (
            <button onClick={this.handleClick}> name={this.buttonName} </button>
        );
    }
}
class CancelButton extends React.Component {
    constructor(props) {
        super(props);
        this.state = {disabled: false};
        this.queueName = props.queueName;
        this.buttonName = props.buttonName;
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        fetch('http://' + window.location.host + '/admin/clear/' + this.queueName)
        this.setState(state => ({
            disabled: !state.disabled
        }));
    }

    render() {
        return (
            <button onClick={this.handleClick}> name={this.buttonName} </button>
        );
    }
}
 */