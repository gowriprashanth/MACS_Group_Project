import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css'
import { Form, Button } from 'react-bootstrap'

function CreateAD() {
    return ( 
        <div className="form-container">
            <Form className="signup-form">
                <Form.Group>
                    <Form.Control  size= "sm" type="text" placeholder="Title" name="title"></Form.Control>
                    <Form.Control  type="text" placeholder="SubTitle" name="title" ></Form.Control>

                    <Button className="submit-button" value="submit" type="submit">submit</Button>
                </Form.Group>
            </Form>
        </div>
     );
}

export default CreateAD;