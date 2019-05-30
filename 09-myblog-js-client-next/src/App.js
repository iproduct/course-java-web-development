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
    	});
	}
	
	render() {
      return (<ArticlesList articles={this.state.articles} />);
	}

}

export default App;
