import { useState } from "react";
import axios from "axios";
import { Navigate, useNavigate } from "react-router-dom";
function Signup(){
    const Navigate = useNavigate();
    const [credentials,setcredentials] = useState({
                                                    "userEmailId":"",
                                                    "userPassword":""});

    function usercredentails(e){
        setcredentials({
                        ...credentials,
                        [e.target.name]:e.target.value
        });
    }

    function Logincall(e){
        e.preventDefault();

        axios.post(`http://localhost:8089/Authentication/signup`,credentials)
        .then((res)=>{alert("Successfully Added");
                     Navigate("/Login");
              
        });
    }
    return(
        <>
        <div>Sign-Up</div>
         <div>
            <form onSubmit={Logincall}>
                <input type="text" name="userEmailId" value={credentials.userEmailId} onChange={usercredentails} placeholder="user@gmail.com" required/>
                <input type="Password" name="userPassword" value={credentials.userPassword} onChange={usercredentails} placeholder="Enter password" required/>
                <button type='submit'>Submit</button>
            </form>
            
        </div>
        </>
    )
}
export default Signup;