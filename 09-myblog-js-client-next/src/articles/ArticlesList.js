import React, { useState } from 'react';
import ARTICLES from './mock-articles';
import { Row, Col, Button, Icon } from 'react-materialize';
import Article from './Article';

function ArticlesList({articles}) {
//   const [articles, setArticles] = useState(ARTICLES);

  return (
    <Row className="articles-main">
        <Col s={8} m={4}>
        {
            articles.map(article => (
                <Article key={article.id} article={article} />
            ))
        }
        </Col>
        <Col s={12} m={4}>
        <div>
            <Button waves="light" onClick={ () => {} }>
                Add Article
                <Icon right>add_box</Icon>
            </Button>
        </div>
        </Col>
    </Row>
  );
}

export default ArticlesList;