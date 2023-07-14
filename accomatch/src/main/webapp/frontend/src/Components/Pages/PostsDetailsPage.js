import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import './PostsDetailsPage.css';
import "react-responsive-carousel/lib/styles/carousel.min.css";
import {useNavigate}  from 'react-router-dom';
import { Carousel } from 'react-responsive-carousel';
import { Link, useNavigate } from 'react-router-dom';
export const PostsDetailsPage = () => {

  const navigate =useNavigate();
  const { applicationId } = useParams();
  const [alreadyApplied,setAlreadyApplied] = useState(false);
  const [post, setPost] = useState(null);
  const [images, setImages] = useState([]);
  const [foodPreferences, setFoodPreferences] = useState([]);
  const [genderPreferences, setGenderPreferences] = useState([]);
  const [errMsg, setErrMsg] =useState ('');
  const [success, setSuccess] = useState(false);
  const handleApplySubmit =async () => {
    let bodyObj = {
        user_id:4,
        application_id:applicationId,
        status:"Pending"
    }

    fetch("http://localhost:8080/api/applicant/apply", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(bodyObj),
    })
    .then((response) => {
        console.log(response);
        if(response.status===200){
            navigate("/posts");
        }
        return response.text(); // Read the response data as text
    })
    .then((data) => {
        console.log(data); // Log the response data
        if (data === "success") {
        setSuccess(true);
        } else {
        setErrMsg("Login failed. Please try again."); // Set an appropriate error message
        }
    })
    .catch((error) => {
        setErrMsg("An error occurred. Please try again."); // Set an appropriate error message
    });
    }
  useEffect(() => {
    const isUserAlreadyApplied = async ()=>{
      let bodyObj = {
        user_id:4,
        application_id:applicationId
    }

    fetch("http://localhost:8080/api/applicant/isApplied", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(bodyObj),
    })
    .then((response) => {
        console.log(response);
        return response.text(); // Read the response data as text
    })
    .then((data) => {
        console.log(data); // Log the response data
        if (data === "success") {
        setSuccess(true);
        } else {
        setErrMsg("Login failed. Please try again."); // Set an appropriate error message
        }
    })
    .catch((error) => {
        setErrMsg("An error occurred. Please try again."); // Set an appropriate error message
    });
    }
    const fetchPostDetails = async () => {
      console.log(applicationId);
      try {
        const postResponse = await axios.get(`http://localhost:8080/api/leaseowner/dashboard/get/post/details/${applicationId}`);
        setPost(postResponse.data);
console.log(postResponse.data)
        const imagesResponse = await axios.get(`http://localhost:8080/api/leaseowner/dashboard/get/list/images/${applicationId}`);
        setImages(imagesResponse.data);

        const foodPreferencesResponse = await axios.get(`http://localhost:8080/api/leaseowner/dashboard/get/list/food/${applicationId}`);
        setFoodPreferences(foodPreferencesResponse.data);

        const genderPreferencesResponse = await axios.get(`http://localhost:8080/api/leaseowner/dashboard/get/list/gender/${applicationId}`);
        setGenderPreferences(genderPreferencesResponse.data);
      } catch (error) {
        console.log(error);
      }
    };

    fetchPostDetails();
  }, [applicationId]);

  if (!post) {
    return <div>Loading...</div>;
  }
  const handleApplicantClick = (userId) => {
    navigate(`/leaseapplicantview/${userId}`);
  };
  return (
    <div className="details-container">
      <div className="details-title">
        <h2>{post.title}</h2>
        <h4>{post.subtitle}</h4>
      </div>

      <h3>Images</h3>
      <div className="carousel-container">
        <Carousel>
          {images.map((image, index) => (
            <div key={index}>
              <img src={image} alt={`Image ${index + 1}`} />
            </div>
          ))}
        </Carousel>
      </div>

      <div className="details-section">
        <h3>Details</h3>
        <div className="details-list-container">
          <ul>
            <li>
              <span className="details-label">Address:</span> {post.address}
            </li>
            <li>
              <span className="details-label">Location:</span> {post.locationCity}
            </li>
            <li>
              <span className="details-label">Size:</span> {post.size}
            </li>
            <li>
              <span className="details-label">Room Type:</span> {post.roomType}
            </li>
            <li>
              <span className="details-label">Rent:</span> {post.rent}
            </li>
            <li>
              <span className="details-label">Other Preferences:</span> {post.otherPreferences}
            </li>
            <li>
              <span className="details-label">Start Date:</span> {post.startDate}
            </li>
          </ul>
        </div>
      </div>

      <div className="preferences-section">
        <h3>Food Preferences</h3>
        <div className="preferences-container">
          {foodPreferences.map((preference, index) => (
            <span key={index} className="preference preference-food">{preference}</span>
          ))}
          
        </div>
      </div>

      <div className="preferences-section">
        <h3>Gender Preferences</h3>
        <div className="preferences-container">
         
          {genderPreferences.map((preference, index) => (
            <span key={index} className="preference preference-gender">{preference}</span>
          ))}
        </div>
      </div>
      {alreadyApplied ?
      {/* Apply button */}
        (<button className="apply-button" onClick={()=>handleApplySubmit()}>Apply</button>) : 
        (<button className="chat-button">Chat</button>)
      }

      {/* Applicant button */}
      <button onClick={() => handleApplicantClick(post.leaseholderApplicationId)}>Applicant</button>
    </div>
      
  );
};
