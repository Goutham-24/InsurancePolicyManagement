import { useState } from "react";
import axios from "axios";
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
        .then((res)=>{console.log("done");
                      setclaim({
                        "userPolicyId":"",
                        "amount":"" });
        });

    }

    return(
        <>
        <div>
            <form onSubmit={postclaim}>
                <input type="text" name="userPolicyId" value={claim.userPolicyId} onChange={claimupdates} placeholder="Enter the User Policy ID" required />
                <input type="number" name="amount" value={claim.amount} onChange={claimupdates} placeholder="Enter the claim Amount " required />
                <button type="submit">Submit Claim</button>
            </form>
        </div>
        </>
    )
}
export default CustomerClaimAppeal;