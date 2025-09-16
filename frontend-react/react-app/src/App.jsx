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
import CustomerPolicy from './CustomerComponents/CustomerPolicy';
import CustomerClaims from './CustomerComponents/CustomerClaims';
import CustomerClaimAppeal from './CustomerComponents/CustomerClaimAppeal';
import AgentViewClaims from './AgentComponents/AgentViewClaims';
import AdminClaimView from './AdminComponents/AdminClaimView';

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
          <Route path='My-policy' element={<CustomerPolicy/>}/>
          <Route path='My-Claims' element={<CustomerClaims/>}/>
          <Route path='My-Appeal' element={<CustomerClaimAppeal/>}/>

      </Route>
    
        
      <Route path='/agent-dashboard' element= {
        <PrivateRoute role="ROLE_AGENT">
          <AgentDashboard/>
        </PrivateRoute>
        }>
          <Route path='Customer-Claims' element={<AgentViewClaims/>}/>
      </Route>


      <Route path='/admin-dashboard' element= {
        <PrivateRoute role="ROLE_ADMIN">
          <AdminDashboard/>
        </PrivateRoute>
        
        }>
          <Route path='Add-Policy' element={<AddPolicy/>}/>
          <Route path='Claim-view' element={<AdminClaimView/>}/>

      </Route>

        <Route path='*' element={<Login/>}/>
    </Routes>
    

    
    </>
  )
}

export default App;
