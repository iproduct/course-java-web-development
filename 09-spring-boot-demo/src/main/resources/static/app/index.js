//const myelement = (<p>Hello, world! I am new  CommentBox.</p>);
//const myelement2 = React.createElement('p',{},'Hello, world! I am new  CommentBox 2.' );

class App extends React.Component {
	state = {
		articles:[]
	};
	
	constructor(props){
		super(props);
		this.fetchData().then(
			() => console.log('Data fetched')
		);
	}
	
	async fetchData() {
		try {
			const resp = await fetch('http://localhost:8080/api/articles')
	    	const articles = await resp.json();
	    	console.log(articles);
			this.setState({articles});
		} catch (err) {
			console.log(err);
		}
	}
	
	render() {
	    return this.state.articles.map(
		  article => (<Article key={article.id} value={article} />)
	    );
	}

}

const Article = ({value}) => (<div>{value.id} -> {value.title} : {value.content}</div>);

ReactDOM.render(<App />, document.getElementById('root'));