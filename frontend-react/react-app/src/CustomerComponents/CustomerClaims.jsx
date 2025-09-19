import { useEffect, useState } from "react";
import axios from 'axios';
import styles from "./customers-css/table.module.css";
function CustomerClaims(){

    const [Claims,setClaims] = useState([]);
    useEffect(()=>{
        axios.get(`http://localhost:8089/CustomerAccess/getAllClaims`,{
            headers: {
                Authorization: `Bearer ${localStorage.getItem("keyToken")}`
            }
        })
        .then((res)=>{setClaims(res.data);
                      console.log(res.data);
        });
    },[]);
    return(
        <>
        <div>
            <table className={styles.table}>
                <thead>
                    <tr>
                        <th>claimId</th>
                        <th>policyName</th>
                        <th>claimAmount</th>
                        <th>claimDate</th>
                        <th>finalStatus</th>
                    </tr>
                </thead>

                <tbody>
                    {Claims.map((userclaim)=> (
                        <tr key={userclaim.claimId}>
                            <td>{userclaim.claimId}</td>
                            <td>{userclaim.policyName}</td>
                            <td>{userclaim.claimAmount}</td>
                            <td>{userclaim.claimDate}</td>
                            <td>{userclaim.finalStatus}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
        </>
    )
}
export default CustomerClaims;