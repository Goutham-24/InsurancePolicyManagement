import { Link, Navigate, Outlet, Route, Routes, useNavigate } from "react-router-dom";
import AddPolicy from "../AdminComponents/AddPolicy";
import PrivateRoute from "../Auth/PrivateRoute";
import CustomerProfile from "../CustomerComponents/CustomerProfile";
function CustomerDashboard(){
    const navigate = useNavigate();

    function logoutMethod(){
        localStorage.removeItem("keyToken");
        localStorage.removeItem("userrole");
        navigate("/Login");
    }
    return(
        <>
        <p>roronava zoro</p>
        <button onClick={logoutMethod}>logout</button>

        <nav>
            <Link to={"All-Policies"}>All Policies</Link>
            <Link to={"add-policy"}><button>My policy</button></Link>
            <button>My Claims</button>
            <Link to={"update-profile"}>profile</Link>
        </nav>

        <Outlet/>

        </>
    )
}
export default CustomerDashboard;