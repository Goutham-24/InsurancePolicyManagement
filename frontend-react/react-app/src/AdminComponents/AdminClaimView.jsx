import { useEffect, useState } from "react";
import axios from "axios";
function AdminClaimView(){
    const [claim,setclaim] = useState([]);

    function CustomerClaims(){
        axios.get(`http://localhost:8089/AdminAccess/getAllClaims`,{
            headers: {
                Authorization: `Bearer ${localStorage.getItem("keyToken")}`
            }
        })
        .then((res)=>{setclaim(res.data);
                      console.log(res.data);});
    }

    useEffect(()=>{
        CustomerClaims();
    },[]);

    function ApproveOrRejectClaims(Id,status){
        if(status === "APPROVE"){
            axios.put(`http://localhost:8089/AdminAccess/claim-Approval/${Id}`,{},{
            headers: {
                Authorization: `Bearer ${localStorage.getItem("keyToken")}`
            }
            })
            .then((res)=>{console.log("Approved");
                      CustomerClaims();
            });
        }
        else if(status === "REJECT"){
            axios.put(`http://localhost:8089/AdminAccess/claim-Reject/${Id}`,{},{
            headers: {
                Authorization: `Bearer ${localStorage.getItem("keyToken")}`
            }
            })
            .then((res)=>{console.log("Reject");
                      CustomerClaims();
            });
        }
    }

    return(
        <>
        <div>
            <table>
                <thead>
                    <tr>
                        <th>claimId</th>
                        <th>policyName</th>
                        <th>userEmailId</th>
                        <th>claimAmount</th>
                        <th>claimDate</th>
                        <th>adminApproval</th>
                    </tr>
                </thead>

                <tbody>
                    {claim.map((claims)=> (
                        <tr key={claims.claimId}>
                            <td>{claims.claimId}</td>
                            <td>{claims.policyName}</td>
                            <td>{claims.userEmailId}</td>
                            <td>{claims.claimAmount}</td>
                            <td>{claims.claimDate}</td>
                            <td>{claims.adminApproval}</td>
                            <td><button onClick={()=>{ApproveOrRejectClaims(claims.claimId,"APPROVE")}}>Approve</button></td>
                            <td><button onClick={()=>{ApproveOrRejectClaims(claims.claimId,"REJECT")}}>Reject</button></td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
        </>
    )
}
export default AdminClaimView;