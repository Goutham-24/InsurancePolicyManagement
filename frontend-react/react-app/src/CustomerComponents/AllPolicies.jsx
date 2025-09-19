import { useEffect, useState } from "react";
import axios from 'axios';
import styles from "./customers-css/table.module.css";
function AllPolicies(){

    const [Policies,setPolicies] = useState([]);

    useEffect(()=>{
        axios.get(`http://localhost:8089/CustomerAccess/AllPolicies`,{
            headers: {
                Authorization: `Bearer ${localStorage.getItem("keyToken")}`
            }
        })
        .then((res)=>{console.log(res.data);
                      setPolicies(res.data);
        });
    },[]);

    function buyPolicy(Id){
        axios.post(`http://localhost:8089/CustomerAccess/BuyPolicy/${Id}`,{},{
            headers: {
                Authorization: `Bearer ${localStorage.getItem("keyToken")}`
            },
            responseType:"text"
        })
        .then((res)=>{console.log("done")})
        .catch((err)=> {console.error(err)})
    }

    return(
        <>
        <h2>All Policy place</h2>
        <br />
        <table className={styles.table}>
            <thead>
                <tr>
                    <th>Policy Name</th>
                    <th>Premium</th>
                    <th>Validity</th>
                    <th>Purchase</th>
                </tr>
            </thead>

            <tbody>
                {Policies.map((policy)=> (
                    <tr key={policy.policyId}>
                        <td>{policy.policyName}</td>
                        <td>{policy.premiumAmount}</td>
                        <td>{policy.userPolicyValidity}</td>
                        <td><button className={styles.accept} onClick={()=>{buyPolicy(policy.policyId)}}>Buy Policy</button></td>
                    </tr>
                ))}
            </tbody>
        </table>
        </>
    )
}
export default AllPolicies;