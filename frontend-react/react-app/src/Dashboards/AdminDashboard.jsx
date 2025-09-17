import { Outlet, useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";

function AdminDashboard(){

    const navigate = useNavigate();

    function logoutMethod(){
        localStorage.removeItem("keyToken");
        localStorage.removeItem("userrole");
        navigate("/Login");
    }
    return(
        <>
        <p>this is admin dash</p>
        <button onClick={logoutMethod}>logout</button>

        <Link to={"Add-Policy"}><button>Add Policy</button></Link>
        <Link to={"Claim-view"}><button>All Claims</button></Link>
        <Link to={"AgentConversion"}><button>Customer to Agent</button></Link>
        <Link to={"AdminConversion"}><button>Agent to Admin</button></Link>
        <Outlet/>
        </>
    )
}
export default AdminDashboard;