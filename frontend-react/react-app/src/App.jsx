import { useState } from 'react'
import './App.css'
import axios from 'axios'
import Login from './Auth/Login';
import AddPolicy from './AdminComponents/AddPolicy';
import { Routes } from 'react-router-dom';
import { Route,Navigate} from 'react-router-dom';
import CustomerDashboard from './Dashboards/CustomerDashboard';
import AgentDashboard from './Dashboards/AgentDashboard';
import AdminDashboard from './Dashboards/AdminDashboard';
import PrivateRoute from './Auth/PrivateRoute';
import Signup from './Auth/Signup';
import CustomerProfile from './CustomerComponents/CustomerProfile';
import AllPolicies from './CustomerComponents/AllPolicies';

function App() {

  const [test,settest] = useState();

  function start(){
    axios.get(`http://localhost:8089/Authentication/test`)
      .then((response)=>{settest(response.data)
      console.log("this is the main app");
      });
  }


  return (
    <>


    <Routes>
      <Route path='/Login' element= {<Login/>}/>
      <Route path='/Signup' element= {<Signup/>}/>

      <Route path='/customer-dashboard' element= {
        <PrivateRoute role="ROLE_CUSTOMER">
          <CustomerDashboard/>
        </PrivateRoute>
        }>
          <Route path='update-profile' element={<CustomerProfile/>}/>
          <Route path='All-Policies' element={<AllPolicies/>}/>
      </Route>
    
        
      <Route path='/agent-dashboard' element= {
        <PrivateRoute role="ROLE_AGENT">
          <AgentDashboard/>
        </PrivateRoute>
        
        }/>
      <Route path='/admin-dashboard' element= {
        <PrivateRoute role="ROLE_ADMIN">
          <AdminDashboard/>
        </PrivateRoute>
        
        }/>

        <Route path='*' element={<Login/>}/>
    </Routes>
    

    
    </>
  )
}

export default App;
