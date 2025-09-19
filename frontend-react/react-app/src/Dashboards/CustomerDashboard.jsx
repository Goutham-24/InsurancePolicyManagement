import { Link, Navigate, Outlet, Route, Routes, useNavigate } from "react-router-dom";
import AddPolicy from "../AdminComponents/AddPolicy";
import PrivateRoute from "../Auth/PrivateRoute";
import CustomerProfile from "../CustomerComponents/CustomerProfile";
import styles from "./Dashboard-css/navbar.module.css"
function CustomerDashboard(){
    const navigate = useNavigate();

    function logoutMethod(){
        localStorage.removeItem("keyToken");
        localStorage.removeItem("userrole");
        navigate("/Login");
    }
    return(
        <>
        <div className={styles.container}>
        <nav className={styles.customerNav}> 
            <Link to={"update-profile"}><button className={styles.btns1}>Profile</button></Link>
            <Link to={"All-Policies"}><button className={styles.btns}>All Policies</button></Link>
            <Link to={"My-policy"}><button className={styles.btns}>My policy</button></Link>
            <Link to={"My-Claims"}><button className={styles.btns}>My Claims</button></Link>
            <Link to={"My-Appeal"}><button className={styles.btns}>claim appeal</button></Link>
            
            <button onClick={logoutMethod} className={styles.logout}>logout</button>
        </nav>

        <div className={styles.dash}>
            <Outlet/>
        </div>
        
        </div>
        </>
    )
}
export default CustomerDashboard;