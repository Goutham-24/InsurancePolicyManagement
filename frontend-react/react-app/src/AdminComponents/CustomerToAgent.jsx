import { useState } from "react";
import axios from "axios";

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
        <form onSubmit={converter}>
            <label htmlFor="">Customer to Agent</label>
            <input type="number" name="CustomerId" value={CustomerId} onChange={storeId} placeholder="Enter Customer ID to Convert" required />
            <button type="submit">Convert</button>
        </form>
        </>
    )
}
export default CustomerToAgent;