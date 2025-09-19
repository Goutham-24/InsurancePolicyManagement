import { Outlet, useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import styles from "./Dashboard-css/navbar.module.css";
function AgentDashboard(){

    const navigate = useNavigate();

    function logoutMethod(){
        localStorage.removeItem("keyToken");
        localStorage.removeItem("userrole");
        navigate("/Login");
    }
    return(
        <>
        <nav className={styles.customerNav}>
            <Link to={"Customer-Claims"}><button className={styles.btns}> View Customer Claims</button></Link>
            <button onClick={logoutMethod} className={styles.logout}>logout</button>
        </nav>
        
        
        <div className={styles.dash}>
        <Outlet/>
        </div>
        </>
    )
}
export default AgentDashboard;