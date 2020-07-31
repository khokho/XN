'use strict';

function Post(props){
    return (
        <div className={'post'}>
            {/*<p>{props.post.text + " BY " + props.post.fromId}</p>*/}
            <div className="card">
                <div className="card-header">
                    {props.post.exam} Announcement
                </div>
                <div className="card-body">
                    <blockquote className="blockquote mb-0">
                        <p>{props.post.text}</p>
                        <footer className="blockquote-footer"><cite title="Source Title">{props.post.lecturer}</cite></footer>
                    </blockquote>
                </div>
            </div>
        </div>
    );
}

class Posts extends React.Component{

    constructor(props) {
        super(props);
        this.state = {posts:[]}

        fetch('http://'+window.location.host + "/getPosts")
            .then(resp => {
                console.log("lol")
                console.log(resp)
                return resp.json()
            })
            .then((jsonData) => {
                console.log(jsonData)
                this.setState({posts:jsonData})
            })
    }

    render(){
        return this.state.posts.map((post)=>(
            <Post post={post}/>
        ))
    }
}

class NewPosts extends React.Component{
    render(){
        return (
            <form action={"/newPost"} method={"post"}>
                <label>
                    Name:
                    <input type="text" name="text" />
                    <input type="hidden" name="examId" value={window.examId}/>
                </label>
                <input type="submit" value="Submit" />
            </form>
        );
    }
}

const postsContainer = document.querySelector('#posts');
const newPost = document.querySelector('#newPost');
ReactDOM.render(<Posts/>, postsContainer);
ReactDOM.render(<NewPosts/>, newPost);
