import { useState } from "react";
import axios from "axios";
import styles from "./Auth-css/Signup.module.css";
import { Navigate, useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import { toast } from "react-toastify";
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
        .then((res)=>{toast.success(res.data);
                     Navigate("/Login");
        })
        .catch((err)=>{
            
            const store = err?.response?.data;
            console.log(store.userEmailId);

            Object.values(store).forEach(msg => {
                toast.error(msg);
            })});
    }
    return(
        <>
         <div className={styles.formcontainer}>
            <h2>SIGN-UP</h2>
            <form onSubmit={Logincall}>
                <div className={styles.formgroup}>
                <label htmlFor="userEmailId">EmailID</label>
                <input type="text" name="userEmailId" value={credentials.userEmailId} onChange={usercredentails} placeholder="user@gmail.com" required/>
                </div>

                <div className={styles.formgroup}>
                <label htmlFor="userPassword">Password</label>
                <input type="Password" name="userPassword" value={credentials.userPassword} onChange={usercredentails} placeholder="Enter password" required/>
                </div>
                
                <button type='submit' className={styles.btn}>Submit</button>
            </form>
            <br />
            <Link to={"/Login"}> <button className={styles.btn2}>Login</button> </Link>
            
        </div>
        </>
    )
}
export default Signup;