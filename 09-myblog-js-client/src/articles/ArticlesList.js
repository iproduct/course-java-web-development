import React from 'react';
import { Row, Col, Button, Icon } from 'react-materialize';
import Article from './Article';

function ArticlesList(props) {
  const {articles} = props;
  return (
    <Row className="articles-main">
        <Col s={12} m={4}>
        {
            articles.map(article => (
                <Article key={article.id} article={article} />
            ))
        }
        </Col>
        <Col s={12} m={8}>
        <div>
            <Button waves="light" onClick={() => {}}>
                Add Article
                <Icon right>add_box</Icon>
            </Button>
        </div>
        </Col>
    </Row>
  );
}

export default ArticlesList;