import { useEffect, useState } from "react";
import axios from "axios";
import { Navigate, useNavigate } from "react-router-dom";
import styles from "./Agent-css/froms.module.css";
import { toast } from "react-toastify";

function AgentViewClaims(){
    const Navigate = useNavigate();
    const [Claimlist,setClaimlist] = useState([]);

    function allCustomerClaims(){
        axios.get(`http://localhost:8089/AgentAccess/getClaims`,{
            headers: {
                Authorization: `Bearer ${localStorage.getItem("keyToken")}`
            }
        })
        .then((res)=>{console.log(res);
                      setClaimlist(res.data || []);
                      console.log(res.data);
        });
    }
    useEffect(()=>{
        allCustomerClaims();
    },[]);

    function ClaimApproveOrReject(claimId,status){
        if(status === "APPROVE"){
            axios.put(`http://localhost:8089/AgentAccess/ClaimApproval/${claimId}`,{},{
            headers: {
                Authorization: `Bearer ${localStorage.getItem("keyToken")}`
            }
        })
        .then((res)=>{toast.success(res.data);
                      allCustomerClaims();
        });
        }

        else if(status === "REJECT"){
            axios.put(`http://localhost:8089/AgentAccess/ClaimDenial/${claimId}`,{},{
            headers: {
                Authorization: `Bearer ${localStorage.getItem("keyToken")}`
            }
        })
        .then((res)=>{toast.info(res.data);
                      allCustomerClaims();
        });
        }
    }
    return(
        <>
        <div>
            <table className={styles.table}>
                <thead>
                    <tr>
                        <th>claim Id</th>
                        <th>policyName</th>
                        <th>userEmailId</th>
                        <th>claimAmount</th>
                        <th>claimDate</th>
                        <th>agentApproval</th>
                        <th>Approval</th>
                        <th>Rejection</th>

                    </tr>
                </thead>

                <tbody>
                    {Claimlist.length > 0 ? (
                    Claimlist.map((claims)=>(
                        <tr key={claims.claimId}>
                            <td>{claims.claimId}</td>
                            <td>{claims.policyName}</td>
                            <td>{claims.userEmailId}</td>
                            <td>{claims.claimAmount}</td>
                            <td>{claims.claimDate}</td>
                            <td>{claims.agentApproval}</td>
                            <td><button className={styles.accept} onClick={()=>{ClaimApproveOrReject(claims.claimId,"APPROVE")}}>Approve</button></td>
                            <td><button className={styles.reject} onClick={()=>{ClaimApproveOrReject(claims.claimId,"REJECT")}}>Reject</button></td>
                        </tr>
                    ))):(
                        <tr><td colSpan="8" style={{ textAlign: "center" }}>
                            No claims available
                        </td>
                        </tr>
                    )
                    }
                </tbody>
            </table>
        </div>
        </>
    )
}
export default AgentViewClaims;