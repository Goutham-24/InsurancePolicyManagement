import { Navigate } from "react-router-dom";

function PrivateRoute({children,role}){
      const token = localStorage.getItem("keyToken");
      const rolebase = localStorage.getItem("userrole");

      if(!token){
        console.log(" no token");
        return <Navigate to="/Login"/>;
      }
      else if(rolebase && rolebase !== role){
        console.log("no role");
        return <Navigate to="/Login"/>;
      }
      
      return children;
}

export default PrivateRoute;