import { useEffect, useState } from "react";
import axios from "axios";
import styles from "./Admin-css/table.module.css";
import { toast } from "react-toastify";
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
            .then((res)=>{toast.success(res.data);
                      CustomerClaims();
            })
            .catch((err)=>{toast.error(err?.response?.data.Body)});
        }
        else if(status === "REJECT"){
            axios.put(`http://localhost:8089/AdminAccess/claim-Reject/${Id}`,{},{
            headers: {
                Authorization: `Bearer ${localStorage.getItem("keyToken")}`
            }
            })
            .then((res)=>{toast.success(res.data);
                      CustomerClaims();
            })
            .catch((err)=>{toast.error(err?.response?.data.Body)});
        }
    }

    return(
        <>
        <div>
            <table className={styles.table}>
                <thead>
                    <tr>
                        <th>claimId</th>
                        <th>policyName</th>
                        <th>userEmailId</th>
                        <th>claimAmount</th>
                        <th>claimDate</th>
                        <th>adminApproval</th>
                        <th>Approval</th>
                        <th>Rejection</th>
                    </tr>
                </thead>

                <tbody>
                    {claim.length > 0? (
                    claim.map((claims)=> (
                        <tr key={claims.claimId}>
                            <td>{claims.claimId}</td>
                            <td>{claims.policyName}</td>
                            <td>{claims.userEmailId}</td>
                            <td>{claims.claimAmount}</td>
                            <td>{claims.claimDate}</td>
                            <td>{claims.adminApproval}</td>
                            <td><button className={styles.accept} onClick={()=>{ApproveOrRejectClaims(claims.claimId,"APPROVE")}}>Approve</button></td>
                            <td><button className={styles.reject} onClick={()=>{ApproveOrRejectClaims(claims.claimId,"REJECT")} }>Reject</button></td>
                        </tr>
                    ))): (
                        <tr><td colSpan={8} style={{ textAlign: "center" }}>
                            No Claim Available
                        </td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
        </>
    )
}
export default AdminClaimView;