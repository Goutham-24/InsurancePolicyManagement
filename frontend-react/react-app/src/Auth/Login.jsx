import {useState} from 'react'
import axios from 'axios'
import {jwtDecode} from "jwt-decode"; 
import {useNavigate} from "react-router-dom";
import { Link } from 'react-router-dom';
function Login(){
    const navigate = useNavigate();
    
    const [credentials,setcredentials] = useState({ "userEmailId":"",
                                                    "userPassword":""});
                                             


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
                     const decoded = jwtDecode(res.data);
                     localStorage.setItem("userrole",decoded.role);
                     
                     if(localStorage.getItem("keyToken")){
                        const role = localStorage.getItem("userrole");
                        if(role == "ROLE_CUSTOMER"){
                            navigate("/customer-dashboard");
                        }
                        else if(role == "ROLE_AGENT"){
                            navigate("/agent-dashboard");
                        }
                        else if(role == "ROLE_ADMIN"){
                            navigate("/admin-dashboard");
                        }
                     }
                     
                     
        })
        .catch((err)=> alert(err.message));

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
                <br />
                <button type='submit'>Submit</button>
            </form>
            <br />
            <button onClick={getusername}>click to get name</button>
            <br />
            <Link to={"/Signup"}>Signup</Link>
            
        </div>
        </>
    )
}
export default Login;