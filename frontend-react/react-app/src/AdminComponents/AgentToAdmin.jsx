import { useState } from "react";
import axios from "axios";
import styles from "./Admin-css/forms.module.css";
import { toast } from "react-toastify";
function AgentToAdmin(){
    const [AgentId,setAgentId] = useState(0);

    function storeId(e){
        setAgentId(e.target.value);
    }
    function converter(e){
        e.preventDefault();

        axios.put(`http://localhost:8089/AdminAccess/AgentToAdmin/${AgentId}`,{},{
            headers: {
                Authorization: `Bearer ${localStorage.getItem("keyToken")}`
            }
        })
        .then((res)=>{toast.success(res.data);
                      setAgentId(0);
        })
        
        .catch((err) =>{
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
            <h2>AGENT CONVERTOR</h2>
        <form onSubmit={converter}>
            <div className={styles.inputholder}>
            <label htmlFor="">Agent to Admin</label>
            <input type="number" name="AgentId" value={AgentId} onChange={storeId} placeholder="Enter Customer ID to Convert" required />
            </div>

            <button type="submit" className={styles.submit}>Convert</button>
        </form>

        </div>
        </>
    )
}
export default AgentToAdmin;