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
    return (
      <ul>
          {this.state.articles.map(a => (<li>{a.title}</li>))}
      </ul>
    );
  }

}

export default App;
