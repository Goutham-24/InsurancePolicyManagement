import { useNavigate } from "react-router-dom";
function AgentDashboard(){

    const navigate = useNavigate();

    function logoutMethod(){
        localStorage.removeItem("keyToken");
        localStorage.removeItem("userrole");
        navigate("/Login");
    }
    return(
        <>
        <p>this is agent dash</p>
        <button onClick={logoutMethod}>logout</button>
        </>
    )
}
export default AgentDashboard;