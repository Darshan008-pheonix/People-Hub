import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import '../Styles/OTPModel.css';
import { useState } from 'react';
import { toast } from 'react-toastify';
import axios from 'axios';
function OTPModel(data) {
    let [otp,setotp] = useState("");
    let isModal = data.data[0]; // isModal taken as props from the Login Page

    console.log(data);

    function changeisModel(){
      isModal(false)
    }


let config = {
  method: 'GET',
  maxBodyLength: Infinity,
  url: 'http://localhost:8080/auth',
  headers: { 
    'eid': data.data[1], 
    'pswd': data.data[2], 
    'otp': otp
  }
};



    function ValidateOTP(e){
      e.preventDefault();
      axios.request(config)
      .then((response) => {
        console.log(response.data);
        sessionStorage.setItem('JWT',`Bearer ${response.data}`);
        toast.success("Logined Success")
      })
      .catch((error) => {
        console.log(error);
        toast.error("Invalid OTP")
      });
    }
    
  return (
    <div className="Modal_design">

    
    <div
      className="OTPModel"
    >
      <Modal.Dialog>
        <Modal.Header closeButton>
          <Modal.Title style={{"color":"white"}}>Modal title</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <form onSubmit={ValidateOTP} action="">
            <input className=' m-3 ' value={otp} onChange={(e)=>{setotp(e.target.value)}} type="text" placeholder='Enter the OTP ' />
            <button type='submit' className=' m-3 btn btn-primary'>check OTP</button>
          </form>
        </Modal.Body>

        <Modal.Footer>
          <Button onClick={changeisModel}  variant="secondary">Close</Button>
        </Modal.Footer>
      </Modal.Dialog>
    </div>
    </div>
  );
}

export default OTPModel;