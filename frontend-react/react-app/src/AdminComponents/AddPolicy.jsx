import { useState } from "react";
import axios from 'axios';
import styles from "./Admin-css/forms.module.css";
function AddPolicy(){

    const [Policy,setPolicy] = useState({"policyName":"",
                                         "premiumAmount":"",
                                         "validTo":"",
                                         "userPolicyValidity":""});  

    function sendPolicytoBackend(e){
        e.preventDefault();

        axios.post(`http://localhost:8089/AdminAccess/AddPolicy`,Policy,{
            headers: {
                Authorization: `Bearer ${localStorage.getItem("keyToken")}`
            }
        })
        .then((res)=>{console.log("added policy")});

    }

    
    function createPolicy(e){
        
        setPolicy({
                    ...Policy,
                    [e.target.name]:e.target.value});
    }

    return(
        <>
            <div className={styles.container}>
                <h2>ADD POLICY</h2>
                <form onSubmit={sendPolicytoBackend}>
                    <div className={styles.inputholder}>
                        <label htmlFor="policyName">Policy Name</label>
                        <input type="text" name="policyName" value={Policy.policyName} onChange={createPolicy} placeholder='policyName' required/>
                    </div>

                    <div className={styles.inputholder}>
                        <label htmlFor="premiumAmount">Premium Amount</label>
                        <input type="number" name="premiumAmount" value={Policy.premiumAmount} onChange={createPolicy} placeholder='premiumAmount' required/>
                    </div>

                    <div className={styles.inputholder}>
                        <label htmlFor="validTo">Policy Vaidity Date</label>
                        <input type="text" name="validTo" value={Policy.validTo} onChange={createPolicy} placeholder='validTo' required/>
                    </div>

                    <div className={styles.inputholder}>
                        <label htmlFor="userPolicyValidity"> Valid Upto</label>
                        <input type="number" name="userPolicyValidity" value={Policy.userPolicyValidity} onChange={createPolicy} placeholder='userPolicyValidity' required/>
                    </div>
                
                
                
                
                <button type='submit' className={styles.submit}>Submit</button>
                </form>
            </div>
        </>
    )
}
export default AddPolicy;