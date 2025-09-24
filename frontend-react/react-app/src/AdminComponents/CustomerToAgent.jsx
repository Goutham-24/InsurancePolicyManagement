import { useState } from "react";
import axios from "axios";
import styles from "./Admin-css/forms.module.css";
import { toast } from "react-toastify";
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
        .then((res)=>{toast.success(res.data);
                      setCustomerId(0);
        })
        .catch((err)=>{
                      let store = err?.response?.data;
                                              
                      if(typeof store === "string"){
                        toast.error(err?.response.data);
                      }
                      else{
                        toast.error(err?.response.data.Body);
                    }
        });
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