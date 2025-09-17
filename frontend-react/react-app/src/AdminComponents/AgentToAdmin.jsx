import { useState } from "react";
import axios from "axios";

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
        .then((res)=>{console.log("Converted to Admin");
                      setAgentId(0);
        });
    }

    return(
        <>
        <form onSubmit={converter}>
            <label htmlFor="">Agent to Admin</label>
            <input type="number" name="AgentId" value={AgentId} onChange={storeId} placeholder="Enter Customer ID to Convert" required />
            <button type="submit">Convert</button>
        </form>
        </>
    )
}
export default AgentToAdmin;