import { useState } from "react";
import axios from 'axios';

function AddPolicy(){

    const [Policy,setPolicy] = useState({"policyName":"",
                                         "premiumAmount":"",
                                         "validTo":"",
                                         "userPolicyValidity":""});  

    function sendPolicytoBackend(e){
        e.preventDefault;

        axios.post(`http://localhost:8089/AdminAccess/AddPolicy`,Policy,{
            headers: {
                Authorization: `Bearer ${localStorage.getItem("keyToken")}`
            },
            responseType:"text"
        })
        .then((res)=>{console.log(res.data)});

    }

    
    function createPolicy(e){
        e.preventDefault;
        setPolicy({
                    ...Policy,
                    [e.target.name]:e.target.value});
    }

    return(
        <>
            <div>
                <form onSubmit={sendPolicytoBackend}>
                <input type="text" name="policyName" value={Policy.policyName} onChange={createPolicy} placeholder='policyName' required/>
                <input type="number" name="premiumAmount" value={Policy.premiumAmount} onChange={createPolicy} placeholder='premiumAmount' required/>
                <input type="text" name="validTo" value={Policy.validTo} onChange={createPolicy} placeholder='validTo' required/>
                <input type="number" name="userPolicyValidity" value={Policy.userPolicyValidity} onChange={createPolicy} placeholder='userPolicyValidity' required/>
                <button type='submit'>Submit</button>
                </form>
            </div>
        </>
    )
}
export default AddPolicy;