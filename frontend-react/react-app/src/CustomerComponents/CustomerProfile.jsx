import { useEffect, useState } from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";

function CustomerProfile(){
    const Navigate = useNavigate();
    const [profile,setProfile] = useState({ 
                                            "userName": "",
                                            "userAge": "",
                                            "userPhonenumber": ""

    });

    useEffect(()=>{
        axios.get(`http://localhost:8089/CustomerAccess/getProfile`,{
            headers: {
                Authorization: `Bearer ${localStorage.getItem("keyToken")}`
            }
        })
        .then((res)=>{console.log(res.data);
                      if(res){
                        setProfile({
                                "userName":res.data.userName,
                                "userAge":res.data.userAge,
                                "userPhonenumber":res.data.userPhonenumber
                        });
                      }

                      console.log(profile);
        });
    },[]);

    function saveProfile(e){
        setProfile({
                    ...profile,
                    [e.target.name]:e.target.value
                    
    });
    }

    function updateProfile(e){
        e.preventDefault();
        axios.put(`http://localhost:8089/CustomerAccess/setProfile`,profile,{
            headers: {
                Authorization: `Bearer ${localStorage.getItem("keyToken")}`
            }
        })
        .then((res)=>{alert(res.data);
                      Navigate("/customer-dashboard");
        });

    }

    return(
        <>
        <div>
            <p>The Profile</p>
            <form onSubmit={updateProfile}>
                <input type="text" name="userName" value={profile.userName} onChange={saveProfile} required/>
                <input type="number" name="userAge" value={profile.userAge} onChange={saveProfile} required/>
                <input type="text" name="userPhonenumber" value={profile.userPhonenumber} onChange={saveProfile} required/>
                <button type="submit">Save</button>
            </form>
            
        </div>
        </>
    )
}
export default CustomerProfile;