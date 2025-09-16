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
        <button onClick={logoutMethod}>logout</button>

        <nav>
            <Link to={"All-Policies"}><button>All Policies</button></Link>
            <Link to={"My-policy"}><button>My policy</button></Link>
            <Link to={"My-Claims"}><button>My Claims</button></Link>
            <Link to={"My-Appeal"}><button>claim appeal</button></Link>
            <Link to={"update-profile"}><button>Profile</button></Link>
        </nav>

        <Outlet/>

        </>
    )
}
export default CustomerDashboard;