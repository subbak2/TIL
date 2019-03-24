1. From ES6, we can use 'Class' in JS. -> React use this property as a 'JSX'.
  
  Ex> below - example of App.js
  
  import React, { Component } from 'react';
  import logo from './logo.svg';
  import './App.css';

  class App extends Component {
    render() {
      const text = "Are you awesome?";
      const condition = true;
      const style = {
        backgroundColor: 'gray',
        border : '1px solid black',
        height : '40px'
      }
      return (
        <div>
          <h1> hi man~~</h1>
          <h2> {text}</h2>
          {
            condition &&'true'
          }
          <div style={style}></div>
        </div>
      );
    }
  }
  export default App;
  
  -> In 'render(){}', there are codes like HTML, which is not strings nor templates. It is JSX.
  
  
  2. JSX looks like XML. These code are bundled by 'babel-loader'.
    Ex> var a = (
        <div>
          <h1> Awesome <b>React</b> </h1>
        </div>
        )
       
       babel-loader convert these to
       
       var a = React.createElement(
         "div",
         null,
         React.createElement(
         "h1",
         null,
         "Awesome ",
         React.createElement(
          "b",
          null,
          "React"
          )
         )
        );
        
        
3. Component must have only one root parent.
    Ex> return(
          <h1> </h1>
          <h2> </h2>
         );
        -> Failed to compile
        
        return(
         <div>
          <h1> </h1>
          <h2> </h2>
         </dix>
         );
        -> OK
  + v16 or later, can use Fragment for rendering many tags. 
     should 'import React, { Component, Fragment } from 'react';
     -> don't need to rendering unnecessary <div>.
     
 
