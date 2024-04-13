import React from 'react';
import 'antd/dist/reset.css';
import './index.css';
import { Form, Input, Button, Checkbox } from 'antd';
import { UserOutlined } from '@ant-design/icons';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';



function Login() {
    const navigate = useNavigate();

const handleSubmit = (values) => {

    console.log(values.username);

    let data = JSON.stringify({
      "username": values.username || "test",
      "password": values.password || "test1234"
    });
    
    let config = {
      method: 'post',
      maxBodyLength: Infinity,
      url: 'http://localhost:8080/api/login',
      headers: { 
        'Content-Type': 'application/json'
      },
      data : data
    };
    
    axios.request(config)
    .then((response) => {
      console.log(JSON.stringify(response.data));
      navigate("/homepage");
    })
    .catch((error) => {
      console.log(error);
    });
    
      };

    return (
        <div >
 <Form
 className="login-form"
 initialValues={{ remember: true }}
 >
        <div style={{fontSize: "x-large", alignItems: "center"}}>Login</div>
        <Form.Item
        label="Username"
        name="username"
        rules={[{ required: true, message: 'Please input your username!' }]}
        >
        <Input size="large" placeholder="username" prefix={<UserOutlined />}
        />
        </Form.Item>
        <Form.Item
        label="Password"
        name="password"
        rules={[{ required: true, message: 'Please input your password!' }]}
        >
        <Input type = "password" size="large" placeholder="password" prefix={<UserOutlined />}
        />
        </Form.Item>
        
        <Form.Item
        name="remember"
        >
         <Checkbox>Remember me</Checkbox>

        </Form.Item>
          <a className="login-form-forgot" href="">
            Forgot password
          </a>
          <Button
            type="primary"
            htmlType="submit"
            className="login-form-button"
            onClick={handleSubmit}
          >
            Log in
          </Button>
          Or <a href="">register now!</a>
      </Form>
      </div>
    );
  }


export default Login;