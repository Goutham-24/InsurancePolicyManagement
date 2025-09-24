import { useEffect, useState } from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import styles from "./customers-css/form.module.css";
import { toast } from "react-toastify";
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
                     
        })
        .catch((err)=>{toast.error("something went wrong");});
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
        .then((res)=>{toast.success(res.data);
                      Navigate("/customer-dashboard/All-Policies");
        })
        .catch((err)=>{console.log(err?.response.data.userPhonenumber);
                       let store = err?.response.data;
                       Object.values(store).forEach((val)=>{toast.error(val)});
        });

    }

    return(
        <>
        <div className={styles.container}>
            <h2>PROFILE</h2>
            <form onSubmit={updateProfile}>
                <div className={styles.inputholder}>
                <label htmlFor="userName">Username</label>
                <input type="text" name="userName" value={profile.userName} onChange={saveProfile} placeholder="Enter username" required/>
                </div>

                <div className={styles.inputholder}>
                <label htmlFor="userAge">Age</label>
                <input type="number" name="userAge" value={profile.userAge} onChange={saveProfile} placeholder="Enter age" required/>
                </div>

                <div className={styles.inputholder}>
                <label htmlFor="userPhonenumber">Phone Number</label>
                <input type="text" name="userPhonenumber" value={profile.userPhonenumber} onChange={saveProfile} placeholder="Enter phonenumber" required/>
                </div>

                <button type="submit" className={styles.submit}>Save</button>
            </form>
            
        </div>
        </>
    )
}
export default CustomerProfile;