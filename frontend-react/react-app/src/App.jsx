import { useState } from 'react'
import './App.css'
import axios from 'axios'
import Login from './Auth/Login';

function App() {

  const [test,settest] = useState();

  function start(){
    axios.get(`http://localhost:8089/Authentication/test`)
      .then((response)=>{settest(response.data)
      console.log("this is the main app");
      });
  }
  return (
    <>
    <p>hello</p>
    <button onClick={start}>click</button>
    <p>{test}</p>

    <Login/>

    
    </>
  )
}

export default App;
