import { Outlet, useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import styles from "./Dashboard-css/navbar.module.css";
function AdminDashboard(){

    const navigate = useNavigate();

    function logoutMethod(){
        localStorage.removeItem("keyToken");
        localStorage.removeItem("userrole");
        navigate("/Login");
    }
    return(
        <>
        
        <nav className={styles.customerNav}>
        <Link to={"Add-Policy"}><button className={styles.btns}>Add Policy</button></Link>
        <Link to={"Claim-view"}><button className={styles.btns}>All Claims</button></Link>
        <Link to={"AgentConversion"}><button className={styles.btns}>Customer to Agent</button></Link>
        <Link to={"AdminConversion"}><button className={styles.btns}>Agent to Admin</button></Link>
        <button onClick={logoutMethod}className={styles.logout}>logout</button>
        </nav>
        <div className={styles.dash}>
        <Outlet/>
        </div>
        </>
    )
}
export default AdminDashboard;