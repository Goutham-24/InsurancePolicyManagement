import { useEffect, useState } from "react";
import axios from 'axios';
import styles from "./customers-css/table.module.css";
function CustomerPolicy(){
    const [userPolicy,setUserPolicy] = useState([]);
    useEffect(()=>{
        axios.get(`http://localhost:8089/CustomerAccess/MyPolicies`,{
            headers: {
                Authorization: `Bearer ${localStorage.getItem("keyToken")}`
            }
        })
        .then((res)=>{setUserPolicy(res.data);
                      console.log(res.data);
        });
    },[]);
    return(
        <>
        <div>
            <table className={styles.table}>
                <thead>
                    <tr>
                        <th>userPolicyId</th>
                        <th>policy ID</th>
                        <th>policy Name</th>
                        <th>purchaseDate</th>
                        <th>expiryDate</th>
                        <th>status</th>
                    </tr>
                </thead>

                <tbody>
                    {userPolicy.length > 0 ? (
                    userPolicy.map((policies)=> (
                        <tr key={policies.userPolicyId}>
                            <td>{policies.userPolicyId}</td>
                            <td>{policies.policy.policyId}</td>
                            <td>{policies.policy.policyName}</td>
                            <td>{policies.purchaseDate}</td>
                            <td>{policies.expiryDate}</td>
                            <td>{policies.status}</td>
                        </tr>
                    ))) :(
                        <tr><td colSpan="6" style={{textAlign: "center"}}>
                            No Policy Bought Yet...
                        </td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
        </>
    )
}
export default CustomerPolicy;
