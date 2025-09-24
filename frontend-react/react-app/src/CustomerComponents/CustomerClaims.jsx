import { useEffect, useState } from "react";
import axios from 'axios';
import styles from "./customers-css/table.module.css";
import { toast } from "react-toastify";
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
        })
        .catch((err)=>{toast.error("something went wrong")});
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
                    {Claims.length > 0? (
                    Claims.map((userclaim)=> (
                        <tr key={userclaim.claimId}>
                            <td>{userclaim.claimId}</td>
                            <td>{userclaim.policyName}</td>
                            <td>{userclaim.claimAmount}</td>
                            <td>{userclaim.claimDate}</td>
                            <td>{userclaim.finalStatus}</td>
                        </tr>
                    ))) : (
                        <tr><td colSpan={"5"} style={{textAlign:"center"}}>
                            No Claims Yet...
                        </td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
        </>
    )
}
export default CustomerClaims;