import {useState} from 'react'
import axios from 'axios'
import {jwtDecode} from "jwt-decode"; 
import {useNavigate} from "react-router-dom";
import { Link } from 'react-router-dom';
import styles from "./Auth-css/Login.module.css"
import { toast } from 'react-toastify';
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
                     
                     toast.success("Successfulluy Logged-In");

                     if(localStorage.getItem("keyToken")){
                        const role = localStorage.getItem("userrole");
                        if(role == "ROLE_CUSTOMER"){
                            navigate("/customer-dashboard/All-Policies");
                        }
                        else if(role == "ROLE_AGENT"){
                            navigate("/agent-dashboard/Customer-Claims");
                        }
                        else if(role == "ROLE_ADMIN"){
                            navigate("/admin-dashboard/Claim-view");
                        }
                     }
                     
                     
        })
        .catch((err)=> {
                       const store = err?.response?.data;
                       
                       try{
                            let errors = JSON.parse(store);
                            Object.values(errors).forEach((val)=>{toast.error(val)});
                       }
                       catch(e){
                            toast.error(store);
                       }
        });

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
        <div className={styles.formcontainer}>
            <h2>LOGIN</h2>
            <form onSubmit={Logincall} >
                <div className={styles.formgroup}>
                <label htmlFor="userEmailId">EmailId</label>
                <input type="text" name="userEmailId" value={credentials.userEmailId} onChange={usercredentails} placeholder="user@gmail.com" required/>
                </div>

                <div className={styles.formgroup}>
                <label htmlFor="userPassword">Password</label>
                <input type="Password" name="userPassword" value={credentials.userPassword} onChange={usercredentails} placeholder="Enter password" required/>
                </div>
                
                <br />
                <button type='submit' className={styles.btn}>Submit</button>
            </form>
            <Link to={"/Signup"}><button className={styles.btn2}>Signup</button></Link>
        </div>
        </>
    )
}
export default Login;