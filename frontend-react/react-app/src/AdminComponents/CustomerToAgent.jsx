import { useState } from "react";
import axios from "axios";
import styles from "./Admin-css/forms.module.css";
function CustomerToAgent(){
    const [CustomerId,setCustomerId] = useState(0);

    function storeId(e){
        setCustomerId(e.target.value);
    }
    function converter(e){
        e.preventDefault();

        axios.put(`http://localhost:8089/AdminAccess/CustomerToAgent/${CustomerId}`,{},{
            headers: {
                Authorization: `Bearer ${localStorage.getItem("keyToken")}`
            }
        })
        .then((res)=>{console.log("Converted to Agent")});
    }

    return(
        <>
        <div className={styles.container}>
            <h2>CUSTOMER CONVERTOR</h2>
        <form onSubmit={converter}>
            <div className={styles.inputholder}>
            <label htmlFor="">Customer to Agent</label>
            <input type="number" name="CustomerId" value={CustomerId} onChange={storeId} placeholder="Enter Customer ID to Convert" required />
            </div>
            
            <button type="submit" className={styles.submit}>Convert</button>
        </form>
        </div>
        </>
    )
}
export default CustomerToAgent;