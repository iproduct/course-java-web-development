import React from 'react';

const Article = ({value}) => (<div>{value.id} -> {value.title} : {value.content}</div>);
export default Article;
