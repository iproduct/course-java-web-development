import React from 'react';
import './App.css';


class App extends React.Component {
  constructor(){
    super();
    this.state= {articles:[{title:'title1'}]};
    fetch('http://localhost:8080/api/articles').then(
      resp => {
        resp.json().then(art => {
          console.log(art);
          this.setState({articles:art});
        });
        
      }
    );
  }

  render(props) {
    let result = [];
    console.log(this.state);
    for(let i = 0; i < this.state.articles.length; i ++) {
      result.push( (<li>{this.state.articles[i].title}</li>) );
    }
    console.log(result);
    return (
      <ul>
          {result}
      </ul>
    );
  }

}

export default App;
