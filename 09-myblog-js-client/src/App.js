import React from 'react';
import ArticlesList from './articles/ArticlesList';
import './App.css';

class App extends React.Component {
	state = {
		articles:[]
	};
	
	constructor(){
	super();
	fetch('http://localhost:8080/api/articles')
    	.then(resp => resp.json())
    	.then(articles => {
    		console.log(articles);
			this.setState({articles});
    	}).catch(err => {
			console.log(`Error: ${err}`);
		});
	}
	
	render() {
		return (
			<ArticlesList articles={this.state.articles} />
		);
	    // return this.state.articles.map(
		//   article => (<Article key={article.id} value={article} />)
	    // );
	}

}

export default App;
