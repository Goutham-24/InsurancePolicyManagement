import {useState} from 'react'
import axios from 'axios'

function Login(){
    const [credentials,setcredentials] = useState({ "userEmailId":"",
                                                    "userPassword":""});

    const [name,setname] = useState(); 

    function usercredentails(e){
        e.preventDefault(); 
        setcredentials({
                        ...credentials,
                        [e.target.name]:e.target.value});
    }

    function Logincall(e){
        e.preventDefault(); 
        axios.post(`http://localhost:8089/Authentication/login`,credentials,{
            responseType:"text"
        })
        .then((res)=>{localStorage.setItem("keyToken",res.data);
                     console.log(localStorage.getItem("keyToken"));
        });

    }

    
    function getLocalstore(){
        return localStorage.getItem("keyToken");
    }

    function getusername(){
        axios.get(`http://localhost:8089/CustomerAccess/getUserName`,{
            headers: {
                Authorization: `Bearer ${localStorage.getItem("keyToken")}`
            },
            responseType:"text"
        })
        .then((res)=>{console.log(res.data)});
    }


    return(
        <>
        <div>
            <form onSubmit={Logincall}>
                <input type="text" name="userEmailId" value={credentials.userEmailId} onChange={usercredentails} placeholder="user@gmail.com" required/>
                <input type="Password" name="userPassword" value={credentials.userPassword} onChange={usercredentails} placeholder="Enter password" required/>
                <button type='submit'>Submit</button>
            </form>

            <button onClick={getusername}>click to get name</button>
        </div>
        </>
    )
}
export default Login;