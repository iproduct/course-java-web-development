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
  for (const {name: n, parents: { father: f }, age } of persons) {
    console.log(`Name: ${n}, Father: ${f}, age: ${age}`);
  }

const p1 = new Promise((resolve) => {setTimeout(resolve, 1000, 'one');});
const p2 = new Promise((resolve) => {setTimeout(resolve, 2000, 'two');});
const p3 = new Promise((res, reject) => {setTimeout(()=> reject('rejected'), 3000, 'three');});
Promise.race([p1, p2, p3]).then(v => console.log('Success:', v))
.catch( err => console.log('Error:', err)).then( () => console.log('Demo finished'));


// for (let  i = 0; i < 5; i++) { 
//   setTimeout(function(){console.log(i); }, 1000 * i);}

function f({a, b = 0} = {a: "!"}) { 	console.log(a, b); } 
f({a: "ok"})
f()
f({})

class Person { 
	constructor(name) { 
           this.name = name; 
           this.username = name.split(/\s+/)[0].toLowerCase(); 
      } 
} 
class Employee extends Person { 
	constructor(name, organization) { 
            super(name);
            this.organization = organization;
      } 
	getInfo(){return `I'm ${this.name} [username: ${this.username}] from ${this.organization}`;} 
} 
let  john = new Employee('Ivan Petrov', 'FMI'); 
console.log(john.getInfo()); 
