import { useNavigate } from "react-router-dom";

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
        </>
    )
}
export default AdminDashboard;