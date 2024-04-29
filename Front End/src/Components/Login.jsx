import { useState } from 'react';
import '../Styles/Login.css'
import OTPModel from './OTPModel';
import {toast} from 'react-toastify'
import axios from 'axios';
const Login = () => {
    let [value, setusername] = useState("")
    let [password, setpassword] = useState("")
    let [isModel, setidModal] = useState(false)
    let [eid,seteid] = useState("")
    let config = {
        method: 'GET',
        maxBodyLength: Infinity,
        url: 'http://localhost:8080/loginpage',
        headers: { 
          'value': value, 
          'password': password
        }
      };
      
      
      
      
    function validateAdmin(e) {
        e.preventDefault();
        axios.request(config)
        .then((res)=>{
            console.log(res.data);
            seteid(res.data)
            setidModal(true)
            toast.success("Dish added successfully")
        })
        .catch((err)=>{
            console.log(err);
            toast.error("Error adding dish ")
        })  

    }
    return (
        <div classNameName="login">
            <section className="h-100 gradient-form" style={{ "background-color": "#eee" }}>
                <div className="container py-5 h-100">
                    <div className="row d-flex justify-content-center align-items-center h-100">
                        <div className="col-xl-10">
                            <div className="card rounded-3 text-black">
                                <div className="row g-0">
                                    <div className="col-lg-6">
                                        <div className="card-body p-md-5 mx-md-4">

                                            <div className="text-center">
                                                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/lotus.webp"
                                                    style={{ "width": "185px" }} alt="logo" />
                                                <h4 className="mt-1 mb-5 pb-1">We are The Lotus Team</h4>
                                            </div>

                                            <form onSubmit={validateAdmin}>
                                                <p>Please login to your account</p>

                                                <div data-mdb-input-init className="form-outline mb-4">
                                                    <input value={value} onChange={(e) => { setusername(e.target.value) }} type="text" id="form2Example11" className="form-control"
                                                        placeholder="Phone number or email address" />
                                                    <label className="form-label" for="form2Example11">Username</label>
                                                </div>

                                                <div data-mdb-input-init className="form-outline mb-4">
                                                    <input value={password} onChange={(e) => { setpassword(e.target.value) }} type="password" id="form2Example22" className="form-control" />
                                                    <label className="form-label" for="form2Example22">Password</label>
                                                </div>

                                                <div className="text-center pt-1 mb-5 pb-1">
                                                    <button data-mdb-button-init data-mdb-ripple-init className="btn btn-primary btn-block py-3 fa-lg gradient-custom-2 mb-3" type="submit">Log
                                                        in</button>
                                                    <a className="text-muted" href="#!">Forgot password?</a>
                                                </div>



                                            </form>

                                        </div>
                                    </div>
                                    <div className="col-lg-6 d-flex align-items-center gradient-custom-2">
                                        <div className="text-white px-3 py-4 p-md-5 mx-md-4">
                                            <h4 className="mb-4">We are more than just a company</h4>
                                            <p className="small mb-0">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                                tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                                                exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            {isModel && <OTPModel data={[setidModal,eid,password]}/>}
        </div>
    );
}

export default Login;