const myelement = (<p>Hello, world! I am new  CommentBox.</p>);
const myelement2 = React.createElement('p',{},'Hello, world! I am new  CommentBox 2.' );

//class App extends React.Component {
//	state = {
//		articles:[]
//	};
//	
//	constructor(){
//	super();
//	fetch('http://localhost:8080/api/articles')
//    	.then(resp => resp.json())
//    	.then(articles => {
//    		console.log(articles);
//			this.setState({articles});
//    	});
//	}
//	
//	render() {
//	    return this.state.articles.map(
//		  article => (<Article key={article.id} value={article} />)
//	    );
//	}
//
//}
//
//const Article = ({value}) => (<div>{value.id} -> {value.title} : {value.content}</div>);
ReactDOM.render(myelement2, document.getElementById('root'));