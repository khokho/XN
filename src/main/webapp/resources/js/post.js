'use strict';

var ws = new SockJS("/ws");
var stomp = Stomp.over(ws);

function EditPost(props) {
    props.post.editing = false;
    return (
        <form action={"/editPost"} method={"post"} name={"text-box"} target={"hidden-edit"}>
            <div className="card">
                <div className="card-header">
                    <div className={"p-2"}>Announcement</div>
                    <div className={"d-flex justify-content-end"}>
                        <input type="submit" value="Submit" />
                    </div>
                </div>
                <div className={"my-area"}>

                    <div className="form-group shadow-textarea">
                        <textarea className="h-100 form-control z-depth-1" id="newPostForm" rows="4"
                                  name={"newText"}>{props.post.text}</textarea>
                    </div>
                    <input type="hidden" name="postId" value={props.post.postId}/>
                </div>
            </div>
        </form>
        );
}

function changePostsEditingState(post) {
    this.setState((state) => {
        for(var i = state.posts.length - 1; i >= 0; i--) {
            if(state.posts[i].postId === post.postId) {
                state.posts[i].editing = true;
            }
        }
        return{posts: state.posts};
    })
}

function Post(props){
    return (
        <div className={'post'}>
            {/*<p>{props.post.text + " BY " + props.post.fromId}</p>*/}
            <div className="card">
                <div className="card-header d-flex">
                    <div className={"p-2"}>{props.post.exam} Announcement</div>
                        {props.post.fromId === window.userId &&
                        <div className={"d-flex justify-content-end"}>
                            <div className={"d-flex justify-content-end"}>
                            <button type="button" onClick={()=>{
                                changePostsEditingState(props.post)
                            }}>Edit</button>
                            </div>
                            <div className={"d-flex justify-content-end"}>
                            <form action={"/removePost/" + window.examId} method={"post"} target={"hidden-remove"}>
                            <input type={"submit"} value={"remove"}/>
                            <input type="hidden" name="postId" value={props.post.postId}/>
                        </form>
                        </div></div>
                        }
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

        fetch('http://'+window.location.host + "/getPosts/" + window.examId)
            .then(resp => {
                console.log("lol")
                console.log(resp)
                return resp.json()
            })
            .then((jsonData) => {
                console.log(jsonData)
                this.setState({posts:jsonData})
            })

        onmessage = (postJSON) => {
            console.log("here broz")
            console.log(postJSON)
            var post = JSON.parse(postJSON.body)
            console.log("teeext:" + post.text)
            if(post.action === "add") {
                this.setState((state) => {
                    return {posts: ([post]).concat(state.posts)}
                })
            } else if (post.action === "remove"){
                this.setState((state) => {
                    for(var i = state.posts.length - 1; i >= 0; i--) {
                        if(state.posts[i].postId === post.postId) {
                            state.posts.splice(i, 1);
                        }
                    }
                    return{posts: state.posts}
                })
            } else if (post.action === "edit"){
                this.setState((state) =>{
                    for(var i = state.posts.length - 1; i >= 0; i--) {
                        if(state.posts[i].postId === post.postId) {
                            state.posts[i].text = post.text;
                        }
                    }
                    return{posts: state.posts}
                })
            }
        }
        onmessage.bind(this)
        changePostsEditingState = changePostsEditingState.bind(this)

        stomp.connect({}, function () {
            stomp.subscribe("/topic/posts-" + window.examId, onmessage, {});
        });

    }

    render(){
        return this.state.posts.map((post)=> {
            // {post.editing === true
            //      ? (<editPost key={post.postId} post={post}/>)
            //      : (<Post key={post.postId} post={post}/>)
            // }
            if (post.editing === true)
                return (<EditPost key={post.postId} post={post}/>)
            else return (<Post key={post.postId} post={post}/>)
        })
    }
}

class NewPosts extends React.Component{
    render(){
        return (
            <form action={"/newPost"} method={"post"} name={"text-box"} target={"hidden-form"}>
            <div className="card">
            <div className="card-header">
                <div className={"p-2"}>Announcement</div>
                <div className={"d-flex justify-content-end"}>
                    <input type="submit" value="Submit" />
                </div>
            </div>
                <div className={"my-area"}>

                        <div className="form-group shadow-textarea">
                        <textarea className="h-100 form-control z-depth-1" id="newPostForm" rows="4"
                                  placeholder="Write something here..." name={"text"}/>
                        </div>
                        <input type="hidden" name="examId" value={window.examId}/>
                </div>
            </div>
            </form>

            );
    }
}

const postsContainer = document.querySelector('#posts');
const newPost = document.querySelector('#newPost');
ReactDOM.render(<Posts/>, postsContainer);
if(window.status === "lector") {
    ReactDOM.render(<NewPosts/>, newPost);
}
