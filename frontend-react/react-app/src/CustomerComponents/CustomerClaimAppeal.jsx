import { useState } from "react";
import axios from "axios";
import styles from "./customers-css/form.module.css";
import { toast } from "react-toastify";
function CustomerClaimAppeal(){
    const [claim,setclaim] = useState({
                                        "userPolicyId":"",
                                        "amount":""
    });

    function claimupdates(e){
        setclaim({
                  ...claim,
                  [e.target.name]:e.target.value
        });
    }

    function postclaim(e){
        e.preventDefault();

        axios.post(`http://localhost:8089/CustomerAccess/claim/${claim.userPolicyId}/${claim.amount}`,{},{
            headers: {
                Authorization: `Bearer ${localStorage.getItem("keyToken")}`
            }
            
            
        })
        .then((res)=>{toast.success(res.data);
                      setclaim({
                        "userPolicyId":"",
                        "amount":"" });
        })
        .catch((err)=>{
                       let store = err?.response?.data;
                       if(typeof store === "string"){
                        toast.error(err?.response?.data);
                       }
                       else{
                        toast.error(err?.response?.data.Body);
                       }
        });

    }

    return(
        <>
        <div className={styles.container}>
            <h2>CLAIM APPEAL</h2>
            <form onSubmit={postclaim}>
                <div className={styles.inputholder}> 
                <label htmlFor="userPolicyId">User Policy ID</label>
                <input type="text" name="userPolicyId" value={claim.userPolicyId} onChange={claimupdates} placeholder="Enter the User Policy ID" required />
                </div>

                <div className={styles.inputholder}>
                <label htmlFor="amount">Appreal Amount</label>
                <input type="number" name="amount" value={claim.amount} onChange={claimupdates} placeholder="Enter the claim Amount " required />
                </div>

                <button type="submit" className={styles.submit}>Submit</button>
            </form>
        </div>
        </>
    )
}
export default CustomerClaimAppeal;