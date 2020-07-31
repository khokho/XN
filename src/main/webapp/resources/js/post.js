'use strict';

function Post(props){
    return (
        <div className={'post'}>
            {/*<p>{props.post.text + " BY " + props.post.fromId}</p>*/}
            <div className="card">
                <div className="card-header">
                    {props.post.exam} Announcement
                    <form action={"/removePost/"+window.examId} method={"post"}>
                        <input type={"submit"} value={"remove"}/>
                        <input type="hidden" name="postId" value={props.post.postId}/>
                    </form>
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
    }

    render(){
        return this.state.posts.map((post)=>(
            <Post post={post}/>
        ))
    }
}
//
// class NewPosts extends React.Component{
//     render(){
//         return (
//
//             // <div className="card">
//             //     <div className="card-body">
//             //         <blockquote className="blockquote mb-0">
//                         <form action={"/newPost"} method={"post"} name={"text-box"}>
//                             <label>
//                                 <input type="text" name="text" placeholder="Talk to me baby..."/>
//                                 <input type="hidden" name="examId" value={window.examId}/>
//                             </label>
//
//                             <input type="submit" value="Submit" />
//                         </form>
//             //         </blockquote>
//             //     </div>
//             // </div>
//
//
//         );
//     }
// }

class NewPosts extends React.Component{
    render(){
        return (

            <div className="card">
            <div className="card-header">
                Announcement
            </div>
                <form action={"/newPost"} method={"post"} name={"text-box"}>
                        <div className="form-group shadow-textarea">
                        <textarea className="form-control z-depth-1" id="exampleFormControlTextarea6" rows="4"
                                  placeholder="Write something here..." name={"text"}></textarea>
                        </div>
                        <input type="hidden" name="examId" value={window.examId}/>
                    <input type="submit" value="Submit" />
                </form>

            </div>


        );
    }
}

const postsContainer = document.querySelector('#posts');
const newPost = document.querySelector('#newPost');
ReactDOM.render(<Posts/>, postsContainer);
ReactDOM.render(<NewPosts/>, newPost);
