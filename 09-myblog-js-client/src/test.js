const a = [1,2,3,4,5,6,7,8];

let [a1, a2, ...rest] = a;

// console.log(`${a1}| ${a2}| ${rest}`);npm sy

let persons = [
    { name: 'Michael Harrison',
      parents: {
        mother: 'Melinda Harrison',
        father: 'Simon Harrison',
      }, age: 35},
    { name: 'Robert Moore',
      parents: {
        mother: 'Sheila Moore',
        father: 'John Moore',
      }, age: 25}];
  for (let {name: n, parents: { father: f }, age } of persons) {
    console.log(`Name: ${n}, Father: ${f}, age: ${age}`);
  }

