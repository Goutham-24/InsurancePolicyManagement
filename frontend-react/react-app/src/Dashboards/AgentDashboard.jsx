import { Outlet, useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
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
        <Link to={"Customer-Claims"}><button> View Customer Claims</button></Link>
        <Outlet/>
        </>
    )
}
export default AgentDashboard;